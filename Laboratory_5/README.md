# Laboratory 5
- In resources folder I created a **persistence.xml** file
- In payara admin page I created a connection pool and a JDBC resource
- I structured my application in layers: controllers (managed beans), repositories and entities (models)
- Firstly, I created an application in which the users were able to create a 'generic' (not specific) exam
- I extended my application by adding new types of exam: written and project 
  - a **written** exam has number of pages
  - a **project** exam has bibliography
  - this features I chose to implement with a generic repository. I tried to generalise also the get method, but I didn't have success with "Class<T> persistentClass"
- In the add exam page the user have the possibility either to add a written exam or a project one
- In every Controller is **injected** a repository
- In every Repository is **injected** an EntityManager
- In test folder is a test file where are unit tests which test my application logic 

- In **search** folder I implemented the logic for searching written exams by name as a default criteria. Because I created a SearchField class, the search functionality could be extended. 
