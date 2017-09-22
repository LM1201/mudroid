package MuDroid.Serialization;

import java.util.ArrayList;
import java.util.List;

import MuDroid.Singleton.JavaAndXmlList;
import MuDroid.Singleton.mutantJavaAndXmlList;
import MuDroid.Util.testReadProjectDirToXmlWithFilter;

public class testDealXmlSaxMutant 
{
	List<String> javaList=new ArrayList<String>();
	public static void main(String[] args) throws Exception 
	{	
		new testDealXmlSaxMutant().main2(args);
	}
	public  void main2(String[] args) throws Exception
	{
		args=new String[]{"null"};
		///testRecordInheritanceRelationToXml.main(args);
		testReadProjectDirToXmlWithFilter.main(args);	
		javaList=JavaAndXmlList.getInstance().getJavaList();
		System.out.println("*************");
		//JavaAndXmlList.getInstance().readJavaList();
		System.out.println("*************");
		if(javaList!=null)
		{
			for(int i=0;i<javaList.size();i++)
			{
				args[0]=javaList.get(i);
				args[0]=args[0].replace("\\", "/");
				args[0]=args[0].substring(args[0].lastIndexOf("/")+1);
				System.out.println("arg[0]:"+args[0]);
				args=new String[]{"F:/muandroid3/AndroidApp-master/app/mutant/main/.projectToXMLs/projectToXMLs_java.xml",args[0],"all","classOp","all"};
				ReadMutantfromXml.main(args);
				mutantJavaAndXmlList.getInstance().readJavaList();
				//DealXmlSaxMutant.main(args);
			}
		}	
	}
}
