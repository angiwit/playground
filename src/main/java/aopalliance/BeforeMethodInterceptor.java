package aopalliance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class BeforeMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("befor method execution");
        String methodName = methodInvocation.getMethod().getName();
        Object[] arguments = methodInvocation.getArguments();
        Class<?> clz = methodInvocation.getClass();
        System.out.println(String.format("running class is %s, running method is %s, running arguments length is %s", clz.getName(), methodName, arguments.length));
        /**
         * To drive the next interceptor's invoke running, next invoke method also has a MethodInvocation.
         * And the MethodInvocation will run all the interceptors in it. It's a tree and runs in recursive way.
         * CglibMethodInterceptor ->
         *      MyMethodInvocation.proceed ->
         *            FirstAopInterceptor.invoke ->
         *                 MyMethodInvocation.proceed ->
         *                      ...
         *            SecondAopInterceptor.invoke <-
         *      End of proceed
         */
        Object result = methodInvocation.proceed();
        return result;
    }
}
