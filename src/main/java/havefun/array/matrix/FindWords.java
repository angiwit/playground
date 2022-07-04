package havefun.array.matrix;

import java.util.*;

/**
 * https://leetcode.cn/problems/word-search-ii/
 */
public class FindWords {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWordsCore(board, i, j, result, trie);
            }
        }
        return new ArrayList<>(result);
    }

    private static void findWordsCore(char[][] board, int i, int j, Set<String> result, Trie trie) {
        if (!trie.children.containsKey(board[i][j])) return;
        char cur = board[i][j];
        trie = trie.children.get(cur);
        if (null != trie.word) {
            result.add(trie.word);
        }
        board[i][j] = '#';
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= 0 && col >= 0 && row < board.length && col < board[0].length) {
                findWordsCore(board, row, col, result, trie);
            }
        }
        board[i][j] = cur;
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));
    }

    static class Trie {
        private String word;
        private Map<Character, Trie> children = new HashMap<>();

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); i++) {
                char t = word.charAt(i);
                if (!cur.children.containsKey(t)) {
                    cur.children.put(t, new Trie());
                }
                cur = cur.children.get(t);
            }
            cur.word = word;
        }
    }
}
