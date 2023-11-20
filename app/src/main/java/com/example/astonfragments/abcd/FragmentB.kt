package com.example.astonfragments.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astonfragments.ClickMoveToAnotherFragment
import com.example.astonfragments.Utils
import com.example.astonfragments.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            (requireActivity() as ClickMoveToAnotherFragment).clickMoveToAnotherFragment(Utils.FROM_B_TO_A)
        }
        binding.btnMoveFragC.setOnClickListener {
            (requireActivity() as ClickMoveToAnotherFragment).clickMoveToAnotherFragment(Utils.FRAGMENT_C_TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentB()
    }
}