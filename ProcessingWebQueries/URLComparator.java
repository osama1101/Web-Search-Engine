
/**
 * This class contains the code to compute the relevance score of a
 * web page, in response to a query.  It also contains a method to
 * compare the scores of two web pages.
 *
 * @author John Donaldson (Spring 2016)
 */

import java.util.*;

class URLComparator implements Comparator<WebPageIndex> {

   List<String> query;
   
   URLComparator(List<String> query){
      this.query = query;
   }
   
   public int score(WebPageIndex idx){
	   int sum = 0;
	   for(String x : query) {
		   sum += idx.getCount(x);
	   }
	   return sum;
   }
      
    public int compare(WebPageIndex idx1, WebPageIndex idx2){
    	if(score(idx1) > score(idx2))
    		return 1;
    	if(score(idx1) == score(idx2))
    		return 0;
    	else
    		return -1; 
    }

}
