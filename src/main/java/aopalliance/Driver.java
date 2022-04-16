package aopalliance;

public class Driver {

    public static void main(String[] args) {
        Person talk = new Person();
        IPerson iTalk = ProxyFactory.getProxy(talk);
        System.out.println(iTalk.sayHello());
    }
}
