package MuDroid.Singleton;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import mujava.util.InheritanceINFO;

public class JavaAndXmlList {
	private static JavaAndXmlList instance;  
    private JavaAndXmlList(){}        
    
    List<String> javaList=new ArrayList<String>();
    List<String> xmlList=new ArrayList<String>();
    
    public void setJavaList(List<String> javaList)
    {
    	this.javaList=javaList;
    }
    public void readJavaList()
    {
    	for(int i=0;i<javaList.size();i++)
    		System.out.println(javaList.get(i));
    }
    public void readXmlList()
    {
    	for(int i=0;i<xmlList.size();i++)
    		System.out.println(xmlList.get(i));
    }
    public void setXmlList(List<String> xmlList)
    {
    	this.xmlList=xmlList;
    }
    public List<String> getJavaList()
    {
    	return this.javaList;
    }
    public List<String> getxmlList()
    {
    	return this.xmlList;
    }
    public static JavaAndXmlList getInstance(){  
        if (instance==null){  
            instance=new JavaAndXmlList();  
        }  
        return instance;  
    }
}
