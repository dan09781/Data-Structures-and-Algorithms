package DataStructureImplementation.Algorithms.GraphAlgorithms.DFS;

import java.util.*;

public class DepthFirstSearchRecursive {
    //Declare the edge class static so we don't have to instantiate the outer class
    private static class Edge{
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }
    }

    //Declare static as well
    public static int dfs(HashMap<Integer,List<Edge>> graph, boolean[] visited, int curNode){
        if (visited[curNode])
            return 0;
        visited[curNode]=true;
        //Counting number of nodes in the graph
        int numNodes = 1;
        List<Edge> neighbours = graph.get(curNode);
        for (Edge edge:neighbours){
            numNodes+=dfs(graph, visited, edge.to);
        }

        return numNodes;
    }

    public static void addVertexToGraph(HashMap<Integer, List<Edge>> graph, int from, int to, int weight){
        Edge newEdge = new Edge(from, to, weight);
        if (graph.containsKey(from))
            graph.get(from).add(newEdge);
        else {
            graph.put(from, new LinkedList<Edge>());
            graph.get(from).add(newEdge);
        }
    }

}
