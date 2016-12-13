import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by MrMan on 04/12/2016.
 */
public class Network
{
    private Person Alice;
    private Person Bob;
    private Person EveA;
    private Person EveB;

    public Network(Person alice, Person bob) {
        Alice = alice;
        Bob = bob;
    }

    public BigInteger initiateConversation(BigInteger p, BigInteger g, BigInteger publicKey)
    {
        EveA = new Person(p, g, "EveA");
        EveA.givePublicKey(publicKey);

        PerfectGenerator generator = new PerfectGenerator();
        BigInteger p1 = generator.getP();
        BigInteger g1 = generator.getG();

        EveB = new Person(p1, g1, "EveB");
        Bob.initiate(p1, g1);
        Bob.givePublicKey(EveB.getSelfPublicKey());
        EveB.givePublicKey(Bob.getSelfPublicKey());

        return EveA.getSelfPublicKey();
    }

    public void send(String text)
    {
        String decrypted = EveA.decrypt(text);
        System.out.println("Eve a intercepté : " + decrypted);
        Bob.receive(EveB.encrypt(decrypted));
    }
}

