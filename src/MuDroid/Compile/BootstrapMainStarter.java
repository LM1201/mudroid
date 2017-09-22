package MuDroid.Compile;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class BootstrapMainStarter
{
	public void start(String[] args, File gradleHome) throws Exception 
	{
		File gradleJar = findLauncherJar(gradleHome);
		URLClassLoader contextClassLoader = new URLClassLoader(new URL[]{gradleJar.toURI().toURL()}, ClassLoader.getSystemClassLoader().getParent());
		Thread.currentThread().setContextClassLoader(contextClassLoader);
		Class<?> mainClass = contextClassLoader.loadClass("org.gradle.launcher.GradleMain");
		Method mainMethod = mainClass.getMethod("main", String[].class);
		mainMethod.invoke(null, new Object[]{args});
		
		System.out.println("333");
	}
	public void startWrapper(String[] args, File gradleHome) throws Exception {
		File gradleJar = findWrapperJar(gradleHome);
		URLClassLoader contextClassLoader = new URLClassLoader(new URL[]{gradleJar.toURI().toURL()}, ClassLoader.getSystemClassLoader().getParent());
		
		Thread.currentThread().setContextClassLoader(contextClassLoader);
		Class<?> mainClass = contextClassLoader.loadClass("org.gradle.wrapper.GradleWrapperMain");
		//System.out.println(">>>>>"+Thread.currentThread().getName());	
		Method mainMethod = mainClass.getMethod("main", String[].class);
		mainMethod.invoke(null, new Object[]{args});
		//System.out.println(">>>>>"+Thread.currentThread().getName());
		
	}
	private File findLauncherJar(File gradleHome) {
		for (File file : new File(gradleHome, "lib").listFiles()) {
			if (file.getName().matches("gradle-launcher-.*\\.jar")) {
				return file;
			}
		}
		throw new RuntimeException(String.format("Could not locate the Gradle launcher JAR in Gradle distribution '%s'.", gradleHome));
	}
	private File findWrapperJar(File gradleHome) {
		System.out.println("11"+gradleHome);
		for (File file : new File(gradleHome, "gradle/wrapper").listFiles()) {
			//   System.out.println(file);
			if (file.getName().matches("gradle-wrapper.jar")) {
				return file;
			}
		}
		throw new RuntimeException(String.format("Could not locate the Gradle launcher JAR in Gradle distribution '%s'.", gradleHome));
	}
	public static void main(String[] args){
		  
		// currentDirectory = SystemProperties.getInstance().getCurrentDir();
		//TODO Auto-generated method stub
		//String gradlehome= System.getenv("GRADLE_HOME");
		//System.out.println("GRADLE_HOME = " +gradlehome);
		//System.out.println(gradleHomeProperty);
		
		//String [] arg=new String[]{"build","-p","E:/gradle/www","--daemon","-Dorg.gradle.appname=\"123\""};
		
		Runnable r = new Runnable() {
			File gradleHome=new File("E:/gradle");
			String [] arg=new String[]{"-v"};
            @Override
            public void run() {
           	 
            	BootstrapMainStarter bms=new BootstrapMainStarter();
        		try 
        		{
        			bms.start(arg, gradleHome);
        			System.out.println("222");
        			
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
    	}
    };
    
       Thread t = new Thread(r);
       t.start();
		
		System.out.println("111");
	}
}
