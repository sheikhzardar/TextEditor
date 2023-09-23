package views;

public class Encrypting {
	
	String plaintext;
	String key;
	
	Encrypting(String plaintext,String key)
	{
		this.plaintext=plaintext;
		this.key=key;
	}
	
	public String CaesarCipher() 
	{
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            if (Character.isLetter(ch)) {
                char base = (Character.isUpperCase(ch)) ? 'A' : 'a';
                char ch1 = (char) ((ch - base + Integer.parseInt(key)) % 26 + base);
                ciphertext.append(ch1);
            } else {
                ciphertext.append(ch);
            }
        }

        return ciphertext.toString();
    }
	
	public String VigenereCipher() 
	{
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) 
        {
            char ch = plaintext.charAt(i);
            char keych = key.charAt(i % key.length());

            if (Character.isLetter(ch)) 
            {
                char base = (Character.isUpperCase(ch)) ? 'A' : 'a';
                int encryptedChar = (ch + keych - 2 * base) % 26 + base;
                ciphertext.append((char) encryptedChar);
            } else 
            {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }


}
