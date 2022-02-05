package hacktheburgh;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ProductDataParser {

    private Nutrition createNutritionObject(JSONObject object){
        Nutrition nutrition = new Nutrition();
        int calories = ((Long) object.get("calories")).intValue();
        String fat = (String) object.get("fat");
        String salt = (String) object.get("salt");
        String sugar = (String) object.get("sugar");
        nutrition.setCalories(calories);
        nutrition.setFat(fat);
        nutrition.setSalt(salt);
        nutrition.setSugar(sugar);
        return nutrition;
    }

    private ProductData createProductDataObject(JSONObject object) {
        ProductData product = new ProductData();
        int id = ((Long) object.get("id")).intValue();
        String title = (String) object.get("title");
        String summary = (String) object.get("summary");
        int prepTimeMins = ((Long) object.get("prep_time_mins")).intValue();
        int cookTimeMins = ((Long) object.get("cook_time_mins")).intValue();
        int servings = ((Long) object.get("servings")).intValue();
        int difficulty = ((Long) object.get("difficulty")).intValue();
        String photoCropFileName = (String) object.get("photo_crop_file_name");
        String photoCropContentType = (String) object.get("photo_crop_content_type");
        int photoCropFileSize = ((Long) object.get("photo_crop_file_size")).intValue();
        String photoCropUpdatedAt = (String) object.get("photo_crop_updated_at");
        int photoCropHeight = ((Long) object.get("photo_crop_height")).intValue();
        int photoCropWidth = ((Long) object.get("photo_crop_width")).intValue();
        String slug = (String) object.get("slug");
        String publishedAt = (String) object.get("published_at");
        String source = (String) object.get("source");
        String course = (String) object.get("course");
        String youtubeUrl = (String) object.get("youtube_url");
        List<String> ingredients = (List<String>) object.get("ingredients");
        JSONObject jsonObject = (JSONObject) object.get("nutrition");
        Nutrition nutrition = createNutritionObject(jsonObject);
        List<String> recipeSteps = (List<String>) object.get("recipe_steps");
        product.setId(id);
        product.setTitle(title);
        product.setSummary(summary);
        product.setPrepTimeMins(prepTimeMins);
        product.setCookTimeMins(cookTimeMins);
        product.setServings(servings);
        product.setDifficulty(difficulty);
        product.setPhotoCropFileName(photoCropFileName);
        product.setPhotoCropContentType(photoCropContentType);
        product.setPhotoCropFileSize(photoCropFileSize);
        product.setPhotoCropUpdatedAt(photoCropUpdatedAt);
        product.setPhotoCropHeight(photoCropHeight);
        product.setPhotoCropWidth(photoCropWidth);
        product.setSlug(slug);
        product.setPublishedAt(publishedAt);
        product.setSource(source);
        product.setCourse(course);
        product.setYoutubeUrl(youtubeUrl);
        product.setIngredients(ingredients);
        product.setNutrition(nutrition);
        product.setRecipeSteps(recipeSteps);
        return product;
    }

    public List<ProductData> getProductData() {
        JSONParser parser = new JSONParser();
        List<ProductData> products = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader(System.getProperty("user.dir") + "/src/hacktheburgh/recipes.json"));
            JSONArray jsonArray = (JSONArray) obj;
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                ProductData product = createProductDataObject(object);
                products.add(product);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
