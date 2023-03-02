package com.example.lektion_7_viewmodeltoastsnackbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.lektion_7_viewmodeltoastsnackbar.counter.CounterViewModel
import com.example.lektion_7_viewmodeltoastsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Initialize ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ID's
        val btnDisplayToast = binding.btnDisplayToast
        val btnCounterValueAdd = binding.btnCounterValueAdd
        val tvCounterValue = binding.tvCounterValue


        tvCounterValue.setOnClickListener() {}
        btnDisplayToast.setOnClickListener() {}
        btnCounterValueAdd.setOnClickListener() {}






        // ViewModel
        val counterViewModel by viewModels<CounterViewModel>()

        // ViewModel LifeCycle
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                counterViewModel.uiState.collect() {

                    // Update UI Elements
                    tvCounterValue.text = counterViewModel.uiState.value.counterValue.toString()

                }
            }
        }

        // OnClick ViewModel Value +1

        btnCounterValueAdd.setOnClickListener() {
            counterViewModel.add()
        }










        // OnClick Toast + Snackbar
        btnDisplayToast.setOnClickListener() {

            // Create Toast
            /** Context - Alternatives for getting context are:
             *      this
             *      MainActivity() <-- Does not work as is
             *      applicationContext (which is a getter)
             */

            val myToast = Toast.makeText(
                applicationContext,
                "The toast is in action, hello from the Toast World!",
                Toast.LENGTH_SHORT).show()

            // Snackbar.make(this, binding.root, "", 0)
            Snackbar.make(binding.root, "This is a Snackbar, Saying Hello!", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", MyUndoListener()
                ).show()

        }



    }
}