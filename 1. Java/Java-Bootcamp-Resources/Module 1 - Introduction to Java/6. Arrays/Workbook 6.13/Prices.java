public class Prices {
    public static void main(String[] args) {
        // The instructions for this workbook are on Learn the Part (workbook 6.13)
        double[][] arr = {
            {12.99,8.99,9.99,10.49,11.99},
            {0.99,1.99,2.49,1.49,2.99},
            {8.99,7.99,9.49,9.99,10.99},
        };
        for(int i=0;i<arr.length;i++)
        {   switch(i)
            {
                case 0: System.out.print("Baking: ");break;
                case 1: System.out.println("Beverage: "); break;
                case 2: System.out.println("Cereals:"); break;
            }
            for(int j=0;j<arr[0].length;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        
         
    }
}
