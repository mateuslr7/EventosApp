package repository;

import model.Event;
import model.Categoria;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {
    private List<Event> eventos = new ArrayList<>();
    private final String FILE_NAME = "events.data";

    public List<Event> getEventos() {
        return eventos;
    }

    public void add(Event e) {
        eventos.add(e);
        save();
    }

    public void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Event e : eventos) {
                pw.println(String.join(";", e.getId(), e.getNome(),
                        e.getEndereco(), e.getCategoria().name(),
                        e.getHorario().toString(), e.getDescricao()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        eventos.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";", -1);
                if (p.length >= 6) {
                    Event e = new Event(p[1], p[2], Categoria.valueOf(p[3]),
                            LocalDateTime.parse(p[4]), p[5]);
                    eventos.add(e);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
