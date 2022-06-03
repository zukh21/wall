import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class WallServiceTest {
    @Test
    fun updateExisting() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Ko", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Kot", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        // создаём информацию об обновлении
        val update = Post(owner_id = 1, date = 2022, text = "Hi Kotli", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2)

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Test
    fun add() {
        val service = WallService
        val addPost = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        val addPost2 = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        val addPost3 = service.add(Post(owner_id = 1, date = 2022, text = "Hi Kotl", friends_only = false, post_type = "post",
            replay_owner_id = 2, signer_id = 2))
        val result = addPost3.id != 0
        assertTrue(result)
    }
}