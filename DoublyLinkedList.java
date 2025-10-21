public class DoublyLinkedList<T> implements List<T>{
    private class Node{
        T data;
        Node next;
        Node prev;

        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    private Node head;
    private int size;

    public DoublyLinkedList(){
        head = null;
        size = 0;
    }

    /**
     * Inserts element at specified index in the list
     * @param index position to insert at
     * @param element element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            System.out.println("Invalid Index");
            System.exit(1);
        }
        if (index == 0) {
            Node new_node = new Node(element);
            new_node.next = head;
            if(head != null) {
                head.prev = new_node;
            }
            head = new_node;
            size++;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node next_node = current.next;
        Node temp = new Node(element);
        current.next = temp;
        temp.prev = current;

        temp.next = next_node;
        if(next_node != null) {
            next_node.prev = temp;
        }
        size++;
    }
    /**
     * Appends element to the end of the list
     * @param element element to add
     * @return true if successful
     */
    @Override
    public boolean add(T element){
        if(head == null){
            head = new Node(element);
            size++;
            return true;
        }
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        Node x = new Node(element);
        current.next = x;
        x.prev = current;
        size++;
        return true;
    }

    /**
     * Retrieves element at specified index
     * @param index position of element to retrieve
     * @return element at the specified index
     */
    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            System.out.println("Invalid Index");
            System.exit(1);
        }

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes and returns element at specified index
     * @param index position of element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index){
        if(index < 0 || index >= size){
            System.out.println("Invalid Index");
            System.exit(1);
        }

        if(index == 0){
            T output = head.data;
            head = head.next;
            if(head != null) {
                head.prev = null;
            }
            size--;
            return output;
        }

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        T output = current.data;
        Node prev_node = current.prev;
        Node next_node = current.next;

        prev_node.next = next_node;
        if(next_node != null){
            next_node.prev = prev_node;
        }

        size--;
        return output;
    }

    /**
     * Returns the number of elements in the list
     * @return current size of the list
     */
    @Override
    public int size(){
        return size;
    }
}
