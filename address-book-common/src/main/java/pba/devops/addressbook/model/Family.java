package pba.devops.addressbook.model;

import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.enums.FamilyRelationship;
import pba.devops.addressbook.model.utils.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Family extends Contact {


    @NotNull(message = "[ Relationship ] is mandatory")
    private FamilyRelationship relationship;

    public Family() {
        super();
    }

    public FamilyRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(FamilyRelationship relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return super.toString() + StringUtils.fixedSizeString(relationship, 40) + "   ";
    }
}
