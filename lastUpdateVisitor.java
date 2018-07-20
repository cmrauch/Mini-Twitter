
public class lastUpdateVisitor implements Visitor {

	@Override
	public void visit(UserGroup group) {
		String[] name = group.newsFeed.get(group.newsFeed.size()-1).split(" ");
		AbstractUsers id = group.find(name[0]);
		System.out.print("The last person to tweet something was " + id + " at t = " + id.lastUpdateTime);
		
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

}
