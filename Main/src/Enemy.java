public class Enemy extends Entity {
    private int xpReward;
    private boolean isBlocking = false;

    public Enemy(String name, int hp, int attack, int defence, int xpReward, int goldReward) {
        super(name, hp, attack, defence, goldReward);
        this.xpReward = xpReward;
    }

    public static Enemy[] createWave() {
        return new Enemy[]{
                new Enemy("Squelette", 30, 8, 1, 30, 10),
                new Enemy("Gobelin", 40, 12, 2, 50, 20),
                new Enemy("Squelette", 300, 1, 3, 120, 100), // troll enemy
                new Enemy("Orc", 80, 18, 5, 80, 60),
                new Enemy("Dragon", 120, 28, 8, 150, 100),
                new Enemy("Dark Mage", 200, 35, 15, 200, 250),
                new Enemy("Son of the Darkness", 500, 50, 30, 400, 500)
        };
    }

    public void act(Player target) {
        isBlocking = false;

        // 1. Priorité Survie : Se soigne s'il est faible
        if (this.hp <= 20 && Math.random() < 0.5) {
            this.hp = Math.min(this.hp + 15, this.maxHp);
            System.out.println(getName() + " boit une potion de vie");
            return;
        }
        // 2. Priorité Exécution : Attaque spéciale si le joueur est faible
        if (target.getHp() <= 30 && Math.random() < 0.8) {
            specialAttack(target);
            return;
        }
        // 3. Action standard
        if (Math.random() < 0.85) {
            attackPlayer(target);
        } else {
            System.out.println(getName() + " bloque les attaques");
            isBlocking = true;
        }
    }

    private void attackPlayer(Player target) {
        int damage = Math.max((int) (this.attack * 0.25), this.attack - target.getDefence());

        if (target.isBlocking()) {
            damage /= 2;
            System.out.println("L'attaque n'est pas aussi effectif");
        }

        damage = Math.max(1, damage);
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " inflige " + damage + " dégâts à " + target.getName() + " !");


    }

    private void specialAttack(Player target) {
        int damage = Math.max(5, (this.attack * 2) - target.getDefence());  // Double dégâts
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " lance un COUP CRITIQUE de " + damage + " dégâts !");
    }

    public int getXpReward() {
        return xpReward;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void dropLoot(Player player) {
        if (Math.random() < 0.2 && (player.getPotions() < 4)) {
            player.receivePotion();
            System.out.println(ConsoleUi.GREEN + getName() + " a laissé tomber une potion ! " +
                    "(Potions: " + player.getPotions() + ")" + ConsoleUi.RESET);
        }
    }
}
