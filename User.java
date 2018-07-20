// leaf nodes
public class User extends AbstractUsers {

	public User(String userName) {
		this.ID = userName ;
		hasChildren  = false;
		creationTime = System.currentTimeMillis();
	}

	@Override
	public void acceptVisitor(Visitor v) {
		v.visit(this);	
	}
	
	@Override
	public String toString(){
		return this.ID;
	}
	
	public String getUserID() {
		return this.ID;
	}
	
}
