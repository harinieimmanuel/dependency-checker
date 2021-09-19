# dependency-checker
This package is for analyzing the dependencies between elements. This is a plain Java project. This uses Java 11.

## Some notes about implementation

To implement this, I was contemplating on using a HashMap<key, HashSet<String>>  type collection of collection
to hold an element and it's dependencies. But it was so much easier to perceive this as an node containing a
collection of objects of its own type(like a parent with children of the same parent type kind of pattern). So I
decided using the latter approach.

The flow is DependencyMain -> DependencyChecker -> Node model & Exceptions.

The main terms in the Node model are :
- Dependencies - The nodes which the main node depends on
- Dependents - The nodes which depend on the main node
- Key - The name string

The circular dependency is captured before finding out the transitive dependency, becuase
it might end up in an infinite loop while recursively finding out the transitiveDependencies.

The optional extension to find out the dependents of the items is also done. This is achieved using
the transitiveDependencies found in the previous step.

##Inputs:

We could get the input from the command line, I had that code. But changed to just type in the input in the
DependencyMain.java so it was easy to retest and debug, not having to input the entire set every time.
Any new set can be given in the DependencyMain with a try catch.

## Future Scope:

The getTransitiveDependencies can be made synchronised and multiple threads can be implemented to process different
inputs. Right now, this solution is implemented with just one thread.
