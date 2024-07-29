import pandas as pd
import time
import os
start_time = time.time()

# check the last line in search history
try:
    with open(str(os.path.dirname(__file__)) + "\\SearchHistory.txt", 'r', encoding='utf-8') as input_file:
        for last_line in input_file:
            pass
    sentence = last_line.strip().lower()
    
    # open data files
    df = pd.read_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\All\Data_full_with_entities.csv")
    
    result = df[df["Content"].str.strip().str.lower().str.contains(sentence) | df["Title"].str.strip().str.lower().str.contains(sentence) | df["Summary"].str.strip().str.lower().str.contains(sentence)]
    result.to_csv(str(os.path.dirname(os.path.dirname(__file__))) + "\Data\Temp.csv", index = False)
    print(result[["Link", "Title", "Summary"]])

except FileNotFoundError:
    print("Error: Input file or CSV file not found.")
except PermissionError:
    print("Error: Permission denied while accessing files.")
except Exception as e:
    print(f"An error occurred: {e}")

end_time = time.time()
print("Time taken:", end_time - start_time)
