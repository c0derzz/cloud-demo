import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/3/19 19:53
 * @Description:
 */
public class Solution {

    public static void main(String[] args) {

        TreeNode node_4 =new TreeNode(4,null ,null );
        TreeNode node_5 =new TreeNode(5,null ,null );
        TreeNode node_3 =new TreeNode(3,null ,node_4 );
        TreeNode node_2 =new TreeNode(2, null, node_5);
        TreeNode root =new TreeNode(1,node_2 , node_3);
        List<Integer> resp = rightSideView(root);
        System.out.println(resp);
    }

    public static List rightSideView(TreeNode root){

        List<Integer> resp = new ArrayList<>();
        loop(root, resp, 0);
        return resp;
    }

    private static void loop(TreeNode node ,List<Integer> resp,int nodeDep){

        if(node == null){
            return;
        }
        if(resp.size() == nodeDep){
            resp.add(node.getValue());
        }
        //放置右节点值
        loop(node.getRight(), resp, nodeDep+1);
    }


}
class TreeNode{
    private int value;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
