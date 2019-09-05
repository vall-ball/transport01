package ru.vallball.transport01;

import java.time.LocalDate;

public class TourInsideCity {
	
	private Vehicle vehicle;
	private LocalDate date;
	private int passengers;
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	
	
	
}
