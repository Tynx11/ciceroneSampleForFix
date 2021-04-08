package com.example.myapplication.ui

import com.example.myapplication.ui.gallery.GalleryFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.slideshow.SlideshowFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun galleryFragment() = FragmentScreen() {
        GalleryFragment()
    }

    fun homeFragment() = FragmentScreen() {
        HomeFragment()
    }

    fun slideShowFragment() = FragmentScreen() {
        SlideshowFragment()
    }
}