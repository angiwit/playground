package aopalliance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

public class MyMethodInvocation implements MethodInvocation {

    private Method method;

    private Object[] arguments;

    private Object target;

    private Object proxy;

    private List<MethodInterceptor> interceptorList;

    private int interceptorIndex = -1;

    public MyMethodInvocation(Method method, Object[] arguments, Object target, Object proxy, List<MethodInterceptor> interceptorList) {
        this.method = method;
        this.arguments = arguments;
        this.target = target;
        this.proxy = proxy;
        this.interceptorList = interceptorList;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        if (interceptorIndex == interceptorList.size() - 1) {
            return ReflectionUtils.invokeMethod(method, target, arguments);
        }
        MethodInterceptor interceptor = interceptorList.get(++interceptorIndex);
        return interceptor.invoke(this);
    }

    @Override
    public Object getThis() {
        return proxy;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
