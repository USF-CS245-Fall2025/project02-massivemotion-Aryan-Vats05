public class LinkedList<T> implements List<T>{
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedList(){
        head = null;
        size = 0;
    }

    private void checkIndex(int index, boolean forAdd) {
        if (!forAdd && (index < 0 || index >= size))
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
        Node newNode = new Node(element);
        if(index == 0){
            newNode.next = head;
            head = newNode;
        }else {
            Node prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
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
        if (index == 0) {
            T val = head.data;
            head = head.next;
            size--;
            return val;
        }

        Node prev = getNode(index - 1);
        Node removed = prev.next;
        prev.next = removed.next;
        size--;
        return removed.data;
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
