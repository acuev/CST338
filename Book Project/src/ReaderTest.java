/*
Title: ReaderTest.java
Date: 02/17/2021
Description: This is a test file that tests the functions in the reader file.
 */
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    @Test
    void addBook() {
        LocalDate localDate = LocalDate.ofYearDay(0000,6);
        Book book = new Book("1337","Headfirst Java","Education",1337,"Grady Booch",localDate);
        Reader reader = new Reader(1,"Rey","831-582-4003");
        assertEquals(reader.addBook(book), Code.SUCCESS);
        assertNotEquals(reader.addBook(book), Code.SUCCESS);
        assertEquals(reader.addBook(book), Code.BOOK_ALREADY_CHECKED_OUT_ERROR);
    }

    @Test
    void removeBook() {
        LocalDate localDate = LocalDate.ofYearDay(0000,7);
        Book book = new Book("1337","Headfirst Java","Education",1337,"Grady Booch",localDate);

        Reader reader = new Reader(1,"Rey","831-582-4003");
        assertEquals(reader.removeBook(book), Code.READER_DOESNT_HAVE_BOOK_ERROR);
        reader.addBook(book);
        assertEquals(reader.removeBook(book), Code.SUCCESS);
    }

    @Test
    void hasBook() {
        LocalDate localDate = LocalDate.ofYearDay(0000,8);
        Book book = new Book("1337","Headfirst Java","Education",1337,"Grady Booch",localDate);
        Reader reader = new Reader(1,"Rey","831-582-4003");
        assertFalse(reader.hasBook(book));
        reader.addBook(book);
        assertTrue(reader.hasBook(book));
    }

    @Test
    void equalityTest(){
        Reader reader1 = new Reader(123, "Abi","123-456-7890");
        Reader reader2 = new Reader(345,"Name","876-980-1234");
        assertNotEquals(reader1,reader2);
        Reader reader3 = new Reader(789,"Harry","451-908-5467");
        Reader reader4 = new Reader(789,"Harry","451-908-5467");
        assertEquals(reader3,reader4);
    }

    @Test
    void getBookCount(){
        LocalDate localDate = LocalDate.ofYearDay(0000,7);
        Book book = new Book("1337","Headfirst Java","Education",1337,"Grady Booch",localDate);
        Book book2 = new Book("1337", "Headfirst Java", "education", 1337,"Grady Booch", localDate);

        Reader reader = new Reader(1,"Rey","831-582-4003");
        assertEquals(reader.removeBook(book), Code.READER_DOESNT_HAVE_BOOK_ERROR);
        reader.addBook(book);
        reader.addBook(book2);
        assertEquals(reader.getBookCount(),2);
    }

    @Test
    void setterTest(){
        Reader reader1 = new Reader(123, "Abi","123-456-7890");
        assertNotEquals(reader1.getCardNumber(),567);
        reader1.setCardNumber(567);
        assertEquals(reader1.getCardNumber(),567);

        assertNotEquals(reader1.getName(), "Harry");
        reader1.setName("Harry");
        assertEquals(reader1.getName(), "Harry");

        assertNotEquals(reader1.getPhone(),"908-785-2345");
        reader1.setPhone("908-785-2345");
        assertEquals(reader1.getPhone(),"908-785-2345");
    }

    @Test
    void getterTest(){
        Reader reader1 = new Reader(123, "Abi","123-456-7890");
        assertEquals(123, reader1.getCardNumber());
        assertEquals("Abi", reader1.getName());
        assertEquals("123-456-7890",reader1.getPhone());
    }
}