package com.example.orango.cart

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.orango.R
import com.example.orango.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    //private val viewModel: CartViewModel by viewModels()
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setBudget.setOnClickListener{
            // Create an instance of the AlertDialog.Builder class
            val builder = AlertDialog.Builder(this.requireContext())
            // Inflate a custom layout for the dialog
            val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.custom_set_budget_dialog, null)
            builder.setView(view)
            // Create and show the dialog
            val dialog = builder.create()
            // Customize the dialog appearance
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            // Show the dialog
            dialog.show()
        }


        binding.addManual.setOnClickListener {
            // Create an instance of the AlertDialog.Builder class
            val builder = AlertDialog.Builder(this.requireContext())
            // Inflate a custom layout for the dialog
            val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.custom_add_manual_dialog, null)
            builder.setView(view)
            // Create and show the dialog
            val dialog = builder.create()
            // Customize the dialog appearance
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            // Show the dialog
            dialog.show()
        }
    }

    private fun setUpRecyclerView(view: View?) {
        val context = requireContext()

    }
}