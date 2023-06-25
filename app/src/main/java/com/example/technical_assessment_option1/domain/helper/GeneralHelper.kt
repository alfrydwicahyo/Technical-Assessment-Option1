package com.example.technical_assessment_option1.domain.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.technical_assessment_option1.R

class GeneralHelper {

    @SuppressLint("PrivateResource")
    @Suppress("DEPRECATION")
    companion object {
        fun setToolbar(activity: Activity, toolbar: Toolbar, text: String?) {
            val textView = toolbar.findViewById<TextView>(R.id.title)
            textView.text = text
            textView.isSelected = true
            toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
            toolbar.setNavigationOnClickListener { _: View? -> activity.onBackPressed() }
        }

        fun getString(context: Context, stringId: Int): String {
            return if (stringId > 0) context.resources
                .getString(stringId) else ""
        }
    }
}