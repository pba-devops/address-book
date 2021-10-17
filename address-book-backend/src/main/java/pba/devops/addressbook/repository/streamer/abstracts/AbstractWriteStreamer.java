package pba.devops.addressbook.repository.streamer.abstracts;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractWriteStreamer extends AbstractReadStreamer implements IWriteStreamer {

    private JsonGenerator writer;

    public AbstractWriteStreamer(String dataFile) {
        super(dataFile);
        try {
            this.writer = this.mapper().getFactory().createGenerator(new FileOutputStream(dataFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JsonGenerator writer() {
        return writer;
    }
}
