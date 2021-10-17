package pba.devops.addressbook.repository.predicate;

import pba.devops.addressbook.repository.predicate.abstracts.AbstractPredicate;

public class NoPredicate<ENTITY> extends AbstractPredicate<ENTITY> {

    public NoPredicate(String dataFile) {
        super(dataFile);
    }

    @Override
    public Boolean relevant(ENTITY entity) {
        return Boolean.TRUE;
    }
}
