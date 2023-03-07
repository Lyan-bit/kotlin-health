package com.example.health

import android.content.Context
import java.util.*

class CalorieCountBean(c: Context) {
    private var model: ModelFacade = ModelFacade.getInstance(c)

	private var exercise = ""
	private var dexercise =  ""
	private var times = ""
	private var dtimes = 0.0

    private var errors = ArrayList<String>()

    fun setExercise(exercisex: String) {
        exercise = exercisex
    }
    fun setTimes(timesx: String) {
        times = timesx
    }

    fun resetData() {
    }

    fun isCalorieCountError(): Boolean {
        errors.clear()
          if (exercise != "") { dexercise = exercise }
else {
 	  errors.add("exercise cannot be empty")
 	  }
    try {
  dtimes = times.toDouble()
} catch (e: Exception) {
  errors.add("times is not a Double")
}

        return errors.size > 0
    }

    fun errors(): String {
        return errors.toString()
    }

     fun calorieCount (): Double {
        return model.calorieCount(dexercise, dtimes)
     }
}
