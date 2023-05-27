import java.awt.List;
public class RedBlackTree {
    private Node root;
    public boolean add(int value){
        if (root != null){
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BlACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BlACK;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value){
        if(node.value > value) {
            if(node.leftChild != null) {
                boolean result = addNode(node.leftChild, value);
                node.leftChild = rebalance(node.leftChild);
                return result;
            } else {
                node.leftChild = new Node();
                node.leftChild.color = Color.RED;
                node.leftChild.value = value;
                return true;
            }
            else {
                if(node.rightChild != null){
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.color = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BlACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if(result.leftChild != null && result.leftChild.color == Color.RED &&
            result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
            result.rightChild != null && result.rightChild.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }
    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
    private Node leftSwap(Node node){
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }
    private void colorSwap(Node node){
        node.rightChild.color = Color.BlACK;
        node.leftChild.color = Color.BlACK;
        node.color = Color.RED;
    }
    private int value;
    private Color color;
    private Node leftChild;
    private Node rightChild;

    public String toString(){
        return "Node{" +
                "value=" + value +
                ", color=" + color +
                "}";
    }
    private enum Color {
        RED, BlACK
    }
    public class Node{
        int value;
        List<Node> childeren;

    }
}