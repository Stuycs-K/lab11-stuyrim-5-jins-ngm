import java.util.ArrayList;
public class Boss extends Adventurer{
  int rating, ratingMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp, ArrayList<Adventurer> party){
    super(name,hp,party);
    ratingMax = 10;
    rating = ratingMax/2;
  }

  public Boss(String name, ArrayList<Adventurer> party){
    this(name,75, party);
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "rating";
  }

  public int getSpecial(){
    return rating;
  }

  public void setSpecial(int n){
    rating = Math.max(0, n);
  }

  public int getSpecialMax(){
    return ratingMax;
  }

  /*Deal 4-8 damage to opponent, restores 1 rating*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*5)+4;
    other.applyDamage(damage);
    restoreSpecial(1);
    if (this.getmaxHP()/2 > this.getHP()){
      return this + " made a rotten drink for "+ other + " and dealt "+ (damage * 1.2) +
      " points of damage. "+other+" threw up and "+this+" laughed.";
    }
    else{
      return this + " made a rotten drink for "+ other + " and dealt "+ damage +
      " points of damage. "+other+" threw up and "+this+" laughed.";
    }
  }

  /*Deal 2-13 damage to opponent, only if rating is high enough.
  *Reduces rating by 3.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 4){
      setSpecial(getSpecial()-4);
      int ownDamage = (int)(Math.random()*8)+1;
      this.applyDamage(ownDamage);
      int damage;
      if (ownDamage >= 4){
        damage = (int)(Math.random()*10)+4;
        other.applyDamage(damage);
      } else {
        damage = (int)(Math.random()*5)+2;
        other.applyDamage(damage);
      }
      if ((damage <= 4) && (this.getmaxHP()/2 > this.getHP())){
        return this + " threw a rotten drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + (ownDamage * 1.2) + " points of damage.";
      }
      if (damage <= 4){
        return this + " threw a rotten drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + ownDamage + " points of damage.";
      }
      if ((damage > 4) && (this.getmaxHP()/2 > this.getHP())){
        return this + " threw a rotten drink at "+other+ " and hit them perfectly. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + (ownDamage * 1.2) + " points of damage.";
      }
      else{
        return this + " threw a rotten drink at "+other+ " and hit them perfectly. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + ownDamage + " points of damage.";
      }
    }else{
      return this+"'s rating is too low to throw a drink. Instead "+attack(other);
    }

  }

  public String support(Adventurer other){
    return support();
  }

  /*Increases rating by 3 (restores special) and 10 hp to self.*/
  public String support(){
    int hp = 10;
    setHP(getHP()+hp);
    return this+" makes and drinks a delicious matcha tea to add "+restoreSpecial(3)+" to their "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
