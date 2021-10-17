package pba.devops.addressbook.repository.predicate.abstracts;

import pba.devops.addressbook.repository.streamer.abstracts.AbstractReadStreamer;

public abstract class AbstractPredicate<ENTITY> extends AbstractReadStreamer implements IPredicate<ENTITY> {

    public AbstractPredicate(String dataFile) {
        super(dataFile);
    }
}
