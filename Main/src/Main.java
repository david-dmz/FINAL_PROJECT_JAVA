import java.util.Scanner;

void main() {
    Scanner sc = new Scanner(System.in);
    ConsoleUi.printTitle();

    System.out.print("Entrez votre nom : ");
    String name = sc.nextLine();

    Player player = new Player(name, 100, 10, 5);
    Enemy[] enemies = Enemy.createWave();

    for (Enemy enemy : enemies) {
        System.out.println(ConsoleUi.BOLD + ConsoleUi.CYAN
                + "\n⚔  Un " + enemy.getName() + " apparaît !"
                + ConsoleUi.RESET);

        while (player.getHp() > 0 && enemy.getHp() > 0) {
            player.act(enemy, sc);
            if (enemy.getHp() <= 0) break;
            enemy.act(player);
        }

        if (player.getHp() <= 0) {
            ConsoleUi.printGameOver(player.getName());
            sc.close();
            return;
        }
        if (enemy.getHp() <= 0) {
            System.out.println(ConsoleUi.GREEN + enemy.getName()
                    + " est vaincu !" + ConsoleUi.RESET);
            player.gainXp(enemy.getXpReward());
            player.addGold(enemy.getGold());

            enemy.dropLoot(player); // Tentative de drop de potion

            if (player.getLevel() >= 2 && player.getPlayerClass() == null) {
                PlayerClass chosen = ConsoleUi.chooseClass(sc);
                player.applyClass(chosen);
            }
            //Soin entre les combats
            if (enemy != enemies[enemies.length - 1]) {
                EventSystem.triggerRandomEvent(player, sc);
                int heal = 20;
                player.setHp(Math.min(player.getHp() + heal, player.getMaxHp()));
                System.out.println("Vous récupérez " + heal + " HP avant le prochain combat.\n");
            }
        }
    }

    ConsoleUi.printVictory(player.getName());
    sc.close();


}