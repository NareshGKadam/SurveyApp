package com.android.surveyapp.ui.main

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import com.android.surveyapp.R
import com.android.surveyapp.SurveyApp
import com.android.surveyapp.SurveyApp.Companion.mPage4_Commercial
import com.android.surveyapp.SurveyApp.Companion.mPage5_Apartments
import com.android.surveyapp.ui.datamodel.Apartments
import com.android.surveyapp.ui.datamodel.Commercial
import com.android.surveyapp.ui.datamodel.Residential

class Fragment4 : Fragment() {
    lateinit var rootView: View
    companion object {
        fun newInstance() = Fragment4()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment4, container, false)

        val b: Button =rootView.findViewById(R.id.button_next2)

        val et4_activitytype: AppCompatEditText =rootView.findViewById(R.id.et4_activitytype)
        val et4_capacity: AppCompatEditText =rootView.findViewById(R.id.et4_capacity)
        val et4_connectsto: AppCompatEditText =rootView.findViewById(R.id.et4_connectsto)
        val et4_distfrompip: AppCompatEditText =rootView.findViewById(R.id.et4_distfrompip)
        val et4_flat: AppCompatEditText =rootView.findViewById(R.id.et4_flat)
        val et4_gisplotid: AppCompatEditText =rootView.findViewById(R.id.et4_gisplotid)
        val et4_hotelbed: AppCompatEditText =rootView.findViewById(R.id.et4_hotelbed)
        val et4_lastbill: AppCompatEditText =rootView.findViewById(R.id.et4_lastbill)
        val et4_metermake: AppCompatEditText =rootView.findViewById(R.id.et4_metermake)
        val et4_meterno: AppCompatEditText =rootView.findViewById(R.id.et4_meterno)
        val et4_monthlybill: AppCompatEditText =rootView.findViewById(R.id.et4_monthlybill)
        val et4_outstanding: AppCompatEditText =rootView.findViewById(R.id.et4_outstanding)
        val et4_paekdemamd: AppCompatEditText =rootView.findViewById(R.id.et4_paekdemamd)
        val et4_pipesz: AppCompatEditText =rootView.findViewById(R.id.et4_pipesz)
        val et4_remark: AppCompatEditText =rootView.findViewById(R.id.et4_remark)
        val et4_remark2: AppCompatEditText =rootView.findViewById(R.id.et4_remark2)
        val et4_shopnoemp: AppCompatEditText =rootView.findViewById(R.id.et4_shopnoemp)
        val et4_tanker: AppCompatEditText =rootView.findViewById(R.id.et4_tanker)
        val et4_waterpressure: AppCompatEditText =rootView.findViewById(R.id.et4_waterpressure)
        val et4_waterqlty: AppCompatEditText =rootView.findViewById(R.id.et4_waterqlty)
        val et4_waterqtt: AppCompatEditText =rootView.findViewById(R.id.et4_waterqtt)
        val et4_wateruse: AppCompatEditText =rootView.findViewById(R.id.et4_wateruse)

        val sp4_meteron: Spinner =rootView.findViewById(R.id.sp4_meteron)
        val sp4_meteryes: Spinner =rootView.findViewById(R.id.sp4_meteryes)
        val sp4_toilet: Spinner =rootView.findViewById(R.id.sp4_toilet)
        val sp4_ugd: Spinner =rootView.findViewById(R.id.sp4_ugd)
        val sp4_type: Spinner =rootView.findViewById(R.id.sp4_type)

        val sp4_bottled: Spinner =rootView.findViewById(R.id.sp4_bottled)
        val sp4_conntype: Spinner =rootView.findViewById(R.id.sp4_conntype)
        val sp4_roplant: Spinner =rootView.findViewById(R.id.sp4_roplant)
        val sp4_waterconnection: Spinner =rootView.findViewById(R.id.sp4_waterconnection)

        var status1 = arrayOf("Apartment", "Society","Gated Community")  //Apartment / Society /Gated
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var status = arrayOf("Yes", "No")
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var status3 = arrayOf("Legel", "Illegal")
        val adapter3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status3)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var status4 = arrayOf("On", "Off")
        val adapter4 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status4)
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        sp4_type.adapter = adapter1

        sp4_meteron.adapter = adapter4
        sp4_meteryes.adapter = adapter2
        sp4_toilet.adapter = adapter2
        sp4_ugd.adapter = adapter2

        sp4_bottled.adapter = adapter2
        sp4_conntype.adapter = adapter3
        sp4_roplant.adapter = adapter2
        sp4_waterconnection.adapter = adapter2




        b.setOnClickListener {

            val mCommercialData: Commercial = Commercial("","","","","","","","","","","","","","","","","","","")
            mPage4_Commercial =mCommercialData

            mPage4_Commercial?.C_TypeCommercial =""+getValue(et4_activitytype)
            mPage4_Commercial?.C_ConnectsTo =""+getValue(et4_connectsto)
            mPage4_Commercial?.C_GISPlotID =""+getValue(et4_gisplotid)
            mPage4_Commercial?.C_LastbillReceipt =""+getValue(et4_lastbill)
            mPage4_Commercial?.C_Meter =""+sp4_meteryes.selectedItem
            mPage4_Commercial?.C_MeterMake =""+getValue(et4_metermake)
            mPage4_Commercial?.C_MeterNo =""+getValue(et4_meterno)
            mPage4_Commercial?.C_MeterOn =""+sp4_meteron.selectedItem
            mPage4_Commercial?.C_NoEmployees =""+getValue(et4_shopnoemp)
            mPage4_Commercial?.C_OutstandingBillAmt =""+getValue(et4_outstanding)
            mPage4_Commercial?.C_PeakDemandHrs =""+getValue(et4_paekdemamd)
            mPage4_Commercial?.C_Remarks =""+getValue(et4_remark)
            mPage4_Commercial?.C_Toilet =""+sp4_toilet.selectedItem
            mPage4_Commercial?.C_TotalBeds =""+getValue(et4_hotelbed)
            mPage4_Commercial?.C_UGDconnection =""+sp4_ugd.selectedItem
            mPage4_Commercial?.C_WaterPressure =""+getValue(et4_waterpressure)
            mPage4_Commercial?.C_WaterQuality =""+getValue(et4_waterqlty)
            mPage4_Commercial?.C_WaterQuantity =""+getValue(et4_waterqtt)
            mPage4_Commercial?.C_WaterUse =""+getValue(et4_wateruse)

            val mApartmentsData: Apartments = Apartments("","","","","","","","","","","","")
            mPage5_Apartments =mApartmentsData

            mPage5_Apartments?.A_BottledWater =""+sp4_bottled.selectedItem
            mPage5_Apartments?.A_StorageFacilityCapacity=""+getValue(et4_capacity)
            mPage5_Apartments?.A_ConnectionType=""+sp4_conntype.selectedItem
            mPage5_Apartments?.A_DistancefromMainPipe =""+getValue(et4_distfrompip)
            mPage5_Apartments?.A_FlatID =""+getValue(et4_flat)
            mPage5_Apartments?.A_PipeSize =""+getValue(et4_pipesz)
            mPage5_Apartments?.A_Remarks =""+getValue(et4_remark2)
            mPage5_Apartments?.A_ROplant =""+sp4_roplant.selectedItem

            mPage5_Apartments?.A_Type =""+sp4_type.selectedItem
            mPage5_Apartments?.A_WaterConnection =""+sp4_waterconnection.selectedItem
            mPage5_Apartments?.A_Billamount =""+getValue(et4_monthlybill)
            mPage5_Apartments?.A_TankerSupply =""+getValue(et4_tanker)


            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment5.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

        }

        val back: Button =rootView.findViewById(R.id.button_back2)
        back.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment3.newInstance())
            transaction?.disallowAddToBackStack()
            transaction?.commit()

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
                    SurveyApp.mPage2_Section =null
                    SurveyApp.mPage3_Resident =null
                    mPage4_Commercial =null
                    mPage5_Apartments =null
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