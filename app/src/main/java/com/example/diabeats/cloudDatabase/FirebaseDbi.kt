package com.example.diabeats.cloudDatabase

import android.util.Log
import com.example.diabeats.model.Classification
import com.example.diabeats.model.ClassificationVO
import com.google.firebase.database.*

class FirebaseDbi() {

    var database: DatabaseReference? = null

    companion object {
        private var instance: FirebaseDbi? = null
        fun getInstance(): FirebaseDbi {
            return instance ?: FirebaseDbi()
        }
    }

    init {
        connectByURL("https://diabeats-3bade-default-rtdb.firebaseio.com/")
    }

    fun connectByURL(url: String) {
        database = FirebaseDatabase.getInstance(url).reference
        if (database == null) {
            return
        }
        val classificationListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get instances from the cloud database
                val classification = dataSnapshot.value as Map<String, Object>?
                if (classification != null) {
                    val keys = classification.keys
                    for (key in keys) {
                        val x = classification[key]
                        ClassificationDAO.parseRaw(x)
                    }
                    // Delete local objects which are not in the cloud:
                    val locals = ArrayList<Classification>()
                    locals.addAll(Classification.ClassificationAllInstances)
                    for (x in locals) {
                        if (keys.contains(x.id)) {
                            //check
                        } else {
                            Classification.killClassification(x.id)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            //cancel
            }
        }
        database!!.child("classifications").addValueEventListener(classificationListener)
    }

    fun persistClassification(ex: Classification) {
        val evo = ClassificationVO(ex)
        val key = evo.id
        if (database == null) {
            return
        }
        database!!.child("classifications").child(key).setValue(evo)
    }

    fun deleteClassification(ex: Classification) {
        val key: String = ex.id
        if (database == null) {
            return
        }
        database!!.child("classifications").child(key).removeValue()
    }
}
