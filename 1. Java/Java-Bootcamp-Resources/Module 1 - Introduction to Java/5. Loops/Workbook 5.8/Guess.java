import java.util.Scanner;

public class Guess  {
    public static void main(String[] args) {

       System.out.print("I chose a number between 1 and 5. Try to guess it: ");
        int num = (int)(Math.random()*6);
       Scanner scan = new Scanner(System.in);
       
       //See Learn the Part for detailed instructions.
        int guess = scan.nextInt();
        while(num!=guess)
        {
            System.out.println("Guess again: ");
            guess = scan.nextInt();
        }
        System.out.println("you got it right");
        scan.close();
    }

}
