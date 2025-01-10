public class Grocer {
    public static void main(String[] args) {
        
        // Instructions for this workbook are on Learn the Part (Workbook 6.3).
        String[] aisles = {"apples", "bananas", "candy", "chocolate", "coffee", "tea"};
        System.out.println("\nDo you sell coffee?");
        for(int i=0;i<aisles.length;i++)
        {
            if(aisles[i]=="coffee")
            {
                System.out.println("\nWe have that in aisle: "+i);
            }
        }
        
    
    }
}
