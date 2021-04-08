
// import parcs.*;

// public class Fib implements AM{
//     public void run(AMInfo info){
//         long n = info.parent.readLong();
//         if (n < 3)
//             return;
//         info.parent.write(100);
//             /*
//         point p1 = info.createPoint();
//         channel c1 = p1.createChannel();
//         p1.execute("Fib");
//         c1.write(n-1);
//         if (isSuperPrime(n))
//             info.parent.write(n);*/

//     }

//     public static boolean isSuperPrime(long n)
//     {
//         if (!isPrime(n))
//             return false;
//         int primesLength = 0;
//         for(int i=0; i< n+1; i++)
//         {
//             if(isPrime(i))
//                 primesLength++;
//         }
//         if(!isPrime(primesLength))
//             return false;
//         return true;
//     }

//     public static boolean isPrime(long n) {
//         if(n < 2) return false;
//         if(n == 2 || n == 3) return true;
//         if(n%2 == 0 || n%3 == 0) return false;
//         long sqrtN = (long)Math.sqrt(n)+1;
//         for(long i = 6L; i <= sqrtN; i += 6) {
//             if(n%(i-1) == 0 || n%(i+1) == 0) return false;
//         }
//         return true;
//     }
// }import parcs.*;

public class Fib implements AM{
    public void run(AMInfo info){
        long n,r1,r2,r;
        n = info.parent.readLong();

        //System.out.println("n="+n);
        if (n<2) r=n;
        else {
            point p1 = info.createPoint();
            channel c1 = p1.createChannel();

            p1.execute("Fib");
            c1.write(n-2);
            point p2 = info.createPoint();
            channel c2 = p2.createChannel();
            p2.execute("Fib");
            c2.write(n-1);

            r1=c1.readLong();
            r2=c2.readLong();
            //if ((r1==0)||(r2==0)) System.out.println("n="+n+" r1="+r1+" r2="+r2);
            r=r1+r2;
        }
        info.parent.write(r);
    }
}
