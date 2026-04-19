public class Enemy extends Entity {
    public Enemy(String name, int hp, int attack, int defence) {
        super(name, 100,
                10,
                defence);
    }

    public void act(Player target) {
        double randomSeed = Math.random();
        if (this.hp <= 20 && randomSeed < 0.5) { // chance de se regener
            this.hp += 15;
            System.out.println(getName() + "prend une possion de vie");
            return;
        }

        if (target.getHp() <= 20 && randomSeed < 0.3) { // attaque speciale si le joueur a moins de 20 HP
            specialAttack(target);
            return;
        }
        if (randomSeed < 0.7) {
            attackPlayer(target);

        } else {
            System.out.println(getName() + " bloque les attaques");

        }
    }

    private void attackPlayer(Player target) {
        int damage = this.attack - target.getDefence();

        if(target.isBlocking()){
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
