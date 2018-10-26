package com.musicalbum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicalbum.music.domain.Album;
import com.musicalbum.music.service.AlbumService;
import com.musicalbum.music.web.AlbumController;


public class AlbumControllerTest extends AbstractMockServerTest{
	
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = null;
	
	@InjectMocks
	private AlbumController albumController;
	
	@Mock
	private AlbumService albumService;
	
	@Before
	public void setUp() {
		albumController = new AlbumController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Test
	public void getAlbumsTest() throws Exception {
		System.out.println("tesd test");
		Album album = new Album();
		album.setArtist("Test artist");
		album.setTitle("Test title");
		album.setReleaseYear("2010");
		album.setGenre("Rock");
		List<Album> list = new ArrayList<>();
		list.add(album);
		Mockito.when(albumService.getAllalbums()).thenReturn(list);
		MvcResult mvcResult = this.mockMvc.perform(get("/albums").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = mvcResult.getResponse().getContentAsString();
		List<Album> albumList = mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Album.class));
		Assert.assertTrue(albumList.size() > 0);
		Assert.assertTrue(mvcResult.getResponse().getStatus() == 200);
		
	}
	
}
