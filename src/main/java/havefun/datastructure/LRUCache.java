package havefun.datastructure;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private ListNode dummyHead;
    private ListNode dummyTail;
    private Map<Integer, ListNode> cache;
    private int capacity = 32;
    private int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummyHead = new ListNode();
        dummyTail = new ListNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        cache = new HashMap<>(capacity);
    }

    public int get(int key) {
        ListNode node = cache.get(key);
        if (node != null) {
            moveToTail(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        ListNode node = cache.get(key);
        if (node == null) {
            ListNode aNode = new ListNode(key, value);
            cache.put(key, aNode);
            addToTail(aNode);
            if (++size > capacity) {
                ListNode head = removeHead();
                cache.remove(head.key);
                --size;
            }
        } else {
            node.val = value;
            moveToTail(node);
        }
    }

    private void addToTail(ListNode aNode) {
        dummyTail.prev.next = aNode;
        aNode.prev = dummyTail.prev;
        dummyTail.prev = aNode;
        aNode.next = dummyTail;
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private ListNode removeHead() {
        ListNode head = dummyHead.next;
        removeNode(head);
        return head;
    }

    private void moveToTail(ListNode node) {
        removeNode(node);
        addToTail(node);
    }

    static class ListNode {

        public ListNode prev;
        public ListNode next;
        public int key;
        public int val;

        public ListNode() {

        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}


