import java.util.ArrayList;

public class MessageVisitor implements Visitor{

	

	@Override
	public void visit(UserGroup group) {
		ArrayList<String> newsFeed = group.getNewsFeed();
		
		System.out.print(newsFeed.size());
		
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

}
