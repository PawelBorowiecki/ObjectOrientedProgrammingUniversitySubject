import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList {
    private class Node{
        Node next;
        public T value;

        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    private Node head, tail;
    private int size = 0;

    public CustomList(){
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        Node n = head;
        for(int i = 0; i < index; i++){
            n = n.next;
            if(n == null){
                throw new NoSuchElementException("Index out of bounds");
            }
        }
        return n.value;
    }

    public void addLast(T value){
        if(tail == null){
            assert head == null;        //assert sprawdza, czy head tez jest null. Jest to "czytelniejszy" if
            tail = new Node(value);
            head = tail;
        }else{
            tail.next = new Node(value);
            head = tail.next;
        }
        size++;
    }

    public T getLast(){
        if(tail == null) throw new NoSuchElementException("List is empty");
        return tail.value;
    }

    public void removeLast(){
        if(tail == null) throw new NoSuchElementException("Trying to remove element which doesn't exist");
        assert head != null;
        if(head == tail){
            head = tail = null;
        }else {
            Node n = head;
            while (n.next != tail) {
                n = n.next;
            }

            tail = n;
            n.next = null;
        }
        size--;
    }

    public void addFirst(T value){
        if(head == null){
            assert tail == null;
            head = new Node(value);
            tail = head;
        }else{
            Node newHead = new Node(value);
            newHead.next = head;
            head = newHead;
        }
        size++;
    }

    public T getFirst(){
        if(head == null) throw new NoSuchElementException("List is empty");
        return head.value;
    }

    public void removeFirst(){
        if(head == null) throw new NoSuchElementException("Trying to pop from empty list");
        head = head.next;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node n = head;
            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public T next() {
                T result = n.value;
                n = n.next;
                return result;
            }
        };
    }

    @Override
    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();   //builder() jest metoda wytworcza
        for(T i : this){
            builder.accept(i);
        }
        return builder.build();
    }

//    public T removeFirst() {
//        if (head == null) throw new NoSuchElementException("Trying to remove element from empty list");
//        T result = head.value;
//        head = head.next;
//        size--;
//        return result;
//    }

    @Override
    public String toString() {
        if(head == null) return "CustomList []";

        StringBuilder result = new StringBuilder("CustomList [");
        Node n = head;
        while(n != null){
            result.append(n.value).append(" ");
            n = n.next;
        }
        result.append("] size = ");
        result.append(size);

        return result.toString();
    }

    public static <S>List<S> filterByClass(List<S> list, Class<?> cls){
//        CustomList<S> output = new CustomList<>();
//        for(S elem : list){
//            if(cls.isInstance(elem)){
//                output.addLast(elem);
//            }
//        }
//        return output;
        return list.stream()
                .filter(cls::isInstance)
                .toList();
    }
}
