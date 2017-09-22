package MuDroid.Singleton;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import mujava.util.InheritanceINFO;

public class mutantJavaAndXmlList {
	private static mutantJavaAndXmlList instance;  
    private mutantJavaAndXmlList(){}        
    

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
    public void removeJavaList()
    {
    	this.javaList.removeAll(javaList);
    }
    public List<String> getxmlList()
    {
    	return this.xmlList;
    }
    public static mutantJavaAndXmlList getInstance(){  
        if (instance==null){  
            instance=new mutantJavaAndXmlList();  
        }  
        return instance;  
    }
}
