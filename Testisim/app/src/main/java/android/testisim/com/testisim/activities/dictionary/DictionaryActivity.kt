package android.testisim.com.testisim.activities.dictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.testisim.com.testisim.R
import kotlinx.android.synthetic.main.activity_dictionary.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase

class DictionaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        dictionaryGoBackButton.setOnClickListener {
            onBackPressed()
        }

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Testisim").child("Sozluk")

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue()

                value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
