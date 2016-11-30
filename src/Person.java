import java.math.BigInteger;
import java.util.Random;

/**
 * Created by MrMan on 23/11/2016.
 */
public class Person {
    private BigInteger p;
    private int privateKey;
    private BigInteger selfPublicKey;
    private BigInteger finalKey;

    public Person(BigInteger p, BigInteger g) {
        this.p = p;
        privateKey = Math.abs(new Random().nextInt()%2048);
        selfPublicKey = g.pow(privateKey).mod(p);
    }

    public void givePublicKey(BigInteger key){
        finalKey = key.pow(privateKey).mod(p);
    }

    public BigInteger getSelfPublicKey() {
        return selfPublicKey;
    }
}
