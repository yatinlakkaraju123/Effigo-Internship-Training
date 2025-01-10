
public class Tax {
    public static void main(String[] args) {
        double[] price = {1.99, 2.99, 3.99, 4.99};
        double[] afterTax = new double[4];
        // See instructions on Learn the Part (Workbook 6.9)
        System.out.println("The original prices are: ");
        for(int i=0;i<price.length;i++)
        {
            System.out.println(price[i]);
        }
        System.out.println("The prices after tax are: ");
        for(int i=0;i<price.length;i++)
        {
            afterTax[i] = price[i] + 0.13*price[i];
            System.out.println(afterTax[i]);
        }

    }
}
