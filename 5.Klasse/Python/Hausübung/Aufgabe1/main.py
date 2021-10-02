import test as bu

# Beginn der Funktion
def add(x, y): # Methodenkopf mit KEYWORD NAME PARAMETER DOPPELPUNKT
    return x+y # Statements sind 4 Leerzeichen (KEINE TABSTOPS) eingerückt
# Ende der Funktion

# print(add(3, 3))
print("__name__ ist: ", __name__)

if __name__ == "__main__":
    print(add(3, 2))
    bu.superActionFunction(3,2)
    bu.prototypeFunction()