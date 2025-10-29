package mate.academy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing.");
        movieService.add(fastAndFurious);
        System.out.println("Added movie: " + movieService.get(fastAndFurious.getId()));

        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(100);
        blueHall.setDescription("Blue Hall with comfy seats");
        cinemaHallService.add(blueHall);
        System.out.println("Added cinema hall: " + cinemaHallService.get(blueHall.getId()));

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(fastAndFurious);
        movieSession.setCinemaHall(blueHall);
        LocalDateTime sessionTime = LocalDate.now().atTime(19, 30);
        movieSession.setShowTime(sessionTime);
        movieSessionService.add(movieSession);
        System.out.println("Added movie session: " + movieSessionService.get(movieSession.getId()));

        LocalDate today = LocalDate.now();
        List<MovieSession> availableSessions =
                movieSessionService.findAvailableSessions(fastAndFurious.getId(), today);
        System.out.println("Available sessions for '" + fastAndFurious.getTitle()
                + "' on " + today + ":");
        availableSessions.forEach(System.out::println);
    }
}
