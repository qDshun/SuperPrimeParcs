import parcs.*;

public class Fib implements AM{
    public void run(AMInfo info){
        
        long n = info.parent.readLong();
        info.parent.write(n);
        long spCount = 0;
        long startsFrom = 1;
        boolean found = false;
        while(spCount != n){
            startsFrom += 2;
            if(isSuperPrime(startsFrom))
                spCount++;
        }
        info.parent.write(startsFrom);
        // if (isSuperPrime(n))
        //     info.parent.write(n);
        // else
        //     info.parent.write(0);
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
