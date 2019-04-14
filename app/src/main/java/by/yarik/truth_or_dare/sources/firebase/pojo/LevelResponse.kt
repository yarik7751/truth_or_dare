package by.yarik.truth_or_dare.sources.firebase.pojo

import com.google.firebase.database.PropertyName

class LevelResponse {

    @set:PropertyName("title")
    @get:PropertyName("title")
    lateinit var title: Title

    @set:PropertyName("value")
    @get:PropertyName("value")
    var value: Int = -1

    class Title {

        @set:PropertyName("en")
        @get:PropertyName("en")
        lateinit var enTitle: String

        @set:PropertyName("ru")
        @get:PropertyName("ru")
        lateinit var ruTitle: String
    }
}