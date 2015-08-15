package jvm;
/**
 * 垃圾回收自救，每个对象的finalize()方法只能执行一次
 * @author luwz3
 *
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK=null;
	public void isAlive(){
		System.out.println("yes,i am still alive");
	}
	@Override
	protected void finalize() throws Throwable {
 		super.finalize();
 		System.out.println("finalize method executed!");
 		FinalizeEscapeGC.SAVE_HOOK=this;
	}
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK=new FinalizeEscapeGC();
		SAVE_HOOK=null;
		System.gc();
		
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no ,i am dead");
		}
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no ,i am dead");
		}
	}
}
