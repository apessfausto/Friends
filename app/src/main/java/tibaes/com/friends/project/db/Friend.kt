package tibaes.com.friends.project.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "friend_table")
data class Friend (

        @ColumnInfo(name = "nome")
        var nome: String,
        @ColumnInfo(name = "telefone")
        var telefone: String = ""

        ):Serializable{
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long = 0
}