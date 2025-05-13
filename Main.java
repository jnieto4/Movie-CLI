import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Pass the arguments to the Args class to be parsed
        Args arguments = Args.parse_args(args);

        // Collect all the Movie data with the file given my the argument
        List<Movie> movies = Movies_Input.input_data(arguments.input_file);

        // Applies all filters that were asked for in the arguments
        List<Movie> filtered_movies = Filter.apply_filters(movies, arguments);

        // Output to file type specified by user, .txt, .csv, .json, terminal output
        // (default)
        switch (arguments.output_format.toLowerCase()) {
            case "json":
                write_to_file.data_to_json(filtered_movies, arguments);
                break;
            case "csv":
                write_to_file.data_to_csv(filtered_movies, arguments);
                break;
            case "txt":
                write_to_file.data_to_txt(filtered_movies, arguments);
                break;
            case "text":
            default:
                write_to_file.data_to_console(filtered_movies, arguments);
                break;
        }
    }
}
