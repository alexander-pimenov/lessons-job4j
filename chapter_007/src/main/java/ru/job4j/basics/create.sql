CREATE DATABASE Tracker;

--c Tracker;

CREATE TABLE Roles (
  Role_ID serial primary key,
  Description text
);

INSERT INTO Roles(Description) values('Admin');
INSERT INTO Roles(Description) values('User');

CREATE TABLE Rights (
  Right_ID serial primary key,
  Description text
);

INSERT INTO Rights(Description) values('ADD');
INSERT INTO Rights(Description) values('DELETE');
INSERT INTO Rights(Description) values('EDIT');

CREATE TABLE Profile (
  Profile_ID serial primary key,
  Role_ID INTEGER references Roles(Role_ID),
  Right_ID INTEGER references Rights(Right_ID),
  Description text
);

INSERT INTO Profile(Description, Role_ID, Right_ID) values('Profile_1', 1, 1);
INSERT INTO Profile(Description, Role_ID, Right_ID) values('Profile_1', 1, 2);
INSERT INTO Profile(Description, Role_ID, Right_ID) values('Profile_1', 1, 3);
INSERT INTO Profile(Description, Role_ID, Right_ID) values('Profile_2', 2, 1);

CREATE TABLE Users (
  User_ID serial primary key,
  Role_ID INTEGER REFERENCES Roles(Role_ID),
  FirstName varchar (50),
  LastName varchar (50)
);

INSERT INTO Users(FirstName, LastName, Role_ID) values('Vlad', 'Pedro', 1);
INSERT INTO Users(FirstName, LastName, Role_ID) values('Ksu', 'Isadora', 2);
INSERT INTO Users(FirstName, LastName, Role_ID) values('Mike', 'Lodes', 2);

CREATE TABLE Category (
  Category_ID serial primary key,
  Description text
);

INSERT INTO Category(Description) values('Important');
INSERT INTO Category(Description) values('Spam');

CREATE TABLE State (
  State_ID serial primary key,
  Description text
);

INSERT INTO State(Description) values('Active');
INSERT INTO State(Description) values('Solved');

CREATE TABLE Items (
  Item_ID serial primary key,
  State_ID INTEGER REFERENCES State(State_ID),
  Category_ID INTEGER REFERENCES Category(Category_ID),
  User_ID INTEGER REFERENCES Users(User_ID),
  Description text
);

INSERT INTO Items(Description, State_ID, Category_ID, User_ID) values('Where is my money?', 1, 1, 2);
INSERT INTO Items(Description, State_ID, Category_ID, User_ID) values('Buy an elephant!', 1, 2, 3);

CREATE TABLE Comments (
  Comment_ID serial primary key,
  Item_ID INTEGER REFERENCES Items(Item_ID),
  Description text
);

INSERT INTO Comments(Description, Item_ID) values('I do not know', 1);

CREATE TABLE Attaches (
  Attach_ID serial primary key,
  Item_ID INTEGER REFERENCES Items(Item_ID),
  Description text
);

INSERT INTO Attaches(Description, Item_ID) values('img', 1);
