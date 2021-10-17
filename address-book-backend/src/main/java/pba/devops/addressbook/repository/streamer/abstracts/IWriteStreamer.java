package pba.devops.addressbook.repository.streamer.abstracts;

import com.fasterxml.jackson.core.JsonGenerator;

public interface IWriteStreamer {

    JsonGenerator writer();
}
