
public class AdminControlPanel {

// ==== Singleton Implementation of the AdminControlPanel class ===============
	private AdminControlPanel() {
		
	}
	
	public static AdminControlPanel Instance(){
		if(admin == null) {
			admin = new AdminControlPanel();
		}
		return admin;
	}
	
	private static AdminControlPanel admin;
// =============================================================================
	
	public boolean createUser(UserGroup selectedUserNode, AbstractUsers newUser){
		// check if newUser already exists
		if(selectedUserNode.find(newUser.getUserID()) != null) {
			error("User Already Exists");
			return false;
		}
		else {
		    // adds ads newUSer as a child of selectedUserNode
		    selectedUserNode.abstractUsers.add(newUser);
		    //selectedUserNode.addUsers(selectedUserNode, newUser)
		    return true;
		}
	}
// ==============================================================================
	
	 boolean followUser(UserGroup root, AbstractUsers thisUser, String follow) {
		 //check to see if he exists
	    AbstractUsers foundHim = root.find(follow);

		if(thisUser.follow(foundHim)){
			System.out.println("followUser....returned true");
			return true;
		}
		else {
			error("User Does not exist");
			return false;
		}

		    
	}
// ================================================================================
	 public void error(String errorMessage) {
		 if(errorMessage == null) {
			 errorMessage = "Error";
		 }
		 System.out.println(errorMessage);
	 }
	
}
