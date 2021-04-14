package tech.itpark.proggerhub.crypto;

public class Hex {
  public static final char[] chars = "0123456789abcdef".toCharArray();

  public static String encode(byte[] bytes) {
    final var result = new char[2 * bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      byte b = bytes[i];
      result[i * 2] = chars[(b & 0b1111_0000) >>> 4];
      result[i * 2 + 1] = chars[b & 0b0000_1111];
    }
    return new String(result);
  }
}
