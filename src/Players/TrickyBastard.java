package Players;

import Logic.RPG_Game;

public class TrickyBastard extends Hero {
    private boolean hasFakedDeath = false;
    private boolean isPretendingDead = false;
    private int roundToFakeDeath = -1;

    public TrickyBastard(String name, int health, int damage) {
        super(name, health, damage, SuperAbility.FAKE_DEATH);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        // Выбираем раунд для "смерти"
        if (!hasFakedDeath) {
            roundToFakeDeath = RPG_Game.random.nextInt(2, 4);
            hasFakedDeath = true;
        }

        // В указанном раунде притворяется мёртвым
        if (RPG_Game.roundNumber == roundToFakeDeath && !isPretendingDead) {
            isPretendingDead = true;
            System.out.println("TrickyBastard притворился мёртвым в раунде " + RPG_Game.roundNumber + "!");
        }

        // В следующем раунде возвращается
        else if (isPretendingDead && RPG_Game.roundNumber == roundToFakeDeath + 1) {
            isPretendingDead = false;
            System.out.println("TrickyBastard снова в игре!");
        }
    }

    public boolean isPretendingDead() {
        return isPretendingDead;
    }
}
