import java.io.*;
import java.util.List;
import java.util.ArrayList;
import parcs.*;

public class Fibon implements AM {   //����� ���������

    public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("Fibon.jar");
        (new Fibon()).run(new AMInfo(curtask, (channel)null));
        curtask.end();
    }

    public void run(AMInfo info) {
      long n;
      try {
          /*byte[] buf = new byte[200];
          System.out.print("Enter n: ");
          System.in.read(buf);
          n=new Long(new String(buf).trim()).longValue();*/

          BufferedReader in = new BufferedReader(new FileReader(info.curtask.findFile("Fibon.data")));
          n = new Long(in.readLine()).longValue();
      } catch (IOException e) {e.printStackTrace(); return;}
      System.out.println("Read from file success");
      List<point> points = new ArrayList<>();
      List<channel> chans = new ArrayList<>();
      for (int i=0; i<n; i++)
      {
        point p1 = info.createPoint();
        channel c1 = p1.createChannel();
        p1.execute("Fib");
        c1.write(i);
        System.out.println("Channel created " + i);
        points.add(p1);
        chans.add(c1);
      }
      for (channel c: chans) {
        System.out.println("Before readlong");
          long primeNumber = c.readLong();
          System.out.println("After readlong : " + primeNumber);
          if (primeNumber != 0)
            System.out.println("Found prime number: " + primeNumber);

      }
      System.out.println("Waiting for result...");

    //   //System.out.println("F"+n+"="+r);
    //   try{
    //       PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
    //       out.println(r);
    //       out.close();
    //   } catch (IOException e) {e.printStackTrace(); return;}
    }
}

