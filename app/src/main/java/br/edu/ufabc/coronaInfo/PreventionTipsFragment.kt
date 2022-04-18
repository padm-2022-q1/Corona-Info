package br.edu.ufabc.coronaInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ufabc.coronaInfo.databinding.FragmentPreventionTipsBinding

class PreventionTipsFragment : Fragment() {
    private lateinit var binding: FragmentPreventionTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreventionTipsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
