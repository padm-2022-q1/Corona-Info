package br.edu.ufabc.coronaInfo.model

/**
 * Transfer (domain) object.
 * @property count count
 * @property next next
 * @property previous previous
 * @property results list od cities
 */
class StateEntity {
    data class StateInfo(
        val count: Long?,
        val next: String?,
        val previous: String?,
        val results: List<City>?
    )

    data class City(
        val city: String?,
        val city_ibge_code: String?,
        val confirmed: Long?,
        val confirmed_per_100k_inhabitants: Double?,
        val date: String?,
        val death_rate: Double?,
        val deaths: Long?,
        val estimated_population: Long?,
        val estimated_population_2019: Long?,
        val is_last: Boolean?,
        val order_for_place: Long?,
        val place_type: String?,
        val state: String?
    )
}

