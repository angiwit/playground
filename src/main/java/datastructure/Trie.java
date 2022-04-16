package datastructure;

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
        Trie temp = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.tries[index] == null) {
                temp.tries[index] = new Trie();
            }
            temp = temp.tries[index];
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = findPrefix(word);
        return trie != null && trie.isEnd == true;
    }

    public boolean startsWith(String prefix) {
        Trie trie = findPrefix(prefix);
        return trie != null;
    }

    public Trie findPrefix(String prefix) {
        Trie temp = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (temp.tries[index] == null) {
                return null;
            } else {
                temp = temp.tries[index];
            }
        }
        return temp;
    }
}