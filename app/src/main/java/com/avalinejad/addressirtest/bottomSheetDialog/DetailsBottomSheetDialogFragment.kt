package com.avalinejad.addressirtest.bottomSheetDialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalinejad.addressirtest.R
import com.avalinejad.addressirtest.adapter.DetailsRecyclerViewAdapter
import com.avalinejad.addressirtest.data.model.Detail
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.details_bottom_sheet_dialog_fragment.*

class DetailsBottomSheetDialogFragment(
    val details: MutableList<Detail>
) : BottomSheetDialogFragment(){

    val adapter : DetailsRecyclerViewAdapter by lazy {
        DetailsRecyclerViewAdapter(
            requireContext()
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, R.layout.details_bottom_sheet_dialog_fragment,null)
        dialog.setContentView(view)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.items = details
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        Log.d("bottomsheet",details.toString())
    }

}