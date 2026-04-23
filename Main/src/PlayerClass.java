public enum PlayerClass {
    GUERRIER("Guerrier", "Dégâts x2, Défense normale", 5, 2, 2.0, 1.0),
    TANK("Tank", "Dégâts x1.5, Défense x2", 3, 10, 1.0, 0.8),
    MAGE("Mage", "Attaque spéciale à 40 HP", 8, -5, 1.0, 1.0),
    ROGUE("Rogue", "Dégâts x3, Défense divisée par 2", 6, 0, 3.0, 0.2);

    public final String label;
    public final String description;
    public final int bonusAtk;
    public final int bonusDef;
    public final double multAtk;
    public final double multDef;

    PlayerClass(String label, String description, int bonusAtk, int bonusDef, double multAtk, double multDef) {
        this.label = label;
        this.description = description;
        this.bonusAtk = bonusAtk;
        this.bonusDef = bonusDef;
        this.multAtk = multAtk;
        this.multDef = multDef;
    }

}
