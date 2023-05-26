
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    public class ListNode {
        int value;
        ListNode next;
        ListNode(int x) {
            value = x;
        }
    }
    public class Resolve {
        public ListNode reverseList(ListNode head) {
//            1 -> 2 -> 3 -> null
//            null -> 3 -> 2 -> 1
            ListNode previousNode = null;
            ListNode currentNode = head;

            while (currentNode != null) {
                ListNode nextNode = currentNode.next;
                currentNode.next = previousNode;
                previousNode = currentNode;
                currentNode = nextNode;
            }
            return previousNode;
        }
    }
}