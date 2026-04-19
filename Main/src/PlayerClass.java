public enum PlayerClass {
    GUERRIER("Guerrier", "+5 ATK, +2 DEF attaque critique améliorée", 5, 2),
    TANK("Tank",   "+3 ATK, +10 DEF, critique x3" ,3, 10),
    MAGE("Mage", "+8 ATK, -1 DEF, attaque spéciale à 40 HP", 8, -5);

    public final String label;
    public final String description;
    public final int bonusAtk;
    public final int bonusDef;

    PlayerClass(String label, String description, int bonusAtk, int bonusDef) {
        this.label = label;
        this.description = description;
        this.bonusAtk = bonusAtk;
        this.bonusDef = bonusDef;
    }

}
