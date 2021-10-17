package pba.devops.addressbook.repository.writer.abstracts;

import pba.devops.addressbook.repository.streamer.abstracts.AbstractWriteStreamer;

public abstract class AbstractWriter<ENTITY,ID> extends AbstractWriteStreamer implements IWriter<ENTITY,ID> {

    private ENTITY entity;

    public AbstractWriter(String dataFile, ENTITY entity) {
        super(dataFile);
        this.entity = entity;
    }

    protected ENTITY entity() {
        return entity;
    }
}