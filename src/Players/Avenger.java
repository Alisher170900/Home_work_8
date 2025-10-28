package Players;

import Logic.RPG_Game;

public class Avenger extends Hero {
    public Avenger(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.TEAM_SHIELD);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int chance = RPG_Game.random.nextInt(100);
        if (chance < 20) { // 20% шанс
            RPG_Game.shieldIsActive = true;
            System.out.println("Мститель создал защитный щит на один раунд!");
        }
    }
}