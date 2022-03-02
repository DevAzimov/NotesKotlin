package com.magicapp.noteskotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NO_TITLE
import com.magicapp.noteskotlin.R

class DialogFragment: DialogFragment() {

    var saveClick: ((String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.customDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_alert_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etText: EditText = view.findViewById(R.id.edt_dialog)
        val btnSave: Button = view.findViewById(R.id.btn_save)
        val btnCancel: Button = view.findViewById(R.id.btn_cancel)

        btnSave.setOnClickListener {
            saveClick!!.invoke(etText.text.toString())
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

}