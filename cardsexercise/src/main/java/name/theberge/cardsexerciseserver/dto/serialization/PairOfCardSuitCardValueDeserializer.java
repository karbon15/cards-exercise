package name.theberge.cardsexerciseserver.dto.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.javatuples.Pair;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class PairOfCardSuitCardValueDeserializer
    extends JsonDeserializer<Pair<CardSuit, CardFaceValue>> {

  @Override
  public Pair<CardSuit, CardFaceValue> deserialize(
      JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    String[] value = node.asText().split(":");
    return Pair.with(CardSuit.valueOf(value[0]), CardFaceValue.valueOf(value[1]));
  }
}
