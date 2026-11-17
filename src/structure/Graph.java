package structure;

import model.Ruta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private AVLTree arbolCentros;
    private List<Ruta> rutas;

    public Graph(AVLTree arbolCentros) {
        this.arbolCentros = arbolCentros;
        this.rutas = new ArrayList<>();
    }

    public boolean agregarRuta(String id1, String id2, int tiempo) {
        if (arbolCentros.search(id1) == null || arbolCentros.search(id2) == null) {
            System.out.println("[ERROR] Uno de los centros no existe en el sistema.");
            return false;
        }

        Ruta nuevaRuta = new Ruta(id1, id2, tiempo);
        rutas.add(nuevaRuta);
        return true;
    }

    public void cortarRuta(String id1, String id2) {
        for (Ruta r : rutas) {
            if ((r.getIdOrigen().equals(id1) && r.getIdDestino().equals(id2)) ||
                    (r.getIdOrigen().equals(id2) && r.getIdDestino().equals(id1))) {
                r.setActiva(false);
                System.out.println("[INFO] " + r);
                return;
            }
        }
    }

    public List<Ruta> getRutasAdyacentesActivas(String idCentro) {
        List<Ruta> adyacentes = new ArrayList<>();
        for (Ruta r : rutas) {
            if (r.isActiva()) {
                if (r.getIdOrigen().equals(idCentro) || r.getIdDestino().equals(idCentro)) {
                    adyacentes.add(r);
                }
            }
        }
        return adyacentes;
    }

    public PathResult dijkstra(String idOrigen, String idDestino) {
        if (arbolCentros.search(idOrigen) == null || arbolCentros.search(idDestino) == null) {
            System.out.println("[ERROR] El centro de origen o destino no existe.");
            return new PathResult(Integer.MAX_VALUE, new ArrayList<>());
        }

        Map<String, Integer> tiemposMinimos = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        PriorityQueue<PathNode> pq = new PriorityQueue<>();

        tiemposMinimos.put(idOrigen, 0);
        pq.add(new PathNode(idOrigen, 0));

        while (!pq.isEmpty()) {
            PathNode actual = pq.poll();
            String idActual = actual.getVertexId();

            if (idActual.equals(idDestino)) {
                break;
            }

            if (actual.getAccumulatedTime() > tiemposMinimos.getOrDefault(idActual, Integer.MAX_VALUE)) {
                continue;
            }

            for (Ruta r : getRutasAdyacentesActivas(idActual)) {
                String vecino = r.getIdOrigen().equals(idActual) ? r.getIdDestino() : r.getIdOrigen();
                int nuevoTiempo = tiemposMinimos.get(idActual) + r.getTiempo();

                if (nuevoTiempo < tiemposMinimos.getOrDefault(vecino, Integer.MAX_VALUE)) {
                    tiemposMinimos.put(vecino, nuevoTiempo);
                    predecesores.put(vecino, idActual);
                    pq.add(new PathNode(vecino, nuevoTiempo));
                }
            }
        }

        List<String> camino = new ArrayList<>();
        String paso = idDestino;

        if (!predecesores.containsKey(paso) && !idOrigen.equals(idDestino)) {
            return new PathResult(Integer.MAX_VALUE, new ArrayList<>());
        }

        camino.add(paso);
        while (predecesores.containsKey(paso)) {
            paso = predecesores.get(paso);
            camino.add(0, paso);
        }

        int tiempoTotal = tiemposMinimos.getOrDefault(idDestino, Integer.MAX_VALUE);
        return new PathResult(tiempoTotal, camino);
    }

    public List<String> bfs(String idOrigen) {
        List<String> visitedOrder = new ArrayList<>();

        if (arbolCentros.search(idOrigen) == null) {
            System.out.println("[ERROR] El centro de origen no existe en el sistema.");
            return visitedOrder;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(idOrigen);
        visited.add(idOrigen);

        while (!queue.isEmpty()) {
            String currentId = queue.poll();
            visitedOrder.add(currentId);

            for (Ruta r : getRutasAdyacentesActivas(currentId)) {
                String vecino = r.getIdOrigen().equals(currentId) ? r.getIdDestino() : r.getIdOrigen();

                if (!visited.contains(vecino)) {
                    visited.add(vecino);
                    queue.add(vecino);
                }
            }
        }

        return visitedOrder;
    }

    public void aislarCentro(String idCentro) {
        if (arbolCentros.search(idCentro) == null) {
            System.out.println("[ERROR] El centro '" + idCentro + "' no existe en el sistema.");
            return;
        }

        int rutasDesactivadas = 0;

        for (Ruta r : rutas) {
            if (r.isActiva()) {
                if (r.getIdOrigen().equals(idCentro) || r.getIdDestino().equals(idCentro)) {
                    r.setActiva(false);
                    rutasDesactivadas++;
                }
            }
        }

        System.out.println("[INFO] El centro '" + idCentro + "' ha sido aislado exitosamente. Se desactivaron "
                + rutasDesactivadas + " ruta(s).");
    }
}