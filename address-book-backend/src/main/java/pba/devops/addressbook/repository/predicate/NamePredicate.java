package pba.devops.addressbook.repository.predicate;

import pba.devops.addressbook.model.abstracts.INamable;
import pba.devops.addressbook.repository.predicate.abstracts.AbstractPredicate;

public class NamePredicate<ENTITY extends INamable> extends AbstractPredicate<ENTITY> {

    final private String partOfName;

    public NamePredicate(String dataFile, String partOfName) {
        super(dataFile);
        this.partOfName = partOfName;
    }

    @Override
    public Boolean relevant(ENTITY entity) {
        return
            entity.getName().toLowerCase().contains(partOfName.toLowerCase());
    }
}
