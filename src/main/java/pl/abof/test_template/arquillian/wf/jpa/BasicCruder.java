package pl.abof.test_template.arquillian.wf.jpa;

import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import pl.abof.test_template.arquillian.wf.jpa.entity.Book;

@Stateless
@LocalBean
public class BasicCruder {

	@PersistenceContext(unitName = "BasicPU")
	EntityManager em;
	
	
	public Collection<Book> getThemAll() {
		Query query = em.createNamedQuery("Book.ALL_BOOKS");
		List<Book> resultList = query.getResultList();
		return resultList;
	}
	
	Logger log = Logger.getLogger(BasicCruder.class);
	
}
