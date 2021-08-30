 package com.san22.socialmedia.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.san22.socialmedia.R
import com.san22.socialmedia.databinding.FragmentFeedBinding

 // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {
                                                                                      
    private  val viewModel: FeedViewModel by activityViewModels()
    private val feedAdapter=FeedRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val feed=arguments?.getString("feed")
        feed?.let { viewModel.updateFeed(it) }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val feed=arguments?.getString("feed")
        
        val binding=FragmentFeedBinding.inflate(inflater,container,false)
        binding.rvGalleryFeed.layoutManager=LinearLayoutManager(requireContext())
        binding.rvGalleryFeed.adapter=feedAdapter
        viewModel.feed?.observe({lifecycle}) {
            Toast.makeText(requireContext(),"Download ${it.size} images",Toast.LENGTH_SHORT).show()
            feedAdapter.submitList(it)
        }

        val rootView=inflater.inflate(R.layout.fragment_feed, container, false)
        feed?.let {

            rootView.findViewById<TextView>(R.id.tvFeedType).text=it
        }

        return  binding.root
    }


}