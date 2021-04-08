import java.math.BigInteger;

import parcs.*;

public class Fib implements AM{
    public void run(AMInfo info){
        String s = info.parent.read().toString();
        String[] strings = s.split(" ");
        long currentIndex = Long.parseLong(strings[0]);
        long superPrimesFound = Long.parseLong(strings[1]);
        long targetSuperPrimeIndex = Long.parseLong(strings[2]);
        if (isSuperPrime(currentIndex))
            superPrimesFound++;
        if(superPrimesFound == targetSuperPrimeIndex)
            info.parent.write(currentIndex);
        else {
            currentIndex++;
            String stringToWrite = currentIndex + " " + superPrimesFound + " " + targetSuperPrimeIndex;
            point p1 = info.createPoint();
            channel c1 = p1.createChannel();
            p1.execute("Fib");
            c1.write(stringToWrite.getBytes());
        }
    }

    public static boolean isSuperPrime(long n)
    {
        if (!isPrime(n))
            return false;
        int primesLength = 0;
        for(int i=0; i< n+1; i++)
        {
            if(isPrime(i))
                primesLength++;
        }
        if(!isPrime(primesLength))
            return false;
        return true;
    }

    public static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long)Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}
