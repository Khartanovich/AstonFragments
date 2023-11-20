package com.example.astonfragments.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astonfragments.ClickMoveToAnotherFragment
import com.example.astonfragments.R
import com.example.astonfragments.Utils
import com.example.astonfragments.databinding.FragmentABinding


class FragmentA : Fragment(R.layout.fragment_a) {
    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnMoveFragB.setOnClickListener {
            (requireActivity() as ClickMoveToAnotherFragment).clickMoveToAnotherFragment(Utils.FRAGMENT_B_TAG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}