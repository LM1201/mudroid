package MuDroid.Util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import MuDroid.Compile.ReadFile;
import mujava.util.InheritanceINFO;

public class testRecordInheritanceRelationToXml {

	RecordInheritanceRelation r;
	
    List<String> jarList=new ArrayList<String>();
    InheritanceINFO[] classInfo = null;
    
    public static void main(String[] args) throws Exception {
    	
    	try {
			ReadFile.main(args);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	new testRecordInheritanceRelationToXml().main2(args);
    	//InheritanceRelation.getInstance().readJarsList();
   // 	InheritanceRelation.getInstance().readInheritanceInfo();
    }
	public  void main2(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		PrintWriter pw=new PrintWriter(System.out);
//		pw.println("12341");
//		pw.flush();
		args=new String[]{"F:/muandroid3/AndroidApp-master"};
		r=new RecordInheritanceRelationToXml();
	//	args=new String[]{"F:/muandroid3/zujian2android"};
		RecordInheritanceRelationToXml.main(args);
		
	}
}
