package org.springframework.roo.addon.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated class is a @Configuration class
 * that manages Spring Security configuration.
 * 
 * @author Juan Carlos García
 * @since 2.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RooWebSecurityConfiguration {

  /**
   * Indicates the profile where this configuration is applied
   */
  String profile() default "";

  /**
   * Indicates if method-level security is enabled. Default is false.
   */
  boolean enableGlobalMethodSecurity() default false;

  /**
   * Indicates if audit support is enabled. Default is false.
   */
  boolean enableJpaAuditing() default false;
}
