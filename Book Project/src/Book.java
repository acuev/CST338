/*
Title: Book.java
Date: 02/14/2021

 */
import java.time.LocalDate;
import java.util.Objects;

public class Book {

    public static final int ISBN_ = 0;
    public static final int TITLE_ = 1;
    public static final int SUBJECT_ = 2;
    public static final int PAGE_COUNT_ = 3;
    public static final int AUTHOR_ = 4;
    public static final int DUE_DATE_ =5;

    private String mISBN;
    private String mTitle;
    private String mSubject;
    private int mPageCount;
    private String mAuthor;
    private LocalDate mDueDate;

    public Book(String isbn, String title, String subject, int pagecount, String author, LocalDate date){
        mISBN = isbn;
        mTitle = title;
        mSubject = subject;
        mPageCount = pagecount;
        mAuthor = author;
        mDueDate = date;
    }
    public String getISBN() { return mISBN; }
    public void setISBN(String isbn) { mISBN = isbn; }
    public String getTitle() { return mTitle; }
    public void setTitle(String title) { mTitle = title; }
    public String getSubject(){
        return mSubject;
    }
    public void setSubject(String subject) {mSubject = subject; }
    public int getPageCount(){
        return mPageCount;
    }
    public void setPageCount(int pagecount) {mPageCount = pagecount; }
    public String getAuthor(){
        return mAuthor;
    }
    public void setAuthor(String author){mAuthor = author; }
    public LocalDate getDueDate(){return mDueDate;}
    public void setDueDate(LocalDate date) {mDueDate = date;}

    @Override
    public String toString() {
        return  mTitle + " " +
                "by " + mAuthor + " " +
                "ISBN: " + mISBN + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return mPageCount == book.mPageCount && Objects.equals(mISBN, book.mISBN) && Objects.equals(mTitle, book.mTitle) && Objects.equals(mSubject, book.mSubject) && Objects.equals(mAuthor, book.mAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mISBN, mTitle, mSubject, mPageCount, mAuthor);
    }
}
