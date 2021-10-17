package pba.devops.addressbook.ui.view.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.ui.useraction.UserAction;
import pba.devops.addressbook.ui.useraction.abstracts.IUserAction;
import pba.devops.addressbook.ui.view.action.Action;

import java.util.List;

public abstract class AbstractView implements IView {

    @Autowired
    @Qualifier("contactRemote")
    protected ContactRemote contactRemote;

    final private IUserAction userAction;
    final private List<Action> actions;

    public AbstractView(List<Action> actions) {
        this.userAction = new UserAction();
        this.actions = actions;
    }

    @Override
    public void load() {
        load(null);
    }

    @Override
    public void load(Object workflowData) {

        initialize(workflowData);
        instructions();
        showMessage(workflowData);
        userAction();
    }

    abstract protected void initialize(Object workflowData);

    private void instructions() {
        actions
            .stream().forEach(
                action -> action.instruction());
    }

    private void showMessage(Object workflowData) {

        if(workflowData == null) {
            return;
        }
        String message = workflowData.toString();
        if(!StringUtils.isEmpty(message)) {
            System.out.println(message);
        }

    }

    private void userAction() {
        userAction.action(this, actions);
    }
}