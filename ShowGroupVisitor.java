
public class ShowGroupVisitor implements Visitor {


	@Override
	public void visit(UserGroup group) {
		int numGroups = 0;
		for(int x = 0; x < group.abstractUsers.size(); ++x) {
			if(group.abstractUsers.get(x).toString().startsWith("Group-ID: ")) {
				++numGroups;
			}
		}
		System.out.println(numGroups);

	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

}
