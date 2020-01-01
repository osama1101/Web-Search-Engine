import java.util.*;

interface PriorityQueueADT<T> {
  public boolean isEmpty();
  public int size();
  public void clear();
  public T remove();
  public boolean add(T item);
}
