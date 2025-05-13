public class Movie {

    // Variable declaration
    public String title;
    public int year;
    public String certificate;
    public int run_time;
    public String genre;
    public double rating;
    public String summary;
    public int metascore;
    public String director;
    public String[] stars;
    public int votes_count;
    public Long gross;

    public Movie(String[] values) {

        // Using the values string to put all the variables into the Object.
        // The save_parse for the long and int values were implemented since some of the
        // values
        // can be null, this ensures that if they are null they will just become 0
        title = values[0];
        year = safe_parse_int(values[1]);
        certificate = values[2];
        run_time = safe_parse_int(values[3].replace(" min", "").trim());
        genre = values[4];
        rating = Double.parseDouble(values[5]);
        summary = values[6];
        metascore = safe_parse_int(values[7]);
        director = values[8];
        stars = new String[] { values[9], values[10], values[11], values[12] };
        votes_count = safe_parse_int(values[13]);
        gross = safe_parse_long(values[14].replace(",", "").replace("\"", ""));

    }

    // default constructor
    public Movie() {
        title = "";
        year = 0;
        certificate = "";
        run_time = 0;
        genre = "";
        rating = 0.0;
        summary = "";
        metascore = 0;
        director = "";
        stars = new String[4];
        votes_count = 0;
        gross = 0L;
    }

    // This is to catch errors with blank spaces that are intended to be integers
    public static int safe_parse_int(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return 0; // Default value if it's blank or invalid
        }
    }

    public static Long safe_parse_long(String value) {
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException | NullPointerException e) {
            return 0L; // Default value if it's blank or invalid
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%d)\n  Rating: %.1f | Metascore %d | Certificate: %s | Runtime: %d min | Genre: %s\n  Director: %s\n  Stars: %s\n  Votes: %,d | Gross: $%,d\n  Overview: %s\n",
                title,
                year,
                rating,
                metascore,
                certificate,
                run_time,
                genre,
                director,
                String.join(", ", stars),
                votes_count,
                gross,
                summary);
    }
}
