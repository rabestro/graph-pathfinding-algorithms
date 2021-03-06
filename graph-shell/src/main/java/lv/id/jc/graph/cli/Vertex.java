package lv.id.jc.graph.cli;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;

@Target({PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Commands.class})
public @interface Vertex {
    String message() default "must be present in the graph scheme";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
