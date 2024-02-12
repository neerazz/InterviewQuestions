## Welcome

If you're reading this, that means we liked you enough to ask you to another round of interviewing. Congratulations!

Now let's get down to programming...

You will be graded by the following criteria:
- Code Readability:  How readable is your code
- Code Structure: How well did you structure your code
- Code Extensability: Is it easy for developers to extend your code, maintain it and add new features
- Solution Accuracy: Your solution should be accurate, not too many false positives and false negatives
- Solution Efficiency: Performance and efficiency of your solution [O(N) - Time Complexity & Space Complexity]

## The Match Game

You receive movie data feeds from many different providers, ex: Google Play, VUDU, Amazon Instant.
These include information about the availability of a given movie or TV show. 
They also usually include some metadata from  the movie, such as the title, director, and release year, alongside availability information like price and the fulfilment URL.

How do you take metadata and availability information found in these provider feeds and match them to an internal database of movies? 
Different feeds have varying types of metadata and there are inconsistencies between sources of data.

In this exercise, you will be given a database of movies and a provider feed. 
You must implement your own matching algorithm to match availability information to the internal database of movies.

## Getting Started 

You will find some files given to you to start off. Feel free to bring in any additional libraries that you feel will help
you accomplish this task.

## Data Files

**movies.csv** : a comma-separated file of around 200,000 movies, with the following schema:

| id | title | year |
| ------------ | ----- | ---- |
| 1            | Finding Nemo | 2006 |

**actors_and_directors.csv**: a comma-separated file of actors and directors associated with each movie. It has the following schema:

| movie_id | name | role |
| ------------ | ---- | -------- |
| 1            | Leonardo DiCaprio | cast |
| 2            | Martin Scorsese | director|

**xbox.csv**: a comma-separated file of the official provider feed for Xbox. It has the following schema:

| MediaId | Title | OriginalReleaseDate | MediaType | Actors | Director | XboxLiveURL |
| ------- | ----- | ------------------- | --------- | ------ | -------- | ----------- |
| 531b964f-0cb9-4968-9b77-e547f2435225| Furious 7 | 4/13/2015 | Movie | Vin Diesel, Paul Walker, Jason Statham | James Wan | video.xbox.com  

## Objective
Make the failing test pass!

# Disclaimer

This skills assessment is part of your interview process and is not working time. This is a generic, open-ended assignment 
created for the purpose of ascertaining your qualification and suitability for the job opening. The results of your skills assessment
will not be used by the Company nor will we obtain useable work product from you. The Company does not extract any direct benefit from 
your participation in the assessment.
## License

At CodeScreen, we strongly value the integrity and privacy of our assessments. As a result, this repository is under exclusive copyright, which means you **do not** have permission to share your solution to this test publicly (i.e., inside a public GitHub/GitLab repo, on Reddit, etc.). <br>

## Submitting your solution

Please push your changes to the `main branch` of this repository. You can push one or more commits. <br>

Once you are finished with the task, please click the `Submit Solution` link on.

## Implementation Details

- **MatcherImpl Class**: Serves as the main entry point for matching operations. It initializes the mapping structures
  for movies by different attributes (ID, title, year, actor, director) and utilizes `MovieMatcher` to find the best
  match for external movie entries.
- **MovieMatcher Class**: Coordinates the matching process by applying a sequence of filter strategies to external movie
  data. Each filter is responsible for narrowing down the list of potential matches based on a specific criterion.
- **FilterStrategy Interface**: Defines a common interface for all filters, requiring the implementation of a `filter`
  method that takes an `ExternalMovie` object and returns a set of movie IDs that match the filter's criterion.
- **Concrete Filters**: `TitleFilter`, `YearFilter`, `ActorNameFilter`, `DirectorFilter`. Each implements
  the `FilterStrategy` interface, providing logic to filter movies based on title, release year, actors involved, and
  director, respectively.

## Runtime Complexity

- The initialization phase (processing movies and actors/directors) operates in linear time relative to the number of
  entries, O(n), where n is the total number of movies or people.
- Each filter's complexity depends on the size of the dataset and the efficiency of map lookups, which are generally O(
  1) for a hash map.
- The overall complexity of the matching process is primarily influenced by the number of external movie entries being
  processed and the efficiency of the filtering logic, which is also linear in nature, O(m*f), where m is the number of
  movies to match and f is the number of filters applied.