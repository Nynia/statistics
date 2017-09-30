package utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

/**
 * 编码工具类
 * 实现aes加密、解密
 */
public class AESEncryptUtils {

    private static final String KEY_ALGORITHM = "AES";
    private static final String ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String ECB_NOPADDING_CIPHER_ALGORITHM = "AES/ECB/NoPadding";
    private static final String CBC_NOPADDING_CIPHER_ALGORITHM = "AES/CBC/NoPadding";

    public static String AES_ECB_ENCRYPT(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return new sun.misc.BASE64Encoder().encode(encrypted);
    }

    public static String AES_ECB_ZEROPadding_ENCRYPT(String data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ECB_NOPADDING_CIPHER_ALGORITHM);
        //padding 0
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        byte[] encrypted = cipher.doFinal(plaintext);

        return new sun.misc.BASE64Encoder().encode(encrypted);
    }

    public static String AES_CBC_ENCRYPT(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return new sun.misc.BASE64Encoder().encode(encrypted);
    }

    public static String AES_CBC_ZEROPadding_ENCRYPT(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CBC_NOPADDING_CIPHER_ALGORITHM);
        //padding 0
        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(plaintext);

        return new sun.misc.BASE64Encoder().encode(encrypted);
    }

    public static String AES_DECRYPT(String data, String key, String iv, String padding_mode) throws Exception {
        String algorithm = null;
        IvParameterSpec ivspec = null;
        if (!iv.equals("")) {
            ivspec = new IvParameterSpec(iv.getBytes());
            if (padding_mode != null && padding_mode.equals("NoPadding")) {
                algorithm = CBC_NOPADDING_CIPHER_ALGORITHM;
            } else
                algorithm = CBC_CIPHER_ALGORITHM;
        } else {
            if (padding_mode != null && padding_mode.equals("NoPadding")) {
                algorithm = ECB_NOPADDING_CIPHER_ALGORITHM;
            } else
                algorithm = ECB_CIPHER_ALGORITHM;
        }

        byte[] encrypted = new BASE64Decoder().decodeBuffer(data);

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
        byte[] plain = cipher.doFinal(encrypted);
        return new String(plain);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(AES_CBC_ZEROPadding_ENCRYPT("admin", "1234567812345678", "1234567812345678"));
        System.out.println(AES_DECRYPT("tgexo5TO+DJGzMrpCYsPUw==", "1234567812345678", "", "NoPadding"));
    }
}

