package pba.devops.addressbook.utils;

import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;
import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.enums.FamilyRelationship;

import java.util.Arrays;
import java.util.Optional;

public enum ContactEnum {

    ID("ID") {
        @Override
        public Object convert(String value) {
            return ContactEnum.longConvert(value);
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setId((Long)convert(value));
        }
    },
    NAME("NAME") {
        @Override
        public Object convert(String value) {
            return value;
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setName(convert(value).toString());
        }
    },
    SURNAME("SURNAME") {
        @Override
        public Object convert(String value) {
            return value;
        }

        @Override
        public void updateField(Contact contact, String value) {
            contact.setSurname(convert(value).toString());
        }
    },
    PHONE_NUMBER("PHONE") {
        @Override
        public Object convert(String value) {
            return value;
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setPhone(convert(value).toString());
        }
    },
    EMAIL("EMAIL") {
        @Override
        public Object convert(String value) {
            return value;
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setEmail(convert(value).toString());
        }
    },
    YEAR_OF_BIRTH("AGE") {
        @Override
        public Object convert(String value) {
            return ContactEnum.integerConvert(value);
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setAge((Integer)convert(value));
        }
    },
    HAIR_COLOR("HAIR") {
        @Override
        public Object convert(String value) {
            return value;
        }
        @Override
        public void updateField(Contact contact, String value) {
            contact.setHair(convert(value).toString());
        }
    },
    YEAR_OF_MEETING("MEETING") {
        @Override
        public Object convert(String value) {
            return ContactEnum.integerConvert(value);
        }
        @Override
        public void updateField(Contact contact, String value) {
            if(contact instanceof Friend) {
                Friend friend = (Friend)contact;
                friend.setMeeting((Integer)convert(value));
            }
        }
    },
    FAMILY_RELATIONSHIP("RELATIONSHIP") {
        @Override
        public Object convert(String value) {
            return FamilyRelationship.value(value);
        }
        @Override
        public void updateField(Contact contact, String value) {
            if(contact instanceof Family) {
                Family family = (Family)contact;
                family.setRelationship(
                    FamilyRelationship.value(value));
            }
        }
    };

    final private String fieldName;

    private ContactEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean convertable(String value) {
        return convert(value) != null;
    }

    abstract public Object convert(String value);
    abstract public void updateField(Contact contact, String value);

    public String fieldName() {
        return fieldName;
    }

    static public Optional<ContactEnum> value(String fieldName) {
        return
            Arrays.stream(values())
                .filter(contactEnum ->
                    contactEnum.fieldName.equalsIgnoreCase(fieldName))
                        .findFirst();
    }

    static private Object longConvert(String value) {
        try {
            return Long.valueOf(Integer.valueOf(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static private Object integerConvert(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
