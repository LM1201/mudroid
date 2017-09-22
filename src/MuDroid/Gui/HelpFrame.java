package MuDroid.Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpFrame extends JFrame implements ActionListener,ItemListener{
	JButton bt1=new JButton();
	JButton bt2=new JButton();
	JLabel jl=new JLabel("show message");
	JTextArea jta=new JTextArea();
	JScrollPane jsp=new JScrollPane(jta);
	JMenuBar jmb;
	JMenu[] jm={
			new JMenu("JMenu1"),new JMenu("JMenu2"),new JMenu("JMenu3")
			};
	JMenuItem[] jmi={
			new JMenuItem("JMenuItem1"),new JMenuItem("JMenuItem2"),new JMenuItem("JMenuItem3")
			};
	JRadioButtonMenuItem[] jrbmi=new JRadioButtonMenuItem[]{
			new JRadioButtonMenuItem("JRadioButton1",true),
			new JRadioButtonMenuItem("JRadioButton2"),
			new JRadioButtonMenuItem("JRadioButton3")
			
	};
	ButtonGroup bg=new ButtonGroup();
	JCheckBoxMenuItem[] jcbmi=new JCheckBoxMenuItem[]
	{
		new JCheckBoxMenuItem("JCheckBoxMenuItem1"),
		new JCheckBoxMenuItem("JCheckBoxMenuItem2")
	};
	HelpFrame()
	{
		init();
		MyInit();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		bt1.setText("button1");
		bt2.setText("button2");
		
		jmb=new JMenuBar();
		jmb.add(jm[0]);
		
		for(int i=0;i<jmi.length;i++)
		{
			jm[0].add(jmi[i]);
			
			jmi[i].addActionListener(this);
		}
		jmb.add(jm[1]);
		for(int i=0;i<jrbmi.length;i++)
		{
			jm[1].add(jrbmi[i]);
			bg.add(jrbmi[i]);
			jrbmi[i].addItemListener(this);
		}
		jm[1].add(jm[2]);
		for(int i=0;i<jcbmi.length;i++)
		{
			jm[2].add(jcbmi[i]);
			jcbmi[i].addItemListener(this);
		}
		this.setJMenuBar(jmb);
		this.add(bt1);
		this.add(bt2);
		this.add(jsp,BorderLayout.CENTER);
		this.add(jl,BorderLayout.SOUTH);
	}

	private void MyInit() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设定窗体关闭后自动退出进程   
	    this.setSize(800,600);//设定窗体的默认尺寸   
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);//设定窗体状态为屏幕最大化，即全屏尺寸。   
	    this.setVisible(true);//显示窗体  
	    
//	    FlowLayout layout = new FlowLayout();
//	    this.setLayout(layout);
	}
	public static void main(String []args)
	{
		new HelpFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jmi[0])
		{
			jta.append("jmi[0]:"+jmi[0]+"\n");
		}
		if(e.getSource()==jmi[1])
		{
			jta.append("jmi[1]"+jmi[1]+"\n");
		}
		if(e.getSource()==jmi[2])
		{
			jta.append("jmi[2]"+jmi[2]+"\n");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<jrbmi.length;i++)
		{
			if(jrbmi[i].isSelected()==true)
			{
				
				jta.append("jrbmi["+i+"]"+jrbmi[i]+"\n");
				
			}
		}
		if(jcbmi[0].isSelected()==true)
		{
			jta.append("jcbmi[0]"+jcbmi[0]+"\n");
		}
		if(jcbmi[1].isSelected()==true)
		{
			jta.append("jcbmi[1]"+jcbmi[1]+"\n");
		}
	}
}
