public class ArrayList<T> implements List<T> {
    Object[] array_list;
    int size, capacity;

    public ArrayList() {
        array_list = new Object[10];
        size = 0;
        capacity = 10;
    }

    /**
     * Inserts element at specified index in the list
     * @param index position to insert at
     * @param element element to insert
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            System.out.println("Invalid index given!");
            System.exit(1);
        }

        if (size == capacity) {
            capacity *= 2;
            Object[] array_list2 = new Object[capacity];
            System.arraycopy(array_list, 0, array_list2, 0, size);
            array_list = array_list2;
        }

        for (int i = size - 1; i >= index; i--) {
            array_list[i + 1] = array_list[i];
        }
        array_list[index] = element;
        size += 1;
    }

    /**
     * Appends element to the end of the list
     * @param element element to add
     * @return true if successful
     */
    @Override
    public boolean add(T element){
        if(size == capacity){
            capacity *= 2;
            Object[] new_arr = new Object[capacity];
            System.arraycopy(array_list, 0, new_arr, 0, size);
            array_list = new_arr;
        }
        array_list[size++] = element;
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
        return (T) array_list[index];
    }

    /**
     * Removes and returns element at specified index
     * @param index position of element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index){
        if (index < 0 || index >= size) {
            System.out.println("Invalid index");
            System.exit(1);
        }

        T element = (T) array_list[index];
        for(int i = index; i < size - 1; i++){
            array_list[i] = array_list[i + 1];
        }
        array_list[size - 1] = null;
        size--;
        return element;
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
