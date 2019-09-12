package ru.vallball.transport01;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("microbus")
public class Microbus extends Bus{
	
	@OneToMany(
	        mappedBy = "bus",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JsonManagedReference
	private List<Tour> tours;
	@OneToMany(
	        mappedBy = "bus",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )

	private List<TourInsideCity> insideTours;
	
	public List<Tour> getTours() {
		return tours;
	}
	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
	public List<TourInsideCity> getInsideTours() {
		return insideTours;
	}
	public void setInsideTours(List<TourInsideCity> insideTours) {
		this.insideTours = insideTours;
	}
	
}
