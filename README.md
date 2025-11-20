Description
- This project simulates a star system with a red star in the center and 
black comets moving across the screen. It was built to practice implementing 
different List data structures and using polymorphism in Java.

How to Run
- Compile the files:
- javac *.java
- java MassiveMotion MassiveMotion.txt


What It Does
- Reads settings from a property file (MassiveMotion.txt)
- Creates a star at a specified position
- Generates comets randomly from the edges of the window
- Moves all objects based on their velocities
- Removes comets when they go off screen

List Implementations
The program can use four different types of lists to store celestial bodies:
- ArrayList - uses an array that grows when needed
- LinkedList - singly-linked with next pointers
- DoublyLinkedList - has both next and prev pointers
- DummyHeadLinkedList - linked list with an empty head node

You can switch between them by changing the `list` setting in MassiveMotion.txt.

Configuration File
Edit MassiveMotion.txt to change things like:
- Window size
- How fast things move
- How often comets spawn
- Star position and size
- Which list type to use

Files
- MassiveMotion.java - main program
- CelestialBody.java - represents stars and comets
- List.java - interface for all list types
- ArrayList.java, LinkedList.java, DoublyLinkedList.java, DummyHeadLinkedList.java - different list implementations

Link to the recording:
https://drive.google.com/file/d/1D94bJy7fFhMMEMyBSsri5zpnq0nkqq34/view?usp=sharing