package gr.parisk85.jare;

import gr.parisk85.jare.core.Engine;
import gr.parisk85.jare.core.Rule;
import gr.parisk85.jare.core.RuleBuilder;

public class Main {

    public static void main(String[] args) {
        Hero dwarfHero = new Hero(14, 9, "Dwarf");
        Hero humanHero = new Hero(5, 9, "Human");

        RuleBuilder.given(Hero.class).when(h -> h.getStrength() == 0).then(t -> t.setStrength(3));
        //Rule rule = StandardRule.given(Hero.class).when(h -> h.getStrength() < 6).then(s -> s.setStrength(7));

        //Rule rule1 = StandardRule.given(Hero.class).when(h -> h.getStrength() < 10).thenThrow(IllegalArgumentException::new).build();

        /*Rule rule = RuleBuilder.given(Hero.class).when(h -> h.getDexterity() < 10).then(h -> h.setDexterity(20));
        Rule rule1 = RuleBuilder.given(Hero.class).when(h -> h.getDexterity() < 25).thenThrow(ArithmeticException::new);*/

        Rule<Hero> dwarfRule = RuleBuilder.given(Hero.class).when(h -> h.getRace().equals("Dwarf")).then(h -> h.setStrength(h.getStrength() + 2));
        Rule<Hero> abilityRule = RuleBuilder.given(Hero.class).when(h -> h.getStrength() < 3 || h.getStrength() > 18).thenThrow(IllegalArgumentException::new);

        Rule<Hero> testRule = RuleBuilder.given(Hero.class).when(h -> h.getStrength() == 16).then(h -> System.out.println("HOORAY"));

        Engine.run(dwarfHero, dwarfRule, abilityRule, testRule);
        Engine.run(humanHero, dwarfRule, abilityRule, testRule);
        //Engine.run(hero, rule, rule1);

        System.out.println(dwarfHero);
        System.out.println(humanHero);
    }
}

class Hero {
    private int strength;
    private int dexterity;
    private String race;

    public Hero(int strength, int dexterity, String race) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.race = race;
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", race='" + race + '\'' +
                '}';
    }
}