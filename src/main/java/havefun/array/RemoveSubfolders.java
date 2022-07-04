package havefun.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/remove-sub-folders-from-the-filesystem/
 */
public class RemoveSubfolders {

    public List<String> removeSubfolders(String[] folder) {
        List<String> parentFolders = new ArrayList<>();
        parentFolders.add("none");
        return Arrays.stream(folder).sorted().filter(x -> {
            if (x.indexOf(parentFolders.get(0)) == 0) {
                return false;
            } else {
                parentFolders.add(0, x + "/");
                return true;
            }
        }).collect(Collectors.toList());
    }
}
