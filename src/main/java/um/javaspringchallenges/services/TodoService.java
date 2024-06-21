package um.javaspringchallenges.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.repository.TodoRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo todos;

    public List<Todo> getAllTodos() throws NullPointerException {
        List<Todo> response = todos.findAll();
        if (!response.isEmpty()) return response;
        else throw new NullPointerException("No todos left for you! Enjoy your leisure!");
    }
}
