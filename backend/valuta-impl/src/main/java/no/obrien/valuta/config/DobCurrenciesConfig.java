package no.obrien.valuta.config;

import no.dnb.openbanking.ApiClient;
import no.dnb.openbanking.api.CurrenciesApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DobCurrenciesConfig {

  @Value("${dob.currencies.url}")
  public String dobCurrenciesUrl;

  @Bean
  public CurrenciesApi currenciesApi(OkHttpClient okHttpCachingClient) {
    return new CurrenciesApi(this.apiClient(okHttpCachingClient));
  }

  private ApiClient apiClient(OkHttpClient okHttpCachingClient) {
    return new ApiClient(okHttpCachingClient).setBasePath(dobCurrenciesUrl);
  }
}
