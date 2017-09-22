package MuDroid.Compile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompileAndroidWithGradle extends CompileAndroid
{
	int level=13;
	final String projectToXML="gradle";
	String projectFile="";
	final static String preDexDebugPath="/app/build/intermediates/pre-dexed/debug";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		args=new String[]{"F:/muandroid3/AndroidApp-master","-v"};
		run(new CompileAndroidWithGradle(),args);
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
	public static void lancher(String[] args) throws IOException
	{
		 // currentDirectory = SystemProperties.getInstance().getCurrentDir();
		String gradlehome= System.getenv("GRADLE_HOME");
		System.out.println("GRADLE_HOME = " +gradlehome);
		System.out.println(args[0]);
		File gradleHome=new File(args[0]);
		File f=new File(args[0]+"/app/out.txt");
		if(!f.exists())
		{
			deletePreDex(args);
			PrintStream out=System.out;	 
			f.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(f);
			PrintStream printStream = new PrintStream(fileOutputStream);
		   // InputStream inputStream=new InputStream(fileOutputStream);

			System.setOut(printStream);
			String [] arg=new String[]{"assembleDebug","-p","F:/muandroid3/AndroidApp-master","--info","--daemon","-Dorg.gradle.appname=\"123\""};
			BootstrapMainStarter bms=new BootstrapMainStarter();
			try {
				bms.startWrapper(arg, gradleHome);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\ndown");
			out.flush();
		}
		System.out.println("\ndone");
	}
	public static void deletePreDex(String[] args)
	{
		String path=args[0];
		for (File file : new File(path, preDexDebugPath).listFiles()) {
		      System.out.println(file);
		      if (file.exists())
		      {
		    	  file.delete();
		      }
		}
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

