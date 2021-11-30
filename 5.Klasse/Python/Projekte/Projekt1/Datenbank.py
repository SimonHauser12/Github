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
        e1=int(d[0])
        e2=int(d[1])
        e3=int(d[2])
        e4=int(d[3])
        e5=int(d[4])
        s1=int(d[5])
        s2=int(d[6])
        k=int(d[7])

        print("Anzahl der gewählten Symbole:")
        print("Schere "+str(e1)+" ("+str(round((e1*100)/(k*2), 2))+"%)")
        print("Stein "+str(e2)+" ("+str(round((e2*100)/(k*2), 2))+"%)")
        print("Papier "+str(e3)+" ("+str(round((e3*100)/(k*2), 2))+"%)")
        print("Echse "+str(e4)+" ("+str(round((e4*100)/(k*2), 2))+"%)")
        print("Spock "+str(e5)+" ("+str(round((e5*100)/(k*2), 2))+"%) \n")
        print("Anzahl Siege:")
        print("Spieler "+str(s1))
        print("Computer "+str(s2)+"\n")
        print("Anzahl der durchgeführten Spiele:")
        print("Spielanzahl "+str(k)+"\n")