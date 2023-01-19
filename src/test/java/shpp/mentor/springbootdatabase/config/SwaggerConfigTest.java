package shpp.mentor.springbootdatabase.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @Test
    void api() {
        SwaggerConfig swaggerTest = new SwaggerConfig();
        Assertions.assertEquals(true, swaggerTest.api().isEnabled());
        swaggerTest=null;
    }
}