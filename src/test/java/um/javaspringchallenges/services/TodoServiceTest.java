package um.javaspringchallenges.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.models.TodoStatus;
import um.javaspringchallenges.models.dto.TodoDTO;
import um.javaspringchallenges.repository.TodoRepo;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoServiceTest {

    private static TodoService todoService;
    //Dependencies
    private static TodoRepo mockTodoRepo;
    private static UtilService mockUtilService;
    //Test data
    private static List<Todo> testTodoList;


    @BeforeAll
    static void setUp() {
        mockTodoRepo = mock(TodoRepo.class);
        mockUtilService = mock(UtilService.class);
        todoService = new TodoService(mockTodoRepo, mockUtilService);
        testTodoList = new ArrayList<>() {{
            add(new Todo("1", "Test todo 1", TodoStatus.OPEN));
            add(new Todo("2", "Test todo 2", TodoStatus.OPEN));
            add(new Todo("3", "Test todo 3", TodoStatus.IN_PROGRESS));
            add(new Todo("4", "Test todo 4", TodoStatus.IN_PROGRESS));
            add(new Todo("5", "Test todo 5", TodoStatus.COMPLETED));
        }};
    }

    @Test
    void getAllTodos_returnsListOfOpenTodos() throws NullPointerException {
        when(mockTodoRepo.findAll()).thenReturn(testTodoList);
        try {
            todoService.getAllTodos();
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
        verify(mockTodoRepo).findAll();
    }

    @Test
    void createTodo_createsANewTodo_withRandomIDAndStatusOpen() throws NullPointerException {
        TodoDTO givenTodo = new TodoDTO("I am the first test todo", TodoStatus.OPEN);
        Todo expected = new Todo("123", givenTodo.description(), givenTodo.status());
        when(mockUtilService.generateId()).thenReturn("123");
        when(mockTodoRepo.save(expected)).thenReturn(expected);
        todoService.createTodo(givenTodo);
        verify(mockTodoRepo).save(expected);
    }
}