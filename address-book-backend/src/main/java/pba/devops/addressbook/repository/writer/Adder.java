package pba.devops.addressbook.repository.writer;

import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.repository.abstracts.ILatestIdRecorder;
import pba.devops.addressbook.repository.writer.abstracts.AbstractWriter;

import java.io.IOException;

public class Adder<ENTITY extends INamable<ID>, ID> extends AbstractWriter<ENTITY, ID> {

    final private ILatestIdRecorder<ID> latestIdRecorder;

    public Adder(String dataFile, ENTITY entity, ILatestIdRecorder<ID> latestIdRecorder) {
        super(dataFile, entity);
        this.latestIdRecorder = latestIdRecorder;
    }

    @Override
    public void doFirst() throws IOException {}

    @Override
    public void doWhile(ENTITY dataFileEntity) throws IOException {
        mapper().writeValue(writer(), dataFileEntity);
        latestIdRecorder.record(dataFileEntity.getId());
    }

    @Override
    public void doLast() throws IOException {

        entity().setId(latestIdRecorder.nextId());

        // ADD THE NEW ENTITY IN THE END OF LIST --
        mapper().writeValue(writer(), entity());
    }

    @Override
    public ID finalId() {
        return entity().getId();
    }
}
