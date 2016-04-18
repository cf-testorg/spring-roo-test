package org.springframework.roo.addon.web.mvc.controller.addon.responses;

import java.util.List;

import org.springframework.roo.classpath.details.annotations.AnnotationMetadata;
import org.springframework.roo.model.JavaType;
import org.springframework.roo.project.Feature;
import org.springframework.roo.project.maven.Pom;

/**
 * This interface provides some different operations that allow
 * to manage controller response types.
 * 
 * @author Juan Carlos García
 * @since 2.0
 */
public interface ControllerMVCResponseService extends Feature {

  /**
   * This operation returns the implementation response type.
   * 
   * @return String with an unique identifier of response type
   */
  String getResponseType();

  /**
   * This operation returns the annotation type that identifies the implementation
   * response.
   * 
   * @return JavaType with the annotation managed by the implementation
   */
  JavaType getAnnotation();

  /**
   * This operation annotates a controller with the annotation defined by 
   * getAnnotation() operation.
   * 
   * @param controller JavaType with the controller to be annotated.
   */
  void annotate(JavaType controller);

  /**
   * This operation adds finders to the specified controller annotation.
   * 
   * @param controller JavaType with the controller to be updated with
   *                   the finders.
   * @param finders List with finder names to be added.
   */
  void addFinders(JavaType controller, List<String> finders);

  /**
   * This operation will check if some controller has the implemented
   * response type.
   * 
   * @param controller JavaType with controller to check
   * @return true if provided controller has the implemented responseType.
   *        If not, return false.
   */
  boolean hasResponseType(JavaType controller);

  /**
   * This operation will install all the necessary items to be able to use
   * the implemented responseType.
   * 
   * @param module Pom with the module where this responsetype should
   *        be installed.
   */
  void install(Pom module);

}
