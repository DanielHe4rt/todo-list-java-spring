package dev.danielheart.vaicaralho.todolist.controllers;

import dev.danielheart.vaicaralho.todolist.models.Todo;
import dev.danielheart.vaicaralho.todolist.services.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public Page<Todo> getTodos(@RequestParam(value = "name", defaultValue = "World") String name) {
        return this.service.paginate();
    }

    @PostMapping("/todos")
    public Todo postTodos(@RequestBody Todo model) {
        return this.service.save(model);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodo(@PathVariable Long id) {
        try {
            return new ResponseEntity<Object>(this.service.find(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("mano busca negócio certo", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public String putTodos(@RequestBody Todo model, @PathVariable Long id) {
        this.service.update(id, model);
        return "eae";
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<String> deleteTodos(@PathVariable Long id) {
        try {
            this.service.delete(id);
            return new ResponseEntity<String>("vai caralho", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<String>("fudeu", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
