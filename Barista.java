public class Barista extends Adventurer{
  int rating, ratingMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Barista(String name, int hp){
    super(name,hp);
    ratingMax = 10;
    rating = ratingMax/2;
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

  /*Deal 4-8 damage to opponent, restores 1 rating*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*5)+4;
    other.applyDamage(damage);
    restoreSpecial(1);
    return this + " made a rotten drink for "+ other + " and dealt "+ damage +
    " points of damage. "+other+" threw up and "+this+" laughed.";
  }

  /*Deal 2-13 damage to opponent, only if rating is high enough.
  *Reduces rating by 3.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 4){
      setSpecial(getSpecial()-3);
      int ownDamage = (int)(Math.random()*8)+1;
      this.applyDamage(ownDamage);
      if (ownDamage >= 4){
        int damage = (int)(Math.random()*10)+4;
        other.applyDamage(damage);
      }
      if (ownDamage < 4){
        int damage = (int)(Math.random()*5)+2;
        other.applyDamage(damage);
      }
      if (damage <= 4){
        return this + " threw a rotten drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, received a bad review, and lost " + ownDamage+ " points of damage."
      }
      else{
        return this + " threw a rotten drink at "+other+ " and hit them perfectly. "+this+" dealt "+damage+
        " points of damage, received a bad review, and lost " + ownDamage+ " points of damage."
      }
    }else{
      return this+"'s rating is too low to throw a drink. Instead "+attack(other);
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
