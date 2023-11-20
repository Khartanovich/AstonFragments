package com.example.astonfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.astonfragments.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoFragmentA.setOnClickListener {
            (requireActivity() as ClickMoveToAnotherFragment)
                .clickMoveToAnotherFragment(Utils.FRAGMENT_A_TAG)
        }
        binding.btnGoFragmentContacts.setOnClickListener {
            (requireActivity() as ClickMoveToAnotherFragment)
                .clickMoveToAnotherFragment(Utils.CONTACTS_FRAGMENT)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}