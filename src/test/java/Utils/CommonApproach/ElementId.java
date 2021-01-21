package Utils.CommonApproach;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ElementId {
    String id() default "ELEMENT ID IS NOT DEFINED";
}