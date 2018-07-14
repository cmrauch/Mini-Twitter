import java.util.ArrayList;

public class PositiveVisitor implements Visitor {

	

	@Override
	public void visit(UserGroup group) {
		
		double positiveCounter = 0;
		double total;
		
		ArrayList<String> newsFeed = group.getNewsFeed();
		for(int x = 0; x < newsFeed.size(); ++x) {
			
			if((newsFeed.get(x).contains("happy"))   ||
				newsFeed.get(x).contains("awesome")  ||
			    newsFeed.get(x).contains("smile")    ||
                newsFeed.get(x).contains("laugh")    ||
                newsFeed.get(x).contains("cheer")) {
				
			    ++positiveCounter;
			}
		      
		}
		total = newsFeed.size();
		System.out.print((positiveCounter/total)*100);
		System.out.print("% positive messages");
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

}
