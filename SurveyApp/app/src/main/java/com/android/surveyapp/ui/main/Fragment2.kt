package com.and


import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.surveyapp.R
import com.android.surveyapp.SurveyApp
import com.android.surveyapp.SurveyApp.Companion.mPage2_Section
import com.android.surveyapp.ui.main.Fragment3
import com.android.surveyapp.ui.main.MainFragment
import com.android.surveyapp.ui.main.MainViewModel


class Fragment2 : Fragment() {
    lateinit var rootView: View
    companion object {
        fun newInstance() = Fragment2()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.fragment2, container, false)
        rootView = inflater.inflate(R.layout.fragment2, container, false)

        val b: Button =rootView.findViewById(R.id.button_next2)

        val et2_q7: AppCompatEditText =rootView.findViewById(R.id.et2_7)
        val et2_q8: AppCompatEditText =rootView.findViewById(R.id.et2_8)
        val et2_q12: AppCompatEditText =rootView.findViewById(R.id.et2_12)
        val et2_q13: AppCompatEditText =rootView.findViewById(R.id.et2_13)
        val et2_q17: AppCompatEditText =rootView.findViewById(R.id.et2_17)
        val et2_q21: AppCompatEditText =rootView.findViewById(R.id.et2_21)
        val et2_q22: AppCompatEditText =rootView.findViewById(R.id.et2_22)
        val et2_q23: AppCompatEditText =rootView.findViewById(R.id.et2_23)
        val et2_q28: AppCompatEditText =rootView.findViewById(R.id.et2_28)

        val sp2_q9: Spinner =rootView.findViewById(R.id.sp2_9)
        val sp2_q11: Spinner =rootView.findViewById(R.id.sp2_11)
        val sp2_q15: Spinner =rootView.findViewById(R.id.sp2_15)
        val sp2_q18: Spinner =rootView.findViewById(R.id.sp2_18)
        val sp2_q20: Spinner =rootView.findViewById(R.id.sp2_20)
        val sp2_q26: Spinner =rootView.findViewById(R.id.sp2_26)

        val rg2_q10: RadioGroup =rootView.findViewById(R.id.rg2_10)
        val rg2_q14: RadioGroup =rootView.findViewById(R.id.rg2_14)
        val rg2_q16: RadioGroup =rootView.findViewById(R.id.rg2_16)
        val rg2_q19: RadioGroup =rootView.findViewById(R.id.rg2_19)
        val rg2_q24: RadioGroup =rootView.findViewById(R.id.rg2_24)
        val rg2_q25: RadioGroup =rootView.findViewById(R.id.rg2_25)
        val rg2_q27: RadioGroup =rootView.findViewById(R.id.rg2_27)

      /*  rg2_q10.check(0)
        rg2_q14.check(0)
        rg2_q16.check(0)
        rg2_q19.check(0)
        rg2_q24.check(0)
        rg2_q25.check(0)
        rg2_q27.check(0)
*/

        var status = arrayOf("Yes", "No")
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp2_q9.adapter = adapter2
        sp2_q11.adapter = adapter2
        sp2_q15.adapter = adapter2
        sp2_q18.adapter = adapter2
        sp2_q20.adapter = adapter2
        sp2_q26.adapter = adapter2

//radiogroup.check(IdOfYourButton)

        b.setOnClickListener {

            mPage2_Section?.q7 =""+getValue(et2_q7)
            mPage2_Section?.q8 =""+getValue(et2_q8)
            mPage2_Section?.q12 =""+getValue(et2_q12)
            mPage2_Section?.q13 =""+getValue(et2_q13)
            mPage2_Section?.q17 =""+getValue(et2_q17)
            mPage2_Section?.q21 =""+getValue(et2_q21)
            mPage2_Section?.q22 =""+getValue(et2_q22)
            mPage2_Section?.q23 =""+getValue(et2_q23)
            mPage2_Section?.q28 =""+getValue(et2_q28)

            mPage2_Section?.q9 =""+sp2_q9.selectedItem
            mPage2_Section?.q11 =""+sp2_q11.selectedItem
            mPage2_Section?.q15 =""+sp2_q15.selectedItem
            mPage2_Section?.q18 =""+sp2_q18.selectedItem
            mPage2_Section?.q20 =""+sp2_q20.selectedItem
            mPage2_Section?.q26 =""+sp2_q26.selectedItem


            var string: String = (rootView.findViewById(rg2_q10.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q10 = ""+string

            var string1: String = (rootView.findViewById(rg2_q14.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q14 = ""+string1

            var string2: String = (rootView.findViewById(rg2_q16.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q16 = ""+string2

            var string3: String = (rootView.findViewById(rg2_q19.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q19 = ""+string3

            var string4: String = (rootView.findViewById(rg2_q24.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q24 = ""+string4

            var string5: String = (rootView.findViewById(rg2_q25.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q25 = ""+string5

            var string6: String = (rootView.findViewById(rg2_q27.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q27 = ""+string6


            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment3.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }

       /* val back: Button =rootView.findViewById(R.id.button_back2)
        back.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, MainFragment.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }*/


        val scroll = rootView.findViewById(R.id.scroll99) as ScrollView
        scroll.setOnTouchListener { v, event ->
          /*  if (myEditText.hasFocus()) {
                myEditText.clearFocus()
            }*/
            false
        }


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun getValue(et1: AppCompatEditText):String {
        var value=""
        value=et1.text.toString().trim()
        return value
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //
                Log.i("-->","back pressed")
                val builder = AlertDialog.Builder(requireContext())
//                    builder.setTitle(R.string.dialogTitle)
                builder.setMessage("Do you want to cancel this form")
                builder.setIcon(android.R.drawable.ic_dialog_alert)
                builder.setPositiveButton("Yes"){dialogInterface, which ->
                    SurveyApp.mPage1_Common =null
                    mPage2_Section=null
                    SurveyApp.mPage3_Resident =null
                    SurveyApp.mPage4_Commercial =null
                    SurveyApp.mPage5_Apartments =null
                    SurveyApp.mPage6_Institutional =null
                    activity?.finish()

                }

                builder.setNegativeButton("No"){dialogInterface, which ->
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()


            }
        })
    }
}