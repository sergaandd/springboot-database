package shpp.mentor.springbootdatabase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.validation.ConstraintValidatorContext;

class IsIpnValidEngineTest {
    ConstraintValidatorContext validator = Mockito.mock(ConstraintValidatorContext.class);
    @Test
    void isValid() {
        boolean result = new IsIpnValidEngine().isValid("2701010395",validator);
        Assertions.assertEquals(true,result);
    }
}