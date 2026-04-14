import java.util.Scanner;

void main() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Bienvenue dans l'aventure. Entrez votre nom: ");
    String name = sc.nextLine();

    Player player = new Player(name, 100, 10, 5);
    Enemy enemy = new Enemy("Goblin", 100, 10, 5);

    while (player.getHp() > 0) {
        player.act(enemy, sc);

        if (enemy.getHp() <= 0) break; // l'ennemi est mort, pas besoin qu'il joue

        enemy.act(player);
    }

    if (player.getHp() <= 0) {
        System.out.println(player.getName() + " est mort... Game Over !");
    } else {
        System.out.println(player.getName() + " a vaincu " + enemy.getName() + " ! Victoire !");
    }

    sc.close();
}


