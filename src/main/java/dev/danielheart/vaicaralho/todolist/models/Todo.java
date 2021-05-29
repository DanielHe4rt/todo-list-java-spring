package dev.danielheart.vaicaralho.todolist.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

//    private Instant deadline;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Instant getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(Instant deadline) {
//        this.deadline = deadline;
//    }
}
