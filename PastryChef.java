public class PastryChef extends Adventurer{
  int sugar, sugarMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public PastryChef(String name, int hp){
    super(name,hp);
    sugar = 15;
    sugar = sugarMax/2;
    setSalmonella(false);
  }

  public PastryChef(String name){
    this(name,50);
  }

  public PastryChef(){
    this("Madeline");
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
    return this + " made a pastry with raw eggs ad fed it to "+ other + ", giving them salmonella. "+additional;
  }

  /*Deal 3-12 damage to opponent, only if caffeine is high enough.
  *Reduces caffeine by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough caffeine to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
