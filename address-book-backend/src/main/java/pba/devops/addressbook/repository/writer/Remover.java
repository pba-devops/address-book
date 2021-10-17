package pba.devops.addressbook.repository.writer;


import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.repository.writer.abstracts.AbstractWriter;

import java.io.IOException;

public class Remover<ENTITY extends INamable<ID>, ID> extends AbstractWriter<ENTITY, ID> {

    public Remover(String dataFile, ENTITY entity) {
        super(dataFile, entity);
    }

    @Override
    public void doFirst() throws IOException {}

    @Override
    public void doWhile(ENTITY dataFileEntity) throws IOException {

        // WE RETAIN ONLY OTHER ENTITIES --
        Boolean isNotEntityToRemove = !entity().getId().equals(dataFileEntity.getId());
        if(isNotEntityToRemove) {
            mapper().writeValue(writer(), dataFileEntity);
        }
    }

    @Override
    public void doLast() throws IOException {}

    @Override
    public ID finalId() {
        return entity().getId();
    }
}