package pba.devops.addressbook.service.abstracts;

import java.util.List;

public interface IContactService<ENTITY,ID> {

    ENTITY add(ENTITY addedEntity);
    ENTITY update(ENTITY updatedEntity);
    void remove(ID removedEntity);
    List<ENTITY> readAll();
    List<ENTITY> readByName(String partOfName);
}
