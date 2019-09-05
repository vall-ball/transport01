package ru.vallball.transport01;

import java.util.List;

public class Microbus extends Passenger{
	
	private List<Tour> tours;
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
