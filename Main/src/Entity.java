abstract class Entity {
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;

    public Entity(String name, int hp, int attack, int defence) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    // Setters
    protected void setName(String name) {}
    protected void setHp(int hp) {
        this.hp = hp;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    protected void setDefence(int defence) {
        this.defence = defence;
    }
}
