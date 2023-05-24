package com.driver;

import java.util.List;
import java.util.Optional;

public class MovieService {
    private MovieRepository movieRepository = new MovieRepository();

    public void addMovie(Movie movie)
    {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director)
    {
        movieRepository.addDirector(director);
    }

    public void addMovieDirectorPair(String movie,String director) throws MovieNotPresentException,DirectorNotPresentException
    {
        Optional<Movie> optionalMovie = movieRepository.getMovieByName(movie);
        Optional<Director> optionalDirector = movieRepository.getDirectorByName(director);

        if(optionalMovie.isEmpty())
        {
            throw new MovieNotPresentException("Movie of this name is not present");
        }
        if(optionalDirector.isEmpty())
        {
            throw new DirectorNotPresentException("Director of this name is not present");
        }

        Director obj = optionalDirector.get();
        obj.setNumberOfMovies(obj.getNumberOfMovies()+1);
        movieRepository.addDirector(obj);

        movieRepository.addMovieDirectorPair(movie,director);
    }

    public Movie getMovieByName(String name) throws MovieNotPresentException
    {
        Optional<Movie> optionalMovie = movieRepository.getMovieByName(name);
        if(optionalMovie.isPresent()) return optionalMovie.get();
        else throw new MovieNotPresentException("Movie of this name is not present");
    }

    public Director getDirectorByName(String name) throws DirectorNotPresentException
    {
        Optional<Director> optionalDirector = movieRepository.getDirectorByName(name);
        if(optionalDirector.isPresent()) return optionalDirector.get();
        else throw new DirectorNotPresentException("Director of this name is not present");
    }
    public List<String> getAllMoviesByDirector(String director)
    {
        return movieRepository.getAllMoviesByDirector(director);
    }

    public List<String> getAllStudents()
    {
        return movieRepository.getAllStudents();
    }
    public void deleteDirectorByName(String director)
    {
        List<String> movies = getAllMoviesByDirector(director);
        movieRepository.deleteDirector(director);

        for(String movie : movies)
        {
            movieRepository.deleteMovie(movie);
        }
    }
    public void deleteAllDirectors()
    {
        List<String> director = movieRepository.getAllDirectors();

        for(String directors : director)
        {
            deleteDirectorByName(directors);
        }
    }
}
