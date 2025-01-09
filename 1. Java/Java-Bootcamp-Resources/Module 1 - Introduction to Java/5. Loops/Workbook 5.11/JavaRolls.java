import java.util.Scanner;

public class JavaRolls {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // See Learn the Part for detailed instructions.
        System.out.println("Let's play Rolling Java. Type anything to start.");
        scan.nextLine();
        System.out.println("Great, here are the rules:\n" + //
                        "- If you roll a 6, the game stops.\n- If you roll a 4, nothing happens.\n- Otherwise, you get 1 point.\\n" + //
                                                        "You must collect at least 3 points to win. Enter anything to roll:");
                                                       
        
        int score = 0;
        while(true)
        {   scan.nextLine();
            int diceRoll = rollDice();
            System.out.println("you rolled:"+diceRoll);
            if(diceRoll==6) 
            {
                System.out.println("End of game");
            break;
            }
            else if(diceRoll==4)
            {
                System.out.println("Zero points. Keep rolling.");
            }
            else
            {
                score++;
                System.out.println("One point. Keep rolling");
            }
           

        }
        if(score>=3) System.out.println("Wow, that's lucky. You win!");
        else System.out.println("Tough luck, you lose :(");
        scan.close();
        
        
    }
    /**
 * Function name: rollDice
 * @return randomNumber (int)
 *
 * Inside the function:
 *  - return a random number between one and six. 
 */
public static int rollDice()
{
    return (int)(Math.random()*6)+1;
}
  
  

  
  
}
