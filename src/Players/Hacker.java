package Players;

import Logic.RPG_Game;

public class Hacker extends Hero {
    private boolean stealTurn;

    public Hacker(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.STEAL_HEALTH);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        stealTurn = !stealTurn; // активируется через каждый раунд
        if (stealTurn) {
            int stealHealth = 40 + RPG_Game.random.nextInt(31); // 40–60 HP
            boss.setHealth(boss.getHealth() - stealHealth);

            Hero targetHero;
            do {
                targetHero = heroes[RPG_Game.random.nextInt(heroes.length)];
            } while (targetHero.getHealth() <= 0 || targetHero == this);

            targetHero.setHealth(targetHero.getHealth() + stealHealth);
            System.out.println("Hacker steal " + stealHealth +
                    " HP from Boss and handed "
                    + targetHero.getName());
        }
    }
}
