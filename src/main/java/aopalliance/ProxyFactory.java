package aopalliance;

import net.sf.cglib.proxy.Enhancer;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(T obj) {
        BeforeMethodInterceptor beforeMethodInterceptor = new BeforeMethodInterceptor();
        AfterMethodInterceptor afterMethodInterceptor = new AfterMethodInterceptor();
        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(beforeMethodInterceptor);
        interceptorList.add(afterMethodInterceptor);
        CglibMethodInterceptor cglibMethodInterceptor = new CglibMethodInterceptor(interceptorList, obj);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(cglibMethodInterceptor);
        return (T) enhancer.create();
    }
}
