import java.util.*;
import java.util.stream.Collectors;

public class Filter {

    public static List<Movie> apply_filters(List<Movie> movies, Args args) {

        // Java streams are being used here to parse through the data because it is
        // cleaner and more concise
        // The 'null' statements are being uitlized if the user does not add the argument
        // for the specified values
        List<Movie> filtered_movies = movies.stream()
                .filter(m -> args.year_after == null || m.year > args.year_after)
                .filter(m -> args.year_before == null || m.year < args.year_before)
                .filter(m -> args.genre == null || m.genre.toLowerCase().contains(args.genre.toLowerCase()))
                .filter(m -> args.rating_above == null || m.rating > args.rating_above)
                .filter(m -> args.rating_below == null || m.rating < args.rating_below)
                .filter(m -> args.director == null || m.director.toLowerCase().contains(args.director.toLowerCase()))
                .filter(m -> args.star == null || Arrays.stream(m.stars)
                        .anyMatch(star -> star.toLowerCase().contains(args.star.toLowerCase()))) // Checks the array of
                                                                                                 // stars to see if it
                                                                                                 // contains the
                                                                                                 // argument
                .filter(m -> args.run_time_more == null || m.run_time > args.run_time_more)
                .filter(m -> args.run_time_less == null || m.run_time < args.run_time_less)
                .filter(m -> args.gross_min == null || m.gross >= args.gross_min)
                .filter(m -> args.gross_max == null || m.gross <= args.gross_max)
                .sorted((a, b) -> Double.compare(b.rating, a.rating))
                .collect(Collectors.toList());

        // If the user requests --top-10 it will provide the top 10 based on the
        // filtered data
        if (args.top_10 != null) {
            List<Movie> top_10_movies = get_top_10_movies(filtered_movies, args.top_10);
            return top_10_movies;
        }

        return filtered_movies;
    }

    public static List<Movie> get_top_10_movies(List<Movie> movies, String top_10_criteria) {

        // Switch/case to sort through the data using the user requested criteria
        // again streams are being used here for more concise code
        switch (top_10_criteria.toLowerCase()) {
            case "highest-rated":
                return movies.stream()
                        .sorted((a, b) -> Double.compare(b.rating, a.rating)) // Sort by rating, descending
                        .limit(10)
                        .collect(Collectors.toList());

            case "most-popular":
                return movies.stream()
                        .sorted((a, b) -> Long.compare(b.votes_count, a.votes_count)) // Sort by votes, descending
                        .limit(10)
                        .collect(Collectors.toList());

            case "highest-grossing":
                return movies.stream()
                        .sorted((a, b) -> Long.compare(b.gross, a.gross)) // Sort by gross revenue, descending
                        .limit(10)
                        .collect(Collectors.toList());

            default:
                System.out.println("Invalid criteria: " + top_10_criteria);
                return Collections.emptyList(); // Return an empty list if the criteria is invalid
        }
    }

    // This method will return the genre insights for the movies that have been
    // listed through the filter
    // This includes average rating, total gross rev, and average runtime
    public static String get_genre_insights(List<Movie> movies) {

        StringBuilder genre_insights_string = new StringBuilder();
        List<String> processed_genres = new ArrayList<>(); // List to track processed genres

        for (Movie movie : movies) {
            // Split genres and iterate over each genre in the movie
            for (String genre : movie.genre.split(",")) {
                String trimmed_genre = genre.trim().replace("\"", ""); // Remove extra spaces and quotes

                // Check if this genre has already been processed
                if (processed_genres.contains(trimmed_genre)) {
                    continue; // Skip if this genre has already been processed
                }

                // Add genre to processed list
                processed_genres.add(trimmed_genre);

                // Filter movies by the genre
                List<Movie> genre_movies = movies.stream()
                        .filter(m -> Arrays.stream(m.genre.split(","))
                                .map(g -> g.trim().replace("\"", "")) // Clean each genre output
                                .anyMatch(g -> g.equalsIgnoreCase(trimmed_genre)))
                        .collect(Collectors.toList());

                // Calculate the average rating, total gross, and average runtime for this genre
                double avg_rating = genre_movies.stream().mapToDouble(m -> m.rating).average().orElse(0.0);
                long total_gross = genre_movies.stream().mapToLong(m -> m.gross).sum();
                double avg_runtime = genre_movies.stream().mapToInt(m -> m.run_time).average().orElse(0.0);

                // Append the genre insights to the StringBuilder
                genre_insights_string.append(String.format("Genre: %s%n", trimmed_genre));
                genre_insights_string.append(String.format("  Average Rating: %.2f%n", avg_rating));
                genre_insights_string.append(String.format("  Total Gross: $%,d%n", total_gross));
                genre_insights_string.append(String.format("  Average Runtime: %.2f minutes%n", avg_runtime));
                genre_insights_string.append("\n");
            }
        }

        // Return the complete genre insights as a string
        return genre_insights_string.toString();
    }

    public static List<Movie> find_hidden_gems(List<Movie> movies) {

        // Sort by rating descending, then by votes ascending
        List<Movie> hidden_gems = movies.stream()
                .sorted(Comparator.comparingInt((Movie m) -> m.votes_count)
                        .thenComparing(Comparator.comparingDouble((Movie m) -> m.rating).reversed()))
                .limit(10)
                .collect(Collectors.toList());

        return hidden_gems;
    }

}
