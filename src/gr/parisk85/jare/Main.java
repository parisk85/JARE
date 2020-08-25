package gr.parisk85.jare;

import gr.parisk85.jare.core.Engine;
import gr.parisk85.jare.core.Rule;
import gr.parisk85.jare.core.RuleBuilder;

public class Main {

    public static void main(String[] args) {
        Hero hero = new Hero(5, 9);

        //Rule rule = StandardRule.given(Hero.class).when(h -> h.getStrength() < 6).then(s -> s.setStrength(7));

        //Rule rule1 = StandardRule.given(Hero.class).when(h -> h.getStrength() < 10).thenThrow(IllegalArgumentException::new).build();

        Rule rule = RuleBuilder.given(Hero.class).when(h -> h.getDexterity() < 10).then(h -> h.setDexterity(20));
        Rule rule1 = RuleBuilder.given(Hero.class).when(h -> h.getDexterity() < 10).thenThrow(ArithmeticException::new);

        Engine.run(hero, rule, rule1);

        System.out.println(hero);
    }
}

class Hero {
    private int strength;
    private int dexterity;

    public Hero(int strength, int dexterity) {
        this.strength = strength;
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                '}';
    }
}