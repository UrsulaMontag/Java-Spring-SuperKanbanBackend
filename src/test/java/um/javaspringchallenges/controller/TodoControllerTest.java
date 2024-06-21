package um.javaspringchallenges.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import um.javaspringchallenges.models.Todo;
import um.javaspringchallenges.models.TodoStatus;
import um.javaspringchallenges.repository.TodoRepo;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepo todoRepo;

    @Test
    void getAllTodos() throws NullPointerException {
        todoRepo.saveAll(List.of(
                (new Todo("1", "Test todo 1", TodoStatus.OPEN)),
                (new Todo("2", "Test todo 2", TodoStatus.IN_PROGRESS)),
                (new Todo("3", "Test todo 3", TodoStatus.IN_PROGRESS)),
                (new Todo("4", "Test todo 4", TodoStatus.COMPLETED))
        ));
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                                                        [
                                                          {
                                                          "id": "1",
                                                          "description": "Test todo 1",
                                                          "status": "OPEN"
                                                          },
                                                          {
                                                          "id": "2",
                                                          "description": "Test todo 2",
                                                          "status": "IN_PROGRESS"
                                                          },
                                                          {
                                                          "id": "3",
                                                          "description": "Test todo 3",
                                                          "status": "IN_PROGRESS"
                                                          },
                                                          {
                                                          "id": "4",
                                                          "description": "Test todo 4",
                                                          "status": "COMPLETED"
                                                          }
                                                        ]
                            """));
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}