import java.util.*;
import java.security.*;
import java.security.interfaces.*;
import com.nimbusds.jose.jwk.*;
import com.nimbusds.jose.jwk.RSAKey;

public class GenerateKey {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Convert to JWK format
            JWK jwk = new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
                    .privateKey((RSAPrivateKey)keyPair.getPrivate())
                    .keyUse(KeyUse.SIGNATURE)
                    .keyID(UUID.randomUUID().toString())
                    .build();

            System.out.println("Private Key: " + privateKey);
            System.out.println("Public Key: " + publicKey);

            System.out.println(jwk.toJSONObject());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
