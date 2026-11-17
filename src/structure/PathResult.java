package structure;

import java.util.List;

public class PathResult {
    private int totalTime;
    private List<String> path;

    public PathResult(int totalTime, List<String> path) {
        this.totalTime = totalTime;
        this.path = path;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public List<String> getPath() {
        return path;
    }

    @Override
    public String toString() {
        if (path.isEmpty() || totalTime == Integer.MAX_VALUE) {
            return "No path available.";
        }
        return "Path: " + String.join(" -> ", path) + " | Total time: " + totalTime + " min";
    }
}