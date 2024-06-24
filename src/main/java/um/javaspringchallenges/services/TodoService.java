package um.javaspringchallenges.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.models.TodoStatus;
import um.javaspringchallenges.models.dto.TodoDTO;
import um.javaspringchallenges.repository.TodoRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todos;
    private final UtilService utilService;

    public List<Todo> getAllTodos() throws NullPointerException {
        List<Todo> response = todos.findAll();
        if (!response.isEmpty()) return response;
        else throw new NullPointerException("No todos left for you! Enjoy your leisure!");
    }

    public Todo createTodo(TodoDTO todo) throws NullPointerException {
        Todo todoToCreate = new Todo(utilService.generateId(), todo.description(), TodoStatus.OPEN);
        return todos.save(todoToCreate);
    }
}
