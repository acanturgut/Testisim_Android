package android.testisim.com.testisim.activities.dictionary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.testisim.com.testisim.R
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_dictionary.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase

class DictionaryActivity : AppCompatActivity() {

    val list = arrayListOf<DictionaryEntity>()
    private val filteredList =  arrayListOf<DictionaryEntity>()

    lateinit var adapter: DictionaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        dictionaryGoBackButton.setOnClickListener {
            onBackPressed()
        }

        // Get a reference to our posts
        val database = FirebaseDatabase.getInstance()
        val ref = database.reference

        dictionaryRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = DictionaryAdapter()
        dictionaryRecyclerView.adapter = adapter

        ref.child("Sozluk").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.children.forEach {
                    System.out.println(it)
                    list.add(DictionaryEntity(it.child("Ack").value.toString(), it.child("Kelime").value.toString()))
                }
                adapter.updateList(list)
                dictionarySearchView.visibility = View.VISIBLE
                dictionaryProgressBar.visibility = View.GONE
                dictionarySearchView.isFocusable = true
                dictionarySearchView.setQuery("", true)
                dictionarySearchView.isIconified = false
                dictionarySearchView.requestFocusFromTouch()
                dictionarySearchView.requestFocus()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
            }
        })

        dictionarySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                filterResults(query)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                filterResults(newText)

                return false
            }
        })
    }

    private fun filterResults(query: String) {
        if (TextUtils.isEmpty(query)) {
            adapter.updateList(list)
            return
        }
        if (list.isEmpty())  {
            adapter.updateList(list)
            return
        }

        filteredList.clear()

        list.forEach {
            if(it.word.toUpperCase().contains(query.toUpperCase())) {
                filteredList.add(it)
            }
        }

        adapter.updateList(filteredList)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }
}
