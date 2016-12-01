import java.util.ArrayList;

public class TranspositionCipher
{

    public static String shuffle(int[] key, String text)
    {
        char[] chain = text.toCharArray();
        int wordSize = (int) Math.ceil(text.length()/key.length);
        char[] newText = new char[wordSize * key.length];

        //Build the array list
        ArrayList<char[]> arrays = new ArrayList<>();
        for (int ignored : key)
        {
            arrays.add(new char[wordSize]);
        }

        //Je remplis les tableaux comme sur le schéma de wikipédia
        for (int i = 0; i < wordSize; i++)
        {
            for (int j = 0; j < key.length; j++)
            {

                arrays.get(j)[i] = chain[i*key.length+j];
            }
        }

        //Et la je les lis dans le bonne ordre pour les mélanger
        int index = 0;
        for (int i : key) {
            for (char c : arrays.get(i-1)) {
                newText[index] = c;
                index++;
            }
        }

        return String.valueOf(newText);
    }
    /*
    public static BigInteger decode(int key[], int wordSize, BigInteger encrypted)
    {

    }
    */
}
