import java.util.Arrays;

public class Store {

    private Movie[] movies;

    public Store() {
        this.movies = new Movie[10];
        
    }

    public Movie getMovie(int index) {
       
        Movie[] copy = Arrays.copyOf(movies,movies.length);
        return copy[index];
    }

    public void setMovie(int index, Movie movie) {
        
        this.movies[index] = movie;
    }


    public String toString() {
        String temp = "";
        for (Movie movie : movies) {
            temp += movie.toString() + "\n";
        }
        return temp;
    }

}
