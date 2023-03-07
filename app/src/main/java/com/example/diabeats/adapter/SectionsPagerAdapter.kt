package com.example.diabeats.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.diabeats.fragments.DiagnoseFragment
import com.example.diabeats.fragments.CreateFragment
import com.example.diabeats.fragments.ListFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        private val TABTITLES = arrayOf("+Classification", "ListClassification", "Classify")
    }

    override fun getItem(position: Int): Fragment { // instantiate a fragment for the page.
        return when (position) {
            0 -> {
                CreateFragment.newInstance(mContext)
            }
            1 -> {
                ListFragment.newInstance(mContext)
            }
            2 -> {
                DiagnoseFragment.newInstance(mContext)
            }
            else -> ListFragment.newInstance(mContext)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TABTITLES[position]
    }

    override fun getCount(): Int {
        return 3
    }
}
