import java.util.List;

public class ListNode {
    int val;
      ListNode next;
      ListNode(int x) { val = x; }

      public static void main(String[] args){
          ListNode[] listNodes = new ListNode[3];
          listNodes[0] = new ListNode(1);
          listNodes[1] = new ListNode(1);
          listNodes[2] = new ListNode(2);
          listNodes[0].next = new ListNode(4);
          listNodes[0].next.next = new ListNode(5);
          listNodes[1].next = new ListNode(3);
          listNodes[1].next.next = new ListNode(4);
          listNodes[2].next = new ListNode(6);
          System.out.println(new Solution().mergeKLists(listNodes));
          ListNode listNode = new ListNode(1);
          listNode.next = new ListNode(2);
          listNode.next.next = new ListNode(3);
          listNode.next.next.next = new ListNode(4);
          System.out.println(new Solution().rotateRight(listNode,5));

      }

  /*  @Override
    public String toString() {
          if(this.next!=null){
              return this.val+" "+this.next.toString();
          }else{
              return this.val+"";
          }
    }*/
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode listNode = new ListNode(0);
        ListNode Temp = listNode;
        if(lists.length==0)return null;

        while (true) {
            int max = -1;
            int last = 0;
            int lastPosition = 0;
            for (int n = 0; n < lists.length; n++) {
                if (lists[n] != null) {
                    lastPosition = n;
                    last++;
                    if (last == 2) break;
                }
            }
            if (last == 0) {
                break;
            } else if (last == 1) {
                max = lastPosition;
            } else {
                for (int n = 0; n < lists.length; n++) {
                    if (lists[n] == null) continue;
                    for (int i = n; i < lists.length; i++) {
                        if (lists[i] == null) continue;
                        if (lists[i].val <= lists[n].val) {
                            max = i;
                            n = i;
                        }
                    }
                    break;
                }
            }
            Temp.next = new ListNode(lists[max].val);
            Temp = Temp.next;
            lists[max] = lists[max].next;
        }
        listNode = listNode.next;

        return listNode;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode listNode = new ListNode(0);
        listNode.next = head;
        ListNode last = listNode;
        if(head==null)return null;
        while(last.next!=null&&last.next.next!=null){
            ListNode temp = last.next;
            ListNode temp1 = last.next;
            temp = temp.next;
            temp1.next = temp.next;
            temp.next = temp1;
            last.next = temp;
            last = last.next.next;
        }
        listNode = listNode.next;
        return listNode;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode listNode = new ListNode(0);
        listNode.next = head;
        ListNode[] temp = new ListNode[k];
        ListNode last  = listNode;

        while(true){
            ListNode check = last;
            boolean end = false;
            for(int n =0;n<k;n++){
                check = check.next;
                temp[n] = check;
                if(check==null){
                    end = true;
                    break;
                }
            }
            if(end){
                break;
            }

            for(int n =0;n<k-1;n++){
                temp[n].next = temp[k-1].next;
            }
            for(int n = k-1;n>0;n--){
                temp[n].next = temp[n-1];
            }
            last.next = temp[k-1];
            for(int n =0;n<k;n++){
                last = last.next;
            }
        }
        listNode = listNode.next;
        return listNode;
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode temp1 = head;
        ListNode temp2 = head;
        for(int n =0;n<k;n++){
            if(temp2==null){
                temp2 = head;
            }
            temp2 = temp2.next;
        }
        while(true){
            if(temp2.next==null){
                break;
            }
            temp2 = temp2.next;
            temp1 = temp1.next;
        }
        ListNode temp3 = temp1.next;
        temp2.next = head;
        temp1.next = null;
        return temp3;

    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null)return null;
        boolean same = false;
        ListNode listNode = new ListNode(0);
        listNode.next = head;
        ListNode temp = listNode;
        while(temp.next.next!=null){
            if(temp.next.val==temp.next.next.val){
                same = true;
                temp.next.next = temp.next.next.next;
            }else{
                if(same){
                    temp.next = temp.next.next;
                    same = false;
                }else{
                    temp = temp.next;
                }
            }
        }
        if(same){
            temp.next = null;
        }
        listNode = listNode.next;
        return listNode;
    }
}
