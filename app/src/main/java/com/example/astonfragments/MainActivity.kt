package com.example.astonfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.astonfragments.abcd.FragmentA
import com.example.astonfragments.abcd.FragmentB
import com.example.astonfragments.abcd.FragmentC
import com.example.astonfragments.abcd.FragmentD
import com.example.astonfragments.contacts.AddUserFragment
import com.example.astonfragments.contacts.ContactsFragment
import com.example.astonfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ClickMoveToAnotherFragment,
    ContactsFragment.ClickMoveToAddUserFragment,
    AddUserFragment.PopBackStack {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun clickMoveToAnotherFragment(tag: String) {
        when (tag) {
            Utils.FRAGMENT_A_TAG -> {
                with(supportFragmentManager.beginTransaction()) {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        FragmentA.newInstance(),
                        Utils.FRAGMENT_A_TAG
                    )
                    addToBackStack(Utils.FRAGMENT_A_TAG)
                    commit()
                }
            }

            Utils.FRAGMENT_B_TAG -> {
                with(supportFragmentManager.beginTransaction()) {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        FragmentB.newInstance(),
                        Utils.FRAGMENT_B_TAG
                    )
                    addToBackStack(Utils.FRAGMENT_B_TAG)
                    commit()
                }
            }

            Utils.FRAGMENT_C_TAG -> {
                with(supportFragmentManager.beginTransaction()) {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container, FragmentC.newInstance("Hello Fragment C"),
                        Utils.FRAGMENT_C_TAG
                    )
                    addToBackStack(Utils.FRAGMENT_C_TAG)
                    commit()
                }
            }

            Utils.FRAGMENT_D_TAG -> {
                with(supportFragmentManager.beginTransaction()) {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        FragmentD.newInstance(),
                        Utils.FRAGMENT_D_TAG
                    )
                    addToBackStack(Utils.FRAGMENT_D_TAG)
                    commit()
                }
            }

            Utils.FROM_B_TO_A -> {
                supportFragmentManager.popBackStack()
            }

            Utils.FROM_C_TO_A -> {
                supportFragmentManager.popBackStack(
                    supportFragmentManager.getBackStackEntryAt(1).id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }

            Utils.FROM_D_TO_B -> {
                supportFragmentManager.popBackStack(
                    supportFragmentManager.getBackStackEntryAt(2).id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }

            Utils.CONTACTS_FRAGMENT -> {
                with(supportFragmentManager.beginTransaction()) {
                    setReorderingAllowed(true)
                    replace(
                        R.id.fragment_container,
                        ContactsFragment.newInstance(),
                        Utils.CONTACTS_FRAGMENT
                    )
                    addToBackStack(Utils.CONTACTS_FRAGMENT)
                    commit()
                }
            }
        }
    }

    override fun clickItemRV(id: Int, name: String, phone: String) {
        with(supportFragmentManager.beginTransaction()) {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                AddUserFragment.newInstance(id, name, phone),
                Utils.ADD_USER_FRAGMENT
            )
            addToBackStack(Utils.ADD_USER_FRAGMENT)
            commit()
        }
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }
}