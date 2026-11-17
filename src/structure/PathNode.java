package structure;

public class PathNode implements Comparable<PathNode> {
    private String vertexId;
    private int accumulatedTime;

    public PathNode(String vertexId, int accumulatedTime) {
        this.vertexId = vertexId;
        this.accumulatedTime = accumulatedTime;
    }

    public String getVertexId() {
        return vertexId;
    }

    public int getAccumulatedTime() {
        return accumulatedTime;
    }

    @Override
    public int compareTo(PathNode other) {
        return Integer.compare(this.accumulatedTime, other.accumulatedTime);
    }
}