package com.lolozianas.lunchtray.ui.order

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lolozianas.lunchtray.R
import com.lolozianas.lunchtray.databinding.FragmentCheckoutBinding
import com.lolozianas.lunchtray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CheckoutFragment : Fragment() {


    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val orderViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = orderViewModel
            checkoutFragment = this@CheckoutFragment
        }
    }


    fun submitOrder() {
        val orderSummary = getString(
            R.string.order_details,
            orderViewModel.entree.value?.name,
            orderViewModel.side.value?.name,
            orderViewModel.accompaniment.value?.name,
            orderViewModel.total.value
        )
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, orderSummary)
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_extra_subject))
        val possibleActivities: List<ResolveInfo> =
            activity?.packageManager?.queryIntentActivities(
                intent,
                PackageManager.MATCH_ALL
            ) as List<ResolveInfo>
        if (possibleActivities.size > 1) {
            // Create intent to show chooser.
            // Title is something similar to "Share this photo with".
            val chooser = getString(R.string.chooser_title).let { title ->
                Intent.createChooser(intent, title)
            }
            startActivity(intent)
        } else if (intent.resolveActivity(activity?.packageManager!!) != null){
            startActivity(intent)
        }
    }

    fun cancelOrder() {
        orderViewModel.resetOrder()
        findNavController().navigate(R.id.action_checkoutFragment_to_startOrderFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}