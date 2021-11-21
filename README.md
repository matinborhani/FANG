# FANG
**Fang** Simple Crawler, Indexer and Search Engine Web Application.

**Java** and **Spring Boot** has been used for implemet this project. **MVC** is architecture of the project

## About Structure of Project
We get a large file with the name `WEBIR`that includes data from university of Tehran, Tebyan, etc.

First, we parse this file with Sax-Parser (https://www.tutorialspoint.com/java_xml/java_sax_parser.htm) and jsoup (https://github.com/jhy/jsoup). These files exist in the `parse` directory. The output file is `textdocs022.txt`.

Then we parse this file and gathering words and create an index table. In this table, we store words and properties. Properties contain URL, docID, count, title and TFIDF(for more information: [Medium.com](https://medium.com/analytics-vidhya/build-your-semantic-document-search-engine-with-tf-idf-and-google-use-c836bf5f27fb)). you can see all properties in `properties.java`.
To save time we save the index table in hashlist.txt and the size is 329 MB!

When the user search, the `spellChecker.java` file suggest the correct word before searching in the index table. work with Bi Gram(for more information: [GeeksforGeeks](https://www.geeksforgeeks.org/spelling-correction-using-k-gram-overlap/#:~:text=The%20steps%20involved%20for%20spelling,are%20finding%20the%20Jaccard%20coefficient).

`SearchEngine.java` in the `controller` directory is used for mapping addresses to proper function. For example localhost/search to `searchProcess` function.

`Main.java` file first Load index Table and dictionary(for Spell Checker) and get a query from the user then call `search process` function and finally prepare result in sort and  paging an HTML page.

## Configuration
Various properties can be specified inside your `application.properties` file. we set port = 8000, you can reset it.

## Run
run `SearchEngineApllication.java`. if you run first time, wait for 15 to 25 min, then **localhost:8000/home** and enjoy it :)






