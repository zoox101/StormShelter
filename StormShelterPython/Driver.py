import datetime
import time

class Message():
    def __init__(self, phonenumber, message):
        print('Sending "' + message + '" to ' + phonenumber)

class Contact():
    def __init__(self, name, phonenumber):
        self.name = name
        self.phonenumber = phonenumber
    
    def message(self, message):
        Message(self.phonenumber, message)
        
    @staticmethod
    def fromString(string):
        data = string.split(',')
        return Contact(data[0], data[1], data[2])
        
    def toString(self):
        return self.firstname + ' ' + self.lastname

class Customer():
    E_TIME = 2
    def __init__(self, name, contacts):
        self.name = name
        self.contacts = contacts
        self.messaged = False
        
    def message(self, message):
        for contact in self.contacts:
            contact.message(message)
            
    def enter(self):
        self.entertime = datetime.datetime.now()
        self.messaged = False
        
    def exit(self):
        self.entertime = None
        self.messaged = False
        
    def timeInShelter(self):
        if self.entertime != None :
            return (datetime.datetime.now() - self.entertime).total_seconds()
            
    def check(self):
        if not(self.messaged) and self.timeInShelter()>Customer.E_TIME:
            self.messaged = True
            self.message(self.name + ' has been in their shelter for over ' 
            + str(Customer.E_TIME/60) + ' minutes. Please check on them.')
        
contacts = [Contact('Abby', '01234'), Contact('Billy', '56789')]
customer = Customer('Will Booker', contacts)

while True:
    cmd = raw_input()
    if cmd == 'break':
        break
    if cmd == 'enter':
        customer.enter()
    if cmd == 'exit':
        customer.exit()
    if cmd == 'check':
        customer.check()
