package com.ramnivas.scrollbehavior;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramnivasindani on 9/21/17.
 */

public class Minion {
    private String name;
    private int age;

    public Minion() {

    }

    public Minion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return "My age is "+age+" years old" ;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Minion> getMyMinions() {
        List<Minion> minions = new ArrayList<>();
        minions.add(new Minion("Ray", 15));
        minions.add(new Minion("Roy", 25));
        minions.add(new Minion("Ryan", 35));
        minions.add(new Minion("Richard", 45));
        return minions;
    }
}
