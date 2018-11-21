package tibaes.com.friends.project.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FriendDao {

    @Insert
    fun insert(friend: Friend)

    @Query("DELETE FROM friend_table")
    fun deleteAll()

    @Query("SELECT * FROM friend_table")
    fun getAll():LiveData<List<Friend>>
}