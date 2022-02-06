import pandas as pd

df = pd.read_csv('../../data/word_predictions.csv')

word1 = 'mediterranean'
word2 = 'sea'

bigram = word1 + "|" + word2
print(df.loc[df['bi-gram'] == bigram].drop('Unnamed: 0', axis=1).drop('bi-gram', axis=1).max().idxmax())


# 'spicy|dipping'
