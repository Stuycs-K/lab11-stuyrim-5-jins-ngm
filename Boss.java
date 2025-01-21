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
    this(name,50, party);
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

  /*Deal 4-8 damage to opponent*/
  public String attack(Adventurer other){
    int damage;
    if (this.getmaxHP()/2 > this.getHP()){
      damage = (int)(1.2*((int)(Math.random()*5)+4));
    }
    else{
      damage = (int)(Math.random()*5)+4;
    }
    int dmgMessage = other.applyDamage(damage);
    if (dmgMessage==-2){
      return this + " made a disgusting drink for "+ other + " and dealt "+ damage +
      " points of damage. "+other+" threw up and "+this+" laughed.";
    } else if (dmgMessage==-1) {
      return this + " made a disgusting drink for "+ other + " and dealt "+ damage +
      " points of damage to "+other+"'s shield.";
    } else {
      return this + " made a disgusting drink for "+ other + " and dealt enough damage to break "+other+
      "'s shield, in addition to dealing "+dmgMessage+" points of damage to "+other+".";
    }
  }

  /*Deal 2-13 damage to opponent, only if rating is high enough.
  *Reduces rating by 3.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 3){
      setSpecial(getSpecial()-3);
      int ownDamage = (int)(Math.random()*8)+1;
      this.applyDamage(ownDamage);
      int damage;
      if (ownDamage >= 3){
        damage = (int)(Math.random()*9)+6;
        other.applyDamage(damage);
      } else {
        damage = (int)(Math.random()*5)+5;
        other.applyDamage(damage);
      }
      if ((damage <= 4) && (this.getmaxHP()/2 > this.getHP())){
        return this + " threw a hot drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + (ownDamage * 1.2) + " points of damage.";
      }
      if (damage <= 4){
        return this + " threw a hot drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + ownDamage + " points of damage.";
      }
      if ((damage > 4) && (this.getmaxHP()/2 > this.getHP())){
        return this + " threw a hot drink at "+other+ " and hit them perfectly. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + (ownDamage * 1.2) + " points of damage.";
      }
      else{
        return this + " threw a hot drink at "+other+ " and hit them perfectly. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + ownDamage + " points of damage.";
      }
    }else{
      if (this.hasSalmonella()){
        int salmonellaDamage = (int)(Math.random()*3)+1;
        this.applyDamage(salmonellaDamage);
      }
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
    setSalmonella(false);
    return this+" makes and drinks a delicious matcha tea to add "+restoreSpecial(3)+" to their "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
