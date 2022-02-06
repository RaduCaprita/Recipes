from collections import Counter
import pandas as pd

trigram_count_df = pd.read_csv('../../data/small_trigram_counts.csv')
trigram_count_df['first'] = trigram_count_df['first'].astype(str)
trigram_count_df['second '] = trigram_count_df['second'].astype(str)
trigram_count_df['third'] = trigram_count_df['third'].astype(str)

trigram_count_dict = {row[1] + '|' + row[2] + '|' + row[3]: row[4] for _, row in trigram_count_df.iterrows()}

n = len(trigram_count_df)*3

vocabulary = {*trigram_count_df['first'], *trigram_count_df['third']}

bigrams = trigram_count_df['first'] + "|" + trigram_count_df['second']
probability_dict = {bigram: {word: 0 for word in vocabulary} for bigram in bigrams}

for _, row in trigram_count_df.iterrows():
    probability_dict[row['first'] + "|" + row['second']][row['third']] += 1

n_c_map = Counter(trigram_count_df['count'])
n_c_map[0] = len(vocabulary)**2-sum(n_c_map.values())

for bigram in probability_dict:
    for word in probability_dict[bigram]:
        try:
            r = trigram_count_dict[bigram + '|' + word]
        except KeyError:
            r=0
        probability_dict[bigram][word] = (r+1) * n_c_map[r+1] / (n_c_map[r] * n)


probability_df = pd.DataFrame(columns=['bi-gram'] + [word for word in vocabulary])
for bigram, map in probability_dict.items():
    probability_df.loc[len(probability_df.index)] = [bigram] + list(map.values())

probability_df.to_csv('../../data/word_predictions.csv')

