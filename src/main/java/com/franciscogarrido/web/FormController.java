package com.franciscogarrido.web;

import java.util.List;
import java.util.UUID;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.franciscogarrido.entities.Reserva;
import com.franciscogarrido.repositories.ReservaRepository;

@Named("formController")
@ViewScoped
@ManagedBean
public class FormController {

	@Autowired
	ReservaRepository reservaRepository;

	private Reserva reserva;


	private  String token;

	@PostConstruct
	public void init() {
		reserva = new Reserva();
	}

	public void submit() {
		if (reserva.getName() != null && reserva.getAge() !=null && reserva.getEmail() != null
				&& reserva.getGender() != null	&& reserva.getAddress() != null ) {
			reserva.setToken(UUID.randomUUID().toString());

			reservaRepository.save(reserva);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "??error."));
		}
	}

	public void reset() {
		reserva = new Reserva();
	}


	public void reset2() {
		if (token!= null ) {
			reserva.setToken(UUID.randomUUID().toString());

			List<Reserva> reservaq = reservaRepository.findAll();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "??error."));
		}
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
