package principal;
import modelo.HibernateUtil;


import org.hibernate.Query;
import org.hibernate.Session;

import domain.Event;
import domain.Question;

import java.util.*;

public class CreateEvents {
	public CreateEvents() {

	}
	private void createAndStroreEvent(Integer eventNumber,Integer questionNumber, String description,Date eventDate) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event evento= new Event();
		evento.setEventNumber(eventNumber);
		evento.setDescription(description);
		evento.setEventDate(eventDate);
		session.save(evento);
		session.getTransaction().commit();
	}
	private void createAndStoreQuestion(Integer queryNumber,String query, float betMinimum,  Event event) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Question q= new Question(query, betMinimum, event);
		q.setQuestionNumber(queryNumber);
		q.setQuestion(query);
		q.setBetMinimum(betMinimum);
		q.setEvent(event);
		session.persist(q);
		session.getTransaction().commit();
	}

	private void createAndStroreEvent(Integer questionNumber,String description,Date eventDate) throws Exception {
		if(eventDate==null)throw new Exception("Falta la fecha");
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q= session.createQuery("from QUESTIONS where questionNumber=:questionNumber");
		q.setParameter("questionNumber", questionNumber);
		List result=q.list();

		Event evento= new Event();
		try {			
			evento.setQuestions((Vector<Question>)result);
		}catch(Exception ex) {
			System.out.println("Error al crear instancia de EventoLogin: no existe usuario"
					+ex.toString());
		}

		evento.setDescription(description);
		evento.setEventDate(eventDate);
		session.save(evento);
		session.getTransaction().commit();
	}

	public void printObjMemBD(String desc, Question q) {
		System.out.println(desc); 
	}

	private List listaEvents() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("From Question").list();
		return result;
	}

	public static void main(String[] args) {
		CreateEvents e = new CreateEvents();
		/*
		e.createAndStoreQuestion(1, "quien ganara?", 1.5f);
		e.createAndStroreEvent("Quien ganara?","barcelona-madrid", new Date());
		e.createAndStroreEvent("Quien ganara?","reaSociedad-madrid", new Date());
		e.createAndStroreEvent("Quien ganara?","realSociedad-Barcelona", new Date());*/
		List preguntas=e.listaEvents();
		for(int i=0; i<preguntas.size(); i++) {
			System.out.println(preguntas.get(i).toString());
		}
	}


}
