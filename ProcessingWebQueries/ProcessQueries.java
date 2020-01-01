
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class ProcessQueries {
	public static void main(String[] args) throws IOException {
		int count = Integer.MAX_VALUE;
		if(args.length == 2) {
			String count1 = args[1];
			count = Integer.parseInt(count1);
		}
		//Scanner input = new Scanner(new File("urls-profs"));// Change it to args[0]
		String file = args[0]; //CHANGE!!!!! LOOK UP
		/*ArrayList<WebPageIndex> list = new ArrayList<WebPageIndex>();
		while(input.hasNext()) {//IS 
			String url = input.nextLine();
			list.add(new WebPageIndex(url));
		}*/
		//Loop to let the user enter multiple queries
		boolean flag = false;
		while(flag == false) {
			Scanner input = new Scanner(new File(file));
			ArrayList<WebPageIndex> list = new ArrayList<WebPageIndex>();
			while(input.hasNext()) {//IS 
				String url = input.nextLine();
				list.add(new WebPageIndex(url));
			}
			ArrayList<String> uList = new ArrayList<String>();
			Scanner sc = new Scanner(System.in);
			// Getting the user input
			System.out.print("Enter a query: ");
			String line = sc.nextLine();
			if (line.equals("")) {
				//System.out.println("WE ARE DONE!");//TESTING!!!!!!
				flag = true;
				break;
			}
			System.out.println();//Aesthetics
			Scanner out = new Scanner(line);
			//Break the input into a list of strings
			while(out.hasNext()) {
				uList.add(out.next());
			}
			//System.out.println(uList);//TESTING!!
			//Construct a comparator based on the query
			URLComparator comparator = new URLComparator(uList);
			MyPriorityQueue<WebPageIndex> queue = new MyPriorityQueue<WebPageIndex>(comparator);
			//insert webPageIndex objects into myPriorityQueue
			while(!list.isEmpty()) {
				queue.add(list.remove(list.size()-1)); //IS THERE AN ERROR HERE?
			}
			int i = 0;
			while(queue.size() > 0 && i < count) {// ADD THE TEST FOR COUNT
				WebPageIndex temp = queue.remove();
				//don't print webpages with zero score
				if(comparator.score(temp) == 0)
					continue;
				System.out.println("(Score : " + comparator.score(temp) +") " + temp.getUrl());
				i++;
			}
		}	
	
		}

}
