class Car:
    name = ""
    color = ""
    def __init__(self):
        self.name = "toyota"
        self.color="white"

    def showData(self):
        print("Car Name : {0} , Car Color : {1}".format(self.name,self.color))

# object = Car()
# object.showData()

# -------------------------------------------------------------
# OOP in Python

class Student:
    rollno=0
    name=""
    age=0
    address=""
    def __init__(self,rollno=1,name="mahesh",age=20,address="Yeola"):
        self.rollno=rollno
        self.name=name
        self.address=address
    def method(self):
        pass   # pass is a statement used to transfer the control to the next scope

# obj = Student()
# print(obj)


# -------------------------------------------------- -
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

# fileName = input("Enter File Name : ")
# mode = input("Enter file opening mode : (r,w,a)")
# fs = FileOperations(fileName,mode)

# fs.doOperation()


# ------------------------------------------------
# Inheritance in python

class Person:
    def __init__(self, name, age, gender):
        self.name = name
        self.age = age
        self.gender = gender
        print("Parent constructor is called...")

    def display(self):
        print(self.name, self.age, self.gender)


class Student(Person):
    def __init__(self, name, age, gender, branch):
        super().__init__(name, age, gender) 
        self.branch = branch
        print("Child constructor is called...")

    def display(self):
        super().display()
        print(self.branch)


obj = Student('Steve', 20, "Male", "Computer Engineering")
obj.display()






