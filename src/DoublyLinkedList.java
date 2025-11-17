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

    private void checkIndex(int index, boolean forAdd) {
        if(!forAdd && (index < 0 || index >= size))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        if (forAdd && (index < 0 || index > size))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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
        checkIndex(index, true);
        Node new_node = new Node(element);

        if (index == 0) {
            new_node.next = head;
            if(head != null) {
                head.prev = new_node;
            }
            head = new_node;
            size++;
            return;
        }

        Node prev = getNode(index - 1);
        Node next = prev.next;

        new_node.prev = prev;
        new_node.next = next;

        prev.next = new_node;
        if(next != null) {
            next.prev = new_node;
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
        add(size, element);
        return true;
    }

    /**
     * Retrieves element at specified index
     * @param index position of element to retrieve
     * @return element at the specified index
     */
    @Override
    public T get(int index){
        checkIndex(index, false);
        return getNode(index).data;
    }

    /**
     * Removes and returns element at specified index
     * @param index position of element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index){
        checkIndex(index, false);

        Node target = getNode(index);
        Node prev = target.prev;
        Node next = target.next;

        if(prev != null){
            prev.next = next;
        }
        else{
            head = next;
        }
        if(next != null) {
            next.prev = prev;
        }

        size--;
        return target.data;
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
