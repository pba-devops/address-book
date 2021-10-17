package pba.devops.addressbook.repository.streamer.abstracts;

import com.fasterxml.jackson.core.JsonParser;

public interface IReadStreamer {

    JsonParser reader();
}
