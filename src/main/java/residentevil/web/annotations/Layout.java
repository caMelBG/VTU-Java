package residentevil.web.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
    String NONE = "none";

    @AliasFor("layout")
    String value() default "";

    @AliasFor("value")
    String layout() default "";
}
