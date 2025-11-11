public class DummyHeadLinkedList<T> implements List<T>{
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

    public DummyHeadLinkedList(){
        head = new Node(null);
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
        Node current = head.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    /**
     * Inserts element at specified index in the list
     * @param index position to insert at
     * @param element element to insert
     */
    @Override
    public void add(int index, T element){
        checkIndexForAdd(index);
        Node temp_node = new Node(element);

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }

        Node next_node = current.next;
        current.next = temp_node;
        temp_node.prev = current;

        temp_node.next = next_node;
        if(next_node != null){
            next_node.prev = temp_node;
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
        Node current = head;
        Node temp_node = new Node(element);

        while(current.next != null){
            current = current.next;
        }
        current.next = temp_node;
        temp_node.prev = current;

        temp_node.next = null;
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
        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        Node temp = current.next;
        current.next = temp.next;

        if(temp.next != null){
            temp.next.prev = current;
        }
        size--;
        return temp.data;
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
