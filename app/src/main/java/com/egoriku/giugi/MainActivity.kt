package com.egoriku.giugi

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import com.egoriku.giugi.adapter.ToysAdapter
import com.egoriku.giugi.data.Toy
import egoriku.com.guigiu.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "All toys"

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.apply {
            setNavigationItemSelectedListener(this@MainActivity)
            setCheckedItem(R.id.nav_all_toys)
        }

        val list = mutableListOf<Toy>()
        list.add(Toy("1", R.drawable.ic1))
        list.add(Toy("2", R.drawable.ic2))
        list.add(Toy("3", R.drawable.ic3))
        list.add(Toy("4", R.drawable.ic4))
        list.add(Toy("5", R.drawable.ic5))

        val adapterToy = ToysAdapter(this, list)

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterToy
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }

        super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_order -> startActivity(Intent(this, OrderActivity::class.java))
            R.id.nav_share -> Toast.makeText(this, "Will share application link", Toast.LENGTH_SHORT).show()
            R.id.nav_send -> Toast.makeText(this, "Will share feedback in Google Play", Toast.LENGTH_SHORT).show()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
