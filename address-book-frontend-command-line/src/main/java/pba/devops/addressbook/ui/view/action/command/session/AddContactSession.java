package pba.devops.addressbook.ui.view.action.command.session;

import pba.devops.addressbook.model.Acquaintance;
import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.enums.Category;
import pba.devops.addressbook.ui.view.action.command.session.abstracts.ISession;
import pba.devops.addressbook.ui.view.action.command.session.abstracts.IStep;
import pba.devops.addressbook.ui.view.action.command.session.abstracts.Step;
import pba.devops.addressbook.utils.ContactEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AddContactSession implements ISession<Contact> {

    final private List<IStep> steps;

    public AddContactSession() {

        this.steps = new ArrayList<IStep>();
        this.steps.add(new Step(ContactEnum.NAME, "name"));
        this.steps.add(new Step(ContactEnum.SURNAME,"surname"));
        this.steps.add(new Step(ContactEnum.PHONE_NUMBER, "phone"));
        this.steps.add(new Step(ContactEnum.EMAIL, "email"));
        this.steps.add(new Step(ContactEnum.YEAR_OF_BIRTH, "birth day (optional)", IStep.Filling.OPTIONAL));
        this.steps.add(new Step(ContactEnum.HAIR_COLOR, "hair color (optional)", IStep.Filling.OPTIONAL));
        this.steps.add(new Step(
                null,
                "Category [ acquaintance - friend - family ]",
                Arrays.asList("acquaintance", "friend", "family"),
                Arrays.asList(
                        new Step(ContactEnum.FAMILY_RELATIONSHIP, "family", "relationship [ mather - father - son - daughter ]"),
                        new Step(ContactEnum.YEAR_OF_MEETING, "friend", "year of first meeting"))));
    }

    @Override
    public Stream<IStep> steps() {
        return steps.stream();
    }

    @Override
    public Contact entity() {

        Contact contact =
                Category.value(steps.get(6).value().toString()).contactInstance();
        steps.get(0).contactEnum().updateField(contact, steps.get(0).value().toString());
        steps.get(1).contactEnum().updateField(contact, steps.get(1).value().toString());
        steps.get(2).contactEnum().updateField(contact, steps.get(2).value().toString());
        steps.get(3).contactEnum().updateField(contact, steps.get(3).value().toString());
        steps.get(4).contactEnum().updateField(contact, steps.get(4).value().toString());
        steps.get(5).contactEnum().updateField(contact, steps.get(5).value().toString());

        if(contact instanceof Friend) {
            Friend friend = (Friend)contact;
            IStep step =
                    (IStep)steps.get(6).subSteps().filter(
                            subStep -> "friend".equalsIgnoreCase(((IStep)subStep).stepParent()))
                            .findFirst().orElse(null);
            step.contactEnum().updateField(friend, step.value().toString());
            return friend;
        }
        if(contact instanceof Family) {
            Family family = (Family)contact;
            IStep step =
                    (IStep)steps.get(6).subSteps().filter(
                            subStep -> "family".equalsIgnoreCase(((IStep)subStep).stepParent()))
                            .findFirst().orElse(null);
            step.contactEnum().updateField(family, step.value().toString());
            return family;
        }
        return (Acquaintance)contact;
    }
}
