import java.util.Scanner;

public class HighScore {
    public static void main(String[] args) {
        
        int highScore = 0;
        Scanner sc = new Scanner(System.in);
        // Instructions for this workbook are on Learn the Part (Workbook 6.5).
        int[] nums = new int[10];
        System.out.println("enter numbers:");
        for(int i=0;i<nums.length;i++)
        {
            System.out.println("enter number "+(i+1)+":");
            nums[i] = sc.nextInt();
        }
        System.out.print("Here are the scores: <score elements>");
        for(int i=0;i<10;i++)
        {
            System.out.println(nums[i]);
            highScore = Math.max(highScore,nums[i]);
        }
        System.out.println("\n\nThe highest score is: " + highScore + ". Give that man a cookie!");
        sc.close();
    }
      /**
     *  Function name - randomNumber
     *  @return (int)
     *
     *  Inside the function
     *    - returns a random number between 0 to 49999
     *
     */
    public static int randomNumber()
    {
        return (int)(Math.random()*55554)+1;
    }    

}
