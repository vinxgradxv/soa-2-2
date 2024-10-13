package controller;

import clients.RestClient;
import dao.NominationDao;
import lombok.SneakyThrows;
import model.MusicBand;
import model.MusicGenre;
import model.Nomination;
import model.NominationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.StringToObjectUsingJackson;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BandsController {

    @Autowired
    private NominationDao nominationDao;

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

    @PostMapping(value = "/band/{band-id}/nominate/{genre}", produces = "application/xml")
    public NominationResponse nominateBand(@PathVariable("band-id") Long bandId, @PathVariable("genre") MusicGenre genre) {
        nominationDao.saveNomination(Nomination.builder()
                        .bandId(bandId)
                        .genre(genre).build());
        return new NominationResponse("Группа успешно номинирована на премию");
    }
}
