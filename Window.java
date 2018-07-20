import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.*; 
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JLabel;
import javax.swing.DebugGraphics;
import java.awt.Font;


public class Window {

	
	static UserGroup User = new UserGroup("Twitter Accounts"); // root
	UserGroup selectedUserNode; 
	DefaultMutableTreeNode top = new DefaultMutableTreeNode(User);
	DefaultTreeModel model = new DefaultTreeModel(top);
	DefaultMutableTreeNode newNode, selectedNode;
	AbstractUsers user,userFrame;
	String userName;
	
	
	
	private JFrame frame;
	private final JTree tree = new JTree(top);


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1104, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tree.setBounds(0, 0, 315, 581);
		frame.getContentPane().add(tree);
		AdminControlPanel admin = AdminControlPanel.Instance();

		tree.getSelectionModel().setSelectionMode
	    (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		// adds a mouse listener
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {    
		    	selectedNode = (DefaultMutableTreeNode)
                        tree.getLastSelectedPathComponent();
		    }
		});
		
		AbstractUsers group = new UserGroup("Twitter Accounts");
		top.setUserObject(group);
		
		JTextArea txtrUserid = new JTextArea();
		txtrUserid.setText("User-ID");
		txtrUserid.setBounds(474, 29, 190, 22);
		frame.getContentPane().add(txtrUserid);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userName = txtrUserid.getText();
				user = new User(userName);
				newNode = new DefaultMutableTreeNode(user,false);
				model = new DefaultTreeModel(top);
	
				//check to see if username already exists
				if(User.find(userName) == null) {
					
				    if(selectedNode != null) { // if clicked  
					        // add new node to JTree
					        selectedNode.add(newNode);
					        // add to the AbstractUser Tree
					        selectedUserNode = (UserGroup) selectedNode.getUserObject();
					        admin.createUser(selectedUserNode,user); 
					
				    }
				    else { // not clicked--> add to root
						    top.add(newNode);
						    admin.createUser(User, user);
				    }
				    tree.setModel(model);
				}

				//tree.setModel(model);	
			}
		});
		btnAddUser.setBounds(840, 29, 85, 22);
		frame.getContentPane().add(btnAddUser);
		
		JTextArea txtrGroupid = new JTextArea();
		txtrGroupid.setText("Group-ID");
		txtrGroupid.setBounds(474, 70, 190, 22);
		frame.getContentPane().add(txtrGroupid);
		
		JButton btnOpenUserView = new JButton("Open User View");
		btnOpenUserView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedNode != null) {// can only open User View if an item is selected
					userFrame = (AbstractUsers) selectedNode.getUserObject();
					userView frame = new userView(User, userFrame);
					frame.setVisible(true);
				}

			}
		});
		
		JButton btnAddGroup = new JButton("Add Group");
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userName = txtrGroupid.getText();
				userName = "Group-ID: " + userName;// unique group label
				user = new UserGroup(userName); //create user object with user input as the name
				newNode = new DefaultMutableTreeNode(user,true); 
				model = new DefaultTreeModel(top); // refresh the tree
			
				
			if(User.find(userName) == null) {
				if(selectedNode != null) { // if clicked
					// add new node to JTree
					selectedNode.add(newNode); 
					// add to the AbstractUser Tree
					selectedUserNode = (UserGroup) selectedNode.getUserObject();
					admin.createUser(selectedUserNode,user); // Add a group to the selected group
				}
				
				else {	    
				    top.add(newNode);
				    admin.createUser(User, user);
				}
				tree.setModel(model); //refresh
			}
		}
		});
		btnAddGroup.setBounds(840, 71, 85, 22);
		frame.getContentPane().add(btnAddGroup);
		btnOpenUserView.setBounds(474, 122, 451, 40);
		frame.getContentPane().add(btnOpenUserView);
		
		JButton btnNewButton = new JButton("Show User Total");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new ShowUserVisitor();
				User.acceptVisitor(v);
			}
		});
		btnNewButton.setBounds(474, 383, 190, 45);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show Group Total");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new ShowGroupVisitor();
				User.acceptVisitor(v);
			}
		});
		btnNewButton_1.setBounds(735, 383, 190, 45);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Show Message Total");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new MessageVisitor();
				User.acceptVisitor(v);
			}
		});
		btnNewButton_2.setBounds(474, 433, 190, 45);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Show Positive Percent");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new PositiveVisitor();
				User.acceptVisitor(v);
			}
		});
		btnNewButton_3.setBounds(735, 433, 190, 45);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblMiniTwitter = new JLabel("Mini Twitter");
		lblMiniTwitter.setFont(new Font("Sylfaen", Font.PLAIN, 31));
		lblMiniTwitter.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
		lblMiniTwitter.setBounds(524, 242, 339, 40);
		frame.getContentPane().add(lblMiniTwitter);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new verifyVisitor();
				User.acceptVisitor(v);
			}
		});
		btnVerify.setBounds(778, 211, 147, 58);
		frame.getContentPane().add(btnVerify);
		
		JButton btnNewButton_4 = new JButton("Find Last Update");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Visitor v = new lastUpdateVisitor();
				User.acceptVisitor(v);
			}
		});
		btnNewButton_4.setBounds(812, 310, 85, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		//tree.setModel(model);	
	
	}
}
