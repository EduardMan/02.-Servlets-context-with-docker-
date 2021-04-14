package tech.itpark.proggerhub.crypto;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class TokenGenerator {
  private SecureRandom random = new SecureRandom();
  public static final int DEFAULT_LENGTH = 128;

  public String generate() {
    return generate(DEFAULT_LENGTH);
  }

  public String generate(int bytesLength) {
    final var bytes = new byte[bytesLength];
    random.nextBytes(bytes);
    return Hex.encode(bytes);
  }
}
