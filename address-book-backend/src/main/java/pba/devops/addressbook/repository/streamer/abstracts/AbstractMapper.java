package pba.devops.addressbook.repository.streamer.abstracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class AbstractMapper implements IMapper {

    private ObjectMapper mapper;

    public AbstractMapper() {
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public ObjectMapper mapper() {
        return mapper;
    }
}
