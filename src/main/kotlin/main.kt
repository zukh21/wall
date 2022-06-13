import javax.xml.stream.events.Comment

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
class NoteNotFoundException(message: String): RuntimeException(message)
class CommentNotFoundException(message: String): RuntimeException(message)
class AllErrorException(message: String): RuntimeException(message)

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

open class Notes{

    data class notesFields(
        val id: Int = 0,
        val text: String,
        var comments: Comments = Comments(),
        val date: Int
    )
    object WallService{
        private var notes = mutableListOf<notesFields>()
        private var removedNotes = mutableListOf<notesFields>()
        private var comments = mutableListOf<Comments>()
        private var removedComments = mutableListOf<Comments>()
        private  var id = 0
        fun add(note: notesFields): notesFields { // Создает новую заметку у текущего пользователя
            notes.add(note)
            notes.add(note.copy(id = ++id))
            return notes.last()
        }
        fun createComment(noteId: Int, noteList: notesFields, comment: Comments): Comments { // Добавляет новый комментарий к заметке
            for (note in notes){
                if (note.id == noteId && noteList in notes){
                    comments.add(comment)
                    return comments.last()
                }
            }
            throw NoteNotFoundException("Note not found with $noteId id")
        }

        fun delete(note: notesFields): String { // Удаляет заметку текущего пользователя
            if (note in notes){
                notes.remove(note)
                removedNotes.add(note)
                return "Note removed successfully"
            }
            throw NoteNotFoundException("Note not found")
        }

        fun deleteComment(note: notesFields, comment: Comments): String { // Удаляет комментарий к заметке
            if (comment in comments && note in notes){
                    comments.remove(comment)
                    removedComments.add(comment)
                    return "comment removed successfully"
            }
            throw NoteNotFoundException("Comment not found")
        }

        fun edit(noteId: Int, newNote: notesFields): notesFields { // Редактирует заметку текущего пользователя.
            for (note in notes){
                if (note.id == noteId){
                    notes[noteId] = newNote.copy(id = noteId, date = note.date)
                    return notes[noteId]
                }
            }
            throw NoteNotFoundException("Note not found with $noteId id")
        }

        fun editComment(noteList: notesFields, comment: Comments, newComments: Comments): Comments { // Редактирует указанный комментарий у заметки.
            for (note in notes){
                if (noteList in notes){
                    if (comment in comments){
                        comments.remove(comment)
                            noteList.comments = newComments
                            return noteList.comments
                    }else throw CommentNotFoundException("Comment not found")
                }else throw NoteNotFoundException("Note not found")
            }
            throw AllErrorException("The comment has not been updated")
        }

        fun get(): MutableList<notesFields> { // Возвращает список заметок, созданных пользователем.
            return notes
        }
        fun getById(id: Int): notesFields { //Возвращает заметку по её id
            for (note in notes){
                if (note.id == id){
                    return notes[id]
                }
            }
            throw NoteNotFoundException("note not found")
        }
        fun getComments(): MutableList<Comments> { //Возвращает список комментариев к заметке.
            return comments
        }

        fun restoreComment(comment: Comments): String { //Восстанавливает удалённый комментарий.
            if (comment !in comments && comment in removedComments){
                comments.add(comment)
                return "Restore comment has been success"
            }
            throw AllErrorException("Restore comment has note been success")
        }

//        End
    }


}

fun main(){
//    val service = Notes.WallService // Сервис для (Заметка)
//    val comment = Comments(count = 1, text = "New comment")
//    val note = Notes.notesFields(text = "Zukhridin notes", comments = comment, date = 2022)
//    val note2 = Notes.notesFields(text = "Ilyo", comments = Comments(text = "it is comment"), date = 2021)
//    service.add(note)
//    println(service.add(note2))
//    val CommentForNote2 = service.createComment(2, note2, comment = Comments(text = "new comment for note2"))
//    println(service.edit(1, Notes.notesFields(text = "Muza", comments = Comments(text = "edit note"), date = 2021))) // id и дата не поменяется
//    println(service.editComment(note2, CommentForNote2, newComments = Comments(text = "It is updated comment")))
//
////    println(service.delete(note))
//    println(service.createComment(1, note, comment))
////    println(service.deleteComment(note, comment))
//    println(service.get())
//    println(service.getById(1))
//    println(service.getComments())
//    println(service.deleteComment(note, comment))
//    println(service.getComments())
//    println(service.restoreComment(comment))
//    println(service.getComments())







//    val video = Video( title = "Netology", duration = 120)
//    val audio = Audio(artist = "Bob", title = "My village", duration = 120)
//    val post = Post( owner_id = 1, date = 2022, text = "Hi Kotlin", friends_only = false, post_type = "post",
//        replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio
//    )
//    val post2 = Post( owner_id = 1, date = 2022, text = "Hi Kotlin", friends_only = false, post_type = "post",
//        replay_owner_id = 2, signer_id = 2, views = null, likes = Likes(), video = video, audio = audio
//    )
//
//
//    val comment = Comments(count = 1, text = "New comment")
//    WallService.add(post)
//    WallService.add(post2)
//    println(WallService.update(post))
//    println(WallService.update(post2))
//    println(WallService.get(1))
//    println(WallService.createComments(2, comment))
}