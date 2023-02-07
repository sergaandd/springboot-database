package shpp.mentor.springbootdatabase.models;

import shpp.mentor.springbootdatabase.controllers.Status;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEnumClass implements ConstraintValidator<CheckEnum, String> {
    @Override
    public void initialize(CheckEnum annotation) {
        //No parameter
    }

    @Override
    public boolean isValid(String st, ConstraintValidatorContext constraintValidatorContext) {
        for (Status val : Status.values()) {
            if (st.equals(val.toString())) {
                return true;
            }
        }
        return false;
    }
}
