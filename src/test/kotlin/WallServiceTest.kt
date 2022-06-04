import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class WallServiceTest {
    @Test
    fun updateExisting() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        val video = Video( title = "Netology", duration = 120)
        val audio = Audio(artist = "Bob", title = "My village", duration = 120)
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Ko", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Kot", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        // создаём информацию об обновлении
        val update = Post(owner_id = 1, date = 2022, text = "Hi Kotli", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio)

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Test
    fun add() {
        val service = WallService
        val video = Video( title = "Netology", duration = 120)
        val audio = Audio(artist = "Bob", title = "My village", duration = 120)
        val addPost = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        val addPost2 = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        val addPost3 = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio))
        val result = addPost3.id != 0
        assertTrue(result)
    }
    @Test()
    fun shouldThrow() {
        val comment = Comments(count = 1, text = "New comment")
       assertThrows(PostNotFoundException::class.java){
           WallService.createComments(1, comment)
       }
        val result = comment.count != 0
        assertTrue(result)
    }
}