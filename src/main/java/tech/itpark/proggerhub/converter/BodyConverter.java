package tech.itpark.proggerhub.converter;

import java.io.Reader;
import java.io.Writer;

public interface BodyConverter {
  <T> T read(Reader reader, Class<T> clazz);
  // application/json
  boolean canRead(String contentType, Class<?> clazz);

  void write(Writer writer, Object value);
  boolean canWrite(String contentType, Object value);
}
