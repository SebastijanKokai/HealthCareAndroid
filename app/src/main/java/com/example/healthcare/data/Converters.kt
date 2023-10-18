package com.example.healthcare.data

import androidx.room.TypeConverter
import com.example.healthcare.data.room.entities.UserEntity
import java.util.UUID

class Converters {

    @TypeConverter
    fun fromUUID(uuid: UUID): String = uuid.toString().takeUnless {
        uuid.toString() == UserEntity.DEFAULT_UUID
    } ?: UUID.randomUUID().toString()

    @TypeConverter
    fun uuidFromString(string: String?): UUID = string?.let {
        UUID.fromString(it)
    } ?: UUID.fromString(UserEntity.DEFAULT_UUID)
}