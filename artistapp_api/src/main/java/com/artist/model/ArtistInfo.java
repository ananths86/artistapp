package com.artist.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "artist")
@Configuration("artistProperties")
public class ArtistInfo {

    private String fName;
    private String lName;
    private String geoLocation;
    private String apiKey;
    
	public ArtistInfo(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
    
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
    
	@Autowired
	private ArtistInfo artistProperties;

	public void method() {

	   apiKey = artistProperties.getApiKey();
	  

	}
}
