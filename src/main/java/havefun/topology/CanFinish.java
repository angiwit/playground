package havefun.topology;

import java.util.*;

public class CanFinish {

    public static void main(String[] args) {
        int[][] prerequisites = {{0, 1}};
        System.out.println(canFinish(2, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        return canFinishCore(numCourses, prerequisites);
    }

    public static boolean canFinishCore(int numCourses, int[][] prerequisites) {
        // calculate in-degree for each tuple.
        // also add related courses into map.
        Map<Integer, List<Integer>> relatedCourses = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            List<Integer> related = relatedCourses.get(prerequisites[i][1]);
            if (related == null) {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                relatedCourses.put(prerequisites[i][1], list);
            } else {
                related.add(prerequisites[i][0]);
            }
        }
        // Add all 0 in-degrees to queue.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        // iterate the queue, reduce the in-degree of the related courses.
        while (!queue.isEmpty()) {
            int course = queue.poll();
            List<Integer> related = relatedCourses.get(course);
            if (related != null && related.size() > 0) {
                for (int i = 0; i < related.size(); i++) {
                    if (--inDegree[related.get(i)] == 0) {
                        queue.offer(related.get(i));
                    }
                }
            }
        }
        // check if all course's in-degree is reduced to 0.
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] != 0) return false;
        }
        return true;
    }
}
