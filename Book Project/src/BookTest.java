/*
Title: BookTest.java
Date: 02/14/2021
 */
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

@Test
    public void equalityTest() {
        LocalDate localDate = LocalDate.ofYearDay(0000, 6);
        Book book2 = new Book("1337", "Headfirst Java", "education", 1337,"Grady Booch", localDate);
        Book book1 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy", "sci-fi", 42, "Douglas Adams", localDate);
        assertNotEquals(book1,book2);
        Book book3 = new Book("34-w-34", "Dune","sci-fi", 235, "Frank Herbert" , localDate);
        Book book4 = new Book("34-w-34", "Dune","sci-fi", 235, "Frank Herbert" , localDate);
        assertEquals(book3,book4);
    }
@Test
    public void setterTest(){

    LocalDate localDate = LocalDate.ofYearDay(0000, 6);
    LocalDate localDates = LocalDate.ofYearDay(0030, 3);
    Book book1 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy","sci-fi",42,"Douglas Adams", localDate);
    assertNotEquals(book1.getISBN(), "42-q-88");
    book1.setISBN("42-q-88");
    assertEquals(book1.getISBN(), "42-q-88");

    assertNotEquals(book1.getTitle(), "Dune");
    book1.setTitle("Dune");
    assertEquals(book1.getTitle(),"Dune");

    assertNotEquals(book1.getSubject(), "fiction");
    book1.setSubject("fiction");
    assertEquals(book1.getSubject(),"fiction");

    assertNotEquals(book1.getPageCount(), 33);
    book1.setPageCount(33);
    assertEquals(book1.getPageCount(),33);

    assertNotEquals(book1.getAuthor(), "Frank Herb");
    book1.setAuthor("Frank Herb");
    assertEquals(book1.getAuthor(), "Frank Herb");

    assertNotEquals(book1.getDueDate(), localDates);
    book1.setDueDate(localDates);
    assertEquals(book1.getDueDate(), localDates);
    }
@Test
    public void getterTestandFieldtest(){
    LocalDate localDate = LocalDate.ofYearDay(2000, 1);
    Book book1 = new Book("1337","Headfirst Java","Education",1337,"Grady Booch",localDate);
    assertEquals("1337",book1.getISBN());
    assertEquals("Headfirst Java",book1.getTitle());
    assertEquals("Education",book1.getSubject());
    assertEquals(1337,book1.getPageCount());
    assertEquals("Grady Booch",book1.getAuthor());
    assertEquals(localDate,book1.getDueDate());
    }
@Test
    public void constructorTest(){
    LocalDate localDate = LocalDate.ofYearDay(0000, 1);
    Book books = null;
    assertNull(books);
    Book bookDummy = new Book("","","",0,"",localDate);
    assertNotNull(bookDummy);
    }
}