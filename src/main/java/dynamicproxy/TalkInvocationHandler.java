package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TalkInvocationHandler implements InvocationHandler {

    private Object talk;

    public TalkInvocationHandler(Object talk) {
        this.talk = talk;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before talk, clear mouth");
        return method.invoke(talk, args);
    }

    public Object getTalk() {
        return talk;
    }

    public void setTalk(Object talk) {
        this.talk = talk;
    }
}
