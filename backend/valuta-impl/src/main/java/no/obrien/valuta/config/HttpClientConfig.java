package no.obrien.valuta.config;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class HttpClientConfig {

  private static final String CACHE_DIR = "tmp/valuta/cache";
  private static final long CACHE_SIZE = 50L * 1024L;

  @Bean
  public OkHttpClient okHttpCachingClient() {
    return new OkHttpClient.Builder()
        .cache(new Cache(new File(CACHE_DIR), CACHE_SIZE))
        .build();
  }
}
