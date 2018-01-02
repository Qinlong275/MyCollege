/**
 * Created by 魏彬 on 2017/6/26.
 */
public class Tickets implements Runnable {
    int count =1;
    private static int i;
    //构造方法
    public  Tickets(int i){
        this.i=i;
    }
    private Object o = new Object();
    public void run(){
       while(true){
            //同步代码块
            synchronized (o)
            {
                if (count<=i) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"已出售第"+count+"张电影票");
                    count ++;
                }
            }
        }
    }
}
