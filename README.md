# Sistema de Gestión de Red Logística - Trabajo Práctico de Algoritmos y Estructuras de Datos II

Sistema desarrollado en **Java** para la gestión y optimización de una red de centros logísticos y rutas de transporte, implementando estructuras de datos avanzadas y algoritmos de grafos desde cero.


## Tecnologías y Estructuras Utilizadas
* **Lenguaje:** Java (Programación Orientada a Objetos).
* **Estructuras de Datos:** 
  * Árbol AVL (con control de alturas y factores de balance).
  * Grafos basados en listas de adyacencia / aristas unificadas.
  * Colas (`Queue`) y Conjuntos (`Set`) para recorridos.
* **Algoritmos:** Dijkstra, BFS (Breadth-First Search), Rotaciones AVL (Simple Izquierda/Derecha, Doble Izquierda-Derecha / Derecha-Izquierda).


## Estructura del Proyecto

```text
src/
│
├── model/
│   ├── CentroLogistico.java      # Entidad que representa un centro de la red
│   └── Ruta.java                 # Entidad que representa una conexión entre centros
│
├── structure/
│   ├── AVLNode.java              # Nodo para el Árbol AVL
│   ├── AVLTree.java              # Implementación del Árbol AVL y sus recorridos
│   ├── Graph.java                # Implementación del Grafo, Dijkstra, BFS y Aislamiento
│   ├── PathNode.java             # Estructura auxiliar para reconstrucción de caminos
│   └── PathResult.java           # Resultado detallado de Dijkstra (ruta y costo total)
│
└── Main.java                     # Clase principal con casos de prueba y validaciones
```
