# class FileOperations:
#     fileName=""
#     fileOpenMode=""
#     def __init__(self,fileName,fileOpenMode="r"):
#         self.fileName=fileName
#         self.fileOpenMode=fileOpenMode
#     def doOperation(self):
#         if self.fileOpenMode=="r" : 
#             file = open(self.fileName,self.fileOpenMode)
#             print("\n----------Reading ",self.fileName," file------------\n")
#             print(file.read())
#         elif self.fileOpenMode=="w":
#             file = open(self.fileName,self.fileOpenMode)
#             print("\n----------Writing ",self.fileName," file------------\n")
#             ip = input("Enter the line you want to write to the file : ")
#             file.writelines(ip)
#         elif self.fileOpenMode=="a":
#             file = open(self.fileName,"a")
#             print("\n----------Appending to ",self.fileName," file------------\n")
#             ip = input("Enter data you want to append : ")
#             file.write(ip)
#         else:
#             print("Please choose enter correct file opening mode : (r,w,a)")
#             exit

# fileName = input("Enter File Name : ")
# mode = input("Enter file opening mode : (r,w,a)")
# fs = FileOperations(fileName,mode)
# fs.doOperation()

# ---------------------
# API Integration in PYTHON

import requests
response = requests.get("https://dogapi.dog/api/v2/facts?limit=2")
data = response.json()
print("Print API response : \n",data)

# for i in data['data']:
#     print(i['id'])
#     print(i['type'])
#     for j in i['attributes']:
#         print(j)

# Output ->
"""
Print API response : 
{
  "data": [
    {
      "id": "52daa494-7fd4-4a44-95c1-128afbfa0ab8",
      "type": "fact",
      "attributes": {
        "body": "The fastest breed, the Greyhound, can run up to 44 miles per hour."
      }
    }
  ]
}
"""