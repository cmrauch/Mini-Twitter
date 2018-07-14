// composite 
import java.util.ArrayList;
import java.util.Iterator;

public class UserGroup extends AbstractUsers{
	
	
	
	//contains all users and user groups
	public ArrayList abstractUsers = new ArrayList();
	
	public UserGroup(String ID){
		this.ID = ID;	
	}
	
    // finds the ID of the calling object's children
	public AbstractUsers find(String ID) {	
			for(int x=0;x<abstractUsers.size();++x) {
			    if(ID.equals(this.abstractUsers.get(x).toString()))
			        return (AbstractUsers) this.abstractUsers.get(x);
			    }
			}
		return null;
	}
	
	// Visitor design pattern
	public void acceptVisitor(Visitor v) {
		v.visit(this);
	}
	
	//The UserGroup class can add both leafs and composite components
	public void addUsers(AbstractUsers newUser) {
		this.abstractUsers.add(newUser);
		
	}
	
	public String toString(){
            return this.ID;
   }
	

}
