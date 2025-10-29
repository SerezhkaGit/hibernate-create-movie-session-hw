package mate.academy.service;

import mate.academy.model.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionService {
    public MovieSession add(MovieSession movieSession);

    public Optional<MovieSession> get(Long id);

    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
