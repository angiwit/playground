import org.openjdk.jol.info.ClassLayout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        Map<String, Boolean> map = new ConcurrentHashMap<>();
//        System.out.println(map.remove("key").toString());
        System.out.println(ClassLayout.parseClass(BooleanWrapper.class).toPrintable());
        System.out.println();
    }

    class BooleanWrapper {
        private boolean value;
    }
}
