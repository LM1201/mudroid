package MuDroid.Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class functionJPanel extends JPanel 
				implements ActionListener
{
	
	JLabel jl=new JLabel("label");
	JButton jb=new JButton("button");
	JButton jb2=new JButton("button2");
	JTextArea jta=new JTextArea();
	
	String filestring="F:/muandroid3/AndroidApp-master";
	
	functionJPanel()
	{
		this.setLayout(null);
		this.add(jl);
		this.add(jb);
		this.add(jb2);
		this.add(jta);
		jl.setBounds(100, 60, 100, 20);
		jb.setBounds(100, 20, 100, 20);
		jb2.setBounds(100, 40, 100, 20);
		jta.setBounds(100, 80, 300, 400);
		jb.addActionListener(this);
		jb2.addActionListener(this);
	}
	
	public static void main(String[] args)
	{
		JFrame jf=new JFrame();
		functionJPanel fjp=new functionJPanel();
		jf.add(fjp);
		fjp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		jf.setBounds(100,100,1300,600);
		jf.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb)
		{
			jl.setText("button1 is click");
			jta.append("+1!\n");
		}
		if(e.getSource()==jb2)
		{
			jl.setText("button2 is click");
			jta.append("+1!\n");
		}
	}

}
