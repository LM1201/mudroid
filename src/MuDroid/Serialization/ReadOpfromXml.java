package MuDroid.Serialization;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Visitor;
import org.dom4j.VisitorSupport;
import org.dom4j.io.OutputFormat;

import MuDroid.Singleton.Op;
import MuDroid.XmlMutation.DealXmlSax;


public class ReadOpfromXml extends DealXmlSax{
	
	List<String> classOp=new ArrayList <String>();
	List<String> traditionalOp=new ArrayList <String>();
	List<String> exceptionOp=new ArrayList <String>();
	List<String> androidOp=new ArrayList <String>();
	List<String> xmlOp=new ArrayList <String>();
	
	
	List<Element> element=new ArrayList <Element>();
	public String mutantPath=null;
	public String className=null;
	public String mutantOp[]=null;
	public final String originalName="original";
	public final String mutantName="mutant";
	public String mutantType=null;
	public static void main(String[] args) {
		args=new String[]{"F:/muandroid3/.op/op.xml"};
	    run(new ReadOpfromXml(), args);
	}

	public ReadOpfromXml() {
	}
	   
	protected void process(Document document) throws Exception 
	{
		getXMLWriter().write(document);
		getXMLWriter().flush();
		
		Visitor visit=new VisitorSupport()
		{
			public void visit(Element e)
			{
				if(e.getName().equals("classOp"))
				{
					element=e.elements();
					if(element!=null)
					for(int i=0;i<element.size();i++)
					{
						
						classOp.add(i, element.get(i).getName());
						System.out.println(classOp.get(i));
					}
				}
				if(e.getName().equals("traditionalOp"))
				{
					element=e.elements();
					if(element!=null)
					for(int i=0;i<element.size();i++)
					{
						
						traditionalOp.add(element.get(i).getName());
					}
				}
				if(e.getName().equals("exceptionOp"))
				{
					element=e.elements();
					if(element!=null)
					for(int i=0;i<element.size();i++)
					{
						exceptionOp.add(element.get(i).getName());
					}
				}
				if(e.getName().equals("androidOp"))
				{
					element=e.elements();
					if(element!=null)
					for(int i=0;i<element.size();i++)
					{
						androidOp.add(element.get(i).getName());
					}
				}
				if(e.getName().equals("xmlOp"))
				{
					element=e.elements();
					if(element!=null)
					for(int i=0;i<element.size();i++)
					{
						xmlOp.add(element.get(i).getName());
					}
				}
			}
			
		};
		document.accept(visit);
		
		Op.getInstance().readClassOp();
		System.out.println("before     .");
		setOP();
		System.out.println("after      .");
		Op.getInstance().readClassOp();
		
	}
	 protected void setFormat()
	{
    	format = OutputFormat.createPrettyPrint();
	}
	protected void setOP()
	{
		Op.getInstance().setClassOp(classOp);
		Op.getInstance().setTraditionalOp(traditionalOp);
		Op.getInstance().setExceptionOp(exceptionOp);
		Op.getInstance().setAndroidOp(androidOp);
		Op.getInstance().setXmlOp(xmlOp);
	}
}
