package com.example.orango.ui.notes

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orango.R
import com.example.orango.databinding.FragmentNotesBinding
import com.example.orango.databinding.FragmentPaymentDetailsBinding

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.deleteAllNotes.setOnClickListener{
            // Create an instance of the AlertDialog.Builder class
            val builder = AlertDialog.Builder(this.requireContext())
            // Inflate a custom layout for the dialog
            val view = LayoutInflater.from(this.requireContext()).inflate(R.layout.custom_delete_notes_dialog, null)
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
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)



    }

}