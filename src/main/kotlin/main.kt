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
    val video: Video,
    val audio: Audio,
)
data class Likes(
    val count: Int = 0,
    val user_likes: Boolean = true,
    val can_publish: Boolean = true
)
data class Comments(
    var count: Int = 0,
    var can_post: Boolean = true,
    var text: String = ""
    )

data class Views(
    val count: Int = 0
)

class PostNotFoundException(message: String): RuntimeException(message)

object WallService{
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
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
    fun createComments(postId: Int, comment: Comments): Comments{
        for (post in posts){
            if (postId == post.id){
                comments += comment
                return comments.last()
            }
        }
        throw PostNotFoundException("Post not found with id: $postId")
    }
}
fun main(){
    val video = Video( title = "Netology", duration = 120)
    val audio = Audio(artist = "Bob", title = "My village", duration = 120)
    val post = Post( owner_id = 1, date = 2022, text = "Hi Kotlin", friends_only = false, post_type = "post",
        replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio
    )
    val post2 = Post( owner_id = 1, date = 2022, text = "Hi Kotlin", friends_only = false, post_type = "post",
        replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio
    )


    val comment = Comments(count = 1, text = "New comment")
    WallService.add(post)
    WallService.add(post2)
    println(WallService.update(post))
    println(WallService.update(post2))
    println(WallService.get(1))
    println(WallService.createComments(2, comment))
}