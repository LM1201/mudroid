package MuDroid.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReplaceOrginalWithMutant extends ReadProjctDir {
	int level=13;
	final String projectToXML="projectToXMLs";
	String filterType="file";
	File orginalFile=null;
	File mutantFile=null;
	List<String> list=new ArrayList<String>();
	public List<String> getList()
	{
	    return this.list;
	}
	public static void main(String[] args) {
		
	//args=new String[]{"F:/muandroid3/AndroidApp-master/app/temp.xml","F:/muandroid3/AndroidApp-master/app/src/main/AndroidManifest.xml"};
		run(new ReplaceOrginalWithMutant(),args);
	}
	public void setFileType (String filterType)
	{
		this.filterType=filterType;
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
			orginalFile=new File(args[0]);
			mutantFile=new File(args[1]);
			
			
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");//�������ڸ�ʽ
			
			settingXmlName=args[0].substring(0, args[0].lastIndexOf("/"))+"/."+projectToXML+"/"+projectToXML+"_"+df.format(new Date())+".xml";
			File file=new File(settingXmlName);
			if(!file.getParentFile().exists())
				file.getParentFile().mkdirs();
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
		replace();
		if(writer.equals(null))
			 writer=createPrintWriter(null);
		File projectDir=new File(rootProject);
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		writer.println("<!-- Date:"+df.format(new Date())+" -->");// new Date()Ϊ��ȡ��ǰϵͳʱ��
		writer.println("<!-- Created by Supermans1201 -->\n");
		writer.println("<!-- rootPath:"+rootProject+" -->"); 
		writer.println("<"+projectDir.getName()+" path=\""+projectDir.getAbsolutePath()+"\""+" name=\""+projectDir.getName()+"\" depth=\"0\">");
		tree(rootProject,0);
		
		writer.println("</"+projectDir.getName()+">");
		writer.flush();
	}
	
	private void replace() throws IOException
	{
		// TODO Auto-generated method stub
		if(orginalFile.exists())
			orginalFile.delete();
		
		FileInputStream fis = new FileInputStream(mutantFile);
        FileOutputStream fos = new FileOutputStream(orginalFile);
 
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        buf=("\n").getBytes();
        fos.write(buf);
      
        fis.close();
        fos.close();
       
		System.out.println("delete>>"+orginalFile);
	}
	protected PrintWriter createPrintWriter(String xmlPath) throws Exception
	{
		//Ĭ���Լ�������·��
		if(level<0)
			level=0;
		if(level>13)
			level=13;
		xmlPath=xmlPath.substring(0,xmlPath.length()-".xml".length()-level)+"_"+filterType+".xml";
		//Ĭ���Լ�������·�� 
		File file = new File(xmlPath);
		return new PrintWriter(file);	 
	}
	 protected Boolean hasString(String file,String type)
	    {
	    	//System.out.println(file);
		 if(type=="")
			 return true;
	    	if(file.indexOf('.')>0)
	    	{
	    		//System.out.println(file.indexOf('.'));
	    	//	System.out.println(file.substring(file.indexOf('.')+1)+":"+type);
	    		if(file.substring(file.indexOf('.')+1).equals(type))
	    		{
	    	//	 System.out.println(file.substring(file.indexOf('.')));
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	protected String checkFile(String arg)
	{
		File file=new File(arg);
		if(file.isDirectory())
		{
			if(file.exists())
				return "Directory";
		}
		return "null";
	}
	int count=0;
	int lastcount=0;
	public int tree(String path,int c) throws IOException
	{
		count++;
		lastcount=c;
		File f = new File(path);
		if(f.isDirectory())
		{
			File[] files = f.listFiles();
			for(File fileIndex:files)
			{
				if(fileIndex.isDirectory())
				{
					tree(fileIndex.getPath(),count);
				}
				else
				{
					if(hasString(fileIndex.getName(),filterType))
					{
						for(int i=count;i>0;i--)
						{	 
							writer.print("\t");
						}
						list.add(fileIndex.getAbsolutePath());
					//	System.out.println(fileIndex.getAbsolutePath());
						writer.println("<File"+" path=\""+fileIndex.getAbsolutePath()+"\""+" name=\""+fileIndex.getName()+"\" "+"depth=\""+count+"\"/>");
						lastcount=count;
					}
				}
			}
		}
		count--;
		return 0;
	}

}
