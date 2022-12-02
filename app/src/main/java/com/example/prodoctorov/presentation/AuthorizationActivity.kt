package com.example.prodoctorov.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.prodoctorov.presentation.authorization.enter_phone.EnterPhoneFragment
import com.example.prodoctorov.R
import com.example.prodoctorov.presentation.authorization.check_password.CheckPasswordFragment
import com.example.prodoctorov.presentation.authorization.confirm_code.ConfirmCodeFragment
import com.example.prodoctorov.presentation.authorization.new_password.CreateNewPasswordFragment
import com.example.prodoctorov.presentation.authorization.new_password.CreateNewPasswordScreen
import com.example.prodoctorov.presentation.main_app.user.SearchSpecialistFragment
import com.example.prodoctorov.presentation.main_app.user.advanced_search.AdvancedSearchFragment

class AuthorizationActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, AdvancedSearchFragment.newInstance())
            .commit()
    }
}