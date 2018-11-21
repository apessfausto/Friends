package tibaes.com.friends.project.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Friend::class], version = 1)
abstract class FriendDatabase: RoomDatabase() {

    abstract fun friendDAO():FriendDao

    companion object {

        @Volatile
        private var INSTANCE: FriendDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope):FriendDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FriendDatabase::class.java,
                        "friend-database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(FriendDatabaseCalback(scope))
                        .build()
                    INSTANCE = instance
                instance
            }
        }
    }

    private class FriendDatabaseCalback(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                   populaTabela(database.friendDAO())
                }
            }
        }
        fun populaTabela(friendDao: FriendDao){
          /*  friendDao.deleteAll()
            friendDao.insert(Friend(id= 1, nome = "Juliana"))
            friendDao.insert(Friend(id= 2, nome = "Helena"))
            friendDao.insert(Friend(id= 3, nome = "Luciana"))
            */
        }
    }

}