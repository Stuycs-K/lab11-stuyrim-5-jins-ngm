import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    //screen border
    for (int i = 1; i < 81; i++){
      Text.go(1,i);
      System.out.print(Text.colorize("\u2500",Text.BOLD,Text.CYAN));
    }
    for (int i = 1; i < 81; i++){
      Text.go(30,i);
      System.out.print(Text.colorize("\u2500",Text.BOLD,Text.CYAN));
    }
    for (int i = 1; i < 31; i++){
      Text.go(i,1);
      System.out.print(Text.colorize("\u2502",Text.BOLD,Text.CYAN));
    }
    for (int i = 1; i < 31; i++){
      Text.go(i,80);
      System.out.print(Text.colorize("\u2502",Text.BOLD,Text.CYAN));
    }
    //lines between
    for (int i = 2; i < 80; i++){
      Text.go(6,i);
      System.out.print(Text.colorize("\u2500",Text.BOLD,Text.CYAN));
    }
    for (int i = 2; i < 80; i++){
      Text.go(11,i);
      System.out.print(Text.colorize("\u2500",Text.BOLD,Text.CYAN));
    }
    for (int i = 12; i < 25; i++){
      Text.go(i,40);
      System.out.print(Text.colorize("\u2502",Text.BOLD,Text.CYAN));
    }
    for (int i = 2; i < 80; i++){
      Text.go(25,i);
      System.out.print(Text.colorize("\u2500",Text.BOLD,Text.CYAN));
    }
    //corners
    Text.go(1, 1);
    System.out.print(Text.colorize("\u250C", Text.BOLD, Text.CYAN));
    Text.go(1, 80);
    System.out.print(Text.colorize("\u2510", Text.BOLD, Text.CYAN));
    Text.go(30, 1);
    System.out.print(Text.colorize("\u2514", Text.BOLD, Text.CYAN));
    Text.go(30, 80);
    System.out.print(Text.colorize("\u2514", Text.BOLD, Text.CYAN));
    Text.go(6, 1);
    System.out.print(Text.colorize("\u251C", Text.BOLD, Text.CYAN));
    Text.go(11, 1);
    System.out.print(Text.colorize("\u251C", Text.BOLD, Text.CYAN));
    Text.go(25, 1);
    System.out.print(Text.colorize("\u251C", Text.BOLD, Text.CYAN));
    Text.go(6, 80);
    System.out.print(Text.colorize("\u2524", Text.BOLD, Text.CYAN));
    Text.go(11, 80);
    System.out.print(Text.colorize("\u2524", Text.BOLD, Text.CYAN));
    Text.go(25, 80);
    System.out.print(Text.colorize("\u2524", Text.BOLD, Text.CYAN));
    Text.go(11, 40);
    System.out.print(Text.colorize("\u252C", Text.BOLD, Text.CYAN));
    Text.go(25, 40);
    System.out.print(Text.colorize("\u2534", Text.BOLD, Text.CYAN));
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    TextBox(startRow, startCol, WIDTH-startCol, HEIGHT-startRow, s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */

  public static int countChar(String s) {
    int ans=0;
    for (int i=0; i<s.length(); i++) {
      if (s.charAt(i)>=32 && s.charAt(i)<=127) {
        ans++;
      }
    }
    while (s.contains("\u001b")) {
      ans-=2;
      s=s.substring(0, s.indexOf("\u001b"))+s.substring(s.indexOf("\u001b")+1);
    }
    return ans;
  }

  public static void TextBox(int row, int col, int width, int height, String text){
    String[] queue;
    if (!text.equals(" ")) {
      queue = text.split(" ");
    } else {
      queue = new String[]{" "};
    }
    Text.go(row, col);
    int currentCol=col;
    int currentRow=row;
    for (int i=0; i<queue.length; i++) {
      if (currentCol+countChar(queue[i])>col+width) {
        currentRow++;
        Text.go(currentRow, col);
        currentCol=col;
      }
      if (currentRow<row+height) {
        System.out.print(queue[i]);
        if (currentCol+countChar(queue[i])<col+width) {
           System.out.print(" ");
           Text.go(currentRow, currentCol+countChar(queue[i])+1);
           currentCol+=countChar(queue[i])+1;
        } else {
          Text.go(currentRow, currentCol+countChar(queue[i]));
          currentCol+=countChar(queue[i]);
        }
      }
    }
  }

    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(ArrayList<Adventurer> party, ArrayList<ArrayList<String>> names){
      int type = (int)(3*Math.random());
      int index = (int) (Math.random()*names.get(type).size());
      String name = names.get(type).get(index);
      names.get(type).remove(index);
      if (type==0) {
        return new Boss(name, party);
      } else if (type==1) {
        return new PastryChef(name, party);
      } else {
        return new PrepChef(name, party);
      }
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow, boolean change){
      if (change) {
        for (int i=0; i<4; i++) {
          for (int j=3; j<80; j++) {
            TextBox(startRow+i, j, 1, 1, " ");
          }
        }
      }
      for (int i=0; i<party.size(); i++) {
        Adventurer member = party.get(i);
        String type;
        if (member.getSpecialName().equals("rating")) {
          type = "Barista";
        } else if (member.getSpecialName().equals("sugar")) {
          type = "PastryChef";
        } else {
          type = "PrepChef";
        }
        String wellness;
        if (member.hasSalmonella()) {
          wellness="sick";
        } else {
          wellness="well";
        }
        String shieldMessage;
        if (!member.isShielded()) {
          shieldMessage="unshielded";
        } else {
          shieldMessage="shieldHP: "+member.getShield().getHP();
        }
        String coloredHP = colorByPercent(member.getHP(), member.getmaxHP());
        TextBox(startRow, 3+i*78/party.size(), 78/party.size(), 1, member.toString()+" ("+type+")");
        TextBox(startRow+1, 3+i*78/party.size(), 78/party.size(), 1, "HP: "+coloredHP);
        TextBox(startRow+2, 3+i*78/party.size(), 78/party.size(), 1, member.getSpecialName()+": "+member.getSpecial()+"/"+member.getSpecialMax());
        TextBox(startRow+3, 3+i*78/party.size(), 78/party.size(), 1, wellness+"/"+shieldMessage);
      }
    }

  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    //String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if (maxHP*(0.5) > hp){
      return Text.colorize(hp+"", Text.BOLD, Text.RED)+Text.colorize("/"+maxHP, Text.BOLD);
    }
    if (maxHP*(0.75)>hp){
      return Text.colorize(hp+"", Text.BOLD, Text.YELLOW)+Text.colorize("/"+maxHP, Text.BOLD);
    }
    else{
      return Text.colorize(hp+"", Text.BOLD, Text.WHITE)+Text.colorize("/"+maxHP, Text.BOLD);
    }
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies, boolean change){

    drawBackground();

    drawParty(party, 2, change);
    drawParty(enemies, 7, change);
    Text.go(29, 2);

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void printMessage(String msg, ArrayList<String> messageQueue, int col, int length) {
    // if (msg.contains("is out of HP and is no longer able to fight.") || msg.contains("is out of HP and is no longer able to fight.")){
    //   msg = Text.colorize(msg, Text.BOLD, Text.RED);
    // }
    // if (msg.contains("All members of the enemy party have forfeited. You have won!")){
    //   msg = Text.colorize(msg, Text.BOLD, Text.GREEN);
    // }
    messageQueue.add(msg);
    if (messageQueue.size()>length) {
      messageQueue.remove(0);
    }
    for (int i=12; i<=24; i++) {
      for (int j=col; j<col+col%2+36; j++) {
        TextBox(i, j, 1, 1, " ");
      }
    }
    for (int i=0; i<messageQueue.size(); i++) {
      if (i<messageQueue.size()-1) {
        TextBox(12+i*12/length, col, 36, 12/length, messageQueue.get(i));
      } else {
        TextBox(12+i*12/length, col, 36, 12/length+1, messageQueue.get(i));
      }
    }
  }

  public static int checkIfDead(ArrayList<Adventurer> party, ArrayList<String> messageQueueRight) {
    int ans=0;
    for (int i=0; i<party.size(); i++) {
      Adventurer a = party.get(i);
      if (a.getHP()<=0) {
        printMessage(a.toString()+" is out of HP and is no longer able to fight.", messageQueueRight, 42, 4);
        party.remove(a);
        i--;
        ans++;
      }
    }
    return ans;
  }

  public static void reboundDamage(Adventurer attacker, Adventurer enemy, ArrayList<String> messageQueueRight) {
    //shield rebound damage
    if (!attacker.getSpecialName().equals("money") && enemy.isShielded()) {
      int dmgMessage = enemy.getShield().reboundDamage(attacker);
      if (dmgMessage==-2){
        printMessage(""+attacker+" lost 2 HP due to rebound damage from "+enemy+"'s shield.", messageQueueRight, 42, 4);
      } else if (dmgMessage==-1) {
        printMessage(attacker + "'s shield lost 2 HP due to rebound damage from "+enemy+"'s shield.", messageQueueRight, 42, 4);
      } else if (dmgMessage>=0) {
        printMessage("Because "+enemy+"'s shield dealt rebound damage, "+attacker+"'s shield broke and they lost "+dmgMessage+" HP.", messageQueueRight, 42, 4);
      }
    }
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();

    //testing the terminal functions
    //drawText("the quick brown fox jumps over the lazy dog.", 20, 70);

    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();
    ArrayList<ArrayList<String>> names=new ArrayList<ArrayList<String>>();
    names.add(new ArrayList<String>(Arrays.asList(new String[]{"Camellia", "Chamomile", "Rose", "Starry", "Lavender", "Jasmine", "Cinnamon", "Lemon"})));
    names.add(new ArrayList<String>(Arrays.asList(new String[]{"Maple", "Hazel", "Reese", "Charlotte", "Candy", "Coco", "Amandine", "Madeline", "Milo", "Sugar", "Taffy", "Graham"})));
    names.add(new ArrayList<String>(Arrays.asList(new String[]{"Brie", "Clementine", "Juniper", "Romaine", "Cherry", "Olive", "Apple", "Ringo", "Anise", "Juniper", "Sage", "Basil", "Saffron"})));
    int enemyCount = (int)(Math.random()*3)+1;
    if (enemyCount == 1){
      enemies.add(new Boss("Hazelnut", enemies));
    }
    else{
      boolean bossAdded = false;
      for (int i = 0; i < enemyCount; i++){
        enemies.add(createRandomAdventurer(enemies, names));
      }
    }

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    int partyCount = (int)(Math.random()*3)+2;
    for (int i = 0; i < partyCount; i++){
      party.add(createRandomAdventurer(party, names));
    }

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    int died=0;
    Scanner in = new Scanner(System.in);
    ArrayList<String> messageQueueLeft = new ArrayList<String>(2);
    ArrayList<String> messageQueueRight = new ArrayList<String>(4);
    String msg = "message!";
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies, false);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input

      //display event based on last turn's input
      //NOTE TO SELF make conditional to only do stuff if hp>0
      while (partyTurn && party.size()>0 && enemies.size()>0){
        for (int i=2; i<80; i++) {
          drawText(" ", 26, i);
        }
        String prompt = "Enter command for "+party.get(whichPlayer)+": (a)ttack/(su)pport/(sp)ecial/(q)uit + 0/1/...";
        drawText(prompt, 26, 3);
        input="some string";

        //input loop
        while (!(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.startsWith("attack") || input.startsWith("a")
        || input.startsWith("special") || input.startsWith("sp") || input.startsWith("su") || input.startsWith("support"))) {
          for (int i=2; i<80; i++) {
            drawText(" ", 27, i);
          }
          Text.go(27, 3);
          input = userInput(in);
          try {
            if (!(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))) {
              int which = Integer.parseInt(input.substring(input.length()-1));
              if (input.startsWith("attack") || input.startsWith("a") || input.startsWith("special") || input.startsWith("sp")) {
                enemies.get(which);
              } else if (input.startsWith("su") || input.startsWith("support")) {
                party.get(which);
              }
            }
          } catch (Exception ex) {
            if (!(input.startsWith("su") || input.startsWith("support"))) {
              input="some string";
            }
          }
        }

        drawScreen(party, enemies, (died>0));
        if (! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))) {
          //Process user input for the last Adventurer:
          if(input.startsWith("attack") || input.startsWith("a")){
            whichOpponent = Integer.parseInt(input.substring(input.length()-1));
            msg = party.get(whichPlayer).attack(enemies.get(whichOpponent));
            //shield rebound damage
            reboundDamage(party.get(whichPlayer), enemies.get(whichOpponent), messageQueueRight);
          }
          else if(input.startsWith("special") || input.startsWith("sp")){
            whichOpponent = Integer.parseInt(input.substring(input.length()-1));
            msg = party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
            
            //if PastryChef attacks the entire enemy party 
            if (party.get(whichPlayer).getSpecialName().equals("sugar") && msg.equals("entire enemy party")) {
              printMessage(party.get(whichPlayer)+" uses their extra sugar to attack the entire enemy party!", messageQueueLeft, 3, 2);
              Text.wait(1500);
              for (int i=0; i<enemies.size()-1; i++) {
                printMessage(party.get(whichPlayer).attack(enemies.get(i)), messageQueueLeft, 3, 2);
                reboundDamage(party.get(whichPlayer), enemies.get(i), messageQueueRight);
                Text.wait(1500);
              }
              msg = party.get(whichPlayer).attack(enemies.get(enemies.size()-1));
            } else {
              //shield rebound damage
              reboundDamage(party.get(whichPlayer), enemies.get(whichOpponent), messageQueueRight);
            }
          }
          else if(input.startsWith("su") || input.startsWith("support")){
            //"support 0" or "su 0" or "su 2" etc.
            //assume the value that follows su  is an integer.
            try {
              int playerNumber = Integer.parseInt(input.substring(input.length()-1));
              if (party.get(whichPlayer).getSpecialName().equals("sugar") && playerNumber==whichPlayer) {
                msg = party.get(whichPlayer).support();
              } else {
                msg = party.get(whichPlayer).support(party.get(playerNumber));
              }
            } catch (Exception ex) {
              msg = party.get(whichPlayer).support();
            }
          }
          if (party.get(whichPlayer).hasSalmonella()) {
            int salmonellaDamage = (int)(Math.random()*3)+2;
            party.get(whichPlayer).setHP(party.get(whichPlayer).getHP()-salmonellaDamage);
            printMessage(""+party.get(whichPlayer)+" is suffering from salmonella and lost "+salmonellaDamage+" HP.", messageQueueRight, 42, 4);
          }
          //You should decide when you want to re-ask for user input

          //example debug statment
          //TextBox(15,41,38,4,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

          //If no errors:
          drawScreen(party, enemies, false);
          died=checkIfDead(party, messageQueueRight);
          whichPlayer-=died;
          died=Math.max(died, checkIfDead(enemies, messageQueueRight));

          printMessage(msg, messageQueueLeft, 3, 2);
          whichPlayer++;

          if(whichPlayer >= party.size()){
            partyTurn = false;
            whichPlayer = 0;
            prompt = "press enter to see enemy's turn";
            whichOpponent = 0;
            drawText(prompt, 26, 3);
            Text.go(27, 3);
          }
        } else {
          partyTurn=false;
        }
        
        //done with one party member
      }

      for (int i=2; i<80; i++) {
        drawText(" ", 26, i);
        drawText(" ", 27, i);
      }

      while (!partyTurn && !(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) && enemies.size()>0 && party.size()!=0){
        //not the party turn!

        Adventurer enemy = enemies.get(whichOpponent);
        Adventurer target = party.get((int)(Math.random()*party.size()));
        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        drawText(prompt, 26, 3);
        input="some string";
        while (!input.equals("")) {
          for (int i=2; i<80; i++) {
            drawText(" ", 27, i);
          }
          Text.go(27, 3);
          input=userInput(in);
        }

        drawScreen(party, enemies, (died>0));
        if(Math.random()<1.0/3){
          msg=enemy.attack(target);
          //rebound damage
          reboundDamage(enemy, target, messageQueueRight);
        } else if(Math.random()<0.5){
          msg=enemy.specialAttack(target);
          //rebound damage
          
          //if enemy is PastryChef and attacks the entire party
          if (enemy.getSpecialName().equals("sugar") && msg.equals("entire enemy party")) {
            printMessage(enemy+" uses their extra sugar to attack the entire party!", messageQueueLeft, 3, 2);
            Text.wait(1500);
            for (int i=0; i<party.size()-1; i++) {
              printMessage(enemy.attack(party.get(i)), messageQueueLeft, 3, 2);
              reboundDamage(enemy, party.get(i), messageQueueRight);
              Text.wait(1500);
            }
            msg = enemy.attack(party.get(party.size()-1));
          } else {
            reboundDamage(enemy, target, messageQueueRight);
          }
        } else {
          int targetIndex =(int)(Math.random()*enemies.size());
          target = enemies.get(targetIndex);
          if (enemy.getSpecialName().equals("sugar") && whichOpponent==targetIndex) {
            msg = enemy.support();
          } else {
            msg = enemy.support(target);
          }
        }
        if (enemy.hasSalmonella()) {
          int salmonellaDamage = (int)(Math.random()*3)+2;
          enemy.setHP(enemy.getHP()-salmonellaDamage);
          printMessage(""+enemy+" is suffering from salmonella and lost "+salmonellaDamage+" points of HP.", messageQueueRight, 42, 4);
        }

        drawScreen(party, enemies, false);
        died = checkIfDead(enemies, messageQueueRight);
        whichOpponent-=died;
        died=Math.max(died, checkIfDead(party, messageQueueRight));

        printMessage(msg, messageQueueLeft,3, 2);

        whichOpponent++;

        if (whichOpponent>=enemies.size()) {
          whichPlayer = 0;
          whichOpponent = 0;
          turn++;
          partyTurn=true;
        }
      }//end of one enemy.

      if (party.size()==0 || enemies.size()==0) {
        input="q";
      }

    }//end of main game loop
    
    if (party.size()==0) {
      // printMessage("All members of your party have forfeited. You have lost!", messageQueueRight, 42, 4);
      Screen.drawLoseScreen();
    } else if (enemies.size()==0) {
      // printMessage("All members of the enemy party have forfeited. You have won!", messageQueueRight, 42, 4);
      Screen.drawWinScreen();
    }

    //After quit reset things:
    quit();
  }
}
