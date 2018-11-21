package tibaes.com.friends.project.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import tibaes.com.friends.project.db.Friend
import tibaes.com.friends.project.db.FriendDatabase
import tibaes.com.friends.project.repository.FriendRepository
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.coroutineContext

class FriendViewModel(application: Application) : AndroidViewModel(application){

    private val repository: FriendRepository
    val allFriends: LiveData<List<Friend>>

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        val friendDao = FriendDatabase.
                getDatabase(application, scope).friendDAO()

        repository = FriendRepository(friendDao)
        allFriends = repository.allFriends
    }

    fun insert(friend: Friend) = scope.launch(Dispatchers.IO){
        repository.insert(friend)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
