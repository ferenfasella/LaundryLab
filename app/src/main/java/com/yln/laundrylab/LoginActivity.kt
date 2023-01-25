package com.yln.laundrylab

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var editEmail: EditText? = null
    private var editPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnRegister: TextView? = null
    private var mAuth: FirebaseAuth? = null
    private var progressDialog: ProgressDialog? = null


    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanState: Bundle?) {
        super.onCreate(savedInstanState)
        setContentView(R.layout.login)

        editEmail = findViewById(R.id.editTextEmailMasuk)
        editPassword = findViewById(R.id.editTextKonfirmPassword)
        btnLogin = findViewById(R.id.buttonMasukLogin)
        btnRegister = findViewById(R.id.buttonRegisterLogin)
        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this@LoginActivity)
        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Silahkan tunggu")
        progressDialog!!.setCancelable(false)

        btnRegister?.setOnClickListener(View.OnClickListener { v: View? ->
            startActivity(
                Intent(
                    applicationContext,
                    RegisterActivity::class.java
                )
            )
        })

        regisNotif()



        btnLogin?.setOnClickListener(View.OnClickListener { v: View? ->
            Log.d("LaundryLab", "Attempting Login")


            if (editEmail?.getText()!!.length > 0 &&
                editPassword?.getText()!!.length > 0) {
                login(editEmail?.getText().toString(), editPassword?.getText().toString())

            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi email dan password",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun login(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                if (task.result!!.user != null) {
                    reload()
                } else {
                    Toast.makeText(applicationContext, "Login gagal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Login gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun regisNotif(){
        val code = Build.VERSION_CODES.O;
        if(Build.VERSION.SDK_INT >= code){
            val channel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    private fun reload() {
        startActivity(Intent(applicationContext, NavbarActivity::class.java))

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("LaundryLab Notification")
            .setContentText("Successfully Logged In")
            .setSmallIcon(R.drawable.ic_notif)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(NOTIFICATION_ID, notification)

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
        }
    }
}