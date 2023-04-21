package springMvc.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Don, Integer> {
    private int lower;
    private int upper;

    public void initialize(Don age) {
        this.lower = age.lower();
        this.upper = age.upper();
    }

    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        if (age == null) {
            return false;
        }
        // Write logic here
        if (age < lower || age > upper) {
            return false;
        }
        return true;
    }
}
