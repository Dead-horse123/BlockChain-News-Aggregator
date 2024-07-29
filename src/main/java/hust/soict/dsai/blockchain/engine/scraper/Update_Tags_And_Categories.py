import pandas as pd
import os
df = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full.csv")
all_tags = set()
all_categories = set()
for tags in df["Tags"]:
    if pd.isna(tags):
        continue
    new_tags = [tag.strip() for tag in tags.split(", ")]
    all_tags.update(new_tags)
for categories in df["Category"]:
    if pd.isna(categories):
        continue
    new_categories = [category.strip() for category in categories.split(", ")]
    all_categories.update(new_categories)

all_tags = sorted(all_tags)
all_categories = sorted(all_categories)
with open(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\All_Tags.csv", "w",encoding= "utf-8") as file:
    for t in all_tags:
        file.write(t+"\n")
    print("All tags written")

with open(str(os.path.dirname(os.path.dirname(__file__)))+ "\Data\All\All_Categories.csv", "w",encoding="utf-8") as file:
    for c in all_categories:
        file.write(c+"\n")
    print("All categories written")

print(len(all_tags), "tags")
print(len(all_categories), "categories")