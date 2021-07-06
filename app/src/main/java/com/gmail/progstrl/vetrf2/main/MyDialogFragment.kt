package com.gmail.progstrl.vetrf2.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class MyDialogFragment(
    // private final DocOrdersFragmentMain docOrdersFragmentMain;
    private val dialogFragmentListener: DialogFragmentListener?,
    private val message: String,
    private val typeButtons: String,
    private val flagQuestion: Int
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder =
            AlertDialog.Builder(activity)
        builder.setMessage(message)
        if (typeButtons === YESNOCANCEL || typeButtons === YESNO) {
            builder.setPositiveButton("Да") { dialog, which ->
                dialogFragmentListener?.dialogFragmentOnClickPositive(
                    flagQuestion
                )
            }
        }
        if (typeButtons === YESNOCANCEL || typeButtons === YESNO) {
            builder.setNegativeButton("Нет") { dialog, which ->
                dialogFragmentListener?.dialogFragmentOnClickNegative(
                    flagQuestion
                )
            }
        }
        if (typeButtons === YESNOCANCEL) {
            builder.setNeutralButton("Отмена") { dialog, which -> }
        }
        return builder.create()
    }

    interface DialogFragmentListener {
        fun dialogFragmentOnClickPositive(flag: Int)
        fun dialogFragmentOnClickNegative(flag: Int)
    }

    companion object {
        const val YESNO = "YESNO"
        const val YESNOCANCEL = "YESNOCANCEL"
    }

}