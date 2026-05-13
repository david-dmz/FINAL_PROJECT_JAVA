import java.util.Scanner;

public class Player extends Entity {
    private boolean isBlocking = false;
    private int xp = 0;
    private int level = 1;
    private int xpToNext = 80;// XP nécessaire pour level up
    private int potions = 3;
    private PlayerClass playerClass = null;
    private int gold = 0;

    public Player(String name, int hp, int attack, int defence) {
        super(name, hp, attack, defence, 0);
    }

    public void gainXp(int amount) {
        xp += amount;
        System.out.println(ConsoleUi.YELLOW + "+ " + amount + " XP !" + ConsoleUi.RESET);

        while (xp >= xpToNext) {
            xp -= xpToNext;
            level++;
            xpToNext = (int) (xpToNext * 1.5);

            // augmentation de stats apres lvl up
            this.attack += 2;
            this.defence += 1;
            this.maxHp += 15;

            this.hp = this.maxHp; // soin complet au level up

            ConsoleUi.printLevelUp(level);

            if (level == 3) {
                this.potions += 1;
                System.out.println(ConsoleUi.GREEN + "  BONUS : Stock de potions augmenté ! (+1)" + ConsoleUi.RESET);
            }
            System.out.println("  ATK +2 | DEF +1 | HP MAX +15 | HP restaurés");
        }
    }

    public void applyClass(PlayerClass chosen) {
        this.playerClass = chosen;
        this.attack += chosen.bonusAtk;
        this.defence += chosen.bonusDef;

        int displayAttack = (int) (this.attack * chosen.multAtk);
        int displayDefence = (int) (this.defence * chosen.multDef);

        System.out.println(ConsoleUi.CYAN + ConsoleUi.BOLD + "Vous devenez un " + chosen.label + "!" + ConsoleUi.RESET);
        System.out.println("  ATK effective → " + displayAttack + " | DEF effective → " + displayDefence);
    }


    public void act(Enemy target, Scanner scanner) {
        boolean actionValide = false;



        while (!actionValide) {
            ConsoleUi.printBattleHeader(this, target);
            System.out.println(ConsoleUi.CYAN + "Niveau " + level + " | XP : " + xp + "/" + xpToNext + ConsoleUi.RESET);

            printMenu();
            if (!scanner.hasNextInt()) {
                System.out.println(ConsoleUi.RED + "Veuillez entrer un nombre !" + ConsoleUi.RESET);
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            isBlocking = false; // reset le blocage à chaque tour

            switch (choice) {
                case 1 -> {
                    attackEnemy(target);
                    actionValide = true;
                }
                case 2 -> actionValide = specialAttack(target);
                case 3 -> {
                    defend();
                    actionValide = true;
                }
                case 4 -> {
                    actionValide = usePotion();
                }
                case 5 -> {
                    System.out.println(getName() + " prend la fuite...");
                    System.exit(0);
                }
                default -> System.out.println(ConsoleUi.RED + "Choix invalide." + ConsoleUi.RESET);
            }
        }

    }

    private void printMenu() {
        String specLabel = (playerClass == PlayerClass.MAGE) ? "Explosion (Ennemi < 40 HP)" : "Coup de grâce (Ennemi < 20 HP)";
        System.out.println("""
                1. Attaquer
                2. %s
                3. Bloquer (Réduit les prochains dégâts)
                4. Potion (+15 HP)
                5. Quitter
                """.formatted(specLabel));
    }

    private void attackEnemy(Enemy target) {
        double mult = (playerClass != null) ? playerClass.multAtk : 1.0;
        int actualAttack = (int) (this.attack * mult);

        int damage = Math.max(1, actualAttack - target.getDefence());
        target.setHp(target.getHp() - damage);
        System.out.println(ConsoleUi.RED + getName() + " inflige " + damage + " dégâts à " + target.getName() + " !" + ConsoleUi.RESET);
    }

    private boolean specialAttack(Enemy target) {
        int threshold = (playerClass == PlayerClass.MAGE) ? 40 : 20;

        if (target.getHp() >= threshold) {
            System.out.println(ConsoleUi.YELLOW + "Attaque spéciale indisponible (HP ennemi >= " + threshold + ")" + ConsoleUi.RESET);
            return false;
        }
        double mult = (playerClass != null) ? playerClass.multAtk : 1.0;
        int damage = (int) (this.attack * mult * 2);

        target.setHp(target.getHp() - damage);
        System.out.println(ConsoleUi.RED + ConsoleUi.BOLD + getName() + " COUP CRITIQUE — " + damage + " dégâts !" + ConsoleUi.RESET);
        return true;
    }

    public void addGold(int amount) {
        this.gold += amount;
        System.out.println(ConsoleUi.YELLOW + "+ " + amount + " pièces d'or !" + ConsoleUi.RESET);
    }

    public boolean spendGold(int amount) {
        if (this.gold >= amount) {
            this.gold -= amount;
            return true;
        }
        return false;
    }

    public int getGold() {
        return gold;
    }


    private boolean usePotion() {
        if (potions <= 0) {
            System.out.println(ConsoleUi.RED + "Plus de potions !" + ConsoleUi.RESET);
            return false;
        }
        if (this.hp >= this.maxHp) {
            System.out.println(ConsoleUi.YELLOW + "Vie déjà pleine !" + ConsoleUi.RESET);
            return false;
        }
        this.hp = Math.min(this.hp + 15, this.maxHp);
        this.potions--;
        System.out.println(ConsoleUi.GREEN + " +15 HP ! (Restantes : " + potions + ")" + ConsoleUi.RESET);
        return true;
    }

    private void defend() {
        System.out.println(ConsoleUi.BLUE + getName() + " se prépare à encaisser !" + ConsoleUi.RESET);
        this.isBlocking = true;
    }

    @Override
    public int getDefence() {
        double mult = (playerClass != null) ? playerClass.multDef : 1.0;
        return (int) (this.defence * mult);
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    //Getters
    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getXpToNext() {
        return xpToNext;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public void receivePotion() {
        this.potions++;
    }

    public int getPotions() {
        return potions;
    }
}