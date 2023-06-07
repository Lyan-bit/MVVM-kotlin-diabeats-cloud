package com.example.diabeats.model

import android.content.Context
import com.example.diabeats.viewModel.CrudViewModel
import java.lang.Exception

class ClassificationBean(c: Context) {
    private var model: CrudViewModel = CrudViewModel.getInstance(c)

    private var one : String = ""
    private var done : Float = 0F
    private var two : String = ""
    private var dtwo : Float = 0F
    private var three : String = ""
    private var dthree : Float = 0F
    private var four : String = ""
    private var dfour : Float = 0F
    private var five : String = ""
    private var dfive : Float = 0F
    private var six : String = ""
    private var dsix : Float = 0F
    private var seven : String = ""
    private var dseven : Float = 0F
    private var eight : String = ""
    private var deight : Float = 0F
    private var result : String = ""
    private var id = ""

    private var errors = ArrayList<String>()

    fun setone(x: String) {
        one = x
    }

    fun settwo(x: String) {
        two = x
    }

    fun setthree(x: String) {
        three = x
    }

    fun setfour(x: String) {
        four = x
    }

    fun setfive(x: String) {
        five = x
    }

    fun setsix(x: String) {
        six = x
    }

    fun setseven(x: String) {
        seven = x
    }

    fun seteight(x: String) {
        eight = x
    }

    fun setresult(x: String) {
        result = x
    }

    fun setid(x: String) {
        id = x
    }

    fun resetData() {
        one = ""
        two = ""
        three = ""
        four = ""
        five = ""
        six = ""
        seven = ""
        eight = ""
        result = ""
        id = ""
    }

    fun iscreateClassificationError(): Boolean {
        errors.clear()

        try {
            done = one.toFloat()
        } catch (e: Exception) {
            errors.add("one is not a float")
        }

        try {
            dtwo = two.toFloat()
        } catch (e: Exception) {
            errors.add("two is not a float")
        }

        try {
            dthree = three.toFloat()
        } catch (e: Exception) {
            errors.add("three is not a float")
        }

        try {
            dfour = four.toFloat()
        } catch (e: Exception) {
            errors.add("four is not a float")
        }

        try {
            dfive = five.toFloat()
        } catch (e: Exception) {
            errors.add("five is not a float")
        }

        try {
            dsix = six.toFloat()
        } catch (e: Exception) {
            errors.add("six is not a float")
        }

        try {
            dseven = seven.toFloat()
        } catch (e: Exception) {
            errors.add("seven is not a float")
        }

        try {
            deight = eight.toFloat()
        } catch (e: Exception) {
            errors.add("eight is not a float")
        }

        return errors.isNotEmpty()
    }

    fun islistClassificationError(): Boolean {
        errors.clear()
        return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }

    fun createClassification () {
        model.createClassification (ClassificationVO(done, dtwo, dthree, dfour, dfive, dsix, dseven, deight, result, id))
        resetData()
    }

    fun deleteClassification() {
        model.deleteClassification(id)
        resetData()
    }
}
