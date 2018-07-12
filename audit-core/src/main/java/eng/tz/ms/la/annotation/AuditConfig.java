package eng.tz.ms.la.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import eng.tz.ms.la.model.custom.AuditMeta;

 

/**
 * @author salvatore mariniello
 */
 
@Retention(value = RUNTIME)
@Target(value = { ElementType.TYPE ,ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface AuditConfig {
	public Class<?> metaActor() default AuditMeta.class;
}
