import com.sun.istack.internal.NotNull;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static void main(String[] args){
      /*  TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(-2);
        root.left.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(-1);*/

        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);

    /*    TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);*/

        System.out.println(new Solution().hasPathSum(root,-2));
    }
    public static int reverseBits(int n) {
        int zheng = 1;
        int fu = Integer.MIN_VALUE;
        int value = 0;
        for(int i = 0;i<32;i++){
            if((n&zheng)!=0) {
                value |= fu;
            }
            zheng<<=1;
            fu>>=1;
        }
        return value;



    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode listNode = new ListNode(0);
        ListNode Temp = listNode;
        if (lists.length == 0) return null;

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
        if (head == null) return null;
        while (last.next != null && last.next.next != null) {
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
        ListNode last = listNode;

        while (true) {
            ListNode check = last;
            boolean end = false;
            for (int n = 0; n < k; n++) {
                check = check.next;
                temp[n] = check;
                if (check == null) {
                    end = true;
                    break;
                }
            }
            if (end) {
                break;
            }

            for (int n = 0; n < k - 1; n++) {
                temp[n].next = temp[k - 1].next;
            }
            for (int n = k - 1; n > 0; n--) {
                temp[n].next = temp[n - 1];
            }
            last.next = temp[k - 1];
            for (int n = 0; n < k; n++) {
                last = last.next;
            }
        }
        listNode = listNode.next;
        return listNode;
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode temp1 = head;
        ListNode temp2 = head;
        for (int n = 0; n < k; n++) {
            if (temp2 == null) {
                temp2 = head;
            }
            temp2 = temp2.next;
        }
        while (true) {
            if (temp2.next == null) {
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
        if (head == null) return null;
        boolean same = false;
        ListNode listNode = new ListNode(0);
        listNode.next = head;
        ListNode temp = listNode;
        while (temp.next.next != null) {
            if (temp.next.val == temp.next.next.val) {
                same = true;
                temp.next.next = temp.next.next.next;
            } else {
                if (same) {
                    temp.next = temp.next.next;
                    same = false;
                } else {
                    temp = temp.next;
                }
            }
        }
        if (same) {
            temp.next = null;
        }
        listNode = listNode.next;
        return listNode;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        try {
            if (nums1.length == 0)
                return nums2.length % 2 == 0 ? (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0 : nums2[nums2.length / 2];
            if (nums2.length == 0)
                return nums1.length % 2 == 0 ? (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0 : nums1[nums1.length / 2];

            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            if (nums1.length == 1 && nums2.length == 1) {
                return (nums1[0] + nums2[0]) / 2.0;
            } else if (nums1.length == 1) {
                if (nums2.length % 2 == 0) {
                    if (nums2[nums2.length / 2] >= nums1[0] && nums2[nums2.length / 2 - 1] <= nums1[0]) {
                        return nums1[0];
                    } else if (nums2[nums2.length / 2] < nums1[0]) {
                        return nums2[nums2.length / 2];
                    } else {
                        return nums2[nums2.length / 2 - 1];
                    }
                } else {
                    if (nums2[nums2.length / 2] == nums1[0]) {
                        return nums1[0];
                    } else if (nums2[nums2.length / 2 + 1] >= nums1[0] && nums2[nums2.length / 2 - 1] <= nums1[0]) {
                        return (nums2[nums2.length / 2] + nums1[0]) / 2.0;
                    } else if (nums1[0] > nums2[nums2.length / 2 + 1]) {
                        return (nums2[nums2.length / 2] + nums2[nums2.length / 2 + 1]) / 2.0;
                    } else {
                        return (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
                    }
                }
            }
            int middle1 = (nums1.length + 1) / 2;
            int middle2 = (nums2.length + 1) / 2;

            if (nums1[nums1.length - 1] <= nums2[0]) {
                int temp = (nums2.length - nums1.length) / 2;
                int last = temp == 0 ? nums1[nums1.length - 1] : nums2[temp - 1];
                return (nums1.length + nums2.length) % 2 == 0 ?
                        (nums2[temp] + last) / 2.0
                        : nums2[temp];
            }
            if (nums2[nums2.length - 1] <= nums1[0]) {
                int temp = (nums2.length + nums1.length) / 2 - 1;
                int last = temp == nums2.length - 1 ? nums1[0] : nums2[temp + 1];
                return (nums1.length + nums2.length) % 2 == 0 ?
                        (last + nums2[temp]) / 2.0
                        : nums2[(nums2.length + nums1.length) / 2];
            }
            int preventOverFlow = nums1[middle1];
            while (!(preventOverFlow >= nums2[middle2 - 1] && nums1[middle1 - 1] <= nums2[middle2])) {
                if (nums1[middle1] <= nums2[middle2 - 1]) {
                    middle2--;
                    middle1++;
                } else {
                    middle1--;
                    middle2++;
                }
                if (middle1 == nums1.length) {
                    preventOverFlow = nums1[nums1.length - 1];
                } else {
                    preventOverFlow = nums1[middle1];
                }
            }


            if ((nums1.length + nums2.length) % 2 == 0) {
                return (Math.max(nums1[middle1 - 1], nums2[middle2 - 1]) +
                        middle1 >= nums1.length ? nums2[middle2] : Math.min(nums1[middle1], nums2[middle2])) / 2.0;
            } else {
                return Math.max(nums1[middle1 - 1], nums2[middle2 - 1]);
            }
        } catch (Exception e) {
            return 0.0f;
        }


    }

    public int[] nextLargerNodes(ListNode head) {
        LinkedList<ListNode> listNodes = new LinkedList<>();
        ListNode temp = head;
        if (head == null) return null;

        int smallestNumber = temp.val;
        int length = 0;
        while (temp != null) {
            length++;
            if (temp.val <= smallestNumber) {
                listNodes.add(temp);
                smallestNumber = temp.val;
            } else {
                smallestNumber = Integer.MAX_VALUE;
                for (int n = 0; n < listNodes.size(); n++) {
                    if (temp.val > listNodes.get(n).val) {
                        listNodes.get(n).val = temp.val;
                        listNodes.remove(n--);
                        continue;
                    }
                    if (listNodes.get(n).val < smallestNumber) {
                        smallestNumber = listNodes.get(n).val;
                    }
                }
                if (smallestNumber == Integer.MAX_VALUE) {
                    smallestNumber = temp.val;
                }
                listNodes.add(temp);
            }
            temp = temp.next;
        }

        while (listNodes.size() != 0) {
            listNodes.get(0).val = 0;
            listNodes.remove(0);
        }

        int[] returnArray = new int[length];
        temp = head;
        int n = 0;
        while (temp != null) {
            returnArray[n] = temp.val;
            n++;
            temp = temp.next;
        }
        return returnArray;

    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<TreeNode> rightTreeNodes = new ArrayList<TreeNode>();
        boolean back = false;
        boolean rightSide = false;

        TreeNode temp = root;

        while (true) {
            if (temp.left == null) {
                if (temp.right == null) {
                    values.add(temp.val);
                    TreeNode temp1 = temp;
                    while (rightTreeNodes.size() != 0 && rightTreeNodes.get(rightTreeNodes.size() - 1).right == temp1) {
                        temp1 = rightTreeNodes.get(rightTreeNodes.size() - 1);
                        values.add(rightTreeNodes.get(rightTreeNodes.size() - 1).val);
                        rightTreeNodes.remove(rightTreeNodes.size() - 1);
                    }
                    if (treeNodes.size() == 0) {
                        while (rightTreeNodes.size() != 0) {
                            values.add(rightTreeNodes.get(rightTreeNodes.size() - 1).val);
                            rightTreeNodes.remove(rightTreeNodes.size() - 1);
                        }
                        break;
                    }
                    temp = treeNodes.get(treeNodes.size() - 1);
                    treeNodes.remove(temp);

                    back = true;
                } else {
                    rightTreeNodes.add(temp);
                    rightSide = true;
                    temp = temp.right;
                }
                continue;
            }
            if (back) {
                if (temp.right == null) {
                    values.add(temp.val);
                    TreeNode temp1 = temp;
                    while (rightTreeNodes.size() != 0 && rightTreeNodes.get(rightTreeNodes.size() - 1).right == temp1) {
                        temp1 = rightTreeNodes.get(rightTreeNodes.size() - 1);
                        values.add(rightTreeNodes.get(rightTreeNodes.size() - 1).val);
                        rightTreeNodes.remove(rightTreeNodes.size() - 1);
                    }
                    if (treeNodes.size() == 0) {
                        while (rightTreeNodes.size() != 0) {
                            values.add(rightTreeNodes.get(rightTreeNodes.size() - 1).val);
                            rightTreeNodes.remove(rightTreeNodes.size() - 1);
                        }
                        break;
                    }
                    temp = treeNodes.get(treeNodes.size() - 1);
                    treeNodes.remove(temp);
                    rightSide = false;

                } else {
                    back = false;
                    rightTreeNodes.add(temp);
                    temp = temp.right;
                    rightSide = true;
                }
                continue;
            }

            if (temp.left != null) {
                treeNodes.add(temp);
                temp = temp.left;
                rightSide = false;
            }


        }
        return values;
    }

    /**
     * 对于任意一个二叉树
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;
        return translate(root, levelOfTwoNode(root, p, q));
    }

    /**
     * 任意一个二叉搜索树
     */
    public TreeNode lowestSearchCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val < root.val) {
            return root;
        } else if (p.val < root.val && q.val > root.val) {
            return root;
        } else if (p.val == root.val || q.val == root.val) {
            return root;
        }
        if (p.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }


    /**
     * 返回字符串数组
     * 其中0代表在左子树
     * 1代表在右子树
     */
    private String[] levelOfTwoNode(TreeNode root, TreeNode p, TreeNode q) {
        String[] nodePosition = new String[2];
        boolean[] isFound = new boolean[2];
        isFound[0] = isFound[1] = false;
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<TreeNode> Stack = new LinkedList<TreeNode>();
        TreeNode temp = root;
        LinkedList<String> tempString = new LinkedList<>();
        while (true) {

            if (!isFound[0] && temp.val == p.val) {
                nodePosition[0] = stringBuilder.toString();
                isFound[0] = true;
            }
            if (!isFound[1] && temp.val == q.val) {
                nodePosition[1] = stringBuilder.toString();
                isFound[1] = true;
            }
            if (isFound[0] && isFound[1]) break;

            if (temp.left == null && temp.right == null) {
                temp = Stack.pop().right;
                stringBuilder.replace(0, stringBuilder.length(), tempString.pop());
                stringBuilder.append(1);
            } else if (temp.left == null) {
                temp = temp.right;
                stringBuilder.append(1);
            } else if (temp.right == null) {
                temp = temp.left;
                stringBuilder.append(0);
            } else {
                Stack.push(temp);
                temp = temp.left;
                tempString.push(stringBuilder.toString());
                stringBuilder.append(0);
            }
        }
        return nodePosition;


    }

    private TreeNode translate(TreeNode root, String[] position) {
        TreeNode temp = root;
        int min = position[0].length() > position[1].length() ? position[1].length() : position[0].length();
        int end = 1;
        if (position[0].charAt(0) != position[1].charAt(0)) {
            return root;
        }


        for (int n = 0; n < min; n++) {
            if (position[0].charAt(n) != position[1].charAt(n)) {
                end = n;
                break;
            }
        }
        String s = position[0].substring(0, end);
        for (int n = 0; n < s.length(); n++) {
            if (s.charAt(n) == '0') {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        return temp;
    }

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> path = new LinkedList<>();
        int targetLayer = 0;
        while (true) {
            if (label >= pow(targetLayer) && label < pow(targetLayer + 1)) {
                break;
            } else {
                targetLayer++;
            }
        }
        boolean isLeft = targetLayer % 2 == 0;
        int position = 0;
        if (isLeft) {
            position = label - pow(targetLayer);
        } else {
            position = pow(targetLayer + 1) - label - 1;
        }
        for (int n = 0; n < targetLayer; n++) {
            boolean isLeft1 = n % 2 == 0;
            if (isLeft1) {
                path.add(pow(n) + position / pow(targetLayer - n));
            } else {
                path.add(pow(n + 1) - position / (pow(targetLayer - n)) - 1);
            }
        }
        path.add(label);
        return path;

    }

    private int pow(int mi) {
        return 1 << mi;
    }

    public List<TreeNode> delNodes(@NotNull TreeNode root, @NotNull int[] to_delete) {

        TreeNode[] fatherNode = new TreeNode[to_delete.length];
        TreeNode[] deleteNode = new TreeNode[to_delete.length];

        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int n = 0; n < to_delete.length; n++) {
            hashSet.add(to_delete[n]);
        }
        ArrayList<TreeNode> Queue = new ArrayList<TreeNode>();
        int number = 0;
        if (hashSet.contains(root.val)) {
            fatherNode[number] = null;
            deleteNode[number++] = root;
        }
        Queue.add(root);
        while (true) {
            TreeNode[] list = new TreeNode[Queue.size()];
            for (int n = 0; Queue.size() != 0; n++) {
                list[n] = Queue.remove(Queue.size() - 1);
            }
            for (TreeNode t : list) {
                if (t.left != null) {
                    if (hashSet.contains(t.left.val)) {
                        fatherNode[number] = t;
                        deleteNode[number++] = t.left;
                    }
                    Queue.add(t.left);
                }
                if (t.right != null) {
                    if (hashSet.contains(t.right.val)) {
                        fatherNode[number] = t;
                        deleteNode[number++] = t.right;
                    }
                    Queue.add(t.right);
                }
            }
            if (Queue.isEmpty()) {
                break;
            }
        }
        ArrayList<TreeNode> value = new ArrayList<TreeNode>();
        for (int n = to_delete.length - 1; n >= 0; n--) {

            if (fatherNode[n] == null) {
                if (deleteNode[n].right == null && deleteNode[n].left == null) {
                    break;
                }
                if (deleteNode[n].right == null) {
                    value.add(deleteNode[n].left);
                } else if (deleteNode[n].left == null) {
                    value.add(deleteNode[n].right);
                } else {
                    value.add(deleteNode[n].left);
                    value.add(deleteNode[n].right);
                }
                break;
            }
            boolean isLeft = fatherNode[n].left != null && fatherNode[n].left.val == deleteNode[n].val;
            if (deleteNode[n].left == null && deleteNode[n].right == null) {
                if (isLeft) {
                    fatherNode[n].left = null;
                } else {
                    fatherNode[n].right = null;
                }
                continue;
            } else if (deleteNode[n].left == null) {
                value.add(deleteNode[n].right);
                if (isLeft) {
                    fatherNode[n].left = null;
                } else {
                    fatherNode[n].right = null;
                }
            } else if (deleteNode[n].right == null) {
                value.add(deleteNode[n].left);
                if (isLeft) {
                    fatherNode[n].left = null;
                } else {
                    fatherNode[n].right = null;
                }
            } else {
                value.add(deleteNode[n].left);
                value.add(deleteNode[n].right);
                if (isLeft) {
                    fatherNode[n].left = null;
                } else {
                    fatherNode[n].right = null;
                }
            }
        }

        if (fatherNode[0] != null) {
            value.add(root);
        }

        return value;


    }

    public void flatten1(TreeNode root) {
        TreeNode treeNode = new TreeNode();
        TreeNode tempTreeNode = treeNode;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        while (true) {

            if (temp == null) {
                if (stack.empty()) {
                    break;
                }
                temp = stack.pop().right;
                continue;
            }
            tempTreeNode.right = new TreeNode(temp.val);
            tempTreeNode = tempTreeNode.right;
            if (temp.left == null && temp.right == null) {
                if (stack.empty()) {
                    break;
                }
                temp = stack.pop().right;
            } else if (temp.left == null) {
                temp = temp.right;
            } else {
                stack.push(temp);
                temp = temp.left;
            }

        }
        root.left = null;
        root.right = treeNode.right.right;

    }

    public void flatten2(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.left == null) {
            flatten2(root.right);
        }

        try {
            TreeNode rootLeft = root.left;
            TreeNode rootRight = root.right;
            TreeNode leftTemp = rootLeft;
            root.right = root.left;
            root.left = null;

            if (root.right.left != null) {
                flatten2(root.right);
            }
            if (rootRight.left != null) {
                flatten2(rootRight);
            }
            while (leftTemp.right != null) {
                leftTemp = leftTemp.right;
            }
            leftTemp.right = rootRight;
        } catch (Exception e) {
            return;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                return true;
            } else return false;
        }
        ArrayList<TreeNode> Stack = new ArrayList<>();
        ArrayList<Integer> minusNumber = new ArrayList<>();
        minusNumber.add(0);
        /*
        is going back
         */
        boolean flag = true;
        int tempSum = root.val;
        while (true) {
            /*
             continue to the left
             */
            try {
                if (root.left != null && flag) {
                    Stack.add(root);
                    root = root.left;
                    tempSum += root.val;
                    minusNumber.add(root.val);
                } else {
                /*
                if it is continue left
                 */
                    if (flag) {
                      //  tempSum += root.val;
                        /*
                    if it is reach the bottom
                    two case
                    1.leaf node
                    check whether true
                    go back
                     */
                        if (root.right == null) {
                            if (tempSum == sum) {
                                return true;
                            }
                            if (Stack.size() == 0 || minusNumber.size() == 0) {
                                break;
                            }
                            flag = false;
                            tempSum -= minusNumber.remove(minusNumber.size() - 1);
                            root = Stack.remove(Stack.size() - 1);
                        }
                    /*
                     2.still have right node
                     */
                        else {
                            root = root.right;
                            tempSum += root.val;
                            if(minusNumber.size()==0){
                                minusNumber.add(root.val);
                            }else {
                                minusNumber.add(minusNumber.remove(minusNumber.size() - 1) + root.val);
                            }
                        }
                    }
                /*
                going back
                 */
                    else {
                    /*
                    go back again
                     */
                        if (root.right == null) {
                            tempSum -= minusNumber.remove(minusNumber.size() - 1);
                            root = Stack.remove(Stack.size() - 1);
                        }
                    /*
                    go right
                     */
                        else {
                            flag = true;
                            root = root.right;
                            tempSum += root.val;
                            if(minusNumber.size()!=0)
                            minusNumber.add(minusNumber.remove(minusNumber.size() - 1) + root.val);
                        }
                    }

                }
            }catch (Exception e ){
                return false;
            }
        }
        return false;
    }




}
      class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(){}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }

          @Override
          public String toString() {
              return "[" +
                      " " + left +
                      " " + val +
                      " " + right +
                      ']';
          }
      }
