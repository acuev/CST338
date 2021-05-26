/*
Title: LibraryTest.java
Description: This is to test the function in the Library.java class.
 */
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addBook() {
        Library temp = new Library("CSUMB");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        temp.addBook(book);
        assertEquals(temp.addBook(book), Code.SHELF_EXISTS_ERROR);
        Shelf newShelf = new Shelf();
        newShelf.setSubject("GrimDark");
        temp.addShelf(newShelf);
        assertEquals(temp.addBook(book), Code.SUCCESS);
    }

    @Test
    void returnBook_Book() {
        Library temp = new Library("Temp");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        temp.addBook(book);
        assertEquals(temp.returnBook(book), Code.SHELF_EXISTS_ERROR);
    }

    @Test
    void returnBook_ReaderAndBook() {
        Library temp = new Library("Temp");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        Reader reader = new Reader(1,"Abi","123-0909");
        assertEquals(temp.addReader(reader), Code.SUCCESS);
        assertEquals(temp.returnBook(reader,book), Code.READER_DOESNT_HAVE_BOOK_ERROR);
    }

    @Test
    void listBooks() {
        Library temp = new Library("Temp");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        temp.addBook(book);
        temp.addBook(book);
        temp.addBook(book);
        assertEquals(temp.listBooks(),3);
    }

    @Test
    void checkOutBook() {
        Library temp = new Library("Temp");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        Reader reader = new Reader(1,"Abi","123-0909");
        temp.addBook(book);
        assertEquals(temp.checkOutBook(reader,book), Code.READER_NOT_IN_LIBRARY_ERROR);
        temp.addReader(reader);
        reader.addBook(book);
        assertEquals(temp.checkOutBook(reader,book), Code.SHELF_EXISTS_ERROR);

    }

    @Test
    void getBookByISBN() {
        Library temp = new Library("Temp");
        Book book = new Book("12345","Best Served Cold","GrimDark",235,"Joe Abercrombie", LocalDate.now());
        temp.addBook(book);
        assertEquals(temp.getBookByISBN("12345"), book);
    }

    @Test
    void listShelves() {
        Library temp = new Library("Temp");
        temp.addShelf("Sci-fi");
        temp.addShelf("Adventure");
        temp.addShelf("Science");
        assertEquals(temp.listShelves(true), Code.SUCCESS);
    }

    @Test
    void addShelf_subject() {
        Library temp = new Library("Temp");
        assertEquals(temp.addShelf("Science"), Code.SUCCESS);
    }

    @Test
    void addShelf_Shelf() {
        Library temp = new Library("CSUMB");
        Shelf newShelf = new Shelf();
        newShelf.setSubject("GrimDark");
        newShelf.setShelfNumber(1);
        assertEquals(temp.addShelf(newShelf), Code.SUCCESS);
    }

    @Test
    void getShelf_Integer() {
        Library temp = new Library("CSUMB");
        Shelf newShelf = new Shelf();
        newShelf.setSubject("GrimDark");
        newShelf.setShelfNumber(1);
        temp.addShelf(newShelf);
        assertEquals(temp.getShelf(1), newShelf);
    }

    @Test
    void getShelf_String() {
        Library temp = new Library("CSUMB");
        Shelf newShelf = new Shelf();
        newShelf.setSubject("GrimDark");
        newShelf.setShelfNumber(1);
        temp.addShelf(newShelf);
        assertEquals(temp.getShelf("GrimDark"), newShelf);
    }

    @Test
    void listReaders() {
        Library temp = new Library("CSUMB");
        Reader reader = new Reader(1,"Abi","123-0909");
        Reader reader1 = new Reader(2,"Temp","535-0909");
        temp.addReader(reader);
        temp.addReader(reader1);
        assertEquals(temp.listReaders(),2);
    }

    @Test
    void listReaders_Boolean() {
        Library temp = new Library("CSUMB");
        Reader reader = new Reader(1,"Abi","123-0909");
        Reader reader1 = new Reader(2,"Temp","535-0909");
        temp.addReader(reader);
        temp.addReader(reader1);
        assertEquals(temp.listReaders(true), 2);
    }

    @Test
    void getReaderByCard() {
        Library temp = new Library("CSUMB");
        Reader reader = new Reader(1,"Abi","123-0909");
        Reader reader1 = new Reader(2,"Temp","535-0909");
        temp.addReader(reader);
        temp.addReader(reader1);
        assertEquals(temp.getReaderByCard(1), reader);
        assertEquals(temp.getReaderByCard(2), reader1);
        assertNull(temp.getReaderByCard(3));
    }

    @Test
    void addReader() {
        Library temp = new Library("CSUMB");
        Reader reader = new Reader(1,"Abi","123-0909");
        Reader reader1 = new Reader(1,"Temp","535-0909");
        assertEquals(temp.addReader(reader), Code.SUCCESS);
        assertEquals(temp.addReader(reader), Code.READER_ALREADY_EXISTS_ERROR);
        assertEquals(temp.addReader(reader1), Code.READER_CARD_NUMBER_ERROR);
    }

    @Test
    void removeReader() {
        Library temp = new Library("CSUMB");
        Reader reader = new Reader(1,"Abi","123-0909");
        Reader reader1 = new Reader(1,"Temp","535-0909");
        temp.addReader(reader);
        assertEquals(temp.removeReader(reader), Code.SUCCESS);
        assertEquals(temp.removeReader(reader1), Code.READER_NOT_IN_LIBRARY_ERROR);
    }

    @Test
    void convertInt() {
        Library temp = new Library("CSUMB");
        String one = "1";
        assertEquals(temp.convertInt(one, Code.SUCCESS), 1);
        assertEquals(temp.convertInt("A",Code.UNKNOWN_ERROR),-999);
    }

    @Test
    void convertDate() {
        Library temp = new Library("CSUMB");
        assertEquals(temp.convertDate("0000", Code.SUCCESS), LocalDate.of(1970,1, 1));
        assertEquals(temp.convertDate("2000-10-12",Code.SUCCESS), LocalDate.of(2000,10,12));
        assertEquals(temp.convertDate("2000-10-00", Code.SUCCESS), LocalDate.of(1970,1,1));
    }

    @Test
    void getLibraryCardNumber() {
        Library temp = new Library("CSUMB");
        assertEquals(temp.getLibraryCardNumber(), 1);
    }
}