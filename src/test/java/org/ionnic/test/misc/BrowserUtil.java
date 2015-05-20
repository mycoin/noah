package org.ionnic.test.misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BrowserUtil {
	public static void main(String[] args) {
		String concreateURL = "http://segmentfault.com/";
		Connection c = Jsoup.connect(concreateURL);
		try {
			Document doc = c.data("query", "Java").userAgent("Chrome").cookie("auth", "token").timeout(5000).post();
			Elements eles = doc.select("meta");
			List<String> nameList = new ArrayList<String>();
			for (Element ele : eles) {
				nameList.add(ele.attr("name"));
				System.out.println(ele);
			}
			
			System.out.println(nameList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}