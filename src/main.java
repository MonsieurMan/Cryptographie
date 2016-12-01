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
        System.out.println(TranspositionCipher.shuffle(new int[]{2, 1, 4, 3}, Bob.finalKey.toString()));

        //Version avec une chaine de caractère
        //System.out.println(TranspositionCipher.shuffle(new int[]{2, 1, 3}, "Bonjourxx"));
    }
}