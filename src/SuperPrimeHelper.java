import java.io.*;

public class SuperPrimeHelper implements Serializable {
    
    private long currentIndex ;
    private long superPrimesFound ;
    private long targetSuperPrimeIndex ;

    SuperPrimeHelper(long currentIndex, long superPrimesFound, long targetSuperPrimeIndex)
    {
        this.currentIndex = currentIndex;
        this.superPrimesFound = superPrimesFound;
        this.targetSuperPrimeIndex = targetSuperPrimeIndex;
    }

    public long CurrentIndex() {
        return this.currentIndex;
    }

    public long SuperPrimesFound() {
        return this.superPrimesFound;
    }

    public long TargetSuperPrimeIndex() {
        return this.targetSuperPrimeIndex;
    }
}
