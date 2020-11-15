package name.theberge.cardsexerciseserver.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import org.javatuples.Pair;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PairOfCardSuitCardValueSerializer extends JsonSerializer<Pair<CardSuite, CardFaceValue>> {
    @Override
    public void serialize(Pair<CardSuite, CardFaceValue> objects,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(objects.getValue0().toString() + ":" + objects.getValue1().toString());
    }
}
