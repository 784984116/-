//登陆界面 
package Pace;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.awt.event.*;
public class Pro1_1 extends JFrame implements ActionListener{
		JFrame jf=new JFrame("登陆界面");
		JLabel lab1=new JLabel("用户账号");
		JLabel lab2=new JLabel("密码");
		JTextField txf1=new JTextField();
		JPasswordField txf2=new JPasswordField();
		String a="1_1_登陆.png";
		String b="1_2_登陆.png";
		String c="1_3_登陆.png";
		MyButton btn1=new MyButton(a,b,c);
		String s=null;
		Connect h=null;
		int o=0;
		public Pro1_1(){
			h=new Connect();
			Container cp=jf.getContentPane();
			jf.setSize(1024,640);
			jf.setLocation(180,50);
			this.setResizable(true);
			lab1.setFont(new   java.awt.Font("Dialog",   1,   25));
			lab2.setFont(new   java.awt.Font("Dialog",   1,   25));
			txf1.setFont(new   java.awt.Font("Dialog",   1,   25));
			txf2.setFont(new   java.awt.Font("Dialog",   1,   25));
			String path="登陆1.jpg";
			Image backGround=new ImageIcon(path).getImage();
			JLabel label = new aLabel(backGround);
			label.setBounds(0, 0, jf.getWidth(), jf.getHeight());
			JPanel imagePanel = (JPanel) jf.getContentPane();  
	        imagePanel.setOpaque(false);  
	        // 把背景图片添加到分层窗格的最底层作为背景  
	        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
			cp.setLayout(null);
		    btn1.addActionListener(this);
			lab1.setBounds(320,240,120,30);
			lab2.setBounds(320,290,120,30);
			txf1.setBounds(460,240,180,30);
			txf2.setBounds(460,290,180,30);
			btn1.setBounds(810,480,198,47);
			cp.add(lab1);
			cp.add(lab2);
			cp.add(txf1);
			cp.add(txf2);
			cp.add(btn1);
			cp.add(label);
			jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
			jf.setVisible(true);
			}
		void sqlconn(){  
			  try{
				  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
				  Statement sql=dbConn.createStatement(); 
				  Statement sql1=dbConn.createStatement();
				  String a=txf1.getText();
				  String b=txf2.getText();
				  String str1=null;
				  String str2=null;
				  String adm1=null;
				  String adm2=null;
				  String str="SELECT*FROM 账户";
				  String adm="SELECT*FROM 管理员信息表";
				  ResultSet rs=sql.executeQuery(str);
				  ResultSet rs1=sql1.executeQuery(adm);
				  while(rs.next()){
					  if(a.equals(rs.getString(1).trim())){
						  str1=rs.getString(1).trim();
						  if(b.equals(rs.getString(2).trim())){
							  str2=rs.getString(2).trim();
						  }
					  }
				  }
				  while(rs1.next()){
					  if(a.equals(rs1.getString(1).trim())){
						  adm1=rs1.getString(1).trim();
						  if(b.equals(rs1.getString(2).trim())){
							  adm2=rs1.getString(2).trim();
						  }
					  }
				  }
				  if(str1==null&&adm1==null){
					  new Pro2_8();
					  txf1.setText(null);
					  txf2.setText(null);
				  }else {
					  if(str1!=null){
						  if(str2==null){
							  o=o+1;
							  new Pro2_7();
							  txf2.setText(null);
						  }else{							  
								  new Pro1_1_1(s,h);
								  jf.setVisible(false);							  	
							  }						  					  
					  }else{
						  if(adm1!=null){
							  if(adm2==null){
								  new Pro2_7();
								  txf2.setText(null);
							  }else
								  {
								  this.s=adm1;
								  new Pro1_6(s,h);
								  jf.setVisible(false);
								  }						  
					  }
					  }  
				  }
				  dbConn.close();
			  }catch(SQLException e1){
					e1.printStackTrace();
				}
		}
		void sqlconndj1(){ 
			  try{
				  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
				  Statement sql=dbConn.createStatement();
				  Statement sql1=dbConn.createStatement();
				  String str="SELECT*FROM 账户 WHERE 账户号='"+s+"'";
				  ResultSet rs=sql.executeQuery(str);
				  while(rs.next()){
				           boolean d=true;
						   String updatstr1="UPDATE 账户 SET 是否冻结='"+d+"'WHERE 账户号= '"+s+"'";
						   sql1.executeUpdate(updatstr1);
				  }
			  }catch(SQLException e1){
						e1.printStackTrace();
					}
			}
		void sqlconndj2(){ 
			  try{
				  Connection dbConn=DriverManager.getConnection(h.dbURL,h.userName,h.userPwd);
				  Statement sql=dbConn.createStatement();
				  Statement sql1=dbConn.createStatement();
				  String str="SELECT*FROM 储蓄卡表 WHERE 账户号='"+s+"'";
				  ResultSet rs=sql.executeQuery(str);
				  while(rs.next()){
				           boolean d=true;
				           String updatstr1="UPDATE 储蓄卡表 SET 是否冻结='"+d+"'WHERE 账户号= '"+s+"'";
						   sql1.executeUpdate(updatstr1);
				  }
			  }catch(SQLException e1){
						e1.printStackTrace();
					}
			}
	public static void main(String args[]){
               new Pro1_1();
	}
public void actionPerformed(ActionEvent e){
		JButton btn=(JButton)e.getSource();
		if(btn==btn1)
			{
			String str1=txf1.getText();
			this.s=str1;
			if(o==3){
				sqlconndj1();
				sqlconndj2();
				new Pro2_27();
				o=0;
			}else{
			sqlconn();
			}
			}
		
    }
}
