package hacktheburgh;


import java.util.*;

public class SearchIngredientsOnline {

    private Set<String> getIngredients() {
        ProductDataParser productDataParser = new ProductDataParser();
        IngredientParser ingredientParser = new IngredientParser();
        List<ProductData> products = productDataParser.getProductData();
        List<String> ingredientsQuantities = new ArrayList<>();
        for(ProductData p : products) {
            ingredientsQuantities.addAll(p.getIngredients());
        }
        List<String[]> ingredientsAndQuantities = ingredientParser.parseIngredients(ingredientsQuantities);

        Set<String> ingredients = new HashSet<>();

        for(String[] s : ingredientsAndQuantities) {
            ingredients.add(s[1]);
        }
        return ingredients;
    }

    private String getURL(String ingredient) {
        String url1 = "https://www.ocado.com/search?entry=";
        String url2 = "&filters=m-s-274710";
        if(ingredient.contains(" ")) {
            ingredient = ingredient.replace(" ", "%20");
        }
        return url1 + ingredient + url2;
    }

    public Map<String, String> getIngredientsURL() {
        Set<String> ingredients = getIngredients();
        Map<String, String> ingredientsURL = new HashMap<>();
        for(String ingredient : ingredients) {
            String url = getURL(ingredient);
            ingredientsURL.put(ingredient, url);
        }
        return ingredientsURL;
    }
}
