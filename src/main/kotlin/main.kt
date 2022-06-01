data class Post(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val text: String = " ",
    val replay_owner_id: Int,
    val friends_only: Boolean,
    val comments: Comments = Comments(),
    val copyright: String = "zim",
    val likes: Likes = Likes(),
    val views: Views = Views(),
    val post_type: String,
    val signer_id: Int,
    val can_pin: Boolean = true,
    val can_delete: Boolean = true,
    val can_edit: Boolean = true,
    val marked_as_ads: Boolean = false,
    val is_favorite: Boolean = false,
)
data class Likes(
    val count: Int = 0,
    val user_likes: Boolean = true,
    val can_publish: Boolean = true
)
data class Comments(
    var count: Int = 0,
    var can_post: Boolean = true, )

data class Views(
    val count: Int = 0
)
object WallService{
    private var posts = emptyArray<Post>()
    fun set(index: Int, post: Post){

    }
    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }
}
fun main(){
    val post = Post(1, 1, 2022, "Hi, Kotlin", 2, false, Comments(),
        "Zukh", Likes(), Views(), "post", 3, can_pin = true, can_delete = false, false, true,
        is_favorite = true
    )
    val post2 = Post(2, 2, 2022 , replay_owner_id = 1, friends_only = false, comments = Comments(),
        likes = Likes(), views = Views(), post_type = "repost", signer_id = 1)
    println("${WallService.add(post)}\n${WallService.add(post2)}")
}