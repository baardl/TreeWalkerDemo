package  no.baardl;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.Scanner;
import java.util.concurrent.Callable;

@Command(name = "TreeNavigator", mixinStandardHelpOptions = true, version = "1.0",
        description = "Naviger i en trestruktur.")
class TreeNavigator implements Callable<Integer> {

    private Navigation navigation;

    public TreeNavigator() {
        // Opprett en teststruktur
        Node root = new Node(1, "Root");
        Node child1 = new Node(2, "Node A");
        Node child2 = new Node(3, "Node B");
        Node grandchild1 = new Node(4, "Node A1");
        Node grandchild2 = new Node(5, "Node A2");

        root.addLink(child1);
        root.addLink(child2);
        child1.addLink(grandchild1);
        child1.addLink(grandchild2);

        navigation = new Navigation(root);
    }

    @Command(name = "goto", description = "Gå til en undernode.")
    void gotoNode(@Parameters(index = "0", description = "Indeks til noden.") int index) {
        navigation.moveTo(index);
    }

    @Command(name = "up", description = "Gå til parent-node.")
    void goUp() {
        navigation.moveUp();
    }

    @Command(name = "exit", description = "Avslutt programmet.")
    void exit() {
        System.out.println("Avslutter...");
        System.exit(0);
    }

    @Override
    public Integer call() {
        Scanner scanner = new Scanner(System.in);
        CommandLine cmd = new CommandLine(this);

        System.out.println("\nVelkommen til TreeNavigator!");
        navigation.displayCurrentNode();

        while (true) {
            System.out.print("\nSkriv kommando (goto <index> | up | exit): ");
            String input = scanner.nextLine().trim();

            //Repeter loop hvis input er tomt
            if (input.isEmpty()) {
                continue;
            }

            // Hvis brukeren skriver kun et tall, tolk det som "goto <tall>"
            if (input.matches("\\d+")) { // Regex: Sjekker om input er kun tall
                input = "goto " + input;
            }

            if (!input.isEmpty()) {
                cmd.execute(input.split(" "));
            }
        }
    }

    public static void main(String[] args) {
        new CommandLine(new TreeNavigator()).execute(args);
    }
}
