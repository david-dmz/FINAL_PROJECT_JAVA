public class ConsoleUi {

    public static final String RESET  = "\u001B[0m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN   = "\u001B[36m";
    public static final String BOLD   = "\u001B[1m";

    public static void printTitle() {
        System.out.println(CYAN + BOLD + """
            ╔═══════════════════════════════════╗
            ║        ⚔  JAVA QUEST  ⚔           ║
            ╚═══════════════════════════════════╝
            """ + RESET);
    }

    public static void printHpBar(String name, int hp, int maxHp) {
        int barLength = 20;
        int filled = (int) ((double) hp / maxHp * barLength);
        filled = Math.max(0, Math.min(filled, barLength));

        String color = hp > maxHp * 0.5 ? GREEN : hp > maxHp * 0.2 ? YELLOW : RED;
        String bar = color + "█".repeat(filled) + RESET + "░".repeat(barLength - filled);

        System.out.printf("%-10s [%s] %d/%d HP%n", name, bar, hp, maxHp);
    }

    public static void printBattleHeader(Player player, Enemy enemy) {
        System.out.println("\n" + BOLD + "─".repeat(45) + RESET);
        printHpBar(player.getName(), player.getHp(), player.getMaxHp());
        printHpBar(enemy.getName(),  enemy.getHp(),  enemy.getMaxHp());
        System.out.println(BOLD + "─".repeat(45) + RESET);
    }

    public static void printLevelUp(int newLevel) {
        System.out.println(YELLOW + BOLD + """
            ╔══════════════════════╗
            ║   ⭐ LEVEL UP ! ⭐   ║
            ╚══════════════════════╝
            """ + "   → Niveau " + newLevel + RESET);
    }

    public static void printVictory(String playerName) {
        System.out.println(GREEN + BOLD + """
            ╔══════════════════════════╗
            ║   🏆  VICTOIRE !  🏆    ║
            ╚══════════════════════════╝
            """ + "   " + playerName + " a gagné !" + RESET);
    }

    public static void printGameOver(String playerName) {
        System.out.println(RED + BOLD + """
            ╔══════════════════════════╗
            ║      💀 GAME OVER 💀     ║
            ╚══════════════════════════╝
            """ + "   " + playerName + " est mort..." + RESET);
    }
}
