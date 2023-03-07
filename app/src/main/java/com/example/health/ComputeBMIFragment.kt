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

class ComputeBMIFragment : Fragment(), View.OnClickListener  {
	private lateinit var root: View
	private lateinit var myContext: Context
	private lateinit var model: ModelFacade
			
	private lateinit var computeBMIBean: ComputeBMIBean
	private lateinit var computeBMI: Button
	private lateinit var cancelComputeBMI: Button
	private lateinit var computeBMIResult: TextView

	private lateinit var heightsTextField: EditText
	private var heightsData: String = ""
	private lateinit var weightTextField: EditText
	private var weightData: String = ""
		  		
    companion object {
        fun newInstance(c: Context): ComputeBMIFragment {
            val fragment = ComputeBMIFragment()
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
		root = inflater.inflate(R.layout.computebmi_layout, container, false)
        model = ModelFacade.getInstance(myContext)
        
		heightsTextField = root.findViewById(R.id.computeBMIHeightsField)
		weightTextField = root.findViewById(R.id.computeBMIWeightField)

		computeBMIResult = root.findViewById(R.id.computeBMIResult)
		computeBMIBean = ComputeBMIBean(myContext)

        computeBMI = root.findViewById(R.id.computeBMIOK)
        computeBMI.setOnClickListener(this)
	
        cancelComputeBMI = root.findViewById(R.id.computeBMICancel)
        cancelComputeBMI.setOnClickListener(this)
        
			
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
			
			R.id.computeBMIOK-> {
				 computeBMIOK()
			}
			R.id.computeBMICancel-> {
				 computeBMICancel()
			}
			
		}
		
	}
	
	private fun computeBMIOK () {
    heightsData = heightsTextField.text.toString()
    weightData = weightTextField.text.toString()

				computeBMIBean.setHeights(heightsData)
		computeBMIBean.setWeight(weightData)

	    if (computeBMIBean.isComputeBMIError()) {
	       Log.w(javaClass.name, computeBMIBean.errors())
	       Toast.makeText(myContext, "Errors: " + computeBMIBean.errors(), Toast.LENGTH_LONG).show()
	    } else {
	       computeBMIResult.text = computeBMIBean.computeBMI().toString()
	       Toast.makeText(myContext, "done!", Toast.LENGTH_LONG).show()
	    }
	}
	
	private fun computeBMICancel () {
	    computeBMIBean.resetData()
	    computeBMIResult.text = ""
		heightsTextField.setText("")
		weightTextField.setText("")

	}
	


}
