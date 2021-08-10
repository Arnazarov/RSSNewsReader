# RSSNewsReader
Simple app that reads from RSS news feed using JavaFX


A RSS News reader that will get the RSS feed from some news source and present each news story using a JavaFX ListView. Each story should have a URL, a description and a picture if available. For the following project I have used this link: http://rss.cnn.com/rss/edition.rss

The app written in MVC architecture and has following classes, files and methods: 
  - Main.java (Main class to run the program)
      - main() method launches the app
      - start() method starts the app (includes lambda expressions)
  
  - Model.java (Data)
      - getInputStream() method gets InputStream from above URL link
      - getNewsItems() methods gets ListNode representing all news items
      - getTitles() methods gets ArrayList of all news titles
      - getNewsItemByTitle() method gets news item by selected title
      - getLink() method gets the link of the selected news title
      - getDescription() method gets the description of the selected news title
      - getImage() method gets the image of the selected news title
  
  - Controller.java (Application logic)
      - initialize() method initializes the Model and View (includes anonymous inner class)
 
 - View.fxml (User-interface)
 
 Screenshot:
 ![Image of example screenshot] (https://github.com/Arnazarov/RSSNewsReader/blob/master/Screenshot.JPG)
 
