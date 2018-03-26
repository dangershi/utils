import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * AES加密解密算法
 */
public class EncryptAES {
	//KeyGenerator 提供对称密钥生成器的功能，支持各种算法  
    private KeyGenerator keygen;  
    //SecretKey 负责保存对称密钥  
    private SecretKey aeskey;  
    //Cipher 负责完成加密或解密工作  
    private Cipher c;  
    //该字节数组负责保存加密的结果  
    private byte[] cipherByte;  
      
    @SuppressWarnings("restriction")
	public EncryptAES() throws Exception{  
        Security.addProvider(new com.sun.crypto.provider.SunJCE());  
        //实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)  
        keygen = KeyGenerator.getInstance("AES");  
        //生成密钥  
        aeskey = keygen.generateKey();  
        //生成Cipher对象,指定其支持的AES算法  
        c = Cipher.getInstance("AES");  
    }  
      
    /** 
     * 对字符串加密 
     * @param str 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public byte[] Encryptor(String str) throws Exception {  
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
        c.init(Cipher.ENCRYPT_MODE, aeskey);  
        byte[] src = str.getBytes();  
        // 加密，结果保存进cipherByte  
        cipherByte = c.doFinal(src);  
        return cipherByte;  
    }  
  
    /** 
     * 对字符串解密 
     * @param buff 
     * @return 
     * @throws InvalidKeyException 
     * @throws IllegalBlockSizeException 
     * @throws BadPaddingException 
     */  
    public byte[] Decryptor(byte[] buff) throws Exception {  
        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式  
        c.init(Cipher.DECRYPT_MODE, aeskey);  
        cipherByte = c.doFinal(buff);  
        return cipherByte;  
    }  
  
    /** 
     * @param args 
     * @throws NoSuchPaddingException  
     * @throws NoSuchAlgorithmException  
     * @throws BadPaddingException  
     * @throws IllegalBlockSizeException  
     * @throws InvalidKeyException  
     */  
    public static void main(String[] args) throws Exception {  
    	EncryptAES aes = new EncryptAES();  
        String msg ="666";  
        byte[] encontent = aes.Encryptor(msg);  
        byte[] decontent = aes.Decryptor(encontent);  
        System.out.println("明文是:" + msg);  
        System.out.println("加密后:" + new String(encontent));  
        System.out.println("解密后:" + new String(decontent));  
    }  
}
