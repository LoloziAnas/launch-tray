package com.lolozianas.lunchtray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lolozianas.lunchtray.R
import com.lolozianas.lunchtray.databinding.FragmentStartOrderBinding


/**
 * [StartOrderFragment] allows users to click to the start button to start an order.
 */
class StartOrderFragment : Fragment() {

    // Binding object instance corresponding to fragment_start_order.xml layout.
    private var _binding: FragmentStartOrderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartOrderBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnStartOrder.setOnClickListener {
            // Navigate to the next destination to select the flavor of the cupcakes
            findNavController().navigate(R.id.action_startOrderFragment_to_entreeMenuFragment)
        }
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}