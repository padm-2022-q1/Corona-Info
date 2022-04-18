package br.edu.ufabc.coronaInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import br.edu.ufabc.coronaInfo.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        adapterSpinner()
        return binding.root
    }

    private fun adapterSpinner() {
        val spinner = binding.statisticsDropdownList
        activity?.let {
            ArrayAdapter.createFromResource(
                it.baseContext,
                R.array.states_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        }
    }
}
