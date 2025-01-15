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
    for (int i = 1; i < 81; i++){
      Text.go(1,i);
      System.out.print(Text.colorize(" ",Text.CYAN+Text.BACKGROUND));
    }
    for (int i = 1; i < 81; i++){
      Text.go(30,i);
      System.out.print(Text.colorize(" ",Text.CYAN+Text.BACKGROUND));
    }
    for (int i = 2; i < 30; i++){
      Text.go(i,1);
      System.out.print(Text.colorize("  ",Text.CYAN+Text.BACKGROUND));
    }
    for (int i = 2; i < 30; i++){
      Text.go(i,79);
      System.out.print(Text.colorize("  ",Text.CYAN+Text.BACKGROUND));
    }
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    TextBox(startRow, startCol, HEIGHT-startRow, WIDTH-startCol, s);
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

  public static void TextBox(int row, int col, int width, int height, String text){
    String[] queue = text.split(" ");
    Text.go(row, col);
    int currentCol=col;
    int currentRow=row;
    for (int i=0; i<queue.length; i++) {
      if (currentCol+queue[i].length()>col+width) {
        currentRow++;
        Text.go(currentRow, col);
        currentCol=col;
      }
      if (currentRow<row+height) {
        System.out.print(queue[i]);
        if (currentCol+queue[i].length()<col+width) {
           Text.go(currentRow, currentCol+queue[i].length()+1);
           currentCol+=queue[i].length()+1;
        } else {
          Text.go(currentRow, currentCol+queue[i].length());
          currentCol+=queue[i].length();
        }
      }
    }
  }

    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(ArrayList<Adventurer> party){
      int type = (int)(3*Math.random());
      if (type==0) {
        String[] names = new String[]{"Camellia", "Chamomile", "Rose", "Starry", "Lavender", "Jasmine", "Cinnamon", "Lemon"};
        String name = names[(int) (Math.random()*names.length)];
        return new Boss(name, party);
      } else if (type==1) {
        String[] names = new String[]{"Maple", "Hazel", "Reese", "Charlotte", "Candy", "Coco", "Amandine", "Madeline",
        "Milo", "Sugar", "Taffy", "Graham"};
        String name = names[(int) (Math.random()*names.length)];
        return new PastryChef(name, party);
      } else {
        String[] names = new String[]{"Brie", "Clementine", "Juniper", "Romaine", "Cherry", "Olive", "Apple", "Ringo", "Anise",
        "Juniper", "Sage", "Basil", "Saffron"};
        String name = names[(int) (Math.random()*names.length)];
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
    public static void drawParty(ArrayList<Adventurer> party,int startRow){
      for (int i=0; i<party.size(); i++) {
        Adventurer member = party.get(i);
        TextBox(startRow, 5+i*78/party.size(), 9, 1, member.toString());
        TextBox(startRow+1, 5+i*78/party.size(), 9, 1, "HP: "+member.getHP());
        TextBox(startRow+2, 5+i*78/party.size(), 9, 1, member.getSpecialName()+": "+member.getSpecial());
        TextBox(startRow+3, 5+i*78/party.size(), 9, 1, "sick: "+member.hasSalmonella());
      }
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if (maxHP/2 > hp){
      return Text.colorize(output, Text.BOLD, Text.RED);
    }
    if (maxHP*(3/4)>hp){
      return Text.colorize(output, Text.BOLD, Text.YELLOW);
    }
    else{
      return Text.colorize(output, Text.BOLD, Text.WHITE);
    }
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){

    drawBackground();

    drawParty(party, 2);
    drawParty(enemies, 7);
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
    int enemyCount = (int)(Math.random()*3)+1;
    if (enemyCount == 1){
      enemies.add(new Boss("Maple", enemies));
    }
    else{
      for (int i = 0; i < enemyCount; i++){
        enemies.add(createRandomAdventurer(enemies));
      }
    }

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    int partyCount = (int)(Math.random()*3)+2;
    for (int i = 0; i < partyCount; i++){
      party.add(createRandomAdventurer(party));
    }

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    Text.go(27, 4);
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          party.get(whichPlayer).attack(enemies.get(whichOpponent));
        }
        else if(input.equals("special") || input.equals("sp")){
          party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          int playerNumber = Integer.parseInt(input.substring(input.length()-1));
          party.get(whichPlayer).support(party.get(playerNumber));
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          Text.go(25, 4);
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          Text.go(27, 4);
          String prompt = "press enter to see enemy's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        if(input.equals("attack") || input.equals("a")){
          enemies.get(whichOpponent).attack(party.get(whichPlayer));
        }
        else if(input.equals("special") || input.equals("sp")){
          enemies.get(whichOpponent).specialAttack(party.get(whichPlayer));
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          int playerNumber = Integer.parseInt(input.substring(input.length()-1));
          enemies.get(whichOpponent).support(enemies.get(playerNumber));

        //Decide where to draw the following prompt:
        Text.go(27, 4);
        String prompt = "press enter to see next turn";

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        Text.go(25, 4);
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(party, enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
  }
