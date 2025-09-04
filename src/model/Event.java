package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event {
    private String id;
    private String nome;
    private String endereco;
    private Categoria categoria;
    private LocalDateTime horario;
    private String descricao;
    private Set<String> participantes = new HashSet<>();

    public Event(String nome, String endereco, Categoria categoria, LocalDateTime horario, String descricao) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public Categoria getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public Set<String> getParticipantes() { return participantes; }

    public void confirmarPresenca(User user) {
        participantes.add(user.getId());
    }

    public void cancelarPresenca(User user) {
        participantes.remove(user.getId());
    }

    public boolean estaAcontecendoAgora() {
        LocalDateTime now = LocalDateTime.now();
        return !horario.isAfter(now) && horario.plusHours(2).isAfter(now);
    }

    @Override
    public String toString() {
        return "[" + categoria + "] " + nome + " - " + horario +
               "\nLocal: " + endereco + "\n" + descricao;
    }
}
