package pba.devops.addressbook.model.abstracts;

public interface IIdentifiable<ID> {

    ID getId();
    void setId(ID id);
}
