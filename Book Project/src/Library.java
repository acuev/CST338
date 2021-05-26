/*
Title: Library.java
Date: 3/12/2021
Description: This is the final class for the library project. We will use
the book, shelf, and reader class to create our own library where we can store
books, readers can checkout books, and we can add shelves.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Library {

    public static final int LENDING_LIMIT = 5;
    private String name;
    private final int erase = 0;
    private static int libraryCard;
    private List<Reader> readers = new ArrayList<>();
    private HashMap<String, Shelf> shelves = new HashMap<>();
    private HashMap<Book, Integer> books = new HashMap<>();

    public Library(String name){
        this.name = name;
    }
    public Code init(String name){//Done
        Scanner scanner = null;
        Scanner temp = null;
        File fil = new File(name);

        try{
            scanner = new Scanner(fil);
            temp = new Scanner(new File(name));//temporary scanner to go through the file to find the number of books, shelves, and reader

        }catch (FileNotFoundException e){
            System.out.println("Could not find the file" + e);
            return Code.FILE_NOT_FOUND_ERROR;
        }

           int tempHolder, bookNum = 0, shelfNum = 0, readerNum = 0;

             if(temp.hasNext()){//got through scanner
                 String tempBookNum = temp.nextLine();
                 tempHolder = convertInt(tempBookNum, Code.BOOK_COUNT_ERROR);//get book number
                 bookNum = tempHolder;//store it in bookNum
                for(int x= 0; x < tempHolder; x++){
                    temp.nextLine();
                }
            }
            if(temp.hasNext()){//go through next section in scanner
                String tempShelfNum = temp.nextLine();
                tempHolder = convertInt(tempShelfNum, Code.SHELF_NUMBER_PARSE_ERROR);//get the shelve number
                shelfNum = tempHolder; // store number in in the shelfNum
                for(int x = 0; x < tempHolder ; x++) {
                    temp.nextLine();
                }
            }
            if(temp.hasNext())
            {
                String tempReaderNum = temp.nextLine();
                tempHolder = convertInt(tempReaderNum, Code.READER_COUNT_ERROR);
                readerNum = tempHolder;//store the reader number in reader num
                for(int x = 0; x<tempHolder;x++){
                    temp.nextLine();
                }
            }

            scanner.nextLine();//skips the first line since its the number
            initBooks(bookNum,scanner);//pass the scanner and number to parse
            listBooks();//lists book in library

            scanner.nextLine();//skips the line since its the number of shelves
            initShelves(shelfNum,scanner);//pass the scanner and number to parse
            listShelves(true);//list shelves

            scanner.nextLine();//skips
            initReader(readerNum,scanner);//pass the scanner and number to parse
            //close scanner
            scanner.close();
            temp.close();

           return Code.SUCCESS;

    }
    private Code initBooks(int bookCount, Scanner scan){//Done
        if(bookCount < 1){//check if books count is less than 1
            return Code.LIBRARY_ERROR;
        }
        System.out.println("parsing " + bookCount + " books");
        if(scan.hasNext()){//parsing books
           for(int x = 0; x < bookCount;x++){
               String str = scan.nextLine();
               System.out.println("parsing book: " + str);
               String[] strArr = str.split(",");//splits string
               String ISBN = strArr[0];//stores the first array for isbn
               String title = strArr[1];
               String subject = strArr[2];
               int pageNum = Integer.parseInt(strArr[3]);
               if(pageNum <=0 ){//check the page number to not be less than or equal to zero
                   return Code.PAGE_COUNT_ERROR;
               }
               String author = strArr[4];
               LocalDate local = convertDate(strArr[5], Code.DATE_CONVERSION_ERROR);
               Book book = new Book(ISBN,title, subject, pageNum, author,local);//LocalDate.of(1970,1, 1));
               addBook(book);//add book to library
           }
        }
        System.out.println("SUCCESS");
        return Code.SUCCESS;
    }
    private Code initShelves(int shelfCount, Scanner scan){//Done
        if(shelfCount < 1){//check that shelf is not less than 0
            return Code.SHELF_COUNT_ERROR;
        }
        System.out.println("parsing " + shelfCount + " shelves");
        if(scan.hasNext()){//parsing shelves
            for(int x = 0; x < shelfCount; x++)
            {
                String str = scan.nextLine();
                System.out.println("Parsing Shelf: " + str );
                String[] shelfArr = str.split(",");//split
                addShelf(shelfArr[1]);//add shelf
            }
        }
        for(Map.Entry<String, Shelf> pair: shelves.entrySet())
        {//loop through the shelves and add the books that matches the subjects in the shelf
            for(Map.Entry<Book, Integer> pairs : books.entrySet()) {
                for (int i = 0; i < pairs.getValue(); i++)
                    addBookToShelf(pairs.getKey(),pair.getValue());
            }
        }
        System.out.println("SUCCESS");
        return Code.SUCCESS;
    }
    private Code initReader(int readerCount, Scanner scan){//DONE
        if(readerCount <= 0){//check if the readerCount is less than zero
            return Code.READER_COUNT_ERROR;
        }
        String[] readerARR = new String[readerCount];
        if(scan.hasNext()){//parsing
            for(int x = 0; x < readerCount; x++)
            {
                String str = scan.nextLine();
                readerARR[x] = str;//store it in an array
            }
        }
        for(int i = 0; i < readerCount; i++){//loop through the array of readers
            String[] temporary = readerARR[i].split(",");//split
            int idNum = Integer.parseInt(temporary[0]);
            Reader reader = new Reader(idNum,temporary[1],temporary[2]);
            readers.add(reader);//add reader to the list of readers
            int getBook = 2, getDate = 5; //used as the index of the array
            int bookParse = Integer.parseInt(temporary[3]);
            int n = bookParse;
            while (bookParse > 0){ //the books the reader has checked out
                bookParse--;
                getBook += 2;
                Book book = getBookByISBN(temporary[getBook]);//get book by isbn
                if (book == null){
                    System.out.println("ERROR");
                } else{
                    LocalDate temp = convertDate(temporary[getDate], Code.DUE_DATE_ERROR);
                    checkOutBook(reader, book);//check out book
                    System.out.println("SUCCESS");
                    if(temporary.length-4 != n*2){
                        bookParse--;
                    }
                }
            }
        }
        System.out.println("SUCCESS");
        return Code.SUCCESS;
    }
    public Code addBook(Book newBook){//DONE
        boolean result = books.containsKey(newBook);//check if the book passed is in the hashmap of books
        if(shelves.containsKey(newBook.getSubject())){//checks if there is a shelf with a matching subject
            addBookToShelf(newBook, shelves.get(newBook.getSubject()));//adds book to that shelf
            return Code.SUCCESS;
        }
        int number;
        if(result){//if there was a book in the hashmap of books, it increments the count
            number = books.get(newBook);
            number++;
            books.replace(newBook,number);
            System.out.println(number + " copies of " + newBook + " in the stacks.");
        } else{//if there was no book in the hashmaps of books, we add the book and number
            books.put(newBook,1);
            System.out.println(newBook + " added to the stacks.");
        }
        System.out.println("No shelf for " + newBook.getSubject() + " books.");
        return Code.SHELF_EXISTS_ERROR;
    }

    public Code returnBook(Reader reader,Book book){
        if(book == null || reader == null){
            return null;
        }
        boolean result = reader.hasBook(book);//Check if reader had book
        if(result) {//if reader has book remove the book
            System.out.println(reader.getName() + " is returning " + book);
            int getCode = reader.removeBook(book).getCode();
            if(getCode == 0){
                returnBook(book);
                return Code.SUCCESS;
            }else{
                System.out.println("Could not return " + book);
                return Code.UNKNOWN_ERROR;
            }
        } else{//if reader does not have the book, prints this
            System.out.println(reader.getName() + " doesn't have " + book + " checked out.");
            return Code. READER_DOESNT_HAVE_BOOK_ERROR;
        }
    }
    public Code returnBook(Book book){//DONE
        if(book == null){
            return null;
        }
        boolean result = shelves.containsKey(book.getSubject());//check if shelf contains the book subject
        if(!result){//if it does not, print this out
            System.out.println("No shelf for " + book.getSubject());
            return Code.SHELF_EXISTS_ERROR;
        }else{//if it does, we add the book to the shelf
            addBook(book);
            return Code.SUCCESS;
        }
    }
    private Code addBookToShelf(Book book, Shelf shelf){ //DONE
        if(shelf.getSubject().equals(book.getSubject())){//Check if the subject matches a shelf with the same subject
            shelf.addBook(book);//adds book to shelf
            shelves.replace(shelf.getSubject(), shelf);//adds them to the hashmap of shelves
            System.out.println(book + " added to shelf " + shelf.toString());
            return Code.SUCCESS;
        } else{ //if books does not match the shelf subject, returns this
            return Code.SHELF_SUBJECT_MISMATCH_ERROR;
        }
    }

    public int listBooks(){//DONE
        int i = 0;
        //sort the books in decreasing order
        LinkedHashMap<Book, Integer> reverseSort = new LinkedHashMap<>();
        books.entrySet().stream().sorted(Map.Entry.comparingByValue
                (Comparator.reverseOrder())).forEachOrdered(x-> reverseSort.put(x.getKey(),x.getValue()));
        books = reverseSort;//stores them in the hashmap of books
        for(Map.Entry<Book, Integer> pair: books.entrySet()){//prints out list of all the books in the library
            System.out.println("" + pair.getValue() + " copies of "+ pair.getKey());
            i+=pair.getValue();//get the total number of books
        }
        return i;//return total number of books
    }

    public Code checkOutBook(Reader reader, Book book){//DONE
        if(reader == null){
            return Code.UNKNOWN_ERROR;
        }
        boolean result = readers.contains(reader);//check if there is a reader that matches the reader in the hashmap of readers
        if(result){//if there is
            int bookCount= reader.getBookCount();
            if(bookCount > LENDING_LIMIT){//checks if the reader hasn't past the lending limit, 5
                System.out.println(reader.getName() + " has reached the lending limit, " + LENDING_LIMIT);
                return Code.BOOK_LIMIT_REACHED_ERROR;
            }
            if(!books.containsKey(book)){//checks if the book is in the library
                System.out.println("ERROR: could not find " + book.getTitle());
                return Code.BOOK_NOT_IN_INVENTORY_ERROR;
            }
            if(!shelves.containsKey(book.getSubject())){//check if there is a shelf with the same subject as the book subject
                System.out.println("No shelf for " + book.getSubject() + " books!");
                return Code.SHELF_EXISTS_ERROR;
            }
            int bookNum = 0;//used for getting the book count number in the shelf
            Shelf delete = new Shelf();
            HashMap<Book, Integer> temps;
            for(Map.Entry<String, Shelf> temp: shelves.entrySet()){
                temps = temp.getValue().getBooks();
                if(temps.containsKey(book)){//get the book count
                    delete = temp.getValue();
                    bookNum = temp.getValue().getBookCount(book);
                }
            }
            if(bookNum < 1)//checks if the shelf has less than 1 copy of the book
            {
                System.out.println("ERROR: no copies of " + book + " remains.");
                return Code.BOOK_NOT_IN_INVENTORY_ERROR;
            }
            int returns = reader.addBook(book).getCode(); //get the code
            if(returns == 1) {//checks if adding the book was successful
                System.out.println("Couldn't checkout " + book.getTitle());
            }
            int s = delete.removeBook(book).getCode();//get the code
            shelves.replace(book.getSubject(), delete);//update the shelves hashmap
            if(s==0){//check if removing the book was successful
                System.out.println(book + " checked out successfully");
            }
        }else{ //if there is not a reader that matches, print out this
            System.out.println(reader.getName() + " doesn't have an account here.");
            return Code.READER_NOT_IN_LIBRARY_ERROR;
        }
        return Code.SUCCESS;
    }
    public Book getBookByISBN(String isbn){//DONE
        Book book;
        for(Map.Entry<Book,Integer> pair: books.entrySet()){//loop
            book = pair.getKey();
            if(book.getISBN().equals(isbn)){//check if theres a book that matches an isbn of the book passed
                return book;//if there is returns the book
            }
        }//if there is not book that matches the isbn, return null
        System.out.println("ERROR: Could not find a book with isbn: " + isbn);
        return null;
    }

    public Code listShelves(boolean code){//DONE
        if(!code){//if boolean is false
            System.out.println(shelves.toString());//calls the toString of each shelf
        }
        else{//if true, lt lists the books in the shelf
            for(Map.Entry<String, Shelf> pair: shelves.entrySet()){
                pair.getValue().listBooks();
                System.out.println(" ");
            }
        }
        return Code.SUCCESS;
    }
    public Code addShelf(String shelfSubject){//DONE
        Shelf shelf = new Shelf();//create shelf
        shelf.setShelfNumber(shelves.size()+1);//set the shelf number to shelf size + 1
        shelf.setSubject(shelfSubject);//set the subject
        addShelf(shelf);//call the add shelf method
        return Code.SUCCESS;
    }

    public Code addShelf(Shelf shelf){//DONE
        boolean result = shelves.containsValue(shelf);//check if there is a shelf that matches the shelf
        if(result){//if true, prints out this
            System.out.println("ERROR: Shelf already exists " + shelf);
            return Code.SHELF_EXISTS_ERROR;
        }
        shelf.setShelfNumber(shelves.size()+1);
        shelves.put(shelf.getSubject(), shelf);//set the shelf pass into the shelves hashmap
        for(Map.Entry<Book, Integer> pair: books.entrySet()){
            for(int i = 0 ; i < pair.getValue();i++)
                    addBookToShelf(pair.getKey(),shelf);//add the books with the matching subject to the shelf
        }
        return Code.SUCCESS;
    }
    public Shelf getShelf(Integer shelf){//DONE
        //go through the shelves hashmap to see if it matches the shelf number
        for(Map.Entry<String, Shelf> temp: shelves.entrySet()){
            if(shelf.equals(temp.getValue().getShelfNumber()))
                return temp.getValue();//returns shelve
        }//if there is no shelf with the same shelf number
        System.out.println("No shelf for " + shelf + " found.");
        return null;//return null
    }
    public Shelf getShelf(String shelf){//DONE
        boolean result = shelves.containsKey(shelf);//check if there is a shelf with the same subject
        Shelf temp;
        if(result){//if true, returns that shelf
            temp = shelves.get(shelf);
            return temp;
        }//if false, return null
        System.out.println("No shelf for " + shelf + " books");
        return  null;
    }

    public int listReaders(){//DONE
        for(Reader temp: readers){//prints out the toString of the readers in the hashmap
            System.out.println(temp.toString());
        }
        return readers.size();//returns the total number of readers
    }
    public int listReaders(boolean reader){//DONE
        if(reader){//if the boolean is true
            for(Reader value: readers){//prints out all the readers and their books
                System.out.println(value.getName() + " (#" + value.getCardNumber()+
                        ") has the following books: " + value.getBooks());
            }
        } else{//if false, prints out the toString of the readers in the hashmap of readers
            for(Reader temp: readers){
                System.out.println(temp.toString());
            }
        }
        return readers.size();//returns the total number of readers
    }
    public Reader getReaderByCard(int reader){//DONE
        for(Reader value: readers){//loop through to get the reader who matches the integer passed
            if(value.getCardNumber() == reader) {
                System.out.println("Returning Reader " + value.toString());
                return value;//returns the reader
            }
        }//if there was no reader with the card number, prints out this
        System.out.println("Could not find a reader with card #" + reader);
        return null;
    }
    public Code addReader(Reader reader){//DONE
        boolean result = readers.contains(reader);//checks if the reader is already in the hashmap of reader
        if(result){//if the reader exists in the hashmap of reader, print out this
            System.out.println(reader.getName() + " already has an account!");
            return Code.READER_ALREADY_EXISTS_ERROR;
        }
        else{//if there if no reader that exists in the hashmap of readers
            for (Reader value : readers) {//check to see if there is another reader with the same card number
                if (value.getCardNumber() == reader.getCardNumber()) {//prints out this
                    System.out.println(value.getName() + " and " + reader.getName() + " have the same card number");
                    return Code.READER_CARD_NUMBER_ERROR;
                }
            }
            readers.add(reader);//adds the reader to the hashmap of readers
            System.out.println(reader.getName() + " added to the library!");
            if(reader.getCardNumber() > libraryCard)//check if the library card number is bigger than the library card field
                libraryCard = reader.getCardNumber();//set it to the reader card number
            return Code.SUCCESS;
        }
    }
    public Code removeReader(Reader reader){//DONE
        boolean result = readers.contains(reader);//check if reade exist in hashmap
        if(result) {//if reader exists
            if (reader.getBookCount() > 0) {//check if the reader has any books
                System.out.println(reader.getName() + " must return all books!");
                return Code.READER_STILL_HAD_BOOKS_ERROR;
            }//if reader has no books, we remove the reader
            readers.remove(reader);
            return Code.SUCCESS;
        } else {//if reader does not exists in hashmap, print out this
                System.out.println(reader.getName() + " is not part of the library.");
                return Code.READER_NOT_IN_LIBRARY_ERROR;
            }
    }
    public int convertInt(String recordCountString, Code code){//Done
        int num;
        try {
             num = Integer.parseInt(recordCountString);//convert the string to an integer
        } catch (NumberFormatException e){
            System.out.println("Value which caused error: " + recordCountString);
            System.out.println("Error message: " + code.getMessage());
            return code.getCode();
        }
        if(code.equals(Code.BOOK_COUNT_ERROR) && num <= 0) {
            System.out.println("Error: Could not read number of books");
            return -2;
        }
        else if(code.equals(Code.PAGE_COUNT_ERROR) && num<=0){
            System.out.println("Error: Could not parse page count");
            return -8;
        }
        else if(code.equals(Code.DATE_CONVERSION_ERROR) && num<=0) {
            System.out.println("Error: Date conversion error");
            return -101;
        }
        else if(code.equals(Code.READER_COUNT_ERROR) && num <= 0){
            System.out.println("Error: Could not read number of readers");
            return -4;
        }
        else if(code.equals(Code.SHELF_NUMBER_PARSE_ERROR) && num <= 0){
            System.out.println("Error: Could not read number of shelves");
            return -61;
        }
        else if(num > 0){
            return num;
        } else {
            System.out.println("Error: Unknown conversion error");
            return -999;
        }
    }

    //NEEDS WORK
    public LocalDate convertDate(String date, Code errorCode) {
        if (date.equals("0000"))//if date is 0000
            return LocalDate.of(1970, 1, 1);
        String[] arrOfStr = date.split("-", 4);//parses the date
        if (arrOfStr.length != 3) {
            System.out.println("ERROR: date conversion error, could not parse " + date);
            System.out.println("Using default date (01 - jan-1970");
            return LocalDate.of(1970, 1, 1);
        }
        for (int i = 0; i < 3; i++) {
            if(Integer.parseInt(arrOfStr[0]) <= 0){
            System.out.println("Error converting Day");
            System.out.println("Using default date (01 - jan- 1970)");
            return LocalDate.of(1970,1,1);
            }
            if(Integer.parseInt(arrOfStr[1]) <= 0){
                System.out.println("Error converting Month");
                System.out.println("Using default date (01 - jan- 1970)");
                return LocalDate.of(1970,1,1);
            }
            if(Integer.parseInt(arrOfStr[2]) <= 0){
                System.out.println("Error converting Year");
                System.out.println("Using default date (01 - jan- 1970)");
                return LocalDate.of(1970,1,1);
            }
        }
        return LocalDate.parse(date);
    }
    public int getLibraryCardNumber(){//DONE
        return libraryCard+1;//returns the libraryCard
    }
    private Code errorCode(int codeNumber){//DONE
        for(Code c: Code.values()){
           if(c.getCode() == codeNumber){
               return c;
           }
        }return Code.UNKNOWN_ERROR;
    }
}
