package  shpp.mentor.springbootdatabase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = IsIpnValidEngine.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TestIpnValid {
    String message() default "No errors in ipn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}