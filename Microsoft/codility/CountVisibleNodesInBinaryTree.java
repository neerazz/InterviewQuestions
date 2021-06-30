import java.util.Stack;

/*


         3  
       1   5 
     5   7 
          9
        11 

 */
public class CountVisibleNodesInBinaryTree {

    public int numberOfVisibleNodes(TreeNode root) {
        if (root == null) return 0;
        int max_value = Integer.MIN_VALUE;
        int count = 0;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, root.val));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            TreeNode node = pair.key;
            if (node.val >= pair.value) {
                if (node.val > max_value)
                    max_value = node.val;
                count++;
            }
            if (node.left != null)
                stack.push(new Pair(node.left, node.val));
            if (node.right != null)
                stack.push(new Pair(node.right, node.val));
        }
        return count;
    }

    private static void test(int actual, int expected) {
        if (actual == expected) {
            System.out.println("PASSED!");
        } else {
            System.out.println(String.format("FAILED! Expected %d, but got: %d", expected, actual));
        }
    }

    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    class Pair {
        TreeNode key;
        Integer value;

        Pair(TreeNode key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
