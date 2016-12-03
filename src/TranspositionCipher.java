import java.util.ArrayList;

public class TranspositionCipher
{
    //TODO update the comments
    public static String encrypt(int[] key, String text)
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


    public static String decrypt(int key[], String cypherText)
    {
        char[] chain = cypherText.toCharArray();
        int wordSize = (int) Math.ceil(cypherText.length()/key.length);
        char[] newText = new char[wordSize * key.length];

        //Build the array list
        ArrayList<char[]> arrays = new ArrayList<>();
        for (int ignored : key)
        {
            arrays.add(new char[wordSize]);
        }

        int index = 0;
        for (int i : key) {
            for (int j = 0; j < wordSize; j++) {
                arrays.get(i-1)[j] = chain[index];
                index++;
            }
        }

        index = 0;
        for (int i = 0; i < wordSize; i++) {
            for (int j = 0; j < key.length; j++) {
                newText[index] = arrays.get(j)[i];
                index++;
            }
        }

        return String.valueOf(newText);
    }

}
