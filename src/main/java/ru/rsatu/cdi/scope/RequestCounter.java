package ru.rsatu.cdi.scope;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestCounter {
    private int count = 0;

    public int increment() {
        return ++count;
    }

    public int getCount() {
        return count;
    }
}