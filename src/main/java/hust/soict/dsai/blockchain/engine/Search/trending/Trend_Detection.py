
import pandas as pd
from collections import defaultdict
import os
import csv


count_dic = defaultdict(int)

file_path = str(os.path.dirname(os.path.dirname(os.path.dirname(__file__)))) + "\Data\All\Data_full_with_entities.csv"

df = pd.read_csv(file_path)

with open( str(os.path.dirname(os.path.dirname(__file__))) + "\\TrendHistory.txt", 'r', encoding='utf-8') as input_file:
        for last_line in input_file:
            input = last_line
            
        

inputs = input.split("|")
inputs[2] = inputs[2]
start_date = inputs[0]
end_date = inputs[1]
number = int(inputs[2])
column = inputs[3]

# Convert the 'Date' column to datetime format
df['Date'] = pd.to_datetime(df['Date'])

# Filter the data
result = df[(df["Date"] >= start_date) & (df["Date"] < end_date)]

author_list = df['Author'].unique()
author_list_split = [name.split() for name in author_list if isinstance(name, str)]
for row in result.iterrows():
    if column == "Entities":
        entities = str(row[1][column]).split("|")
        for entity in entities:
            if entity in author_list or entity in author_list_split or "CryptoSlate" in entity or not any(c.isalnum() for c in entity) or (entity.isnumeric() and int(entity) < 10):
                continue
            count_dic[entity] += str(row[1]["Title"]).count(entity) + str(row[1]["Summary"]).count(entity) + str(row[1]["Content"]).count(entity)
    else:
        tags = str(row[1][column]).split(", ")
        for tag in tags:
            if tag == "nan":
                continue
            count_dic[tag] += 1

count_list = [(key,values) for key,values in count_dic.items()]
count_list = sorted(count_list, key= lambda x: -x[1])
file = open(str(os.path.dirname(__file__)) +"\\Trend.csv", "w")
writer = csv.writer(file, lineterminator='\n')
writer.writerows(count_list[0:number])
file.close()

#try:
    #labels, values = zip(*count_list[0:number])
    #plt.figure(figsize=(10, 6))
    #plt.bar(labels, values)
    #plt.title(column + " " + 'Frequencies')
    #plt.xlabel(column)
    #plt.ylabel('Frequency')
    #plt.xticks(fontsize = 8,rotation=30)
    #plt.show()
#except ValueError as e:
    #print("No data")