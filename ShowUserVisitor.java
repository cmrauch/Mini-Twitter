
public class ShowUserVisitor implements Visitor {



	@Override
	public void visit(UserGroup group) {
		
		int numGroups = 0;
		int numUsers = group.abstractUsers.size();
		for(int x = 0; x < group.abstractUsers.size(); ++x) {
			if(group.abstractUsers.get(x).toString().startsWith("Group-ID: ")) {
				++numGroups;
			}
		}
	    numUsers -= numGroups;
		
		System.out.println(numUsers);
		
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}
	

}
