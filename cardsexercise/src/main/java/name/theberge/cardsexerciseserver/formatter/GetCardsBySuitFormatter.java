package name.theberge.cardsexerciseserver.formatter;

import name.theberge.cardsexerciseserver.dto.CardBySuit;
import name.theberge.cardsexerciseserver.dto.GetCardsBySuitResponse;
import name.theberge.cardsexerciseserver.model.CardSuit;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCardsBySuitFormatter {
    public static GetCardsBySuitResponse toGetCardsBySuitResponseFormatter(Map<CardSuit, Integer> input) {

        return GetCardsBySuitResponse.builder()
                .cardsBySuit(input.entrySet().stream()
                        .map(entry -> new CardBySuit(entry.getKey(), entry.getValue()))
                        .sorted(Comparator.comparing(CardBySuit::getCardSuit).reversed())
                        .collect(Collectors.toList()))
                .build();
    }
}
