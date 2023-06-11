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

    var id = ""  /* identity */
    var pregnancies = 0
    var glucose = 0
    var bloodPressure = 0
    var skinThickness = 0
    var insulin = 0
    var bmi = 0.0
    var diabetesPedigreeFunction = 0.0
    var age = 0
    var outcome = ""  /* derived */
}
