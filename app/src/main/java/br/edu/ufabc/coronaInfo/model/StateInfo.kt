package br.edu.ufabc.coronaInfo.model

/**
 * Transfer (domain) object.
 * @property count count
 * @property next next
 * @property previous previous
 * @property results list od cities
 */
data class StateInfo(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<City>
)
