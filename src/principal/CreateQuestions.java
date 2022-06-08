package principal;
import modelo.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

import domain.Event;
import domain.Question;

import java.util.*;

public class CreateQuestions {
	public CreateQuestions() {
		
	}
	private void createAndStroreQuestion(Integer queryNumber,String query, float betMinimum,  Event event) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Question q= new Question(query, betMinimum, event);
		q.setQuestionNumber(queryNumber);
		q.setQuestion(query);
		q.setBetMinimum(betMinimum);
		q.setEvent(event);
		session.save(q);
		session.getTransaction().commit();
	}
	private void InsertQuestionIntoEvent(Question q, Event ev) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		
	}
	
	private void createAndStroreQuestion(String query, float betMinimum,  Event event) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q= session.createQuery("from Event where description=:description and eventDate=:eventDate");
		q.setParameter("description", event.getDescription());
		q.setParameter("eventDate", event.getEventDate());
		List result=q.list();
		

		Question qu= new Question();
		try {			
			qu.setEvent(((Event)result.get(0)));
		}catch(Exception ex) {
			System.out.println("Error al crear instancia de Event: no hay ninguna pregunta"
					+ex.toString());
		}
		qu.setQuestion(query);
		qu.setBetMinimum(betMinimum);
		qu.setEvent(event);
		session.save(qu);
		session.getTransaction().commit();
	}
	private void createAndStroreEvent(String description,Date eventDate) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event evento= new Event();
		evento.setDescription(description);
		evento.setEventDate(eventDate);
		session.save(evento);
		session.getTransaction().commit();
	}
	
	private List listaQuestions() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("From Question").list();
		return result;
	}
	public static void main(String[] args) {
		Event ev= new Event();
		CreateQuestions e = new CreateQuestions();
		e.createAndStroreEvent("quien ganara?", new Date());
		e.createAndStroreQuestion("pregunta", 1.5f,ev );
		e.createAndStroreQuestion("pregunta2", 2.5f,ev );
		e.createAndStroreQuestion("pregunta3", 3.5f,ev );
		List preguntas=e.listaQuestions();
		for(int i=0; i<preguntas.size(); i++) {
			System.out.println(preguntas.get(i).toString());
		}
	}
}
