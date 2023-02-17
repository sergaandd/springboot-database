package shpp.mentor.springbootdatabase.models;

import shpp.mentor.springbootdatabase.controllers.Status;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEnumClass implements ConstraintValidator<CheckEnum, Status> {
    @Override
    public void initialize(CheckEnum annotation) {
        //No parameter
    }

    @Override
    public boolean isValid(Status st, ConstraintValidatorContext constraintValidatorContext) {
        for (Status val : Status.values()) {
            if (st.equals(val)) {
                return true;
            }
        }
        return false;
    }
}
