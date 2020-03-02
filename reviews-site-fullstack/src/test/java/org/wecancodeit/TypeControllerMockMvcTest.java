package org.wecancodeit;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(TypeController.class)
public class TypeControllerMockMvcTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private TypeRepository typeRepo;
	
	@MockBean
	private StrainRepository strainRepo;
	
	@Mock
	private Type type;
	
	@Mock
	private Type anotherType;
	
	@Mock
	private Strain strain;
	
	@Mock
	private Strain anotherStrain;
	
	
	@Test
	public void shouldRouteToSingleTypeView() throws Exception {
		long arbitraryTypeId = 1;
		when(typeRepo.findById(arbitraryTypeId)).thenReturn(Optional.of(type));
		mvc.perform(get("/type?id=1")).andExpect(view().name(is("type")));
	}
	
	@Test
	public void shouldBeOkForSingleType() throws Exception {
		long arbitraryTypeId = 1;
		when(typeRepo.findById(arbitraryTypeId)).thenReturn(Optional.of(type));
		mvc.perform(get("/type?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleType() throws Exception {
		mvc.perform(get("/type?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleTypeIntoModel() throws Exception {
		when(typeRepo.findById(1L)).thenReturn(Optional.of(type));
		
		mvc.perform(get("/type?id=1")).andExpect(model().attribute("types", is(type)));
	}
	
	@Test
	public void shouldRouteToAllTypesView() throws Exception {
		mvc.perform(get("/show-types")).andExpect(view().name(is("types")));
	}
	
	@Test
	public void shouldBeOkForAllTypes() throws Exception {
		mvc.perform(get("/show-types")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllTypesIntoModel() throws Exception {
		Collection<Type> allTypes = Arrays.asList(type, anotherType);
		when(typeRepo.findAll()).thenReturn(allTypes);
		
		mvc.perform(get("/show-types")).andExpect(model().attribute("types", is(allTypes)));
	}
	
	@Test
	public void shouldRouteToSingleStrainView() throws Exception {
	long arbitraryStrainId = 42;
	when(strainRepo.findById(arbitraryStrainId)).thenReturn(Optional.of(strain));
	mvc.perform(get("/strain?id=42")).andExpect(view().name(is("topic")));
	}
	
	@Test
	public void shouldBeOkForSingleStrain() throws Exception {
		long arbitraryStrainId = 42;
		when(strainRepo.findById(arbitraryStrainId)).thenReturn(Optional.of(strain));
		mvc.perform(get("/strain?id=42")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleStrain() throws Exception {
		mvc.perform(get("/strain?id=42")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleStrainIntoModel() throws Exception {
		when(strainRepo.findById(42L)).thenReturn(Optional.of(strain));
		mvc.perform(get("/strain?id=42")).andExpect(model().attribute("strains", is(strain)));
	}
	
	@Test
	public void shouldBeOkForAllStrains() throws Exception {
		mvc.perform(get("/strains")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldRouteToAllStrainsView() throws Exception {
		mvc.perform(get("/strains")).andExpect(view().name(is("strains")));
	}
	
	@Test
	public void shouldPutAllStrainsIntoModel() throws Exception {
		Collection<Strain> allStrains = Arrays.asList(strain, anotherStrain);
		when(strainRepo.findAll()).thenReturn(allStrains);
		
		mvc.perform(get("/strains")).andExpect(model().attribute("strains", is(allStrains)));
	}
}
