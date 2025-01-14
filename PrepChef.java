public class PreChef extends Adventurer{
  int money, moneyMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public PrepChef(String name, int hp){
    super(name,hp);
    moneyMax = 10;
    money = moneyMax/2;
    setSalmonella(false);
  }

  public PrepChef(String name){
    this(name,75);
  }

  public PrepChef(){
    this("Maddie");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "money";
  }

  public int getSpecial(){
    return money;
  }

  public void setSpecial(int n){
    money = n;
  }

  public int getSpecialMax(){
    return moneyMax;
  }

  /*Decreases opponent's special, while losing 2 money*/
  public String attack(Adventurer other){
    int removed = (int)(Math.random()*other.getSpecial());
    setSpecial(getSpecial()-2);
    other.setSpecial(other.getSpecial()-removed);
    return this + " stole ingredients from "+ other + " and took away "+ removed +
    other.getSpecialName+". "+this+" celebrates.";
  }

  /*Deal 2-13 damage to opponent, only if money is high enough.
  *Reduces money by 3.
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
      if ((damage <= 4) && (this.getmaxHP/2 > this.getHP)){
        return this + " threw a rotten drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + (ownDamage * 1.2) + " points of damage.";
      }
      if (damage <= 4){
        return this + " threw a rotten drink at "+other+ " but almost missed. "+this+" dealt "+damage+
        " points of damage, but also received a bad review and lost " + ownDamage + " points of damage.";
      }
      if ((damage > 4) && (this.getmaxHP/2 > this.getHP)){
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
  /*Increases money by 3 (restores special) and 10 hp to self.*/
  public String support(){
    int hp = 10;
    setHP(getHP()+hp);
    return this+" makes and drinks a delicious matcha tea to add "+restoreSpecial(3)+" to their "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
