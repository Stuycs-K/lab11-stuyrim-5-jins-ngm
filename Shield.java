public class Shield{
  private int HP;
  private boolean rebound;
  private Adventurer target;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Shield(int maxHP, boolean rebound, Adventurer a){
    HP=maxHP;
    this.rebound=rebound;
    target=a;
  }

  public int getHP() {
    return HP;
  }

  public Adventurer getTarget() {
    return target;
  }

  public void reboundDamage(Adventurer other) {
    if (rebound && other==target) {
      other.applyDamage(2);
    }
  }

  public void applyDamage(int n) {
    HP-=n;
  }

  
}
