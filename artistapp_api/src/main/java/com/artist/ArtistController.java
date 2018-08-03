package com.artist;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
    	return "Hello " + name;
    }
    
    @RequestMapping("/artistInfo")
    public ArtistInfo getArtistInfo(@RequestBody ArtistInfo artistInfo ) {
        return new ArtistInfo(artistInfo.getfName(), artistInfo.getlName());
    }
    
    
}
