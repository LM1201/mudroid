package MuDroid.JavaMutation;

import java.util.ArrayList;
import java.util.List;

import MuDroid.Compile.ReadFile;
import MuDroid.Singleton.JavaAndXmlList;
import MuDroid.Util.testReadProjectDirToXmlWithFilter;
import MuDroid.Util.testRecordInheritanceRelationToXml;

public class testDealJavaOJMutant {
	
    List<String> javaList=new ArrayList<String>();
    
    public static void main(String[] args) throws Exception {
    	
    	new testDealJavaOJMutant().main2(args);
    }
	public  void main2(String[] args) throws Exception {
		
		args=new String[]{"F:/muandroid3/AndroidApp-master"};
		
		ReadFile.main(null);
		testRecordInheritanceRelationToXml.main(args);
		testReadProjectDirToXmlWithFilter.main(args);
		
		
		javaList=JavaAndXmlList.getInstance().getJavaList();
		System.out.println("*************");
		JavaAndXmlList.getInstance().readJavaList();
		System.out.println("*************");
		//Op.getInstance();
		for(int i=0;i<javaList.size();i++)
		{
			args[0]=javaList.get(i);
//			File f=new File(args[0]+".log");
//			FileOutputStream fos= new FileOutputStream(f);
//			PrintStream ps=new PrintStream(fos);
			//System.setOut(ps);
			//System.setErr(ps);
			System.out.println("arg[0]:"+args[0]);
	//	DealJavaOJMutantClass.main(args);
				DealJavaOJMutantException.main(args);
	//	DealJavaOJMutantTradtional.main(args);
	//		DealJavaOJMutantAndroid.main(args);
		}
		// TODO Auto-generated method stub
	}
}
