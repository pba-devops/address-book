package pba.devops.addressbook.ui.useraction.abstracts;

import pba.devops.addressbook.ui.view.action.Action;
import pba.devops.addressbook.ui.view.abstracts.IView;

import java.util.List;

public interface IUserAction {

    void action(IView view, List<Action> possibleActions);
}
