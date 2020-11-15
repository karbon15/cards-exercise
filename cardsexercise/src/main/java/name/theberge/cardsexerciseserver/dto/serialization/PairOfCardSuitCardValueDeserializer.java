package name.theberge.cardsexerciseserver.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.javatuples.Pair;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PairOfCardSuitCardValueDeserializer extends JsonDeserializer<Pair<CardSuit, CardFaceValue>> {

    @Override
    public Pair<CardSuit, CardFaceValue> deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String[] value = node.asText().split(":");
        return Pair.with(CardSuit.valueOf(value[0]), CardFaceValue.valueOf(value[1]));
    }
}
