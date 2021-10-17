package pba.devops.addressbook.ui.view;

import org.springframework.stereotype.Component;
import pba.devops.addressbook.ui.view.abstracts.AbstractView;
import pba.devops.addressbook.ui.view.action.Action;

import java.util.Arrays;

@Component("updateView")
public class UpdateView extends AbstractView {

    public UpdateView() {
        super(Arrays.asList(
            Action.VIEW_OF_CONTACTS,
            Action.VIEW_OF_MENU,
            Action.VIEW_QUIT
        ));
    }

    @Override
    protected void initialize(Object workflowData) {

    }
}