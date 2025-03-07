package no.baardl;

import java.util.*;

class Node {
    int id;
    String name;
    List<Node> links = new ArrayList<>();

    Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void addLink(Node child) {
        links.add(child);
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}

