package com.artist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.assertj.core.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping("/artistInfo/topAlbumByLocation")
    public String getTopAlbumByLocation(@RequestParam(name="artistName") String artistName) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String albumRes = restTemplate.getForObject(
    	  "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=cher&api_key=3f773d29058b0bda480cb6681cb2ee0f&format=json",
    	  String.class);
    	
    	LOGGER.info(albumRes);
    	return albumRes;
    }
    
    @RequestMapping("/artistInfo/geoLocation")
    public String getGeoLocationByUser(@RequestParam String loginName) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String geoLocation = restTemplate.getForObject(
    	  "http://ws.audioscrobbler.com/2.0/?method=user.getinfo&user=rj&api_key=3f773d29058b0bda480cb6681cb2ee0f&format=json",
    	  String.class);
    	String country = null;
    	try {
			JSONObject json = new JSONObject(geoLocation);
			JSONObject userObj = (JSONObject)json.get("user");
			country = (String)userObj.get("country");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	LOGGER.info(country);
    	return country;
    }
    
    @RequestMapping("/artistInfo/listArtist/{geoLocation}")
    public List<String> getArtistByGeoLocation(@PathVariable String geoLocation) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String artist = restTemplate.getForObject(
    	  "http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=" +geoLocation +"&api_key=3f773d29058b0bda480cb6681cb2ee0f&format=json",
    	  String.class);
    	List<String> artistList = new ArrayList<String>();
    	try {
			JSONObject json = new JSONObject(artist);
			JSONObject userObj = (JSONObject)json.get("topartists");
			JSONArray artistArry = (JSONArray)userObj.get("artist");
			for (int i=0; i < (artistArry.length()-1); i++) {
				JSONObject artistObj = (JSONObject)artistArry.get(i);
				artistList.add(artistObj.getString("name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	LOGGER.info("" + artistList);
    	return artistList;
    }
    
    @RequestMapping("/artistInfo/listArtist/{geoLocation}")
    public List<String> getTopAlbumsByGeoLocation(@PathVariable String geoLocation) {
    	
    	List<String> artists = this.getArtistByGeoLocation(geoLocation);
    	return null;
    }
}
