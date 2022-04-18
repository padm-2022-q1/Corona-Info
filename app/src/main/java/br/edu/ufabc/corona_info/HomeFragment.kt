package br.edu.ufabc.corona_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import br.edu.ufabc.corona_info.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    enum class MenuItens { INFO, PREVENTION, VACCINE, STATISTICS }

    companion object {
        const val itemClickedKey = "itemClickedKey"
        const val itemClickedValue = "itemClickedValue"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()
        bindEvents()
    }

    private fun bindEvents() {
        binding.homeCardViewVirus.setOnClickListener {
            setFragmentResult(itemClickedKey, bundleOf(itemClickedValue to MenuItens.INFO))
        }

        binding.homeCardViewPreventionTips.setOnClickListener {
            setFragmentResult(itemClickedKey, bundleOf(itemClickedValue to MenuItens.PREVENTION))
        }

        binding.homeCardViewAboutVaccine.setOnClickListener {
            setFragmentResult(itemClickedKey, bundleOf(itemClickedValue to MenuItens.VACCINE))
        }

        binding.homeCardViewStatistics.setOnClickListener {
            setFragmentResult(itemClickedKey, bundleOf(itemClickedValue to MenuItens.STATISTICS))
        }
    }
}