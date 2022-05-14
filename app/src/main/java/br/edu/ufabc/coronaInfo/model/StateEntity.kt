package br.edu.ufabc.coronaInfo.model

import com.google.gson.annotations.SerializedName

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
        @SerializedName("city_ibge_code")
        val cityIbgeCode: String?,
        val confirmed: Long?,
        @SerializedName("confirmed_per_100k_inhabitants")
        val confirmedPer100kInhabitants: Double?,
        val date: String?,
        @SerializedName("death_rate")
        val deathRate: Double?,
        val deaths: Long?,
        @SerializedName("estimated_population")
        val estimatedPopulation: Long?,
        @SerializedName("estimated_population_2019")
        val estimatedPopulation2019: Long?,
        @SerializedName("is_last")
        val isLast: Boolean?,
        @SerializedName("order_for_place")
        val orderForPlace: Long?,
        @SerializedName("place_type")
        val placeType: String?,
        val state: String?
    )
}
