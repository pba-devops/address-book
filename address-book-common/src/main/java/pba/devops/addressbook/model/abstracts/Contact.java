package pba.devops.addressbook.model.abstracts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pba.devops.addressbook.model.Acquaintance;
import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;
import pba.devops.addressbook.model.utils.StringUtils;

import javax.validation.constraints.*;
import java.util.Calendar;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "objectType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Acquaintance.class, name = "acquaintance"),
    @JsonSubTypes.Type(value = Friend.class, name = "friend"),
    @JsonSubTypes.Type(value = Family.class, name = "family")})
public abstract class Contact implements INamable<Long> {

    // Technical field ( but not mandatory ) --
    @Digits(integer = Long.BYTES, fraction = 0, message = "[ Id ] requires an integer is required")
    @Min(value = 1, message = "[ Id ] can not be outside of ( {value} ) and ( " + Long.BYTES + " )")
    @Max(value = Long.BYTES, message = "[ Id ] can not be outside of ( 1 ) and ( {value} )")
    private Long id;

    // Mandatory fields --
    @NotNull(message = "[ Name ] is mandatory")
    @Size(min = 1, message = "[ Name ] is mandatory")
    @Size(max = 20, message = "[ Name ] max ( {max} ) characters")
    private String name;

    @NotNull(message = "[ Surname ] is mandatory")
    @Size(min = 1, message = "[ Surname ] is mandatory")
    @Size(max = 20, message = "[ Surname ] max ( {max} ) characters")
    private String surname;

    @NotNull(message = "[ Phone number ] is mandatory")
    @Size(min = 1, message = "[ Phone number ] is mandatory")
    @Size(max = 20, message = "[ Phone number ] max ( {max} ) numbers")
    @Pattern(regexp = "^[0-9]*$", message = "[ Phone number ] only numbers are valid")
    private String phone;

    @NotNull(message = "[ Email ] is mandatory")
    @Size(min = 1, message = "[ Email ] is mandatory")
    @Size(max = 30, message = "[ Email ] max ( {max} ) characters")
    @Email(message = "[ Email ] should be valid")
    private String email;

    // Optional fields --
    @Digits(integer = 4, fraction = 0, message = "[ Year of birth ] requires an integer")
    private Integer age;

    @Size(max = 10)
    private String hair;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    @Override
    public String toString() {
        return "" +
                "   " + StringUtils.fixedSizeString(id, 6) +
                "   " + StringUtils.fixedSizeString(getClass().getSimpleName(), 13) +
                "   " + StringUtils.fixedSizeString(surname, 11) +
                "   " + StringUtils.fixedSizeString(name, 8) +
                "   " + StringUtils.fixedSizeString(phone, 13) +
                "   " + StringUtils.fixedSizeString(email, 10) +
                "   " + StringUtils.fixedSizeString((age != null ? ("(" + age + ")-" + (Calendar.getInstance().get(Calendar.YEAR) - age)) : "??"), 10) +
                "   " + StringUtils.fixedSizeString(("".equals(hair) || hair == null) ? "??" : hair, 9) +
                "   ";
    }
}
