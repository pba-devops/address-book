package pba.devops.addressbook.repository.predicate.abstracts;

import pba.devops.addressbook.repository.streamer.abstracts.IMapper;
import pba.devops.addressbook.repository.streamer.abstracts.IReadStreamer;

public interface IPredicate<ENTITY> extends IReadStreamer, IMapper {

    Boolean relevant(ENTITY entity);
}
