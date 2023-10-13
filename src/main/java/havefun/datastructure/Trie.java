package havefun.datastructure;

class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("zaniu");
    }

    private Trie[] tries;
    private boolean isEnd;

    public Trie() {
        tries = new Trie[26];
    }

    public void insert(String word) {
        if (word == null) return;
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.tries[index] == null) {
                curr.tries[index] = new Trie();
            }
            curr = curr.tries[index];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        Trie curr = findPrefix(word);
        return curr != null && curr.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie curr = findPrefix(prefix);
        return curr != null;
    }

    private Trie findPrefix(String word) {
        if (word == null) return null;
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.tries[index] == null) {
                return null;
            }
            curr = curr.tries[index];
        }
        return curr;
    }
}