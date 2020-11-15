package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.CardBySuitAndValue;
import name.theberge.cardsexerciseserver.dto.GetCardsBySuitAndValueResponse;
import name.theberge.cardsexerciseserver.model.CardFaceValue;
import name.theberge.cardsexerciseserver.model.CardSuite;
import org.javatuples.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCardsBySuitAndValueFormatter {
    public static GetCardsBySuitAndValueResponse
    toGetCardsBySuitAndValueResponseFormatter(Map<Pair<CardSuite, CardFaceValue>, Integer> input) {

        return GetCardsBySuitAndValueResponse.builder()
                .cardsBySuitAndValue(input.entrySet().stream()
                        .map(entry -> new CardBySuitAndValue(entry.getKey(), entry.getValue()))
                        .sorted(Comparator
                                .<CardBySuitAndValue, CardSuite>comparing(csv ->
                                        csv.getCardSuiteAndValue().getValue0(), Comparator.reverseOrder())
                                .thenComparing(csv ->
                                        csv.getCardSuiteAndValue().getValue1(), Comparator.reverseOrder()))
                        .collect(Collectors.toList()))
                .build();
    }
}
