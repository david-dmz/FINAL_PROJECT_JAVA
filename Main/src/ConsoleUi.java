import java.util.Scanner;

public class ConsoleUi {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";

    public static void printTitle() {
        System.out.println(CYAN + BOLD + """
                ╔════════════════════════════════╗
                ║        ⚔  JAVA QUEST  ⚔        ║  
                ╚════════════════════════════════╝
                """ + RESET);
    }

    public static void printHpBar(String name, int hp, int maxHp) {
        int barLength = 20;
        int filled = (int) ((double) hp / maxHp * barLength);
        /*pour Java 21 et -
        filled = Math.max(0, Math.min(filled, barLength));
         pour Java 21 et + */
        filled = Math.clamp(filled, 0, barLength);

        String color = hp > maxHp * 0.5 ? GREEN : hp > maxHp * 0.2 ? YELLOW : RED;
        String bar = color + "█".repeat(filled) + RESET + "░".repeat(barLength - filled);

        System.out.printf("%-10s [%s] %d/%d HP%n", name, bar, hp, maxHp);
    }

    public static void printBattleHeader(Player player, Enemy enemy) {
        System.out.println("\n" + BOLD + "─".repeat(45) + RESET);
        printHpBar(player.getName(), player.getHp(), player.getMaxHp());
        printHpBar(enemy.getName(), enemy.getHp(), enemy.getMaxHp());
        System.out.println(BOLD + "─".repeat(45) + RESET);
    }

    public static void printLevelUp(int newLevel) {
        System.out.println(YELLOW + BOLD + """
                ╔══════════════════════╗
                ║ ⭐⭐ LEVEL UP! ⭐⭐ ║
                ╚══════════════════════╝
                """ + "   → Niveau " + newLevel + RESET);
    }

    public static void printVictory(String playerName) {
        System.out.println(GREEN + BOLD + """
                ╔══════════════════════════╗
                ║  🏆🏆  VICTOIRE !  🏆🏆 ║
                ╚══════════════════════════╝
                """ + "   " + playerName + " a gagné !" + RESET);
    }

    public static void printGameOver(String playerName) {
        System.out.println(RED + BOLD + """
                ╔══════════════════════════╗
                ║   💀💀 GAME OVER 💀 💀  ║
                ╚══════════════════════════╝
                """ + "   " + playerName + " est mort..." + RESET);
    }

    public static PlayerClass chooseClass(Scanner sc) {
        System.out.println(CYAN + BOLD + """
                ╔══════════════════════════════════════╗
                ║      ⭐⭐ CHOIX DE CLASSE ⭐⭐      ║
                ╚══════════════════════════════════════╝
                """ + RESET);

        PlayerClass[] classes = PlayerClass.values();
        for (int i = 0; i < classes.length; i++) {
            System.out.println(BOLD + (i + 1) + ". " + classes[i].label + RESET
                    + " — " + classes[i].description);
        }

        System.out.print("\nVotre choix : ");
        int choice;
        while (true) {
            choice = sc.nextInt();
            if (choice >= 1 && choice <= classes.length) break;
            System.out.print(RED + "Choix invalide, réessayez : " + RESET);
        }
        return classes[choice - 1];
    }
}
