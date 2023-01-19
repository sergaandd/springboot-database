package shpp.mentor.springbootdatabase.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student studentTest = new Student("first_nameTest","last_nameTest","123456789");

    @Test
    void getFirst_name() {
        Assertions.assertEquals("first_nameTest",studentTest.getFirst_name());
    }

    @Test
    void getLast_name() {
        Assertions.assertEquals("last_nameTest",studentTest.getLast_name());
    }

    @Test
    void getIpn() {
        Assertions.assertEquals("123456789",studentTest.getIpn());
    }
}