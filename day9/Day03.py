import psycopg2

connection = psycopg2.connect(
    database="postgres",
    user="postgres",
    password="mahesh",
    host="localhost",
    port="5432"
)
cursor=connection.cursor()
print("Database connected succesfully...")

insertQuery = "INSERT INTO employees(name,department,salary) values(%s,%s,%s)"
cursor.execute(insertQuery,("Mahesh","CSE",10000))

connection.commit()
print("Data inserted succesfully...")