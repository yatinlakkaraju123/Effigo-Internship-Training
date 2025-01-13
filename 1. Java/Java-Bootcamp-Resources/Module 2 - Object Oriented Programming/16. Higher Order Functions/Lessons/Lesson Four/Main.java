import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Double> prices = Arrays.asList(341.67, 209.32, 88.41, 269.99, 68.49, 499.99, 28.12, 354.38);
        prices.stream().filter(price ->{
            return price>100;}).map(price-> {
                return price -20;
            }).sorted((left,right)->left.compareTo(right)).map(price->{
                return "$"+price;
            }).forEach(price->{
                System.out.println(price);
            });;
    }
}