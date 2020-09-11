package com.ddona.demomvvm.view.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ddona.demomvvm.view.viewpager.song.SongFragment

class SongAdapter : FragmentPagerAdapter{
    constructor(manager:FragmentManager):super(manager){

    }
    override fun getItem(position: Int): Fragment {
        val ar = Bundle()
        when(position){
            0->{
                ar.putString("DATA", "")
            }
            1->{
                ar.putString("DATA", "Xa em")
            }
            2->{
                ar.putString("DATA", "Big city boi")
            }
            3->{
                ar.putString("DATA", "Lac Troi")
            }
            4->{
                ar.putString("DATA", "bad guy")
            }
        }
        val fg = SongFragment()
        fg.arguments = ar
        return fg
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "Favourite"
            1->return "Xa em"
            2->return "Big city boi"
            3->return "Lac Troi"
            4->return "bad guy"
        }
        return ""
    }

    override fun getCount(): Int {
       return 5
    }
}