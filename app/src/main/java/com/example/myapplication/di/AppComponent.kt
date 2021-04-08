package com.example.myapplication.di

import com.example.myapplication.MainActivity
import com.example.myapplication.ui.gallery.GalleryFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.slideshow.SlideshowFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NavigationModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: GalleryFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: SlideshowFragment)
    fun getRouter(): Router
    fun getNavigatorHolder(): NavigatorHolder
}