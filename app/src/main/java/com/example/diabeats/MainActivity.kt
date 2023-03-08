package com.example.diabeats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.diabeats.adapter.SectionsPagerAdapter
import com.example.diabeats.fragments.ListFragment
import com.example.diabeats.model.ClassificationVO
import com.example.diabeats.viewModel.CrudViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {

    private lateinit var model: CrudViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewpager: ViewPager = findViewById(R.id.view_pager)
        viewpager.adapter = myPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewpager)
        model = CrudViewModel.getInstance(this)
    }

    override fun onListFragmentInteraction(item: ClassificationVO) {
        model.setSelectedClassification(item)
    }
}
