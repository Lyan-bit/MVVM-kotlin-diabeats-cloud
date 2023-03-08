package com.example.diabeats.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diabeats.adapter.ClassificationRecyclerViewAdapter
import com.example.diabeats.model.ClassificationVO
import com.example.diabeats.viewModel.CrudViewModel
import com.example.diabeats.R

class ListFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    private var root: View? = null
    private lateinit var myContext: Context
    private lateinit var model: CrudViewModel

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        fun newInstance(c: Context): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, 1)
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mColumnCount = requireArguments().getInt(ARG_COLUMN_COUNT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_layout, container, false)
        model = CrudViewModel.getInstance(myContext)

        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
        }
        root = view

        return root as View
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onResume() {
        super.onResume()
            (root as RecyclerView).adapter = ClassificationRecyclerViewAdapter(model.listClassification(), mListener!!)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: ClassificationVO)
    }
}
