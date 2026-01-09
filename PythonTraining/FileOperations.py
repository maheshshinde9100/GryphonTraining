# ----------------------------------------------------------------------------
# performing operations on file

class FileOperations:
    fileName=""
    fileOpenMode=""
    def __init__(self,fileName,fileOpenMode="r"):
        self.fileName=fileName
        self.fileOpenMode=fileOpenMode
    
    def doOperation(self):
        if self.fileOpenMode=="r" : 
            file = open(self.fileName,self.fileOpenMode)
            print("\n----------Reading ",self.fileName," file------------\n")
            print(file.read())
        elif self.fileOpenMode=="w":
            file = open(self.fileName,self.fileOpenMode)
            print("\n----------Writing ",self.fileName," file------------\n")
            ip = input("Enter the line you want to write to the file : ")
            file.write(ip)
        elif self.fileOpenMode=="a":
            file = open(self.fileName,"a")
            print("\n----------Appending to ",self.fileName," file------------\n")
            ip = input("Enter data you want to append : ")
            file.write(ip)
        else:
            print("Please choose enter correct file opening mode : (r,w,a)")
            exit

fileName = input("Enter File Name : ")
mode = input("Enter file opening mode : (r,w,a)")
fs = FileOperations(fileName,mode)

fs.doOperation()