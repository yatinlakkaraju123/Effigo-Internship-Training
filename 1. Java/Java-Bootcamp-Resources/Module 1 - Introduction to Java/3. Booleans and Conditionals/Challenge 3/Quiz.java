import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        
        //See Learn the Part for the complete instructions.


        Scanner scan = new Scanner(System.in);

        System.out.println("1. Which country held the 2016 Summer Olympics?");
        System.out.println("\ta) China\n\tb) Ireland\n\tc) Brazil\n\td) Italy\n");
        //store answer 1
        char ans1 = scan.next().charAt(0);
        System.out.println("\n2. Which planet is the hottest?");

        System.out.println("\ta) Venus\n\tb) Saturn\n\tc) Mercury\n\td) Mars\n");
        //store answer 2
        char ans2 = scan.next().charAt(0);
        System.out.println("\n3. What is the rarest blood type?");
        System.out.println("\ta) O\n\tb) A\n\tc) B\n\td) AB-Negative\n");
        //store answer 3
        char ans3 = scan.next().charAt(0);
        System.out.println("\n4. Which one of these characters is friends with Harry Potter?");
        System.out.println("\ta) Ron Weasley\n\tb) Hermione Granger\n\tc) Draco Malfoy\n");
        //store answer 4
        char ans4 = scan.next().charAt(0);
        int score = 0;
        if(ans1=='c') score+=5;
        if(ans2=='a') score+=5;
        if(ans3=='d') score+=5;
        if(ans4=='a'||ans4=='b') score+=5;

        //Task 2: Check each answer - For each correct answer, add 5 points to the score. 
        
        System.out.println("Your final score is: " + score + "/20");

        //Task 3: print a message depending on the score
        if(score>=15) System.out.println("Wow, you know your stuff!");
        else if(score>5 && score<15) System.out.println("Not bad!");
        else System.out.println("better luck next time");
        // if the score is 15 or higher, print: "Wow, you know your stuff!";
        // if the score is between 5 and 15, print "Not bad!";
        // else, better luck next time. 


        scan.close();

    }
}
