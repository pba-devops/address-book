package pba.devops.addressbook.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum FamilyRelationship {

    MOTHER("Mother"),
    FATHER("Father"),
    GRAND_MOTHER("Grand mother"),
    GRAND_FATHER("Grand father"),
    SON("Son"),
    DAUGHTER("Daughter"),
    BROTHER("Brother"),
    SISTER("Sister"),
    AUNT("Aunt"),
    UNCLE("Uncle"),
    NIECE("Niece"),
    NEPHEW("Nephew"),
    COUSIN("Cousin");

    final private String label;

    private FamilyRelationship(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    static public FamilyRelationship value(String value) {
        return
            Arrays.stream(values())
                .filter(familyRelationship -> familyRelationship.label().equalsIgnoreCase(value))
                    .findFirst().orElse(null);
    }
}
