package com.example.diabeats.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.diabeats.cloudDatabase.FirebaseDbi
import com.example.diabeats.model.Classification
import com.example.diabeats.model.ClassificationVO


class CrudViewModel (context: Context): ViewModel() {

    private var cdb: FirebaseDbi = FirebaseDbi.getInstance()

    companion object {
        private var instance: CrudViewModel? = null
        fun getInstance(context: Context): CrudViewModel {
            return instance ?: CrudViewModel(context)
        }
    }

    /* This metatype code requires OclType.java, OclAttribute.java, OclOperation.java */
    fun initialiseOclTypes() {
        val classificationOclType: OclType = OclType.createByPKOclType("Classification")
        classificationOclType.setMetatype(Classification::class.java)
    }

    private var currentClassification: ClassificationVO? = null
    private var currentClassifications: ArrayList<ClassificationVO> = ArrayList()

    fun listClassification(): ArrayList<ClassificationVO> {
        val classifications: ArrayList<Classification> = Classification.ClassificationAllInstances
        currentClassifications.clear()
        for (i in classifications.indices) {
            currentClassifications.add(ClassificationVO(classifications[i]))
        }
        return currentClassifications
    }

    fun stringListClassification(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentClassifications.indices) {
            res.add(currentClassifications[x].toString() + "")
        }
        return res
    }

    fun getClassificationByPK(value: String): Classification? {
        return Classification.ClassificationIndex[value]
    }

    fun retrieveClassification(value: String): Classification? {
        return getClassificationByPK(value)
    }

    fun allClassificationids(): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        for (x in currentClassifications.indices) {
            res.add(currentClassifications[x].getid() + "")
        }
        return res
    }

    fun setSelectedClassification(x: ClassificationVO) {
        currentClassification = x
    }

    fun setSelectedClassification(i: Int) {
        if (i < currentClassifications.size) {
            currentClassification = currentClassifications[i]
        }
    }

    fun getSelectedClassification(): ClassificationVO? {
        return currentClassification
    }

    fun persistClassification(x: Classification) {
        val vo = ClassificationVO(x)
        cdb.persistClassification(x)
        currentClassification = vo
    }

    fun editClassification(x: ClassificationVO) {
        var obj = getClassificationByPK(x.getid())
        if (obj == null) {
            obj = Classification.createByPKClassification(x.getid())
        }
        obj.one = x.getone()
        obj.two = x.gettwo()
        obj.three = x.getthree()
        obj.four = x.getfour()
        obj.five = x.getfive()
        obj.six = x.getsix()
        obj.seven = x.getseven()
        obj.eight = x.geteight()
        obj.result = x.getresult()
        obj.id = x.getid()
        cdb.persistClassification(obj)
        currentClassification = x
    }

    fun createClassification(x: ClassificationVO) {
        editClassification(x)
    }

    fun deleteClassification(id: String) {
        val obj = getClassificationByPK(id)
        if (obj != null) {
            cdb.deleteClassification(obj)
            Classification.killClassification(id)
        }
        currentClassification = null
    }
}