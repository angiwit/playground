package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteTalkInvocationHandler implements InvocationHandler {

    /**
     * The proxy object is an enhanced original object,
     * it means the proxy object implements the interface as well.
     * For pure interface scenario, in the invoke method,
     * if the methods are not Object method,
     * you can write RPC logic to implement the interface methods.
     */
    // proxy: the generated object that implemented the interfaces.
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("equals")) {
            return proxy == args[0];
        } else if (method.getName().equals("hashCode")) {
            return System.identityHashCode(proxy);
        } else if (method.getName().equals("toString")) {
            return proxy.getClass().getName() + System.identityHashCode(proxy);
        }
        // For non Object methods, override to new logic.
        Thread.sleep(1000L);// mock RPC call.
        return "done";
    }
}
