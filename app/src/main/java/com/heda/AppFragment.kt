package com.heda

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.heda.view_models.RouterViewModel
import kotlinx.android.synthetic.main.app.*
import kotlinx.android.synthetic.main.toolbar.*


class AppFragment : Fragment(R.layout.app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val router = ViewModelProvider(requireActivity())[RouterViewModel::class.java]

        //val casted: RoutingActivity = activity as RoutingActivity
        //casted.changePage(childFragmentManager, {() -> HomeFragment()})
        //(RoutingActivity)activity.changePage(childFragmentManager, {() -> HomeFragment()})

        //parentFragmentManager.beginTransaction().apply {
        //    replace(R.id.flApp, router.createCurrentTab())
        //    commit()
        //}

        imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        tlApp.getTabAt(router.tabPosition)?.select()

        tlApp.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0 -> router.changeTab(parentFragmentManager, tab.position) {-> HomeFragment()}
                        1 -> router.changeTab(parentFragmentManager, tab.position) {-> RecipesFragment()}
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

}