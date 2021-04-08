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
      string s = c1.read().toString();
      String[] strings = s.split(" ");
      long currentIndex = Long.parseLong(strings[0]);
      long superPrimesFound = Long.parseLong(strings[1]);
      long targetSuperPrimeIndex = Long.parseLong(strings[2]);
      System.out.println("Result found: " + currentIndex);

      //System.out.println("F"+n+"="+r);
      try{
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
          out.println(currentIndex);
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}
