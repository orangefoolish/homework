import java.util.Date;

public class test {
    {
        Date date=new Date();
        long startTime=date.getTime();
        for(int i=0;i<100000000;i++){
            Object obj=new Object();
            boolean a=(Long)obj==1;
        }
        System.out.println(date.getTime()-startTime);
    }
    public static void main(String args[]){
        Date date=new Date();
        String startTime=date.getTime()+"";
        System.out.println(startTime);
        for(int i=0;i<1000000000;i++){
            Object obj=new Object();
            obj="awdawdawdawdawd";
            boolean a=obj.equals("a");
        }
        System.out.println(date.getTime());
    }
}
