package structure;

import model.CentroLogistico;

public class AVLNode {
    CentroLogistico centro;
    String key;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(CentroLogistico centro) {
        this.centro = centro;
        this.key = centro.getId();
        this.height = 1;
    }
}