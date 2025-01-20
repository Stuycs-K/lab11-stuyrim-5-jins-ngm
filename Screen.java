public class Screen{
    public static void drawWinScreen(){
    Text.clear();
      for (int i = 1; i < 81; i++){
        Text.go(1,i);
        System.out.print(Text.colorize(" ",Text.GREEN+Text.BACKGROUND));
      }
      for (int i = 1; i < 81; i++){
        Text.go(30,i);
        System.out.print(Text.colorize(" ",Text.GREEN+Text.BACKGROUND));
      }
      for (int i = 1; i < 31; i++){
        Text.go(i,1);
        System.out.print(Text.colorize(" ",Text.GREEN+Text.BACKGROUND));
      }
      for (int i = 1; i < 31; i++){
        Text.go(i,80);
        System.out.print(Text.colorize(" ",Text.GREEN+Text.BACKGROUND));
      }
      //"you"
      for (int i = 0; i < 5; i++){
        Text.go(6+i,22+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(10-i,26+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        for (int n = 0; n < 3; n++){
            Text.go(10+n,26);
            System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        }
      }
      for (int i = 0; i < 8; i++){
        Text.go(6,37+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(12,37+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 7; i++){
        Text.go(6+i,37);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(6+i,44);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 8; i++){
        Text.go(12,51+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 7; i++){
        Text.go(6+i,51);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(6+i,58);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      //"won"
      for (int i = 0; i < 6; i++){
        Text.go(16+i,15+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(21-i,20+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(16+i,25+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(21-i,30+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 8; i++){
        Text.go(16,40+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(21,40+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 6; i++){
        Text.go(16+i,40);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(16+i,47);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 8; i++){
        Text.go(16,40+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(21,40+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 6; i++){
        Text.go(16+i,52);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
        Text.go(16+i,59);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 6; i++){
        Text.go(16+i,53+i);
        System.out.print(Text.colorize(" ",Text.MAGENTA+Text.BACKGROUND));
      }
      for (int i = 0; i < 4; i++){
        Text.go(16+i, 65);
        System.out.print(Text.colorize("  ",Text.MAGENTA+Text.BACKGROUND));
      }
      Text.go(21, 65);
      System.out.print(Text.colorize("  ",Text.MAGENTA+Text.BACKGROUND));
    Text.go(31,1);
    }

    public static void main(String[] args){
      drawWinScreen();
    }
}