package com.example.prodoctorov.presentation.view

import android.text.Editable
import android.text.TextWatcher


class MaskWatcher(
    private val mask: String = "### ### ####",
    private val maskOnTextChanged: ((CharSequence, Int, Int, Int) -> Unit)? = null
) : TextWatcher {
    private var isRunning = false
    private var isDeleting = false
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        maskOnTextChanged?.let { it(charSequence, start, before, count) }
    }

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        if (mask.length < editable.length) {
            editable.delete(mask.length, editable.length)
            isRunning = false
            return
        }
        isRunning = true

        val editableLength: Int = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }

        isRunning = false
    }

//    companion object {
//        fun buildCpf(): MaskWatcher {
//            return MaskWatcher("### ### ####")
//        }
//    }
}

//1) номер начался не с 7 и не с 8 -> автомтаический вставить +7 и введенную цифру после него
//2) Номер начаося с 7 -> ничего не делаем
//3) Номер начался с 8 -> Заменяем на +7
