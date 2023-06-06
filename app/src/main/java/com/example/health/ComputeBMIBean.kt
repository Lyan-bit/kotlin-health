package com.example.health

import android.content.Context
import java.util.*

class ComputeBMIBean(c: Context) {
    private var model: ModelFacade = ModelFacade.getInstance(c)

	private var heights = ""
	private var dheights = 0.0
	private var weight = ""
	private var dweight = 0.0

    private var errors = ArrayList<String>()

    fun setHeights(heightsx: String) {
        heights = heightsx
    }
    fun setWeight(weightx: String) {
        weight = weightx
    }

    fun resetData() {
	    //resetData
    }

    fun isComputeBMIError(): Boolean {
        errors.clear()
    try {
  dheights = heights.toDouble()
} catch (e: Exception) {
  errors.add("heights is not a Double")
}
    try {
  dweight = weight.toDouble()
} catch (e: Exception) {
  errors.add("weight is not a Double")
}

	if (dheights > 0.0) {
		//validation
	}
	else { errors.add("ComputeBMI: heights > 0.0") }
	if (dweight > 0.0) {
		//validation
	}
	else { errors.add("ComputeBMI: weight > 0.0") }
        return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }

     fun computeBMI (): Double {
        return model.computeBMI(dheights, dweight)
     }
}
