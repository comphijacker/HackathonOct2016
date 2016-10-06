# HackathonOct2016
Blockchain based money flow using QR code

As we know that there are many social payment options are available in the market like Venmo,Facebook messanger,Square cash where a person can easily transfer money from one to another through tweet,chat or email.
I think when money is being transferred  it should be  sent with QR code and if receiver goes to the ATM with that code and scans the QIR code to withdraw the money instantly .Now to solve the issue related to the ledger balancing and duplicate payment we can use Blockchain at the back end.On top of it can also support to the digital currencies like Bitcoin,Ethereum,Litecoin.

User stories for this application-

Front end-
1-As a user I want a page/screen where I can fill the payment information to make a payment.
2-As a user I want to have a submit button to trasfer money.
3-As a user I want confirmation page showing that the amount has been transfer.
4-As a user I should have a profile page where I can see my total amount in my wallet.

Back end-
1- Read the data sent by the form
2- Enter the data into blockchain database and deduct the amount from sender account
3- Read the data from database
4- Generate a QR code and mail it to the recipeint.
5- Read the data through QR APP/Read program
6- Add the amount in recipeint account.



