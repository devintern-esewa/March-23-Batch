import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import me.Book;
import me.BookController;
import me.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    Book record1 = new Book(1L, "Atomic", "How to be don", 6);
    Book record2 = new Book(2L, "Thinking", "How to be god don", 9);
    Book record3 = new Book(3L, "Algorithm", "How to learn and don", 2);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllRecords_success() throws Exception {
        List<Book> records = new ArrayList<>(Arrays.asList(record1, record2, record3));
       when(bookRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name",is("Algorithm")));
    }

    @Test
    public void getBookById_success() throws Exception{
        when(bookRepository.findById(record1.getBookId())).thenReturn(java.util.Optional.of(record1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/book/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",is("Atomic")));

    }

    @Test
    public void createRecord_success() throws Exception{
        Book record = Book.builder()
                .bookId(4L)
                .name("Introduction to Don")
                .summary("I am don")
                .rating(10)
                .build();

        when(bookRepository.save(record)).thenReturn(record);

        String content = objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("Introduction to Don")));
    }

    @Test
    public void updateBookRecord_success() throws Exception{
        Book updatedBook = Book.builder()
                .bookId(1L)
                .name("Updated book name")
                .summary("updated summary")
                .rating(10)
                .build();

        when(bookRepository.findById(record1.getBookId())).thenReturn(java.util.Optional.ofNullable(record1));
        when(bookRepository.save(updatedBook)).thenReturn(updatedBook);

        String updatedContent = objectWriter.writeValueAsString(updatedBook);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("Updated book name")));

    }

    @Test
    public void deleteBookById_success() throws Exception{
        when(bookRepository.findById(record1.getBookId())).thenReturn(Optional.of(record1));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/book/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }



}
