package um.javaspringchallenges.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.models.TodoStatus;
import um.javaspringchallenges.repository.TodoRepo;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoServiceTest {

    private static TodoService todoService;
    //Dependencies
    private static TodoRepo mockTodoRepo;
    //Test data
    private static List<Todo> testTodoList;


    @BeforeAll
    static void setUp() {
        mockTodoRepo = mock(TodoRepo.class);
        todoService = new TodoService(mockTodoRepo);
        testTodoList = new ArrayList<>() {{
            add(new Todo("1", "Test todo 1", TodoStatus.OPEN));
            add(new Todo("2", "Test todo 2", TodoStatus.OPEN));
            add(new Todo("3", "Test todo 3", TodoStatus.IN_PROGRESS));
            add(new Todo("4", "Test todo 4", TodoStatus.IN_PROGRESS));
            add(new Todo("5", "Test todo 5", TodoStatus.COMPLETED));
        }};
    }

    @Test
    void getAllTodos() throws NullPointerException {
        when(mockTodoRepo.findAll()).thenReturn(testTodoList);
        try {
            todoService.getAllTodos();
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
        verify(mockTodoRepo).findAll();
    }
}