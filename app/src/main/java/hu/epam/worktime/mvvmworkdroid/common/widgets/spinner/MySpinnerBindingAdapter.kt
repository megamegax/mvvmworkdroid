package hu.epam.worktime.mvvmworkdroid.common.widgets.spinner

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.support.v7.widget.AppCompatSpinner
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter

/**
 * Created by Mihaly_Hunyady on 2017. 01. 06..
 */

object MySpinnerBindingAdapter {

    @BindingAdapter(value = *arrayOf("bind:selectedValue", "bind:selectedValueAttrChanged"), requireAll = false)
    fun bindSpinnerData(pAppCompatSpinner: AppCompatSpinner, newSelectedValue: String?, newTextAttrChanged: InverseBindingListener) {
        pAppCompatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                newTextAttrChanged.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        if (newSelectedValue != null) {
            val pos = (pAppCompatSpinner.adapter as ArrayAdapter<String>).getPosition(newSelectedValue)
            pAppCompatSpinner.setSelection(pos, true)
        }
    }

    @InverseBindingAdapter(attribute = "bind:selectedValue", event = "bind:selectedValueAttrChanged")
    fun captureSelectedValue(pAppCompatSpinner: AppCompatSpinner): String {
        return pAppCompatSpinner.selectedItem as String
    }

}