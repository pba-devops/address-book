package pba.devops.addressbook.repository.writer;

import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.repository.writer.abstracts.AbstractWriter;

import java.io.IOException;

public class Updater<ENTITY extends INamable<ID>,ID> extends AbstractWriter<ENTITY,ID> {

    public Updater(String dataFile, ENTITY entity) {
        super(dataFile, entity);
    }

    @Override
    public void doFirst() throws IOException {}

    @Override
    public void doWhile(ENTITY dataFileEntity) throws IOException {

        Boolean isEntityToUpdate = dataFileEntity.getId().equals(entity().getId());
        mapper().writeValue(writer(),
                isEntityToUpdate
                        ? entity()
                        : dataFileEntity);
    }

    @Override
    public void doLast() throws IOException {}

    @Override
    public ID finalId() {
        return entity().getId();
    }
}
