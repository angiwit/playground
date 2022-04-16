package havefun.linkedlist;

public class MyDeque {

    class ListNode {
        private ListNode prev;
        private ListNode next;
        private int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public MyDeque() {
        this.head = new ListNode(0);
        this.tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }

    public int get(int index) {
        if (size == 0 || index >= size) return -1;
        ListNode curr = head;
        // index steps needed
        // to move from sentinel node to wanted index
        if (index + 1 < size - index)
            for (int i = 0; i < index + 1; ++i) curr = curr.next;
        else {
            curr = tail;
            for (int i = 0; i < size - index; ++i) curr = curr.prev;
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index < 0) index = 0;
        ListNode prev = head;
        ListNode succ = tail;
        //insert into the left part
        if (index < size - index) {
            for (int i = 0; i < index; i++) prev = prev.next;
            succ = prev.next;
        } else {
            for (int i = size - 1; i > index - 1; i--) succ = succ.prev;
            prev = succ.prev;
        }
        ListNode node = new ListNode(val);
        node.next = succ;
        node.prev = prev;
        prev.next = node;
        succ.prev = node;
        ++size;
    }

    public void deleteAtIndex(int index) {
        //DO NOT miss the 'equals' here
        if (index < 0 || index >= size) return;
        ListNode toDelete;
        if (index < size - index) {
            ListNode prev = head;
            for (int i = 0; i < size; i++) {
                prev = prev.next;
            }
            toDelete = prev.next;
        } else {
            ListNode succ = tail;
            for (int i = size - 1; i > index - 1; i--) {
                succ = succ.prev;
            }
            toDelete = succ.prev;
        }
        ListNode prev = toDelete.prev;
        ListNode succ = toDelete.next;
        prev.next = succ;
        succ.prev = prev;
        --size;
    }
}
