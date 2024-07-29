import pandas as pd
import os

full_data_path = str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full.csv"
if os.path.exists(full_data_path):
    df = pd.read_csv(full_data_path)
else:
    df = pd.DataFrame(columns=["Link", "WebsiteSource", "Type", "Summary", "Title", "Content", "Date", "Tags", "Author", "Category"])
websites = ["BCN", "CSL"]
for web in websites:
    filePath = str(os.path.dirname(os.path.dirname(__file__))) + "\\Data\\" + web + "\\" + web + "_formatted.csv"
    df1 = pd.read_csv(filePath)
    df = pd.concat([df1,df], ignore_index = True)
    df = df.drop_duplicates(subset=["Link"])


df = df[["Link", "WebsiteSource", "Type", "Summary", "Title", "Content", "Date", "Tags", "Author", "Category"]]
print(df["Link"])

df.to_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full.csv", index = False)