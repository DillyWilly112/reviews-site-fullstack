package org.wecancodeit;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TypePopulator implements CommandLineRunner {

	@Resource
	private TypeRepository typeRepo;
	
	@Resource
	private StrainRepository strainRepo;
	
	@Resource
	private CompositionRepository compositionRepo;

	@Override
	public void run(String... args) throws Exception {
		Strain ogKush = new Strain("OG Kush");
		ogKush = strainRepo.save(ogKush);
		Strain sourDiesel = new Strain("Sour Diesel");
		sourDiesel = strainRepo.save(sourDiesel);
		Strain northernLights = new Strain("Northern Lights");
		northernLights = strainRepo.save(northernLights);
		Strain purpleHaze = new Strain("Purple Haze");
		purpleHaze = strainRepo.save(purpleHaze);
		Strain sundayDriver = new Strain("Sunday Driver");
		sundayDriver = strainRepo.save(sundayDriver);
		Strain garlicCookies = new Strain("Garlic Cookies");
		garlicCookies = strainRepo.save(garlicCookies);
		Strain pineappleExpress = new Strain("Pineapple Express");
		pineappleExpress = strainRepo.save(pineappleExpress);
		Strain whiteWidow = new Strain("White Widow");
		whiteWidow = strainRepo.save(whiteWidow);
		Strain aK47 = new Strain("AK-47");
		aK47 = strainRepo.save(aK47);
		
		Type indica = new Type("Indica Dominant", "Indica-dominant strains are known for relaxation & reduce stress.", northernLights, sundayDriver, garlicCookies, ogKush, whiteWidow, aK47);
		indica = typeRepo.save(indica);
		Type sativa = new Type("Sativa Dominant", "Sativa-dominant strains are known for creative thinking & uplifting.", pineappleExpress, purpleHaze, sourDiesel, ogKush, whiteWidow, aK47);
		sativa = typeRepo.save(sativa);
		Type hybrid = new Type("Hybrids", "Hybrid strains create a balance of mind and body.", ogKush, whiteWidow, aK47);
		hybrid = typeRepo.save(hybrid);
		
		compositionRepo.save(new Composition("Create Balance of Mind and Body", hybrid));
		compositionRepo.save(new Composition("Creative Thinking, Uplifting", sativa));
		compositionRepo.save(new Composition("Relaxation, Reduce Stress", indica));
	}
	
	
	
	
}
