package pba.devops.addressbook.repository.abstracts;

import java.util.List;

public interface IRepository<ENTITY,ID> {

    ENTITY add(ENTITY addedEntity);
    ENTITY update(ENTITY updatedEntity);
    void remove(ENTITY removedEntity);
    List<ENTITY> readAll();
    ENTITY readById(ID id);
    List<ENTITY> readByName(String partOfName);
}
