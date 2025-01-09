public class WeatherNetwork {
    public static void main(String[] args) {

        //See Learn the Part for the complete instructions (link in resources folder of Udemy video).  

        
        int temp = -12;  

        String forecast = "";
        if(temp<=-1)
        {
            System.out.println("The forecast is FREEZING! Stay home!");
        }
        else if(temp<=10)
        {
            System.out.println("The forecast is Chilly. Wear a coat!");
        }
        else
        {
            System.out.println("It's warm. Go outside!");
        }
        //IF - ELSE IF - ELSE STATEMENTS HERE!
        
        
        System.out.println(forecast);
    }
}
