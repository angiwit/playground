package shellcalljava;

public class HelloWorld {
    public static void main(String[] args) {
        String pid = args[0];
        String className = args[1];
        String classPath = args[2];
        System.out.println(pid);
    }
}
