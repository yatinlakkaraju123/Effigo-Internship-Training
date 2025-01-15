package clinic.programming.training;
import org.apache.commons.lang3.StringUtils;
public class Application {
    
    public Application() {
        System.out.println ("Inside Application");
    }
    public int countWords(String words)
    {
        String[] seperateWords = StringUtils.split(words,' ');
        return (seperateWords==null) ? 0 : seperateWords.length;
    }

    // method main(): ALWAYS the APPLICATION entry point
    public static void main (String[] args) {
    	System.out.println ("Starting Application");
        
	    Application app = new Application();
        int count = app.countWords("I have four words");
        System.out.println("count of words:"+count);
    }
}