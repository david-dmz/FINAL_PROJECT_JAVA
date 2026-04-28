#  JavaQuest : Terminal RPG

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-En_Développement-green?style=for-the-badge)

**JavaQuest** est un jeu de rôle (RPG) développé en Java, jouable directement dans la console. Le projet met en avant des mécaniques de combat tactique, une gestion de progression de personnage et des événements aléatoires dynamiques.
---
## Table des matières
* [Fonctionnalités principales](#-fonctionnalités-principales)
* [Architecture Technique](#️-architecture-technique)
* [Comment jouer ?](#-comment-jouer-)
* [Road Map (À venir)](#️-road-map-à-venir)
* [Auteur](#-auteur)

---

## Fonctionnalités principales

* **Système de Combat au Tour par Tour :** Affrontez des vagues d'ennemis avec des actions stratégiques (Attaque, Blocage, Spécial, Potions).
* **Système de Classes :** Choisissez entre **Guerrier, Tank, Mage ou Rogue**, chacun ayant ses propres multiplicateurs de statistiques et capacités uniques.
* **Économie et Loot :** Collectez de l'or sur vos ennemis pour commercer avec un marchand ambulant.
* **Événements Aléatoires :** Découvrez des coffres mystérieux ou des autels anciens entre chaque combat qui peuvent booster vos stats ou vous piéger.
* **Progression (Level Up) :** Gagnez de l'expérience (XP) pour monter en niveau et améliorer vos statistiques de base (HP, ATK, DEF).
* **Interface Colorée :** Utilisation de codes ANSI pour une expérience visuelle immersive en terminal.

---
## Architecture Technique

Le projet suit les principes de la **Programmation Orientée Objet (POO)** :

* **Héritage :** Une classe abstraite `Entity` sert de base pour `Player` et `Enemy`.
* **Encapsulation :** Gestion rigoureuse des statistiques via des getters/setters et des modificateurs de combat.
* **Modularité :** Séparation des responsabilités avec un `EventSystem` dédié aux rencontres fortuites et un `ConsoleUi` pour l'affichage.
* **Logique IA :** Les ennemis possèdent une intelligence de base (soins prioritaires, attaques critiques imprévisibles, blocages).

 ---

## Comment jouer ?

### Prérequis
* **JDK 21** ou plus récent.
* Un terminal supportant les codes couleur ANSI (IntelliJ Terminal, PowerShell, Bash).

### Installation
1. Clonez le repository :
   ```bash
   git clone https://github.com/DavidMVgit-coder/FINAL_PROJECT_JAVA.git
2. Compliez le projet :
    ```bash
    javac *.java
3. Lancez le jeu :
     ```bash
     java Main

## Road Map (À venir)

- [ ] **Transition JavaFX :** Création d'une interface graphique complète avec barres de vie visuelles et assets 2D.
- [ ] **Système d'Équipement :** Ajout d'armes et d'armures avec des raretés différentes.
- [ ] **Nouveaux Ennemis :** Ajout de boss avec des patterns d'attaque plus complexes.
- [ ] **Sauvegarde :** Implémentation d'un système de sauvegarde via fichiers JSON ou texte.

---

## Auteur
* **David Martinez** - *Développeur Principal*
