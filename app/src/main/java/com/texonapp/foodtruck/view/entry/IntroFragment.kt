package com.texonapp.foodtruck.view.entry

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentIntroBinding
import com.texonapp.foodtruck.util.IS_SEEN_INTRO
import com.texonapp.foodtruck.util.SharedPreferenceUtil
import com.texonapp.foodtruck.view.base.BaseFragment

class IntroFragment : BaseFragment() {

    private lateinit var binding: FragmentIntroBinding
    private val layouts: IntArray = intArrayOf(
        R.layout.intro_one,
        R.layout.intro_two,
        R.layout.intro_three,
        R.layout.intro_four,
        R.layout.intro_five,
        R.layout.intro_six
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        val view = binding.root
        initData()
        return view
    }

    override fun listeners() {
        binding.btnNext.setOnClickListener {
            val current = getNextSlidePosition()
            if (current < layouts.size) {
                binding.viewPager.currentItem = current
            } else {
                launchLogin()
            }
        }

        binding.btnSkip.setOnClickListener {
            launchLogin()
        }
    }

    private fun initData() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
             requireActivity().finish()
            }
        })
        val myViewPagerAdapter = MyViewPagerAdapter()
        binding.viewPager.adapter = myViewPagerAdapter
        binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        binding.dotsIndicator.setViewPager(binding.viewPager)
    }

    private fun getNextSlidePosition(): Int {
        return binding.viewPager.currentItem + 1
    }

    private fun launchLogin() {
        //SharedPreferenceUtil.saveValue(IS_SEEN_INTRO, true)
        findNavController().navigate(R.id.action_introFragment_to_loginFragment)
    }

    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            when (position) {
                layouts.size - 1 -> {
                    binding.btnNext.text = getString(R.string.get_start)
                    binding.btnSkip.visibility = View.GONE
                }
                else -> {
                    binding.btnNext.text = getString(R.string.next)
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater =
                requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
            val view: View = layoutInflater!!.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

}