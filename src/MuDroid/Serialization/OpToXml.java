package MuDroid.Serialization;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import MuDroid.Singleton.Op;
import MuDroid.Util.RecordInheritanceRelation;

public class OpToXml extends RecordInheritanceRelation {
	int level=13;
	final String projectToXML="op";
	
	List<String> classOp=null;
	List<String> traditionalOp=null;
	List<String> exceptionOp=null;
	List<String> androidOp=null;
	List<String> xmlOp=null;
	
	
	
	
	
	public  static void  main(String[] args) {
		setOp();
		args=new String[]{"F:/muandroid3"};
		run(new OpToXml(),args);
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
			process(args[0]);
			//return "�ɹ�";
		}
		//�˴����������־��¼������ʲô����
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		writer.println("<!-- Date:"+df.format(new Date())+" -->");// new Date()Ϊ��ȡ��ǰϵͳʱ��
		writer.println("<!-- Created by Supermans1201 -->\n");
		writer.println("<!-- rootPath:"+rootProject+" -->"); 
		writer.println("<"+projectDir.getName()+" path=\""+projectDir.getAbsolutePath()+"\">");
		writeOptoXml();
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
		xmlPath=xmlPath.substring(0,xmlPath.length()-".xml".length()-level)+".xml";
		//Ĭ���Լ�������·�� 
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
			//��Ŀ¼���Ҵ��� �ŷ���Directory
		}
		return "null";
	}

	public void writeOptoXml() throws IOException
	{
	
		int num=0;
		if(classOp!=null)
		{
			num=classOp.size();
			writer.println("\t<classOp num=\""+num+"\">");
			for(int i=0;i<classOp.size();i++)
			{
				writer.println("\t\t<"+classOp.get(i)+"/>");
			}	
			writer.println("\t</classOp>");
		}
		else
		{
			num=0;
			writer.println("\t<classOp num=\""+num+"\">");	
			writer.println("\t</classOp>");
		}
		if(traditionalOp!=null)
		{
			num=traditionalOp.size();
			writer.println("\t<traditionalOp num=\""+num+"\">");
			for(int i=0;i<traditionalOp.size();i++)
			{
				writer.println("\t\t<"+traditionalOp.get(i)+"/>");
			}	
			writer.println("\t</traditionalOp>");
		}
		else
		{
			num=0;
			writer.println("\t<traditionalOp num=\""+num+"\">");	
			writer.println("\t</traditionalOp>");
		}
		if(exceptionOp!=null)
		{
			num=exceptionOp.size();
			writer.println("\t<exceptionOp num=\""+num+"\">");
			for(int i=0;i<exceptionOp.size();i++)
			{
				writer.println("\t\t<"+exceptionOp.get(i)+"/>");
			}	
			writer.println("\t</exceptionOp>");
		}
		else
		{
			num=0;
			writer.println("\t<exceptionOp num=\""+num+"\">");	
			writer.println("\t</exceptionOp>");
		}
		if(androidOp!=null)
		{
			num=androidOp.size();
			writer.println("\t<androidOp num=\""+num+"\">");
			for(int i=0;i<androidOp.size();i++)
			{
				writer.println("\t\t<"+androidOp.get(i)+"/>");
			}	
			writer.println("\t</androidOp>");
		}
		else
		{
			num=0;
			writer.println("\t<androidOp num=\""+num+"\">");	
			writer.println("\t</androidOp>");
		}
		if(xmlOp!=null)
		{
			num=xmlOp.size();
			writer.println("\t<xmlOp num=\""+num+"\">");
			for(int i=0;i<xmlOp.size();i++)
			{
				writer.println("\t\t<"+xmlOp.get(i)+"/>");
			}	
			writer.println("\t</xmlOp>");
		}
		else
		{
			num=0;
			writer.println("\t<xmlOp num=\""+num+"\">");	
			writer.println("\t</xmlOp>");
		}
		
	}
	
public  void recordOp() 
{
	 classOp=Op.getInstance().getClassOp();
	 traditionalOp=Op.getInstance().getTradtionalOp();
	 exceptionOp=Op.getInstance().getExceptionOp();
	 androidOp=Op.getInstance().getAndroidOp();
	 xmlOp=Op.getInstance().getXmlOp();
}
public  static void setOp() 
{
	Op.getInstance().setClassOp(null);
//	 classOp=Op.getInstance().getClassOp();
//	 traditionalOp=Op.getInstance().getTradtionalOp();
//	 exceptionOp=Op.getInstance().getExceptionOp();
//	 androidOp=Op.getInstance().getAndroidOp();
//	 xmlOp=Op.getInstance().getXmlOp();
}

}
