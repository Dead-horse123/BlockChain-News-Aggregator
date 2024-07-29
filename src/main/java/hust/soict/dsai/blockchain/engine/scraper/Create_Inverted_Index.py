import time
start = time.time()
import spacy
from collections import defaultdict
import pandas as pd
import string
import os

punctuations = string.punctuation
df1 = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full_with_entities.csv")
dic = defaultdict(list)

nlp = spacy.load("en_core_web_lg")
for i in range(len(df1)):
    text = (df1["Title"].iloc[i] if not pd.isna(df1["Title"].iloc[i]) else " ") + " " + (df1["Summary"].iloc[i] if not pd.isna(df1["Summary"].iloc[i]) else " ") + " " + (df1["Content"].iloc[i] if not pd.isna(df1["Content"].iloc[i]) else " ")
    doc = nlp(text)
    tokens = [token.lemma_.lower() for token in doc if not token.is_stop and token.text not in punctuations]
    for t in set(tokens):
        dic[t].append((i, tokens.count(t)))

dic_list = [[key,values] for key,values in sorted(dic.items())]
for i in range(len(dic_list)):
    dic_list[i][1] = "|".join(["-".join([str(x) for x in ii]) for ii in dic_list[i][1]])
print(len(dic))

dic_list.insert(0,["Token","Inv_Index"])
# Convert list of lists to DataFrame
df = pd.DataFrame(dic_list)

# Specify the file path
csv_file = str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Inv_index.csv"

# Write DataFrame to CSV file
df.to_csv(csv_file, index=False, header=False)

print("CSV file has been created successfully!")
print(-start + time.time())