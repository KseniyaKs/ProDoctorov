package com.example.prodoctorov.presentation.authorization.enter_phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.prodoctorov.R
import com.example.prodoctorov.databinding.FragmentEnterPhoneBinding
import com.example.prodoctorov.presentation.view.MaskWatcher

class EnterPhoneFragment : Fragment(R.layout.fragment_enter_phone) {

    companion object {
        fun newInstance() = EnterPhoneFragment()
    }

    private val viewBinding: FragmentEnterPhoneBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(viewBinding) {
            agreementPolicy.text =
                "Нажимая кнопку “продолжить” вы соглашаетесь с пользовательским соглашением и политикой конфиденцальности"

            continueBtn.alpha = if (continueBtn.isEnabled) 1F else 0.5F
            continueBtn.setOnClickListener { }

            phoneLayout.phoneEdtTxt.addTextChangedListener(MaskWatcher { text, start, before, count ->
                phoneLayout.cancel.isVisible = !text.toString().isNullOrEmpty()
                continueBtn.isEnabled = !text.toString().isNullOrEmpty()
                continueBtn.alpha = if (continueBtn.isEnabled) 1F else 0.5F
            })

            phoneLayout.cancel.setOnClickListener {
                phoneLayout.phoneEdtTxt.setText("")
            }

        }
    }
}