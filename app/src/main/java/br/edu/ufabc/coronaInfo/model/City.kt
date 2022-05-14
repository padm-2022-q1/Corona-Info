package br.edu.ufabc.coronaInfo.model

/**
 * Transfer (domain) object.
 * @property city the city
 * @property city_ibge_code the city_ibge_code
 * @property confirmed the confirmed cases
 * @property confirmed_per_100k_inhabitants the confirmed cases per 100k inhabitants
 * @property date the date
 * @property death_rate the death rate
 * @property deaths the deaths
 * @property estimated_population the estimated population
 * @property estimated_population_2019 the estimated population 2019
 * @property is_last the is_last
 * @property order_for_place order for place
 * @property place_type the place type
 * @property state the state
 */
data class City(
    val city: String?,
    val city_ibge_code: String,
    val confirmed: Long,
    val confirmed_per_100k_inhabitants: Double,
    val date: String,
    val death_rate: Double,
    val deaths: Long,
    val estimated_population: Long,
    val estimated_population_2019: Long,
    val is_last: Boolean,
    val order_for_place: Long,
    val place_type: String,
    val state: String
)
