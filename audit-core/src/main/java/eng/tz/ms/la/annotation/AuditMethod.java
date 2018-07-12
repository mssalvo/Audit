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
@Target(value={ElementType.METHOD})
@Documented
public @interface AuditMethod {
	public String key() default "";
	public Class<?> type() default AuditMethod.class;
	public String[] method() default {};
	public boolean print() default true;
}
 