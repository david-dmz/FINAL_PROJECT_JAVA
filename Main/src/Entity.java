abstract class Entity {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;
    protected int maxHp;
    protected int gold;

    public Entity(String name, int hp, int attack, int defence, int gold) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.maxHp = hp;
        this.gold = gold;
    }

    // Getters

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    // Setters
    protected void setName(String name) {
    }

    public int getHp() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    protected void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
