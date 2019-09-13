package ru.vallball.transport01;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tours")
public class TourRestController {

	@Autowired
	TourRepository tourRepository;

	private static final Logger logger = LoggerFactory.getLogger(BusRestController.class);
	
	@PostMapping
	public ResponseEntity<Object> createTour(@RequestBody Tour tour) {
		tourRepository.save(tour);
		logger.info("ИД тура: " + String.valueOf(tour.getId()));
		return new ResponseEntity<>("Tour is created successfully", HttpStatus.CREATED);
	}

	@GetMapping
	@ResponseBody
	public List<Tour> listTour() {
		return tourRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity oneTour(@PathVariable(value = "id") Long id) {
		try {
			Tour tour = tourRepository.findById(id).get();
			ResponseEntity<Tour> answer = new ResponseEntity<>(tour, HttpStatus.ACCEPTED);
			return answer;
		} catch (NoSuchElementException e) {
			logger.info("Tour not found");
			return new ResponseEntity<>("Tour not found", HttpStatus.BAD_REQUEST);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateTour(@PathVariable(value = "id") Long id, @RequestBody Tour tour) {
		try {
			Tour tourForUpdate = tourRepository.findById(id).get();
			tourForUpdate.setBus(tour.getBus());
			tourForUpdate.setDate(tour.getDate());
			tourForUpdate.setTime(tour.getTime());
			tourForUpdate.setDistance(tour.getDistance());
			tourForUpdate.setFrom(tour.getFrom());
			tourForUpdate.setTo(tour.getTo());
			tourForUpdate.setPassengers(tour.getPassengers());
			tourRepository.save(tourForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Tour not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Tour is udated successfully", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteTour(@PathVariable(value = "id") Long id) {
		try {
			tourRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			logger.info("Bus not found");
			return new ResponseEntity<>("Bus not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Bus is deleted successfully", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/profit")
	public ResponseEntity<Object> profit() {
		List<Tour> tours = tourRepository.findAll();
		double priceForKm = 5;
		double priceForSeat = 200;
		double profit = 0;
		for (Tour t : tours) {
			profit = profit + (t.getDistance()*priceForKm + priceForSeat) * t.getPassengers();
		}
		return new ResponseEntity<>("Your profit is " + profit + " rub", HttpStatus.ACCEPTED);
	}
	
}
