import java.util.ArrayList;
public class PastryChef extends Adventurer{
  int sugar, sugarMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public PastryChef(String name, int hp, ArrayList<Adventurer> party){
    super(name,hp,party);
    sugarMax = 15;
    sugar = sugarMax/2;
  }

  public PastryChef(String name,ArrayList<Adventurer> party){
    this(name,50, party);
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "sugar";
  }

  public int getSpecial(){
    return sugar;
  }

  public void setSpecial(int n){
    sugar = n;
  }

  public int getSpecialMax(){
    return sugarMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
    String additional="";
    if (sugar<0.3*sugarMax) {
      int damage = (int)(Math.random()*6)+2;
      other.applyDamage(damage);
      additional="Due to "+this+"'s shortage of sugar, the pastry also tastes really bad. "+other+" thus sustains "+damage+" points of damage.";
    }
    other.setSalmonella(true);
    return this + " made a pastry with raw eggs and fed it to "+ other + ", giving them salmonella. "+additional;
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      return this+" uses their extra sugar to whip up another poisonous pastry!";
    }else{
      return "Unfortunately, "+this+" is a bit short on sugar, so they can only make one pastry. "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    int heal = 3+(int)(Math.random()*4);
    other.setHP(other.getHP()+heal);
    return this+" whips up a delicious pastry and feeds it to "+other+", restoring"
    + other.restoreSpecial(5)+" "+other.getSpecialName()+" and "+heal+" HP.";
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int heal = 3+(int)(Math.random()*4);
    setHP(getHP()+heal);
    return this+" whips up a delicious pastry and feeds it to eats it, restoring"
    + restoreSpecial(5)+" "+getSpecialName()+" and "+heal+" HP.";
  }
}
