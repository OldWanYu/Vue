package com.tadu.book.action.pushbookaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddChapter {

	String baseUrl = "http://td.kaiqi.com/api/addChapter";
	private AddChapter() {
		String result = null;
		try {
			result = sendPost();
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
	protected String sendPost() throws FileNotFoundException {
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
			// ##CP书籍ID
			buffer.append("&bookid=123404");
			// ##CP版权ID
			buffer.append("&copyrightid=4");
			// ##章节名称
			buffer.append("&title="+URLEncoder.encode("第仨章节", "UTF-8"));
			// ##章节内容
			buffer.append("&content="+URLEncoder.encode("第仨章节内容", "UTF-8"));
			// ##章节序号
			buffer.append("&chapternum=3");
			// ##是否收费 1收费0免费
			buffer.append("&isvip=1");
			// ##CP章节ID
			buffer.append("&chapterid=23456");
			// ##更新状态 1为新增 2为修改
			buffer.append("&updatemode=1");

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

	public static void main(String[] args) {
		new AddChapter();
	}
}
