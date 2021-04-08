import java.io.*;
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

      point p1 = info.createPoint();
      channel c1 = p1.createChannel();
      p1.execute("Fib");
      c1.write(n);

      System.out.println("Waiting for result...");
      boolean shouldStop = false;
      while (!shouldStop)
      {
          long r = c1.readLong();
          System.out.println("Super Prime Number found: " + r);
          if (r == 0)
            shouldStop = true;
      }
      long r = c1.readLong();
      System.out.println("Result found: " + n);

      //System.out.println("F"+n+"="+r);
      try{
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
          out.println(r);
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}

