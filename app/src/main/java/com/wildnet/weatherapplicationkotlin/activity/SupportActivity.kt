package com.wildnet.weatherapplicationkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.wildnet.weatherapplicationkotlin.MainClass
import com.wildnet.weatherapplicationkotlin.R
import com.wildnet.weatherapplicationkotlin.model.SupportModel
import kotlinx.android.synthetic.main.activity_support.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class SupportActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        supportActionBar!!.hide()
        btn_submitData.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var name: String = et_name.text.toString()
        var email: String = et_email.text.toString()
        var message: String = et_message.text.toString()


            if (!name.equals("", ignoreCase = true) &&
                    !email.equals("", ignoreCase = true) && !message.equals("", ignoreCase = true)) {
                val supportModel = SupportModel(name, email, message)
                MainClass.getInstance(this).supportData(supportModel)
                var intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please fill all field", Toast.LENGTH_SHORT).show()
            }

    }

    fun isValidEmail(email: String): Boolean {
        var check: Boolean = false
        var email: String = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"+
                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +
                "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"

        var pattern = Pattern.compile(email)

        var match: Matcher = pattern.matcher(email)
        var flag = match.matches()
        if (flag) {
            check = true
        }

      return check
    }
}
