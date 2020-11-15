package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.CardBySuitAndValue;
import name.theberge.cardsexerciseserver.dto.GetCardsBySuitAndValueResponse;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuit;
import org.javatuples.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCardsBySuitAndValueFormatter {
    public static GetCardsBySuitAndValueResponse
    toGetCardsBySuitAndValueResponseFormatter(Map<Pair<CardSuit, CardFaceValue>, Integer> input) {

        return GetCardsBySuitAndValueResponse.builder()
                .cardsBySuitAndValue(input.entrySet().stream()
                        .map(entry -> new CardBySuitAndValue(entry.getKey(), entry.getValue()))
                        .sorted(Comparator
                                .<CardBySuitAndValue, CardSuit>comparing(csv ->
                                        csv.getCardSuitAndValue().getValue0(), Comparator.reverseOrder())
                                .thenComparing(csv ->
                                        csv.getCardSuitAndValue().getValue1(), Comparator.reverseOrder()))
                        .collect(Collectors.toList()))
                .build();
    }
}
