data class Post(
    var id: Int = 0,
    val owner_id: Int,
    val date: Int,
    val text: String = " ",
    val replay_owner_id: Int,
    val friends_only: Boolean,
    val comments: Comments = Comments(),
    val copyright: String = "zim",
    val likes: Likes?,
    val views: Views?,
    val post_type: String,
    val signer_id: Int,
    val can_pin: Boolean = false,
    val can_delete: Boolean = true,
    val can_edit: Boolean = true,
    val marked_as_ads: Boolean = false,
    val is_favorite: Boolean = false,
    val attachments: Array<Attachment> = arrayOf()
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
    private var id: Int = 0;
    fun add(post: Post): Post {
        posts += post
        posts += post.copy(id = ++id)
        return posts.last()
    }
    fun update(post: Post): Boolean{
        for ((index, newPost) in posts.withIndex()){
            if (newPost.id == post.id){
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }
    fun get(id: Int): Post {
        return posts[id]
    }
}
fun main(){
    val video = Video( title = "Netology")
    val audio = Audio(artist = "Bob", title = "My village", duration = 120)
    val a1 = AudioAttachment("audio", audio)
    val v1 = VideoAttachment("video", video)


    val post = Post( owner_id = 1, date = 2022, text = "Hi Kotlin", friends_only = false, post_type = "post",
        replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), attachments = arrayOf(a1,v1)
    )
//
    WallService.add(post)
//    println(WallService.update(post))
    println(WallService.get(1))
}