interface Attachment{
    val type: String
}

data class Audio(
    val id: Int = 0, // Идентификатор аудиозаписи.
    val owner_id: Int = 0, // Идентификатор владельца аудиозаписи.
    val artist: String? = null, // Исполнитель.
    val title: String? = null, // Название композиции.
    val duration: Int = 0, // Длительность ролика в секундах.
)

data class Video(
    val id: Int = 0, // Идентификатор видеозаписи.
    val owner_id: Int = 0, // Идентификатор владельца видеозаписи.
    val title: String? = null, // Название видеозаписи.
    val date: Int = 2022, // Дата создания видеозаписи в формате Unixtime.

)

data class AudioAttachment(override val type: String, val audio: Audio = Audio()) : Attachment
data class VideoAttachment(override val type: String, val video: Video = Video(title = "Netology history")) : Attachment