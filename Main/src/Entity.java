abstract class Entity {
    private String name;
    private int hp;
  private   int attack;
   private int defence;

    public Entity(String name, int hp,int attack,int defence) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
    }
  public String getName(){
        return name;
  }
  public int getHp() {
        return hp;
  }
  public void setHp(int hp) {
        this.hp = hp;
  }
}
