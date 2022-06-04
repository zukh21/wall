import java.lang.reflect.Array
import java.time.Duration

//interface Post{
//    var id: Int
//    val owner_id: Int
//    val date: Int
//    val text: String
//    val replay_owner_id: Int
//    val friends_only: Boolean
//    val comments: Comments
//    val copyright: String
//    val likes: Likes?
//    val views: Views?
//    val post_type: String
//    val signer_id: Int
//    val can_pin: Boolean
//    val can_delete: Boolean
//    val can_edit: Boolean
//    val marked_as_ads: Boolean
//    val is_favorite: Boolean
//}

interface Attachment{
    val type: String
}

class Audio(
    override val type: String = "audio",
    val id : Int = 0, // Идентификатор аудиозаписи.
    val owner_id: Int = 0, // Идентификатор владельца аудиозаписи.
    val artist: String, // Исполнитель.
    val title: String, // Название композиции.
    val duration: Int, // Длительность ролика в секундах.
    val url: String = "www.netology.ru", // Ссылка на mp3.
    val date: Int = 2022, // Дата добавления.

): Attachment{

}

class Video(
    override val type: String = "video", // TypePost
    val id : Int = 0, // Идентификатор видеозаписи.
    val owner_id: Int = 0, // Идентификатор владельца видеозаписи.
    val title: String, // Название видеозаписи.
    val description: String = "description", // Текст описания видеозаписи.
    val duration: Int, // Длительность ролика в секундах.
    val date: Int = 2022, // Дата создания видеозаписи в формате Unixtime.
    val views: Int = 0, // Количество просмотров видеозаписи.

) : Attachment{

}