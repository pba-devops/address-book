package pba.devops.addressbook.ui.view.action.command.abstracts;

public interface ICommand<ENTITY> {

    Boolean isMyJob(String userCommand);
    IJobDone doMyJob(String userCommand);

    /*
     * Inner classes
     */

    public interface IJobDone<RESULT> {

        Boolean isInError();
        String message();
        RESULT result();
    }

    public class JobDone<RESULT> implements IJobDone<RESULT> {

        private Boolean isInError;
        private String message;
        private RESULT result;

        public JobDone(RESULT result) {
            this(Boolean.FALSE, "", result);
        }

        public JobDone(Boolean isInError, String message) {
            this(isInError, message, null);
        }

        public JobDone(Boolean isInError, String message, RESULT result) {
            this.isInError = isInError;
            this.message = message;
            this.result = result;
        }

        @Override
        public Boolean isInError() {
            return isInError;
        }

        @Override
        public String message() {
            return message;
        }

        @Override
        public RESULT result() {
            return result;
        }
    }
}
