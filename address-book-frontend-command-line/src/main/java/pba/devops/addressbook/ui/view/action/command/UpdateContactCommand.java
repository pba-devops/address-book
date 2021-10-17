package pba.devops.addressbook.ui.view.action.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.utils.StringUtils;
import pba.devops.addressbook.ui.view.ContactsView;
import pba.devops.addressbook.ui.view.action.command.abstracts.AbstractCommand;
import pba.devops.addressbook.utils.BeanProviderUtils;
import pba.devops.addressbook.utils.CommandUtils;
import pba.devops.addressbook.utils.ContactEnum;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class UpdateContactCommand extends AbstractCommand<Contact> {

    static final private Integer COMMAND_NAME = 0;
    static final private Integer CONTACT_ID = 1;
    static final private Integer FIELD_NAME = 2;
    static final private Integer FIELD_VALUE = 3;

    public UpdateContactCommand(String commandName) {
        super(commandName);
    }

    @Override
    public Boolean isMyJob(String rawCommand) {

        List<String> command = CommandUtils.niceSplit(rawCommand);

        // 4 parts have to be present
        if(command.size() != 4) {
            return Boolean.FALSE;
        }
        // 1st part has to be equal to commandName
        if(!command.get(COMMAND_NAME).equalsIgnoreCase(commandName)) {
            return Boolean.FALSE;
        }
        // 2th part has to be an Integer
        if(!StringUtils.isInteger(command.get(CONTACT_ID))) {
            return Boolean.FALSE;
        }
        // 2th part has to be a contact id
        if(!BeanProviderUtils.getBean(ContactsView.class)
            .contactExists(Long.valueOf(command.get(CONTACT_ID)))) {
            return Boolean.FALSE;
        }
        // 3th part has to be a field of an entity
        if(ContactEnum.value(command.get(FIELD_NAME)) == null) {
            return Boolean.FALSE;
        }
        // 3th part cannot be ID or CATEGORY
        if(isNotUpdatableField(command.get(FIELD_NAME))) {
            return Boolean.FALSE;
        }
        // 4th part has to be an suitable value of this field
        Optional<ContactEnum> field = ContactEnum.value(command.get(FIELD_NAME));
        return
            field.isPresent() &&
            field.get().convertable(command.get(FIELD_VALUE));
    }

    @Override
    public IJobDone<Contact> doMyJob(String rawCommand) {
        try {

            if(!isMyJob(rawCommand)) {
                return null;
            }

            List<String> command = CommandUtils.niceSplit(rawCommand);
            Contact contact =
                BeanProviderUtils.getBean(ContactsView.class).contact(Long.valueOf(command.get(CONTACT_ID)));
            ContactEnum.value(command.get(FIELD_NAME))
                .ifPresent(contactEnum -> contactEnum.updateField(contact, command.get(FIELD_VALUE)));

            return
                runIfValid(
                    command.get(FIELD_NAME),
                    command.get(FIELD_VALUE),
                    () ->
                        BeanProviderUtils.getBean(ContactRemote.class)
                        .update(contact));
        } catch(Exception e) {
            return null;
        }
    }

    /*
     * Private methods
     */

    private boolean isNotUpdatableField(String fieldName) {

        final var optOfField = ContactEnum.value(fieldName);
        if(optOfField.isEmpty()) {
            return Boolean.FALSE;
        }
        final var field = optOfField.get();
        return
            field.equals(ContactEnum.ID) ||
            field.fieldName() == "CATEGORY";
    }

    private IJobDone<Contact> runIfValid(String fieldName, String fieldValue, Supplier<String> supplier) {

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<?> validate =
                fieldName.equalsIgnoreCase("relationship")
                    ? validator.validateValue(
                        Family.class,
                        fieldName.toLowerCase(),
                        ContactEnum.value(fieldName).get().convert(fieldValue))
                    : fieldName.equalsIgnoreCase("meeting")
                        ? validator.validateValue(
                            Friend.class,
                            fieldName.toLowerCase(),
                            ContactEnum.value(fieldName).get().convert(fieldValue))
                        : validator.validateValue(
                            Contact.class,
                            fieldName.toLowerCase(),
                            ContactEnum.value(fieldName).get().convert(fieldValue));

            if(!validate.isEmpty()) {

                StringBuilder message = new StringBuilder(String.format("\n\t??? -> [ %s ]", fieldValue));
                validate.stream().forEach(contactConstraintViolation -> {
                    message.append(((ConstraintViolation)contactConstraintViolation).getMessage());
                    message.append("\n");
                });
                return
                    new JobDone(Boolean.TRUE, message.toString(),null);
            }
            Contact contact = null;
            try {
                contact = new ObjectMapper().readValue(supplier.get(), Contact.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return
                new JobDone(Boolean.FALSE, "", contact);
    }
}
