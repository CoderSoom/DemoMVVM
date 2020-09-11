package com.ddona.demomvvm.view.viewpager.song

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.Observer
import com.ddona.demomvvm.databinding.FragmentSongBinding
import com.ddona.demomvvm.view.SongAdapter
import com.ddona.demomvvm.viewmodel.SongViewModel


class SongFragment :Fragment(),
    LifecycleOwner,
    SongAdapter.ISongAdapter,
    View.OnClickListener {
    private lateinit var model: SongViewModel
    private lateinit var binding: FragmentSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSongBinding.inflate(
            inflater, container, false
        )

        model = SongViewModel()
        binding.data=model
        binding.rc.layoutManager = LinearLayoutManager(context)
        binding.rc.adapter = SongAdapter(this)
        register()
        binding.btnSearch.setOnClickListener(this)


        binding.edtSearch.setText(arguments?.getString("DATA"))
        model.searSong(arguments?.getString("DATA")!!, context!!)
        return binding.root
    }

    private fun register() {
        model.songData.observe(this, Observer {
            Log.d("MainActivity", "observe register...................")
            binding.rc.adapter!!.notifyDataSetChanged()

        })
    }

    override fun onClick(v: View) {
        val content = binding.edtSearch.text.toString()
        model.searSong(content, context!!)
    }

    override fun getCount(): Int {
        if (model.songData.value == null){
            return 0
        }
        return model.songData.value!!.size
    }

    override fun getData(position: Int) = model.songData.value!!.get(position)
    override fun onClickItem(position: Int) {

    }
}