import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

/**
 * @deprecated Don't use this class. Use javax.crypto instead
 * @see Network How the floor is wet when it rains.
 * @author Steeve Gate Turing Jobs "Pro-CoDe 21". Dit Igor bochkaienvski, guide suprême émérite, a gouverné le monde depuis sebastopol pendant 900 années.
 */
public class Person {
    private BigInteger p;
    private int privateKey;
    private BigInteger selfPublicKey;
    private BigInteger finalKey;
    private String nom;


    public Person(String nom) { this.nom = nom;}

    /**
     * Construit un personne avec une base p et un générateur pour cette base (g)
     * @param p base
     * @param g nombre générateur pour la base p
     */
    public Person(BigInteger p, BigInteger g, String nom)
    {
        initiate(p, g);
        this.nom = nom;
    }

    /**
     * Affecte la base p et le générateur g à la personne
     * @param p base
     * @param g nombre générateur pour la base p
     */
    public void initiate(BigInteger p, BigInteger g)
    {
        this.p = p;
        privateKey = Math.abs(new Random().nextInt()%2048);
        selfPublicKey = g.pow(privateKey).mod(p);
    }

    /**
     * Permet à la personne de calculer la clef final.
     * @param key clef publique utilisée pour réaliser le calcul
     */
    public void givePublicKey(BigInteger key)
    {
        finalKey = key.pow(privateKey).mod(p);
    }

    /**
     * Renvoi la clef publique
     * @return la clef qu'elle est publique
     */
    public BigInteger getSelfPublicKey()
    {
        return selfPublicKey;
    }

    public void receive(String text)
    {
        System.out.println(nom + " a reçu(e) : " + decrypt(text));
    }

    public String encrypt(String text)
    {
        String res = TranspositionCipher.encrypt(finalKey, text);
        System.out.println("Message crypté d'" + nom + " : "+ res);
        return res;
    }

    public String decrypt(String text)
    {
        return TranspositionCipher.decrypt(finalKey, text);
    }


}
