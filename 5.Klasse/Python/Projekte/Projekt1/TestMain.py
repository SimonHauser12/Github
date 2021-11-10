import SchereSteinPapier as SSP

def kontrolle(ein):
    if(ein=="j"):
        return False
    if(ein=="n"):
        return False
    if(ein!="j" and ein!="n"):
        print("Falsche Eingabe: NUR j und n erlaubt!")
        return True
    
def erneut():
    r=True
    while(r):
        print("Erneut Spielen? (j, n)")
        ein=input()
        r=kontrolle(ein)
        if(ein=="n"):
            return False
        if(ein=="j"):
            return True

def Main():
    psieg=0
    csieg=0
    zaehler=0
    loop=True
    while(loop):
        ein1=SSP.willkommen()
        loop=kontrolle(ein1)
    if(ein1=="j"):
        l=True
        while(l):
            a = SSP.start(psieg,csieg, zaehler)
            print(str(a[0])+","+str(a[1])+","+str(a[2]))
            psieg=a[0]
            csieg=a[1]
            zaehler=a[2]
            l=erneut()
    if(ein1=="n"):
        print("Spiel wird beendet!")
    print("Spiel beendet!")

if __name__ == "__main__":
    Main()