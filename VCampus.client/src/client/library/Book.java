/**
* Created on 2015-8-28
 *图书类：
 *参数： bookID，bookname，bookborrow（用于记录图书是否被借阅），lendmanID借阅人id,lendDate借阅时间
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client.library;

import java.awt.Image;
import java.io.File;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * @author 陈石开
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Book {
	public String bookId;//书籍Id
	public String bookName;//书籍名
	public String bookAuthor;//书籍名
	public String bookPublisher;//书籍名
	public String bookPublishingDate;//书籍名
	public String bookDetails;//详细信息
	public int bookNum;//总数
	public int bookBorrowedNum = 0;//借阅数
	public File bookPic;
	public Book clone(){
		Book a = new Book();
		a.setBookId(this.bookId);
		a.setBookName(this.bookName);
		a.setBookAuthor(this.bookAuthor);
		a.setBookPublisher(this.bookPublisher);
		a.setBookPublishingDate(this.bookPublishingDate);
		a.setBookDetails(this.bookDetails);
		a.setBookNum(this.bookNum);
		a.setBookBorrowedNum(this.bookBorrowedNum);
		return a;
	}
	public void setBookPic(File icon){
		bookPic = icon;
	}
	public File getBookPic(){
		return bookPic;
	}
	public void updateBookInDB(String oldID){
		OperateDB opdb = new OperateDB();
		 opdb.updateBookInDB(this, oldID);
	}
	public Book(String bookid,String bookname, int num){
		bookId = bookid;
		bookName =bookname;
		bookNum = num;
	}
	public Book(){
	}
	public void setBookDetails(String details){
		bookDetails = details;
	}
	public void setBookBorrowedNum(int num){
		bookBorrowedNum = num;
	}
	public int getBookBorrowedNum(){
		return bookBorrowedNum;
	}
	public String getBookDetails(){
		return bookDetails;
	}
	public String getBookBorrowStatus(){
		return (bookNum - bookBorrowedNum) + "/" + bookNum;
	}
	public void setBookId(String bookid){
		bookId = bookid;
	}
	public String getBookId(){
		return bookId;
	}
	public int getAvailableBookNum(){
		return (bookNum - bookBorrowedNum);
	}
	public void setBookName(String bookname){
		bookName = bookname;
	}
	public String getBookName(){
		return bookName;
	}
	public void setBookAuthor(String author){
		bookAuthor = author;
	}
	public String getBookAuthor(){
		return bookAuthor;
	}
	public void setBookPublisher(String publisher){
		bookPublisher = publisher;
	}
	public String getBookPublisher(){
		return bookPublisher;
	}
	public void setBookPublishingDate(String date){
		bookPublishingDate = date;
	}
	public String getBookPublishingDate(){
		return bookPublishingDate;
	}
	public boolean setBookNum(int sum){
		if(bookBorrowedNum <= sum){
			bookNum = sum;
			return true;
		}
		return false;
	}
	public int getBookNum(){
		return bookNum;
	}
	public boolean addReader(){
		//有书则减一,添加读者信息,读者添加书信息
		return true;
	}
}

