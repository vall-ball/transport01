package ru.vallball.transport01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buses")
public class BusRestController {
	
	@Autowired
	BusRepository busRepository;
	
	public ResponseEntity<Object> createBus(@RequestBody Bus bus) {
	      busRepository.save(bus);
	      return new ResponseEntity<>("Bus is created successfully", HttpStatus.CREATED);
	   }
	
	
}
