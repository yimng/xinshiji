package com.halsign.kunlu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyOfTest {

	private static Map<String, char[]> code = new HashMap<String, char[]>();
	static String f1 = "C:\\Users\\ShawnWang\\workspace3\\Test\\src\\com\\halsign\\kunlu\\wubi86.dict.yaml";
	static String f2 = "C:\\Users\\ShawnWang\\workspace3\\Test\\src\\com\\halsign\\kunlu\\xinshijiwubi.dict.yaml";
	static String f3 = "D:\\Dropbox\\wubi\\新世纪词库.txt";
	static String f4 = "C:\\Users\\ShawnWang\\workspace3\\Test\\src\\com\\halsign\\kunlu\\小鸭五笔-词库(五笔).txt";
	static {
		code(f2, code);
	}
	static Pattern r2 = Pattern.compile("(\\p{IsHan}{1})(\\p{IsHan}{1})");
	static Pattern r3 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	static Pattern r4 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	static Pattern r5 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	static Pattern r6 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	static Pattern r7 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	static Pattern r8 = Pattern.compile("(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})(\\p{IsHan})");
	
	
//	public static void main(String args[]) {
//		int n = 2;
//		Set<String> set1 = new HashSet<String>();
//		Set<String> set2 = new HashSet<String>();
//		find(f2, n, set1);
//		find2(f4, n, set2);
//		System.out.println(set1.size());
//		System.out.println(set2.size());
//		set2.removeAll(set1);
//		System.out.println(set2.size());
//		try {
//			PrintWriter writer = new PrintWriter(
//					"C:\\Users\\ShawnWang\\workspace3\\Test\\src\\com\\halsign\\kunlu\\"
//							+ n + ".txt", "UTF-8");
//			for (String s : set2) {
//				switch(n) {
//				case 2:
//					s = encode2(s);
//					break;
//				case 3:
//					s = encode3(s);
//					break;
//				case 4:
//					s = encode4(s);
//					break;
//				case 5:
//					s = encode5(s);
//					break;
//				case 6:
//					s = encode6(s);
//					break;
//				case 7:
//					s = encode7(s);
//					break;
//				case 8:
//					s = encode8(s);
//					break;
//				default:
//					break;
//				}
//				writer.println((s));
//			}
//			writer.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
////		statics(f2);
//	}

	private static void find(String file, int n, Set<String> set) {
		String pattern = "^(\\p{IsHan}{" + n + "})\\s+";
		Pattern r = Pattern.compile(pattern);
		for (String line : file2Set(file)) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				set.add(m.group(1));
			}
		}
	}
	
	private static void find2(String file, int n, Set<String> set) {
		String pattern = "^(\\p{IsHan}{" + n + "})(?:\\s+(\\p{IsHan}{" + n + "}))*?$";
		Pattern r = Pattern.compile(pattern);
		for (String line : file2Set(file)) {
			Matcher m = r.matcher(line);
			while (m.find()) {
				String g1 = m.group(1);
				String g2 = m.group(2);
				if (g1 != null) {
					set.add(m.group(1));
				}
				if (g2 != null) {
					set.add(m.group(2));
				}
			}
		}
	}

	public static void main(String [] args) {
		Set<String> set = new HashSet<String>();
		find2("", 2, set);
	}
	private static Set<String> file2Set(String f) {
		Set<String> l = new HashSet<String>();
		String line;
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(f));
			while ((line = bufferedReader.readLine()) != null) {
				l.add(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return l;
	}

	private static void code(String file, Map<String, char[]> map) {
		String pattern = "^(\\p{IsHan}{1})\\s+([a-z]{2,})";
		Pattern r = Pattern.compile(pattern);
		for (String line : file2Set(file)) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				map.put(m.group(1), m.group(2).toCharArray());
			}
		}
	}

	private static void printcode(String f) {
		Map<String, char[]> m = new HashMap<String, char[]>();
		for (String line : file2Set(f)) {
			code(line, m);
		}
		for (String key : m.keySet()) {
			System.out.print(key);
			System.out.print("-------");
			System.out.println(m.get(key));
		}
		System.out.println(m.size());
	}
	
	private static String encode2(String s) {
		Matcher m = r2.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			return s + "\t" + code.get(c1)[0] + code.get(c1)[1] + code.get(c2)[0] + code.get(c2)[1];
		}
		return "";
	}
	private static String encode3(String s) {
		Matcher m = r3.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c3)[1];
		}
		return "";
	}
	private static String encode4(String s) {
		Matcher m = r4.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			String c4 = m.group(4);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c4)[0];
		}
		return "";
	}
	
	private static String encode5(String s) {
		Matcher m = r5.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			String c5 = m.group(5);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c5)[0];
		}
		return "";
	}
	
	private static String encode6(String s) {
		Matcher m = r6.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			String c6 = m.group(6);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c6)[0];
		}
		return "";
	}
	
	private static String encode7(String s) {
		Matcher m = r7.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			String c7 = m.group(7);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c7)[0];
		}
		return "";
	}
	
	private static String encode8(String s) {
		Matcher m = r8.matcher(s);
		if (m.find()) {
			String c1 = m.group(1);
			String c2 = m.group(2);
			String c3 = m.group(3);
			String c8 = m.group(8);
			return s + "\t" + code.get(c1)[0] + code.get(c2)[0] + code.get(c3)[0] + code.get(c8)[0];
		}
		return "";
	}
	
	private static void statics(String f) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Pattern r = Pattern.compile("^(\\p{IsHan}{2,})\\s+(\\w+)");
		Set<String> s = file2Set(f);
		for (String line : s) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				String key = m.group(2);
				String value = m.group(1);
				if (map.containsKey(key)) {
					map.get(key).add(value);
				} else {
					List<String> l = new ArrayList<String>();
					l.add(value);
					map.put(key, l);
				}
			}
		}
		float count = 0f;
		for (String key: map.keySet()) {
			if (map.get(key).size() > 5) {
				System.out.println(map.get(key));
				count++;
			}
		}
		System.out.println(count);
		System.out.println(count/map.size());
	}
}
