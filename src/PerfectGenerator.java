import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by MrMan on 23/11/2016.
 */
public class PerfectGenerator {
    private BigInteger g;
    private BigInteger p;

    public PerfectGenerator() {
        g = BigInteger.valueOf(2);
        p = BigInteger.probablePrime(2048, new SecureRandom());
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getP() {
        return p;
    }
}
