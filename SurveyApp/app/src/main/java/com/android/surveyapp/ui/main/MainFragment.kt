package com.android.surveyapp.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.and.Fragment2
import com.android.surveyapp.R
import com.android.surveyapp.SurveyApp
import com.android.surveyapp.SurveyApp.Companion.mPage1_Common
import com.android.surveyapp.SurveyApp.Companion.mPage2_Section
import com.android.surveyapp.SurveyApp.Companion.mPage3_Resident
import com.android.surveyapp.SurveyApp.Companion.mPage4_Commercial
import com.android.surveyapp.SurveyApp.Companion.mPage5_Apartments
import com.android.surveyapp.SurveyApp.Companion.mPage6_Institutional
import com.android.surveyapp.ui.datamodel.Commondata
import com.android.surveyapp.ui.datamodel.SectionData
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {
    //    @Inject
    lateinit var rootViewProjectDocuments: View
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        rootViewProjectDocuments = inflater.inflate(R.layout.main_fragment, container, false)

        val b:Button=rootViewProjectDocuments.findViewById(R.id.button_next)
        val sp_investigator: Spinner =rootViewProjectDocuments.findViewById(R.id.sp_investigatorname)
        val et1_starttime:AppCompatTextView=rootViewProjectDocuments.findViewById(R.id.et1_starttime)
        val et1_date:AppCompatTextView=rootViewProjectDocuments.findViewById(R.id.et1_date)  // its text view not et
        val et1_endtime:AppCompatTextView=rootViewProjectDocuments.findViewById(R.id.et1_endtime)
        val et1_gis_plot:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_gis_plot)
        val et1_loc:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_loc)
        val et1_ward:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_ward)
        val et1_plotno:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_plotno)
        val et1_prop_type:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_prop_type)
        val et1_connection_no:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_connection_no)
        val et1_q1:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_owner)
        val et1_q2:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_address)
        val et1_other:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_other)
        val et1_specifyother:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_specifyother)

        val et1_grid_no:AppCompatEditText=rootViewProjectDocuments.findViewById(R.id.et1_grid_no)

        val sp1_q3a:Spinner=rootViewProjectDocuments.findViewById(R.id.sp1_male)
        val sp1_q3b:Spinner=rootViewProjectDocuments.findViewById(R.id.sp1_female)
        val sp1_q3c:Spinner=rootViewProjectDocuments.findViewById(R.id.sp1_malechild)
        val sp1_q3d:Spinner=rootViewProjectDocuments.findViewById(R.id.sp1_femalechaild)
        val sp1_status:Spinner=rootViewProjectDocuments.findViewById(R.id.sp1_status)

        val rg1_q6: RadioGroup =rootViewProjectDocuments.findViewById(R.id.rg1_6)



        val cb1_1:CheckBox=rootViewProjectDocuments.findViewById(R.id.checkBox1)
        val cb1_2:CheckBox=rootViewProjectDocuments.findViewById(R.id.checkBox2)
        val cb1_3:CheckBox=rootViewProjectDocuments.findViewById(R.id.checkBox3)
        val cb1_4:CheckBox=rootViewProjectDocuments.findViewById(R.id.checkBox4)
        val cb1_5:CheckBox=rootViewProjectDocuments.findViewById(R.id.checkBox5)


        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
//                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ), 5
        )


        var numbers = arrayOf("  0  ", "  1  ", "  2  ", "  3  ", "  4  ", "  5  ","  6  ","  7  ","  8  ")
        var status = arrayOf("Yes", "No")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, numbers)
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp1_q3a.adapter = adapter
        sp1_q3b.adapter = adapter
        sp1_q3c.adapter = adapter
        sp1_q3d.adapter = adapter
        sp1_status.adapter = adapter2

        var investigator = arrayOf("Select",
            "Mohammed Owais (18-145)",
            "Mohammed Faisal (18-148)",
            "D.Uma Maheshwar (19-108)",
            "B.Sai Kiran(20-109)",
            "K.Narendar(19-116)",
            "Aakash (20-111)",
            "K.Pranay(20-131)",
            "Mohammad Nouman ( 18 -138)",
            "Mohammad Abrar uddin (18-133)",
            "P.bhavani (20-150)",
            "R.sowmya (20-153)",
            "B.shiva teja (20-112)",
            "Mohd abdul farhan(18-141)",
            "Mohammed sameer(18-140)",
            "Marripelli Soujanya (20-142)",
            "Vodnala sravanthi (20-163)",
            "Team B",
            "Team C"
        )

        val adapter3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, investigator)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_investigator.adapter = adapter3


        et1_date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                et1_date.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
            }, year, month, day)
            dpd.show()
        }

        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            et1_starttime.text = SimpleDateFormat("hh:mm a").format(cal.time)
        }
        val cal2 = Calendar.getInstance()
        val timeSetListener2 = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal2.set(Calendar.HOUR_OF_DAY, hour)
            cal2.set(Calendar.MINUTE, minute)
            et1_endtime.text = SimpleDateFormat("hh:mm a").format(cal2.time)
        }

        et1_starttime.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }

        et1_endtime.setOnClickListener {
            TimePickerDialog(context, timeSetListener2, cal2.get(Calendar.HOUR_OF_DAY), cal2.get(Calendar.MINUTE), false).show()
        }



        b.setOnClickListener {

//            Log.i("",""+getValue(et1_investigator))
            if (sp_investigator.selectedItemPosition==0){
                Toast.makeText(requireContext(),"Select Investigator",Toast.LENGTH_LONG).show()
            }else{
                val mCommondata=Commondata(""+getValue(et1_connection_no),""+et1_date.text.trim(),""+et1_endtime.text.toString(),
                    ""+getValue(et1_gis_plot),""+sp_investigator.selectedItem,""+getValue(et1_loc),""+getValue(et1_plotno),
                    ""+getValue(et1_prop_type),""+et1_starttime.text.trim(),""+getValue(et1_ward),""+getValue(et1_grid_no))

                mPage1_Common=mCommondata
                var chekQ5=""
                if (cb1_1.isChecked){
                    chekQ5=cb1_1.text.toString()
                }
                if (cb1_2.isChecked){
                    chekQ5=chekQ5+","+cb1_2.text.toString()
                }
                if (cb1_3.isChecked){
                    chekQ5=chekQ5+","+cb1_3.text.toString()
                }
                if (cb1_4.isChecked){
                    chekQ5=chekQ5+","+cb1_4.text.toString()
                }
                if (cb1_5.isChecked){
                    chekQ5=chekQ5+","+cb1_5.text.toString()
                }
                var string: String =""

                string = (rootViewProjectDocuments.findViewById(rg1_q6.getCheckedRadioButtonId()) as RadioButton).text.toString()
                Log.i("------>",""+string)

                val mSecdata=SectionData(""+getValue(et1_q1),"","","","","","","",
                    "","","",""+getValue(et1_q2),"","","","","","","","","","","",
                    "","","","","","","","","","",""+sp1_q3a.selectedItem,""+sp1_q3b.selectedItem,
                    ""+sp1_q3c.selectedItem,""+sp1_q3d.selectedItem,""+sp1_status.selectedItem
                    ,""+chekQ5,""+string,"","","")

                mPage2_Section=mSecdata

                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, Fragment2.newInstance())
                transaction?.disallowAddToBackStack()
                transaction?.commit()

            }
        }


        return rootViewProjectDocuments
    }

    private fun getValue(et1: AppCompatEditText):String {
        var value=""
        value=et1.text.toString().trim()
        return value
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel


        /*  supportFragmentManager.beginTransaction()
              .replace(R.id.container, MainFragment.newInstance())
              .commitNow()*/


        /*    launchDetailsFrag.findNavController().navigate(
                R.id.project_details,
                bundleOf(PROJECT_SELECTION_KEY to projectTitle)
            )*/

    }

    override fun onResume() {
        super.onResume()
        if(mPage1_Common!=null){

            // set values


        }

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
                    mPage1_Common=null
                    mPage2_Section=null
                    mPage3_Resident =null
                    mPage4_Commercial =null
                    mPage5_Apartments =null
                    mPage6_Institutional =null
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