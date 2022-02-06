package hacktheburgh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebRecipeScraper {
    Pattern ingredientPattern;

    public WebRecipeScraper(Set<String> ingredientSet) {
        ingredientPattern = Pattern.compile(String.join("|", ingredientSet));
        System.out.println(String.join("|", ingredientSet));
    }

    public List<String> getIngredients(String url) throws IOException {
        Document page = Jsoup.connect(url).get();
        Set<String> ingredientsSet = new HashSet<>();
        System.out.println(page.body().text());
        Matcher matcher = ingredientPattern.matcher(page.body().text());
        while(matcher.find()) {
            ingredientsSet.add(matcher.group());
        }
        return new ArrayList<>(ingredientsSet);
    }
}
