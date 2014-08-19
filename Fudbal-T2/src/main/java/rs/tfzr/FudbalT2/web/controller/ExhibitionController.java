/**
 * 
 */
package rs.tfzr.FudbalT2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.tfzr.FudbalT2.repository.ExhibitionRepository;
import rs.tfzr.FudbalT2.repository.PlayerRepository;

/**
 * @author Miroslav
 *
 */
@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

	@Autowired
	private ExhibitionRepository exhibitionService;

	@Autowired
	private PlayerRepository playerService;
	
	
}
