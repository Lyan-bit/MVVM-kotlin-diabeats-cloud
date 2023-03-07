package com.example.diabeats.model

import java.util.HashMap

class Classification {

    init {
        ClassificationAllInstances.add(this)
    }

    companion object {
        var ClassificationAllInstances = ArrayList<Classification>()

        fun createClassification(): Classification {
            return Classification()
        }

        var ClassificationIndex = HashMap<String, Classification>()

        fun createByPKClassification(idx: String): Classification {
            var result: Classification? = ClassificationIndex[idx]
            if (result != null) {return result}
            result = Classification ()
            ClassificationIndex.put(idx, result)
            result.id = idx
            return result
        }

        fun killClassification(idx: String) {
            val rem = ClassificationIndex[idx] ?: return
            val remd = ArrayList<Classification>()
            remd.add(rem)
            ClassificationIndex.remove(idx)
            ClassificationAllInstances.removeAll(remd.toSet())
        }
    }

    var one : Float = 0F
    var two : Float = 0F
    var three : Float = 0F
    var four : Float = 0F
    var five : Float = 0F
    var six : Float = 0F
    var seven : Float = 0F
    var eight : Float = 0F
    var result = ""
    var id = ""  /* primary */
}