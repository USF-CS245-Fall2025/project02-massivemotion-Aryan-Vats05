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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for size " + size
            );
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " out of bounds for add operation (size " + size + ")"
            );
        }
    }

    private Node getNode(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current;
    }

    /**
     * Inserts element at specified index in the list
     * @param index position to insert at
     * @param element element to insert
     */
    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
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
        checkIndex(index);
        return getNode(index).data;
    }

    /**
     * Removes and returns element at specified index
     * @param index position of element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index){
        checkIndex(index);
        Node current = getNode(index);

        if (current.prev != null){
            current.prev.next = current.next;
        }
        else{
            head = current.next;
        }

        if(current.next != null){
            current.next.prev = current.prev;
        }

        size--;
        return current.data;
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
