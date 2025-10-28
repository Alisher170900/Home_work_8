package Players;

import Logic.RPG_Game;

public class Hacker extends Hero {
    private boolean stealTurn = false;

    public Hacker(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.STEAL_HEALTH);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        stealTurn = !stealTurn; // активируется через каждый раунд
        if (stealTurn) {
            int stolenHealth = 30 + RPG_Game.random.nextInt(21); // 30–50 HP
            boss.setHealth(boss.getHealth() - stolenHealth);

            Hero targetHero;
            do {
                targetHero = heroes[RPG_Game.random.nextInt(heroes.length)];
            } while (targetHero.getHealth() <= 0 || targetHero == this);

            targetHero.setHealth(targetHero.getHealth() + stolenHealth);
            System.out.println("Hacker украл " + stolenHealth + " HP у босса и передал " + targetHero.getName());
        }
    }
}
