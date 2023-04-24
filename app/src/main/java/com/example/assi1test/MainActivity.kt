package com.example.assi1test


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.GONE
        show.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)


        }
        save.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            var name = YourName.text.toString()
            var number = YourNumber.text.toString()
            var address = YourAddress.text.toString()


            if(name.isEmpty() || number.isEmpty() || address.isEmpty() ){
                progressBar.visibility = View.GONE
                Toast.makeText(this, "please add data in field", Toast.LENGTH_SHORT).show()


            }else{

                val person = hashMapOf(
                    "name" to "$name",
                    "number" to "$number",
                    "address" to "$address"

                )
                db.collection("Ms").add(person).addOnSuccessListener {e ->
                    progressBar.visibility = View.GONE

                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show()

                    this.YourName.text.clear()
                    this.YourNumber.text.clear()
                    this.YourAddress.text.clear()

                }.addOnFailureListener {
                        e->
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()

                }
            }

        }


    }
}
