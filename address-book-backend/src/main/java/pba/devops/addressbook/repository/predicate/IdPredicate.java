package pba.devops.addressbook.repository.predicate;

import pba.devops.addressbook.model.abstracts.IIdentifiable;
import pba.devops.addressbook.repository.predicate.abstracts.AbstractPredicate;

public class IdPredicate<ENTITY extends IIdentifiable, ID> extends AbstractPredicate<ENTITY> {

    final private ID id;

    public IdPredicate(String dataFile, ID id) {
        super(dataFile);
        this.id = id;
    }

    @Override
    public Boolean relevant(ENTITY entity) {
        return
                entity.getId().equals(id);
    }
}
