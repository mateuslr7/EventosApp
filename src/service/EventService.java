package service;

import model.Event;
import model.User;
import repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public void addEvent(Event e) {
        repo.add(e);
    }

    public List<Event> getEventosOrdenados() {
        return repo.getEventos().stream()
                .sorted((a, b) -> a.getHorario().compareTo(b.getHorario()))
                .collect(Collectors.toList());
    }

    public List<Event> getEventosAtuais() {
        return repo.getEventos().stream()
                .filter(Event::estaAcontecendoAgora)
                .collect(Collectors.toList());
    }

    public List<Event> getEventosPassados() {
        return repo.getEventos().stream()
                .filter(e -> e.getHorario().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    public void confirmarPresenca(Event e, User u) {
        e.confirmarPresenca(u);
    }

    public void cancelarPresenca(Event e, User u) {
        e.cancelarPresenca(u);
    }
}
