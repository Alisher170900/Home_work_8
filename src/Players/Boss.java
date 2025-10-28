package Players;

import Logic.RPG_Game;

public class Boss extends GameEntity {
    private SuperAbility defence;

    public Boss(String name, int health, int damage) {
        super(name, health, damage);
    }

    public void chooseDefence() {
        SuperAbility[] variants = SuperAbility.values(); // [HEAL, CRITICAL_DAMAGE, BOOST, BLOCK_REVERT]
        int randIndex = RPG_Game.random.nextInt(variants.length); // 0, 1, 2, 3
        this.defence = variants[randIndex];
    }

    public void attack(Hero[] heroes) {
        for (Hero hero : heroes) {
            if (hero.getHealth() > 0) {
                if (RPG_Game.shieldIsActive) {
                    continue;
                }
                // Проверка: TrickyBastard не получает урон, если "мертв"
                if (hero instanceof TrickyBastard tricky && tricky.isPretendingDead()) {
                    continue;
                }
                if (hero instanceof Berserk berserk &&
                        this.defence != SuperAbility.BLOCK_REVERT) {
                    int block = RPG_Game.random.nextInt(1, 3) * 5; // 5, 10
                    berserk.setBlockedDamage(block);
                    berserk.setHealth(berserk.getHealth() - (this.getDamage() - block));
                } else {
                    hero.setHealth(hero.getHealth() - this.getDamage());
                }
            }
        }
    }

    public SuperAbility getDefence() {
        return defence;
    }

    @Override
    public String toString() {
        return super.toString() + " defence: " + defence;
    }
}