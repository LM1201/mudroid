package MuDroid.Util;

import java.util.ArrayList;
import java.util.List;

import MuDroid.Singleton.JavaAndXmlList;

public class testReadProjectDirToXmlWithFilter {

	ReadProjctDir r;
	List<String> javaList=new ArrayList<String>();
    List<String> xmlList=new ArrayList<String>();
    
    public static void main(String[] args) throws Exception {
    	
    	new testReadProjectDirToXmlWithFilter().main2(args);
    	JavaAndXmlList.getInstance().readJavaList();
    	JavaAndXmlList.getInstance().readXmlList();
    }
	public  void main2(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		PrintWriter pw=new PrintWriter(System.out);
//		pw.println("12341");
//		pw.flush();
		args=new String[]{"F:/muandroid3/AndroidApp-master"};
		
		String javaarg0=args[0]+"/app/src/main";
		String xmlarg0=args[0]+"/app/src/main";
		r=new ReadProjctDirToXmlWithFilter();
		args=new String[]{javaarg0,"java"};
		((ReadProjctDirToXmlWithFilter) r).run(args);
		javaList=((ReadProjctDirToXmlWithFilter) r).getList();
		r=new ReadProjctDirToXmlWithFilter();
		args=new String[]{xmlarg0,"xml"};
		((ReadProjctDirToXmlWithFilter) r).run(args);
		xmlList=((ReadProjctDirToXmlWithFilter) r).getList();
		JavaAndXmlList.getInstance().setJavaList(javaList);
		JavaAndXmlList.getInstance().setXmlList(xmlList);
	}
}
