public class Enemy extends Entity {
    private int xpReward;
    private boolean isBlocking = false;

    public Enemy(String name, int hp, int attack, int defence, int xpReward, int goldReward) {
        super(name, hp, attack, defence, goldReward);
        this.xpReward = xpReward;
    }

    public static Enemy[] createWave() {
        return new Enemy[]{
                new Enemy("Squelette", 40, 14, 2, 30, 15),
                new Enemy("Gobelin", 55, 18, 4, 55, 25),
                new Enemy("Squelette Ancien", 350, 1, 1, 150, 150), // troll enemy
                new Enemy("Orc", 120, 25, 10, 100, 80),
                new Enemy("Dragon", 200, 35, 15, 200, 150),
                new Enemy("Dark Mage", 350, 50, 20, 400, 300),
                new Enemy("Son of the Darkness", 800, 75, 40, 1000, 1000)
        };
    }

    public void act(Player target) {
        isBlocking = false;

        if (this.name.equals("Squelette Ancien") && Math.random() < 0.01) {
            trollCriticalHit(target);
            return;
        }

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

    private void trollCriticalHit(Player target) {
        int hugeDamage = 50;
        target.setHp(target.getHp() - hugeDamage);

        System.out.println(ConsoleUi.RED + ConsoleUi.BOLD + "\n⚠️  INCROYABLE ! " + getName() +
                " Le Squelette Ancien baille... puis vous met une gifle monumentale de " + hugeDamage + " dégâts !" +
                ConsoleUi.RESET);
    }

    private void attackPlayer(Player target) {
        int damage = Math.max((int) (this.attack * 0.25), this.attack - target.getDefence());

        if (target.isBlocking()) {
            damage /= 2;
            System.out.println("L'attaque n'est pas aussi efficace");
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
