package com.ffr.booklibrary.inventory.core.application.ports.incoming;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
// From https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s13.html
@Pattern(
    regexp =
        "^(?:ISBN(?:-1[03])?:?●)?(?=[0-9X]{10}$|(?=(?:[0-9]+[-●]){3})↵\n"
            + "[-●0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-●]){4})[-●0-9]{17}$)↵\n"
            + "(?:97[89][-●]?)?[0-9]{1,5}[-●]?[0-9]+[-●]?[0-9]+[-●]?[0-9X]$")
@ReportAsSingleViolation
public @interface Isbn {
    String message() default "{invalid.isbn}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}