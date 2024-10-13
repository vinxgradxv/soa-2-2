package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@JacksonXmlRootElement(localName = "NominationResponse")
@AllArgsConstructor
public class NominationResponse {
    @NonNull
    private String message; // cannot be null
}