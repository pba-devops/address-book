package pba.devops.addressbook.repository.writer.abstracts;

import pba.devops.addressbook.repository.streamer.abstracts.IMapper;
import pba.devops.addressbook.repository.streamer.abstracts.IReadStreamer;
import pba.devops.addressbook.repository.streamer.abstracts.IWriteStreamer;

import java.io.IOException;

public interface IWriter<ENTITY, ID> extends IReadStreamer, IWriteStreamer, IMapper {

    void doFirst() throws IOException;
    void doWhile(ENTITY dataFileEntity) throws IOException;
    void doLast() throws IOException;
    ID finalId();
}
