package com.android.surveyapp.ui.main

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import com.and.Fragment2
import com.android.surveyapp.R
import com.android.surveyapp.SurveyApp
import com.android.surveyapp.SurveyApp.Companion.mPage2_Section
import com.android.surveyapp.SurveyApp.Companion.mPage3_Resident
import com.android.surveyapp.ui.datamodel.Commondata
import com.android.surveyapp.ui.datamodel.Residential

class Fragment3 : Fragment() {
    lateinit var rootView: View
    companion object {
        fun newInstance() = Fragment3()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        return inflater.inflate(R.layout.fragment2, container, false)
        rootView = inflater.inflate(R.layout.fragment3, container, false)

        val b: Button =rootView.findViewById(R.id.button_next2)

        val et3_q35: AppCompatEditText =rootView.findViewById(R.id.et3_35)
        val et3_q38: AppCompatEditText =rootView.findViewById(R.id.et3_38)
        val et3_gis_id_sort: AppCompatEditText =rootView.findViewById(R.id.et3_gis_id_sort)
        val et3_annualincome: AppCompatEditText =rootView.findViewById(R.id.et3_annualincome)
        val et3_connect_to: AppCompatEditText =rootView.findViewById(R.id.et3_connect_to)
        val et3_connectiono: AppCompatEditText =rootView.findViewById(R.id.et3_connectiono)
        val et3_contactno: AppCompatEditText =rootView.findViewById(R.id.et3_contactno)
        val et3_educ: AppCompatEditText =rootView.findViewById(R.id.et3_educ)
        val et3_email: AppCompatEditText =rootView.findViewById(R.id.et3_email)
        val et3_family_size: AppCompatEditText =rootView.findViewById(R.id.et3_family_size)
        val et3_fname: AppCompatEditText =rootView.findViewById(R.id.et3_fname)
        val et3_gis_propid: AppCompatEditText =rootView.findViewById(R.id.et3_gis_propid)
        val et3_gisid: AppCompatEditText =rootView.findViewById(R.id.et3_gisid)
        val et3_grid: AppCompatEditText =rootView.findViewById(R.id.et3_grid)
        val et3_lname: AppCompatEditText =rootView.findViewById(R.id.et3_lname)
        val et3_loc: AppCompatEditText =rootView.findViewById(R.id.et3_loc)
        val et3_midname: AppCompatEditText =rootView.findViewById(R.id.et3_midname)
        val et3_occup: AppCompatEditText =rootView.findViewById(R.id.et3_occup)
        val et3_plotno: AppCompatEditText =rootView.findViewById(R.id.et3_plotno)
        val et3_prop_cat: AppCompatEditText =rootView.findViewById(R.id.et3_prop_cat)
        val et3_prop_name: AppCompatEditText =rootView.findViewById(R.id.et3_prop_name)
        val et3_prop_type: AppCompatEditText =rootView.findViewById(R.id.et3_prop_type)
        val et3_remark: AppCompatEditText =rootView.findViewById(R.id.et3_remark)
//            val et3_toilet: AppCompatEditText =rootView.findViewById(R.id.et3_toilet)
//            val et3_ugd: AppCompatEditText =rootView.findViewById(R.id.et3_ugd)
        val et3_ward_name: AppCompatEditText =rootView.findViewById(R.id.et3_ward_name)
        val et3_wardno: AppCompatEditText =rootView.findViewById(R.id.et3_wardno)
        val et3_watersource: AppCompatEditText =rootView.findViewById(R.id.et3_watersource)

        val sp3_q29: Spinner =rootView.findViewById(R.id.sp3_29)
        val sp3_q30: Spinner =rootView.findViewById(R.id.sp3_30)
        val sp3_q32: Spinner =rootView.findViewById(R.id.sp3_32)
        val sp3_q33: Spinner =rootView.findViewById(R.id.sp3_33)
        val sp3_q34: Spinner =rootView.findViewById(R.id.sp3_34)
        val sp3_q35: Spinner =rootView.findViewById(R.id.sp3_35)
        val sp3_q36: Spinner =rootView.findViewById(R.id.sp3_36)
        val sp3_q37: Spinner =rootView.findViewById(R.id.sp3_37)
        val sp3_toilet: Spinner =rootView.findViewById(R.id.sp3_toilet)
        val sp3_ugd: Spinner =rootView.findViewById(R.id.sp3_ugd)


        val rb31_1: CheckBox =rootView.findViewById(R.id.rb31_1)
        val rb31_2: CheckBox =rootView.findViewById(R.id.rb31_2)
        val rb31_3: CheckBox =rootView.findViewById(R.id.rb31_3)
        val rb31_4: CheckBox =rootView.findViewById(R.id.rb31_4)
        val rb31_5: CheckBox =rootView.findViewById(R.id.rb31_5)
        val rb31_6: CheckBox =rootView.findViewById(R.id.rb31_6)
        val rb31_7: CheckBox =rootView.findViewById(R.id.rb31_7)
        val rb31_8: CheckBox =rootView.findViewById(R.id.rb31_8)
        val rb31_9: CheckBox =rootView.findViewById(R.id.rb31_9)
        val rb31_10: CheckBox =rootView.findViewById(R.id.rb31_10)
        val rb31_11: CheckBox =rootView.findViewById(R.id.rb31_11)
        val rb31_12: CheckBox =rootView.findViewById(R.id.rb31_12)

//        val rg3_q31: RadioGroup =rootView.findViewById(R.id.rg3_31)
        val rg3_q37: RadioGroup =rootView.findViewById(R.id.rg3_37)
//          rg3_q37.check(0)

        var status = arrayOf("Yes", "No")
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp3_q29.adapter = adapter2
        sp3_q30.adapter = adapter2
        sp3_q32.adapter = adapter2
        sp3_q33.adapter = adapter2
        sp3_q34.adapter = adapter2
        sp3_q35.adapter = adapter2
        sp3_q36.adapter = adapter2
        sp3_q37.adapter = adapter2
        sp3_toilet.adapter = adapter2
        sp3_ugd.adapter = adapter2



        b.setOnClickListener {



            var string37: String = (rootView.findViewById(rg3_q37.getCheckedRadioButtonId()) as RadioButton).text.toString()
            mPage2_Section?.q37a=""+string37



            var chekQ31=""
            if (rb31_1.isChecked){
                chekQ31=rb31_1.text.toString()
            }
            if (rb31_2.isChecked){
                chekQ31=chekQ31+","+rb31_2.text.toString()
            }
            if (rb31_3.isChecked){
                chekQ31=chekQ31+","+rb31_3.text.toString()
            }
            if (rb31_4.isChecked){
                chekQ31=chekQ31+","+rb31_4.text.toString()
            }
            if (rb31_5.isChecked){
                chekQ31=chekQ31+","+rb31_5.text.toString()
            }
            if (rb31_6.isChecked){
                chekQ31=chekQ31+","+rb31_6.text.toString()
            }
            if (rb31_7.isChecked){
                chekQ31=chekQ31+","+rb31_7.text.toString()
            }
            if (rb31_8.isChecked){
                chekQ31=chekQ31+","+rb31_8.text.toString()
            }
            if (rb31_9.isChecked){
                chekQ31=chekQ31+","+rb31_9.text.toString()
            }
            if (rb31_10.isChecked){
                chekQ31=chekQ31+","+rb31_10.text.toString()
            }
            if (rb31_11.isChecked){
                chekQ31=chekQ31+","+rb31_11.text.toString()
            }
            if (rb31_12.isChecked){
                chekQ31=chekQ31+","+rb31_12.text.toString()
            }
            mPage2_Section?.q31=""+chekQ31

            mPage2_Section?.q35a=""+getValue(et3_q35)
            mPage2_Section?.q38 =""+getValue(et3_q38)


            val mResData:Residential= Residential("","","","","","","","","","","","","","","","","","","","","","","","","","")
            SurveyApp.mPage3_Resident =mResData

            mPage3_Resident?.R_GISPlotIDSorted =""+getValue(et3_gis_id_sort)
            mPage3_Resident?.R_AnnualIncome =""+getValue(et3_annualincome)
            mPage3_Resident?.R_connectsto =""+getValue(et3_connect_to)
            mPage3_Resident?.R_ConnectionNo =""+getValue(et3_connectiono)
            mPage3_Resident?.R_ContactNo =""+getValue(et3_contactno)
            mPage3_Resident?.R_Education =""+getValue(et3_educ)
            mPage3_Resident?.R_Email =""+getValue(et3_email)
            mPage3_Resident?.R_FamilySize =""+getValue(et3_family_size)
            mPage3_Resident?.R_FirstName =""+getValue(et3_fname)
            mPage3_Resident?.R_GISPropertyID =""+getValue(et3_gis_propid)
            mPage3_Resident?.R_GISPlotID =""+getValue(et3_gisid)
            mPage3_Resident?.R_GridNo =""+getValue(et3_grid)
            mPage3_Resident?.R_LastName =""+getValue(et3_lname)
            mPage3_Resident?.R_Location =""+getValue(et3_loc)
            mPage3_Resident?.R_MiddleName =""+getValue(et3_midname)
            mPage3_Resident?.R_Occupation =""+getValue(et3_occup)
            mPage3_Resident?.R_PlotNo =""+getValue(et3_plotno)
            mPage3_Resident?.R_PropertyCategory =""+getValue(et3_prop_cat)
            mPage3_Resident?.R_PropertyName =""+getValue(et3_prop_name)
            mPage3_Resident?.R_PropertyType =""+getValue(et3_prop_type)
            mPage3_Resident?.R_Remarks =""+getValue(et3_remark)
            mPage3_Resident?.R_Toilet =""+sp3_toilet.selectedItem
            mPage3_Resident?.R_UGDconnection=""+sp3_ugd.selectedItem
            mPage3_Resident?.R_WardName =""+getValue(et3_ward_name)
            mPage3_Resident?.R_WardNo =""+getValue(et3_wardno)
            mPage3_Resident?.R_WaterSource =""+getValue(et3_watersource)

            mPage2_Section?.q29 =""+sp3_q29.selectedItem
            mPage2_Section?.q30 =""+sp3_q30.selectedItem
            mPage2_Section?.q32 =""+sp3_q32.selectedItem
            mPage2_Section?.q33 =""+sp3_q33.selectedItem
            mPage2_Section?.q34 =""+sp3_q34.selectedItem
            mPage2_Section?.q35 =""+sp3_q35.selectedItem
            mPage2_Section?.q36 =""+sp3_q36.selectedItem
            mPage2_Section?.q37 =""+sp3_q37.selectedItem




            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment4.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }

       /* val back: Button =rootView.findViewById(R.id.button_back2)
        back.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment2.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }
*/
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
                    mPage3_Resident =null
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