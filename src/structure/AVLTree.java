package structure;

import model.CentroLogistico;

public class AVLTree {
    private AVLNode root;

    public AVLTree() {
        this.root = null;
    }

    public AVLNode getRoot() {
        return root;
    }

    private int height(AVLNode n) {
        if (n == null) return 0;
        return n.height;
    }

    private int getBalance(AVLNode n) {
        if (n == null) return 0;
        return height(n.left) - height(n.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(CentroLogistico centro) {
        root = insertRec(root, centro);
    }

    private AVLNode insertRec(AVLNode node, CentroLogistico centro) {
        String key = centro.getId();

        if (node == null) {
            return new AVLNode(centro);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertRec(node.left, centro);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, centro);
        } else {
            System.out.println("[AVISOS AVL] El centro con ID '" + key + "' ya se encuentra registrado.");
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public CentroLogistico search(String id) {
        return searchRec(root, id);
    }

    private CentroLogistico searchRec(AVLNode node, String id) {
        if (node == null) return null;

        int cmp = id.compareTo(node.key);
        if (cmp == 0) return node.centro;
        if (cmp < 0) return searchRec(node.left, id);
        return searchRec(node.right, id);
    }

    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(AVLNode node) {
        if (node != null) {
            printInOrderRec(node.left);
            System.out.print("[" + node.key + ": " + node.centro.getNombre() + "] ");
            printInOrderRec(node.right);
        }
    }
}