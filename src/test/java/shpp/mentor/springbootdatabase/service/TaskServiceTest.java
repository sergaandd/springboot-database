package shpp.mentor.springbootdatabase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.models.ApiErrorDTO;
import shpp.mentor.springbootdatabase.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    TaskEntity task = new TaskEntity("WakeUp", LocalDateTime.now());
    TaskEntity task1 = new TaskEntity("Shower", LocalDateTime.now().plusMinutes(10));
    TaskEntity task2 = new TaskEntity("Breakfast", LocalDateTime.now().plusMinutes(25));
    TaskEntity task3 = new TaskEntity("Dressing", LocalDateTime.now().plusMinutes(40));

    @Mock
    TaskRepository repository;
    @Mock
    BindingResult bindingResult;
    @InjectMocks
    TaskService taskService;


    @Test
    void testGetAll() {
        List<TaskEntity> list = new ArrayList<>();
        list.add(task);
        list.add(task1);
        list.add(task2);
        list.add(task3);
        when(repository.findAll()).thenReturn(list);
        assertEquals(taskService.getAll(), list);
    }

    @Test
    void testAdd() {
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<Object> result1 = taskService.addNew(task, bindingResult);
        ResponseEntity<Object> result2 = ResponseEntity.ok().build();

        assertEquals(result1, result2);
    }

    @Test
    void testFindById() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(task));
        ResponseEntity<Object> result1 = taskService.findById(1L);
        ResponseEntity<Object> result2 = ResponseEntity.ok(Optional.ofNullable(task));

        assertEquals(result1, result2);
    }

    @Test
    void testDelete() {
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(task));

        ResponseEntity<Object> result1 = taskService.deleteById(1L);
        ResponseEntity<Object> result2 = new ResponseEntity<>(new ApiErrorDTO("200",
                ResourceBundle.getBundle("lang",Locale.GERMANY).getString("ok1"),
                ResourceBundle.getBundle("lang",Locale.GERMANY).getString("ok2"))
                .toSuccess(), HttpStatus.OK);

        assertEquals(result1, result2);
    }
}
