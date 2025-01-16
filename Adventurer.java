import java.util.Random;
import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private boolean salmonella, buffed;
  private Shield shield;
  private ArrayList<Adventurer> party;

  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  standard methods
  */

  public void applyDamage(int amount){
    this.HP = Math.max(this.HP-amount, 0);
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string", new ArrayList<Adventurer>());
  }

  public Adventurer(String name, ArrayList<Adventurer> party){
    this(name, 10, party);
  }

  public Adventurer(String name, int hp, ArrayList<Adventurer> party){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    salmonella=false;
    shield=null;
    buffed=false;
    this.party=party;
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  public boolean hasSalmonella() {
    return salmonella;
  }

  public boolean isShielded() {
    return !(shield==null);
  }

  public Shield getShield() {
    return shield;
  }

  public boolean isBuffed() {
    return buffed;
  }

  public ArrayList<Adventurer> getParty() {
    return party;
  }

  //Set Methods
  public void setHP(int health){
    if (health > maxHP){
      this.HP = maxHP;
    }
    else{
      this.HP = health;
    }
  }

  public void setName(String s){
    this.name = s;
  }

  public void setSalmonella(boolean b) {
    salmonella=b;
  }

  public void setBuffed(boolean b) {
    buffed=b;
  }

  public void setShield(Shield s) {
    shield=s;
  }
}
