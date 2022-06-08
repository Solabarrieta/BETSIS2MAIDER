package dataAccess;


import domain.*;
import modelo.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import configuration.UtilDate;

import java.util.*;

import javax.persistence.EntityManager;

public class HibernateDataAccess {
	protected static Session  session;

	public HibernateDataAccess() {

	}
	
public void initializeDB(){
		
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			session.save(ev1);
			session.save(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);
			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);			
			
			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public Event createAndStroreEvent(String description,Date eventDate){

		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event evento= new Event();
		try {			
			evento.setDescription(description);
			evento.setEventDate(eventDate);
			session.save(evento);
			session.getTransaction().commit();
		}catch(org.hibernate.PropertyValueException ex) {
			System.out.println("Error: falta fecha: ");
			evento=null;
			session.getTransaction().rollback();
			evento=null;
		}catch(Exception ex) {
			System.out.println("Error El evento no se ha podido crear: "+ ex.toString());
			evento=null;
		}
		return evento;
	}

	public boolean deleteEvent(Event ev) {
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		try {
			session.delete(ev);
			session.getTransaction().commit();
		}catch(Exception ex) {
			System.out.println("Error: "+  ex.toString());
			return false;
		}
		return true;
	}

	public List<Event> getEventsWithDate(Date d) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result;
		try {
			Query q =session.createQuery("from Event where eventDate= :d");
			q.setParameter("d", d);
			result=q.list();
			session.getTransaction().commit();
		}catch(Exception ex){
			System.out.println("Error: "+ ex.toString());
			result=null;
		}
		return result;
	}

	public Question createQuestion(Event event, String question, float betMinimum) {
		Session session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Question e= new Question();
		try {
			e.setEvent((Event)session.get(Event.class, event.getEventNumber()));
			e.setQuestion(question);
			e.setBetMinimum(betMinimum);
			session.save(e);
			session.getTransaction().commit();
		}catch(Exception ex) {
			System.out.println("Error: el evento no existe: "+ ex.toString());
			e=null;
		}
		return e;
	}

	public List<Question> getQuestions(){
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from QUESTION").list();
		session.getTransaction().commit();
		return result;
	}
	public List<Event> getAllEvents(){
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q= session.createQuery("from Event");
		List result= q.list();
		session.getTransaction().commit();
		return result;
	}
	
	public Vector<Event> getEvents(Date d){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result;
		try {
			Query q =session.createQuery("from Event where eventDate= :d");
			q.setParameter("d", d);
			result=q.list();
			session.getTransaction().commit();
		}catch(Exception ex){
			System.out.println("Error: "+ ex.toString());
			result=null;
		}
		Vector<Event> resultFinal= new Vector();
		Iterator i=result.iterator();
		while(i.hasNext()) {
			resultFinal.add((Event)i.next());
		}
		return resultFinal;
	}
	
	public Vector<Date> getEventsMonth(Date d){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result;
		Date d1= UtilDate.firstDayMonth(d);
		Date d2= UtilDate.lastDayMonth(d);
		try {
			Query q =session.createQuery("from Event where eventDate BETWEEN :d1 and :d2");
			q.setParameter("d1", d1);
			q.setParameter("d2", d2);
			result=q.list();
			session.getTransaction().commit();
		}catch(Exception ex){
			System.out.println("Error: "+ ex.toString());
			result=null;
		}
		Vector<Date> resultFinal= new Vector();
		Iterator i=result.iterator();
		Event e;
		while(i.hasNext()) {
			e= (Event)i.next();
			resultFinal.add(e.getEventDate());
		}
		return resultFinal;
	}
	
	
	public List<Question> getQuestionsEvent(Event event){
		session= HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria c= session.createCriteria(Question.class).createCriteria("event").
				add(Restrictions.eq("this", event));
		List<Question> result= c.list();
		session.getTransaction().commit();
		return result;
	}
	public static void main(String[] args) {
		HibernateDataAccess e= new HibernateDataAccess();
		e.initializeDB();
		System.out.println("Creacion del evento");
		Date d= new Date(2021-1900, 12-1, 9);
		Vector<Date> le=e.getEventsMonth(d);
		System.out.println(le);
		System.out.println("Evento de la fecha: "+d.toString());
		List<Event> event= e.getEventsWithDate(d);
	}
}
