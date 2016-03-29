package cn.edu.tju.scs.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Aspect
public class BaseAspect {

	// 选择特定类型的连接点
	@Pointcut("execution(* cn.edu.tju.scs.web.controller.*.*(..))")
	public void pointcut() {}

	// 匹配当前包下的任何类（连接点的范围）
	@Pointcut("within(cn.edu.tju.scs.web.controller.*)")
	public void bizPointcut() {}

	// Before
	@Before("pointcut()")
	public void before() {
		System.out.println("Before.");
	}

	// Before with arguments, 即 对应方法上传入的参数
	@Before("pointcut() && args(arg)")
	public void beforeWithParam(Object arg) {
		System.out.println("BeforeWithParam." + arg);
	}
	
	@Before("pointcut() && @annotation(requestMapping)")
	public void beforeWithAnnotaion(RequestMapping requestMapping) {
		System.out.println("Before With Annotation." + requestMapping.value()[0]);
	}

	@Pointcut("execution(* cn.edu.tju.scs.web.controller.*.*(..)) &&  @annotation(requestMapping)")
	public void pointcutWithAnnotation(RequestMapping requestMapping) {}


	@Before(value = "pointcutWithAnnotation(requestMapping)", argNames = "requestMapping")
	public void beforeWithAnnotaion2(RequestMapping requestMapping) {
		System.out.println("Before With Annotation." + requestMapping.value()[0]);
	}

	// AfterReturning
	@AfterReturning(pointcut="bizPointcut()")
	public void afterReturningNoReturnValue() {
		System.out.println("AfterReturning no return value: --------------");
	}

	// AfterReturning with return value
	@AfterReturning(pointcut="bizPointcut()", returning="returnValue")
	public void afterReturning(Object returnValue) {
		System.out.println("AfterReturning with return value---------------: " + returnValue);
	}

	// AfterThrowing
	@AfterThrowing(pointcut="pointcut()")
	public Object afterThrowingWithOutValue() {
		System.out.println("AfterThrowing ======================================: ");
		return "error";
	}

	// AfterThrowing with return value
	@AfterThrowing(pointcut="pointcut()", throwing="e")
	public Object afterThrowing(RuntimeException e) {
		System.out.println("AfterThrowing ======================================: " + e.getMessage());
		return "error";
	}


	// 必须准备处理正常和异常两种返回方式
	@After("pointcut()")
	public void after() {
		System.out.println("After.");
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Around 1. 前");
		// 调用真正执行的方法（），由于不确定是否有返回值，所以采用一种通用的 Object 的方式，处理返回值
		Object obj = pjp.proceed();
		System.out.println("Around 2. 后");
		System.out.println("Around : " + obj);
		return obj;
	}
	
}
