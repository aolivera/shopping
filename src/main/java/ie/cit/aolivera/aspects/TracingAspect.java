package ie.cit.aolivera.aspects;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TracingAspect {
	Log log = LogFactory.getLog(TracingAspect.class);

	@Before("execution(* ie.cit.aolivera.data.dao.Jdbc*.*(..)) && target(repo)")
	public void trace(JoinPoint point, Object repo) {
		String methodName = point.getSignature().getName();
		log.trace("method invoked: " + repo.getClass().getName() + " # " + methodName);
	}
}
