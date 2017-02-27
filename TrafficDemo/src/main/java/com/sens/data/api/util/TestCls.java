package com.sens.data.api.util;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class TestCls {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hellovnbkldfbln");
		
		String passwordToEncrypt = "Password1";
		
		//System.out.println(TestCls.encrypt(passwordToEncrypt));
		
		//LQD4TKpfbArYhMp6R8vJlWaloDx6dfXl
		
		System.out.println(TestCls.decrypt("lSS9D8MdHb6X27mxoMC80GaFt+Ilry2H", "sensDemo"));
	}
	
	
	 /**
     * Decrypts the password using the Jasypt library
     *
     * @param encrypted  password, key
     * @return decrypted password
     */
    public static String decrypt(String password, String key) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        String encryptedPassword = null;
        encryptor.setPassword(key);
        try {
            encryptedPassword = encryptor.decrypt(password);
        } catch (Exception e) {
        	
        	e.printStackTrace();
        }
        return encryptedPassword;
    }
    
    /**
     * encryption the password using the Jasypt library
     *
     * @param passwordToEncrypt  password to encrypt
     */
    public static String encrypt(String passwordToEncrypt){
            //Encrypt
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword("sensDemo"); // this will be the key while decryption
            String encryptedPassword = encryptor.encrypt(passwordToEncrypt);
            System.out.println("Encryption done and encrypted password is : " + encryptedPassword ); 
            
            return encryptedPassword;
 
    }

}
