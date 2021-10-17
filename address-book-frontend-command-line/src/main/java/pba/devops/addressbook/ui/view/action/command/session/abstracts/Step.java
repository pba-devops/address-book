package pba.devops.addressbook.ui.view.action.command.session.abstracts;


import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.ui.view.action.command.abstracts.ICommand;
import pba.devops.addressbook.utils.ContactEnum;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Step implements IStep {

    final private String stepParent;
    final private ContactEnum contactEnum;
    final private String fieldInformation;
    final private Filling filling;
    final private List<String> possibleValues;
    final private List<IStep> subSteps;
    private Object value;
    private String errors = "";

    public Step(ContactEnum contactEnum, String fieldInformation) {
        this("", contactEnum, fieldInformation, Filling.MANDATORY, new ArrayList<String>(), new ArrayList<IStep>());
    }

    public Step(ContactEnum contactEnum, String stepParent, String fieldInformation) {
        this(stepParent, contactEnum, fieldInformation, Filling.MANDATORY, new ArrayList<String>(), new ArrayList<IStep>());
    }

    public Step(ContactEnum contactEnum, String fieldInformation, List<IStep> subSteps) {
        this("", contactEnum, fieldInformation, Filling.MANDATORY, new ArrayList<String>(), new ArrayList<IStep>());
    }

    public Step(ContactEnum contactEnum, String fieldInformation, Filling filling) {
        this("", contactEnum, fieldInformation, filling, new ArrayList<String>(), new ArrayList<IStep>());
    }

    public Step(ContactEnum contactEnum, String fieldInformation, List<String> possibleValues, List<IStep> subSteps) {
        this("", contactEnum, fieldInformation, Filling.MANDATORY, possibleValues, subSteps);
    }

    public Step(String stepParent, ContactEnum contactEnum, String fieldInformation, Filling filling, List<String> possibleValues, List<IStep> subSteps) {
        this.stepParent = stepParent;
        this.contactEnum = contactEnum;
        this.fieldInformation = fieldInformation;
        this.filling = filling;
        this.possibleValues = possibleValues;
        this.subSteps = subSteps;
    }

    @Override
    public String stepParent() {
        return stepParent;
    }

    @Override
    public Stream<IStep> subSteps() {
        return subSteps.stream();
    }

    @Override
    public String fieldInformation() {
        return
            !errors.equalsIgnoreCase("")
                ? errors.concat(fieldInformation)
                : fieldInformation;
    }

    @Override
    public ICommand.IJobDone value(Object value) {

        if(contactEnum == null) {
            this.value = value;
            return
                new ICommand.JobDone(value.toString());
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<?> errors =
            contactEnum.fieldName().equalsIgnoreCase("relationship")
                ? validator.validateValue(
                    Family.class,
                    contactEnum.fieldName().toLowerCase(),
                    contactEnum.convert(value.toString()))
                : contactEnum.fieldName().equalsIgnoreCase("meeting")
                    ? validator.validateValue(
                        Friend.class,
                        contactEnum.fieldName().toLowerCase(),
                        contactEnum.convert(value.toString()))
                    : validator.validateValue(
                        Contact.class,
                        contactEnum.fieldName().toLowerCase(),
                        contactEnum.convert(value.toString()));
        if(!errors.isEmpty()) {
            this.errors = String.format("\n\t??? -> [ %s ]", value);
            errors.stream().forEach( error ->
                this.errors.concat((
                    (ConstraintViolation)errors.stream().findFirst().get()).getMessage()).concat("\n"));
            return
                new ICommand.JobDone(Boolean.TRUE, this.errors);
        }
        this.value = value;
        return
            new ICommand.JobDone(this.value);
    }

    @Override
    public ContactEnum contactEnum() {
        return contactEnum;
    }

    @Override
    public Object value() {
        return value;
    }
}
