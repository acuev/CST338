/*
Title: ShelfTest.java
Date: 02/21/2021
Description: This is to test the functions in the shelf.java
 */
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    @Test
    void getBookCount() {
        Shelf shelf = new Shelf();
        LocalDate localDate =  LocalDate.ofYearDay(0000,6);;
        Book book1 = new Book("34-w-34", "Dune","sci-fi", 235, "Frank Herbert" , localDate);
        Book book2 = new Book("1337", "Headfirst Java", "Fiction", 1337,"Grady Booch", localDate);
        shelf.addBook(book1);
        shelf.addBook(book1);
        shelf.addBook(book1);
        shelf.addBook(book1);
        System.out.println(shelf.getBookCount(book1));
        shelf.removeBook(book1);
        System.out.println(shelf.getBookCount(book1));
        System.out.println(shelf.getBookCount(book2));
    }

    @Test
    void addBook() {
        Shelf shelf = new Shelf();
        LocalDate localDate =  LocalDate.ofYearDay(0000,6);;
        Book book1 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy","sci-fi",42,"Douglas Adams", localDate);
        Book book2 = new Book("1337", "Headfirst Java", "Fiction", 1337,"Grady Booch", localDate);
        //shelf.addBook(book1);
        assertEquals(shelf.addBook(book1), Code.SUCCESS);
        System.out.println(shelf.getBookCount(book1));
        //shelf.addBook(book1);
        assertEquals(shelf.addBook(book1),Code.SUCCESS);
        System.out.println(shelf.getBookCount(book1));
        assertEquals(shelf.addBook(book2),Code.SHELF_SUBJECT_MISMATCH_ERROR);
        System.out.println(shelf.getBookCount(book2));
    }

    @Test
    void removeBook() {
        LocalDate localDate =  LocalDate.ofYearDay(0000,6);;
        Book book2 = new Book("1337", "Headfirst Java", "education", 1337,"Grady Booch", localDate);
        Shelf shelf = new Shelf();
        assertEquals(shelf.removeBook(book2), Code.BOOK_NOT_IN_INVENTORY_ERROR);
        assertEquals(shelf.addBook(book2), Code.SUCCESS);
        System.out.println(shelf.getBookCount(book2));
        assertEquals(shelf.removeBook(book2), Code.SUCCESS);
        System.out.println(shelf.getBookCount(book2));
        assertEquals(shelf.removeBook(book2), Code.BOOK_NOT_IN_INVENTORY_ERROR);
        System.out.println(shelf.getBookCount(book2));
    }

    @Test
    void listBooks() {
        Shelf shelf = new Shelf();
        shelf.setShelfNumber(2);
        shelf.setSubject("Education");

        LocalDate localDate =  LocalDate.ofYearDay(0000,6);;
        Book book2 = new Book("1337", "Headfirst Java", "education", 1337,"Grady Booch", localDate);
        shelf.addBook(book2);
        shelf.addBook(book2);
        shelf.listBooks();

        Shelf temp = new Shelf();
        temp.setShelfNumber(1);
        temp.setSubject("sci-fi");
        Book book1 = new Book("34-w-34", "Dune","sci-fi", 235, "Frank Herbert" , localDate);
        temp.addBook(book1);
        temp.listBooks();
    }
    @Test
    void getShelfNumber() {
        Shelf shelf = new Shelf();
        shelf.setShelfNumber(2);
        assertEquals(shelf.getShelfNumber(), 2);
    }

    @Test
    void setShelfNumber() {
        Shelf shelf = new Shelf();
        shelf.setShelfNumber(3);
        assertNotEquals(shelf.getShelfNumber(), 2);
        shelf.setShelfNumber(2);
        assertEquals(shelf.getShelfNumber(),2);
    }

    @Test
    void getSubject() {
        Shelf shelf = new Shelf();
        shelf.setSubject("education");
        assertEquals(shelf.getSubject(),"education");
    }

    @Test
    void setSubject() {
        Shelf shelf = new Shelf();
        shelf.setSubject("sci-fi");
        assertNotEquals(shelf.getSubject(), "education");
        shelf.setSubject("education");
        assertEquals(shelf.getSubject(), "education");
    }

    @Test
    void getBooks() {
        LocalDate localDate = LocalDate.ofYearDay(0000, 6);
        Book book1 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy","sci-fi",42,"Douglas Adams", localDate);
        Shelf shelf = new Shelf();
        HashMap<Book,Integer>book = new HashMap<>();
        book.put(book1,1);
        shelf.setBooks(book);
        assertEquals(shelf.getBooks(),book);
    }

    @Test
    void setBooks() {
        LocalDate localDate = LocalDate.ofYearDay(0000, 4);
        Book book1 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy","education",42,"Douglas Adams", localDate);
        Shelf shelf = new Shelf();
        HashMap<Book,Integer>book = new HashMap<>();
        book.put(book1,1);
        shelf.setBooks(book);
        assertEquals(shelf.getBooks(),book);
    }

}