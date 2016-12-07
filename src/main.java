import java.io.BufferedReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class main{
    public static void main(String[] args) {
        PerfectGenerator generator = new PerfectGenerator();
        BigInteger g = generator.getG();
        BigInteger p = generator.getP();

        Person Alice = new Person(p,g, "Alice");
        Person Bob = new Person("Bob");

        Network network = new Network(Alice,Bob);

        Alice.givePublicKey(network.initiateConversation(p,g, Alice.getSelfPublicKey()));

        network.send(Alice.encrypt("Whatever niggaz it works !"));
    }
}