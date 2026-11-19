package ru.rsatu.cdi.scope;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class SessionCounter implements Serializable {
    private int count = 0;

    public int increment() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}