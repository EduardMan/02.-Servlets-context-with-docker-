package tech.itpark.proggerhub.converter;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import tech.itpark.proggerhub.exception.ConversionException;
import tech.itpark.servlet.ContentTypes;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

@Component
public class GsonBodyConverter implements BodyConverter {
  private final Gson gson = new Gson();

  @Override
  public <T> T read(Reader reader, Class<T> clazz) {
      return gson.fromJson(reader, clazz);
  }

  @Override
  public boolean canRead(String contentType, Class<?> clazz) {
    return ContentTypes.APPLICATION_JSON.equals(contentType);
  }

  @Override
  public void write(Writer writer, Object value) {
    try {
      writer.write(gson.toJson(value));
    } catch (IOException e) {
      throw new ConversionException(e);
    }
  }

  @Override
  public boolean canWrite(String contentType, Object value) {
    return ContentTypes.APPLICATION_JSON.equals(value);
  }
}
