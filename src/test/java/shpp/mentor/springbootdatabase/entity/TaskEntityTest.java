package shpp.mentor.springbootdatabase.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TaskEntityTest {
private TaskEntity entityTest = new TaskEntity();

    @Test
    void getId() {
        entityTest.setId(1L);
        Assertions.assertEquals(1,entityTest.getId());
    }

    @Test
    void getName() {
        entityTest.setName("nameTest");
        Assertions.assertEquals("nameTest",entityTest.getName());
    }

    @Test
    void getDetail() {
        entityTest.setStart(LocalDateTime.now());
        Assertions.assertEquals(LocalDateTime.now().getDayOfMonth(),(entityTest.getStart()).getDayOfMonth());
    }

    @Test
    void getIpn() {
//        entityTest.setStatus("DONE");
//        Assertions.assertEquals("DONE",entityTest.getStatus());
    }
}