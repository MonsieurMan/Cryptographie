import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

public class TranspositionCipher
{
    /**
     * Code un message par transpostion
     * @param key la clef de transposition
     * @param text le texte à crypté
     * @return le texte crypté
     */
    public static String encrypt(BigInteger key, String text)
    {
        int[] keyArr = IntegerToArray(key);
        int diff = text.length() % keyArr.length;
        String stuff = "";
        if(diff != 0)
        {
            for (int i = 0; i < 8-diff; i++) {
                stuff += ' ';
            }
        }
        char[] chain = (text + stuff).toCharArray();
        int wordSize = chain.length/keyArr.length;
        char[] newText = new char[wordSize * keyArr.length];

        //Build the array list
        ArrayList<char[]> arrays = new ArrayList<>();
        for (int ignored : keyArr)
        {
            arrays.add(new char[wordSize]);
        }

        //Je remplis les tableaux comme sur le schéma de wikipédia
        for (int i = 0; i < wordSize; i++)
        {
            for (int j = 0; j < keyArr.length; j++)
            {
                arrays.get(j)[i] = chain[i* keyArr.length+j];
            }
        }

        //Et la je les lis dans le bonne ordre pour les mélanger
        int index = 0;
        for (int i : keyArr) {
            for (char c : arrays.get(i-1)) {
                newText[index] = c;
                index++;
            }
        }

        return String.valueOf(newText);
    }

    /**
     * Décrypte un message précedemment encrypté avec la méthode encrypt
     * @param key la clef de cryptage
     * @param cypherText le texte crypté
     * @return le texte décrypté
     */
    public static String decrypt(BigInteger key, String cypherText)
    {
        int[] keyArr = IntegerToArray(key);
        char[] chain = cypherText.toCharArray();
        int wordSize = (int) Math.ceil(cypherText.length()/ keyArr.length);
        char[] newText = new char[wordSize * keyArr.length];

        //Build the array list
        ArrayList<char[]> arrays = new ArrayList<>();
        for (int ignored : keyArr)
        {
            arrays.add(new char[wordSize]);
        }

        int index = 0;
        for (int i : keyArr)
        {
            for (int j = 0; j < wordSize; j++)
            {
                arrays.get(i-1)[j] = chain[index];
                index++;
            }
        }

        index = 0;
        for (int i = 0; i < wordSize; i++)
        {
            for (int j = 0; j < keyArr.length; j++)
            {
                newText[index] = arrays.get(j)[i];
                index++;
            }
        }

        return String.valueOf(newText);
    }

    /**
     * Permet de passé d'un bigInteger à une clef de transposition simplifié
     * @param bigInteger Le BigInteger à traiter
     * @return La clef de transposition simplifié
     */
    static int[] IntegerToArray(BigInteger bigInteger)
    {
        char[] chain = bigInteger.toString().toCharArray();
        int size = chain.length/8;
        BigInteger[] key = new BigInteger[8];

        for (int i = 0; i < 8; i++)
        {
            String nextInt = "";
            for (int j = 0; j < size; j++)
            {
                nextInt += chain[i*size+j];
            }
            key[i] = new BigInteger(nextInt);
        }

        int[] finalKey = new int[8];
        int index = 0;
        for (BigInteger b : key) {
            int i = 0;
            for (BigInteger ba : key) {
                if(b.max(ba).equals(b))
                    i++;
            }
            finalKey[index] = i;
            index++;
        }
        return finalKey;
    }
}
