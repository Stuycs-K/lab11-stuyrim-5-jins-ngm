public class Barista extends Adventurer{
  int rating, ratingMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Barista(String name, int hp){
    super(name,hp);
    ratingMax = 10;
    rating = ratingMax/2;
  }

  public Barista(String name, int hp){
    this(name,hp);
  }

  public Barista(String name){
    this(name,75);
  }

  public Barista(){
    this("Starry");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "rating";
  }

  public int getSpecial(){
    return rating;
  }

  public void setSpecial(int n){
    rating = n;
  }

  public int getSpecialMax(){
    return ratingMax;
  }

  /*Deal 15-25 damage to opponent, restores 1 rating*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*11)+15;
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " threw a rotten drink at "+ other + " and dealt "+ damage +
    " points of damage. They then increase their rating.";
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
