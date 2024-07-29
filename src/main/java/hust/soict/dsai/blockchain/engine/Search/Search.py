import time
start = time.time()
import pandas as pd
import spacy
import string
import os

punctuations = string.punctuation
from collections import defaultdict
def default_value():
    n =len(toks)
    return [1-1/pow(n,0.2) for i in range(n)]
def points(arr):
    ans = 1
    for i in arr:
        ans *= i
    return ans
nlp =spacy.load("en_core_web_lg")
with open(str(os.path.dirname(__file__)) + "\\SearchHistory.txt", 'r', encoding='utf-8') as input_file:
        for last_line in input_file:
            pass
        querry = last_line.strip().lower()

custom_stopwords = set(nlp.Defaults.stop_words) - {"us"}
toks = [tok.lemma_.lower() for tok in nlp(querry) if tok.text.lower() not in custom_stopwords and tok.text not in punctuations]
tok_dict = defaultdict(default_value)

df = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Inv_index.csv")
pos = 0
for index,row in df[df["Token"].isin(toks)].iterrows():
    iv_ids = row["Inv_Index"].split("|")
    iv_id_list = (tuple(iv_id.split("-")) for iv_id in iv_ids)
    for iv_id in iv_id_list:
        tok_dict[iv_id[0]][pos] += int(iv_id[1])
    pos += 1
return_list = ((int(key),points(value)) for key,value in tok_dict.items())
return_list = sorted(return_list, key= lambda x: -x[1])
return_index = [i[0] for i in return_list]
data = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full_with_entities.csv")
result = data.iloc[return_index]
result.to_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\Temp.csv", index = False)
print(result[["Link", "Title", "Summary"]])
end = time.time()
print("Time taken:", end - start)