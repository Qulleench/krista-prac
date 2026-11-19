package ru.rsatu.cdi.scope;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationCounter {
    private int count = 0;

    public int increment() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}