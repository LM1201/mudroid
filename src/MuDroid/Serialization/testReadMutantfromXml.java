package MuDroid.Serialization;

import java.util.ArrayList;
import java.util.List;

import MuDroid.Singleton.JavaAndXmlList;
import MuDroid.Util.testReadProjectDirToXmlWithFilter;
import MuDroid.Util.testRecordInheritanceRelationToXml;
import MuDroid.XmlMutation.DealXmlSaxMutant;

public class testReadMutantfromXml 
{
	List<String> xmlList=new ArrayList<String>();
	public static void main(String[] args) throws Exception 
	{	
		new testReadMutantfromXml().main2(args);
	}
	public  void main2(String[] args) throws Exception
	{
		args=new String[]{"src"};
		testRecordInheritanceRelationToXml.main(args);
		testReadProjectDirToXmlWithFilter.main(args);	
		xmlList=JavaAndXmlList.getInstance().getxmlList();
		System.out.println("*************");
		//JavaAndXmlList.getInstance().readJavaList();
		System.out.println("*************");
		if(xmlList!=null)
		{
			for(int i=0;i<xmlList.size();i++)
			{
				args[0]=xmlList.get(i);
				System.out.println("arg[0]:"+args[0]);
				DealXmlSaxMutant.main(args);
			}
		}	
	}
}
