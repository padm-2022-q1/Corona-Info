package br.edu.ufabc.coronaInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ufabc.coronaInfo.databinding.FragmentAboutVaccineBinding

class AboutVaccineFragment : Fragment() {
    private lateinit var binding: FragmentAboutVaccineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutVaccineBinding.inflate(inflater, container, false)
        return binding.root
    }
}
