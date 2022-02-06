import pandas as pd
import re

df = pd.read_csv('../../data/recipes_82k.csv').recipe_name.str.lower().apply(lambda recipe_name: re.sub("[,()\":]", "", recipe_name)).apply(lambda recipe_name: re.sub("[-|]", " ", recipe_name))
df.to_csv('../../data/recipe_names.csv')
