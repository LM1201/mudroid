package MuDroid.Gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import MuDroid.JavaMutation.DealJavaOJMutantAndroid;
import MuDroid.JavaMutation.DealJavaOJMutantClass;
import MuDroid.JavaMutation.DealJavaOJMutantException;
import MuDroid.JavaMutation.DealJavaOJMutantTradtional;
import MuDroid.Singleton.Op;
import MuDroid.Util.*;
import MuDroid.XmlMutation.DealXmlSaxMutant;

public class ProgressJFrame extends JFrame
implements ActionListener{
	
	
	Thread t;
	private final int width=600;
	private final int height=371;
	private boolean noProject=true;
	private JPanel jp=new JPanel();
	private JPanel javaPanel=new JPanel();
	private JPanel xmlPanel=new JPanel();
	public JLabel javajl=new JLabel("Show java information");
	public JLabel javaopjl=new JLabel("Show opType");
	public JLabel xmljl=new JLabel("Show java information");
	public JLabel xmlopjl=new JLabel("Show opType");
	public JProgressBar jpb=new JProgressBar();
	
	public JPanel btJP=new JPanel();
	public JPanel btleftJP=new JPanel();
	public JButton bt1=new JButton("run in background");
	public JPanel btrightJP=new JPanel();
	public JButton bt2=new JButton("Stop");
	
	public JProgressBar jpb2=new JProgressBar();
	String[] javafile_list;
	String[] xmlfile_list;
	String[] manifestfile_list;
	String[] args;
	String java_SRC_PATH;
	String manifest_SRC_PATH;
	String xml_SRC_PATH;
	 boolean b=true;
	private static ProgressJFrame instance;
	private ProgressJFrame()
	{
		init();
		
	}
	public void setSome(String[] javafile_list,String []xmlfile_list,String []manifestfile_list,String[] args,String java_SRC_PATH,String xml_SRC_PATH,String manifest_SRC_PATH)
	{
		this.javafile_list=javafile_list;
		this.xmlfile_list=xmlfile_list;
		this.manifestfile_list=manifestfile_list;
		this.args=args;
		
		this.java_SRC_PATH=java_SRC_PATH;
		this.xml_SRC_PATH=xml_SRC_PATH;
		this.manifest_SRC_PATH=manifest_SRC_PATH;
	}
	private void init() {
		// TODO Auto-generated method stub
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		int startLocationX=(screenWidth-width)/2;
		int startLocationY=(screenHeight-height)/2;
	
		initlayout();
		
		this.setResizable(false);
		this.setBounds(startLocationX, startLocationY, width, height);
		
	}

public  void strat()	{
		 Runnable r = new Runnable() {
             @Override
             public void run() {
            	
				if(b)
            	 {
            		 b=false;
            		
                 		
                 		
             			jpb.setValue(0);
             			jpb2.setValue(0);
             			if(javafile_list!=null)
             			for(int i=0;i<javafile_list.length;i++)
             			{
             				System.out.println("javafile£º"+javafile_list[i]);
             				args[0]=java_SRC_PATH+"\\"+javafile_list[i];
             				
             				javajl.setText(" The file is dealing is  :  "+javafile_list[i]);
             				xmljl.setText(" The file is dealing is  :  ");
             				xmlopjl.setText(" The opType is  : ");
             				jpb.setValue((i+1)*100/javafile_list.length);
             				jpb.validate();
             				jpb.repaint();
             				String[] nu=new String[]{};
             				List<String> nullList = Arrays.asList(nu);
             				if(Op.getInstance().getClassOp()!=null&&Op.getInstance().getClassOp().size()>0)
             				{
             				
             					javaopjl.setText(" The opType is  :  Class Op");
             					try {
									PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/cmlog.txt")));
									System.setOut(ps);
             					} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
             				
             					DealJavaOJMutantClass.main(args);
             					jpb.setValue((i+1)*100/javafile_list.length+1*(25/(javafile_list.length)));
             				}
             				if(Op.getInstance().getTradtionalOp()!=null&&Op.getInstance().getTradtionalOp().size()>0)
             				{javaopjl.setText(" The opType is  : tradtional Op");
             				
             				try {
								PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/tmlog.txt")));
								System.setOut(ps);
         					} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
             				DealJavaOJMutantTradtional.main(args);
             					jpb.setValue((i+1)*100/javafile_list.length+2*(25/(javafile_list.length)));
             				
             				}
             				if(Op.getInstance().getExceptionOp()!=null&&Op.getInstance().getExceptionOp().size()>0)
             				{
             					try {
    								PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/emlog.txt")));
    								System.setOut(ps);
             					} catch (FileNotFoundException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
             					javaopjl.setText(" The opType is  :  Exception Op");
             					DealJavaOJMutantException.main(args);
             					jpb.setValue((i+1)*100/javafile_list.length+3*(25/(javafile_list.length)));
             				}
//////             		
             				if(Op.getInstance().getAndroidOp()!=null&&Op.getInstance().getAndroidOp().size()>0)
             				{
             					javaopjl.setText(" The opType is  :  Android Op");
             					try {
    								PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/amlog.txt")));
    								System.setOut(ps);
             					} catch (FileNotFoundException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
             					DealJavaOJMutantAndroid.main(args);
             					jpb.setValue((i+1)*100/javafile_list.length+4*(25/(javafile_list.length)));
             				
             				}
             				
             			}
             			xmlopjl.setText(" The opType is  : Xml op");
             			if(manifestfile_list!=null)
             				for(int i=0;i<manifestfile_list.length;i++)
                 			{
             					
             					xmljl.setText(" The file is dealing is  :  "+manifestfile_list[i]);
                 				args[0]=manifest_SRC_PATH+"\\"+manifestfile_list[i];
                 				if(Op.getInstance().getXmlOp()!=null&&Op.getInstance().getXmlOp().size()>0)
                 				{
                 					try {
        								PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/xm1log.txt")));
        								System.setOut(ps);
                 					} catch (FileNotFoundException e) {
        								// TODO Auto-generated catch block
        								e.printStackTrace();
        							}
                 					DealXmlSaxMutant.main(args);
                 				}
                 				System.out.println(manifestfile_list[i]);
                 			}
             			if(xmlfile_list!=null)
             			for(int i=0;i<xmlfile_list.length;i++)
             			{
             				xmljl.setText(" The file is dealing is  :  "+xmlfile_list[i]);
             				args[0]=xml_SRC_PATH+"\\"+xmlfile_list[i];
             				jpb2.setValue((i+1)*100/xmlfile_list.length);
             				if(Op.getInstance().getXmlOp()!=null&&Op.getInstance().getXmlOp().size()>0)
             				{
             					try {
    								PrintStream ps=new PrintStream(new FileOutputStream(new File("F:/muandroid3/AndroidApp-master/app/xm2log.txt")));
    								System.setOut(ps);
             					} catch (FileNotFoundException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
//             					args[0]=xmlList.get(i);
             					System.out.println("arg[0]:"+args[0]);
             					DealXmlSaxMutant.main(args);
             				}
             				System.out.println(xmlfile_list[i]);
             			}
             			
             		
             				try {
             					testReadProjectDirToXmlWithFilterOfMutant.main(null);
             				} catch (Exception e) {
             					// TODO Auto-generated catch block
             					e.printStackTrace();
             				}
             			
             			
             			
             			b=true;
            	 }
				else
				{}
            	 
     	}
     };

     t = new Thread(r);
     t.start();
//         if(b)
//         t.start();
//         else
//         {
//        	
//        	 t.stop();
//        	 System.out.println(">>>>>>>>>>>>>>>>>>>>>");
//         }
        
      //   bt2.doClick();
	}

public void stop()
{
	
}
	private void initlayout() {
		// TODO Auto-generated method stub
		this.add(jp);
		jp.add(javaPanel);
		jp.add(xmlPanel);
		javaPanel.setLayout(new BoxLayout(javaPanel,BoxLayout.PAGE_AXIS));
		javaPanel.setBorder(new TitledBorder("Show java mutant progress"));
		xmlPanel.setLayout(new BoxLayout(xmlPanel,BoxLayout.PAGE_AXIS));
		xmlPanel.setBorder(new TitledBorder("Show xml mutant progress"));
		javaPanel.add(jpb);
		javaPanel.add(javajl);
		javaPanel.add(javaopjl);
		xmlPanel.add(jpb2);
		xmlPanel.add(xmljl);
		xmlPanel.add(xmlopjl);
		jp.setBorder(new TitledBorder("Show the progress of mutant"));;
		jp.setLayout(new BoxLayout(jp,BoxLayout.PAGE_AXIS));
		jpb.setValue(0);
		jpb2.setValue(0);
		jpb.setStringPainted(true);
		jpb2.setStringPainted(true);
		jp.add(btJP);
		btJP.setLayout(new BoxLayout(btJP,BoxLayout.LINE_AXIS));
		btJP.add(btleftJP);
		btJP.add(btrightJP);
		btleftJP.add(bt1);
		bt1.addActionListener(this);
		bt1.setPreferredSize(new Dimension(160,30));
		btrightJP.add(bt2);
		bt2.addActionListener(this);
		bt2.setPreferredSize(new Dimension(120,30));
		
	}

	public static void main(String [] args)
	{
		 ProgressJFrame.getInstance().setVisible(true);;
	}
	 public static ProgressJFrame getInstance(){  
	        if (instance==null){  
	            instance=new ProgressJFrame();  
	        }  
	        return instance;  
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1)
		{
			instance.setVisible(false);
		}
		if(e.getSource()==bt2)
		{
			
			{t.stop();
			b=true;}
			this.setVisible(false);
			
		}
	}

}
