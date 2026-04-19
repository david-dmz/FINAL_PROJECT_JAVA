import java.util.Scanner;

public class Player extends Entity {
    private boolean isBlocking = false;
    private int xp      = 0;
    private int level   = 1;
    private int xpToNext = 100; // XP nécessaire pour level up

    public Player(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence);
    }

    /** Appelé après chaque ennemi vaincu */
    public void gainXp(int amount) {
        xp += amount;
        System.out.println(ConsoleUi.YELLOW + "+ " + amount + " XP !" + ConsoleUi.RESET);

        while (xp >= xpToNext) {
            xp -= xpToNext;
            level++;
            xpToNext = (int) (xpToNext * 1.5);
            this.attack  += 2;
            this.defence += 1;
            this.hp = this.maxHp; // soin complet au level up
            ConsoleUi.printLevelUp(level);
            System.out.println("  ATK +" + 2 + " | DEF +" + 1 + " | HP restaurés");
        }
    }

    public int getLevel() { return level; }
    public int getXp()    { return xp; }
    public int getXpToNext() { return xpToNext; }

    public void act(Enemy target, Scanner scanner) {
        ConsoleUi.printBattleHeader(this, target);
        System.out.println(ConsoleUi.CYAN + "Niveau " + level
                + " | XP : " + xp + "/" + xpToNext + ConsoleUi.RESET);
        System.out.println("""
                Que voulez-vous faire ?
                1. Attaquer
                2. Attaque spéciale (ennemi < 20 HP)
                3. Bloquer
                4. Potion de vie (+15 HP)
                5. Quitter
                """);

        int choice = scanner.nextInt();
        isBlocking = false; // reset le blocage à chaque tour

        switch (choice) {
            case 1 -> attackEnemy(target);
            case 2 -> specialAttack(target);
            case 3 -> {
                System.out.println(getName() + " se met en garde !");
                isBlocking = true;
            }
            case 4 -> {
                if (this.hp >= this.maxHp) {
                    System.out.println("Vous êtes déjà au maximum !");
                } else {
                    this.hp = Math.min(this.hp + 15, this.maxHp);
                    System.out.println(getName() + " boit une potion (+15 HP)");
                }
            }
            case 5 -> {
                System.out.println("À bientôt !");
                System.exit(0);
            }
            default -> System.out.println(ConsoleUi.RED + "Choix invalide." + ConsoleUi.RESET);
        }
    }

    private void attackEnemy(Enemy target) {
        int damage = Math.max(0, this.attack - target.getDefence());
        target.setHp(target.getHp() - damage);
        System.out.println(ConsoleUi.RED + getName() + " inflige " + damage
                + " dégâts à " + target.getName() + " !" + ConsoleUi.RESET);
    }

    private void specialAttack(Enemy target) {
        if (target.getHp() >= 20) {
            System.out.println(ConsoleUi.YELLOW
                    + "Attaque spéciale indisponible (HP ennemi >= 20)" + ConsoleUi.RESET);
            return;
        }
        int damage = this.attack * 2;
        target.setHp(target.getHp() - damage);
        System.out.println(ConsoleUi.RED + getName()
                + " COUP CRITIQUE — " + damage + " dégâts !" + ConsoleUi.RESET);
    }

    public boolean isBlocking() { return isBlocking; }
}