package com.texonapp.foodtruck.view.profileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.HelpQuestionAdapter
import com.texonapp.foodtruck.databinding.FragmentHelpBinding
import com.texonapp.foodtruck.model.HelpQuestionModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment


class HelpFragment : BaseFragment() {
    private lateinit var binding: FragmentHelpBinding

    private val adapter: HelpQuestionAdapter by lazy {
        HelpQuestionAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        initialData()
    }

    private fun initialData() {
        initRecycler()
        adapter.addAll(getHelpQuestions())
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {
                if (obj is HelpQuestionModel) {

                }
            }
        }
    }

    private fun getHelpQuestions(): ArrayList<HelpQuestionModel> {
        val data = ArrayList<HelpQuestionModel>()
        data.add(
            HelpQuestionModel(
                "Can I pay without a credit card? ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Nisi, proin cursus cum nunc. Eget id praesent cum in dapibus." +
                        " Id viverra iaculis sed tincidunt elementum risus tempus morbi lacus." +
                        " Viverra netus ut commodo, fames aenean eleifend nibh elementum." +
                        " Blandit a, egestas pretium amett."
            )
        )
        data.add(
            HelpQuestionModel(
                "Can I pay without a credit card? ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Nisi, proin cursus cum nunc. Eget id praesent cum in dapibus." +
                        " Id viverra iaculis sed tincidunt elementum risus tempus morbi lacus." +
                        " Viverra netus ut commodo, fames aenean eleifend nibh elementum." +
                        " Blandit a, egestas pretium amett."
            )
        )
        data.add(
            HelpQuestionModel(
                "Can I pay without a credit card? ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Nisi, proin cursus cum nunc. Eget id praesent cum in dapibus." +
                        " Id viverra iaculis sed tincidunt elementum risus tempus morbi lacus." +
                        " Viverra netus ut commodo, fames aenean eleifend nibh elementum." +
                        " Blandit a, egestas pretium amett."
            )
        )
        data.add(
            HelpQuestionModel(
                "Can I pay without a credit card? ",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                        " Nisi, proin cursus cum nunc. Eget id praesent cum in dapibus." +
                        " Id viverra iaculis sed tincidunt elementum risus tempus morbi lacus." +
                        " Viverra netus ut commodo, fames aenean eleifend nibh elementum." +
                        " Blandit a, egestas pretium amett."
            )
        )
        return data
    }
}
