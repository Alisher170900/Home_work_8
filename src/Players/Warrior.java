package Players;

import Logic.RPG_Game;

public class Warrior extends Hero {
    public Warrior(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.CRITICAL_DAMAGE);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int crit = RPG_Game.random.nextInt(2, 6) * this.getDamage();
        boss.setHealth(boss.getHealth() - crit);
        System.out.println("Warrior " + this.getName() + " hit critically " + crit);
    }
}