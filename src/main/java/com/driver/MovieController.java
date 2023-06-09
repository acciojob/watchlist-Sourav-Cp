package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService = new MovieService();
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        movieService.addMovie(movie);
        return new ResponseEntity<>("New Movie Added", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        movieService.addDirector(director);
        return new ResponseEntity<>("New Director Added", HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director)
    {
        try {
            movieService.addMovieDirectorPair(movie, director);
            return new ResponseEntity<>("Movie-Director Pair Added", HttpStatus.OK);
           }
        catch (MovieNotPresentException ex)
        {
            return new ResponseEntity<>("Movie name not found",HttpStatus.NOT_FOUND);
        }
        catch (DirectorNotPresentException ex)
        {
            return new ResponseEntity<>("Director name not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name)
    {
        try {
           Movie movies = movieService.getMovieByName(name);
           return new ResponseEntity<>(movies,HttpStatus.OK);
        }
        catch (MovieNotPresentException ex)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name)
    {
        try {
            Director directors = movieService.getDirectorByName(name);
            return new ResponseEntity<>(directors,HttpStatus.OK);
        }
        catch (DirectorNotPresentException ex)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director)
    {
        List<String> movies = movieService.getAllMoviesByDirector(director);
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director)
    {
       movieService.deleteDirectorByName(director);
       return new ResponseEntity<>("Director removed from database",HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted",HttpStatus.OK);
    }

}
