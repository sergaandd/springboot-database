package shpp.mentor.springbootdatabase.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsEntityTest {
private StudentsEntity entityTest = new StudentsEntity();

    @Test
    void getId() {
        entityTest.setId(1L);
        Assertions.assertEquals(1,entityTest.getId());
    }

    @Test
    void getFirstName() {
        entityTest.setFirst_name("first_nameTest");
        Assertions.assertEquals("first_nameTest",entityTest.getFirstName());
    }

    @Test
    void getLastName() {
        entityTest.setLast_name("last_nameTest");
        Assertions.assertEquals("last_nameTest",entityTest.getLastName());
    }

    @Test
    void getIpn() {
        entityTest.setIpn("1234567890");
        Assertions.assertEquals("1234567890",entityTest.getIpn());
    }
}