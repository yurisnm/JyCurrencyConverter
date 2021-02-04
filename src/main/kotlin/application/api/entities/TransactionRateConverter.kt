package application.api.entities

class TransactionRateConverter {

    fun getConversionRate(sourceCurrency: String, targetCurrency: String): Float{
        val baseUrl = "https://api.exchangeratesapi.io/latest?base="
        val url = "${baseUrl}${sourceCurrency}"
        return khttp.get(url).jsonObject.getJSONObject("rates").getDouble(targetCurrency).toFloat()
    }
}