package pba.devops.addressbook.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pba.devops.addressbook.dataresolver.ContactRemote;
import pba.devops.addressbook.ui.view.*;

@Component
public class BeanProviderUtils implements ApplicationContextAware {

    static private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanProviderUtils.context = applicationContext;
    }

    static public <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }

//    static public WelcomeView welcomeView() {
//        return getBean(WelcomeView.class);
//    }
//
//    static public ContactsView contactsView() {
//        return getBean(ContactsView.class);
//    }
//
//    static public NewContactView newContactView() {
//        return getBean(NewContactView.class);
//    }
//
//    static public UpdateView updateView() {
//        return getBean(UpdateView.class);
//    }
//
//    static public ContactRemote contactRemote() {
//        return getBean(ContactRemote.class);
//    }
}
