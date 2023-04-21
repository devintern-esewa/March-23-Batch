package springMvc.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface Don {
    String message() default "{invalidAgeMessage}";

    int lower() default 18;

    int upper() default 60;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
