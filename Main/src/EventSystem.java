import java.util.Scanner;

public class EventSystem {

    public static void triggerRandomEvent(Player player, Scanner sc) {
        double chance = Math.random();

        System.out.println("\n" + ConsoleUi.CYAN + "✨ ÉVÉNEMENT EN CHEMIN..." + ConsoleUi.RESET);

        if (chance < 0.4) {
            handleAltar(player, sc);
        } else if (chance < 0.7) {
            handleMerchant(player, sc);
        } else {
            handleChest(player);
        }
    }

    private static void handleAltar(Player player, Scanner sc) {
        System.out.println(ConsoleUi.PURPLE + """
             Vous trouvez un Autel Mystérieux... 
            Une voix résonne : 'Un sacrifice en échange de puissance ?'
            """ + ConsoleUi.RESET);
        System.out.println("""
                1. Offrir du sang (-20 HP) pour gagner +5 ATK
                2. Prier calmement (Rien ne se passe)
                """);

        int choice = getValidChoice(sc, 2);
        if (choice == 1) {
            player.setHp(player.getHp() - 20);
            player.setAttack(player.getAttack() + 5);
            System.out.println(ConsoleUi.RED + "L'autel brille ! Votre attaque augmente, mais vous vous sentez affaibli." + ConsoleUi.RESET);
            if (player.getHp() <= 0) player.setHp(1); // On évite une mort idiote sur un autel
        } else {
            System.out.println("Vous passez votre chemin prudemment.");
        }
    }

    private static void handleChest(Player player) {
        System.out.println(ConsoleUi.YELLOW + " Vous trouvez un coffre poussiéreux !" + ConsoleUi.RESET);
        if (Math.random() > 0.4) {
            player.receivePotion();
            System.out.println("Incroyable ! Vous trouvez une " + ConsoleUi.GREEN + "Potion" + ConsoleUi.RESET + ".");
        } else {
            int trapDmg = 10;
            player.setHp(player.getHp() - trapDmg);
            System.out.println(ConsoleUi.RED + "C'était un piège ! Une fléchette vous touche (-" + trapDmg + " HP)." + ConsoleUi.RESET);
        }
    }

    private static void handleMerchant(Player player, Scanner sc) {
        System.out.println(ConsoleUi.BLUE + "Un marchand ambulant vous hèle : 'Hé, voyageur ! J'ai des stocks frais !'" + ConsoleUi.RESET);
        System.out.println("Votre bourse : " + ConsoleUi.YELLOW + player.getGold() + " Or" + ConsoleUi.RESET);
        System.out.println("""
                1. Potion de soin (15 Or)
                2. Aiguisage d'arme (+3 ATK) (40 Or)
                3. Rien pour moi, merci.
                """);

        int choice = getValidChoice(sc, 3);
        switch (choice) {
            case 1 -> {
                if (player.spendGold(15)) {
                    player.receivePotion();
                    System.out.println("Vous achetez une potion.");
                } else {
                    System.out.println("Pas assez d'or !");
                }
            }
            case 2 -> {
                if (player.spendGold(40)) {
                    player.setAttack(player.getAttack() + 3);
                    System.out.println("Votre arme brille d'un nouvel éclat ! (+3 ATK)");
                } else {
                    System.out.println("Le marchand ricane : 'Reviens quand tu seras riche !'");
                }
            }
            default -> System.out.println("Vous reprenez votre route.");
        }
    }

    private static int getValidChoice(Scanner sc, int max) {
        int choice;
        while (true) {
            System.out.print("Votre choix : ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= max) break;
            } else {
                sc.next();
            }
            System.out.println(ConsoleUi.RED + "Choix invalide." + ConsoleUi.RESET);
        }
        return choice;
    }
}