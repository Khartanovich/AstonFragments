package com.example.astonfragments.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfragments.App
import com.example.astonfragments.Utils
import com.example.astonfragments.databinding.FragmentContactsBinding
import java.util.Collections

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private val userAdapter = UserRVAdapter(object : ClickListener {
        override fun onItemClick(user: User) {
            (requireActivity() as ClickMoveToAddUserFragment)
                .clickItemRV(user.id, user.name, user.phoneNumber)
        }
    })

    private val repository: UserRepository
        get() = (requireContext().applicationContext as App).repository

    private val simpleItemTouchCallback =
        object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter = recyclerView.adapter as UserRVAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(repository.getDataList(), from, to)
                adapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(Utils.REQUEST_KEY) { _, bundle ->
            val id = bundle.getInt(Utils.ID)
            val name = bundle.getString(Utils.NAME)
            val phone = bundle.getString(Utils.PHONE_NUMBER)
            if (name != null && phone != null) {
                val newUser = User(id, name, phone, false)
                repository.updateInfoUser(newUser)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvUser.adapter = userAdapter
            rvUser.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(rvUser)
            userAdapter.setData(repository.getDataList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ClickMoveToAddUserFragment {
        fun clickItemRV(id: Int, name: String, phone: String)
    }

    companion object {
        fun newInstance() = ContactsFragment()
    }
}