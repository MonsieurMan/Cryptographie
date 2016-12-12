import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class main{
    public static void main(String[] args) throws IOException {
        PerfectGenerator generator = new PerfectGenerator();
        BigInteger g = generator.getG();
        BigInteger p = generator.getP();

        Person Alice = new Person(p,g, "Alice");
        Person Bob = new Person("Bob");

        Network network = new Network(Alice,Bob);

        Alice.givePublicKey(network.initiateConversation(p,g, Alice.getSelfPublicKey()));

        String text = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(text != "q"){
            System.out.println("Entrez le texte à envoyé ");
            text = br.readLine();
            network.send(Alice.encrypt(text));
            System.out.println();
        }
    }
}