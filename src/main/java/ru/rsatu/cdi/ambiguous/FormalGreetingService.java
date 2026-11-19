package ru.rsatu.cdi.ambiguous;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@FormalGreeting
public class FormalGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Здравствуй, мир!";
    }
}