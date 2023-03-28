package com.example.health

import android.os.Bundle
import android.widget.*
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.health.*
import java.lang.Exception

class CalorieCountFragment : Fragment(), View.OnClickListener , AdapterView.OnItemSelectedListener {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade

	private lateinit var calorieCountBean: CalorieCountBean
	private lateinit var calorieCount: Button
	private lateinit var cancelCalorieCount: Button
	private lateinit var calorieCountResult: TextView

	private lateinit var exerciseSpinner: Spinner
	private var exerciseListItems: Array<String> = arrayOf("walking", "jogging", "running", "swimming", "weights")
	private var exerciseData: String = ""
	private lateinit var timesTextField: EditText
	private var timesData: String = ""

	companion object {
		fun newInstance(c: Context): CalorieCountFragment {
			val fragment = CalorieCountFragment()
			val args = Bundle()
			fragment.arguments = args
			fragment.myContext = c
			return fragment
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onResume() {
		super.onResume()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		root = inflater.inflate(R.layout.caloriecount_layout, container, false)
		model = ModelFacade.getInstance(myContext)

		exerciseSpinner = root.findViewById(R.id.calorieCountExerciseSpinner)
		val exerciseAdapter = ArrayAdapter<String>(myContext, android.R.layout.simple_spinner_item,exerciseListItems)
		exerciseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
		exerciseSpinner.adapter = exerciseAdapter
		exerciseSpinner.onItemSelectedListener = this
		timesTextField = root.findViewById(R.id.calorieCountTimesField)

		calorieCountResult = root.findViewById(R.id.calorieCountResult)
		calorieCountBean = CalorieCountBean(myContext)

		calorieCount = root.findViewById(R.id.calorieCountOK)
		calorieCount.setOnClickListener(this)

		cancelCalorieCount = root.findViewById(R.id.calorieCountCancel)
		cancelCalorieCount.setOnClickListener(this)


		return root
	}

	override fun onClick(v: View?) {
		val imm = myContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		try {
			if (v != null) {
				imm.hideSoftInputFromWindow(v?.windowToken, 0)
			}
		} catch (e: Exception) {
			e.message
		}

		when (v?.id) {

			R.id.calorieCountOK-> {
				calorieCountOK()
			}
			R.id.calorieCountCancel-> {
				calorieCountCancel()
			}

		}

	}

	private fun calorieCountOK () {
		timesData = timesTextField.text.toString()

		calorieCountBean.setExercise(exerciseData)
		calorieCountBean.setTimes(timesData)

		if (calorieCountBean.isCalorieCountError()) {
			Log.w(javaClass.name, calorieCountBean.errors())
			Toast.makeText(myContext, "Errors: " + calorieCountBean.errors(), Toast.LENGTH_LONG).show()
		} else {
			calorieCountResult.text = calorieCountBean.calorieCount().toString()
			Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
		}
	}

	private fun calorieCountCancel () {
		calorieCountBean.resetData()
		calorieCountResult.text = ""
		timesTextField.setText("")

	}


	override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
		if (parent === exerciseSpinner) {
			exerciseData = exerciseListItems[position]
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>) {
		//onNothingSelected
	}

}
