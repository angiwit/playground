package havefun.linkedlist;

public class MyLinkedList {

    class ListNode {
        private ListNode next;
        private int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private int size;

    public MyLinkedList() {
        this.head = new ListNode(0);
        this.size = 0;
    }

    public int get(int index) {
        if (size == 0 || index >= size) return -1;
        ListNode temp = head;
        ListNode curr = head;
        // index steps needed
        // to move from sentinel node to wanted index
        for (int i = 0; i < index + 1; ++i) curr = curr.next;
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
        int i = 0;
        ListNode prev = head;
        while (head.next != null && i < index) {
            prev = prev.next;
            i++;
        }
        ListNode node = new ListNode(val);
        node.next = prev.next;
        prev.next = node;
        ++size;
        printMyLinkedList(this);
    }

    public void deleteAtIndex(int index) {
        //DO NOT miss the 'equals' here
        if (index < 0 || index >= size) return;
        int i = 0;
        ListNode prev = head;
        while (head.next != null && i < index) {
            prev = prev.next;
            i++;
        }
        ListNode toDelete = prev.next;
        ListNode next = toDelete.next;
        prev.next = next;
        --size;
        printMyLinkedList(this);
    }

    private static void printMyLinkedList(MyLinkedList myLinkedList) {
        for (int i = 0; i < myLinkedList.size; i++) {
            System.out.print(myLinkedList.get(i) + " ");
        }
        System.out.println("size:" + myLinkedList.size);
    }

    /**
     * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
     * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
     */

    /**
     * ["MyLinkedList","","","","","","","","","","","deleteAtIndex"]
     * [[],[],[],[],[],[],[],[],[],[],[],[4]]
     *
     * @param args
     */
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(2);
        myLinkedList.deleteAtIndex(1);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(3);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(5);
        myLinkedList.addAtTail(5);
        myLinkedList.get(5);
        myLinkedList.deleteAtIndex(6);
        myLinkedList.deleteAtIndex(4);
    }
}
