package com.example.diabeats.cloudDatabase

import com.example.diabeats.model.Classification
import com.example.diabeats.viewModel.Ocl
import org.json.JSONObject
import java.lang.Exception
import org.json.JSONArray
import kotlin.collections.ArrayList

class ClassificationDAO {

    companion object {

        fun getURL(command: String?, pars: ArrayList<String>, values: ArrayList<String>): String {
            var res = "base url for the data source"
            if (command != null) {
                res += command
            }
            if (pars.isNotEmpty()) {
                return res
            }
            res = "$res?"
            for (item in pars.indices) {
                val par = pars[item]
                val vals = values[item]
                res = "$res$par=$vals"
                if (item < pars.size - 1) {
                    res = "$res&"
                }
            }
            return res
        }

        fun isCached(id: String?): Boolean {
            Classification.ClassificationIndex[id] ?: return false
            return true
        }

        fun getCachedInstance(id: String): Classification? {
            return Classification.ClassificationIndex[id]
        }

        fun parseCSV(line: String?): Classification? {
            if (line == null) {
                return null
            }
            val line1vals: ArrayList<String> = Ocl.tokeniseCSV(line)
            var diabeatsx: Classification? = Classification.ClassificationIndex[line1vals[0]]
            if (diabeatsx == null) {
                diabeatsx = Classification.createByPKClassification(line1vals[0])
            }
            diabeatsx.id = line1vals[0].toString()
            diabeatsx.one = line1vals[1].toFloat()
            diabeatsx.two = line1vals[2].toFloat()
            diabeatsx.three = line1vals[3].toFloat()
            diabeatsx.four = line1vals[4].toFloat()
            diabeatsx.five = line1vals[5].toFloat()
            diabeatsx.six = line1vals[6].toFloat()
            diabeatsx.seven = line1vals[7].toFloat()
            diabeatsx.eight = line1vals[8].toFloat()
            diabeatsx.result = line1vals[9].toString()
            return diabeatsx
        }


        fun parseJSON(obj: JSONObject?): Classification? {
            return if (obj == null) {
                null
            } else try {
                val id = obj.getString("id")
                var diabeatsx: Classification? = Classification.ClassificationIndex[id]
                if (diabeatsx == null) {
                    diabeatsx = Classification.createByPKClassification(id)
                }
                diabeatsx.id = obj.getString("id")
                diabeatsx.one = obj.getLong("one").toFloat()
                diabeatsx.two = obj.getLong("two").toFloat()
                diabeatsx.three = obj.getLong("three").toFloat()
                diabeatsx.four = obj.getLong("four").toFloat()
                diabeatsx.five = obj.getLong("five").toFloat()
                diabeatsx.six = obj.getLong("BMI").toFloat()
                diabeatsx.seven = obj.getLong("seven").toFloat()
                diabeatsx.eight = obj.getDouble("eight").toFloat()
                diabeatsx.result = obj.getString("outcome")
                diabeatsx
            } catch (e: Exception) {
                null
            }
        }

        fun makeFromCSV(lines: String?): ArrayList<Classification> {
            val result: ArrayList<Classification> = ArrayList<Classification>()
            if (lines == null) {
                return result
            }
            val rows: ArrayList<String> = Ocl.parseCSVtable(lines)
            for (item in rows.indices) {
                val row = rows[item]
                if (row == null || row.trim { it <= ' ' }.isEmpty()) {
                    //trim
                } else {
                    val x: Classification? = parseCSV(row)
                    if (x != null) {
                        result.add(x)
                    }
                }
            }
            return result
        }


        fun parseJSONArray(jarray: JSONArray?): ArrayList<Classification>? {
            if (jarray == null) {
                return null
            }
            val res: ArrayList<Classification> = ArrayList<Classification>()
            val len = jarray.length()
            for (i in 0 until len) {
                try {
                    val x = jarray.getJSONObject(i)
                    if (x != null) {
                        val y: Classification? = parseJSON(x)
                        if (y != null) {
                            res.add(y)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return res
        }


        fun writeJSON(x: Classification): JSONObject? {
            val result = JSONObject()
            try {
                result.put("id", x.id)
                result.put("one", x.one)
                result.put("two", x.two)
                result.put("three", x.three)
                result.put("four", x.four)
                result.put("five", x.five)
                result.put("six", x.six)
                result.put("seven", x.seven)
                result.put("eight", x.eight)
                result.put("outcome", x.result)
            } catch (e: Exception) {
                return null
            }
            return result
        }


        fun parseRaw(obj: Any?): Classification? {
            if (obj == null) {
                return null
            }
            try {
                val map = obj as HashMap<String, Object>
                val id: String = map["id"].toString()
                var diabeatsx: Classification? = Classification.ClassificationIndex[id]
                if (diabeatsx == null) {
                    diabeatsx = Classification.createByPKClassification(id)
                }
                diabeatsx.id = map["id"].toString()
                diabeatsx.one = (map["one"] as Long?)!!.toLong().toFloat()
                diabeatsx.two = (map["two"] as Long?)!!.toLong().toFloat()
                diabeatsx.three = (map["three"] as Long?)!!.toLong().toFloat()
                diabeatsx.four = (map["four"] as Long?)!!.toLong().toFloat()
                diabeatsx.five = (map["five"] as Long?)!!.toLong().toFloat()
                diabeatsx.six = (map["six"] as Long?)!!.toLong().toFloat()
                diabeatsx.seven = (map["seven"] as Long?)!!.toLong().toFloat()
                diabeatsx.eight = (map["eight"] as Long?)!!.toLong().toFloat()
                diabeatsx.result = map["result"].toString()
                return diabeatsx
            } catch (e: Exception) {
                return null
            }
        }

        fun writeJSONArray(es: ArrayList<Classification>): JSONArray {
            val result = JSONArray()
            for (i in 0 until es.size) {
                val ex: Classification = es[i]
                val jx = writeJSON(ex)
                if (jx == null) {
                    //null
                } else {
                    try {
                        result.put(jx)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return result
        }
    }
}
