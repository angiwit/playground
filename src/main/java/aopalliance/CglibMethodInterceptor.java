package aopalliance;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Cglib creates a proxy object `MethodInvocation` and proceed method will start the engine,
 * In the proxy object, multiple interceptors created by user will be invoked in chain approach.
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    private List<org.aopalliance.intercept.MethodInterceptor> interceptorList;

    private Object target;

    public CglibMethodInterceptor(List<org.aopalliance.intercept.MethodInterceptor> interceptorList, Object target) {
        this.interceptorList = interceptorList;
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInvocation invocation = new MyMethodInvocation(method, objects, target, methodProxy, interceptorList);
        return invocation.proceed();
    }
}
