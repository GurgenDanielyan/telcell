package com.telcell.movie.di.component

import com.telcell.movie.di.module.MainModule
import com.telcell.movie.di.scope.MainScope
import com.telcell.movie.main.movies.MoviesFragment
import com.telcell.movie.main.vodDetail.VodDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@MainScope
interface MainComponent {
    //    fun inject(activity: MainActivity)
//    fun inject(activity: FullVodPlayerActivity)
//
    fun inject(fragment: MoviesFragment)
    fun inject(fragment: VodDetailFragment)
//    fun inject(fragment: SettingsFragment)
//    fun inject(fragment: TabsChannelsFragment)
//    fun inject(fragment: PlayerFragment)
//    fun inject(fragment: ReminderPickerFragment)
//    fun inject(fragment: DayEpgFragment)
//    fun inject(fragment: VodListFragment)
//    fun inject(fragment: DescriptionVodFragment)
//    fun inject(fragment: InternalProfileFragment)
//    fun inject(fragment: InitialBlockVoDFragment)
//    fun inject(fragment: TabsVoDFragment)
//    fun inject(fragment: ShopChannelsFragment)
//    fun inject(fragment: ShopPackagesFragment)
//    fun inject(fragment: TabsSubscriptionsFragment)
//    fun inject(fragment: ShopPackageDetailsFragment)
//    fun inject(fragment: SearchVodFragment)
//
//    fun inject(presenter: SettingsPresenter)
//    fun inject(presenter: TabsChannelsPresenter)
//    fun inject(presenter: PlayerPresenter)
//    fun inject(presenter: ChannelsPresenter)
//
//    fun getFavoriteIds(): FavoriteIds
//    fun getAppSettings(): AppSettings
//    fun getChannelsBox(): ChannelsBox
//    fun getListInListVodPresenter(): VodListPresenter
}