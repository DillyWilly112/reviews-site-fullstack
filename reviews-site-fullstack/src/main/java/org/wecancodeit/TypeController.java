package org.wecancodeit;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TypeController {

	@Resource
	TypeRepository typeRepo;
	
	@Resource
	StrainRepository strainRepo;
	
	@RequestMapping("/type")
	public String findOneType(@RequestParam(value = "id") long id, Model model) throws TypeNotFoundException {
		Optional<Type> type = typeRepo.findById(id);
		
		if(type.isPresent()) {
			model.addAttribute("types", type.get());
			return "type";
		}
		throw new TypeNotFoundException();
	}

	@RequestMapping("/show-types")
	public String findAllTypes(Model model) {
		model.addAttribute("types", typeRepo.findAll());
		return ("types");
	}

	@RequestMapping("/strain")
	public String findOneStrain(@RequestParam(value = "id") long id, Model model) throws StrainNotFoundException {
        Optional<Strain> strain = strainRepo.findById(id);
		
		if(strain.isPresent()) {
			model.addAttribute("strains", strain.get());
			model.addAttribute("types", typeRepo.findByStrainsContains(strain.get()));
			return "strain";
		}
		throw new StrainNotFoundException();
		
	}

	@RequestMapping("/strains")
	public String findAllStrains(Model model) {
		model.addAttribute("strains", strainRepo.findAll());
		return ("strains");
		
	}

}
