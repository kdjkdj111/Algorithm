//22212048_김동준

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class HW2 {
    public static void main(String[] args) {
        ListNode head = null;
        Solution2 sol = new Solution2();

        int[] list = {-1,5,3,4,0};
        head = makeLinkedList(list);
        head = sol.insertionSortList(head);
        printList(head);
    }

    private static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode makeLinkedList(int[] list) {
        ListNode head = null;
        for(int i = list.length-1; i >= 0 ; i--){
            head = new ListNode(list[i],head);
        }

        return head;
    }
}

class Solution2 {
    public ListNode insertionSortList(ListNode head) {
        ListNode result = new ListNode(-1); //새 연결 리스트의 초기 노드

        ListNode cur=head; //삽입할 노드

        while(cur!=null){
            ListNode i=result; //삽입 위치의 앞 노드
            while(i.next!=null && i.next.val<cur.val){//삽입 위치  탐색
                i=i.next; //삽입할 값이 더 크다면 뒤로
            }

            ListNode temp= cur.next; //다음 노드 기억
            cur.next = i.next; //연결
            i.next = cur;

            cur=temp;
        }

        return result.next; //초기 노드 제거
    }
}

