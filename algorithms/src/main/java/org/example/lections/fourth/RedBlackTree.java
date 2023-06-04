
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T extends Comparable<T>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<T> root;

    private static class Node<T> {
        T value;
        boolean color;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        Node(T value, boolean color, Node<T> left, Node<T> right, Node<T> parent) {
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, RED, null, null, null);
        if (root == null) {
            root = newNode;
            root.color = BLACK;
        } else {
            Node<T> current = root;
            while (true) {
                int cmp = value.compareTo(current.value);
                if (cmp < 0) {
                    if (current.left == null) {
                        current.left = newNode;
                        newNode.parent = current;
                        break;
                    }
                    current = current.left;
                } else if (cmp > 0) {
                    if (current.right == null) {
                        current.right = newNode;
                        newNode.parent = current;
                        break;
                    }
                    current = current.right;
                } else {
                    return; // value already exists in tree
                }
            }
            fixAfterInsertion(newNode);
        }
    }

    private void fixAfterInsertion(Node<T> x) {
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node<T> y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node<T> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    private void setColor(Node<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }

    private Node<T> parentOf(Node<T> node) {
        return node != null ? node.parent : null;
    }

    private Node<T> leftOf(Node<T> node) {
        return node != null ? node.left : null;
    }

    private Node<T> rightOf(Node<T> node) {
        return node != null ? node.right : null;
    }

    private boolean colorOf(Node<T> node) {
        return node != null ? node.color : BLACK;
    }

    private void rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != null)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.right)
            x.parent.right = y;
        else
            x.parent.left = y;
        y.right = x;
        x.parent = y;
    }

    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        if (root == null)
            return result;

        Node<T> current = root;
        while (current.left != null)
            current = current.left;

        while (current != null) {
            result.add(current.value);
            current = successor(current);
        }

        return result;
    }

    private Node<T> successor(Node<T> x) {
        if (x.right != null) {
            Node<T> current = x.right;
            while (current.left != null)
                current = current.left;
            return current;
        } else {
            Node<T> current = x.parent;
            Node<T> child = x;
            while (current != null && child == current.right) {
                child = current;
                current = current.parent;
            }
            return current;
        }
    }
}