package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_portal.*
const val EXTRA_PORTAL = "EXTRA_PORTAL"
class addPortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_portal)
        initViews()
    }

    private fun initViews(){
        btnAddPortal.setOnClickListener{onSaveClick()}
    }

    private fun onSaveClick(){
        if(teTitle.text.toString().isNotBlank() && teUrl.text.toString().isNotBlank()){
            val portal = Portal(teUrl.text.toString(),teTitle.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL,portal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }else{
            Toast.makeText(this, "The reminder cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}
