package com.example.orango.ui.paymentDetails

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orango.R
import com.example.orango.databinding.FragmentCartBinding
import com.example.orango.databinding.FragmentPaymentDetailsBinding

class PaymentDetailsFragment : Fragment() {
    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = PaymentDetailsFragment()
    }

    private lateinit var viewModel: PaymentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentDetailsBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkout.setOnClickListener{
            // Create an instance of the AlertDialog.Builder class
            val builder = AlertDialog.Builder(this.requireContext())
            // Inflate a custom layout for the dialog
            val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.custom_points_dialog, null)
            builder.setView(view)
            // Create and show the dialog
            val dialog = builder.create()
            // Customize the dialog appearance
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            // Show the dialog
            dialog.show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}