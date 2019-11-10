package io.github.aungkothet.padc.assignment11.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aungkothet.padc.assignment11.data.vos.PhotoVO
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPhotos(events: List<PhotoVO>): Single<LongArray>

    @Query("SELECT * FROM photoUrl")
    abstract fun getAllPhotos(): LiveData<List<PhotoVO>>

    @Query("SELECT * FROM photoUrl")
    abstract fun getAllPhotosMaybe(): Maybe<List<PhotoVO>>

    @Query("SELECT * FROM photoUrl WHERE id LIKE :id")
    abstract fun getSearchPhoto(id: String): List<PhotoVO>
}