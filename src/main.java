import java.math.BigInteger;


public class main{
    public static void main(String[] args) {
        PerfectGenerator generator = new PerfectGenerator();
        BigInteger p = generator.getP();
        BigInteger g = generator.getG();

        System.out.println("WOW");
        Person Bob = new Person(p, g);
        Person Alice = new Person(p, g);
        Bob.givePublicKey(Alice.getSelfPublicKey());
        Alice.givePublicKey(Bob.getSelfPublicKey());
    }
}