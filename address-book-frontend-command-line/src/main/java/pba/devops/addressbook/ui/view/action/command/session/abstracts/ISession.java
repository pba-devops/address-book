package pba.devops.addressbook.ui.view.action.command.session.abstracts;

import java.util.stream.Stream;

public interface ISession<ENTITY> {

    Stream<IStep> steps();
    ENTITY entity();
}
