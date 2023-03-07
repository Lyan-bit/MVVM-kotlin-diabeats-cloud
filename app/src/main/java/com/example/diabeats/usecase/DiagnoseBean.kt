package com.example.diabeats.usecase

import android.content.Context
import com.example.diabeats.model.Classification
import com.example.diabeats.viewModel.ClassificationViewModel
import com.example.diabeats.viewModel.CrudViewModel

class DiagnoseBean(c: Context) {
    private var model: ClassificationViewModel = ClassificationViewModel.getInstance(c)
    private var crudvm = CrudViewModel.getInstance(c)

    private var classification = ""
    private var instanceClassification: Classification? = null

    private var errors = ArrayList<String>()

    fun setclassification(classificationx: String) {
        classification = classificationx
    }

    fun resetData() {
        classification = ""
    }

     fun isdiagnoseerror(): Boolean {
        errors.clear()
        instanceClassification = crudvm.getClassificationByPK(classification)
        if (instanceClassification == null) {
            errors.add("classification must be a valid Classification id")
        }
        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

    fun diagnose (): String {
        return model.classify(instanceClassification!!)
    }
}