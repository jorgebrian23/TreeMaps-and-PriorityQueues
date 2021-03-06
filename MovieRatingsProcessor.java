/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;

public class MovieRatingsProcessor {

    public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {

        List<String> movies = new ArrayList<>();

        if (movieRatings == null || movieRatings.isEmpty()) {
            return movies;
        }

        movies = new ArrayList<>(movieRatings.keySet());

        return movies;
    }

    public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

        List<String> movies = new ArrayList<>();

        if (movieRatings == null || movieRatings.isEmpty() || rating < 0) {
            return movies;
        }

        int movieRating;
        for (String title : movieRatings.keySet()) {
            movieRating = movieRatings.get(title).peek();
            if (movieRating > rating) {
                movies.add(title);
            }
        }

        return movies;
    }

    public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

        TreeMap<String, Integer> moviesRemovedRatings = new TreeMap<>();

        if (movieRatings == null || movieRatings.isEmpty()) {
            return moviesRemovedRatings;
        }
        
        Iterator<Map.Entry<String, PriorityQueue<Integer>>> iterator = movieRatings.entrySet().iterator();
        Map.Entry<String, PriorityQueue<Integer>> entry;
        
        String movieName;
        PriorityQueue<Integer> ratings;
        int ratingsRemoved;
        List<Integer> toRemove;
        
        while (iterator.hasNext()) {
            entry = iterator.next();
            movieName = entry.getKey();
            ratings = entry.getValue();
            
            toRemove = new ArrayList<>();
            for (int movieRating : ratings) {
                if (movieRating < rating) {
                    toRemove.add(movieRating);
                }
            }

            ratings.removeAll(toRemove);
            ratingsRemoved = toRemove.size();

            if (ratings.size() == 0) {
                iterator.remove();
            }

            if (ratingsRemoved > 0) {
                moviesRemovedRatings.put(movieName, ratingsRemoved);
            }
        }

        return moviesRemovedRatings;
    }
}