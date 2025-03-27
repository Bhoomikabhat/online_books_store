package com.assighnment.online_book_store.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Books
{
	@Id
	private int id;
	private String title;
	private String author;
    private int price;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + "]";
	}
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
