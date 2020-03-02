package org.wecancodeit;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class TypeControllerTest {

	@InjectMocks
	private TypeController underTest;
	
	@Mock
	private Type type;
	
	@Mock
	private Type anotherType;
	
	@Mock
	private Strain strain;
	
	@Mock
	private Strain anotherStrain;
	
	@Mock
	private TypeRepository typeRepo;
	
	@Mock
	private StrainRepository strainRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleTypeToModel() throws TypeNotFoundException {
		long arbitraryTypeId = 1;
		when(typeRepo.findById(arbitraryTypeId)).thenReturn(Optional.of(type));
		
	    underTest.findOneType(arbitraryTypeId, model);
	    verify(model).addAttribute("types", type);
	}
	
	@Test
	public void ShouldAddAllTypesToModel() {
		Collection<Type> allTypes = Arrays.asList(type, anotherType);
		when(typeRepo.findAll()).thenReturn(allTypes);
		
		underTest.findAllTypes(model);
		verify(model).addAttribute("types", allTypes);
	}
	
	@Test
	public void shouldAddSingleStrainToModel()throws StrainNotFoundException {
		long arbitraryStrainId = 1;
		when(strainRepo.findById(arbitraryStrainId)).thenReturn(Optional.of(strain));
		
		underTest.findOneStrain(arbitraryStrainId, model);
		verify(model).addAttribute("strains", strain);
	}
	
	@Test
	public void shouldAddAllStrainsToModel() {
		Collection<Strain> allStrains = Arrays.asList(strain, anotherStrain);
		when(strainRepo.findAll()).thenReturn(allStrains);
		
		underTest.findAllStrains(model);
		verify(model).addAttribute("strains", allStrains);
	}
}
