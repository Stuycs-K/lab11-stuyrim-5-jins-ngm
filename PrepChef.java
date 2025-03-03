import java.util.ArrayList;
public class PrepChef extends Adventurer{
  int money, moneyMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public PrepChef(String name, int hp, ArrayList<Adventurer> party){
    super(name,hp,party);
    moneyMax = 10;
    money = moneyMax/2;
  }

  public PrepChef(String name, ArrayList<Adventurer> party){
    this(name,25,party);
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "money";
  }

  public int getSpecial(){
    return money;
  }

  public void setSpecial(int n){
    money = Math.max(0, n);
  }

  public int getSpecialMax(){
    return moneyMax;
  }

  /*Decreases opponent's special*/
  public String attack(Adventurer other){
    int removed = (int)(Math.random()*other.getSpecial()*0.6)+1;
    if (this.isBuffed()){
      removed+=2;
    }
    setBuffed(false);
    if (other.isShielded()) {
      return this + " tried to steal ingredients from "+other+", but couldn't get past their shield.";
    } else {
      other.setSpecial(other.getSpecial()-removed);
      return this + " stole ingredients from "+ other + " and took away "+ removed + " " +
      other.getSpecialName()+". "+this+" celebrates.";
    }
  }

  /*Decreases opponent's special, while losing 3 money.
  */
  public String specialAttack(Adventurer other){
    setBuffed(false);
    int moneySpent = 3+(int)(Math.random()*3);
    if(getSpecial() >= moneySpent){
      setSpecial(getSpecial()-moneySpent);
      int shieldHP = 5*moneySpent/2;
      boolean rebound;
      if (Math.random()>0.5) {
        rebound=true;
      } else {
        rebound=false;
      }
      Shield s = new Shield(shieldHP, rebound, other);
      for (int i=0; i<getParty().size(); i++) {
        Adventurer member=getParty().get(i);
        member.setShield(s);
      }
      String msg = this+" went out and spent "+moneySpent+" money on buying everyone aprons! They are now shielded from potential attack. ";
      if (rebound) {
        msg+="These aprons will deal rebound damage whenever "+other+" attacks.";
      }
      return msg;
    }else{
      return this+" wanted to go out and buy everyone aprons, but didn't have enough money. Instead, "+attack(other);
    }

  }

  public String support(Adventurer other){
    return support();
  }

  /*Increases money by 3 (restores special) and 10 hp to self.*/
  public String support(){
    int hp = (int)(Math.random()*4)+3;
    int specialRestore = 3+(int)(Math.random())+(int)(Math.random());
    for (int i=0; i<getParty().size(); i++) {
      Adventurer member=getParty().get(i);
      member.setHP(member.getHP()+hp);
      member.restoreSpecial(specialRestore);
      member.setBuffed(true);
      member.setSalmonella(false);
    }
    String s= this+" bought and prepped ingredients, restoring "+hp+" HP and "+specialRestore+" ";
    for (int i=0; i<getParty().size(); i++) {
      s+=getParty().get(i).getSpecialName();
      if (i<getParty().size()-1) {
        s+="/";
      }
    }
    s+=" for their party. They are so excited to cook that everyone's moves are more effective on their next turn.";
    return s;
  }
}
