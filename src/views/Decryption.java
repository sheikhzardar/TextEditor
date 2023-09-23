package views;

public class Decryption {
	String ciphertext;
	String key;
	Decryption(String ciphertext,String key)
	{
		this.ciphertext=ciphertext;
		this.key=key;
	}
	
	String CaesarCipher() 
	{
        StringBuilder plaintext = new StringBuilder();
        
        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            char ch1;
            if (Character.isLetter(ch)) 
            {
                char base = (Character.isUpperCase(ch)) ? 'A' : 'a';
                ch1 = (char) ((ch - base - Integer.parseInt(key) + 26) % 26 + base);
                plaintext.append(ch1);
            } else {
                plaintext.append(ch);
            }
        }
        
        return plaintext.toString();
    }
	
	public String VigenereCipher() 
	{
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) 
        {
            char ch = ciphertext.charAt(i);
            char keyChar = key.charAt(i % key.length());

            if (Character.isLetter(ch)) 
            {
                char base = (Character.isUpperCase(ch)) ? 'A' : 'a';
                int decryptedChar = (ch - keyChar + 26) % 26 + base;
                plaintext.append((char) decryptedChar);
            } 
            else 
            {
            	plaintext.append(ch);
            }
        }

        return plaintext.toString();
	}

}
