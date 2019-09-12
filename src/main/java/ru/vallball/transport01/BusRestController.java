package ru.vallball.transport01;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

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
@RequestMapping("/buses")
public class BusRestController {

	@Autowired
	BusRepository busRepository;

	private static final Logger logger = LoggerFactory.getLogger(BusRestController.class);

	@PostMapping("/microbus")
	public ResponseEntity<Object> createBus(@Valid @RequestBody Microbus bus) {
		logger.info("Было обращение к POST");
		logger.info("Емкость автобуса: " + String.valueOf(bus.getCapacity()));
		logger.info(busRepository.toString());
		busRepository.save(bus);
		logger.info("ИД автобуса: " + String.valueOf(bus.getId()));
		return new ResponseEntity<>("Bus is created successfully", HttpStatus.CREATED);
	}

	@PostMapping("/bigbus")
	public ResponseEntity<Object> createBigBus(@Valid @RequestBody Bigbus bus) {
		logger.info("Было обращение к POST");
		logger.info("Емкость автобуса: " + String.valueOf(bus.getCapacity()));
		logger.info(busRepository.toString());
		busRepository.save(bus);
		logger.info("ИД автобуса: " + String.valueOf(bus.getId()));
		return new ResponseEntity<>("Bus is created successfully", HttpStatus.CREATED);
	}

	@GetMapping
	@ResponseBody
	public List<Bus> listBus() {
		return busRepository.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBus(@PathVariable(value = "id") Long id, @RequestBody Bus bus) {
		try {
			Bus busForUpdate = busRepository.findById(id).get();
			busForUpdate.setCapacity(bus.getCapacity());
			busRepository.save(busForUpdate);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Bus not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Bus is udated successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity oneBus(@PathVariable(value = "id") Long id) {
		try {
			Bus bus = busRepository.findById(id).get();
			ResponseEntity<Bus> answer = new ResponseEntity<>(bus, HttpStatus.ACCEPTED);
			return answer;
		} catch (NoSuchElementException e) {
			logger.info("Bus not found");
			return new ResponseEntity<>("Bus not found", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBus(@PathVariable(value = "id") Long id) {
		try {
			busRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			logger.info("Bus not found");
			return new ResponseEntity<>("Bus not found", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Bus is deleted successfully", HttpStatus.ACCEPTED);
	}
}
