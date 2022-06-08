package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import dataAccess.HibernateDataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


public class BetsBean {
	String question;
	private float minBet;
	private Date fecha;
	private ArrayList<Question> questions=new ArrayList<Question>();
	private ArrayList<Event> events=new ArrayList<Event>();
	Event evento;
	Question pregunta;

	HibernateDataAccess bl = new HibernateDataAccess();

	public BetsBean() {
		bl.initializeDB();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}


	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}


	public ArrayList<Event> getEvents() {
		return events;
	}


	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}


	public Event getEvento() {
		return evento;
	}


	public void setEvento(Event evento) {
		this.evento = evento;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}
	
	public float getMinBet() {
		return minBet;
	}
	
	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public Question getPregunta() {
		return pregunta;
	}

	public void setPregunta(Question question) {
		this.pregunta = question;
	}

	public void setEvents(Date d){
		events.clear();

		Vector<Event> eventsVector = bl.getEvents(d);
		if(eventsVector.size()<0) {

		}else {
			Iterator<Event> i= eventsVector.iterator();
			while(i.hasNext()) {
				events.add(i.next());
			}
		}
	}

	public void listener(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("El evento seleccionado es:"+evento.getDescription()));
	}

	public void onDateSelect(SelectEvent event) {

		setEvents((Date)event.getObject());


		//FacesContext fc= FacesContext.getCurrentInstance();
		//fc.addMessage(null,new FacesMessage("Fecha escogida: "+event.getObject().toString()));
	}

	
	public void setQuestions(Event ev){
		questions.clear();
		List<Question> questionsVector=ev.getQuestions();
		if(questionsVector.size()<0) {
		}else {
			Iterator<Question> i = questionsVector.iterator();
			while(i.hasNext()) {
				questions.add(i.next());
			}
		}
	}
	public void onRowSelect(SelectEvent eve) {
		Event even=(Event)eve.getObject();
		FacesContext fc= FacesContext.getCurrentInstance();	
		setQuestions((Event) eve.getObject());
		fc.addMessage(null,new FacesMessage("Evento escogido: "+ even.toString()));
	}

	public void createQuestion(){
		FacesContext fc= FacesContext.getCurrentInstance();	
		fc.addMessage(null,new FacesMessage("Ha entrado aqui "));
		try {			
			System.out.println("Evento: "+evento+ " Question:"+question+" Minima apuesta"+minBet);
			bl.createQuestion(evento, question, minBet);
			fc.addMessage(null,new FacesMessage("pregunta creada "));
			
		}catch(Exception e) {
			fc.addMessage(null,new FacesMessage("No se ha podido crear la pregunta"));
		}
	}






}






