public class Args {

    // All possible allowed arguments
    public String input_file;
    public String output_file;
    public String output_format;
    public Integer year_before;
    public Integer year_after;
    public String genre;
    public Double rating_above;
    public Double rating_below;
    public String director;
    public String star;
    public Integer run_time_more;
    public Integer run_time_less;
    public Long gross_min;
    public Long gross_max;
    public Integer votes_min;
    public Integer votes_max;
    public String top_10;
    public boolean genre_insights;
    public boolean find_hidden_gems;

    // Default constructor (output_file is set to filtered_movies as to be default)
    private Args() {
        input_file = null;
        output_file = "filtered_movies";
        output_format = "";
        year_before = null;
        year_after = null;
        genre = null;
        rating_above = null;
        rating_below = null;
        director = null;
        star = null;
        run_time_more = null;
        run_time_less = null;
        gross_min = null;
        gross_max = null;
        votes_min = null;
        votes_max = null;
        top_10 = null;
        genre_insights = false;
        find_hidden_gems = false;
    }

    public static Args parse_args(String[] arguments) {

        // Using a switch/case method to extract all arguments thatn were inputed by the
        // user
        Args args = new Args();
        for (int i = 0; i < arguments.length; i++) {
            switch (arguments[i]) {
                case "--input" -> {
                    args.input_file = arguments[++i];
                }
                case "--output-format" -> {
                    args.output_format = arguments[++i];
                }
                case "--output-file" -> {
                    args.output_file = arguments[++i];
                }
                case "--year-after" -> {
                    args.year_after = Integer.valueOf(arguments[++i]);
                }
                case "--year-before" -> {
                    args.year_before = Integer.valueOf(arguments[++i]);
                }
                case "--genre" -> {
                    args.genre = arguments[++i];
                }
                case "--rating-above" -> {
                    args.rating_above = Double.valueOf(arguments[++i]);
                }
                case "--rating-below" -> {
                    args.rating_below = Double.valueOf(arguments[++i]);
                }
                case "--director" -> {
                    args.director = arguments[++i];
                }
                case "--actor" -> {
                    args.star = arguments[++i];
                }
                case "--runtime-more-than" -> {
                    args.run_time_more = Integer.valueOf(arguments[++i]);
                }
                case "--runtime-less-than" -> {
                    args.run_time_less = Integer.valueOf(arguments[++i]);
                }
                case "--gross-min" -> {
                    args.gross_min = Long.valueOf(arguments[++i]);
                }
                case "--gross-max" -> {
                    args.gross_max = Long.valueOf(arguments[++i]);
                }
                case "--votes-min" -> {
                    args.votes_min = Integer.valueOf(arguments[++i]);
                }
                case "--votes-max" -> {
                    args.votes_max = Integer.valueOf(arguments[++i]);
                }
                case "--top-10" -> {
                    args.top_10 = arguments[++i];
                }
                case "--genre-insights" -> {
                    args.genre_insights = true;
                }
                case "--find-hidden-gems" -> {
                    args.find_hidden_gems = true;
                }

            }
        }
        return args;
    }
}