package com.artist;

public class ArtistInfo {

    private String fName;
    private String lName;
    private String geoLocation;
    
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
    
    
}
