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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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
        checkIndex(index);
        Node newNode = new Node(element);

        Node prev = (index == 0) ? head : getNode(index - 1);
        Node next = prev.next;

        prev.next = newNode;
        newNode.prev = prev;

        newNode.next = next;
        if(next != null){
            next.prev = newNode;
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
        checkIndex(index + 1);
        return getNode(index).data;
    }

    /**
     * Removes and returns element at specified index
     * @param index position of element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index){
        checkIndex(index + 1);

        Node prev = (index == 0) ? head : getNode(index - 1);
        Node target = prev.next;
        Node next = target.next;

        prev.next = next;
        if (next != null) {
            next.prev = prev;
        }

        size--;

        target.prev = null;
        target.next = null;
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
