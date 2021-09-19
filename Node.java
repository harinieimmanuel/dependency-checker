package com.nab.coding.model;

import com.nab.coding.exception.CyclicDependencyException;

import java.util.*;
import java.util.stream.Collectors;

public class Node implements Comparable<Node> { // Implementing comparableNode to easily check for Set contains

    String key;

    Set<Node> dependencyNodes = new HashSet<>(); // Using set to keep the dependencies unique

    Set<Node> dependents = new TreeSet<>(); // To maintain sort order and uniqueness

    public Node(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void addDependencyNode(Node n) throws CyclicDependencyException {
        // Capture the cyclic dependencies and throw exception
        if (n.getTransitiveDependencies().contains(this)) {
            String errorText = this.getKey() + " and " + n.getKey() + " have cyclic dependency";
            throw new CyclicDependencyException(errorText);
        }
        else {
            this.dependencyNodes.add(n);
        }
    }

    /**
     * Calculates the transitive dependencies of the dependency nodes
     * @return TreeSet of transitive dependencies
     */
    public Set<Node> getTransitiveDependencies() {
        Set<Node> transitiveDependencies = new TreeSet<>();
        for (Node n : this.dependencyNodes) {
            transitiveDependencies.add(n);
            // Recursively gets all the dependencies, a cyclic one will end up having an infinite loop.
            transitiveDependencies.addAll(n.getTransitiveDependencies());
        }
        return transitiveDependencies;
    }

    public void setDependentNode(Node depNode) {
        this.dependents.add(depNode);
    }

    public String getDependenciesText() {
        Set<Node> transitiveDependencies = getTransitiveDependencies();
        if(transitiveDependencies.isEmpty()) {
            return "";
        }
        return key + " " + getTransitiveDependencies().stream()
                .map(x -> x.key)
                .collect(Collectors.joining(" "));
    }

    public void updateDependents() {
        // Set the dependents
        Iterator<Node> it = getTransitiveDependencies().iterator();
        while(it.hasNext() ) {
            Node n = it.next();
            n.setDependentNode(this);
        }
    }

    public String getDependentsText() {
        if(this.dependents.isEmpty()) {
            return "";
        }
        return key + " " + this.dependents.stream()
                .map(x -> x.key)
                .collect(Collectors.joining(" "));
    }

    @Override
    public int compareTo(Node o) {
        return key.compareTo(o.key);
    }

}
