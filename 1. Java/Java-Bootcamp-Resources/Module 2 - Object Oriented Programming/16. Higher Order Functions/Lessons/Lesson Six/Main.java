import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        
        // Creating Stream from Datasource: Array
        String[] greetings = new String[]{"Hello!", "Hola!", "Bonjour!", "Hallo!"};
        Arrays.stream(greetings).forEach((greeting)->{
            System.out.println(greeting);
        });

        // Creating Stream from Datasource: File
        Path path = Paths.get("chorus.txt");
        try {
            Files.lines(path).forEach(file->System.out.println(file));;
        } catch (Exception e) {
            System.out.println("file not found");
        }
       

    }
}