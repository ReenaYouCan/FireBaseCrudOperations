package com.reena.programs

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.firebase.client.Firebase
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import android.text.TextUtils
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.content_main.*
import com.reena.programs.model.User


@Suppress("UNREACHABLE_CODE")
/**
 *
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var mFirebaseDatabase: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase

    var UserId: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //Initialize Firebase
        Firebase.setAndroidContext(this);

        // Drawer View
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        // Navigation View
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)


        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            setUpFireBase()
        }
    }

    /**
     * Setup firebase
     * */
    fun setUpFireBase() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");
        // app_title change listener

        mFirebaseInstance.getReference("app_title").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val appTitle = dataSnapshot?.getValue(String::class.java)

                supportActionBar?.setTitle(appTitle)
            }
        })

        if (UserId.equals("")) {
            createUser(edtUserName.text.toString(), edtUserPassword.text.toString())
        } else {
            updateUser(edtUserName.text.toString(), edtUserPassword.text.toString())
        }

    }
    /*
    *
    * Creating new user node under 'users'
    */


    fun createUser(name: String, email: String) {
        UserId = mFirebaseDatabase.push().key

        val user = User(name, email);
        mFirebaseDatabase.child(UserId).setValue(user)

        mFirebaseDatabase.child(UserId).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val user = dataSnapshot?.getValue(User::class.java)

                // Check for null
                if (user == null) {
                    Log.e("Null user", "User data is null!")
                    return
                }

                Log.e("User Data Changed", "User data is changed!" + user!!.name + ", " + user!!.email)


                // clear edit text
                edtUserName.setText("")
                edtUserPassword.setText("")

            }
        })
    }

    /**
     * update user if already exists
     */
    fun updateUser(name: String, email: String) {
        val userId = mFirebaseDatabase.push().getKey()

        mFirebaseDatabase.child(userId).child("name").setValue(name)
        mFirebaseDatabase.child(userId).child("email").setValue(email)

    }


    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onStart() {
        super.onStart()
        printLog(localClassName, "onStart")
    }

    override fun onResume() {
        super.onResume()
        printLog(localClassName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        printLog(localClassName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        printLog(localClassName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        printLog(localClassName, "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        printLog(localClassName, "onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        printLog(localClassName, "onRestart")
    }

    fun printLog(className: String, errMsg: String) {
        Log.e(className, errMsg)
    }
}
