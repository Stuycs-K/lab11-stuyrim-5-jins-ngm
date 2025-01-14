public class PrepChef extends Adventurer{
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
    int moneySpent = 3+(int)(Math.random()*3);
    if(getSpecial() >= moneySpent){
      setSpecial(getSpecial()-moneySpent);
      int ownDamage = (int)(Math.random()*8)+1;
      this.applyDamage(ownDamage);
      return this+" went out and spent "+moneySpent+" on buying everyone aprons! ";
    }else{
      return this+" wanted to go out and buy everyone aprons, but didn't have enough money. Instead, "+attack(other);
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
