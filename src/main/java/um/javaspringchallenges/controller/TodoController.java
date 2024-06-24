package um.javaspringchallenges.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.models.dto.TodoDTO;
import um.javaspringchallenges.services.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() throws NullPointerException {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoDTO todo) throws NullPointerException {
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }
}
