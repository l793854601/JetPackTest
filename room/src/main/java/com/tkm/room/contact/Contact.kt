package com.tkm.room.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var id: Int,

    @ColumnInfo(name = "first_name", typeAffinity = ColumnInfo.TEXT)
    var firstName: String?,

    @ColumnInfo(name = "last_name", typeAffinity = ColumnInfo.TEXT)
    var lastName: String?,

    @ColumnInfo(name = "phone", typeAffinity = ColumnInfo.TEXT)
    var phone: String?,
) : Serializable {
    @Ignore
    constructor(firstName: String?, lastName: String?, phone: String?) : this(0, firstName, lastName, phone)

    @Ignore
    constructor() : this(0, null, null, null)

    val displayName: String
        get() {
            val sb = StringBuilder()
            if (!firstName.isNullOrEmpty()) {
                sb.append(firstName)
            }
            if (!lastName.isNullOrEmpty()) {
                sb.append(" ")
                sb.append(lastName)
            }
            return sb.toString()
        }
}
