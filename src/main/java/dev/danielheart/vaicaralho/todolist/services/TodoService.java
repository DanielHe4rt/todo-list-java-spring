package dev.danielheart.vaicaralho.todolist.services;

import dev.danielheart.vaicaralho.todolist.models.Todo;
import dev.danielheart.vaicaralho.todolist.repositories.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Page<Todo> paginate() {
        return this.repository.findAll(PageRequest.of(0, 5));
    }

    public Todo save(Todo model) {
        this.repository.save(model);

        return model;
    }

    public int count() {
        return (int) this.repository.count();
    }

    public void update(Long id, Todo model) {
        Optional<Todo> query = this.repository.findById(id);
        query.orElseThrow();

        model.setId(id);
        this.repository.save(model);
    }

    public Todo find(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        Optional<Todo> query = this.repository.findById(id);
        query.orElseThrow();
        this.repository.deleteById(id);
    }
}
