package com.tadu.book.action.pushbookaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login {
	/**
	 * 定义两个请求参数，分别为用户名和密码
	 */
	//CP务必要输入正确的用户名和密码
	private String username="ceshi";
	private String password="111111";
	//请求的地址(URL)
	String baseUrl="http://solomotest6.3g.qq.com/TTCPPlatform/";
	String param="username="+username+"&password="+password;
	private Login(){
		StringBuffer natUrl=new StringBuffer(baseUrl);
		natUrl.append("push/login");
		//natUrl.append("?"+param);
		String result=sendPost(natUrl.toString());
		if(result==null||result.equals("")){
			System.out.println("抱歉，没有在服务器获取结果,请检查参数是否有误。");
		}else{
			System.out.println("结果："+result);
		}
	}
	//向服务器发送的POST方法。
	protected String sendPost(String strUrl){

    System.setProperty("http.proxyHost", "localhost"); 
    System.setProperty("http.proxyPort", "8888"); 
    System.setProperty("https.proxyHost", "localhost");
    System.setProperty("https.proxyPort", "8888");


		StringBuffer result=new StringBuffer();
		BufferedReader in;
		  String line;
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);  
			conn.setDoInput(true);  
			conn.setRequestMethod("POST");  
			conn.setUseCaches(false);  
			conn.setInstanceFollowRedirects(true);
			conn.addRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(param);
			out.close();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = in.readLine()) != null) {
				 result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	//主方法
	public static void main(String[] args) {
		new Login();
	}
}
