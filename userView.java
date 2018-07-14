import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class userView extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public userView(UserGroup root, AbstractUsers selectedUser) {
		selectedUser.display();
		setBounds(100, 100, 532, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		AdminControlPanel admin = AdminControlPanel.Instance();
		
		DefaultListModel model = new DefaultListModel();
		DefaultListModel model_1 = new DefaultListModel();
		
		JTextArea textArea = new JTextArea("User ID...");
		textArea.setBounds(35, 36, 156, 35);
		contentPane.add(textArea);
		

		JTextArea txtrTweetMessage = new JTextArea();
		txtrTweetMessage.setText("Tweet Message");
		txtrTweetMessage.setBounds(35, 305, 177, 35);
		contentPane.add(txtrTweetMessage);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Default"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setToolTipText("Followers");
		list_1.setBounds(65, 106, 288, 154);
		contentPane.add(list_1);
		JScrollPane listScroller = new JScrollPane(list_1);
		//listScroller_1.setVisible(true);
		
		JList list_2 = new JList();
		list_2.setBounds(90, 365, 331, 114);
		contentPane.add(list_2);
		JScrollPane listScroller_1 = new JScrollPane(list_2);
		listScroller_1.setVisible(true);
		
		JButton btnNewButton_2 = new JButton("Follow User\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String follow = textArea.getText();
				boolean userExists = admin.followUser(root,selectedUser, follow);
				
				if(userExists) {
					updateFollowers(selectedUser,list_1,model);
				}
			}
		});
		btnNewButton_2.setBounds(268, 38, 186, 33);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Post Tweet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String tweet = selectedUser.toString()+ " said: " +txtrTweetMessage.getText();
				selectedUser.addTweet(root,tweet);
				updateTweets(selectedUser,list_2,model_1);
			}
		});
		btnNewButton_1.setBounds(289, 305, 177, 35);
		contentPane.add(btnNewButton_1);
		list_2.setModel(model_1);
		updateTweets(selectedUser,list_2,model_1);
		updateFollowers(selectedUser,list_1,model);
		
		String title[] = {selectedUser.toString()};
		JList list = new JList(title);
		list.setBounds(144, 2, 141, 24);
		contentPane.add(list);
		

};
		void updateTweets(AbstractUsers selectedUser, JList list, DefaultListModel model) {
			model = new DefaultListModel();
			for(int x = (selectedUser.newsFeed.size()-1); x >= 0;--x) {
				model.addElement(selectedUser.newsFeed.get(x));
			}
			list.setModel(model);	
		}
		void updateFollowers(AbstractUsers selectedUser, JList list, DefaultListModel model) {
			model = new DefaultListModel();
			for(int x = (selectedUser.following.size()-1); x >= 0;--x) {
				model.addElement(selectedUser.following.get(x));
			}
			list.setModel(model);	
		}
}
	
	
	

