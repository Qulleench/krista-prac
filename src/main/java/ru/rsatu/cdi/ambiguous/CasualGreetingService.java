package ru.rsatu.cdi.ambiguous;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class CasualGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Привет, мир!";
    }
}