/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imsofa.utils;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author lendle
 */
public class HashUtil {
    public static String encode(String enc, String source) throws Exception{
        MessageDigest digest=MessageDigest.getInstance(enc);
        digest.update(source.getBytes());
        byte [] result=digest.digest();
        //return Base64.encodeBase64String(result);
        return Hex.encodeHexString(result).toLowerCase();
    }
    
    public static void main(String [] args) throws Exception{
        System.out.println(HashUtil.encode("sha-1", "123"));
    }
}
