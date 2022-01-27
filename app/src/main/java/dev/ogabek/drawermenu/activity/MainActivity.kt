package dev.ogabek.drawermenu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import dev.ogabek.drawermenu.R
import dev.ogabek.drawermenu.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)

        }

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frame_layout)
        if (fragment is HomeFragment) {
            super.onBackPressed()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).commit()
            }
            R.id.nav_photos -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, PhotosFragment()).commit()
            }
            R.id.nav_movies -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MoviesFragment()).commit()
            }
            R.id.nav_notification -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, NotificationFragment()).commit()
            }
            R.id.nav_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_layout, SettingsFragment()).commit()
            }
            R.id.nav_about_us -> {
                Toast.makeText(this, "You Pressed About Us Button on Navigation Drawer Menu", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_privacy_policy -> {
                Toast.makeText(this, "You Pressed Privacy Policy Button on Navigation Drawer Menu", Toast.LENGTH_SHORT).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}