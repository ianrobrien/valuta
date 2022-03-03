package no.obrien.valuta.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.dnb.openbanking.ApiException;
import no.dnb.openbanking.api.CurrenciesApi;
import no.dnb.openbanking.models.CurrencyRateResponse;
import no.obrien.valuta.api.ValutaApi;
import no.obrien.valuta.mappers.CurrencyRateResponseMapper;
import no.obrien.valuta.models.CurrencyRateTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
@Slf4j
public class ValutaResourceImpl implements ValutaApi {

  private final CurrenciesApi currenciesApi;
  private final CurrencyRateResponseMapper currencyRateResponseMapper;

  @Override
  public ResponseEntity<CurrencyRateTO> getCurrencyRate(
      String baseCurrency,
      String quoteCurrency,
      @NotNull @Valid String amount,
      String xApiKey) {
    CurrencyRateResponse currencyRateResponse = null;
    try {
      currencyRateResponse = currenciesApi
          .getCurrencyRateUsingGET(baseCurrency, quoteCurrency, xApiKey, amount);
    } catch (ApiException ex) {
      log.error("Could not fetch currency rate", ex);
    }

    return currencyRateResponse == null
        ? ResponseEntity.badRequest().build()
        : ResponseEntity.ok(currencyRateResponseMapper.toCurrencyRateTO(currencyRateResponse));
  }

  @Override
  public ResponseEntity<List<CurrencyRateTO>> getCurrencyRateList(
      String quoteCurrency,
      String xApiKey) {
    List<CurrencyRateResponse> currencyRateResponses = null;
    try {
      currencyRateResponses = currenciesApi
          .getCurrencyRatesListUsingGET(quoteCurrency, xApiKey);
    } catch (ApiException ex) {
      log.error("Could not fetch currency rate list", ex);
    }

    return currencyRateResponses == null
        ? ResponseEntity.badRequest().build()
        : ResponseEntity.ok(
            currencyRateResponses
                .stream()
                .map(currencyRateResponseMapper::toCurrencyRateTO)
                .collect(Collectors.toList()));
  }
}
