package MuDroid.Compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class TestAndroidWithGradle extends CompileAndroid {
	int level=13;
	final String projectToXML="gradle";
	String projectFile="";
	
	public TestAndroidWithGradle()
	{
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		args=new String[]{"F:/muandroid3/AndroidApp-master","build"};
//		run(new TestAndroidWithGradle(),args);
//		
		args=new String[]{"F:/muandroid3/AndroidApp-master","connectAndroidTest"};
		run(new TestAndroidWithGradle(),args);

		//run(new ReadProjctDirToXml(),args);
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
			process(args);
			//return "成功";
		}
		//此处可以添加日志记录进行了什么操作
		System.out.println(settingXmlName);
		return settingXmlName;	
	}
	public static void lancher(String[] args) throws IOException{
		  
		 // currentDirectory = SystemProperties.getInstance().getCurrentDir();
		// TODO Auto-generated method stub
		
	    
		
		File gradleHome=new File(args[0]);
		String [] arg=new String[]{args[1],"-p",args[0],"--daemon","-Dorg.gradle.appname=\"123\""};
		BootstrapMainStarter bms=new BootstrapMainStarter();
			try {
				bms.startWrapper(arg, gradleHome);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("\ndown");
			
		
		System.out.println("\ndone");
	}
	
	protected void process(String[] args ) throws Exception 
	{
		if(writer.equals(null))
			 writer=createPrintWriter(null);
		File projectDir=new File(args[0]);
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		writer.println("<!-- Date:"+df.format(new Date())+" -->");// new Date()为获取当前系统时间
		writer.println("<!-- Created by Supermans1201 -->\n");
		writer.println("<!-- rootPath:"+args[0]+" -->"); 
		writer.println("<"+projectDir.getName()+" path=\""+projectDir.getAbsolutePath()+"\""+" name=\""+projectDir.getName()+"\" depth=\"0\">");
		
		lancher(args);
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
		xmlPath=xmlPath.substring(0,xmlPath.length()-".xml".length()-level)+".txt";
		//默认以及创建好路径 
		File file = new File(xmlPath);
		return new PrintWriter(file);	 
	}


}
