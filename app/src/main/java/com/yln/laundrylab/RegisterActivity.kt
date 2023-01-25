package com.yln.laundrylab

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    private var editNama: EditText? = null
    private var editEmail: EditText? = null
    private var editPassword: EditText? = null
    private var editKonfirmasiPassword: EditText? = null
    private var btnRegister: Button? = null
    private var btnLogin: TextView? = null
    private var mAuth: FirebaseAuth? = null
    private var progressDialog: ProgressDialog? = null
    //notif
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanState: Bundle?) {
        super.onCreate(savedInstanState)
        setContentView(R.layout.register)

        //notif
        regisNotif()

//        val pendingIntent = TaskStackBuilder.create(this).run{
//            addNextIntentWithParentStack(intent)
//        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("BLaundryLab Notification")
            .setContentText("Successfully Registered")
            .setSmallIcon(R.drawable.ic_notif)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()


        editNama = findViewById<EditText>(R.id.editTextTextName)
        editEmail = findViewById(R.id.editTextTextEmailAddress)
        editPassword = findViewById(R.id.editTextTextPassword)
        editKonfirmasiPassword = findViewById(R.id.editTextKonfirmPassword)
        btnLogin = findViewById(R.id.buttonRegisterLogin)
        btnRegister = findViewById<Button>(R.id.buttonRegister)
        mAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this@RegisterActivity)
        progressDialog!!.setTitle("Loading")
        progressDialog!!.setMessage("Silahkan tunggu")
        progressDialog!!.setCancelable(false)

        this.btnRegister?.setOnClickListener(View.OnClickListener { v: View? ->
            Log.d("LaundryLab", "Attempting Register")
            if (editNama?.getText()?.length!! > 0 &&
                editEmail?.getText()?.length!! > 0 &&
                editPassword?.getText()?.length!! > 0 &&
                editKonfirmasiPassword?.getText()?.length!! > 0
            ) {
                if (editPassword?.getText().toString() == editKonfirmasiPassword?.getText()
                        .toString()
                ) {
                    register(
                        editNama?.getText().toString(),
                        editEmail?.getText().toString(),
                        editPassword?.getText().toString()
                    )
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password tidak sama",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data",
                    Toast.LENGTH_SHORT
                ).show()
            }

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(NOTIFICATION_ID, notification)

        })
        btnLogin?.setOnClickListener(View.OnClickListener { v: View? -> finish() })




    }

    private fun register(nama: String, email: String, password: String) {
        progressDialog!!.show()
        mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                val firebaseUser = task.result!!.user
                if (firebaseUser != null) {
                    val request = UserProfileChangeRequest.Builder()
                        .setDisplayName(nama)
                        .build()
                    firebaseUser.updateProfile(request).addOnCompleteListener { reload() }
                } else {
                    Toast.makeText(applicationContext, "Register gagal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    task.exception!!.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
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
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
        }
    }




}