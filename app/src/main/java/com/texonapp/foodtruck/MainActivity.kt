package com.texonapp.foodtruck


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import com.texonapp.foodtruck.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            NavigationUI.onNavDestinationSelected(item, navController)
            navController.popBackStack(item.itemId, false)
            true
        }

        binding.bottomNavigation.menu.getItem(2).isEnabled = false

        binding.centerButton.setOnClickListener {
            binding.bottomNavigation.menu.getItem(2).isEnabled = true
            binding.bottomNavigation.selectedItemId = R.id.orderFragment
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.profileFragment,
                R.id.invitationFragment,
                R.id.vouchersFragment,
                R.id.homeFragment -> {
                    binding.bottomNavigation.menu.getItem(2).isEnabled = false
                }
            }
        }

        hideKeyboard(this)

    }

    // close soft keyboard
    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showBottomNavigation() {
        binding.bottomAppBarShadow.visibility = View.INVISIBLE
        binding.bottomAppBar.visibility = View.VISIBLE
        binding.centerButtonShadow.visibility = View.VISIBLE
        binding.centerButton.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomAppBarShadow.visibility = View.GONE
        binding.bottomAppBar.visibility = View.GONE
        binding.centerButtonShadow.visibility = View.GONE
        binding.centerButton.visibility = View.GONE
    }

}