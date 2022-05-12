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
 * @property place_type the place type
 * @property state the state
 *
 */
data class StateInfo(
    val city: Long,
    val city_ibge_code: String,
    val confirmed: String,
    val confirmed_per_100k_inhabitants: String,
    val date: String,
    val death_rate: Long,
    val deaths: Long,
    val estimated_population: Long,
    val estimated_population_2019: Long,
    val is_last: Boolean,
    val place_type: String,
    val state: String
)
