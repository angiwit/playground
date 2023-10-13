package aopalliance;

public class Person implements IPerson {
    @Override
    public String sayHello() {
        return this.sayHello1();
    }

    /**
     * When running this method, before/after interceptors will not run.
     * private method can not be inherited, so CGLIB can't generate a class file that overwrites this method.
     * The approaches to proxy private method are: use aspectj weaving, use java agent to dynamically modify
     * the class byte code when loading.
     *
     * @return
     */
    private String sayHello1() {
        return "Alice";
    }
}
