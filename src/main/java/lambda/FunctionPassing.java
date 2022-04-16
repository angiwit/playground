package lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

public class FunctionPassing {

    public static Map<Integer, String> mappings = new HashMap<>();

    static {
        mappings.put(1, "1");
        mappings.put(2, "2");
        mappings.put(4, "3");
    }

    public static Map<String, Function<Integer, String>> functionMap = new HashMap<>();

    static {
        functionMap.put("firstMapFunc", integer -> {
            String result = mappings.get(integer);
            if (result == null) return "NAN";
            return result;
        });
    }

    public static void main(String[] args) {
        Function<Integer, Integer> function = x -> x * x;
        System.out.println(process(function));
        Function<Integer, Integer> function1 = x -> x == null ? -1 : x * x + 1;
        System.out.println(process(function1));
        System.out.println(process1(functionMap.get("firstMapFunc")));
    }

    public static Integer process(Function<Integer, Integer> func) {
        Integer rand = new Random(System.currentTimeMillis()).nextInt(1000);
        return func.apply(rand);
    }

    public static String process1(Function<Integer, String> func) {
        Integer rand = new Random(System.currentTimeMillis()).nextInt(1000);
        return func.apply(rand);
    }


}
