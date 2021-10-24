package com.ffr.booklibrary.inventory.core.application.ports.incoming;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$")
@ReportAsSingleViolation
public @interface Isbn {
  String message() default "{invalid.isbn}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
