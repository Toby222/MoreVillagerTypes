package tech.tobot.morevillagers.base.iface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tech.tobot.morevillagers.base.ClientModule;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Module {
    boolean alwaysEnabled() default false;

    boolean enabledByDefault() default true;

    String description() default "";

    String mod() default "";

    Class<? extends ClientModule> client() default ClientModule.class;
}