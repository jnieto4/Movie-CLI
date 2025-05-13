import java.io.*;
import java.util.*;

public class write_to_file {

    // This will output the data into a CSV file
    public static void data_to_csv(List<Movie> data, Args args) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(args.output_file + ".csv"))) {

            // Replaces list with the hidden gems in the sorted list if requested by the
            // user
            if (args.find_hidden_gems) {
                data = Filter.find_hidden_gems(data);
            }

            // Foramtting the output
            writer.println(
                    "Title,Year,Certificate, Runtime, Genre, IMDB Rating, Summary, Metascore, Director, Star 1, Star 2, Star 3, Star 4, Vote Count, Gross");
            for (Movie movie : data) {
                writer.printf("%s,%d,%s,%d,%s,%.1f,%s,%d,%s,%s,%s,%s,%s,%d,%d%n",
                        movie.title,
                        movie.year,
                        movie.certificate,
                        movie.run_time,
                        movie.genre,
                        movie.rating,
                        movie.summary,
                        movie.metascore,
                        movie.director,
                        movie.stars[0],
                        movie.stars[1],
                        movie.stars[2],
                        movie.stars[3],
                        movie.votes_count,
                        movie.gross);
            }

            if (args.genre_insights) {
                genre_insights_to_txt(data, args);
            }
            System.out.println("CSV data written to " + args.output_file);

        } catch (IOException e) {
            System.err.println("Failed to write to file (CSV): " + e.getMessage());
        }
    }

    // This will output the data into a JSON file
    public static void data_to_json(List<Movie> data, Args args) {

        // Replaces list with the hidden gems in the sorted list if requested by the
        // user
        if (args.find_hidden_gems) {
            data = Filter.find_hidden_gems(data);
        }

        StringBuilder json_builder = new StringBuilder();
        json_builder.append("[\n");

        for (int i = 0; i < data.size(); i++) {
            Movie movie = data.get(i);
            json_builder.append("  {\n");
            json_builder.append("    \"title\": \"" + movie.title.replace("\"", "") + "\",\n");
            json_builder.append("    \"year\": " + movie.year + ",\n");
            json_builder.append("    \"certificate\": \"" + movie.certificate + "\",\n");
            json_builder.append("    \"run_time\": " + movie.run_time + ",\n");
            json_builder.append("    \"genre\": \"" + movie.genre.replace("\"", "") + "\",\n");
            json_builder.append("    \"rating\": " + movie.rating + ",\n");
            json_builder.append("    \"summary\": \"" + movie.summary.replace("\"", "") + "\",\n");
            json_builder.append("    \"metascore\": " + movie.metascore + ",\n");
            json_builder.append("    \"director\": \"" + movie.director + "\",\n");

            // Convert stars array to JSON array
            json_builder.append("    \"stars\": [");
            for (int j = 0; j < movie.stars.length; j++) {
                json_builder.append("\"" + movie.stars[j] + "\"");
                if (j < movie.stars.length - 1) {
                    json_builder.append(", ");
                }
            }
            json_builder.append("],\n");

            json_builder.append("    \"votes_count\": " + movie.votes_count + ",\n");
            json_builder.append("    \"gross\": " + movie.gross + "\n");
            json_builder.append("  }");

            // Add comma between movie objects if not the last one
            if (i < data.size() - 1) {
                json_builder.append(",");
            }
            json_builder.append("\n");
        }

        json_builder.append("]");

        genre_insights_to_txt(data, args);

        // Write JSON to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args.output_file + ".json"))) {
            writer.write(json_builder.toString());
            System.out.println("JSON data written to " + args.output_file);
        } catch (IOException e) {
            System.err.println("Failed to write file (JSON): " + e.getMessage());
        }

    }

    // This will output the data into a TXT file
    public static void data_to_txt(List<Movie> data, Args args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(args.output_file))) {

            // Replaces list with the hidden gems in the sorted list if requested by the
            // user
            if (args.find_hidden_gems) {
                data = Filter.find_hidden_gems(data);
            }

            for (Movie m : data) {
                writer.println(m.toString());
            }
            if (args.genre_insights) {
                genre_insights_to_txt(data, args);
            }

            System.out.println("TXT data written to " + args.output_file);
        } catch (IOException e) {
            System.err.println("\"Failed to write to file (TXT): " + e.getMessage());
        }
    }

    // This will output the data to the console, this is default.
    public static void data_to_console(List<Movie> data, Args args) {

        // Replaces list with the hidden gems in the sorted list if requested by the
        // user
        if (args.find_hidden_gems) {
            data = Filter.find_hidden_gems(data);
        }

        for (Movie m : data) {
            System.out.println(m.toString()); // Uses Movie.toString()
        }

        // If the --genre-insights is an argument it will add the data to the end
        if (args.genre_insights) {
            System.out.println(Filter.get_genre_insights(data));
        }

    }

    // I decieded to output genre insights into a txt file due to the convenience
    // for the user
    // If it were appended to the end of the file it would be deficult to scroll to
    // the bottom if there
    // Are a lot of Movies listed.
    // This also keeps the data uniform through the entire file.
    // NOTE: I kept the terminal output with the genre insights at the bottom
    public static void genre_insights_to_txt(List<Movie> data, Args args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("genre_insights.txt"))) {
            writer.println(Filter.get_genre_insights(data));

            System.out.println("Exported genre_insights (TXT)");
        } catch (IOException e) {
            System.err.println("\"Failed to write genre insights to file (TXT): " + e.getMessage());
        }
    }
}
