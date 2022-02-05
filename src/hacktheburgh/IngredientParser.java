package hacktheburgh;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IngredientParser {
    private final Pattern ingredientPattern;

    public IngredientParser() {
        ingredientPattern = Pattern.compile("(\\d+(?:ml|g|\\stsp|\\stbsp|\\sjar|\\sbunch|\\ssticks)?)\\s(.*)");
    }

    public List<String[]> parseIngredients(List<String> ingredientList) {
        return ingredientList.stream()
                .map(ingredientPattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> new String[]{matcher.group(1), matcher.group(2)})
                .collect(Collectors.toList());
    }
}
