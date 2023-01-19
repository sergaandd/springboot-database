package shpp.mentor.springbootdatabase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsIpnValidEngine implements ConstraintValidator<TestIpnValid, String> {
    @Override
    public void initialize(TestIpnValid annotation) {
        //This field is empty
    }
    @Override
    public boolean isValid(String ipn,
                           ConstraintValidatorContext constraintValidatorContext) {
        return ipn.length()==10?((((Integer.parseInt(ipn.substring(0,1))*(-1)+
                Integer.parseInt(ipn.substring(1,2))*5+
                Integer.parseInt(ipn.substring(2,3))*7+
                Integer.parseInt(ipn.substring(3,4))*9+
                Integer.parseInt(ipn.substring(4,5))*4+
                Integer.parseInt(ipn.substring(5,6))*6+
                Integer.parseInt(ipn.substring(6,7))*10+
                Integer.parseInt(ipn.substring(7,8))*5+
                Integer.parseInt(ipn.substring(8,9))*7)%11)%10) == Integer.parseInt(ipn.substring(9,10))):false;
    }
}