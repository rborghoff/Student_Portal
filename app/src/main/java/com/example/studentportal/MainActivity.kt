package com.example.studentportal


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


const val ADD_PORTAL_REQUEST_CODE = 100
class MainActivity : AppCompatActivity() {

   private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals,{portal: Portal -> portalClicked(portal)})

    private fun startAddActivity(){
        val intent = Intent(this, addPortal :: class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }
 private fun portalClicked(portal: Portal){
    var url = portal.portalTitle
     if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://$url"
     val browserIntent =
         Intent(Intent.ACTION_VIEW, Uri.parse(url))
     startActivity(browserIntent)


 }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
         portalAdapter

        fabAdd.setOnClickListener { startAddActivity()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                ADD_PORTAL_REQUEST_CODE -> {
                    val reminder =
                        data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(reminder)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun  initViews(){

        rvPortal.layoutManager = GridLayoutManager(this@MainActivity, 2)
        rvPortal.adapter = portalAdapter



    }

    private fun openSesame(portal: Portal) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(portal.portalTitle)
    }
    private fun createItemTouchhelper(): ItemTouchHelper {
        val callback = object  : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portals.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}
