import java.io.*;
import java.util.*;

public class Movies_Input {

    // Method to add all movies and corrisponding data from the file to a List
    public static List<Movie> input_data(String file_path) {
        List<Movie> all_movies = new ArrayList<>();

        Movie movie;
        List<String[]> data = read_file(file_path);
        for (String[] m : data) {
            movie = new Movie(m);
            all_movies.add(movie);
        }
        return all_movies;
    }

    // Method to parse through the file and gram the data fior each movie
    public static List<String[]> read_file(String file_path) {
        // List that contains the strings of each row
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file_path))) {
            String line;
            boolean is_header = true;
            while ((line = br.readLine()) != null) {
                if (is_header) { // skip the first line in the CSV file, this line is the column headers
                    is_header = false;
                    continue;
                }
                // Split by any other characters other than the movie data
                String[] row_values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                data.add(row_values);
            }
        } catch (IOException e) {
            System.out.println("Failed to read file:" + e.getMessage()); // contingency if it cannot read file.
        }
        return data;
    }
}
