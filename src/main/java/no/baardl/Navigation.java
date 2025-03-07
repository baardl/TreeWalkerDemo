package no.baardl;

import java.util.*;

class Navigation {
    private Node currentNode;
    private Stack<Node> history = new Stack<>();

    Navigation(Node root) {
        this.currentNode = root;
    }

    void moveTo(int childIndex) {
        if (childIndex < 0 || childIndex >= currentNode.links.size()) {
            System.out.println("Ugyldig valg. Prøv igjen.");
            return;
        }
        history.push(currentNode);
        currentNode = currentNode.links.get(childIndex);
        displayCurrentNode();
    }

    void moveUp() {
        if (history.isEmpty()) {
            System.out.println("Du er på toppnoden. Kan ikke gå opp.");
            return;
        }
        currentNode = history.pop();
        displayCurrentNode();
    }

    void displayCurrentNode() {
        System.out.println("\nDu er nå på: " + currentNode);
        System.out.println("Sti: " + getCurrentPath());

        if (currentNode.links.isEmpty()) {
            System.out.println("Ingen undernoder.");
        } else {
            System.out.println("Tilgjengelige valg:");
            for (int i = 0; i < currentNode.links.size(); i++) {
                System.out.println(i + ": " + currentNode.links.get(i).name);
            }
        }
    }

    // Ny metode: Henter hele stien fra root til nåværende node
    String getCurrentPath() {
        List<String> path = new ArrayList<>();
        for (Node node : history) {
            path.add(node.name);
        }
        path.add(currentNode.name); // Legger til nåværende node
        return String.join("\\", path);
    }

    Node getCurrentNode() {
        return currentNode;
    }
}
