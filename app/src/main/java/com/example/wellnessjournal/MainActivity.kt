package com.example.wellnessjournal
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wellnessjournal.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_timer,
                R.id.navigation_fitness,
                R.id.navigation_journal_features
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val currentNavPosition = navController.currentDestination

        // Don't show bottom navigation bar for Welcome screen, but do show it for all other screens
        if (currentNavPosition != null) {
            if (currentNavPosition.label == "Welcome") {
                navView.visibility = View.GONE
            } else {

                navView.visibility = View.VISIBLE
            }
        }
    }

    // Set up the back button to go to the previous fragment
    override fun onSupportNavigateUp(): Boolean {
        val navigationController = findNavController(R.id.nav_host_fragment_activity_main)
        return navigationController.navigateUp()
    }
}