package hacktheburgh;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeSuggester {
    private final HashMap<String, Set<ProductData>> ingredientToRecipeMap;
    IngredientParser ingredientParser;

    public RecipeSuggester() {
        this.ingredientToRecipeMap = new HashMap<>();
        this.ingredientParser = new IngredientParser();
    }

    public void addRecipe(ProductData recipe) {
        for (String ingredient:
                ingredientParser.parseIngredients(recipe.getIngredients())
                        .stream()
                        .map(quantityIngredientArr -> quantityIngredientArr[1])
                        .collect(Collectors.toList())) {

            Set<ProductData> recipeSet = ingredientToRecipeMap.computeIfAbsent(ingredient, k -> new HashSet<>());

            recipeSet.add(recipe);
        }
    }

    public List<ProductData> suggestRecipes(List<String> ingredientList) {
        HashMap<ProductData, Integer> recipeScores = new HashMap<>();
        for (String ingredient: ingredientList) {
            for (ProductData recipe: ingredientToRecipeMap.get(ingredient)) {
                if (!recipeScores.containsKey(recipe)) {
                    recipeScores.put(recipe, 1);
                } else {
                    recipeScores.put(recipe, recipeScores.get(recipe)+1);
                }
            }
        }
        
        return recipeScores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
