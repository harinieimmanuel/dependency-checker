package com.nab.coding;

import com.nab.coding.exception.CyclicDependencyException;
import com.nab.coding.exception.InvalidInputException;
import com.nab.coding.service.DependencyChecker;

public class DependencyMain {

    public static void main(String[] args) throws InterruptedException {
        try {
            DependencyChecker depChecker = new DependencyChecker();

            System.out.println("Input Set 1");

            depChecker.addDependency("A B C");
            depChecker.addDependency("B C E");
            depChecker.addDependency("C G");
            depChecker.addDependency("D A F");
            depChecker.addDependency("E F");
            depChecker.addDependency("F H");

            System.out.println("Dependencies:");
            depChecker.printDependencies();

            System.out.println("Dependents:");
            depChecker.printDependents();
        } catch (CyclicDependencyException | InvalidInputException e) {
            e.printStackTrace();
        }

        Thread.sleep(500);

        try {
            DependencyChecker depChecker = new DependencyChecker();
            System.out.println("Input Set 2");

            depChecker.addDependency("B C E");
            depChecker.addDependency("C B H G");

            System.out.println("Dependencies:");
            depChecker.printDependencies();

            System.out.println("Dependents:");
            depChecker.printDependents();
        } catch (CyclicDependencyException | InvalidInputException e) {
            e.printStackTrace();
        }

        Thread.sleep(500);

        try {
            DependencyChecker depChecker = new DependencyChecker();
            System.out.println("Input Set 3");

            depChecker.addDependency(" ");
            depChecker.addDependency("A B");

            System.out.println("Dependencies:");
            depChecker.printDependencies();

            System.out.println("Dependents:");
            depChecker.printDependents();
        } catch (CyclicDependencyException | InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
