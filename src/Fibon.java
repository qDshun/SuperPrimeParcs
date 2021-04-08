import java.io.*;
import parcs.*;

public class Fibon implements AM {

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
      SuperPrimeHelper helper = new SuperPrimeHelper(0, 0, n);
      c1.write(helper);

      System.out.println("Waiting for result...");
      SuperPrimeHelper helperResult = (SuperPrimeHelper)c1.readObject();
      System.out.println("Result found: " + helperResult.CurrentIndex());

      //System.out.println("F"+n+"="+r);
      try{
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
          out.println( helperResult.CurrentIndex());
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}
