package pba.devops.addressbook.ui.view.action.command.session.abstracts;

import pba.devops.addressbook.ui.view.action.command.abstracts.ICommand;
import pba.devops.addressbook.utils.ContactEnum;

import java.util.stream.Stream;

public interface IStep {

    String stepParent();
    Stream<IStep> subSteps();
    String fieldInformation();
    ICommand.IJobDone value(Object value);
    ContactEnum contactEnum();
    Object value();

    enum Filling {
        OPTIONAL,
        MANDATORY;
    }
}
