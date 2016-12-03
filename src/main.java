import java.math.BigInteger;

public class main{
    public static void main(String[] args) {

        PerfectGenerator generator = new PerfectGenerator();
        BigInteger p = generator.getP();
        BigInteger g = generator.getG();

        Person Bob = new Person(p, g);
        Person Alice = new Person(p, g);

        Bob.givePublicKey(Alice.getSelfPublicKey());
        Alice.givePublicKey(Bob.getSelfPublicKey());

        //Pour la transposition sur le biginteger c'est plus facile de le faire sous forme de string.

        //Je suis pas encore sur que ça fonctionne avec le BigInteger
        System.out.println(Bob.finalKey);
        String encryptedKey = TranspositionCipher.encrypt(new int[]{2, 1, 4, 3}, Bob.finalKey.toString());
        System.out.println(encryptedKey);

        String decryptedKey = TranspositionCipher.decrypt(new int[]{2, 1, 4, 3}, encryptedKey);
        System.out.println(decryptedKey);

        BigInteger testKey = new BigInteger(decryptedKey);

        System.out.println(testKey.equals(Bob.finalKey));

        /*Version avec une chaine de caractère
        String chain = "Bonjourxx";

        chain = TranspositionCipher.encrypt(new int[]{2, 1, 3}, chain);
        System.out.println(chain);

        chain = TranspositionCipher.decrypt(new int[]{2, 1, 3}, chain);
        System.out.println(chain);
        */
    }
}