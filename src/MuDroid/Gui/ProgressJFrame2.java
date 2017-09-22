package MuDroid.Gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import MuDroid.Serialization.ReadMutantfromXml;
import MuDroid.Singleton.mutantJavaAndXmlList;
import MuDroid.Util.*;

public class ProgressJFrame2 extends JFrame
implements ActionListener{
	
	
	Thread t;
	private final int width=700;
	private final int height=550;
	private boolean noProject=true;
	private JPanel jp=new JPanel();
	private JPanel javaPanelcm=new JPanel();
	private JPanel javaPaneltm=new JPanel();
	private JPanel javaPanelem=new JPanel();
	private JPanel javaPanelam=new JPanel();
	private JPanel xmlPanelxm=new JPanel();
	public JLabel javacmjl=new JLabel("Show java information");
	public JLabel javacmopjl=new JLabel("Show opType");
	public JLabel javatmjl=new JLabel("Show java information");
	public JLabel javatmopjl=new JLabel("Show opType");
	public JLabel javaemjl=new JLabel("Show java information");
	public JLabel javaemopjl=new JLabel("Show opType");
	public JLabel javaamjl=new JLabel("Show java information");
	public JLabel javaamopjl=new JLabel("Show opType");
	public JLabel xmljl=new JLabel("Show java information");
	public JLabel xmlopjl=new JLabel("Show opType");
	public JProgressBar jpbcm=new JProgressBar();
	public JProgressBar jpbtm=new JProgressBar();
	public JProgressBar jpbem=new JProgressBar();
	public JProgressBar jpbam=new JProgressBar();
	public JPanel btJP=new JPanel();
	public JPanel btleftJP=new JPanel();
	public JButton bt1=new JButton("run in background");
	public JPanel btrightJP=new JPanel();
	public JButton bt2=new JButton("Stop");
	
	public JProgressBar jpb2=new JProgressBar();
	
	String[] args;
	
	 boolean b=true;
	private static ProgressJFrame2 instance;
	private ProgressJFrame2()
	{
		init();
		
	}
	boolean cmbisSelected=false;
	boolean tmbisSelected=false;
	boolean embisSelected=false;
	boolean ambisSelected=false;
	boolean xmbisSelected=false;
	FileTableModel jftM;
	FileTableModel jcmOptM;
	FileTableModel jtmOptM;
	FileTableModel jemOptM;
	FileTableModel jamOptM;
	FileTableModel jxmOptM;
	public void setSome(boolean cmbisSelected,boolean tmbisSelected,boolean embisSelected,boolean ambisSelected,boolean xmbisSelected)
	{
		this.cmbisSelected=cmbisSelected;
		this.tmbisSelected=tmbisSelected;
		this.embisSelected=embisSelected;
		this.ambisSelected=ambisSelected;
		this.xmbisSelected=xmbisSelected;
	}
	public void setSome2(FileTableModel jftM,FileTableModel jcmOptM,FileTableModel jtmOptM,	FileTableModel jemOptM,	FileTableModel jamOptM,	FileTableModel jxmOptM)
	{
		this.jftM=jftM;
		this.jcmOptM=jcmOptM;
		this.jtmOptM=jtmOptM;
		this.jemOptM=jemOptM;
		this.jamOptM=jamOptM;
		this.jxmOptM=jxmOptM;
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
            		
                 		
            		System.out.println("runTest");
         			FileTableModel fTableModel = jftM;
           			String[] javafile_list = fTableModel.getSelectedFiles();
           			for(int i=0;i<javafile_list.length;i++)
           			{
           				System.out.println(javafile_list[i]);
           				if(cmbisSelected)
           				{
           					
           					fTableModel = jcmOptM;
           					String[] file_list = fTableModel.getSelectedFiles();
           					if(file_list!=null)
           					{
           						for(int j=0;j<file_list.length;j++)
           						{
           							jpbcm.setValue((i+1)*100/javafile_list.length+((j+1)*100)/(javafile_list.length*file_list.length));
           							System.out.println(file_list[j]);
           							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
           							ReadMutantfromXml.main(args);
           							mutantJavaAndXmlList.getInstance().readJavaList();
           							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
           							String tempfilename;
           							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
           							String ofilename;
           							if(f_l.size()>0)
           							{
           								tempfilename=((String)f_l.get(0)).replace("\\", "/");
           								if(tempfilename.indexOf("classOp")>=0)
           									tempfilename=tempfilename.substring(0, tempfilename.indexOf("classOp")-1);
           								
           								tempfilename=tempfilename+".java";
           								ofilename=tempfilename.replace("/mutant/", "/src/");
           								String[] args2= new String[]{tempfilename2,ofilename};
           								ReplaceOrginalWithMutant.main(args2);
           								
           								for(int k=0;k<f_l.size();k++)
           	  							{
           	  								
           	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
           	  								if(tempfilename.indexOf("classOp")>=0)
           	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("classOp")-1);
           	  								
           	  								tempfilename=tempfilename+".java";
           	  								
           	  								 ofilename=tempfilename.replace("/mutant/", "/src/");
           	  								System.out.println(ofilename);
           	  								
           	  								args2= new String[]{ofilename,(String) f_l.get(k)};
           	  								ReplaceOrginalWithMutant.main(args2);
           	  							
           								args2= new String[]{ofilename,tempfilename2};
           								ReplaceOrginalWithMutant.main(args2);
           								
           								try{
           									System.out.println("run test class Op>>>>>>>");
           									 Process p = null;  
           									 String line = null;  
           									    BufferedReader stdout = null;  
           									   BufferedWriter bw=null;
           									   bw=new BufferedWriter(new FileWriter(tempfilename2+k+"CO.log"));
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
           	  							}
           							}
           							
           						}
           					}
           				}
           				else
           				{
           					jpbcm.setValue(100);
           				}
           				if(tmbisSelected)
           				{
           				

           					fTableModel = jtmOptM;
           					String[] file_list = fTableModel.getSelectedFiles();
           					if(file_list!=null)
           					{
           						
           						for(int j=0;j<file_list.length;j++)
           						{
           							jpbtm.setValue((i+1)*100/javafile_list.length+((j+1)*100)/(javafile_list.length*file_list.length));
           							
           							System.out.println(file_list[j]);
           							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
           							ReadMutantfromXml.main(args);
           							mutantJavaAndXmlList.getInstance().readJavaList();
           							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
           							String tempfilename;
           							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
           							String ofilename;
           							
           							if(f_l.size()>0)
           							{
           								
           								tempfilename=((String)f_l.get(0)).replace("\\", "/");
           								if(tempfilename.indexOf("traditionalOp")>=0)
           									tempfilename=tempfilename.substring(0, tempfilename.indexOf("traditionalOp")-1);
           								
           								tempfilename=tempfilename+".java";
           								ofilename=tempfilename.replace("/mutant/", "/src/");
           								String[] args2= new String[]{tempfilename2,ofilename};
           								ReplaceOrginalWithMutant.main(args2);
           								
           								
           								for(int k=0;k<f_l.size();k++)
           	  							{
           	  								
           	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
           	  								if(tempfilename.indexOf("traditionalOp")>=0)
           	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("traditionalOp")-1);
           	  								tempfilename=tempfilename+".java";
           	  								
           	  								ofilename=tempfilename.replace("/mutant/", "/src/");
           	  								System.out.println(ofilename);
           	  								args2= new String[]{ofilename,(String) f_l.get(k)};
           	  								ReplaceOrginalWithMutant.main(args2);
           	  							
           	  							try{
           	  							System.out.println("run test tradtional Op>>>>>>>");
           	  							 Process p = null;  
           	  							 String line = null;  
           	  							    BufferedReader stdout = null;  
           	  							  BufferedWriter bw=null;
           	  							 bw=new BufferedWriter(new FileWriter(tempfilename2+k+"TO.log")); 
           	  							    //list the files and directorys under C:\  
           	  							    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
           	  							    stdout = new BufferedReader(new InputStreamReader(p  
           	  							      .getInputStream()));  
           	  							    while ((line = stdout.readLine()) != null) {
           	  							     System.out.println(line); 
           	  							    bw.write(line);
           	 						    bw.newLine();;
           							    bw.flush();
//           	  				                   //  Thread.sleep( 10 );
           	  							    }  
           	  							   
           	  							    
           	  							    stdout.close();  
           	  							    p.destroy();
           	  							      
           	  							   } catch (Exception e1) {  
           	  							    e1.printStackTrace();  
           	  							   }  
           	  							}
           								args2= new String[]{ofilename,tempfilename2};
           								ReplaceOrginalWithMutant.main(args2);
           							}
           							
           						}
           					}
           				}
           				else
           				{
           					jpbtm.setValue(100);
           				}
           				if(embisSelected)
           				{
           					
           					fTableModel = (FileTableModel)jemOptM;
           					String[] file_list = fTableModel.getSelectedFiles();
           					if(file_list!=null)
           					{
           						
           						for(int j=0;j<file_list.length;j++)
           						{
           							jpbem.setValue((i+1)*100/javafile_list.length+((j+1)*100)/(javafile_list.length*file_list.length));
           							System.out.println(file_list[j]);
           							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
           							ReadMutantfromXml.main(args);
           							mutantJavaAndXmlList.getInstance().readJavaList();
           							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
           							String tempfilename;
           							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
           							String ofilename;
           							if(f_l.size()>0)
           							{
           								tempfilename=((String)f_l.get(0)).replace("\\", "/");
           								if(tempfilename.indexOf("exceptionOp")>=0)
           									tempfilename=tempfilename.substring(0, tempfilename.indexOf("exceptionOp")-1);
           								
           								tempfilename=tempfilename+".java";
           								ofilename=tempfilename.replace("/mutant/", "/src/");
           								String[] args2= new String[]{tempfilename2,ofilename};
           								ReplaceOrginalWithMutant.main(args2);
           								for(int k=0;k<f_l.size();k++)
           	  							{
           	  								
           	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
           	  								if(tempfilename.indexOf("exceptionOp")>=0)
           	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("exceptionOp")-1);
           	  								tempfilename=tempfilename+".java";
           	  								
           	  								ofilename=tempfilename.replace("/mutant/", "/src/");
           	  								System.out.println(ofilename);
           	  								args2= new String[]{ofilename,(String) f_l.get(k)};
           	  								ReplaceOrginalWithMutant.main(args2);
           	  								
           	  							try{
           	  							System.out.println("run test exception Op>>>>>>>");
           	  							 Process p = null;  
           	  							 String line = null;  
           	  							    BufferedReader stdout = null;  
           	  							   BufferedWriter bw=null;
           	  							 bw=new BufferedWriter(new FileWriter(tempfilename2+k+"EO.log"));
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
           	  							}
           								args2= new String[]{ofilename,tempfilename2};
           								ReplaceOrginalWithMutant.main(args2);
           							}
           							
           						}
           					}
           				}
           				else
           				{
           					jpbem.setValue(100);
           				}
           				if(ambisSelected)
           				{
           					
           					fTableModel = (FileTableModel)jamOptM;
           					String[] file_list = fTableModel.getSelectedFiles();
           					if(file_list!=null)
           					{
           						
         							
           						for(int j=0;j<file_list.length;j++)
           						{
           							jpbam.setValue((i+1)*100/javafile_list.length+((j+1)*100)/(javafile_list.length*file_list.length));
           							System.out.println(file_list[j]);
           							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
           							ReadMutantfromXml.main(args);
           							mutantJavaAndXmlList.getInstance().readJavaList();
           							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
           							String tempfilename;
           							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
           							String ofilename;
           							if(f_l.size()>0)
           							{
           								tempfilename=((String)f_l.get(0)).replace("\\", "/");
           								if(tempfilename.indexOf("androidOp")>=0)
           									tempfilename=tempfilename.substring(0, tempfilename.indexOf("androidOp")-1);
           								
           								tempfilename=tempfilename+".java";
           								ofilename=tempfilename.replace("/mutant/", "/src/");
           								String[] args2= new String[]{tempfilename2,ofilename};
           								ReplaceOrginalWithMutant.main(args2);
           								
           								for(int k=0;k<f_l.size();k++)
           	  							{
           	  								
           	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
           	  								if(tempfilename.indexOf("androidOp")>=0)
           	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("androidOp")-1);
           	  								tempfilename=tempfilename+".java";
           	  								
           	  								ofilename=tempfilename.replace("/mutant/", "/src/");
           	  								System.out.println(ofilename);
           	  								args2= new String[]{ofilename,(String) f_l.get(k)};
           	  								ReplaceOrginalWithMutant.main(args2);
           	  								
           	  								
           	  							try{
           	  								
           	  								System.out.println("run test Android Op>>>>>>>");
           	  								Process p = null;  
           	  								String line = null;  
           	  							    BufferedReader stdout = null;  
           	  							   BufferedWriter bw=null;
           	  							 bw=new BufferedWriter(new FileWriter(tempfilename2+k+"AO.log"));
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
           	  							   
           	  							p.destroy();
           	  							    stdout.close();  
           	  							    
           	  							  
           	  							      
           	  							   } catch (Exception e1) {  
           	  							    e1.printStackTrace();  
           	  							   }  
           	  								
           	  							}
           								args2= new String[]{ofilename,tempfilename2};
           								ReplaceOrginalWithMutant.main(args2);
           							}
           							
           						}
           					}
           				}
           				else
           				{
           					jpbam.setValue(100);
           				}
           				if(xmbisSelected)
           				{
           				
           					fTableModel = (FileTableModel)jxmOptM;
           					String[] file_list = fTableModel.getSelectedFiles();
           					if(file_list!=null)
           					{
           						for(int j=0;j<file_list.length;j++)
           						{
           							jpb2.setValue((i+1)*100/javafile_list.length+((j+1)*100)/(javafile_list.length*file_list.length));
           							System.out.println(file_list[j]);
           							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_xml.xml",javafile_list[i],"all","xmlOp",file_list[j]};
           							ReadMutantfromXml.main(args);
           							mutantJavaAndXmlList.getInstance().readJavaList();
           							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
           							String tempfilename;
           							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.xml";
           							String ofilename;
           							if(f_l.size()>0)
           							{
           								tempfilename=((String)f_l.get(0)).replace("\\", "/");
           								if(tempfilename.indexOf("xmlOp")>=0)
           									tempfilename=tempfilename.substring(0, tempfilename.indexOf("xmlOp")-1);
           								
           								tempfilename=tempfilename+".xml";
           								ofilename=tempfilename.replace("/mutant/", "/src/");
           								System.out.println(ofilename);
           								String[] args2= new String[]{tempfilename2,ofilename};
           								//System.err.println(">>>>>>>>"+tempfilename2);
           								ReplaceOrginalWithMutant.main(args2);
           								//System.err.println(">>>>>>>>");
           								
           								for(int k=0;k<f_l.size();k++)
           	  							{
           	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
           	  								if(tempfilename.indexOf("xmlOp")>=0)
           	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("xmlOp")-1);
           	  								tempfilename=tempfilename+".xml";
           	  								
           	  								ofilename=tempfilename.replace("/mutant/", "/src/");
           	  								System.out.println(ofilename);
           	  								args2= new String[]{ofilename,(String) f_l.get(k)};
           	  								ReplaceOrginalWithMutant.main(args2);
           	  								//Ö´ÐÐ²âÊÔ
           	  								
           	  							try{
           	  							System.out.println("run test xml Op>>>>>>>");
           	  							 Process p = null;  
           	  							 String line = null;  
           	  							 BufferedReader stdout = null;  
           	  							   BufferedWriter bw=null;
           	  							 bw=new BufferedWriter(new FileWriter(tempfilename2+j+"XO.log"));
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
           	  								
           	  							}
           							
           							args2= new String[]{ofilename,tempfilename2};
           							ReplaceOrginalWithMutant.main(args2);
           							}
           							
           						}
           					}
           				}
           				else
           				{
           					jpb2.setValue(100);
           				}
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
		jp.add(javaPanelcm);
		jp.add(javaPaneltm);
		jp.add(javaPanelem);
		jp.add(javaPanelam);
		javaPanelcm.setLayout(new BoxLayout(javaPanelcm,BoxLayout.PAGE_AXIS));
		javaPanelcm.setBorder(new TitledBorder("Show run test of java CM"));
		jp.add(xmlPanelxm);
		javaPaneltm.setLayout(new BoxLayout(javaPaneltm,BoxLayout.PAGE_AXIS));
		javaPaneltm.setBorder(new TitledBorder("Show run test of java TM"));
		
		javaPanelem.setLayout(new BoxLayout(javaPanelem,BoxLayout.PAGE_AXIS));
		javaPanelem.setBorder(new TitledBorder("Show run test of java EM"));
		
		javaPanelam.setLayout(new BoxLayout(javaPanelam,BoxLayout.PAGE_AXIS));
		javaPanelam.setBorder(new TitledBorder("Show run test of java AM"));
		
		xmlPanelxm.setLayout(new BoxLayout(xmlPanelxm,BoxLayout.PAGE_AXIS));
		xmlPanelxm.setBorder(new TitledBorder("Show run test of xml XM"));
		javaPanelcm.add(jpbcm);
		javaPanelcm.add(javacmjl);
		javaPanelcm.add(javacmopjl);
		javaPaneltm.add(jpbtm);
		javaPaneltm.add(javatmjl);
		javaPaneltm.add(javatmopjl);
		javaPanelem.add(jpbem);
		javaPanelem.add(javaemjl);
		javaPanelem.add(javaemopjl);
		javaPanelam.add(jpbam);
		javaPanelam.add(javaamjl);
		javaPanelam.add(javaamopjl);
		xmlPanelxm.add(jpb2);
		xmlPanelxm.add(xmljl);
		xmlPanelxm.add(xmlopjl);
		jp.setBorder(new TitledBorder("Show the progress of run test"));;
		jp.setLayout(new BoxLayout(jp,BoxLayout.PAGE_AXIS));
		jpbcm.setValue(0);
		jpbtm.setValue(0);
		jpbem.setValue(0);
		jpbam.setValue(0);
		jpb2.setValue(0);
		jpbcm.setStringPainted(true);
		jpbtm.setStringPainted(true);
		jpbem.setStringPainted(true);
		jpbam.setStringPainted(true);
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
		 ProgressJFrame2.getInstance().setVisible(true);;
	}
	 public static ProgressJFrame2 getInstance(){  
	        if (instance==null){  
	            instance=new ProgressJFrame2();  
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
