package com.nab.coding.service;

import com.nab.coding.exception.CyclicDependencyException;
import com.nab.coding.exception.InvalidInputException;
import com.nab.coding.model.Node;
import java.util.*;

public class DependencyChecker {

    private final Map<String, Node> nodeMap = new HashMap<>();

    public void printDependencies() {
        for (String key : nodeMap.keySet()) {
            String printText = nodeMap.get(key).getDependenciesText();
            if(printText != null) {
                System.out.println(printText);
            }
        }
    }

    public void printDependents() {
        // Update the dependents of each node
        for (String key : nodeMap.keySet()) {
            nodeMap.get(key).updateDependents();
        }

        // Next step is to print the dependents of each node.
        for (String key : nodeMap.keySet()) {
            String printText = nodeMap.get(key).getDependentsText();
            if(printText != null) {
                System.out.println(printText);
            }
        }

    }

    /**
     * If already exists, retrieve the existing node, else create a new one.
     * Upon creation, add the node entry to the nodeMap.
     */
    public Node addNodeEntry(String nodeKey) {
        Node node;
        if(nodeMap.containsKey(nodeKey)) {
            node = nodeMap.get(nodeKey);
        } else {
            node = new Node(nodeKey);
            nodeMap.put(nodeKey, node);
        }
        return node;
    }

    public void addDependency(String dependency) throws CyclicDependencyException, InvalidInputException {
        String[] elements = dependency.split(" ");
        if(elements.length <= 0) {
            throw new InvalidInputException("Input seems invalid. Please re-enter your input");
        }
        String nodeKey = elements[0];

        // Create main node or the key node and add it in the nodeMap.
        // For e.g. if the dep string is A B C, A is the main node and B, C are the dependency nodes.
        Node mainNode = addNodeEntry(nodeKey);

        // Add the dependencies(the nodes which the main node is dependent on) inside the main node
        for (int i = 1; i < elements.length; i++) {
            mainNode.addDependencyNode(addNodeEntry(elements[i]));
        }
    }
}
