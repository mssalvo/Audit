package eng.tz.ms.la.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author salvatore mariniello
 */
 
@Retention(value=RUNTIME)
@Target(value={ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface AuditRequestParam {
	public String key() default "";
	public String name() default "";
	public Class<?> type() default AuditRequestParam.class;
	public boolean print() default true;
}
 