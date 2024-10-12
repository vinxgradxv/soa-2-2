package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;


@Getter
@JacksonXmlRootElement(localName = "musicGenre")
public enum MusicGenre {
    POP, MATH_ROCK, BRIT_POP;
}
