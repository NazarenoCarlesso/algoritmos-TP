import model.CentroLogistico;
import structure.AVLTree;
import structure.Graph;
import structure.PathResult;

public class Main {
    public static void main(String[] args) {
        AVLTree arbolCentros = new AVLTree();
        Graph grafo = new Graph(arbolCentros);

        arbolCentros.insert(new CentroLogistico("A", "Centro A"));
        arbolCentros.insert(new CentroLogistico("B", "Centro B"));
        arbolCentros.insert(new CentroLogistico("C", "Centro C"));
        arbolCentros.insert(new CentroLogistico("D", "Centro D"));
        arbolCentros.insert(new CentroLogistico("E", "Centro E"));
        arbolCentros.insert(new CentroLogistico("F", "Centro F"));

        grafo.agregarRuta("A", "B", 4);
        grafo.agregarRuta("A", "C", 2);
        grafo.agregarRuta("B", "C", 1);
        grafo.agregarRuta("B", "D", 5);
        grafo.agregarRuta("C", "D", 8);
        grafo.agregarRuta("C", "E", 10);
        grafo.agregarRuta("D", "E", 2);
        grafo.agregarRuta("D", "F", 6);
        grafo.agregarRuta("E", "F", 3);

        System.out.println("Calculando el camino óptimo desde A hasta F en una red compleja...");
        PathResult resultado = grafo.dijkstra("A", "F");
        System.out.println("\n--- RESULTADO OBTENIDO ---");
        System.out.println(resultado);
        System.out.println("\n[AVISO] El tiempo obtenido fue: " + resultado.getTotalTime());
        System.out.println();

        java.util.List<String> ordenBfs = grafo.bfs("A");
        System.out.println("Orden de visita (por niveles de saltos): " + String.join(" -> ", ordenBfs));
        System.out.println("Cantidad de centros alcanzados desde A: " + ordenBfs.size());
        System.out.println();

        grafo.aislarCentro("C");
        System.out.println("Intentando recalcular el camino mínimo de A a F sin el centro C...");
        PathResult resultadoPostAislamiento = grafo.dijkstra("A", "F");

        System.out.println(resultadoPostAislamiento);
        System.out.println();

        System.out.print("Árbol antes de eliminar ('B'): ");
        arbolCentros.printInOrder();
        System.out.println();
        arbolCentros.delete("B");
        System.out.print("Árbol después de eliminar ('B'): ");
        arbolCentros.printInOrder();
        System.out.println();

        System.out.print("In-Order: ");
        arbolCentros.printInOrder();

        System.out.print("Pre-Order: ");
        arbolCentros.printPreOrder();

        System.out.print("Post-Order: ");
        arbolCentros.printPostOrder();
    }
}