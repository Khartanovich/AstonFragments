package com.example.astonfragments.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.astonfragments.ClickMoveToAnotherFragment
import com.example.astonfragments.R
import com.example.astonfragments.Utils
import com.example.astonfragments.databinding.FragmentCBinding

private const val ARG_PARAM1 = "param1"

class FragmentC : Fragment(R.layout.fragment_c) {
    private var param1: String? = null
    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        with(binding) {
            tvSomeText.text = param1.toString()

            btnMoveFragD.setOnClickListener {
                (requireActivity() as ClickMoveToAnotherFragment).clickMoveToAnotherFragment(Utils.FRAGMENT_D_TAG)
            }

            btnMoveFragA.setOnClickListener {
                (requireActivity() as ClickMoveToAnotherFragment).clickMoveToAnotherFragment(Utils.FROM_C_TO_A)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String?) =
            FragmentC().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}