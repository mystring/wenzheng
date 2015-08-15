package jvm;

public class SwitchTest {
	public int doSwitch(char str){
		switch (str) {
		case '1': return 1;
		case '2': return 2;	
		default:
			return 0;
		}
	}
}
