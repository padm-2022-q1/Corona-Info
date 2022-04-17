package br.edu.ufabc.corona_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.edu.ufabc.corona_info.databinding.FragmentPreventionTipsBinding

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