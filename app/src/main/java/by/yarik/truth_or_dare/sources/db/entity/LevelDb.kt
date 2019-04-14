package by.yarik.truth_or_dare.sources.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "levels")
data class LevelDb (@PrimaryKey(autoGenerate = true) val id: Long, val enTitle: String, val ruTitle: String, val value: Int)