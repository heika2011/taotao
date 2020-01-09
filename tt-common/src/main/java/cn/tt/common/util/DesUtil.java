package cn.tt.common.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


/**   DES 加密解密
 *  因为没有用户输入   调用getOriginKey得到原始密钥
 * @梁梓云
 */
public class DesUtil {

    /**
     * 用DES算法进行加密（调用API Cipher库）
     * @param clearText 明文
     * @param originKey 原始密钥
     * @return
     * @throws NoSuchPaddingException  填充异常   签名
     * @throws NoSuchAlgorithmException  没有此算法异常
     * @throws InvalidKeyException 无效的密钥
     * @throws BadPaddingException  不良填充
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */

    private  String desEncript(String clearText, String originKey) throws Exception{
        Cipher cipter = Cipher.getInstance("DES");//这是加密算法库对象，获取DES算法

        SecretKeySpec key =getKey(originKey);
        //将对向进行初始化
        cipter.init(cipter.ENCRYPT_MODE, key);//第一参数是加密，第二个参数是将原始密钥换算后的密钥

        //将明文转换为密文
        byte[] aaaa=cipter.doFinal(clearText.getBytes());
        //由于密文是被DES加密，所以编码需要base64来，不然乱码
        String encode = Base64.getEncoder().encodeToString(aaaa);
        return encode;
    }

    /**
     *   解密
     * @param cipherText 密文
     * @param originKey  原始密钥
     * @return 明文
     * @throws Exception
     */
    private  String desDeript(String cipherText, String originKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");//调用工具
        SecretKeySpec key =getKey(originKey);//初始化原始密钥得到新的密钥
        //初始化对象
        cipher.init(cipher.DECRYPT_MODE,key);//第一参数是解密
        //将密文进行base64解码

        byte[] decode =Base64.getDecoder().decode(cipherText.getBytes());
        decode=cipher.doFinal(decode);
        return new String(decode);

    }

    /**
     * SecretKeySpec 将给定的字节数组组成一个新的密钥
     * @param originKey 原始密钥
     * @return 新的密钥
     * @throws UnsupportedEncodingException
     */
    private  SecretKeySpec getKey(String originKey) throws UnsupportedEncodingException {
        SecretKeySpec key =new  SecretKeySpec(originKey.getBytes(),"DES");
        System.out.println(key);
        return key;
    }


    /**
     * 获得随机原始密钥  要求八个字节；
     * @return
     */
    private String getOriginKey() {
        String codes = "123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i<8;  i++) {
            int index = new Random().nextInt(codes.length());

            sb.append(codes.charAt(index));
        }
        String copySb = sb+"";

        return copySb;
    }
}
