package com.artist.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.artist.model.ArtistInfo;

@RestController
public class ArtistController {
	
	private static final Logger LOGGER = Logger.getLogger( ArtistController.class.getName() );

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
    	return "Hello " + name;
    }
    
    @RequestMapping("/artistInfo")
    public ArtistInfo getArtistInfo(@RequestBody ArtistInfo artistInfo ) {
        return new ArtistInfo(artistInfo.getfName(), artistInfo.getlName());
    }
    
    @RequestMapping("/artistInfo/topAlbum")
    public String getTopAlbum(@RequestParam(name="artistName") String artistName) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String albumRes = restTemplate.getForObject(
    	  "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=cher&api_key=3f773d29058b0bda480cb6681cb2ee0f&format=json",
    	  String.class);
    	
    	LOGGER.info(albumRes);
    	return albumRes;
    }
}
