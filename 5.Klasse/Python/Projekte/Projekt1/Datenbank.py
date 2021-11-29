import mysql.connector

def connectionOpen():
    connection = mysql.connector.connect(host = "localhost", user = "root", passwd = "sh30112002", db = "scheresteinpapier")
    return connection

def connectionClose(connection):
    connection.disconnect()

def insert(connection, anzahlSC, anzahlST, anzahlPA, anzahlE, anzahlSPO, anzahlP, anzahlC, anzahlSP):
    if(anzahlSP>0):
        curser = connection.cursor()
        curser.execute("insert into anzahl values(null, "+str(anzahlSC)+", "+str(anzahlST)+", "+str(anzahlPA)+", "+str(anzahlE)+", "+str(anzahlSPO)+", "+str(anzahlP)+", "+str(anzahlC)+", "+str(anzahlSP)+")")
        curser.close()
        connection.commit()
    

def select(connection):
    curser = connection.cursor()
    curser.execute("select sum(anzahlSchere), sum(anzahlStein), sum(anzahlPapier), sum(anzahlEchse), sum(anzahlSpock), sum(siegePlayer), sum(siegeCOM), sum(anzahlSpiel) from anzahl")
    result = curser.fetchall()
    curser.close()
    ausgabe(result)     
    
def ausgabe(result):
    for d in result:
        print("Anzahl der gewählten Symbole:")
        print("Schere "+str(int(d[0])))
        print("Stein "+str(int(d[1])))
        print("Papier "+str(int(d[2])))
        print("Echse "+str(int(d[3])))
        print("Spock "+str(int(d[4]))+"\n")
        print("Anzahl Siege:")
        print("Spieler "+str(int(d[5])))
        print("Computer "+str(int(d[6]))+"\n")
        print("Anzahl der durchgeführten Spiele:")
        print("Spielanzahl "+str(int(d[7]))+"\n")