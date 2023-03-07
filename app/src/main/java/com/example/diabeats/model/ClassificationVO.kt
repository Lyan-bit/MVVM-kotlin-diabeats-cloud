package com.example.diabeats.model

import java.util.ArrayList

class ClassificationVO {
    private var one : Float = 0F
    private var two : Float = 0F
    private var three : Float = 0F
    private var four : Float = 0F
    private var five : Float = 0F
    private var six : Float = 0F
    private var seven : Float = 0F
    private var eight : Float = 0F
    private lateinit var result : String
    private lateinit var id: String

    constructor() {}

    constructor(
        onex: Float,
        twox: Float,
        threex: Float,
        fourx: Float,
        fivex: Float,
        sixx: Float,
        sevenx: Float,
        eightx: Float,
        resultx: String,
        idx: String,
    ) {
        one = onex
        two = twox
        three = threex
        four = fourx
        five = fivex
        six = sixx
        seven = sevenx
        eight = eightx
        result = resultx
        id = idx
    }

    constructor (x: Classification) {
        one = x.one
        two = x.two
        three = x.three
        four = x.four
        five = x.five
        six = x.six
        seven =x.seven
        eight = x.eight
        result = x.result
        id = x.id
    }

    override fun toString(): String {
        return "one= $one,two= $two,three= $three,four= $four,five= $five,six= $six,seven= $seven,eight= $eight,result= $result, id= $id"
    }

    fun toStringList(list: List<ClassificationVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    fun getone(): Float {
        return one
    }
    fun gettwo(): Float {
        return two
    }
    fun getthree(): Float {
        return three
    }
    fun getfour(): Float {
        return four
    }
    fun getfive(): Float {
        return five
    }
    fun getsix(): Float {
        return six
    }
    fun getseven(): Float {
        return seven
    }
    fun geteight(): Float {
        return eight
    }
    fun getresult(): String {
        return result
    }
    fun getid(): String {
        return id
    }

    fun setone(x: Float) {
        one = x
    }

    fun settwo(x: Float) {
        two = x
    }

    fun setthree(x: Float) {
        three = x
    }

    fun setfour(x: Float) {
        four = x
    }

    fun setfive(x: Float) {
        five = x
    }

    fun setsix(x: Float) {
        six = x
    }

    fun setseven(x: Float) {
        seven = x
    }

    fun seteight(x: Float) {
        eight = x
    }
    fun setresult(x: String) {
        result = x
    }

    fun setid(x: String) {
        id = x
    }
}