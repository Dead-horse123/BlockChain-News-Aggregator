import pandas as pd
import os

#load data files
#bad lines (caused by bad internet connection, rate limit when scaping) are skipped
org_file_path = str(os.path.dirname(os.path.dirname(__file__))) + "\\Data\\CSL\\CSL_raw.csv"
f = pd.read_csv(org_file_path, skipinitialspace=True, on_bad_lines= 'skip')

#remove duplicates
f = f.drop_duplicates(subset= ["Link"])

#Reformat the data
f["Content"] = f["Content"].str.replace(r"^Ad\s+", "", regex=True) #remove ad
f['Category'] = f['Category'].str.replace(' - ', ', ')
f['Category'] = f['Category'].str.split(r', | · ').apply(lambda x: ', '.join(x) if isinstance(x, list) else '')
f['Tags'] = f['Tags'].str.replace(' - ', ', ')
f.Tags =f.Tags.str.lower()
f['Date'] = pd.to_datetime(f['Date'], format='%b. %d, %Y at %I:%M %p %Z').dt.strftime('%Y-%m-%d %H:%M:%S')


f['Type'] = f['Type'].astype(int)
print(f["Title"])

#Create new file
new_file_path = str(os.path.dirname(os.path.dirname(__file__))) + "\\Data\\CSL\\CSL_formatted.csv"
f.to_csv(new_file_path, index = False)
print("New file created")
