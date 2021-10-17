package pba.devops.addressbook.repository.abstracts;

public interface ILatestIdRecorder<ID> {

    void record(ID id);
    ID nextId();
}
