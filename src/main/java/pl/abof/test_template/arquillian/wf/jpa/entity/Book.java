package pl.abof.test_template.arquillian.wf.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(
			name="Book.ALL_BOOKS", 
			query = "SELECT b FROM Book b"
		)
})
@Entity
public class Book {

	@Id
	private Long id;
	
	@Basic
	private String title;

	@ManyToOne
	@JoinColumn(name="AUTHOR_ID") //, referencedColumnName = "id"
	private Author author;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author+"]";
	}
}
