// leaf nodes
public class User extends AbstractUsers {

	public User(String userName) {
		this.ID = userName ;
		//followers.add(this); //add yourself as your own follower
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
