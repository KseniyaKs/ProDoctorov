package com.example.prodoctorov.presentation.authorization.check_password

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.articlestest.extension.visible
import com.example.prodoctorov.R
import com.example.prodoctorov.databinding.FragmentCheckPasswordBinding


class CheckPasswordFragment : Fragment(R.layout.fragment_check_password) {

    companion object {
        fun newInstance() = CheckPasswordFragment()
    }

    private val viewBinding: FragmentCheckPasswordBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        var passwordVisibility = PasswordVisibility.GONE

        with(viewBinding) {
            passwordEdtTxt.doOnTextChanged { text, start, before, count ->
                passwordVisibleImg.isVisible = !text.toString().isNullOrEmpty()
                continueBtn.isEnabled = !text.toString().isNullOrEmpty()
                continueBtn.alpha = if (continueBtn.isEnabled) 1F else 0.5F
            }

            continueBtn.alpha = if (continueBtn.isEnabled) 1F else 0.5F
            continueBtn.setOnClickListener {
                //                passwordEdtTxt.setBackgroundResource(R.drawable.edt_text_red_border)// если неправильный пароль
            }

            passwordVisibleImg.setOnClickListener {
                if (passwordVisibility == PasswordVisibility.GONE) {
                    passwordEdtTxt.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    passwordVisibleImg.setImageResource(R.drawable.ic_closed_eye)

                    passwordVisibility = PasswordVisibility.VISIBLE
                } else {
                    passwordEdtTxt.transformationMethod = PasswordTransformationMethod.getInstance()
                    passwordVisibleImg.setImageResource(R.drawable.ic_arrow_to_down)

                    passwordVisibility = PasswordVisibility.GONE
                }
            }

            restorePasswordTxt.setOnClickListener { }
            back.setOnClickListener {  }

        }
    }
}

enum class PasswordVisibility {
    VISIBLE, GONE
}