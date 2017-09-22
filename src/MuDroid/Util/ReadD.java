package MuDroid.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ReadD {
	static	File file=new File("F:/muAndroid/setting.xml");
	static FileWriter fw;
	 static
	 {
		try {
			fw=new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 };
	 public static void main(String[] args) throws IOException{
//		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//	        document.addComment("Date:"+df.format(new Date()));// new Date()为获取当前系统时间
//	        document.addComment("Created by Supermans1201");
		 	System.out.println("<project name=\"name\" depth=\"0\">");
	        tree("F:/muAndroid/exampleDir1",0);
	        System.out.println("</project>");
	    }
	  static int count=0;
	  static int lastcount=0;
	 public static void sysOutput(int count,File fileIndex)
	 {
		 for(int i=count;i>0;i--)
	         	System.out.print("\t");
		 if(fileIndex.isDirectory()){      	
         	System.out.println(count+":"+fileIndex.getName()+"\tDir");
         }
         else{
             System.out.println(count+":"+fileIndex.getName()+"\tFile");
         }
	 }
	    //显示目录的方法
	    public static int tree(String path,int c) throws IOException{
	    	count++;
	    	lastcount=c;
	    	File f = new File(path);
	    	
	    	// ArrayList<File> fileList = new ArrayList();
	        //判断传入对象是否为一个文件夹对象
	        if(f.isDirectory()){
	        	File[] files = f.listFiles();
	        	boolean a=false;
	            for(File fileIndex:files){
	            	a=true;
	            //	System.out.println((lastcount+0)+":"+count);
	            	if(lastcount+1==count)
                	{  
	            		;
                	}
	            	else
	            	{
	            		for(int j=1;j<=lastcount-count;j++)
	            		{for(int i=lastcount-j;i>0;i--)
	            			{
	            			System.out.print("\t");}
	            			System.out.println("</Dir>");
	            		}
	            	}
	                //判断文件列表中的对象是否为文件夹对象，如果是则执行tree递归，直到把此文件夹中所有文件输出为止
	                if(fileIndex.isDirectory()){
	                for(int i=count;i>0;i--)
	                {fw.append("\t");System.out.print("\t");   }  	
	                System.out.print("<Dir name=\""+fileIndex.getName()+"\" "+"depth=\""+count+"\">"); 	    
	                boolean b=false;//b为false表示不存在子目录
	                for(File subfile:fileIndex.listFiles())
	                {
	                	b=true;
//	                	System.out.print("<\\Dir>");
//	                	if(subfile.isDirectory())
//	                		{;}
//	                	else
//	                		{;}
	                }
	                if(!b)
	                {
	                	fw.append("<\\Dir>");
	                	System.out.println("</Dir>");
	                	
	                }
	                else
	                {
	                	fw.append("");
	                	System.out.println("");
	                }
	                	 
	                	
	                	//xmlOutput(count,fileIndex,file,"Dir");
	                    tree(fileIndex.getPath(),count);
	                }
	                else{
	                	 for(int i=count;i>0;i--)
	                	 {fw.append("\t");	System.out.print("\t");}
	                	 fw.append("<File name=\""+fileIndex.getName()+"\" "+"depth=\""+count+"\">"+"<\\File>");
	                	System.out.println("<File name=\""+fileIndex.getName()+"\" "+"depth=\""+count+"\">"+"</File>");
	                //	sysOutput(count,fileIndex);
	                	//xmlOutput(count,fileIndex,file,"File");
	                	lastcount=count;
	                }
	            }
	           
	        }
	        else{	
	        	
	        }
	        count--;
	        return 0;
	    }

}
