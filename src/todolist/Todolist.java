package todolist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JSpinner;

public class Todolist<Jpanel> extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane ;
	private JPanel createAccount;
	private JPanel login;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_2;
	private JLabel lblNewLabel_5;
	private JTextField textField_3;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JPanel userOrPassWrong;
	private JLabel message;
	private JButton btnNewButton_4;
	private JPanel ADDandShow;
	private JButton addTask;
	private JButton viewTasks;
	private JPanel panel_2;
	private JLabel lblNewLabel_7;
	private JTextField task;
	private JButton ADD;
	private JPanel panel_3;
	private JLabel lblNewLabel_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		
		try{  
			   Class.forName("com.mysql.cj.jdbc.Driver");
			    
			   //                                                       local host , port : 3306  , root , password  
			   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");  

			   Statement stmt = con.createStatement();
			    
			   // executeQuery => select(output) statemnt   
			   
			   
			   ResultSet rs=stmt.executeQuery("select * from user.users"); 

			   while(rs.next())  
			    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			     
			   con.close();  
			   
			   }catch(Exception e){
			    System.out.println(e);
			   }
		
		// print database of users 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todolist frame = new Todolist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	String userNameLogIn ;
	int IDLogIn ;
	String passwordLogIn ;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public Todolist() {
		
		setTitle("to do list");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		//                            (R ,  G ,  B )
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 624, 81);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("log in\r\n");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				change_screen(login);
			}
		});
		btnNewButton.setBounds(10, 32, 132, 23);
		panel.add(btnNewButton);
		//log in btn
		
		JButton btnNewButton_1 = new JButton("create account");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_screen(createAccount); 
			}
		});
		btnNewButton_1.setBounds(467, 32, 147, 23);
		panel.add(btnNewButton_1);
		//create btn
		
		lblNewLabel_6 = new JLabel("pages");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(276, 0, 42, 14);
		panel.add(lblNewLabel_6);
		
		//start the under screen 
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 103, 624, 397);
		contentPane.add(layeredPane);
		
		ADDandShow = new JPanel();
		ADDandShow.setBounds(0, 0, 624, 397);
		layeredPane.add(ADDandShow);
		ADDandShow.setLayout(null);
		
		addTask = new JButton("AddTask");
		addTask.setFont(new Font("Tahoma", Font.BOLD, 11));
		addTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_2.setVisible(true);
			
			}
		});
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 128, 0));
		panel_2.setBounds(143, 41, 328, 138);
		ADDandShow.add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_7 = new JLabel("write task");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(127, 11, 95, 14);
		panel_2.add(lblNewLabel_7);
		
		task = new JTextField();
		task.setBounds(10, 36, 308, 20);
		panel_2.add(task);
		task.setColumns(10);
		
		ADD = new JButton("ADD");
		ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = task.getText();
				// add value to data base 
				try{  
					   Class.forName("com.mysql.cj.jdbc.Driver");
					    
					   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");  

					   Statement stmt=con.createStatement();
					   
					   ResultSet rs = stmt.executeQuery("SELECT MAX( toDoId ) FROM user.todo ;");
					   rs.next() ;
					   int max_toDoId = rs.getInt(1);
					   max_toDoId ++ ;
					   // last row in database 
					   
					   
					   stmt.executeUpdate("INSERT INTO user.todo (toDoId, userID, toDolist) VALUES (" + max_toDoId +"," + IDLogIn + ",'" + t + "');"); 

					   con.close();  
					   
					   }
				catch(Exception ex){
					    System.out.println(ex);
					   } 
				
				
				
				panel_2.setVisible(false);
			}
		});
		ADD.setFont(new Font("Tahoma", Font.BOLD, 12));
		ADD.setBounds(127, 104, 89, 23);
		panel_2.add(ADD);
		panel_2.setVisible(false);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(128, 0, 255));
		panel_3.setBounds(143, 11, 328, 375);
		ADDandShow.add(panel_3);
		panel_3.setLayout(null);
		
		lblNewLabel_8 = new JLabel("Tasks");
		lblNewLabel_8.setBounds(140, 11, 67, 14);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(lblNewLabel_8);
		
		scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 36, 308, 328);
		panel_3.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Dialog", Font.BOLD, 14));
		scrollPane.setViewportView(textArea);
		panel_3.setVisible(false);
		addTask.setBounds(525, 11, 89, 23);
		ADDandShow.add(addTask);
		
		viewTasks = new JButton("Show Tasks");
		viewTasks.setFont(new Font("Tahoma", Font.BOLD, 11));
		viewTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
				textArea.setText("");
				
				String todoText = new String() ;
				todoText = "" ;
				try{  
					   Class.forName("com.mysql.cj.jdbc.Driver");
					    
					   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");  

					   Statement stmt=con.createStatement();
					    
					   ResultSet rs=stmt.executeQuery("select toDolist from user.todo where userID = " + IDLogIn); 

					   while(rs.next())  
						   todoText +=" -: " + rs.getString("toDolist") + "\n";
					   
					   textArea.setText(todoText);
					   
					   con.close();  
					   
					   }catch(Exception ex){
					    System.out.println(ex);
					   } 
				
				
				
				
				
			}
		});
		viewTasks.setBounds(10, 11, 123, 23);
		ADDandShow.add(viewTasks);
		ADDandShow.setVisible(false);
		
		
		createAccount = new JPanel();
		createAccount.setBackground(new Color(128, 0, 255));
		createAccount.setBounds(0, 0, 624, 397);
		layeredPane.add(createAccount);
		createAccount.setLayout(null);
		
		lblNewLabel = new JLabel("create new account");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(223, 11, 140, 28);
		createAccount.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("user name : ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(41, 79, 91, 27);
		createAccount.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(235, 80, 325, 30);
		createAccount.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(41, 216, 91, 28);
		createAccount.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(235, 220, 325, 30);
		createAccount.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton_2 = new JButton("create");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = new String();
				name = textField.getText() ;
				String pass = textField_1.getText() ;
				try{  

					   Class.forName("com.mysql.cj.jdbc.Driver");
					    
					   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");  

					   Statement stmt=con.createStatement();

					   ResultSet rs = stmt.executeQuery("SELECT MAX( id ) AS max_id FROM user.users ;");
					   rs.next() ;
					   int maxId = rs.getInt("max_id");
					   maxId ++ ;

						   String insertQuery = "INSERT INTO user.users (id, user, pass) VALUES (" + maxId + " , ' " + name + " ', ' " + pass + " ' )";
						   stmt.executeUpdate(insertQuery) ;
					   
					   ResultSet show =stmt.executeQuery("select * from user.users"); 
					   while(show.next()) {
						   System.out.println(show.getInt(1)+"  " + show.getString(2)+"  "+show.getString(3));
					   }
					   
					   
					   con.close();  
					
					   }catch(Exception ex){
					    System.out.println(ex);
					   } 
				
				textField.setText("");
				textField_1.setText("");
				
			}
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(260, 360, 89, 23);
		createAccount.add(btnNewButton_2);
		login = new JPanel();
		login.setBackground(new Color(128, 0, 128));
		login.setBounds(0, 0, 624, 397);
		layeredPane.add(login);
		login.setLayout(null);
		
		
		userOrPassWrong = new JPanel();
		userOrPassWrong.setBackground(new Color(255, 255, 255));
		userOrPassWrong.setBounds(179, 121, 221, 123);
		login.add(userOrPassWrong);
		userOrPassWrong.setLayout(null);
		
		message = new JLabel("user name or passowrd incorrect");
		message.setForeground(new Color(255, 0, 0));
		message.setFont(new Font("Tahoma", Font.BOLD, 12));
		message.setBounds(10, 32, 211, 36);
		userOrPassWrong.add(message);
		
		btnNewButton_4 = new JButton("OK\r\n");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userOrPassWrong.setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.setBounds(70, 89, 89, 23);
		
		userOrPassWrong.add(btnNewButton_4);
		userOrPassWrong.setVisible(false);
		lblNewLabel_3 = new JLabel("log in your account\r\n");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(230, 11, 144, 29);
		login.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("User name : ");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(30, 91, 98, 17);
		login.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(235, 80, 325, 30);
		login.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(30, 225, 98, 17);
		login.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(235, 220, 325, 30);
		login.add(textField_3);
		
		btnNewButton_3 = new JButton("log in\r\n");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				userNameLogIn = textField_2.getText();
				passwordLogIn = textField_3.getText();
				
				try{
					
					   Class.forName("com.mysql.cj.jdbc.Driver");
					    
					   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","1234");  

					   Statement stmt=con.createStatement();
					    
					   ResultSet rs=stmt.executeQuery("SELECT * FROM user.users WHERE TRIM(user) = '" + userNameLogIn + "' and TRIM(pass) = '" + passwordLogIn+"';"  );
					   rs.next();
					   try {
						   // user name and pass word are correct
						   
						   String struser = rs.getString("user");
						   String strpass = rs.getString("pass");
						   
						   IDLogIn = rs.getInt("id");
						   
						   change_screen(ADDandShow);
						   ADDandShow.setVisible(true);
							
						   
					   }catch (Exception exx) {
						   // user name or pass word are not correct
						    
							userOrPassWrong.setVisible(true);
							
					   }
					   
					   con.close();  
					   
					   }catch(Exception ex){
					    System.out.println(ex);
					   } 
				
				
				
				textField_2.setText("");
				textField_3.setText("");
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(260, 360, 89, 23);
		login.add(btnNewButton_3);
	}
	
	
	public void change_screen( JPanel p ) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
}
