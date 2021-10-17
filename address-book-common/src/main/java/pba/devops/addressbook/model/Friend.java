package pba.devops.addressbook.model;

import pba.devops.addressbook.model.abstracts.Contact;
import pba.devops.addressbook.model.utils.StringUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

public class Friend  extends Contact {

    @NotNull(message = "[ Year Of Meeting ] is mandatory")
    @Digits(integer = 4, fraction = 0, message = "[ Year Of Meeting ] requires an integer")
    private Integer meeting;

    public Friend() {
        super();
    }

    public Integer getMeeting() {
        return meeting;
    }

    public void setMeeting(Integer meeting) {
        this.meeting = meeting;
    }

    @Override
    public String toString() {
        return super.toString() +
            StringUtils.fixedSizeString(
    "(" + meeting + ")-" +
                (Calendar.getInstance().get(Calendar.YEAR) - meeting), 40);
    }
}
