package controller;

import clients.RestClient;
import lombok.SneakyThrows;
import model.MusicBand;
import model.MusicGenre;
import models.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.StringToObjectUsingJackson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class BandsController {


    @GetMapping(value = "/bands/get-by-genre/{genre}", produces = "application/xml")
    @SneakyThrows
    public List<MusicBand> getBandsByGenre(@PathVariable MusicGenre genre) {
        var client = new RestClient();
        var responseFromMainService = client.get("/api/music-bands");
        var origBands = StringToObjectUsingJackson.convertStringToListOfObject(responseFromMainService);
        var res = new ArrayList<MusicBand>();
        for (var band : origBands) {
            if (band.getGenre().equals(genre)) {
                res.add(band);
            }
        }
        return res;
    }

    @PostMapping(value = "/persons", produces = "application/xml")
    public List<Person> getPersons() {
        return Arrays.asList(
                new Person("John", "Doe", 30),
                new Person("Jane", "Doe", 25)
        );
    }

}
