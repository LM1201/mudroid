package MuDroid.Serialization;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import MuDroid.Singleton.Project;
import MuDroid.Util.RecordInheritanceRelation;

public class StateToXml extends RecordInheritanceRelation {
	int level=13;
	final String projectToXML="state";
	
	List<String> projectlist=null;
	boolean readproject=false;
	String selectProject="";
	
	
	
	
	
	public  static void  main(String[] args) {
		setOp();
		args=new String[]{"F:/muandroid3"};
		run(new StateToXml(),args);
	}
	
	public String run(String[] args) throws Exception 
	{
		if (args.length < 1) 
		{
			printUsage("\nargs[0]:项目根目录 args[1]:xml文件地址");
			return settingXmlName;//没有参数的话什么也不用做了
		}
		if(args.length>=1)
		{//可以扩展为多个文件同时获取。目前不需要
			if(checkFile(args[0]).toString().equals("null"))
			{
				System.out.println("目录不存在");
				//System.out.println(settingXmlName);
				return settingXmlName;//目录不存在就什么也不用做了
			}
		
			//如果存在，就在该目录下新建一个文件
			//分析的结果输出到目录中
			
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");//设置日期格式
			
			settingXmlName=args[0]+"/."+projectToXML+"/"+projectToXML+"_"+df.format(new Date())+".xml";
			File file=new File(settingXmlName);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
//			//不需要创建文件
//			file.createNewFile();
			
			writer = createPrintWriter(settingXmlName);
			process(args[0]);
			//return "成功";
		}
		//此处可以添加日志记录进行了什么操作
		System.out.println(settingXmlName);
		return settingXmlName;
		
	}
	
	protected void process(String rootProject) throws Exception 
	{
		recordOp();
		if(writer.equals(null))
			 writer=createPrintWriter(null);
		File projectDir=new File(rootProject);
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		writer.println("<!-- Date:"+df.format(new Date())+" -->");// new Date()为获取当前系统时间
		writer.println("<!-- Created by Supermans1201 -->\n");
		writer.println("<!-- rootPath:"+rootProject+" -->"); 
		writer.println("<"+projectDir.getName()+" path=\""+projectDir.getAbsolutePath()+"\">");
		writeOptoXml();
		writer.println("</"+projectDir.getName()+">");
		writer.flush();
	}
	protected PrintWriter createPrintWriter(String xmlPath) throws Exception
	{
		//默认以及创建好路径
		if(level<0)
			level=0;
		if(level>13)
			level=13;
		xmlPath=xmlPath.substring(0,xmlPath.length()-".xml".length()-level)+".xml";
		//默认以及创建好路径 
		File file = new File(xmlPath);
		return new PrintWriter(file);	 
	}
	protected String checkFile(String arg)
	{
		File file=new File(arg);
		if(file.isDirectory())
		{
			
			if(file.exists())
				return "Directory";
			//是目录并且存在 才返回Directory
		}
		return "null";
	}

	public void writeOptoXml() throws IOException
	{
	
		int num=0;
		if(projectlist!=null)
		{
			num=projectlist.size();
			writer.println("\t<selectProject name=\""+selectProject+"\"/>");
			writer.println("\t<projectlist readproject=\""+readproject+"\">");
			for(int i=0;i<projectlist.size();i++)
			{
				writer.println("\t\t<project name=\""+projectlist.get(i)+"\"/>");
			}	
			writer.println("\t</projectlist>");
		}
		else
		{
			num=0;
			writer.println("\t<selectProject name=\""+selectProject+"\"/>");
			writer.println("\t<projectlist readproject=\""+readproject+"\">");	
			writer.println("\t</projectlist>");
		}
		
		
	}
	
public  void recordOp() 
{
	selectProject=Project.getInstance().getSelectProject();
	projectlist=Project.getInstance().getProjectlist();
	readproject=Project.getInstance().getReadProject();
}
public  static void setOp() 
{
	Project.getInstance().setSelectProject("F:/muandroid3/AndroidApp-master");
	Project.getInstance().setReadProject(true);
	List<String> pl=new ArrayList<String>();
	pl.add("F:/muandroid3");
	pl.add("F:/muandroid3/AndroidApp-master");
	Project.getInstance().setProjectlist(pl);
	//Op.getInstance().setClassOp(null);
//	 classOp=Op.getInstance().getClassOp();
//	 traditionalOp=Op.getInstance().getTradtionalOp();
//	 exceptionOp=Op.getInstance().getExceptionOp();
//	 androidOp=Op.getInstance().getAndroidOp();
//	 xmlOp=Op.getInstance().getXmlOp();
}

}
