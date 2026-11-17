package structure;

import model.CentroLogistico;

public class AVLNode {
    CentroLogistico centro;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(CentroLogistico centro) {
        this.centro = centro;
        this.height = 1;
    }
}