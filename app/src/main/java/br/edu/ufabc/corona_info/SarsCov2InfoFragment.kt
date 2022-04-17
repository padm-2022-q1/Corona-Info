package br.edu.ufabc.corona_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ufabc.corona_info.databinding.FragmentSarsCov2InfoBinding

class SarsCov2InfoFragment : Fragment() {

    private lateinit var binding: FragmentSarsCov2InfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSarsCov2InfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}