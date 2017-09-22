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
			printUsage("\nargs[0]:��Ŀ��Ŀ¼ args[1]:xml�ļ���ַ");
			return settingXmlName;//û�в����Ļ�ʲôҲ��������
		}
		if(args.length>=1)
		{//������չΪ����ļ�ͬʱ��ȡ��Ŀǰ����Ҫ
			if(checkFile(args[0]).toString().equals("null"))
			{
				System.out.println("Ŀ¼������");
				//System.out.println(settingXmlName);
				return settingXmlName;//Ŀ¼�����ھ�ʲôҲ��������
			}
			//������ڣ����ڸ�Ŀ¼���½�һ���ļ�
			//�����Ľ�������Ŀ¼��
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");//�������ڸ�ʽ
			
			settingXmlName=args[0]+"/."+projectToXML+"/"+projectToXML+"_"+df.format(new Date())+".xml";
			File file=new File(settingXmlName);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
//			//����Ҫ�����ļ�
//			file.createNewFile();
			
			writer = createPrintWriter(settingXmlName);
			process(args);
			//return "�ɹ�";
		}
		//�˴����������־��¼������ʲô����
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		writer.println("<!-- Date:"+df.format(new Date())+" -->");// new Date()Ϊ��ȡ��ǰϵͳʱ��
		writer.println("<!-- Created by Supermans1201 -->\n");
		writer.println("<!-- rootPath:"+args[0]+" -->"); 
		writer.println("<"+projectDir.getName()+" path=\""+projectDir.getAbsolutePath()+"\""+" name=\""+projectDir.getName()+"\" depth=\"0\">");
		
		lancher(args);
		writer.println("</"+projectDir.getName()+">");
		writer.flush();
	}
	
	protected PrintWriter createPrintWriter(String xmlPath) throws Exception
	{
		//Ĭ���Լ�������·��
		if(level<0)
			level=0;
		if(level>13)
			level=13;
		xmlPath=xmlPath.substring(0,xmlPath.length()-".xml".length()-level)+".txt";
		//Ĭ���Լ�������·�� 
		File file = new File(xmlPath);
		return new PrintWriter(file);	 
	}


}
