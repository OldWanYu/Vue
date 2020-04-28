package com.tadu.book.action.pushbookaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.StringPart;

public class AddBook {
	/**
	 * 定义请求参数
	 */
	//必须的参数
	String baseUrl="http://td.kaiqi.com/api/addBook";
	public AddBook(int i) throws UnsupportedEncodingException{
		String result = null;
		try {
			result = sendPost(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(result==null||result.equals("")){
			System.out.println("抱歉，没有在服务器获取结果,请检查参数是否有误。");
		}else{
			System.out.println("结果："+result);
		}
	}
	//向服务器要发送的POST请求
	protected String sendPost(List<StringPart> params) throws FileNotFoundException{
		URL url = null;
		HttpURLConnection conn = null;
		String str = "";
		try {
			url = new URL(baseUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Charsert", "UTF-8");
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer buffer = new StringBuffer();
			//key
			String secret = "fc47201e540d2a101273eab25b46e5bb";
			//copyrightid + password
			String securityid = "4"+secret;
			String digest = new SHA1().getDigestOfString(securityid.getBytes());
			//密钥
			buffer.append("key="+digest);
			//CP书籍ID
			buffer.append("&cpid=1234");
			//CP版权ID
			buffer.append("&copyrightid=4");
			//封面图
			buffer.append("&coverimage=http://media.tadu.com/0/3/6/d/036d787be20141fd9827f55d584d7ecf_a.jpg");
			//书籍名称
			buffer.append("&bookname="+URLEncoder.encode("第一本书", "UTF-8"));
			//作者名称
			buffer.append("&authorname="+URLEncoder.encode("第一作者", "UTF-8"));
			//书籍简介
			buffer.append("&intro="+URLEncoder.encode("第一简介,她世家女军医重生成为落魄世家嫡系的后代，拥有逆天回春术，只救该救之人，双瞳识宝，过目不忘，成为振兴家族的幕后人。助父兄得富贵，随夫君远行边防，献兵法，大败敌军，回京都，陷五龙夺谪之乱，与夫君一起携手终扶得太子登基，荣封一品诰命夫人。", "UTF-8"));
			//建议分类ID
			buffer.append("&classid=108");
			//是否连载  1连载0全本
			buffer.append("&serial=1");
			//是否收费  1收费0免费
			buffer.append("&isvip=1");
			//原站链接
			
//			System.out.println("POST:" + buffer.toString());
			dos.write(buffer.toString().getBytes());
			dos.flush();
			dos.close();
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String aa = "";
			while ((aa = bufferReader.readLine()) != null) {
				str += aa;
			}
			System.out.println(str);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return str;
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
//		for(int i=1;i<2;i++){
			new AddBook(0);
//		}
//		String a = "bookid=123457";
//		String b = "intro=";
//		
//		if ("bookid".equals(a.substring(0, 6))) {
//			System.out.println(a.substring(7));
//		}
//		if ("intro".equals(b.substring(0, 5))) {
//			System.out.println(b.substring(6));
//		}
	}
}
