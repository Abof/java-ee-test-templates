package pl.abof.test_template.arquillian.wf.jpa.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class Author {

	@Id
	private Long id;
	
	@Basic
	private String forname;
	
	@Basic
	private String surname;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String name) {
		this.forname = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + forname + " " + surname + "]";
	}

}
