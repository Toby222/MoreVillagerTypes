package tech.tobot.morevillagers.base.iface;

import tech.tobot.morevillagers.base.ClientModule;

import java.lang.annotation.*;

@Target({ ElementType.TYPE }) @Retention(RetentionPolicy.RUNTIME) @Inherited public @interface Module {
  boolean alwaysEnabled() default false;
  
  boolean enabledByDefault() default true;
  
  String description() default "";
  
  String mod() default "";
  
  Class<? extends ClientModule> client() default ClientModule.class;
}