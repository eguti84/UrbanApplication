package com.example.urbanapplication.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import java.util.*

class DefinitionTest {

    @Test
    fun `given params, when constructor called, then return Definition`() {
        val obj = Definition(
            defid = 1,
            soundUrls = listOf(),
            thumbsDown = 1,
            author = "author",
            writtenOn = Calendar.getInstance(),
            definition = "definition",
            permalink = "permalink",
            thumbsUp = 10,
            word = "word",
            currentVote = "currentVote",
            example = "example"
        )

        // Then
        assertEquals(1, obj.defid)
        assertEquals(listOf<Definition>(), obj.soundUrls)
        assertEquals(1, obj.thumbsDown)
        assertEquals("author", obj.author)
        assertNotNull(obj.writtenOn)
        assertEquals("definition", obj.definition)
        assertEquals("permalink", obj.permalink)
        assertEquals(10, obj.thumbsUp)
        assertEquals("word", obj.word)
        assertEquals("currentVote", obj.currentVote)
        assertEquals("example", obj.example)

    }
}