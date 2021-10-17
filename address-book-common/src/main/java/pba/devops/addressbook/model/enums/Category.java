package pba.devops.addressbook.model.enums;

import pba.devops.addressbook.model.Acquaintance;
import pba.devops.addressbook.model.Family;
import pba.devops.addressbook.model.Friend;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public enum Category {

    ACQUAINTANCE(Acquaintance.class),
    FRIEND(Friend.class),
    FAMILY(Family.class);

    private Class<?> clazz;

    Category(Class<?> clazz) {
        this.clazz = clazz;
    }

    public <T> T contactInstance() {
        try {
            return (T)clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public Category value(String className) {
        return
            Arrays.stream(values())
                .filter(category ->
                    category.clazz.getSimpleName().equalsIgnoreCase(className))
                        .findFirst().orElse(null);
    }
 }
