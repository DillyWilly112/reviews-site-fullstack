package org.wecancodeit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private StrainRepository strainRepo;
	
	@Resource 
	private TypeRepository typeRepo;
	
	@Resource
	private CompositionRepository compositionRepo;
	
	
	@Test
	public void shouldSaveAndLoadStrain() {
		Strain strain = strainRepo.save(new Strain("strain"));
		long strainId = strain.getId();
		
		entityManager.flush(); //forces jpa to hit db when we try to find it
		entityManager.clear();
		
		Optional<Strain> result = strainRepo.findById(strainId);
		strain = result.get();
		assertThat(strain.getName(), is("strain"));
	}
	
	@Test
	public void shouldGenerateStrainId() {
		Strain strain = strainRepo.save(new Strain("strain"));
		long strainId = strain.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(strainId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadType() {
		Type type = new Type("type name", "description");
		type = typeRepo.save(type);
		long typeId = type.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Type> result = typeRepo.findById(typeId);
		type = result.get();
		assertThat(type.getName(), is("type name"));
	}
	
	@Test
	public void shouldEstablishTypeToStrainsRelationship() {
		//topic is not the owner so we create these first
		Strain ogKush = strainRepo.save(new Strain("OG Kush"));
		Strain sourDiesel = strainRepo.save(new Strain("Sour Diesel"));
		Strain northernLights = strainRepo.save(new Strain("Northern Lights"));
		Strain purpleHaze = strainRepo.save(new Strain("Purple Haze"));
		Strain sundayDriver = strainRepo.save(new Strain("Sunday Driver"));
		Strain garlicCookies = strainRepo.save(new Strain("Garlic Cookies"));
		Strain pineappleExpress = strainRepo.save(new Strain("Pineapple Express"));
		Strain whiteWidow = strainRepo.save(new Strain("White Widow"));
		Strain aK47 = strainRepo.save(new Strain("AK-47"));
		
		Type type = new Type("indica", "description", northernLights, sundayDriver, garlicCookies);
		type = typeRepo.save(type);
		long typeId = type.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Type> result = typeRepo.findById(typeId);
		type = result.get();
		
		assertThat(type.getTypes(), containsInAnyOrder(northernLights, sundayDriver, garlicCookies));
	}
	
	@Test
	public void shouldFindTypesForStrain() {
		Strain northernLights = strainRepo.save(new Strain("northernLights"));
		Strain sourDiesel = strainRepo.save(new Strain("sourDiesel"));
		
		Type indica = typeRepo.save(new Type("Indica", "Description", northernLights));
		Type sativa = typeRepo.save(new Type("Sativa", "Description", sourDiesel));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Type> typesForStrain = typeRepo.findByStrainsContains(northernLights);
		
		assertThat(typesForStrain,containsInAnyOrder(indica, sativa));
	}
	
	@Test
	public void shouldFindTypesForStrainId() {
		Strain ogKush = strainRepo.save(new Strain("OG Kush"));
		long strainId = ogKush.getId();
		
		Type indica = typeRepo.save(new Type("Indica", "Description", ogKush));
		Type sativa = typeRepo.save(new Type("Sativa", "Description", ogKush));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Type> typersForStrain = typeRepo.findByStrainsId(strainId);
		
		assertThat(typersForStrain,containsInAnyOrder(indica, sativa));
	}
	
	@Test
	public void shouldEstablishCompositionToTypeRelationship() {
		Type type = new Type("name", "description");
		typeRepo.save(type);
		long typeId = type.getId();
		
		Composition composition = new Composition("origin", type);
		compositionRepo.save(composition);
		
		Composition composition2 = new Composition("origin two", type);
		compositionRepo.save(composition2);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Type> result = typeRepo.findById(typeId);
		type = result.get();
		assertThat(type.getCompositions(), containsInAnyOrder(composition, composition2));
		
	}
}
