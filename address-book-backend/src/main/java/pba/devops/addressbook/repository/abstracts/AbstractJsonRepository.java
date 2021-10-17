package pba.devops.addressbook.repository.abstracts;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.repository.predicate.IdPredicate;
import pba.devops.addressbook.repository.predicate.abstracts.IPredicate;
import pba.devops.addressbook.repository.predicate.NamePredicate;
import pba.devops.addressbook.repository.predicate.NoPredicate;
import pba.devops.addressbook.repository.writer.Adder;
import pba.devops.addressbook.repository.writer.abstracts.IWriter;
import pba.devops.addressbook.repository.writer.Remover;
import pba.devops.addressbook.repository.writer.Updater;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJsonRepository<ENTITY extends INamable<ID>,ID> implements IRepository<ENTITY,ID> {

    public AbstractJsonRepository() {
        initDataFile();
    }

    /*
     * Implemented methods
     */

    @Override
    public ENTITY add(final ENTITY addedEntity) {
        return
            (ENTITY) write(
                addedEntity.getId() == null
                    ? new Adder(dataFile(), addedEntity, latestIdRecorder())
                    : new Updater(dataFile(), addedEntity));
    }

    @Override
    public ENTITY update(final ENTITY updatedEntity) {
        return
            (ENTITY) write(
                updatedEntity.getId() != null
                    ? new Updater(dataFile(), updatedEntity)
                    : new Adder(dataFile(), updatedEntity, latestIdRecorder()));
    }

    @Override
    public void remove(final ENTITY removedEntity) {
        write(
            new Remover(dataFile(), removedEntity));
    }

    @Override
    public List<ENTITY> readAll() {
        return
            read(new NoPredicate(dataFile()));
    }

    @Override
    public ENTITY readById(ID id) {
        return
            read(new IdPredicate<ENTITY,ID>(dataFile(), id))
                .stream().findFirst().orElse(null);
    }

    @Override
    public List<ENTITY> readByName(String partOfName) {
        return
            read(new NamePredicate(dataFile(), partOfName));
    }

    /*
     * Abstract methods
     */

    abstract protected String dataFile();
    abstract protected List<ENTITY> sortedEntities(List<ENTITY> entities);
    abstract protected Class<ENTITY> clazz();
    abstract protected ILatestIdRecorder<ID> latestIdRecorder();

    /*
     * Private methods
     */

    private List<ENTITY> read(IPredicate<ENTITY> predicate) {

        // STREAM READER --
        JsonParser reader = predicate.reader();
        ObjectMapper mapper = predicate.mapper();

        List<ENTITY> entities = new ArrayList<ENTITY>();
        // STREAM READER --
        try(reader) {

            beginingOfListChecking(reader);

            // ENTITIES' ITERATION --
            while(reader.nextToken() != JsonToken.END_ARRAY) {
                ENTITY entity = mapper.readValue(reader, clazz());
                if(predicate.relevant(entity)) {
                    entities.add(entity);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sortedEntities(entities);
        }
    }

    private ENTITY write(IWriter<ENTITY,ID> persistrator) {

        // STREAM READER AND WRITER --
        JsonParser reader = persistrator.reader();
        JsonGenerator writer = persistrator.writer();
        ObjectMapper mapper = persistrator.mapper();

        try {

            beginingOfListChecking(reader);

            writer.writeStartArray();

                persistrator.doFirst();

                // ENTITIES' ITERATION --
                while(reader.nextToken() != JsonToken.END_ARRAY) {
                    persistrator.doWhile(mapper.readValue(reader, clazz()));
                }

                persistrator.doLast();

            writer.writeEndArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
                if(writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return
                readById(persistrator.finalId());
        }
    }

    private void beginingOfListChecking(JsonParser reader) throws IOException {
        if(reader.nextToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an array");
        }
    }

    private void initDataFile() {

        File datafile = new File(dataFile());
        if(!datafile.exists()) {
            try(Writer writer = new BufferedWriter(new FileWriter(datafile)).append("[]")) {
                System.out.format("Data file [ %s] has been created.", dataFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
