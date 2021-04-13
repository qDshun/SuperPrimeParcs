import java.awt.Point;
import java.io.*;
import java.nio.channels.Channel;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
      List<Long> primeNumbers = new ArrayList<Long>();
      long val =0;
      Date date1 = new Date();
      Timestamp ts1 = new Timestamp(date1.getTime());
      System.out.println("Started at: " + ts1.getTime());
      for (int i=0; i<n; i++)
      {
        points.add(info.createPoint());
        chans.add(points.get(i).createChannel());
        points.get(i).execute("Fib");
        chans.get(i).write(val);
        System.out.println("Channel created " + val);
        val++;
      }
      for (channel c: chans) {
          long primeNumber = c.readLong();
          if (primeNumber != 0)
            primeNumbers.add(primeNumber);
            System.out.println("Found prime number: " + primeNumber);

      }
      System.out.println("Waiting for result...");
      Date date2 = new Date();
      Timestamp ts2 = new Timestamp(date2.getTime());
      System.out.println("Finished at: " + ts2.getTime());
      long diff = ts2.getTime() - ts1.getTime();
      System.out.println("Total time: " + diff);


      //System.out.println("F"+n+"="+r);
      try{
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Fibon.res")));
          for(int i=0; i<primeNumbers.size(); i++)
            out.println(primeNumbers.get(i));
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}

