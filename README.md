
# Telekinesis Game: Search Algorithms Implementation

## Problem Description

The problem is to implement a telekinesis game in which a telekinetic agent has the ability to move furniture within a room represented as an n × m grid. The furniture can be either horizontally or vertically placed and occupies two or three grid cells. The agent itself occupies two horizontal cells in the second row from the top of the room and can move left or right. The goal of the game is to create a plan using search algorithms to move the furniture in such a way that the agent can reach the only exit on the right-most cell of the second row.
## Search problem ADT
Search problem represented as 5-tuples
<br>**Actions** (operators):
   In the Telekinesis problem, the set of actions available to the agent consists of moving the furniture pieces either horizontally or vertically within the grid. The agent can move the furniture pieces one step at a time, either to the left, right, up, or down, depending on the orientation of the furniture and the available empty spaces in the grid.

The actions available to the agent can be summarized as follows:

1. Move Furniture Horizontally: If a furniture piece is oriented horizontally, the agent can move it one step to the left or one step to the right, as long as there is an empty space in the corresponding direction.

2. Move Furniture Vertically: If a furniture piece is oriented vertically, the agent can move it one step up or one step down, provided that there is an empty space in the corresponding direction.

3. No Action: The agent can also choose not to move any furniture piece in a given step.

It's important to note that the agent cannot push furniture pieces through obstacles or move them diagonally. Additionally, the agent can only move one furniture piece at a time, and it cannot skip over other furniture pieces.

The goal of the agent is to rearrange the furniture in the grid to create a clear path from the starting position (usually on the left side) to the goal position (usually on the right side) for the agent to reach the target location.

By applying the available actions strategically, the agent can navigate the grid, rearranging the furniture pieces to create a clear path toward the goal state, eventually reaching the target position and solving the Telekinesis problem.
2.**Thw initial state :**
The initial state of the telekinesis game is the starting configuration of the room represented as an n × m grid. The grid contains furniture objects, and the agent is positioned in the room at the beginning of the game.

In the context of the problem description, the initial state consists of the following elements:
                     
1. The room grid: The grid is represented by an n × m matrix, where each cell can be empty, occupied by the agent, or occupied by a piece of furniture. The furniture can be placed either horizontally or vertically and occupies two or three adjacent cells.

2. Agent position: The agent is represented by a two-cell horizontal block, positioned in the second row from the top of the grid. The agent can move left or right within the row.

3. Furniture placement: Furniture objects are placed in different positions on the grid, occupying two or three adjacent cells. The furniture can be oriented either horizontally or vertically.

4. Exit position: There is a single exit on the right-most cell of the second row of the grid. The goal of the game is to move the furniture in such a way that the agent can reach this exit.

The initial state is the starting point from which the search algorithms explore the possible configurations of the room to find a plan for the agent to reach the exit. The search process will involve expanding nodes and generating child states by applying valid moves to the furniture pieces until the goal state is reached or until all possible configurations have been explored.
<br>
<br>
![image](https://github.com/mostaf7583/Telekinesis/blob/mo1/images/WhatsApp%20Image%202023-07-23%20at%2015.33.46.jpg)
**The goal test:** in the context of the telekinesis game is a condition that checks whether the current state represents the goal state. In other words, it determines whether the agent has successfully reached the exit on the right-most cell of the second row.

For the telekinesis game, the goal test can be defined as follows:

1. Check if the agent's position (represented by a two-cell horizontal block) is on the right-most cell of the second row.

If the agent is in the desired position (on the right-most cell of the second row), then the goal test evaluates to true, indicating that the goal state has been achieved. This means that the agent has successfully reached the exit, and the game has been won.

During the search process, the goal test is applied to each state explored by the search algorithms. If the goal test evaluates to true for a particular state, the search process terminates, and the solution is found. On the other hand, if the goal test evaluates to false for all states, the search algorithms continue exploring until a solution is found or all possible configurations have been explored.<br><br><br>
**The path cost**, often denoted as "g(n)", is the cumulative cost of reaching a particular node in the search space from the initial state. In the context of search algorithms, the path cost is the sum of the costs of all the actions taken to reach a specific node in the search tree from the initial state.

When applying search algorithms to find a solution path, the path cost is an essential factor in determining the optimal path. Different actions or moves in the search space may have different costs associated with them. The goal of the search algorithms is to find the path with the lowest total cost, i.e., the path with the minimum path cost.

For example, in the telekinesis game, each move or action taken by the agent to rearrange the furniture may have an associated cost. The path cost for reaching a particular state in the game will be the sum of the costs of all the moves taken to reach that state from the initial configuration of the room.

In summary, the path cost represents the total cost incurred in moving from the initial state to a particular state during the search process. It plays a crucial role in determining the optimality of the solution path found by the search algorithms.
<br>
<br>
![image](https://github.com/mostaf7583/Telekinesis/blob/mo1/images/WhatsApp%20Image%202023-07-23%20at%2015.32.45.jpg)



## Implementation Overview

The implementation involves generating a random grid with furniture, creating a search problem specific to this telekinesis game, and applying various search algorithms to find a winning plan for the agent. The provided implementation of the search-tree node ADT is geared towards solving the telekinesis game using search algorithms like A* and Greedy search.

## Key Aspects and Design Choices

1. **State Representation:**
   - The `Grid` class is used to represent the state of the telekinesis game. It stores the grid configuration, the position of the agent, and the list of furniture objects.
   - The state is converted to a string (`strState`) to facilitate easy comparison and hashing.

2. **Node Hierarchy:**
   - The `Node` class is designed to form a tree-like structure where each node can have multiple children (leaves). Each node represents a particular state in the game.
   - The nodes are linked through the `parentNode` reference, allowing easy traversal from child to parent nodes.

3. **Node Expansion:**
   - The `expand()` method generates child nodes by applying valid moves to the furniture pieces in the current state.
   - If a furniture piece is oriented vertically, two child nodes are generated by moving the furniture up and down.
   - If a furniture piece is oriented horizontally, two child nodes are generated by moving the furniture left and right.
   - This node expansion process explores different possible configurations of the game, creating a search tree.

4. **Goal Test:**
   - The `goaltest()` method checks if the current state represents the goal state. In the context of the telekinesis game, the goal state is when the agent can reach the exit on the right-most cell of the second row.
   - The goal test is an essential part of the search algorithm, as it allows the search process to terminate once the goal state is found.

5. **Heuristic Calculation:**
   - The `setHeuristic()` method calculates heuristic values (`h1` and `h2`) for each node. These heuristics are used in A* and Greedy search algorithms to guide the search towards the goal state efficiently.
   - `h1` estimates the cost from the current state to the goal state by considering the obstacles (furniture) that the agent needs to bypass.
   - `h2` estimates the cost by considering the number of vertical furniture pieces that need to be moved to open the agent's path.

6. **Comparator Classes:**
   - The provided comparator classes (`Sortbycost`, `SortbyaStar_cost`, `Sortbyhersic`) allow sorting nodes based on their costs and heuristic values. These comparators are utilized in priority queues to select the most promising nodes during the search.

7. **Parent-Child Relationship:**
   - Each node maintains a reference to its parent node using the `parentNode` field. This parent-child relationship is crucial for tracing back the path from the goal node to the initial state once the goal is found.
## implementation of the Telekinesis problem.
1. `Grid` Class: The `Grid` class represents the grid environment where the furniture movement takes place. It includes methods for generating the grid, printing the grid in a visual format, moving furniture in different directions (e.g., up, down, left, right), and saving the grid to a file. The grid is represented as a 2D array of integers, where each value indicates the type of cell (e.g., empty cell, agent body, furniture).

2. `Furniture` Class: The `Furniture` class represents individual pieces of furniture that can be moved within the grid. Each furniture object stores information such as its position (x, y), length, and orientation (horizontal or vertical).

3. `Node` Class: The `Node` class is used to represent nodes in the search tree. Each node contains a reference to the current state of the grid, a list of possible successor nodes (leaves), the cost associated with reaching that node, depth in the search tree, and other relevant attributes. The `expand()` method generates successor nodes based on possible movements of the furniture.

4. `Search` Class: The `Search` class contains various search algorithms to solve the Telekinesis problem. The implemented algorithms include Breadth-First Search (BFS), Depth-First Search (DFS), Depth-Limited Search (DLS), Iterative Deepening Search (IDS), Uniform Cost Search (UCS), A* Search, and Greedy Search. These algorithms explore the search space to find a sequence of movements that lead to the goal state.

5. Heuristics: In implementation,  ''  have used two heuristics: `h1()` and `setHeuristic()`. Heuristics are used in informed search algorithms (A* and Greedy Search) to estimate the distance from a node to the goal state. The `h1()` method provides a simple heuristic that returns 0, effectively turning A* and Greedy Search into Uniform Cost Search and BFS, respectively. The `setHeuristic()` method calculates a more sophisticated heuristic that considers the number of obstacles and their positions.

6. Search Algorithms: Each search algorithm in the `Search` class explores the search space by expanding nodes, generating successor nodes, and adding them to the search queue. The algorithms use various data structures (e.g., `ArrayList`, `LinkedList`, `HashSet`) to manage the search process efficiently.

7. Solution: The `Solution` class is used to store the results of each search algorithm, including the expansion sequence, remaining nodes in the queue, the goal node (if found), and a boolean indicating whether a solution exists.

##  implemention  the various search algorithms

1. Breadth-First Search (BFS):
   - we used an `ArrayList` called `queue` to store the nodes to be expanded.
   - we used an `ArrayList` called `uniqueStates` to keep track of unique grid states to avoid duplicates.
   - The `expandSequence` is an `ArrayList` that stores the sequence of nodes expanded during the search process.
   - we applied the BFS strategy by adding newly generated nodes at the end of the queue using `queue.add(newNode)`.
   - we used `queue.remove(0)` to retrieve and remove the first node from the queue (frontier) for expansion.
   - we checked for the goal state using the `node.goaltest()` method.
   - The BFS search process continues until the queue becomes empty.

2. Depth-First Search (DFS):
   - Similar to BFS, we used an `ArrayList` called `queue` to store the nodes to be expanded.
   - we used the `uniqueStates` list to keep track of unique grid states.
   - The `expandSequence` list stores the sequence of nodes expanded during the search process.
   - we applied the DFS strategy by adding newly generated nodes at the beginning of the queue using `queue.add(0, newNode)`.
   - we used `queue.remove(0)` to retrieve and remove the first node from the queue (frontier) for expansion.
   - Like BFS, we checked for the goal state using the `node.goaltest()` method.
   - The DFS search process continues until the queue becomes empty.

3. Depth-Limited Search (DLS):
   - In the `depthLimited` method, we used a `Queue` (specifically, a `LinkedList`) called `queue` to store the nodes to be expanded.
   - we used a `HashSet` called `uniqueStates` to keep track of unique grid states to avoid duplicates.
   - The `expandSequence` is an `ArrayList` that stores the sequence of nodes expanded during the search process.
   - we set a depth limit (`depthLimit`) to control the depth of exploration in the search tree.
   - we applied the DFS strategy with depth limitation by not expanding nodes beyond the specified depth limit.
   - If a node's depth is less than the depth limit, its successors are added to the queue for further exploration.
   - we checked for the goal state using the `node.goaltest()` method.
   - The DLS process continues until the queue becomes empty.

4. Iterative Deepening Search (IDS):
   - In the `iterativeDeepening` method, we iteratively apply the `depthLimited` method with increasing depth limits until a solution is found.
   - The `iterativeDeepening` method returns a `Solution` object with the results of the search, including the expansion sequence and whether a solution was found.
   - By repeatedly performing DLS with increasing depth limits, we ensure that we explore the search space deeper and deeper until a solution is found.

5. Uniform Cost Search (UCS):
   - we used an `ArrayList` called `queue` to store the nodes to be expanded.
   - we used an `ArrayList` called `uniqueStates` to keep track of unique grid states to avoid duplicates.
   - The `expandSequence` is an `ArrayList` that stores the sequence of nodes expanded during the search process.
   - we implemented UCS by sorting the queue based on the cost of nodes using the `Sortbycost` comparator.
   - Nodes with lower costs are expanded first to prioritize paths with lower cumulative costs.
   - The UCS search process continues until the queue becomes empty.

6. A* Search:
   - we used an `ArrayList` called `queue` to store the nodes to be expanded.
   - we used an `ArrayList` called `uniqueStates` to keep track of unique grid states to avoid duplicates.
   - The `expandSequence` is an `ArrayList` that stores the sequence of nodes expanded during the search process.
   - we implemented A* search by sorting the queue based on the A* cost of nodes using the `SortbyaStar_cost` comparator.
   - The A* cost combines the actual cost to reach the node and the estimated cost from the node to the goal state using the heuristic function.
   - Nodes with lower A* costs are expanded first, leading to the exploration of more promising paths.
   - The A* search process continues until the queue becomes empty.

7. Greedy Search:
   - we used an `ArrayList` called `queue` to store the nodes to be expanded.
   - we used an `ArrayList` called `uniqueStates` to keep track of unique grid states to avoid duplicates.
   - The `expandSequence` is an `ArrayList` that stores the sequence of nodes expanded during the search process.
   - we implemented Greedy search by sorting the queue based on the greedy cost of nodes using the `Sortbyhersic` comparator.
   - The greedy cost is based solely on the heuristic function and represents the estimated distance to the goal state.
   - Nodes with lower greedy costs are expanded first, prioritizing nodes that seem to be closer to the goal state.
   - The Greedy search process continues until the queue becomes empty.

## To run the program and interpret the output, follow these steps:

1. Open your Java development environment (e.g., Eclipse, IntelliJ, or Visual Studio Code).

2. Create a new Java project and add the necessary classes (`Grid`, `Furniture`, `Node`, `Search`, and any other supporting classes) to the project.

3. Ensure that you have the `CommandLineTable` class available (you can find this class online or use a library that provides similar functionality). This class is used for visualizing the grid in a tabular format.

4. In the `Grid` class, ensure that the `printGrid()` method is updated with the table visualization code using `CommandLineTable`, as shown in your previous implementation.

5. In the `Grid` class, implement the `moveLeft(Furniture f)` method similar to the `moveRight(Furniture f)` method to move the furniture left.

6. In the `Node` class, make sure the heuristic functions `h1()` and `setHeuristic()` are correctly implemented. These functions should provide meaningful heuristic estimates for the search algorithms (e.g., Manhattan distance to the goal state).

7. In the `Search` class, ensure that the search algorithms (`breadthFirst`, `depthFirst`, `depthLimited`, `iterativeDeepening`, `uniformCost`, `aStar`, and `greedySearch`) are implemented correctly.

8. In the `main` method or a separate class, you can perform the following steps:

   a. Generate a grid using the `Grid.generateGrid()` method.

   b. Create a root node with the generated grid.

   c. Use the search algorithms from the `Search` class to find a solution.

9. To interpret the output, the `Solution` class should provide necessary information about the search process and results. The `Solution` class may contain:

   - The sequence of expanded nodes (`expandSequence`).
   - The list of nodes remaining in the frontier (`queue`) when the goal state is reached.
   - The goal node, indicating whether a solution is found (`solutionExist`).

10. After running the search algorithms, you can print the output to the console. For example:

   a. Print the grid representation of each node in the expanded sequence and the final state.

   b. Display the list of nodes remaining in the frontier when the goal state is reached.

   c. Indicate whether a solution is found or not.

11. To interpret the output, consider the following:

   - If a solution is found, you can analyze the sequence of expanded nodes to understand the search path and the explored states.
   
   - Observe the nodes remaining in the frontier, as they represent potential next steps in the search process.

   - Pay attention to the search algorithm's efficiency in finding a solution. Compare the number of expanded nodes and the search time for different algorithms.

   - Check if the heuristic functions impact the performance of informed search algorithms (A* and Greedy Search).

12. You may experiment with different grid sizes, furniture configurations, and heuristic functions to observe how they affect the search algorithms' behavior and performance.

Remember to handle any exceptions that may arise during the program's execution and ensure that the classes are correctly imported and referenced in the main program. With these steps, you should be able to run the program, visualize the grid, and interpret the output to understand the behavior of the implemented search algorithms on the Telekinesis problem.
###  Example:
```
 public static void main(String[] args) {

        Grid grid = new Grid();
        grid.generateGridTest();

        Node root = new Node(grid);
        
        Search strategy = new Search();
        Solution sol1 = strategy.breadthFirst(root);
        Solution sol2 = strategy.depthFirst(root);
        Solution sol3 = strategy.iterativeDeepening(root);
        Solution sol4 = strategy.uniformCost(root);
        
        sol4.visualizeSolution();


    }
```


.
## Performance Comparison

The performance of various search algorithms was tested and compared based on the number of search tree nodes expanded. The algorithms that were implemented and compared include:

1. Breadth-First Search
2. Depth-First Search
3. Iterative Deepening Search
4. Uniform-Cost Search
5. Greedy Search with Two Heuristics
6. A* Search with Two Admissible Heuristics



## Conclusion

The implementation provides a solid foundation for implementing various search algorithms to find an optimal path for the agent to reach the goal state in the telekinesis game. The use of priority queues and heuristic functions allows for more efficient exploration of the search space, which is essential for solving complex problems.

The performance comparison helps identify the most effective search algorithm for the given telekinesis game, providing valuable insights into the strengths and weaknesses of each approach. With this implementation and analysis, players can enjoy solving the telekinesis game efficiently and find the optimal plan to guide the agent to the exit.
