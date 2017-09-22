package MuDroid.Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import MuDroid.Serialization.getSummaryMutantfromXml;


public class chooseMutantJPanel extends JPanel 
				implements ActionListener,ItemListener
{
	private static final long serialVersionUID = 1L;
	JPanel listPanel=new JPanel();
	JPanel strategyPanel=new JPanel();
	
	JPanel functionPanel=new JPanel();
	JPanel useagePanel=new JPanel();
	JPanel runPanel=new JPanel();
	JPanel showPanel=new JPanel();
	JButton all=new JButton("All");
	JButton none=new JButton("None");
	
	JCheckBox cmb=new JCheckBox("class Op",true);
	JCheckBox tmb=new JCheckBox("tradtional Op",true);
	JCheckBox emb=new JCheckBox("exception Op",true);
	JCheckBox amb=new JCheckBox("android Op",true);
	JCheckBox xmb=new JCheckBox("xml Op",true);
	
	JRadioButton opAll=new JRadioButton("All files");
	JRadioButton opSome=new JRadioButton("choose some file",true);
	JRadioButton fAll=new JRadioButton("All files");
	JRadioButton fSome=new JRadioButton("choose some file",true);
	
	JTable jF;
	JTable jcmOp;
	JTable jtmOp;
	JTable jemOp;
	JTable jxmOp;
	JTable jamOp;
	 JScrollPane jcmOpsp;
	JScrollPane jtmOpsp;
	JScrollPane jemOpsp;
	JScrollPane jamOpsp;
	JScrollPane jxmOpsp;
	JPanel opListPanel=new JPanel();
	Set<String> nameSet=new HashSet<String>();
	Set<String> opTypeSet=new HashSet<String>();
	Set<String> cmopSet=new HashSet<String>();
	Set<String> tmopSet=new HashSet<String>();
	Set<String> emopSet=new HashSet<String>();
	Set<String> amopSet=new HashSet<String>();
	Set<String> xmopSet=new HashSet<String>();
	
	JButton showSummary=new JButton("show the summary");
	JButton showChoosedMutant=new JButton("show choosed mutant");
	JButton runTest=new JButton("runTest");
	JToggleButton maxShow=new JToggleButton("Max the ShowPanel");
	SummaryOfMutantPanel summaryp=new SummaryOfMutantPanel();
	SummaryOfMutantPanel choosep=new SummaryOfMutantPanel();
	JScrollPane jsp=new JScrollPane(showPanel);
	JLabel[] jlArray=new JLabel[]
			{
			new JLabel("<html><font size=7>Welcome to the Android Mutants Analysis System</font></html>"),
			new JLabel("<html><font size=6>For better use,you should know follow things</font></html>"),
			new JLabel("<html><font size=6>(1) Know the workflow</font></html>"),
			new JLabel("<html><font size=6>(2) make sure your APP can be build by Gradle</font></html>"),
	 		};
	String filestring="F:/muandroid3/AndroidApp-master";
	
	chooseMutantJPanel()
	{
		getTable(); 
		functionPanel.setLayout(new BoxLayout(functionPanel,BoxLayout.LINE_AXIS));
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		functionPanel.add(listPanel);
		functionPanel.add(strategyPanel);
		functionPanel.setBorder(BorderFactory.createEtchedBorder());
		this.add(functionPanel);
		
		
		for(int i=0;i<jlArray.length;i++)
		{
	//		showPanel.add(jlArray[i]);
		}
		listPanel.setBorder(new TitledBorder("list Panel"));
		listPanel.setLayout(new BoxLayout(listPanel,BoxLayout.LINE_AXIS));
		listPanel.setPreferredSize(new Dimension(300,1200));
		listPanel.setMaximumSize(new Dimension(300,1200));
		listPanel.setMinimumSize(new Dimension(300,600));
		FileTableModel fTableModel = new FileTableModel(getNewTragetFiles(),"file");
		jF=new JTable(fTableModel);
		initFileColumnSizes(jF,fTableModel);
		JScrollPane jFsp=new JScrollPane(jF);
		listPanel.add(jFsp);
		jFsp.setPreferredSize(new Dimension(200,300));
		jFsp.setMaximumSize(new Dimension(200,1200));
		jFsp.setMinimumSize(new Dimension(200,600));
		
		opListPanel.setPreferredSize(new Dimension(80,1200));
		opListPanel.setMinimumSize(new Dimension(80,600));
		opListPanel.setLayout(new BoxLayout(opListPanel,BoxLayout.PAGE_AXIS));
		
		
		FileTableModel cmopTableModel = new FileTableModel(getNewTragetcmOps(),"cm op");
		 jcmOp=new JTable(cmopTableModel);
		 initColumnSizes(jcmOp,cmopTableModel);
		 jcmOpsp=new JScrollPane(jcmOp);
		FileTableModel tmopTableModel = new FileTableModel(getNewTragettmOps(),"tm op");
		 jtmOp=new JTable(tmopTableModel);
		 initColumnSizes(jtmOp,tmopTableModel);
		 
		 jtmOpsp=new JScrollPane(jtmOp);
		FileTableModel emopTableModel = new FileTableModel(getNewTragetemOps(),"em op");
		 jemOp=new JTable(emopTableModel);
		 initColumnSizes(jemOp,emopTableModel);
		 jemOpsp=new JScrollPane(jemOp);
		
		
		FileTableModel amopTableModel = new FileTableModel(getNewTragetamOps(),"am op");
		 jamOp=new JTable(amopTableModel);
		 initColumnSizes(jamOp,amopTableModel);
		 jamOpsp=new JScrollPane(jamOp);
		FileTableModel xmopTableModel = new FileTableModel(getNewTragetxmOps(),"xm op");
		 jxmOp=new JTable(xmopTableModel);
		 initColumnSizes(jxmOp,xmopTableModel);
		 jxmOpsp=new JScrollPane(jxmOp);
		 if(cmb.isEnabled())
			{
				opListPanel.add(jcmOpsp);
			}
			if(tmb.isEnabled())
				opListPanel.add(jtmOpsp);
			if(emb.isEnabled())
				opListPanel.add(jemOpsp);
			if(amb.isEnabled())
				opListPanel.add(jamOpsp);
			if(xmb.isEnabled())
				opListPanel.add(jxmOpsp);
		listPanel.add(opListPanel);
//		JPanel j=new JPanel();
//		listPanel.add(j);
		strategyPanel.setBorder(new TitledBorder("strategy Panel"));
		strategyPanel.setLayout(new BoxLayout(strategyPanel,BoxLayout.PAGE_AXIS));
		strategyPanel.setPreferredSize(new Dimension(220,1400));
		strategyPanel.setMaximumSize(new Dimension(220,1400));
		strategyPanel.setMinimumSize(new Dimension(220,600));
		JPanel chooseFilePanel=new JPanel();
		chooseFilePanel.setBorder(new TitledBorder("choose File"));
		chooseFilePanel.setPreferredSize(new Dimension(220,70));
		chooseFilePanel.setMaximumSize(new Dimension(220,70));
		ButtonGroup fbg=new ButtonGroup();
		
		fbg.add(fAll);
		fAll.addItemListener(this);
		fbg.add(fSome);
		fSome.addItemListener(this);
		chooseFilePanel.add(fAll);
		chooseFilePanel.add(fSome);
		strategyPanel.add(chooseFilePanel);
		JPanel chooseOpTypePanel=new JPanel();
		chooseOpTypePanel.setBorder(new TitledBorder("choose opType"));
		strategyPanel.add(chooseOpTypePanel);
		
		chooseOpTypePanel.setLayout(new BoxLayout(chooseOpTypePanel,BoxLayout.LINE_AXIS));
		chooseOpTypePanel.setPreferredSize(new Dimension(220,170));
		chooseOpTypePanel.setMaximumSize(new Dimension(220,170));
		JPanel checkBoxPanel=new JPanel();
		
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel,BoxLayout.PAGE_AXIS));
		//checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JPanel allnonePanel=new JPanel();
		allnonePanel.setLayout(new BoxLayout(allnonePanel,BoxLayout.PAGE_AXIS));
		//allnonePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		all.addActionListener(this);
		none.addActionListener(this);
		all.setPreferredSize(new Dimension(65,30));
		all.setMaximumSize(new Dimension(65,30));
		none.setPreferredSize(new Dimension(65,30));
		none.setMaximumSize(new Dimension(65,30));
		allnonePanel.add(Box.createVerticalGlue());
		
		allnonePanel.add(all);
		
		allnonePanel.add(Box.createVerticalGlue());
		allnonePanel.add(none);
		allnonePanel.add(Box.createVerticalGlue());
		chooseOpTypePanel.add(Box.createHorizontalGlue());
		chooseOpTypePanel.add(checkBoxPanel);
		chooseOpTypePanel.add(Box.createHorizontalGlue());
		chooseOpTypePanel.add(allnonePanel);
		chooseOpTypePanel.add(Box.createHorizontalGlue());
		
		checkBoxPanel.add(cmb);
		checkBoxPanel.add(tmb);
		checkBoxPanel.add(emb);
		checkBoxPanel.add(amb);
		checkBoxPanel.add(xmb);
		cmb.addItemListener(this);
		tmb.addItemListener(this);
		emb.addItemListener(this);
		amb.addItemListener(this);
		xmb.addItemListener(this);
		JPanel chooseOpPanel=new JPanel();
		chooseOpPanel.setBorder(new TitledBorder("choose mutant Op"));
		strategyPanel.add(chooseOpPanel);
		chooseOpPanel.setPreferredSize(new Dimension(220,70));
		chooseOpPanel.setMaximumSize(new Dimension(220,70));
		ButtonGroup opbg=new ButtonGroup();
		
		opbg.add(opAll);
		opAll.addItemListener(this);
		opbg.add(opSome);
		opSome.addItemListener(this);
		chooseOpPanel.add(opAll);
		chooseOpPanel.add(opSome);
		JPanel chooseStrategyPanel=new JPanel();
		chooseStrategyPanel.setBorder(new TitledBorder("choose the strategy"));
		strategyPanel.add(chooseStrategyPanel);
		
		
		ButtonGroup strategybg=new ButtonGroup();
		JRadioButton strategAll=new JRadioButton("Choose All the mutants",true);
		strategybg.add(strategAll);
		JRadioButton strateRadom=new JRadioButton("Radom choose mutants");
		strategybg.add(strateRadom);
		JRadioButton strateMore=new JRadioButton("will add More strategy.");
		strategybg.add(strateMore);
		
		
		
		
		chooseStrategyPanel.add(Box.createRigidArea(new Dimension(300,20)));
		
		JPanel radioPanel=new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel,BoxLayout.PAGE_AXIS));
		chooseStrategyPanel.add(radioPanel);
		
	
		radioPanel.add(strategAll);
	
		radioPanel.add(strateRadom);
		
		radioPanel.add(strateMore);
	
	
		this.showChoosedMutant.addActionListener(this);
		this.showSummary.addActionListener(this);
		this.runTest.addActionListener(this);
		maxShow.addActionListener(this);
		
		runPanel.setLayout(new BoxLayout(runPanel,BoxLayout.PAGE_AXIS));
		showPanel.setBorder(new TitledBorder("show Panel"));
		showPanel.setLayout(new BoxLayout(showPanel,BoxLayout.PAGE_AXIS));
		
		showPanel.add(summaryp);
		
		useagePanel.setLayout(new BoxLayout(useagePanel,BoxLayout.LINE_AXIS));
		useagePanel.add(maxShow);
		useagePanel.add(showSummary);
		useagePanel.add(showChoosedMutant);
		useagePanel.add(runTest);
		this.add(runPanel);
		runPanel.add(jsp);
		jsp.setWheelScrollingEnabled(true);
		runPanel.add(useagePanel);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		fAll.doClick();
		opAll.doClick();
		this.showChoosedMutant.doClick();
	}
	
	 

	private void getTable() {
		//System.out.println(xmb.getParent());
		// TODO Auto-generated method stub
		
		  getSummaryMutantfromXml gsm=new getSummaryMutantfromXml();
		  String[] args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml"};
			try {
				gsm.run( args);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  this.nameSet=gsm.nameSet;
			this.opTypeSet=gsm.opTypeSet;
			this.cmopSet=gsm.cmopSet;
			this.tmopSet=gsm.tmopSet;
			this.emopSet=gsm.emopSet;
			this.amopSet=gsm.amopSet;
			this.xmopSet=gsm.xmopSet;
			  
			
			
			cmb.setSelected(false);
			tmb.setSelected(false);
			emb.setSelected(false);
			amb.setSelected(false);
			xmb.setSelected(false);
			
			
			cmb.setEnabled(false);
			tmb.setEnabled(false);
			emb.setEnabled(false);
			amb.setEnabled(false);
			xmb.setEnabled(false);
			
		//	opListPanel.removeAll();
			for(String value:opTypeSet)
			{
				System.out.println(value);
				
				if(value.indexOf("classOp")>=0)
				{
					
					cmb.setEnabled(true);;
					cmb.setSelected(true);
				
				}
				if(value.indexOf("traditionalOp")>=0)
				{
					
					tmb.setEnabled(true);;
					tmb.setSelected(true);
					
				}
				if(value.indexOf("exceptionOp")>=0)
				{
					
					emb.setEnabled(true);;
					emb.setSelected(true);
					
				}
				if(value.indexOf("androidOp")>=0)
				{
					
					amb.setEnabled(true);;
					amb.setSelected(true);
					
				}
				if(value.indexOf("xmlOp")>=0)
				{
					
					xmb.setEnabled(true);;
					xmb.setSelected(true);
					
				}
				
			}
	}
	 private Vector getNewTragetcmOps() {
			// TODO Auto-generated method stub
		 Vector<String> vc=new Vector<String>();
		 vc.addAll(cmopSet);
		 return vc;
		}
	 private Vector getNewTragettmOps() {
			// TODO Auto-generated method stub
		 Vector<String> vc=new Vector<String>();
		 vc.addAll(tmopSet);
		 return vc;
		}
	 private Vector getNewTragetemOps() {
			// TODO Auto-generated method stub
		 Vector<String> vc=new Vector<String>();
		 vc.addAll(emopSet);
		 return vc;
		}
	 private Vector getNewTragetamOps() {
			// TODO Auto-generated method stub
		 Vector<String> vc=new Vector<String>();
		 vc.addAll(amopSet);
		 return vc;
		}
	 private Vector getNewTragetxmOps() {
			// TODO Auto-generated method stub
		 Vector<String> vc=new Vector<String>();
		 vc.addAll(xmopSet);
		 return vc;
		}
	private Vector<String> getNewTragetOps() {
		// TODO Auto-generated method stub
		  Vector<String> vc=new Vector<String>();
		if(cmb.isSelected())
		{
			vc.addAll(cmopSet);
		}
		if(tmb.isSelected())
		{
			vc.addAll(tmopSet);
		}
		if(emb.isSelected())
		{
			vc.addAll(emopSet);
		}
		if(amb.isSelected())
		{
			vc.addAll(amopSet);
		}
		if(xmb.isSelected())
		{
			vc.addAll(xmopSet);
		}
		return vc;
	}
	  private Vector<String> getNewTragetFiles() {
			
			Vector<String> vc=new Vector<String>();
			
			vc.addAll(nameSet);
			// TODO Auto-generated method stub
			return vc;
		}

	protected void initColumnSizes(JTable table, AbstractTableModel model)
	   {
	      initTripleColumnWidth(table, model, 30, 90, 80);
	   }
	  protected void initFileColumnSizes(JTable table, AbstractTableModel model) 
	   {
	      initTripleColumnWidth(table,model,30,700,80);
	   } 
	  protected void initTripleColumnWidth(JTable table, AbstractTableModel model, 
             int w1, int w2, int w3) 
	  {
		TableColumn column = null;
		
		for (int i = 0; i < table.getColumnCount(); i++) 
		{
			column = table.getColumnModel().getColumn(i);
			switch(i) 
			{
			case 0 :  column.setMaxWidth(w1);
			break;
			case 1 :  column.setMaxWidth(w2); 
			break;
			case 2 :  column.setMaxWidth(w3);
			break;
			}
		}
	  }
	

	public static void main(String[] args)
	{
		JFrame jf=new JFrame();
		chooseMutantJPanel fjp=new chooseMutantJPanel();
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
		
		if(e.getSource()==all)
		{
			if(cmb.isEnabled())
			cmb.setSelected(true);
			if(tmb.isEnabled())
			tmb.setSelected(true);
			if(emb.isEnabled())
			emb.setSelected(true);
			if(amb.isEnabled())
			amb.setSelected(true);
			if(xmb.isEnabled())
			xmb.setSelected(true);
		}
		if(e.getSource()==none)
		{
			if(cmb.isEnabled())
			cmb.setSelected(false);
			if(tmb.isEnabled())
			tmb.setSelected(false);
			if(emb.isEnabled())
			emb.setSelected(false);
			if(amb.isEnabled())
			amb.setSelected(false);
			if(xmb.isEnabled())
			xmb.setSelected(false);
		}
		if(e.getSource()==this.maxShow)
		{
			if(maxShow.isSelected())
			{
				maxShow.setBackground(Color.red);;
				maxShow.setText("ShowPanel is maxium");
				this.remove(this.functionPanel);
				this.validate();
				this.repaint();
			
			}
			else
			{
				maxShow.setBackground(null);;
				maxShow.setText("ShowPanel is normal");
				
				this.removeAll();
				this.add(this.functionPanel);
				
				this.add(this.runPanel);
				runPanel.validate();
				runPanel.repaint();
				showPanel.validate();
				showPanel.repaint();
				this.validate();
				this.repaint();
			}
		}
		if(e.getSource()==this.showSummary)
		{
			if(maxShow.isSelected())
			{}
			else
			{maxShow.doClick();}
				
				if(showPanel.getComponents().equals(this.summaryp))
				{}
				else
				{
					showPanel.removeAll();
					showPanel.setBorder(new TitledBorder("show the Summary"));
					showSummary.setBackground(Color.CYAN);
					showChoosedMutant.setBackground(null);
					showPanel.add(this.summaryp);
				}
				showPanel.validate();
				showPanel.repaint();
				jsp.validate();
				jsp.repaint();
			//	showSumAndChooseBt.setText("this Show the Summary");
		}
		if(e.getSource()==this.runTest)
		{
			ProgressJFrame2 pjf2=ProgressJFrame2.getInstance();
			pjf2.setVisible(true);
			pjf2.setSome(cmb.isSelected(), tmb.isSelected(), emb.isSelected(), amb.isSelected(), xmb.isSelected());
			FileTableModel jftM=(FileTableModel)jF.getModel();
			FileTableModel jcmOptM=(FileTableModel)jcmOp.getModel();
			FileTableModel jtmOptM=(FileTableModel)jtmOp.getModel();
			FileTableModel jemOptM=(FileTableModel)jemOp.getModel();
			FileTableModel jamOptM=(FileTableModel)jamOp.getModel();
			FileTableModel jxmOptM=(FileTableModel)jxmOp.getModel();
			pjf2.setSome2(jftM, jcmOptM, jtmOptM, jemOptM, jamOptM, jxmOptM);
			
			pjf2.strat();
//			System.out.println("runTest");
//			FileTableModel fTableModel = (FileTableModel)jF.getModel();
//  			String[] javafile_list = fTableModel.getSelectedFiles();
//  			for(int i=0;i<javafile_list.length;i++)
//  			{
//  				System.out.println(javafile_list[i]);
//  				if(cmb.isSelected())
//  				{
//  					
//  					fTableModel = (FileTableModel)jcmOp.getModel();
//  					String[] file_list = fTableModel.getSelectedFiles();
//  					if(file_list!=null)
//  					{
//  						for(int j=0;j<file_list.length;j++)
//  						{
//  							System.out.println(file_list[j]);
//  							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
//  							ReadMutantfromXml.main(args);
//  							mutantJavaAndXmlList.getInstance().readJavaList();
//  							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
//  							String tempfilename;
//  							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
//  							String ofilename;
//  							if(f_l.size()>0)
//  							{
//  								tempfilename=((String)f_l.get(0)).replace("\\", "/");
//  								if(tempfilename.indexOf("classOp")>=0)
//  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("classOp")-1);
//  								
//  								tempfilename=tempfilename+".java";
//  								 ofilename=tempfilename.replace("/mutant/", "/src/");
//  								String[] args2= new String[]{tempfilename2,ofilename};
//  								ReplaceOrginalWithMutant.main(args2);
//  								
//  								for(int k=0;k<f_l.size();k++)
//  	  							{
//  	  								
//  	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
//  	  								if(tempfilename.indexOf("classOp")>=0)
//  	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("classOp")-1);
//  	  								
//  	  								tempfilename=tempfilename+".java";
//  	  								
//  	  								 ofilename=tempfilename.replace("/mutant/", "/src/");
//  	  								System.out.println(ofilename);
//  	  								
//  	  								args2= new String[]{ofilename,(String) f_l.get(k)};
//  	  								ReplaceOrginalWithMutant.main(args2);
//  	  							}
//  								args2= new String[]{ofilename,tempfilename2};
//  								ReplaceOrginalWithMutant.main(args2);
//  								
//  								try{
//  									System.out.println("run test class Op>>>>>>>");
//  									 Process p = null;  
//  									 String line = null;  
//  									    BufferedReader stdout = null;  
//  									  
//  									    //list the files and directorys under C:\  
//  									    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
//  									    stdout = new BufferedReader(new InputStreamReader(p  
//  									      .getInputStream()));  
//  									    while ((line = stdout.readLine()) != null) {
//  									     System.out.println(line); 
//  									    
//  						                   //  Thread.sleep( 10 );
//  									    }  
//  									   
//  									    
//  									    stdout.close();  
//  									    p.destroy();
//  									      
//  									   } catch (Exception e1) {  
//  									    e1.printStackTrace();  
//  									   }  
//  							}
//  							
//  						}
//  					}
//  				}
//  				if(tmb.isSelected())
//  				{
//  					opTypeSet.add("traditionalOp");
//
//  					fTableModel = (FileTableModel)jtmOp.getModel();
//  					String[] file_list = fTableModel.getSelectedFiles();
//  					if(file_list!=null)
//  					{
//  						
//  						for(int j=0;j<file_list.length;j++)
//  						{
//  							System.out.println(file_list[j]);
//  							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
//  							ReadMutantfromXml.main(args);
//  							mutantJavaAndXmlList.getInstance().readJavaList();
//  							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
//  							String tempfilename;
//  							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
//  							String ofilename;
//  							
//  							if(f_l.size()>0)
//  							{
//  								tempfilename=((String)f_l.get(0)).replace("\\", "/");
//  								if(tempfilename.indexOf("traditionalOp")>=0)
//  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("traditionalOp")-1);
//  								
//  								tempfilename=tempfilename+".java";
//  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  								String[] args2= new String[]{tempfilename2,ofilename};
//  								ReplaceOrginalWithMutant.main(args2);
//  								for(int k=0;k<f_l.size();k++)
//  	  							{
//  	  								
//  	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
//  	  								if(tempfilename.indexOf("traditionalOp")>=0)
//  	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("traditionalOp")-1);
//  	  								tempfilename=tempfilename+".java";
//  	  								
//  	  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  	  								System.out.println(ofilename);
//  	  								args2= new String[]{ofilename,(String) f_l.get(k)};
//  	  								ReplaceOrginalWithMutant.main(args2);
//  	  							
//  	  							try{
//  	  							System.out.println("run test tradtional Op>>>>>>>");
//  	  							 Process p = null;  
//  	  							 String line = null;  
//  	  							    BufferedReader stdout = null;  
//  	  							  
//  	  							    //list the files and directorys under C:\  
//  	  							    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
//  	  							    stdout = new BufferedReader(new InputStreamReader(p  
//  	  							      .getInputStream()));  
//  	  							    while ((line = stdout.readLine()) != null) {
//  	  							     System.out.println(line); 
//  	  							    
//  	  				                   //  Thread.sleep( 10 );
//  	  							    }  
//  	  							   
//  	  							    
//  	  							    stdout.close();  
//  	  							    p.destroy();
//  	  							      
//  	  							   } catch (Exception e1) {  
//  	  							    e1.printStackTrace();  
//  	  							   }  
//  	  							}
//  								args2= new String[]{ofilename,tempfilename2};
//  								ReplaceOrginalWithMutant.main(args2);
//  							}
//  							
//  						}
//  					}
//  				}
//  				if(emb.isSelected())
//  				{
//  					opTypeSet.add("exceptionOp");
//  					fTableModel = (FileTableModel)jemOp.getModel();
//  					String[] file_list = fTableModel.getSelectedFiles();
//  					if(file_list!=null)
//  					{
//  						
//  						for(int j=0;j<file_list.length;j++)
//  						{
//  							System.out.println(file_list[j]);
//  							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
//  							ReadMutantfromXml.main(args);
//  							mutantJavaAndXmlList.getInstance().readJavaList();
//  							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
//  							String tempfilename;
//  							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
//  							String ofilename;
//  							if(f_l.size()>0)
//  							{
//  								tempfilename=((String)f_l.get(0)).replace("\\", "/");
//  								if(tempfilename.indexOf("exceptionOp")>=0)
//  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("exceptionOp")-1);
//  								
//  								tempfilename=tempfilename+".java";
//  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  								String[] args2= new String[]{tempfilename2,ofilename};
//  								ReplaceOrginalWithMutant.main(args2);
//  								for(int k=0;k<f_l.size();k++)
//  	  							{
//  	  								
//  	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
//  	  								if(tempfilename.indexOf("exceptionOp")>=0)
//  	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("exceptionOp")-1);
//  	  								tempfilename=tempfilename+".java";
//  	  								
//  	  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  	  								System.out.println(ofilename);
//  	  								args2= new String[]{ofilename,(String) f_l.get(k)};
//  	  								ReplaceOrginalWithMutant.main(args2);
//  	  								
//  	  							try{
//  	  							System.out.println("run test exception Op>>>>>>>");
//  	  							 Process p = null;  
//  	  							 String line = null;  
//  	  							    BufferedReader stdout = null;  
//  	  							  
//  	  							    //list the files and directorys under C:\  
//  	  							    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
//  	  							    stdout = new BufferedReader(new InputStreamReader(p  
//  	  							      .getInputStream()));  
//  	  							    while ((line = stdout.readLine()) != null) {
//  	  							     System.out.println(line); 
//  	  							    
//  	  				                   //  Thread.sleep( 10 );
//  	  							    }  
//  	  							   
//  	  							    
//  	  							    stdout.close();  
//  	  							    p.destroy();
//  	  							      
//  	  							   } catch (Exception e1) {  
//  	  							    e1.printStackTrace();  
//  	  							   }  
//  	  							}
//  								args2= new String[]{ofilename,tempfilename2};
//  								ReplaceOrginalWithMutant.main(args2);
//  							}
//  							
//  						}
//  					}
//  				}
//  				if(amb.isSelected())
//  				{
//  					opTypeSet.add("androidOp");
//  					fTableModel = (FileTableModel)jamOp.getModel();
//  					String[] file_list = fTableModel.getSelectedFiles();
//  					if(file_list!=null)
//  					{
//  						
//							
//  						for(int j=0;j<file_list.length;j++)
//  						{
//  							System.out.println(file_list[j]);
//  							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","all",file_list[j]};
//  							ReadMutantfromXml.main(args);
//  							mutantJavaAndXmlList.getInstance().readJavaList();
//  							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
//  							String tempfilename;
//  							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
//  							String ofilename;
//  							if(f_l.size()>0)
//  							{
//  								tempfilename=((String)f_l.get(0)).replace("\\", "/");
//  								if(tempfilename.indexOf("androidOp")>=0)
//  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("androidOp")-1);
//  								
//  								tempfilename=tempfilename+".java";
//  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  								String[] args2= new String[]{tempfilename2,ofilename};
//  								for(int k=0;k<f_l.size();k++)
//  	  							{
//  	  								
//  	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
//  	  								if(tempfilename.indexOf("androidOp")>=0)
//  	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("androidOp")-1);
//  	  								tempfilename=tempfilename+".java";
//  	  								
//  	  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  	  								System.out.println(ofilename);
//  	  								args2= new String[]{ofilename,(String) f_l.get(k)};
//  	  								ReplaceOrginalWithMutant.main(args2);
//  	  								
//  	  								
//  	  							try{
//  	  								
//  	  								System.out.println("run test Android Op>>>>>>>");
//  	  								Process p = null;  
//  	  								String line = null;  
//  	  							    BufferedReader stdout = null;  
//  	  							  
//  	  							    //list the files and directorys under C:\  
//  	  							    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
//  	  							    stdout = new BufferedReader(new InputStreamReader(p  
//  	  							      .getInputStream()));  
//  	  							    while ((line = stdout.readLine()) != null) {
//  	  							     System.out.println(line); 
//  	  							    
//  	  				                   //  Thread.sleep( 10 );
//  	  							    }  
//  	  							   
//  	  							    
//  	  							    stdout.close();  
//  	  							    p.destroy();
//  	  							  
//  	  							      
//  	  							   } catch (Exception e1) {  
//  	  							    e1.printStackTrace();  
//  	  							   }  
//  	  								
//  	  							}
//  								args2= new String[]{ofilename,tempfilename2};
//  								ReplaceOrginalWithMutant.main(args2);
//  							}
//  							
//  						}
//  					}
//  					}
//  				if(xmb.isSelected())
//  				{
//  					opTypeSet.add("xmlOp");
//  					fTableModel = (FileTableModel)jxmOp.getModel();
//  					String[] file_list = fTableModel.getSelectedFiles();
//  					if(file_list!=null)
//  					{
//  						for(int j=0;j<file_list.length;j++)
//  						{
//  							System.out.println(file_list[j]);
//  							String []args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",javafile_list[i],"all","classOp",file_list[j]};
//  							ReadMutantfromXml.main(args);
//  							mutantJavaAndXmlList.getInstance().readJavaList();
//  							List f_l=mutantJavaAndXmlList.getInstance().getJavaList();
//  							String tempfilename;
//  							String tempfilename2="F:/muandroid3/AndroidApp-master/app/temp.java";
//  							String ofilename;
//  							if(f_l.size()>0)
//  							{
//  								tempfilename=((String)f_l.get(0)).replace("\\", "/");
//  								if(tempfilename.indexOf("xmlOp")>=0)
//  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("xmlOp")-1);
//  								
//  								tempfilename=tempfilename+".java";
//  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  								String[] args2= new String[]{tempfilename2,ofilename};
//  								for(int k=0;k<f_l.size();k++)
//  	  							{
//  	  								
//  	  								tempfilename=((String)f_l.get(k)).replace("\\", "/");
//  	  								if(tempfilename.indexOf("xmlOp")>=0)
//  	  									tempfilename=tempfilename.substring(0, tempfilename.indexOf("xmlOp")-1);
//  	  								tempfilename=tempfilename+".java";
//  	  								
//  	  								ofilename=tempfilename.replace("/mutant/", "/src/");
//  	  								System.out.println(ofilename);
//  	  								args2= new String[]{ofilename,(String) f_l.get(k)};
//  	  								ReplaceOrginalWithMutant.main(args2);
//  	  								//Ö´ÐÐ²âÊÔ
//  	  								
//  	  							try{
//  	  							System.out.println("run test xml Op>>>>>>>");
//  	  							 Process p = null;  
//  	  							 String line = null;  
//  	  							 BufferedReader stdout = null;  
//  	  							  
//  	  							    //list the files and directorys under C:\  
//  	  							    p = Runtime.getRuntime().exec("F:/muandroid3/AndroidApp-master/gradlew.bat connectAndroidTest",null,new File("F:/muandroid3/AndroidApp-master") );  
//  	  							    stdout = new BufferedReader(new InputStreamReader(p  
//  	  							      .getInputStream()));  
//  	  							    while ((line = stdout.readLine()) != null) {
//  	  							     System.out.println(line); 
//  	  							    
//  	  				                   //  Thread.sleep( 10 );
//  	  							    }  
//  	  							   
//  	  							    
//  	  							    stdout.close();  
//  	  							    p.destroy();
//  	  							      
//  	  							   } catch (Exception e1) {  
//  	  							    e1.printStackTrace();  
//  	  							   }  
//  	  								
//  	  							}
//  								args2= new String[]{ofilename,tempfilename2};
//  								ReplaceOrginalWithMutant.main(args2);
//  							}
//  							
//  						}
  					//}
  			//	}
  			//}
  			
			
  			
  			
		//	String []args=null;
		//	ReadMutantfromXml.main(args);
			
			
		//	mutantJavaAndXmlList.getInstance().readJavaList();;
		}
		if(e.getSource()==this.showChoosedMutant)
		{
			if(maxShow.isSelected())
			{maxShow.doClick();}
			else
			{}
			
			showPanel.removeAll();
			showPanel.setBorder(new TitledBorder("show choosed mutant"));
			showSummary.setBackground(null);
			showChoosedMutant.setBackground(Color.CYAN);
			Set<String> nameSet=new HashSet<String>();
			
			Set<String> opTypeSet=new HashSet<String>();
			Set<String> cmopSet=new HashSet<String>();			
			Set<String> tmopSet=new HashSet<String>();
			Set<String> emopSet=new HashSet<String>();
			Set<String> amopSet=new HashSet<String>();
			Set<String> xmopSet=new HashSet<String>();	
			FileTableModel fTableModel = (FileTableModel)jF.getModel();
			String[] file_list = fTableModel.getSelectedFiles();
			if(file_list!=null)
			for(int i=0;i<file_list.length;i++)
			{
				nameSet.add(file_list[i]);	
			}
			System.out.println(nameSet.size());
			if(cmb.isSelected())
			{
				opTypeSet.add("classOp");
				fTableModel = (FileTableModel)jcmOp.getModel();
				file_list = fTableModel.getSelectedFiles();
				if(file_list!=null)
				for(int i=0;i<file_list.length;i++)
				{
					cmopSet.add(file_list[i]);
				} 
			}
			if(tmb.isSelected())
			{
				opTypeSet.add("traditionalOp");

				fTableModel = (FileTableModel)jtmOp.getModel();
				file_list = fTableModel.getSelectedFiles();
				if(file_list!=null)
				for(int i=0;i<file_list.length;i++)
				{
					tmopSet.add(file_list[i]);
				} 
			}
			if(emb.isSelected())
			{
				opTypeSet.add("exceptionOp");
				fTableModel = (FileTableModel)jemOp.getModel();
				file_list = fTableModel.getSelectedFiles();
				if(file_list!=null)
				for(int i=0;i<file_list.length;i++)
				{
					emopSet.add(file_list[i]);
				} 
			}
			if(amb.isSelected())
			{
				opTypeSet.add("androidOp");
				fTableModel = (FileTableModel)jamOp.getModel();
				file_list = fTableModel.getSelectedFiles();
				if(file_list!=null)
				for(int i=0;i<file_list.length;i++)
				{
					amopSet.add(file_list[i]);
				} 
			}
			if(xmb.isSelected())
			{
				opTypeSet.add("xmlOp");
				fTableModel = (FileTableModel)jxmOp.getModel();
				file_list = fTableModel.getSelectedFiles();
				if(file_list!=null)
				for(int i=0;i<file_list.length;i++)
				{
					xmopSet.add(file_list[i]);
				} 
			}
			
			
			
			
			
			
			SummaryOfMutantPanel choosep=new SummaryOfMutantPanel("F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",nameSet,opTypeSet,cmopSet,tmopSet,emopSet,amopSet,xmopSet);
			showPanel.add(choosep);
			}
			showPanel.validate();
			showPanel.repaint();
			jsp.validate();
			jsp.repaint();
			
			
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==fAll||e.getSource()==fSome)
		{
			if(fAll.isSelected())
			{
				FileTableModel table = (FileTableModel)jF.getModel();
			    table.setAllSelectValue(true);
			    jF.setModel(table);
			    jF.repaint();
			}
			else if(fSome.isSelected())
			{
				FileTableModel table = (FileTableModel)jF.getModel();
			    table.setAllSelectValue(false);
			    jF.setModel(table);
			    jF.repaint();
			}
		}
		if(e.getSource()==opAll||e.getSource()==opSome)
		{
			if(opAll.isSelected())
			{
				
				FileTableModel table = (FileTableModel)jcmOp.getModel();
			    table.setAllSelectValue(true);
			    jcmOp.setModel(table);
			    jcmOp.repaint();
			    table = (FileTableModel)jtmOp.getModel();
			    table.setAllSelectValue(true);
			    jtmOp.setModel(table);
			    jtmOp.repaint();
			     table = (FileTableModel)jemOp.getModel();
			    table.setAllSelectValue(true);
			    jemOp.setModel(table);
			    jemOp.repaint();
			    table = (FileTableModel)jamOp.getModel();
			    table.setAllSelectValue(true);
			    jamOp.setModel(table);
			    jamOp.repaint();
			    table = (FileTableModel)jxmOp.getModel();
			    table.setAllSelectValue(true);
			    jxmOp.setModel(table);
			    jxmOp.repaint();
			}
			else if(opSome.isSelected())
			{
				FileTableModel table = (FileTableModel)jcmOp.getModel();
			    table.setAllSelectValue(false);
			    jcmOp.setModel(table);
			    jcmOp.repaint();
			    table = (FileTableModel)jtmOp.getModel();
			    table.setAllSelectValue(false);
			    jtmOp.setModel(table);
			    jtmOp.repaint();
			     table = (FileTableModel)jemOp.getModel();
			    table.setAllSelectValue(false);
			    jemOp.setModel(table);
			    jemOp.repaint();
			    table = (FileTableModel)jamOp.getModel();
			    table.setAllSelectValue(false);
			    jamOp.setModel(table);
			    jamOp.repaint();
			    table = (FileTableModel)jxmOp.getModel();
			    table.setAllSelectValue(false);
			    jxmOp.setModel(table);
			    jxmOp.repaint();
			}
		}
		if(e.getSource()==cmb||e.getSource()==tmb||e.getSource()==emb||e.getSource()==amb||e.getSource()==xmb)
		{
//			if(e.getSource()==cmb)
//			System.out.println("cmb");
//			if(e.getSource()==tmb)
//				System.out.println("tmb");
//			if(e.getSource()==amb)
//				System.out.println("amb");
			opListPanel.removeAll();
			if(cmb.isSelected())
			opListPanel.add(jcmOpsp);
			if(tmb.isSelected())
			opListPanel.add(jtmOpsp);
			if(emb.isSelected())
			opListPanel.add(jemOpsp);
			if(amb.isSelected())
			opListPanel.add(jamOpsp);
			if(xmb.isSelected())
			opListPanel.add(jxmOpsp);
				
//			FileTableModel table = (FileTableModel)jcmOp.getModel();
//			
//			table= new FileTableModel(getNewTragetcmOps(),"op");
//			 jcmOp.setModel(table);
//			jcmOp.repaint();
//			
//			table= new FileTableModel(getNewTragettmOps(),"op");
//			 jtmOp.setModel(table);
//			jtmOp.repaint();
//			table= new FileTableModel(getNewTragetemOps(),"op");
//			 jemOp.setModel(table);
//			jemOp.repaint();
//			table= new FileTableModel(getNewTragetamOps(),"op");
//			 jamOp.setModel(table);
//			jamOp.repaint();
//			table= new FileTableModel(getNewTragetxmOps(),"op");
//			 jxmOp.setModel(table);
//			jxmOp.repaint();
			opListPanel.validate();
			opListPanel.repaint();
			opSome.doClick();
			opAll.doClick();;
		}
		
	}

}
