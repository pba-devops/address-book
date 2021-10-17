package pba.devops.addressbook.ui.useraction;

import pba.devops.addressbook.ui.view.action.Action;
import pba.devops.addressbook.ui.useraction.abstracts.IUserAction;
import pba.devops.addressbook.ui.view.abstracts.IView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class UserAction implements IUserAction {

    static final protected Scanner scanner = new Scanner(System.in);

    @Override
    public void action(IView view, List<Action> possibleActions) {

        final String userAnswer = userAnswer();

        AtomicReference<Boolean> concerned = new AtomicReference<Boolean>(Boolean.FALSE);
        possibleActions.stream().forEach(action -> {
            if(action.isMyJob(userAnswer)) {
                concerned.set(Boolean.TRUE);
                action.run(userAnswer);
                return;
            }
        });
        if(!concerned.get()) {
            view.load(String.format("What do you mean by [ %s ] ?", userAnswer));
        }
    }

    public String userAnswer() {
        return
            userAnswer("");
    }

    public String userAnswer(String information) {

        System.out.format("-- Address-Book -- %s # ", information);
        return scanner.nextLine().trim();
    }
}
