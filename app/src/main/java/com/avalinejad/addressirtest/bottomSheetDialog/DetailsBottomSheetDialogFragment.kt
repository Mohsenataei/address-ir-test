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
import kotlinx.android.synthetic.main.details_bottom_sheet_dialog_fragment.view.*
import kotlinx.android.synthetic.main.recycler_row_item.*
import kotlinx.android.synthetic.main.recycler_row_item.view.*
import kotlinx.android.synthetic.main.recycler_row_item.view.image

class DetailsBottomSheetDialogFragment(
    val details: MutableList<Detail>,
    val title: String,
    val resId: Int
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
        initView(view)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        image.background = requireContext().getDrawable(resId)
        image.setBackgroundResource(resId)
        Log.d("bottomsheet",details.toString())
    }

    private fun initView(view: View){
        adapter.items = details
        view.place_title.text = title
//        view.image.background = requireContext().getDrawable(resId)
        view.places_count.text = details.size.toString() + title + "پیدا شد"
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = adapter
    }

}