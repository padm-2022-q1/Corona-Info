package br.edu.ufabc.corona_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import br.edu.ufabc.corona_info.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindEvents()
    }

    private fun bindEvents() {
        supportFragmentManager.setFragmentResultListener(HomeFragment.itemClickedKey,
            this) {
            _, bundle ->

            when (bundle.get(HomeFragment.itemClickedValue)) {
                HomeFragment.MenuItens.INFO ->
                    supportFragmentManager.commit {
                        replace(binding.mainFragmentContainer.id, SarsCov2InfoFragment())
                        addToBackStack(null)
                    }
                HomeFragment.MenuItens.STATISTICS ->
                    supportFragmentManager.commit {
                        replace(binding.mainFragmentContainer.id, StatisticsFragment())
                        addToBackStack(null)
                    }
            }
        }
    }
}