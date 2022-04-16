package dynamicproxy;

public class Driver {

    public static void main(String[] args) {
        ITalk talk = (ITalk) TalkProxyFactory.getProxyInstance(new Talk());
        System.out.println(talk.talk());

        ITalk remoteTalk = TalkProxyFactory.getRemoteProxyInstance(ITalk.class);
        System.out.println(remoteTalk.talk());
    }
}
