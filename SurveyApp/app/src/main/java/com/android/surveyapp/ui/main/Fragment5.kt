package com.android.surveyapp.ui.main

import android.R.attr.bitmap
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.surveyapp.R
import com.android.surveyapp.SurveyApp.Companion.mPage1_Common
import com.android.surveyapp.SurveyApp.Companion.mPage2_Section
import com.android.surveyapp.SurveyApp.Companion.mPage3_Resident
import com.android.surveyapp.SurveyApp.Companion.mPage4_Commercial
import com.android.surveyapp.SurveyApp.Companion.mPage5_Apartments
import com.android.surveyapp.SurveyApp.Companion.mPage6_Institutional
import com.android.surveyapp.ui.api.AtlasDBInterface
import com.android.surveyapp.ui.datamodel.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class Fragment5 : Fragment() {
    lateinit var rootView: View
    var imageView1: ImageView? =null
    var imageData=""
    companion object {
        fun newInstance() = Fragment5()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment5, container, false)

        val b: Button =rootView.findViewById(R.id.button_next2)
        val buttonPic: Button =rootView.findViewById(R.id.buttonPic)
        imageData=""
         imageView1 =rootView.findViewById(R.id.imageView1)

        val et5_capacity: AppCompatEditText =rootView.findViewById(R.id.et5_capacity)
        val et5_connqlty: AppCompatEditText =rootView.findViewById(R.id.et5_connqlty)
        val et5_hospitalbeds: AppCompatEditText =rootView.findViewById(R.id.et5_hospitalbeds)
        val et5_hotelchairs: AppCompatEditText =rootView.findViewById(R.id.et5_hotelchairs)
        val et5_monthlybill: AppCompatEditText =rootView.findViewById(R.id.et5_monthlybill)
        val et5_officemployee: AppCompatEditText =rootView.findViewById(R.id.et5_officemployee)
        val et5_connectiontype: AppCompatEditText =rootView.findViewById(R.id.et5_conntype)
        val et5_pipsz: AppCompatEditText =rootView.findViewById(R.id.et5_pipsz)
        val et5_remark: AppCompatEditText =rootView.findViewById(R.id.et5_remark)
        val et5_sourcesupply: AppCompatEditText =rootView.findViewById(R.id.et5_sourcesupply)
        val et5_studcapacity: AppCompatEditText =rootView.findViewById(R.id.et5_studcapacity)
        val et5_watersupplyhr: AppCompatEditText =rootView.findViewById(R.id.et5_watersupplyhr)

        val sp5_bottledwater: Spinner =rootView.findViewById(R.id.sp5_bottledwater)
        val sp5_connectiontypelegel: Spinner =rootView.findViewById(R.id.sp5_conntypelegal)
        val sp5_roplant: Spinner =rootView.findViewById(R.id.sp5_roplant)
        val sp5_waterconnection: Spinner =rootView.findViewById(R.id.sp5_waterconnection)

        val et5_taps: AppCompatEditText =rootView.findViewById(R.id.et5_taps)

        var status1 = arrayOf("Legel", "Illegal")
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var status = arrayOf("Yes", "No")
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

//        sp5_taps.adapter = adapter2
        sp5_bottledwater.adapter = adapter2
        sp5_connectiontypelegel.adapter = adapter1
        sp5_roplant.adapter = adapter2
        sp5_waterconnection.adapter = adapter2

        buttonPic.setOnClickListener {

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, 1)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }

        b.setOnClickListener {

            val mInstitutionalData: Institutional =
                Institutional("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
            mPage6_Institutional = mInstitutionalData

            mPage6_Institutional?.I_BillAmount = "" + getValue(et5_monthlybill)
            mPage6_Institutional?.I_BottledWater = "" + sp5_bottledwater.selectedItem
            mPage6_Institutional?.I_ConnectionQuality = "" + getValue(et5_connqlty)
            mPage6_Institutional?.I_ConnectionType = "" + getValue(et5_connectiontype)
            mPage6_Institutional?.I_ConnectionTypeLegal = "" + sp5_connectiontypelegel.selectedItem
            mPage6_Institutional?.I_NoOfTaps = "" + getValue(et5_taps)
            mPage6_Institutional?.I_NumberofChairs = "" + getValue(et5_hotelchairs)
            mPage6_Institutional?.I_PipeSize = "" + getValue(et5_pipsz)
            mPage6_Institutional?.I_Remarks = "" + getValue(et5_remark)
            mPage6_Institutional?.I_ROplant = "" + sp5_roplant.selectedItem
            mPage6_Institutional?.I_SourceWaterSupply = "" + getValue(et5_sourcesupply)
            mPage6_Institutional?.I_StorageFacility = "" + getValue(et5_capacity)
            mPage6_Institutional?.I_StudentCapacity = "" + getValue(et5_studcapacity)
            mPage6_Institutional?.I_TotalBeds = "" + getValue(et5_hospitalbeds)
            mPage6_Institutional?.I_TotalEmployees = "" + getValue(et5_officemployee)
            mPage6_Institutional?.I_WaterConnection = "" + sp5_waterconnection.selectedItem
            mPage6_Institutional?.I_WaterSupplyHours = "" + getValue(et5_watersupplyhr)

            Log.i("jobj--23->>", "" + mPage6_Institutional?.I_Remarks)
            var mCommon: Commondata? = mPage1_Common
            var mSection: SectionData? = mPage2_Section
            var mResident: Residential? = mPage3_Resident
            var mCommercial: Commercial? = mPage4_Commercial
            var mApartments: Apartments? = mPage5_Apartments
            var mInstitutional: Institutional? = mPage6_Institutional


            val sformAll:SaveFormDataAll=SaveFormDataAll(""+mPage1_Common?.ConnectionNo,""+mPage1_Common?.EndingTime,""+mPage1_Common?.GISPlotID,""+mPage1_Common?.Investigator,""+mPage1_Common?.Location,""+mPage1_Common?.PlotNo,""+mPage1_Common?.PropertyType,""+mPage1_Common?.StartingTime,""+ mPage1_Common?.WARDNO,""+mPage1_Common?.dt,""+mPage5_Apartments?.A_Billamount,""+mPage5_Apartments?.A_BottledWater,""+mPage5_Apartments?.A_ConnectionType,""+mPage5_Apartments?.A_DistancefromMainPipe,""+mPage5_Apartments?.A_FlatID,""+mPage5_Apartments?.A_PipeSize,""+mPage5_Apartments?.A_ROplant,""+mPage5_Apartments?.A_Remarks,""+mPage5_Apartments?.A_StorageFacilityCapacity,""+mPage5_Apartments?.A_TankerSupply,""+mPage5_Apartments?.A_Type,""+mPage5_Apartments?.A_WaterConnection,
                ""+mPage4_Commercial?.C_ConnectsTo,""+mPage4_Commercial?.C_GISPlotID,""+mPage4_Commercial?.C_LastbillReceipt,""+mPage4_Commercial?.C_Meter,""+mPage4_Commercial?.C_MeterMake,""+mPage4_Commercial?.C_MeterNo,""+mPage4_Commercial?.C_MeterOn,""+mPage4_Commercial?.C_NoEmployees,""+mPage4_Commercial?.C_OutstandingBillAmt,""+mPage4_Commercial?.C_PeakDemandHrs,""+mPage4_Commercial?.C_Remarks,""+mPage4_Commercial?.C_Toilet,""+mPage4_Commercial?.C_TotalBeds,""+mPage4_Commercial?.C_TypeCommercial,""+mPage4_Commercial?.C_UGDconnection,""+mPage4_Commercial?.C_WaterPressure,""+mPage4_Commercial?.C_WaterQuality,""+mPage4_Commercial?.C_WaterQuantity,""+mPage4_Commercial?.C_WaterUse,
                ""+mPage6_Institutional?.I_BillAmount,""+mPage6_Institutional?.I_BottledWater,""+mPage6_Institutional?.I_ConnectionQuality,""+mPage6_Institutional?.I_ConnectionType,""+mPage6_Institutional?.I_ConnectionTypeLegal,""+mPage6_Institutional?.I_NoOfTaps,""+mPage6_Institutional?.I_NumberofChairs,""+mPage6_Institutional?.I_PipeSize,""+mPage6_Institutional?.I_ROplant,""+mPage6_Institutional?.I_Remarks,""+mPage6_Institutional?.I_SourceWaterSupply,""+mPage6_Institutional?.I_StorageFacility,""+mPage6_Institutional?.I_StudentCapacity,""+mPage6_Institutional?.I_TotalBeds,""+mPage6_Institutional?.I_TotalEmployees,""+mPage6_Institutional?.I_WaterConnection,""+mPage6_Institutional?.I_WaterSupplyHours,
                ""+mPage3_Resident?.R_AnnualIncome,""+mPage3_Resident?.R_ConnectionNo,""+mPage3_Resident?.R_ContactNo,""+mPage3_Resident?.R_Education,""+mPage3_Resident?.R_Email,""+mPage3_Resident?.R_FamilySize,""+mPage3_Resident?.R_FirstName,""+mPage3_Resident?.R_GISPlotID,""+mPage3_Resident?.R_GISPlotIDSorted,""+mPage3_Resident?.R_GISPropertyID,""+mPage3_Resident?.R_GridNo,""+mPage3_Resident?.R_LastName,""+mPage3_Resident?.R_Location,""+mPage3_Resident?.R_MiddleName,""+mPage3_Resident?.R_Occupation,""+mPage3_Resident?.R_PlotNo,""+mPage3_Resident?.R_PropertyCategory,""+mPage3_Resident?.R_PropertyName,""+mPage3_Resident?.R_PropertyType,""+mPage3_Resident?.R_Remarks,""+mPage3_Resident?.R_Toilet,""+mPage3_Resident?.R_UGDconnection,""+mPage3_Resident?.R_WardName,""+mPage3_Resident?.R_WardNo,""+mPage3_Resident?.R_WaterSource,""+mPage3_Resident?.R_connectsto,
                ""+mPage2_Section?.q1,""+mPage2_Section?.q10,""+mPage2_Section?.q11,""+mPage2_Section?.q12,""+mPage2_Section?.q13,""+mPage2_Section?.q14,""+mPage2_Section?.q15,""+mPage2_Section?.q16,""+mPage2_Section?.q17,""+mPage2_Section?.q18,""+mPage2_Section?.q19,""+mPage2_Section?.q2,""+mPage2_Section?.q20,""+mPage2_Section?.q21,""+mPage2_Section?.q22,""+mPage2_Section?.q23,""+mPage2_Section?.q24,""+mPage2_Section?.q25,""+mPage2_Section?.q26,""+mPage2_Section?.q27,""+mPage2_Section?.q28,""+mPage2_Section?.q29,"",""+mPage2_Section?.q30,""+mPage2_Section?.q31,""+mPage2_Section?.q32,""+mPage2_Section?.q33,""+mPage2_Section?.q34,""+mPage2_Section?.q35,
                ""+mPage2_Section?.q35a,""+mPage2_Section?.q36,""+mPage2_Section?.q37,""+mPage2_Section?.q37a,""+mPage2_Section?.q38,""+mPage2_Section?.q3a,""+mPage2_Section?.q3b,""+mPage2_Section?.q3c,""+mPage2_Section?.q3d,""+mPage2_Section?.q4,""+mPage2_Section?.q5,""+mPage2_Section?.q6,""+mPage2_Section?.q7,""+mPage2_Section?.q8,""+mPage2_Section?.q9,
            ""+imageData,""+mPage1_Common?.grid_no)
            Log.i("jobj--->>", "" + sformAll)
            /*  val jobj: SurveyDataForm = SurveyDataForm(
                  mApartments!!, mCommercial!!,
                  mCommon!!, mInstitutional!!, mResident!!, mSection!!
              )
              Log.i("jobj--->>", "" + jobj)*/

            val progressDialog = ProgressDialog(requireContext())
//            progressDialog.setTitle("Kotlin Progress Bar")
            progressDialog.setMessage("please wait...")
            progressDialog.show()

            // build finel object
            // call api

            val apiInterface = AtlasDBInterface.create().saveForm(sformAll)//( jobj )
            apiInterface.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    progressDialog.dismiss()
                    val responseObj = response.body()!!
                    Log.i("############## --->", " "+responseObj)

                    Toast.makeText(requireContext(),"Form Submitted",Toast.LENGTH_LONG).show()
                    activity?.finish()

                    mPage1_Common=null
                    mPage2_Section=null
                    mPage3_Resident=null
                    mPage4_Commercial=null
                    mPage5_Apartments=null
                    mPage6_Institutional=null

                }

                override fun onFailure(
                    call: Call<ResponseBody>,
                    t: Throwable
                ) {
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_LONG).show()
                    Log.i("ERROR-------->", " " + t.message)
                }
            })



            // redirect to home  and clear cache




        }

        val back: Button =rootView.findViewById(R.id.button_back2)
        back.setOnClickListener {





            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, Fragment4.newInstance())
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



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            val byteArrayOutputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            val encoded: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            imageData=""+encoded
//            imageData="data:image/png;base64,"+encoded
            imageView1?.setImageBitmap(imageBitmap)

        }
    }
}