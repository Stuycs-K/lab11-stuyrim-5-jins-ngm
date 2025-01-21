import java.util.ArrayList;
public class PastryChef extends Adventurer{
  int sugar, sugarMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public PastryChef(String name, int hp, ArrayList<Adventurer> party){
    super(name,hp,party);
    sugarMax = 30;
    sugar = sugarMax/2;
  }

  public PastryChef(String name,ArrayList<Adventurer> party){
    this(name,30, party);
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "sugar";
  }

  public int getSpecial(){
    return sugar;
  }

  public void setSpecial(int n){
    sugar = Math.max(0, n);
  }

  public int getSpecialMax(){
    return sugarMax;
  }

  public String attack(Adventurer other){
    String additional="";
    if (sugar<0.3*sugarMax) {
      int damage = (int)(Math.random()*6)+2;
      if (this.isBuffed()){
        damage+=5;
      }
      int dmgMessage = other.applyDamage(damage);
      if (dmgMessage==-2){
        additional="Due to "+this+"'s shortage of sugar, the pastry also tastes really bad. "+other+" thus loses "+damage+" HP.";
      } else if (dmgMessage==-1) {
        additional="Due to "+this+"'s shortage of sugar, the pastry also tastes really bad. "+other+"'s shield thus sustains "+damage+" points of damage.";
      } else {
        additional="Due to "+this+"'s shortage of sugar, the pastry also tastes really bad. "+other+"'s shield breaks and "+other+" loses "+damage+" HP.";
      }
    }
    setBuffed(false);
    other.setSalmonella(true);
    return this + " made a pastry with raw eggs and fed it to "+ other + ", giving them salmonella. "+additional;
  }

  public String specialAttack(Adventurer other){
    setBuffed(false);
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      return "entire enemy party";
    }else{
      return "Unfortunately, "+this+" is a bit short on sugar, so they can only make one pastry. "+attack(other);
    }

  }

  public String support(Adventurer other){
    other.setSalmonella(false);
    setBuffed(false);
    int heal = 3+(int)(Math.random()*4);
    other.setHP(other.getHP()+heal);
    return this+" whips up a delicious pastry and feeds it to "+other+", restoring "
    + other.restoreSpecial(5)+" "+other.getSpecialName()+" and "+heal+" HP.";
  }

  public String support(){
    this.setSalmonella(false);
    setBuffed(false);
    int heal = 3+(int)(Math.random()*4);
    setHP(getHP()+heal);
    return this+" whips up a delicious pastry and eats it, restoring "
    + restoreSpecial(5)+" "+getSpecialName()+" and "+heal+" HP.";
  }
}
