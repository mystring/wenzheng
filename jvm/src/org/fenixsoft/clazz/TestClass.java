package org.fenixsoft.clazz;

public class TestClass {
 	private int m;
	@SuppressWarnings("finally")
	public int inc(){ 
		int x;
		
		try {
			x=1;
			return x;
		} catch (Exception e) {
			 x=2;
			 return x;
 		}finally{
 			//System.out.println("nihao");
			x=3;
			//return x;
		}
 	}
	public String inc(String a){
		return a;
	}
	public static int inc(int a){
		return a+1;
	}
	public static void main(String[] args) {
		System.out.println(new TestClass().inc());
	}
}
