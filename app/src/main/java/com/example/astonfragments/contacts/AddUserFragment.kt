package com.example.astonfragments.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.example.astonfragments.Utils
import com.example.astonfragments.databinding.FragmentAddUserBinding


class AddUserFragment : Fragment() {
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idOldUser = arguments?.getInt(Utils.ID)
        val oldName = arguments?.getString(Utils.NAME)
        val oldPhone = arguments?.getString(Utils.PHONE_NUMBER)

        if (idOldUser != null) {
            changeUserInfo(idOldUser, oldName, oldPhone)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeUserInfo(userId: Int, userName: String?, userPhone: String?) {
        with(binding) {
            idText.text = userId.toString()
            writeName.setText(userName)
            writePhone.setText(userPhone)
            save.setOnClickListener {
                val name = writeName.text.toString()
                val phoneNumber = writePhone.text.toString()
                if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                    val bundle = Bundle().apply {
                        putInt(Utils.ID, userId)
                        putString(Utils.NAME, name)
                        putString(Utils.PHONE_NUMBER, phoneNumber)
                    }
                    setFragmentResult(Utils.REQUEST_KEY, bundle)
                    (requireActivity() as PopBackStack).popBackStack()

                } else {
                    Toast.makeText(requireContext(), "Внесите данные", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            cansel.setOnClickListener {
                (requireActivity() as PopBackStack).popBackStack()
            }
        }
    }

    interface PopBackStack {
        fun popBackStack()
    }

    companion object {
        fun newInstance(id: Int, name: String, phone: String) = AddUserFragment().apply {
            arguments = Bundle().apply {
                putInt(Utils.ID, id)
                putString(Utils.NAME, name)
                putString(Utils.PHONE_NUMBER, phone)
            }
        }
    }
}