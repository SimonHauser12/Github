import mysql.connector

def connectionOpen():
    connection = mysql.connector.connect(host = "localhost", user = "root", passwd = "sh30112002", db = "scheresteinpapier")
    return connection

def connectionClose(connection):
    connection.disconnect()

def insert(connection, anzahlSC, anzahlST, anzahlPA, anzahlE, anzahlSPO, anzahlP, anzahlC, anzahlSP):
    curser = connection.cursor()
    curser.execute("insert into anzahl values(null, "+str(anzahlSC)+", "+str(anzahlST)+", "+str(anzahlPA)+", "+str(anzahlE)+", "+str(anzahlSPO)+", "+str(anzahlP)+", "+str(anzahlC)+", "+str(anzahlSP)+")")
    curser.close()
    connection.commit()

def select(connection):
    curser = connection.cursor()
    curser.execute("select * from anzahl")
    result = cursor.fetchall()
    curser.close()
    l=[]
    for d in result:
        l.add[d[0], d[1], d[2], d[3]]
    return l