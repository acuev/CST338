/*
Title: Shelf.java
Date: 02/21/2021
Description: This is the shelf class where the book where be stored.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Shelf {
    public static final int SHELF_NUMBER_= 0;
    public static final int SUBJECT_ = 1;

    private int shelfNumber;
    private String subject;
    private HashMap<Book, Integer> books;


    public Shelf(){
        books = new HashMap<>();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public HashMap<Book, Integer> getBooks()   {
        return books;
    }

    public void setBooks( HashMap<Book,Integer> books)
    {
        this.books = books;
    }

    @Override
    public String toString() {
        return shelfNumber +
                ": " + subject ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelf shelf = (Shelf) o;
        return getShelfNumber() == shelf.getShelfNumber() && Objects.equals(getSubject(), shelf.getSubject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShelfNumber(), getSubject());
    }

    public int getBookCount(Book book)
    {
        boolean result = books.containsKey(book);
        int number;
        if(result){
            number = books.get(book);
            return number;
        }
        else {
            return -1;
        }
    }
    public Code addBook(Book book){
        int number = 0;
        boolean result = books.containsKey(book);
        if(result) {
            number = books.get(book);
            number++;
            books.replace(book,number);
            return Code.SUCCESS;
        }
        else{
            if(book.getSubject().equals(subject)){//book.getSubject().equals("sci-fi") || book.getSubject().equals("education") || book.getSubject().equals("Adventure")){
                books.put(book,1);

                return Code.SUCCESS;
            }
            else{
                return Code.SHELF_SUBJECT_MISMATCH_ERROR;
            }
       }

    }
    public Code removeBook(Book book){
        boolean result = books.containsKey(book);
        if(!result){
            System.out.println(book.getTitle() + " is not on shelf " + book.getSubject());
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }
        else{
            if(getBookCount(book) > 0) {
                int temp = getBookCount(book);
                temp--;
                books.replace(book,temp);
                System.out.println(book + " successfully removed from shelf " + book.getSubject());
                return Code.SUCCESS;
            }
            else{
                System.out.println("No copies of " + book.getTitle() + " remain on shelf " + book.getSubject());
                return Code.BOOK_NOT_IN_INVENTORY_ERROR;
            }
        }

    }
    public String listBooks(){
        int i = 0;
        for(Map.Entry<Book, Integer> pair: books.entrySet()){
            i+=pair.getValue();
        }
        System.out.println( i + " books on shelf: " + shelfNumber + " : " + subject );
        for(Map.Entry<Book, Integer> pair: books.entrySet()){
            System.out.println(("" + pair.getKey() + " " + pair.getValue()));
        }
        return "";
    }
}
