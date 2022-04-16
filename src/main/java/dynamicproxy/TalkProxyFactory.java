package dynamicproxy;

import java.lang.reflect.Proxy;

public class TalkProxyFactory {

    public static <T> T getProxyInstance(Object obj) {
        TalkInvocationHandler tih = new TalkInvocationHandler(obj);
        return (T) Proxy.newProxyInstance(TalkProxyFactory.class.getClassLoader(),
                obj.getClass().getInterfaces(), tih);
    }

    public static <T> T getRemoteProxyInstance(Class<T> clz) {
        RemoteTalkInvocationHandler tih = new RemoteTalkInvocationHandler();
        return (T) Proxy.newProxyInstance(TalkProxyFactory.class.getClassLoader(), new Class[]{clz}, tih);
    }
}
