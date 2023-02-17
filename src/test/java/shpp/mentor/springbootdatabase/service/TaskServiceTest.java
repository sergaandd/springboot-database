package shpp.mentor.springbootdatabase.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shpp.mentor.springbootdatabase.controllers.Status;
import shpp.mentor.springbootdatabase.entity.TaskEntity;
import shpp.mentor.springbootdatabase.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    TaskEntity task = new TaskEntity("WakeUp", LocalDateTime.now());
    TaskEntity task1 = new TaskEntity("Shower", LocalDateTime.now().plusMinutes(10));
    TaskEntity task2 = new TaskEntity("Breakfast", LocalDateTime.now().plusMinutes(25));
    TaskEntity task3 = new TaskEntity("Dressing", LocalDateTime.now().plusMinutes(40));

    @Mock
    TaskRepository repository;
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
    void testPlanned() {
        assertEquals(Status.PLANNED.toString(),task.getStatus());
    }
    @Test
    void testCoordinate() {
        task1.setStatus(Status.nextStatus(task1.getStatus()));
        assertEquals(Status.COORDINATE.toString(),task1.getStatus());
    }
    @Test
    void testWork_in_progress() {
        task2.setStatus(Status.nextStatus(task2.getStatus()));
        task2.setStatus(Status.nextStatus(task2.getStatus()));
        assertEquals(Status.WORK_IN_PROGRESS.toString(),task2.getStatus());
    }

    @Test
    void testDone() {
        task3.setStatus(Status.nextStatus(task3.getStatus()));
        task3.setStatus(Status.nextStatus(task3.getStatus()));
        task3.setStatus(Status.nextStatus(task3.getStatus()));
        assertEquals(Status.DONE.toString(),task3.getStatus());
    }
    @Test
    void testUnableChangeDone() {
        task3.setStatus(Status.DONE);
        task3.setStatus(Status.nextStatus(task3.getStatus()));
        assertEquals(Status.DONE.toString(),task3.getStatus());
    }
    @Test
    void testPositionDone() {
        int doneIndex = Status.values().length-2;
        assertEquals(Status.DONE,Status.values()[doneIndex]);
    }

    @Test
    void testPositionCancel() {
        int doneIndex = Status.values().length-1;
        assertEquals(Status.CANCEL,Status.values()[doneIndex]);
    }
}
