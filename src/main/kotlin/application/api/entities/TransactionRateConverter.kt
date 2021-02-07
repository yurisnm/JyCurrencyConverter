package application.api.entities

/**
 * Access an Api and get the rate that will be used for conversion.
 */
class TransactionRateConverter {

    /**
     * Access the api and get each rate value.
     * @property sourceCurrency[String] The currency in which the value is.
     * @property targetCurrency[String] The currency that the value will be converted.
     * @return [Float] A rate value to be used for currency conversion.
     */
    fun getConversionRate(sourceCurrency: String, targetCurrency: String): Float{
        val baseUrl = "https://api.exchangeratesapi.io/latest?base="
        val url = "${baseUrl}${sourceCurrency}"
        return khttp.get(url).jsonObject.getJSONObject("rates").getDouble(targetCurrency).toFloat()
    }
}