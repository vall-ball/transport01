package ru.vallball.transport01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buses")
public class BusRestController {

	@Autowired
	BusRepository busRepository;

	private static final Logger logger = LoggerFactory.getLogger(BusRestController.class);

	@PostMapping("/microbus")
	public ResponseEntity<Object> createBus(@RequestBody Microbus bus) {
		logger.info("Было обращение к POST");
		logger.info("Емкость автобуса: " + String.valueOf(bus.getCapacity()));
		logger.info(busRepository.toString());
		busRepository.save(bus);
		logger.info("ИД автобуса: " + String.valueOf(bus.getId()));
		return new ResponseEntity<>("Bus is created successfully", HttpStatus.CREATED);
	}

	@PostMapping("/bigbus")
	public ResponseEntity<Object> createBigBus(@RequestBody Bigbus bus) {
		logger.info("Было обращение к POST");
		logger.info("Емкость автобуса: " + String.valueOf(bus.getCapacity()));
		logger.info(busRepository.toString());
		busRepository.save(bus);
		logger.info("ИД автобуса: " + String.valueOf(bus.getId()));
		return new ResponseEntity<>("Bus is created successfully", HttpStatus.CREATED);
	}

}
