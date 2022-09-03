package com.avaand.app.solve;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Log
@Component
public class FunctionalSolve {

    private final Queue<Node> queue = new LinkedList<>();

    @PostConstruct
    public void init(){

        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);
        Node v4 = new Node(4);

        // Todo : Node/Vertex Index 0
        v0.addNode(v1);
        v0.addNode(v2);
        v0.addNode(v3);

        // Todo : Node/Vertex Index 1
        v1.addNode(v0);
        v1.addNode(v2);

        // Todo : Node/Vertex Index 2
        v2.addNode(v1);
        v2.addNode(v0);
        v2.addNode(v4);

        // Todo : Node/Vertex Index 3
        v3.addNode(v0);

        // Todo : Node/Vertex Index 4
        v4.addNode(v2);

        v0.setVisited(true);
        queue.add(v0);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            List<Node> nodeList = node.getNeighbors();
            System.out.print(node.getValue() + " ");
            for (Node n : nodeList) {
                if (!n.isVisited()) {
                    n.setVisited(true);
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

}