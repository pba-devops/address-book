package pba.devops.addressbook.dataresolver;

import pba.devops.addressbook.model.abstracts.INamable;

import java.util.List;

public interface IRemote<ENTITY,ID> {

    List<ENTITY> entities() throws RemoteException;
    String delete(ID id);
    String add(ENTITY entity);
    String update(ENTITY entity);
}
