package tibaes.com.friends.project.repository

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import tibaes.com.friends.project.db.Friend
import tibaes.com.friends.project.db.FriendDao

class FriendRepository(private val friendDao: FriendDao) {

    val allFriends: LiveData<List<Friend>> = friendDao.getAll()

    fun insert(friend: Friend){
        friendDao.insert(friend)
    }
}