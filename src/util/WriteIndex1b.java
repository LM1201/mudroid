package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
//import net.sf.json.JSONArray;






import singleton.Project;

public class WriteIndex1b {

	static int buildfailed = 0;
	static int buildsuccess = 0;
	static int number = 0;
	static float time = 0;
	static boolean start = false;
	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<String,Integer>file0=new HashMap();
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	static Map<String,Integer> op0=new HashMap(){};
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	static Map<String,Integer> opType0=new HashMap(){};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Map<String,Integer>file=new HashMap();
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	static Map<String,Integer> op=new HashMap(){};
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	static Map<String,Integer> opType=new HashMap(){};
	
	public static void main(String[] args) {
		try {
			// read file content from file
			StringBuffer sb = new StringBuffer("");
			Project.getInstance().setSelectProject("F:/EspressoExamples-master/EspressoExamples-master");

			FileReader reader = new FileReader(
					Project.getInstance().getRunTestLogPath());
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			String [] info=null;
			while ((str = br.readLine()) != null) {
				if (str.startsWith(":app")) {
					skip();
				} else {
					if (str.startsWith("#")) {
						number++;
						info=str.substring(1).split(" ");
						
						file0.put(info[0],1+ file0.getOrDefault(info[0], 0));
						op0.put(info[1],1+ op0.getOrDefault(info[1], 0));
//						System.out.println(info[2]);
						if(info[2].indexOf("androidOp")>=0)
						{
							opType0.put("androidOp",1+ opType0.getOrDefault("androidOp", 0));
						}
						if(info[2].indexOf("classOp")>=0)
						{
							opType0.put("classOp",1+ opType0.getOrDefault("classOp", 0));
						}
						if(info[2].indexOf("traditionalOp")>=0)
						{
							opType0.put("traditionalOp",1+ opType0.getOrDefault("traditionalOp", 0));
						}
						if(info[2].indexOf("exceptionOp")>=0)
						{
							opType0.put("exceptionOp",1+ opType0.getOrDefault("exceptionOp", 0));
						}
						if(info[2].indexOf("xmlOp")>=0)
						{
							opType0.put("xmlOp",1+ opType0.getOrDefault("xmlOp", 0));
						}
//						for (String s :info)
//						{
//							
//							System.out.println(s);
//						}
						sb.append(str + "\n");
						// System.out.print(str);
					}

					Pattern p1 = Pattern.compile("Total time: (.*?) secs");
					Matcher m1 = p1.matcher(str);
					if (m1.find()) {
						String t = m1.group(1);
						sb.append("时间："+ t + "secs \n");
						sb.append("--------------------------------------------------------------------------------end\n\n");
						if (t.indexOf(" mins ") > 0) {
							float min = Float.parseFloat(t.substring(0,
									t.indexOf(" mins ")));
							float sec = Float.parseFloat(t.substring(t
									.indexOf("mins ") + 5));
							// s System.out.print(t+"mmmm"+min+"mmm"+sec);
							time += min * 60 + sec;
						} else {
							time += Float.parseFloat(t);
						}
					}

					Pattern pattern = Pattern.compile("Starting.*tests on",
							Pattern.CASE_INSENSITIVE);

					Matcher matcher = pattern.matcher(str);
					if (matcher.find()) {
						start = true;
						file.put(info[0],1+ file.getOrDefault(info[0], 0));
						op.put(info[1],1+ op.getOrDefault(info[1], 0));
//						System.out.println(info[2]);
						if(info[2].indexOf("androidOp")>=0)
						{
							opType.put("androidOp",1+ opType.getOrDefault("androidOp", 0));
						}
						if(info[2].indexOf("classOp")>=0)
						{
							opType.put("classOp",1+ opType.getOrDefault("classOp", 0));
						}
						if(info[2].indexOf("traditionalOp")>=0)
						{
							opType.put("traditionalOp",1+ opType.getOrDefault("traditionalOp", 0));
						}
						if(info[2].indexOf("exceptionOp")>=0)
						{
							opType.put("exceptionOp",1+ opType.getOrDefault("exceptionOp", 0));
						}
						if(info[2].indexOf("xmlOp")>=0)
						{
							opType.put("xmlOp",1+ opType.getOrDefault("xmlOp", 0));
						}
						sb.append("start--------------------------------------------------------------------------------\n");
					}
					else
					{
						
					}
					if (start) {
						if (str.startsWith("BUILD")) {
							if (str.equals("BUILD FAILED")) {
								buildfailed++;
							} else if (str.equals("BUILD SUCCESSFUL")) {
								buildsuccess++;
							}
							start = false;
							sb.append(str + "\n");
							
						} else {
							sb.append(str + "\n");

						}

					}

				}

			}
			br.close();
			reader.close();
			// write string to file
			
			
			for(String key:op.keySet())
			{System.out.println(key+","+op.getOrDefault(key, 0));}
			
			for(String key:file.keySet())
			{System.out.println(key+","+file.getOrDefault(key, 0));}
			
			for(String key:opType.keySet())
			{System.out.println(key+","+opType.getOrDefault(key, 0));}
			
			float hour = time / 3600;
			float min = time / 60 % 60;
			float sec = (float) (int) ((time % 60) * 100) / 100;

			// System.out.println(time);
			float a = (float) (buildfailed + buildsuccess) / number;
			float b = (float) buildfailed / (buildfailed + buildsuccess);

			sb.append(str + "/n");
			System.out.println("一共测试了" + number + "个变异体程序，其中构建成功并测试的有"
					+ (buildfailed + buildsuccess) + "个,比例为"
					+ (float) (int) (a * 10000) / 100 + "%");
			System.out.print("测试用例检测出来：" + buildfailed + "个");
			System.out.print("未检测出来：" + buildsuccess + "个");
			System.out.println("变异得分为" + (float) (int) (b * 10000) / 100);

			System.out.println("用时：" + (int) hour + " h " + (int) min + " min "
					+ sec + " sec . ");
			
			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class",
					ClasspathResourceLoader.class.getName());

			ve.init();
			Template t = ve.getTemplate("indexChart.vm");
			VelocityContext ctx = new VelocityContext();

			System.out.println();
			
			ctx.put("keylist1", getKey(op0.keySet()));
			ctx.put("datalist1", getData(op0));
			
			ctx.put("keylist2", getKey(opType0.keySet()));
			ctx.put("datalist2", getData(opType0));
			
			
			
			ctx.put("keylist3", getKey(op.keySet()));
			ctx.put("datalist3", getData(op));
			
			ctx.put("keylist4", getKey(opType.keySet()));
			ctx.put("datalist4", getData(opType));
			
			ctx.put("k1", getKey(file.keySet()));
			ctx.put("v1", getVal(file.values()));
			ctx.put("k2", getKey(file0.keySet()));
			ctx.put("v2", getVal(file0.values()));
			
			ctx.put("w1","变异算子");
			ctx.put("w2", "变异算子类型");
			ctx.put("w3", "文件数");
			ctx.put("thevalue",(float) (int) (b * 10000) / 100);
			
			 
			 
			StringWriter sw = new StringWriter();

			t.merge(ctx, sw);


			File indexfile = new File(
					Project.getInstance().getConfigDir()+"/html/assets/js/charts/indexChart.js");
		
			indexfile.delete();
			indexfile.createNewFile();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(indexfile), "UTF-8"));

			bw.write(sw.toString());

			bw.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Object getVal(Collection<Integer> set) {
		// TODO Auto-generated method stub
		String s="";
		
		for(Integer key : set)
		{
			s+=""+key+",";
		}
		System.out.println("ssssssssssssss"+s);
		String  keylist="["+s.substring(0,s.length()-1)+"]";
		return keylist;
		
	}

	public static String getData(Map<String, Integer> op2)
	{
		String s="";
		String s2="";
		for(String key :op2.keySet())
		{ 
			s="{value:"+op2.get(key)+","+"name:"+"'"+key+"'}";
			System.out.println(s);
			s2+=s+",\n";
		}
		String datalist="["+s2.substring(0,s2.length())+"]";
		return datalist;
	}
	public static String getKey(Set<String> set)
	{
		String s="";
		
		for(String key : set)
		{
			s+="'"+key+"',";
		}
		System.out.println("ssssssssssssss"+s);
		String  keylist="["+s.substring(0,s.length()-1)+"]";
		return keylist;
	}
	private static void skip() {
		// TODO Auto-generated method stub

	}

}
