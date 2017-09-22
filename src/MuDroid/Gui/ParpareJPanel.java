package MuDroid.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ParpareJPanel extends JPanel 
				implements ActionListener
{
	PrintStream printStream ;
	JPanel functionPanel=new JPanel();
	JPanel showPanel=new JPanel();
	JPanel labelPanel=new JPanel();
	JPanel label1Panel=new JPanel();
	JPanel label2Panel=new JPanel();
	JPanel buttonPanel=new JPanel();
	String projectName="";
	JScrollPane messageSP = new JScrollPane();
	JTextArea messageTP = new JTextArea();
	JButton compileBt=new JButton("compile and test");
	JButton getInheritanceRelationBt=new JButton("getInheritanceRelation");
	JLabel[] jlArray=new JLabel[]
			{
				new JLabel("<html><font size=6>Welcome to the Android Mutants Analysis System</font></html>"),
				new JLabel("<html><font size=4>For better use,you should know follow things</font></html>"),
				new JLabel("<html><font size=5>(1) Parpare£ºKnow the workflow and Examine the project canbe compile and text </font></html>"),
				new JLabel("<html><font size=5>(2) Mutant£ºGet the mutant by your choosed op in your choosed file </font></html>"),
				new JLabel("<html><font size=5>(3) Run£ºGet the mutanted imformation and run test by your choose </font></html>"),
				new JLabel("<html><font size=5>(4) Anaylysis£ºAnaylysis the result.</font></html>"),
			};
	String filestring="F:/muandroid3/AndroidApp-master";
	
	void init()
	{
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
//		
		functionPanel.setLayout(new BoxLayout(functionPanel,BoxLayout.LINE_AXIS));
		functionPanel.setBorder(BorderFactory.createEtchedBorder());
		functionPanel.add(labelPanel);
		functionPanel.add(buttonPanel);
		
		labelPanel.add(label1Panel);
		labelPanel.add(label2Panel);

		label1Panel.setLayout(new BoxLayout(label1Panel,BoxLayout.PAGE_AXIS));
		JLabel information=new JLabel("");
		information.setText("the project name£º"+this.projectName);
		information.setForeground(Color.darkGray);
		label1Panel.add(information);
		for(int i=0;i<jlArray.length;i++)
		{
			label1Panel.add(jlArray[i]);
		}
		jlArray[0].setForeground(Color.darkGray);
//		label1Panel.setBorder(BorderFactory.createEtchedBorder());
		labelPanel.setPreferredSize(new Dimension(800,300));
		
		
		JLabel jl1=new JLabel("in this panel, you can konw the workflow about the system ");
		JLabel jl2=new JLabel("compile and test the project");
		JLabel jl3=new JLabel("get the class inheritance relation ");
		
		JPanel label3Panel=new JPanel();
		label3Panel.setLayout(new BoxLayout(label3Panel,BoxLayout.PAGE_AXIS));
		label3Panel.add(Box.createGlue());
		label3Panel.add(jl1);
		label3Panel.add(jl2);
		label3Panel.add(jl3);
		label3Panel.add(Box.createGlue());
		buttonPanel.add(label3Panel);
	//	buttonPanel.setPreferredSize(new Dimension(500,300));
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
		JPanel button1Panel=new JPanel();
		buttonPanel.add(button1Panel);
		button1Panel.setLayout(new BoxLayout(button1Panel,BoxLayout.PAGE_AXIS));
		button1Panel.add(Box.createGlue());
		
		compileBt.setPreferredSize(new Dimension(170,30));
		compileBt.setMinimumSize(new Dimension(170,30));
		compileBt.setMaximumSize(new Dimension(170,30));
		button1Panel.add(compileBt);
		button1Panel.add(Box.createGlue());
		compileBt.addActionListener(this);
		getInheritanceRelationBt.setPreferredSize(new Dimension(170,30));
		getInheritanceRelationBt.setMinimumSize(new Dimension(170,30));
		getInheritanceRelationBt.setMaximumSize(new Dimension(170,30));
		getInheritanceRelationBt.addActionListener(this);
		button1Panel.add(getInheritanceRelationBt);
		//button1Panel.setBorder(BorderFactory.createEtchedBorder());
		button1Panel.add(Box.createGlue());
		showPanel.setBorder(BorderFactory.createEtchedBorder());
		showPanel.setLayout(new BoxLayout(showPanel,BoxLayout.LINE_AXIS));
		messageSP.setPreferredSize(new Dimension(1300,300));
		messageSP.setBorder(new TitledBorder("Message"));
		messageSP.getViewport().add(messageTP, null);
		showPanel.add(messageSP);
		this.add(functionPanel);
		this.add(showPanel);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	ParpareJPanel()
	{
		this.projectName="F:/muandroid3/AndroidApp-master";
		init();
	}
	ParpareJPanel(String projectName)
	{
		this.projectName=projectName;
		init();
	}
	
	public static void main(String[] args)
	{
		JFrame jf=new JFrame();
		ParpareJPanel fjp=new ParpareJPanel();
		jf.add(fjp);
		fjp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		jf.setBounds(100,100,1300,600);
		jf.setVisible(true);	
		jf.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	
	boolean b=true;
	 Thread t;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.compileBt)
		{
			Runnable r = new Runnable() {
	             @Override
	             public void run() {
	            	
					if(b)
	            	 {
	            		 b=false;
	            		 messageTP.setText("");
	         			GUIPrintStream printStream = new GUIPrintStream(System.out,messageTP);
	         			System.setOut(printStream);
	         			System.setErr(printStream);
	         			try{
	         			 Process p = null;  
	         			 String line = null;  
	         			    BufferedReader stdout = null;  
	         			    BufferedWriter bw=null;
	         			    bw=new BufferedWriter(new FileWriter("F:/muandroid3/AndroidApp-master/app/comilpe.txt"));
	         			    //list the files and directorys under C:\  
	         			    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
	         			    stdout = new BufferedReader(new InputStreamReader(p  
	         			      .getInputStream()));  
	         			    while ((line = stdout.readLine()) != null) {
	         			     System.out.println(line); 
	         			     bw.write(line);
	         				 bw.newLine();;
	         				 bw.flush();
	                            //  Thread.sleep( 10 );
	         			    }  
	         			   
	         			    
	         			    stdout.close(); 
	         			    p.destroy();
	         			  
	         			      
	         			   } catch (Exception e1) {  
	         			    e1.printStackTrace();  
	         			   }  
	            		 b=true;
	            	 }
	             }
			};
			Thread t=new Thread(r);
			if(b)
			{
				t.start();
			}
			else
			{
				System.out.println("runing");
			}
			
			
		}
		if(e.getSource()==this.getInheritanceRelationBt)
		{
			Runnable r = new Runnable() {
	             @Override
	             public void run() {
	            	
					if(b)
	            	 {
						b=false;
						messageTP.setText("");
						GUIPrintStream printStream = new GUIPrintStream(System.out,messageTP);
						System.setOut(printStream);
						System.setErr(printStream);
						
						File f=new File("F:/muandroid3/AndroidApp-master/app/out.txt");
						if(f.exists())
						{
							
							try {
								String line = null;  
							    BufferedReader stdout = null; 
								stdout=new BufferedReader(new FileReader(f));
								while ((line = stdout.readLine()) != null) {
								     System.out.println(line); 
								}
								stdout.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						else
						{
							try{
								String path="F:/muandroid3/AndroidApp-master";
								String preDexDebugPath="/app/build/intermediates/pre-dexed/debug";
								for (File file : new File(path, preDexDebugPath).listFiles()) {
								      System.out.println(">>delect "+file);
								      if (file.exists())
								      {
								    	  file.delete();
								      }
								}
								 Process p = null;  
								 String line = null;  
								    BufferedReader stdout = null;  
								    BufferedWriter bw=null;
								    bw=new BufferedWriter(new FileWriter("F:/muandroid3/AndroidApp-master/app/out.txt"));
								    //list the files and directorys under C:\  
								    //assembleDebug","-p","F:/muandroid3/AndroidApp-master","--info","--daemon","-Dorg.gradle.appname=\"123\"
								    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat assembleDebug -p F:/muandroid3/AndroidApp-master --info --daemon -Dorg.gradle.appname=123",null,new File("F:/muandroid3/AndroidApp-master") );  
								    stdout = new BufferedReader(new InputStreamReader(p  
								      .getInputStream()));  
								    while ((line = stdout.readLine()) != null) {
								     System.out.println(line); 
								     bw.write(line);
									 bw.newLine();
									 bw.flush();
					                   //  Thread.sleep( 10 );
								    }  
								   
								    
								    stdout.close(); 
								    p.destroy();
								  
								      
								   } catch (Exception e1) {  
								    e1.printStackTrace();  
								   }  
						}
						
					
						b=true;
	            	 }
	             }
			};
			Thread t=new Thread(r);
			if(b)
			{
				t.start();
			}
			else
			{
				System.out.println("running");
			}
		}
			
	}

}
