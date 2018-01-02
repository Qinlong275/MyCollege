/**
 * Created by 魏彬 on 2017/6/26.
 */
import java.util.*;
public class ThreadTest {
    public static void main(String[] args){
        System.out.print("请输入您要出售的电影票总数：");
        Scanner in = new Scanner(System.in);
        int i=in.nextInt();
        Tickets tickets=new Tickets(i);
        Thread t1 = new Thread(tickets,"一号窗口");
        Thread t2 = new Thread(tickets,"二号窗口");
        Thread t3 = new Thread(tickets,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}
