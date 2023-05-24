package com.driver;

import org.apache.tomcat.util.digester.ArrayStack;

import java.util.*;

public class MovieRepository {
    private Map<String,Movie> movieData = new HashMap<>();
    private Map<String,Director> directorData = new HashMap<>();

    private Map<String, ArrayList<String>> movieDirectorPair = new HashMap<>();

    public void addMovie(Movie movie)
    {
        movieData.put(movie.getName(),movie);
    }

    public void addDirector(Director director)
    {
        directorData.put(director.getName(),director);
    }

    public Optional<Movie> getMovieByName(String movie)
    {
        if(movieData.containsKey(movie))
        {
            return Optional.of(movieData.get(movie));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirectorByName(String director)
    {
        if(directorData.containsKey(director))
        {
            return Optional.of(directorData.get(director));
        }
        return Optional.empty();
    }
    public void addMovieDirectorPair(String movie,String director)
    {
        ArrayList<String> movies = movieDirectorPair.getOrDefault(director,new ArrayList<>());
        movies.add(movie);
        movieDirectorPair.put(director,movies);
    }

    public List<String> getAllMoviesByDirector(String director)
    {
        return movieDirectorPair.getOrDefault(director,new ArrayList<>());
    }
    public List<String> getAllStudents()
    {
        return new ArrayList<>(movieData.keySet());
    }

    public void deleteDirector(String director)
    {
        directorData.remove(director);
        movieDirectorPair.remove((director));
    }
    public void deleteMovie(String movie)
    {
        movieData.remove(movie);
    }

    public List<String> getAllDirectors()
    {
        return new ArrayList<>(directorData.keySet());
    }

}
