package name.theberge.cardsexerciseserver.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import name.theberge.cardsexerciseserver.model.Card;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import org.javatuples.Pair;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PairOfCardSuitCardValueDeserializer extends JsonDeserializer<Pair<CardSuite, CardFaceValue>> {

    @Override
    public Pair<CardSuite, CardFaceValue> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String[] value = node.asText().split(":");
        return Pair.with(CardSuite.valueOf(value[0]), CardFaceValue.valueOf(value[1]));
    }
}
