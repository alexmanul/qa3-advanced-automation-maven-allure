package Utils.CommonApproach;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Identificator {
    String id() default "IDENTIFICATOR IS NOT DEFINED";
}