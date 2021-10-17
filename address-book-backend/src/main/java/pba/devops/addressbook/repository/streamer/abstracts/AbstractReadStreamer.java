package pba.devops.addressbook.repository.streamer.abstracts;

import com.fasterxml.jackson.core.JsonParser;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractReadStreamer extends AbstractMapper implements IReadStreamer {

    private JsonParser reader;

    public AbstractReadStreamer(String dataFile) {
        super();
        try {
            this.reader = this.mapper().getFactory().createParser(new FileInputStream(dataFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonParser reader() {
        return reader;
    }
}
