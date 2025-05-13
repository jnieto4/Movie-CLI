# Movie-CLI
This program is a CLI to sort through movie data using various commands to narrow down searches and find data stored in a CSV file.

Commands
    java Main.java              (Required to run the program)
    --input filename.csv        (Required to put data into the program)
    --output-format             (TXT, CSV, or JSON - if not added it will default to the terminal output)
    --output-file               (Specify the name of the output file, if not added "filtered_movies" is default)
    --year-before               (Filter movies before specified year)
    --year-after                (Filter movies after specified year)
    --rating-above              (Filter movies above specified rating (0-10))
    --rating-below              (Filter movies below specified rating (0-10))
    --run-time-more             (Filter movies more than the specified runtime)
    --run-time-less             (Filter movies less than the specified runtime)
    --gross-max                 (Filter movies by gross profit up to a specified maximum)
    --gross-min                 (Filter movies by gross profit above a specified minimum)
    --votes-max                 (Filter movies by vote count up to a specified maximum)
    --votes-min                 (Filter movies by vote count above a specified minimum)
    --genre                     (Filter moviers based on specified genre **)
    --director                  (Filter moviers based on director)
    --star                      (Filter movie based on who stars in it)
    --top-10                    (Show the top 10 movies based on "highest-rated", "highest-grossing", or "most-popular")
    --genre-insights            (Will include information at the end of the output about the average genre statistics from the movies that were filtered) 
        | (This will be output to a txt file unless it is not exported to annother file type)
    --find-hidden-gems          (Will show 10 movies that are the highest rated and lowest vote coounts)


