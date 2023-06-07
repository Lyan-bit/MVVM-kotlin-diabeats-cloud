package com.example.diabeats.viewModel

import android.content.Context
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import com.example.diabeats.model.Classification
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class ClassificationViewModel (context: Context): ViewModel() {

    private val assetManager: AssetManager = context.assets
    private val crudViewModel = CrudViewModel.getInstance(context)

    companion object {
        private var instance: ClassificationViewModel? = null
        fun getInstance(context: Context): ClassificationViewModel {
            return instance ?: ClassificationViewModel(context)
        }
    }

    //classification
    private fun loadModelFile(assetManager: AssetManager, modelPath: String): ByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset, declaredLength)
    }

    fun classify(classification: Classification) : String {
        var res : String
        lateinit var tflite : Interpreter
        lateinit var tflitemodel : ByteBuffer

        try{
            tflitemodel = loadModelFile(assetManager, "Diabetes_model_8F.tflite")
            tflite = Interpreter(tflitemodel)
        } catch(ex: Exception){
            ex.printStackTrace()
        }

        val done: Float = (classification.one - 0) / (17-0)
        val dtwo = (classification.two - 0) / (199-0)
        val dthree = (classification.three - 0) / (122-0)
        val dfour = (classification.four - 0) / (99-0)
        val dfive = (classification.five - 0) / (846-0)
        val dsix = (classification.six - 0) / (67.1f-0)
        val dseven = (classification.seven - .078f) / (2.42f-.078f)
        val deight = (classification.eight - 21) / (81-21)

        val inputVal: FloatArray = floatArrayOf(done, dtwo, dthree, dfour, dfive, dsix, dseven,deight)
        val outputVal: ByteBuffer = ByteBuffer.allocateDirect(8)
        outputVal.order(ByteOrder.nativeOrder())

        tflite.run(inputVal, outputVal)
        outputVal.rewind()

        val result = FloatArray(2)
        for (i in 0..1) {
            result[i] = outputVal.float
        }

        if (result[0]>result[1])
            res = "Result is negative"
        else
            res = "Result is positive"

        classification.result = res
        crudViewModel.persistClassification(classification)

        return res
    }
}
