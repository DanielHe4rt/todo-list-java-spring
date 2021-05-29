package dev.danielheart.vaicaralho.todolist.repositories;

import dev.danielheart.vaicaralho.todolist.models.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

}
