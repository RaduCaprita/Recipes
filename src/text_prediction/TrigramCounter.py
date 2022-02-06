import functools

import pandas as pd

recipe_name_df = pd.read_csv('../../data/recipe_names.csv').recipe_name.apply(lambda recipe_name: recipe_name.split(" "))

trigrams = functools.reduce(lambda trigram_list, word_list: trigram_list + list(zip(word_list, word_list[1:], word_list[2:])), recipe_name_df, [])

trigram_counter = {}
for trigram in trigrams:
    key = "|".join(trigram)
    if key not in trigram_counter:
        trigram_counter[key] = 1
    else:
        trigram_counter[key] += 1

trigram_counts = {'first': [], 'second': [], 'third': [], 'count': []}
for trigram_count in trigram_counter.items():
    first, second, third = trigram_count[0].split("|")
    trigram_counts['first'].append(first)
    trigram_counts['second'].append(second)
    trigram_counts['third'].append(third)
    trigram_counts['count'].append(trigram_count[1])


trigram_count_df = pd.DataFrame(trigram_counts)

trigram_count_df.to_csv('../../data/trigram_counts.csv')
