public class Enemy extends Entity {

    private int xpReward;

    public Enemy(String name, int hp, int attack, int defence, int xpReward) {
        super(name, hp, attack, defence);
        this.xpReward = xpReward;
    }

    public int getXpReward() {
        return xpReward;
    }

    public static Enemy[] createWave() {
        return new Enemy[]{
                new Enemy("Gobelin", 40, 8, 2, 30),
                new Enemy("Squelette", 60, 10, 3, 50),
                new Enemy("Orc", 80, 13, 5, 80),
                new Enemy("Dragon", 120, 18, 8, 150),
        };

    }

    public void act(Player target) {
        double randomSeed = Math.random();

        if (this.hp <= 20 && randomSeed < 0.5) { // chance de se regener
            this.hp = Math.min(this.hp + 15, this.maxHp);
            System.out.println(getName() + " prend une possion de vie");
            return;
        } else if (target.getHp() <= 20 && randomSeed < 0.3) { // attaque speciale si le joueur a moins de 20 HP
            specialAttack(target);
            return;
        } else if (randomSeed < 0.7) {
            attackPlayer(target);
        } else {
            System.out.println(getName() + " bloque les attaques");
        }
    }

    private void attackPlayer(Player target) {
        int damage = this.attack - target.getDefence();

        if (target.isBlocking()) {
            damage /= 2;
            System.out.println("L'attaque n'est pas aussi effectif");
        }
        if (damage < 0) damage = 0;

        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " inflige " + damage + " dégâts à " + target.getName() + " !");
    }

    private void specialAttack(Player target) {
        int damage = this.attack * 2; // Double dégâts
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " lance un COUP CRITIQUE de " + damage + " dégâts !");
    }
}
