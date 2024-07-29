import pandas as pd
import os
with open(str(os.path.dirname(__file__)) + "\\FilterHistory.txt", 'r', encoding='utf-8') as input_file:
        for last_line in input_file:
            pass
        input = last_line.strip()
        print(input)
df = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\Temp.csv")

df.fillna("NaN", inplace=True)
inputs = input.split("|")
cols = ["Link", "Title", "Summary"]
index = 0
result = df
while index + 1 < len(inputs):
    col = inputs[index]
    print(col)
    selected = inputs[index + 1]
    print(selected)
    result = result[result[col].str.contains(selected)]
    index += 2
print(result[cols])

result.to_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\Temp.csv", index = False)