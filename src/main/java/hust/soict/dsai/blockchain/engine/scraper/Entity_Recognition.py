import time
start =time.time()
import spacy
import pandas as pd
import os
df = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full.csv")
to_check = ["Title", "Summary", "Content", "Tags","Category"]
nlp = spacy.load("en_core_web_lg")
entities_col = []
named_entity_tags = ["PERSON", "NORP", "FAC", "ORG", "GPE", "LOC", "PRODUCT", "EVENT", "WORK_OF_ART", "LAW", "LANGUAGE", "PERCENT", "MONEY","QUANTITY"]
for index, row in df.iterrows():
    print(index)
    text = ""
    for tc in to_check:
        if pd.isna(row[tc]):
            text += " "
        else:
            text += row[tc] + " "
    doc = nlp(text)
    ent_list = set()
    for ent in doc.ents:
        if ent.label_ in named_entity_tags:
            ent_list.add(ent.text)
    entities_col.append("|".join(sorted(ent_list)))
df["Entities"] = entities_col
print(df["Entities"])
df.to_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full_with_entities.csv")
end = time.time()
print(end - start)