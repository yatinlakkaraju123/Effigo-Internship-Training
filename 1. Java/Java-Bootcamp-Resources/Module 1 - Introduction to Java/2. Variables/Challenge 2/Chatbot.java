//import Scanner

import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        
        //See Learn the Part for the complete instructions. 

        
        //set up scanner. 
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello. What is your name?");
        //Pick up user's name and store it. 
        String userName = sc.nextLine();
                           
        //add new a line before asking next question. 
        System.out.println("\nHi "+userName+"! I'm Javabot. Where are you from?");
        //Pick up user's home and store it. 
        String userHome = sc.nextLine();

        //add new a line before asking next question.
        System.out.println("\nI hear it's beautiful at "+userHome+"! I'm from a place called Oracle");
        System.out.println("How old are you?");
        //Pick up age and store it.
        int age = sc.nextInt();

        //add new a line before asking next question.
        System.out.println("So you're "+age+", cool! I'm 400 years old.");
        System.out.println("This means I'm "+(double)(age)/400+"times older than you.");
        System.out.println("Enough about me. What's your favourite language? (just don't say Python)");
        //Pick up language and store it. 
        sc.nextLine();
        String language = sc.nextLine();

        //add new a line here.
        System.out.println(""+language+", that's great! Nice chatting with you "+userName+". I have to log off now. See ya!");
        
        //close scanner. 
        sc.close();
        
    }
}
