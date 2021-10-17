package pba.devops.addressbook.ui.view;

import org.springframework.stereotype.Component;
import pba.devops.addressbook.ui.view.abstracts.AbstractView;
import pba.devops.addressbook.ui.view.action.Action;

import java.util.Arrays;

@Component("newContactView")
public class NewContactView extends AbstractView {

    public NewContactView() {
        super(Arrays.asList(
            Action.VIEW_NEW_CONTACT,
            Action.VIEW_OF_MENU,
            Action.VIEW_QUIT
        ));
    }

    @Override
    protected void initialize(Object workflowData) {

        // NEW CONTACT WORKFLOW --
//        addContactWorkflow.nextState(workflowData);
    }
}