import java.text.AttributedString;
import java.util.ArrayList;

public abstract class AbstractUsers {
	
	public AbstractUsers() {
		followers.add(this); //add yourself as your own follower
	}
	
    // visitor design pattern 
	public abstract void acceptVisitor(Visitor v);
	
	//returns true upon successfully following user
	public boolean follow(AbstractUsers followThisGuy) {
		if(followThisGuy == null) {
			System.out.println("follow....returned false");
			return false;
		}

		this.following.add(followThisGuy);// add to the array of people you are following
		followThisGuy.addFollower(this);//add yourself as a follower of foolowThisGuy
	    System.out.println("follow....returned true");
	    return true;
	}

	public String getUserID() {
		return this.ID;
	}
	
	public void setUserID(String ID) {
		this.ID = ID;
	}
	
	@Override
    public String toString(){
        return this.ID;
    }
	
	public void display() {
		System.out.println(this.ID + "\nCreated at t = " + creationTime);
		System.out.println("Last Tweet at t = " + lastUpdateTime);
	}
	
	public void addTweet(AbstractUsers root, String tweet) {
		this.tweets.add(tweet);
		root.newsFeed.add(tweet);
		
		for(int x=0; x<followers.size();++x) {
			//add to a ledger of their entire news feed
			((AbstractUsers) followers.get(x)).newsFeed.add(tweet);
			
			lastUpdateTime = System.currentTimeMillis();
		}
	}
	
	
	public void addUsers(UserGroup root, AbstractUsers newUser) {
		root.abstractUsers.add(newUser); // add newUser to tree	
	}
	
	
	public void addToNewsFeed(String someTweet) {
		this.newsFeed.add(someTweet);
	}
	
	public void addFollower(AbstractUsers follower) {
		this.followers.add(follower);	
	}
	
	ArrayList getNewsFeed() {
		return newsFeed;
	}
	
	public boolean hasChildren() {
		return this.hasChildren;
	}
	
	protected long lastUpdateTime;
	protected long creationTime;
	protected boolean hasChildren;
	protected String ID;
	protected ArrayList following = new ArrayList();
	protected ArrayList followers = new ArrayList();
	protected ArrayList<String> tweets = new ArrayList();//personal tweets
	protected ArrayList<String> newsFeed = new ArrayList();//personal + following tweets

	
	
}
