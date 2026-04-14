import java.util.Scanner;

public class Player extends Entity {
    public Player(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence);
    }

    public void act(Enemy target, Scanner scanner) {
        System.out.print("Bonjour dans l'aventure veuillez choisir votre nom: ");
        setName(scanner.next());

        System.out.println("""
                Que voulez-vous faire ?.
                1. Attaquer
                2. Attaque spéciale
                3. Bloquer
                4. Prendre une potion de vie
                5. Arreter le combat
                """);

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> attackEnemy(target);
            case 2 -> specialAttack(target);
            case 3 -> System.out.println(getName() + " bloque les attaques !");
            case 4 -> {
                if(this.hp  >= 100) {
                    System.out.println("Vous ne pouvez pas depasser le montant maximum de vie.");
                }else {
                    this.hp += 15;
                    System.out.println(getName() + " prend une potion de vie (+15 HP)");
                }
            }
            default -> System.out.println("Choix invalide, vous passez votre tour.");
        }
    }

    private void attackEnemy(Enemy target) {
        int damage = this.attack - target.getDefence();
        if (damage < 0) damage = 0;

        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " inflige " + damage + " dégâts à " + target.getName() + " !");
    }

    private void specialAttack(Enemy target) {
        int damage = this.attack * 2;
        target.setHp(target.getHp() - damage);
        System.out.println(getName() + " lance un COUP CRITIQUE de " + damage + " dégâts !");
    }
}
