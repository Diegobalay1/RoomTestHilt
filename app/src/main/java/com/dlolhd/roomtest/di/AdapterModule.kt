package com.dlolhd.roomtest.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ActivityComponent::class)
@Module
object AdapterModule {

    @Provides
    fun provideLayoutManager(@ApplicationContext context: Context): LayoutManager =
        LinearLayoutManager(context)


}