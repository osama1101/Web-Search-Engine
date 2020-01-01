
import java.util.*;

class MyPriorityQueue<E> implements PriorityQueueADT<E> {
   
    // you can use either of the following declarations for your heap
   ArrayList<E> heap;
    //E[] heap;

   Comparator<E> comparator;
      
   MyPriorityQueue(Comparator<E> comparator){
      this.comparator = comparator;
      heap = new ArrayList<E>();
      // or
      //heap = (E[]) new Object[/* initial length */];
   }
   
   public boolean add(E item){
	   heap.add(item);
	   siftUp(heap.size()-1);
      return true;
   }
   
   public E remove(){
	   if(heap.size() == 1)
		   return heap.remove(0);
	   E root = heap.get(0);
	   heap.set(0, heap.remove(size()-1));
	   siftDown(0);
	   return root;
   }
   
   public boolean isEmpty(){
	   if(heap.isEmpty())
		   return true;
	   return false;
   }
   
   public int size(){
	   return heap.size();
   }

   public void clear(){
	   heap = new ArrayList<E>();
   }

   public String toString(){
	   String s = "";
	   s += "[";
	   for(int i = 0; i < heap.size(); i++) {
		   if(i != 0) {
			   s += ", ";
		   }
		   s += heap.get(i);
	   }
	   s += "]";
      return s;
   }
   
   private void siftUp(int pos){
	   if(pos == 0)// If the node is the root exit
		   return;
	   if(parent(pos) == -1) // If parent doesn't exist, exit
		   return;
	   //System.out.println(heap.get(pos) + "  Gamd  " + heap.get(parent(pos)));//TEST
	   //System.out.println(comparator.compare(heap.get(pos), heap.get(parent(pos))));//TEST
	   // System.out.println(comparator.compare(heap.get(pos), heap.get(parent(pos))));//TEST
	   if(comparator.compare(heap.get(pos), heap.get(parent(pos))) < 0) {// Node data < parent data
		   return;
	   }
	   else {
		   E temp = heap.get(parent(pos));
		   heap.set(parent(pos), heap.get(pos));
		   heap.set(pos, temp);
		   siftUp(parent(pos));
	   }
   }
         
   public void siftDown(int pos){
	   E largeChild; E lChild; E rChild;
	   E node = heap.get(pos);
	   int largeChildIndex;
	   if((2*pos+1) >= size() && (2*pos+2) >= size()) {
		   return;
	   }
	   if((2*pos+1) >= size() && (2*pos+2) < size()) {
		   rChild = heap.get(2*pos+2);
		   largeChild = rChild;
		   largeChildIndex = 2*pos+2;
	   }
	   else if((2*pos+1) < size() && (2*pos+2) >= size()) {
		   lChild = heap.get(2*pos+1);
		   largeChild = lChild;
		   largeChildIndex = 2*pos+1;
	   }
	   else {
		   rChild = heap.get(2*pos+2);
		   lChild = heap.get(2*pos+1);
		   if(comparator.compare(lChild, rChild) > 0){
			   largeChild = lChild;
			   largeChildIndex = 2*pos+1;
		   }
		   else {
			   largeChild = rChild;
			   largeChildIndex = 2*pos+2;
		   }
	   }
	   if(comparator.compare(node,largeChild) > 0) {
		   return;
	   }
	   else {
		   E temp = node;
		   node = largeChild;
		   largeChild = temp;
		   heap.set(pos, node);
		   heap.set(largeChildIndex, largeChild);
		   siftDown(largeChildIndex);
	   }
	   //TEST THIS BEFORE CONTINUING
      //TODO:  Write this method
   }   

   private int parent(int x){
	   if(((x - 1)/2) < 0)
		   return -1;
	   return (x - 1)/2;
   }
   
   private int leftChild(int x){
	   if(2*x+1 > heap.size() - 1)
		   return -1;
	   return 2*x+1;
   }
   
   private int rightChild(int x){
	   if(2*x+2 > heap.size() - 1)
		   return -1;
	   return 2*x+2;
   }
   
}