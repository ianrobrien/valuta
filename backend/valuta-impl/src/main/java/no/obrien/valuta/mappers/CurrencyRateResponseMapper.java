package no.obrien.valuta.mappers;

import no.dnb.openbanking.models.CurrencyRateResponse;
import no.obrien.valuta.models.CurrencyRateTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyRateResponseMapper {

  @Mapping(target = "baseCurrency", source = "baseCurrency")
  @Mapping(target = "quoteCurrency", source = "quoteCurrency")
  @Mapping(target = "baseAmount", source = "amount")
  @Mapping(target = "midRate", source = "midRate")
  @Mapping(target = "updatedDate", source = "updatedDate")
  @Mapping(target = "buyRateTransfer", source = "buyRateTransfer")
  @Mapping(target = "sellRateTransfer", source = "sellRateTransfer")
  @Mapping(target = "changeInMidRate", source = "changeInMidRate")
  @Mapping(target = "previousMidRate", source = "previousMidRate")
  @Mapping(target = "buyRateCash", source = "buyRateCash")
  @Mapping(target = "sellRateCash", source = "sellRateCash")
  CurrencyRateTO toCurrencyRateTO(CurrencyRateResponse currencyRateResponse);

}
