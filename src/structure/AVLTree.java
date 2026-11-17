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
        if (n == null)
            return 0;
        return n.height;
    }

    private int getBalance(AVLNode n) {
        if (n == null)
            return 0;
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

        int cmp = key.compareTo(node.centro.getId());
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

        if (balance > 1 && key.compareTo(node.left.centro.getId()) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.centro.getId()) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.centro.getId()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.centro.getId()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public CentroLogistico search(String id) {
        return searchRec(root, id);
    }

    private CentroLogistico searchRec(AVLNode node, String id) {
        if (node == null)
            return null;

        int cmp = id.compareTo(node.centro.getId());
        if (cmp == 0)
            return node.centro;
        if (cmp < 0)
            return searchRec(node.left, id);
        return searchRec(node.right, id);
    }

    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(AVLNode node) {
        if (node != null) {
            printInOrderRec(node.left);
            System.out.print("[" + node.centro.getId() + ": " + node.centro.getNombre() + "] ");
            printInOrderRec(node.right);
        }
    }

    public void printPreOrder() {
        printPreOrderRec(root);
        System.out.println();
    }

    private void printPreOrderRec(AVLNode node) {
        if (node != null) {
            System.out.print("[" + node.centro.getId() + ": " + node.centro.getNombre() + "] ");
            printPreOrderRec(node.left);
            printPreOrderRec(node.right);
        }
    }

    public void printPostOrder() {
        printPostOrderRec(root);
        System.out.println();
    }

    private void printPostOrderRec(AVLNode node) {
        if (node != null) {
            printPostOrderRec(node.left);
            printPostOrderRec(node.right);
            System.out.print("[" + node.centro.getId() + ": " + node.centro.getNombre() + "] ");
        }
    }

    public void delete(String id) {
        if (search(id) == null) {
            System.out
                    .println("[ERROR] No se puede eliminar: El centro con ID '" + id + "' no existe en el Árbol AVL.");
            return;
        }
        root = deleteRec(root, id);
        System.out.println("[INFO] Centro con ID '" + id + "' eliminado exitosamente del Árbol AVL.");
    }

    private AVLNode deleteRec(AVLNode root, String id) {
        if (root == null) {
            return null;
        }

        int comparacion = id.compareTo(root.centro.getId());

        if (comparacion < 0) {
            root.left = deleteRec(root.left, id);
        } else if (comparacion > 0) {
            root.right = deleteRec(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            AVLNode successor = minValueNode(root.right);

            root.centro = successor.centro;
            root.right = deleteRec(root.right, successor.centro.getId());
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
}