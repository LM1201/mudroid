package MuDroid.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RunJPanel extends JPanel 
				implements ActionListener
{
	
	JTabbedPane jtp=new JTabbedPane();
	
	JLabel[] jlArray=new JLabel[]
			{
			new JLabel("<html><font size=7>Welcome to the Android Mutants Analysis System</font></html>"),
			new JLabel("<html><font size=6>For better use,you should know follow things</font></html>"),
			new JLabel("<html><font size=6>(1) Know the workflow</font></html>"),
			new JLabel("<html><font size=6>(2) make sure your APP can be build by Gradle</font></html>"),
	 		};
	String filestring="F:/muandroid3/AndroidApp-master";
	String projectName="";
	RunJPanel()
	{
		init();
	}
	RunJPanel(String projectName)
	{
		this.projectName=projectName;
		init();
	}
	void init()
	{
		
		this.add(jtp);
		
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		chooseMutantJPanel cmjp=new chooseMutantJPanel();
		choosexmlMutantJPanel xcmjp=new choosexmlMutantJPanel();
		orginalAndMutantPanel oamjp=new orginalAndMutantPanel();
		xmlorginalAndMutantPanel xoamjp=new xmlorginalAndMutantPanel();
	//	functionPanel.add(cmjp);
		jtp.addTab("choose java mutants", cmjp);
		jtp.addTab("choose xml mutants", xcmjp);
		jtp.addTab("show java mutants", oamjp);
		jtp.addTab("show xml mutants", xoamjp);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public static void main(String[] args)
	{
		JFrame jf=new JFrame();
		RunJPanel fjp=new RunJPanel();
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
