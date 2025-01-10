public class RandomNumbers {
    public static void main(String[] args) {
        
        // The instructions for this workbook are on Learn the Part (Workbook 6.14)
        int[][] array = {
            {48, 56, 56, 76, 0, 81, 51, 81, 99, 70},
            {38, 52, 73, 6, 10, 56, 1, 71, 47, 9},
            {85, 35, 47, 98, 91, 25, 69, 52, 2, 93}
        };
        //print2DArray(array);
        int[][] arr = new int[100][20];
        for(int i=0;i<arr.length;i++)
        {
            for(int j=0;j<arr[0].length;j++)
            {
                arr[i][j] = randomNumber();
            }
        }
        print2DArray(arr);
    }
    public static int randomNumber() {
        double randomNumber = Math.random()*100;
        return (int)randomNumber;
    }
    /**
 * Function name: print2DArray
 * @param array ( int[][] )
 *
 * Inside the function:
 *  1. Nested loop:
 *      - Inner Loop: System.out.print(array[i][j] + " ");
 *      - After the Inner Loop Completes: System.out.print("\n");
 */
public static void print2DArray(int[][] array)
{
    for(int i=0;i<array.length;i++)
    {
        for(int j=0;j<array[0].length;j++)
        {
            System.out.print(array[i][j] + " ");
        }
        System.out.print("\n");
    }
}

}
