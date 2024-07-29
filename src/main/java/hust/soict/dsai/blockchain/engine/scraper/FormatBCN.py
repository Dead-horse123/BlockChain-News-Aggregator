import pandas as pd
import os

#load data files
#bad lines (caused by bad internet connect, rate limit when scaping) are skipped
org_file_path = str(os.path.dirname(os.path.dirname(__file__))) + "\Data\BCN\BCN_raw.csv"
f = pd.read_csv(org_file_path, skipinitialspace=True, on_bad_lines= 'skip')

#remove duplicates
f = f.drop_duplicates()

#the same format in CryptoSlateScraper
f['Category'] = f['Category'].str.replace('Stablecoin', 'Stablecoins').str.replace('CBDC', 'CBDCs').str.replace('NFT', 'NFTs').str.replace('Exchange', 'Exchanges')
def join_unique(series):
    return ', '.join(series.unique())

f = f.groupby(f.columns.difference(['Category']).tolist()).agg({'Category': join_unique}).reset_index()
f['Date'] = pd.to_datetime(f['Date'], format='%b %d, %Y %H:%M')
f['Tags'] = f['Tags'].str.split(' - ').apply(lambda x: ', '.join(x))
print(f["Title"])

#Create new file
new_file_path = str(os.path.dirname(os.path.dirname(__file__))) + "\Data\BCN\BCN_formatted.csv"
f.to_csv(new_file_path, index = False)
print("New file created")