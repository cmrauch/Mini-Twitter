
public class verifyVisitor implements Visitor{

	@Override
	public void visit(UserGroup group) {
		//see if all users in tree are unique
		System.out.print("[");
		for(int x =0; x < group.abstractUsers.size(); ++x) {
			if(((AbstractUsers) group.abstractUsers.get(x)).hasChildren() == true) {
				this.visit((UserGroup) group.abstractUsers.get(x));
			}
			for(int y = (x+1); y < (group.abstractUsers.size());++y) {
				//if any two names are the same, output their names
				if(group.abstractUsers.get(x).toString().equals(group.abstractUsers.get(y).toString())){
					System.out.print(group.abstractUsers.get(x).toString());
				}
			}	  
			System.out.println();
		}	
		System.out.print("]");
	}

	@Override
	public void visit(User user) {
		// TODO Auto-generated method stub
		
	}

}
