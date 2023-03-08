package com.example.diabeats.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.diabeats.usecase.DiagnoseBean
import com.example.diabeats.viewModel.CrudViewModel
import com.example.diabeats.R
import java.lang.Exception
import java.util.ArrayList

class DiagnoseFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var root: View
    private lateinit var myContext: Context
    private lateinit var diagnosebean: DiagnoseBean
    private lateinit var crudViewModel: CrudViewModel

    private lateinit var diagnoseSpinner: Spinner
    private var diagnoseListItems: List<String> = ArrayList()
    private lateinit var diagnoseAdapter: ArrayAdapter<String>
    private lateinit var diagnoseEditText: EditText

    private var diagnoseData = ""
    private lateinit var diagnoseResult: TextView

    private lateinit var diagnose: Button
    private lateinit var cancel: Button

    companion object {
        fun newInstance(c: Context): DiagnoseFragment {
            val fragment = DiagnoseFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.myContext = c
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        root = inflater.inflate(R.layout.diagnose_layout, container, false)

        crudViewModel = CrudViewModel.getInstance(myContext)
        diagnoseSpinner = root.findViewById(R.id.diagnosepinner)
        Log.i("model1",crudViewModel.listClassification().toString())
        Log.i("model1",crudViewModel.allClassificationids().toString())
        diagnoseListItems = crudViewModel.allClassificationids()

        diagnoseAdapter = ArrayAdapter(myContext, android.R.layout.simple_spinner_item, diagnoseListItems)
        diagnoseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diagnoseSpinner.adapter = diagnoseAdapter

        diagnoseSpinner.onItemSelectedListener = this
        diagnoseResult = root.findViewById(R.id.diagnoseResult)
        diagnosebean = DiagnoseBean(myContext)

        diagnoseEditText = root.findViewById(R.id.diagnoseEditText)
        diagnoseEditText.setText(diagnoseData)

        diagnose = root.findViewById(R.id.diagnoseOK)
        diagnose.setOnClickListener(this)

        cancel = root.findViewById(R.id.diagnoseCancel)
        cancel.setOnClickListener(this)



        return root
    }

    override fun onClick(v: View?) {
        val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            if (v != null) {
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        when (v?.id) {
            R.id.diagnoseOK -> {
                diagnoseOK()
            }
            R.id.diagnoseCancel -> {
                diagnoseCancel()
            }
        }
    }

    private fun diagnoseOK () {
        diagnoseData = diagnoseEditText.text.toString()
        diagnosebean.setclassification(diagnoseData)
        if (diagnosebean.isdiagnoseerror()) {
            Log.w(javaClass.name, diagnosebean.errors())
            Toast.makeText(myContext, "Errors: " + diagnosebean.errors(), Toast.LENGTH_LONG).show()
        } else {
            diagnoseResult.text = diagnosebean.diagnose()
            Toast.makeText(myContext, "Classification updated!", Toast.LENGTH_LONG).show()
        }
    }

    private fun diagnoseCancel () {
        diagnosebean.resetData()
        diagnoseResult.text = ""
    }

    override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
        if (parent === diagnoseSpinner) {
            diagnoseData = diagnoseListItems[position]
            diagnoseEditText.setText(diagnoseData)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        diagnoseData = ""
    }
}

