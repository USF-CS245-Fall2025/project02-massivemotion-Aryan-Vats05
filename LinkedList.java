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

    /**
     * Inserts element at specified index in the list
     * @param index position to insert at
     * @param element element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        Node new_head = new Node(element);

        if(index == 0){
            new_head.next = head;
            head = new_head;
        }else {
            Node current = head;

            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node temp = current.next;
            current.next = new Node(element);
            current.next.next = temp;
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
        }else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(element);
        }
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
        if(index == 0){
            return head.data;
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
        if(head == null){
            System.out.println("Empty Linked List");
            System.exit(1);
        }
        if(index < 0 || index >= size){
            System.out.println("Invalid Index");
            System.exit(1);
        }
        if(index == 0){
            T head_data = head.data;
            head = head.next;
            size--;
            return head_data;
        }
        Node current = head;
        while(index > 1){
            current = current.next;
            index--;
        }
        Node x = current.next;
        current.next = x.next;
        size--;
        return x.data;
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
