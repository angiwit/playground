package havefun;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProblemPicker {

    private static final Map<String, Integer> problemNo = new HashMap<>();
    private static final Random random = new Random(System.currentTimeMillis());
    private static final int CODE_TOP_PAGE = 10;

    static {
        problemNo.put("array", 6);
        problemNo.put("linkedList", 8);
        problemNo.put("hash", 9);
        problemNo.put("string", 7);
        problemNo.put("double-pointer", 10);
        problemNo.put("stack-queue", 8);
        problemNo.put("binary-tree", 34);
        problemNo.put("backtrace", 21);
        problemNo.put("greedy", 24);
        problemNo.put("dp", 53);
        problemNo.put("single-stack", 5);
        problemNo.put("tencent", 50);
        problemNo.put("minihack", 20);
        problemNo.put("design-datastructure", 7);
    }

    private static final int problemType = problemNo.size();

    private static void pickSpecific(String type) {
        if (type.equals("codetop")) {
            int pageNo = new Random(System.currentTimeMillis()).nextInt(CODE_TOP_PAGE);
            int number = new Random(System.currentTimeMillis()).nextInt(20);
            System.out.println(String.format("pageNo is: %s, number is: %s", pageNo, number));
            return;
        }
        int count = problemNo.get(type);
        System.out.println(random.nextInt(count));
    }

    private static void pickFromAll() {
        int next = random.nextInt(problemType);
        int i = 0;
        for (Map.Entry<String, Integer> no : problemNo.entrySet()) {
            if (i == next) {
                String type = no.getKey();
                int bound = no.getValue();
                int picked = random.nextInt(bound);
                System.out.println(String.format("type is: %s, picked no is: %s", type, picked + 1));
                break;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        pickSpecific("codetop");
//        pickFromAll();
    }
}
