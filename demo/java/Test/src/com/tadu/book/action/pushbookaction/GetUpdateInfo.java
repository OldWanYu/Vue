package com.tadu.book.action.pushbookaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.StringPart;

public class GetUpdateInfo {
	/**
	 * 定义请求参数
	 */

	String baseUrl = "http://td.kaiqi.com/api/getUpdateInfo";

	public GetUpdateInfo(int i) throws UnsupportedEncodingException {
		String result = null;
		try {
			result = sendPost(null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (result == null || result.equals("")) {
			System.out.println("抱歉，没有在服务器获取结果,请检查参数是否有误。");
		} else {
			System.out.println("结果：" + result);
		}
	}

	// 向服务器要发送的POST请求
	protected String sendPost(List<StringPart> params) throws FileNotFoundException {
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
			//CP侧的图书ID
			buffer.append("&cpid=123457");
			//CP版权ID
			buffer.append("&copyrightid=4");
			
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
		// for(int i=1;i<2;i++){
		new GetUpdateInfo(0);
		// }
		// String a = "bookid=123457";
		// String b = "intro=";
		//		
		// if ("bookid".equals(a.substring(0, 6))) {
		// System.out.println(a.substring(7));
		// }
		// if ("intro".equals(b.substring(0, 5))) {
		// System.out.println(b.substring(6));
		// }
	}
}
