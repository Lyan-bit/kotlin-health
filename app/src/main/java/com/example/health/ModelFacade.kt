package com.example.health

import android.content.Context

class ModelFacade private constructor(context: Context) {



    init {
    	//init
	}

    companion object {
        private var instance: ModelFacade? = null
        fun getInstance(context: Context): ModelFacade {
            return instance ?: ModelFacade(context)
        }
    }
    
    fun computeBMI(heights: Double, weight: Double): Double {
	      var result : Double
          result  = weight / (heights * heights)
	return result
	}
	          
    fun calorieCount(exercise: String, times: Double): Double {
	      var result : Double
        var factor: Double

        if (exercise == "walking") {
	            factor  = 100.0
        } else {
              if (exercise == "running") {
      	            factor  = 300.0
              } else {
                    if (exercise == "jogging") {
            	            factor  = 200.0
                    } else {
                            factor  = 250.0
                    }
              }
        }
          result  = factor * (times / 60.0)
	return result
	}
	          


		
}
