package shpp.mentor.springbootdatabase.models;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CheckEnumClass.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEnum {

    String message() default "No errors in field Status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value() default "";

}

