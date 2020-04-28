package com.tadu.book.action.pushbookaction;

import java.io.File;
import java.io.FileInputStream;

public class GetFileSize {
	public static long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

}
