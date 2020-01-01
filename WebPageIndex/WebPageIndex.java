
import java.util.*;
import java.io.*;

public class WebPageIndex {
	RBTreeMap<String,List<Integer>> index;
	String URL;
	int wordsCount = 0;
   
   //TODO: Insert the instance variables here

   // WebPageIndex constructor
   WebPageIndex(String url) throws IOException {
	   index = new RBTreeMap<String,List<Integer>>();
	   URL = url;
       HTMLScanner scanner = new HTMLScanner(URL);
       while(scanner.hasNext()) {
    	   String word = scanner.next();
    	   word = word.toLowerCase();
    	   
    	   if(index.get(word) == null) {
    		   LinkedList<Integer> list = new LinkedList<Integer>();
    		   list.add(wordsCount);
    		   index.put(word, list);
    	   }
    	   else {
    		   index.get(word).add(wordsCount);
    		   //index.value.add(wordsCount);
    	   }
    	   wordsCount++;
       }

      // TODO - implement me! - add some comments too!
   }     
   
   // Returns a count of the words in this web page
   public int getWordCount() {
	   return wordsCount;
      // TODO - implement me! - add some comments too!
   }
   
   public String getUrl() {
	   return URL;
      // TODO - implement me! - add some comments too!
   }
   
   public boolean contains(String s) {
	   if(index.get(s) != null) {
		   return true;
	   }
	   return false;
      // TODO - implement me! - add some comments too!
   }
   
   public int getCount(String s) {
	   List<Integer> list = index.get(s);
	   return list.size();
      // TODO - implement me! - add some comments too!
   }
   
   public double getFrequency(String s) {
	   double x = (double) getCount(s)/getWordCount();
	   return x;
      // TODO - implement me! - add some comments too!
   }
   
   public List<Integer> getLocations(String s) {
	   if(index.get(s) == null) {
		   List<Integer> list = new LinkedList<Integer>();
		   return list;
	   }
	   return index.get(s);
      // TODO - implement me! - add some comments too!
   }

   // return an Iterator over all the words in the index
   public Iterator<String> words() {
	   return index.keys();
      // TODO - implement me! - add some comments too!
   }
   
   public String toString() {
	   return index.toString();
      // TODO - implement me! - add some comments too!
   }

   // The main method is an application using a WebPageIndex
   public static void main(String[] args) throws IOException {
	   if(args.length<1){
		    System.out.println("Usage: java TestScanner <url>");
		    System.exit(1);
		}
	   try {
	   String URL = args[0];
	   WebPageIndex site = new WebPageIndex(URL);
	   System.out.println();
	   System.out.println("Frequency and index of words in " + URL);
	   System.out.println();
	   Iterator<String> whatever = site.words(); 
	   while(whatever.hasNext()){
		   String s = whatever.next();
		   System.out.printf("%13s %10.5f %s", s, site.getFrequency(s), site.getLocations(s));
		   //System.out.println(site.getFrequency(s));
		   //System.out.println(site.getLocations(s));
		   System.out.println();
	   }
	   System.out.println();
	   int n = site.index.size();
	   System.out.println("Nodes: " + n);
	   System.out.println("Height: " + site.index.getHeight());
	   //System.out.println(site.containsPhrase("is he"));//HERE! TESTING CONTAINS METHOD!
	   //System.out.println(site.getPhraseCount("is he"));//HERE! TESTING Get Phrase Count METHOD!
	   //System.out.println(site.getPhraseLocations("is he"));//HERE! TESTING Get Phrase location METHOD!
	   //System.out.println(site.getPhraseFrequency("is he"));//HERE! TESTING Get Phrase frequency METHOD!
	   } catch (FileNotFoundException e) {
           System.out.println(e);
	   }

      // TODO - implement me! - add some comments too!
   }

   
   /* 
    * additional features to support multi-word phrases 
    * work on these after you have the previous methods working correctly
    */
   
   public boolean containsPhrase(String s) throws IOException {
	   WebPageIndex site = new WebPageIndex(URL);
	   String [] phrases = s.split(" ");
	   boolean flag = false;
	   LinkedList [] locations = new LinkedList[phrases.length];
	   for(int i = 0; i < phrases.length; i++) {
		   locations[i] = (LinkedList) site.getLocations(phrases[i]);
	   }
	   if(locations[0].size() == 0) {
		   return false;
	   }
	   //System.out.println(locations[0].size() + "yasta");//TESTING!!!!
	   //for(LinkedList x: locations)//TESTING!!!!!!!!!!!
		   //System.out.println(x);//TESTING!!!!!!!
	   for(int i = 0; i < locations.length-1; i++) {
		   if(locations[i].size() == 0) {// IS THERE AN ERROR HERE?
			   return false;
		   }
		   for(int j = 0; j < locations[i].size(); j++) {
			   flag = false;
			   int n = (int) locations[i].get(j);
				   if(locations[i + 1].contains(n+1)) {
					   flag = true;
					   break;
				   }
		   }
		   if(flag == false)
			   return false;
		   
	   }
	   return true;
	   
      // TODO - implement me! - add some comments too!
   }
   
   public int getPhraseCount(String s) throws IOException {
	   if(containsPhrase(s) == false)
		   return 0;
	   //
	   int count = 0;
	   WebPageIndex site = new WebPageIndex(URL);
	   String [] phrases = s.split(" ");
	   boolean flag = false;
	   LinkedList [] locations = new LinkedList[phrases.length];
	   ArrayList<Integer> list = new ArrayList<Integer>();
	   for(int i = 0; i < phrases.length; i++) {
		   locations[i] = (LinkedList) site.getLocations(phrases[i]);
	   }
	   ArrayList<ArrayList> bigList = new ArrayList<ArrayList>();
	   boolean loop = false;
       while(loop == false) {
	   for(int i = 0; i < locations.length-1; i++) {
		   if(locations[i].size() == 0 || locations[i + 1].size() == 0) {
			   loop = true;
			   break;
		   }
		   list = new ArrayList<Integer>();
		   for(int j = 0; j < locations[i].size(); j++) {
			   flag = false;
			   int n = (int) locations[i].get(j);
				   if(locations[i + 1].contains(n+1)) {
					   flag = true;
					   if(!list.contains(n))
					       list.add(n);
					   if(!list.contains(n + 1))
					       list.add(n + 1);
					   locations[i].remove(j);
					   break;
				   }
				   else {
					   locations[i].remove(j);
				   }
		   }
		   if(flag == false)
			   return count;
		   
	   }
	   if(loop == false) {
		   count++;
		   bigList.add(list);
	   }
       }
	   return count;//Chnage this
	   
      // TODO - implement me! - add some comments too!
   }
   
   public double getPhraseFrequency(String s) throws IOException {
	   double n = (double) getPhraseCount(s)/getWordCount();
      // TODO - implement me! - add some comments too!
      return n;
   }

   public List<Integer> getPhraseLocations(String s) throws IOException {
	   if(containsPhrase(s) == false)
		   return new LinkedList<Integer>();
	   //
	   ArrayList<Integer> locationList = new ArrayList<Integer>();
	   int count = 0;
	   WebPageIndex site = new WebPageIndex(URL);
	   String [] phrases = s.split(" ");
	   boolean flag = false;
	   LinkedList [] locations = new LinkedList[phrases.length];
	   ArrayList<Integer> list = new ArrayList<Integer>();
	   for(int i = 0; i < phrases.length; i++) {
		   locations[i] = (LinkedList) site.getLocations(phrases[i]);
	   }
	   ArrayList<ArrayList> bigList = new ArrayList<ArrayList>();
	   boolean loop = false;
       while(loop == false) {
	   for(int i = 0; i < locations.length-1; i++) {
		   if(locations[i].size() == 0 || locations[i + 1].size() == 0) {
			   loop = true;
			   break;
		   }
		   list = new ArrayList<Integer>();
		   for(int j = 0; j < locations[i].size(); j++) {
			   flag = false;
			   int n = (int) locations[i].get(j);
				   if(locations[i + 1].contains(n+1)) {
					   flag = true;
					   if(!list.contains(n))
					       list.add(n);
					   if(!list.contains(n + 1))
					       list.add(n + 1);
					   locations[i].remove(j);
					   break;
				   }
				   else {
					   locations[i].remove(j);
				   }
		   }
		   if(flag == false)
			   return locationList;
		   
	   }
	   if(loop == false) {
		   count++;
		   bigList.add(list);
		   locationList.add(list.get(0));
	   }
       }
	   return locationList;
	   
      // TODO - implement me! - add some comments too!

	   
	   
	   
	   
	   
      // TODO - implement me! - add some comments too!
      //return null;
   }
   
   
}
