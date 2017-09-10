# earthScraper.java

## Project name
earthScraper

## Description 
a reddit image scraper in Java. Designed to scrape images off /r/earthPorn for new wallpapers.

## How to Run
clone the repo and run the code on the command line through javac command or a java-supported ide.
You will have to manually set your user agent, follow the reddit API here: https://github.com/reddit/reddit/wiki/API
and input the path for your files.
## Personal note 
I've always been fascinated by web scraping after tinkering around with it in my first CS course a year and a half ago. 
I've played around with this small project over the course of two weeks with bits of time dedicated here and there, and know that the code isn't too easy on the eyes and the technology used behind it isn't suited for this purpose (it's buggy at times; the whole process could have been much easier with Python + reddit API) 
but I really enjoy Java as a language and wanted to try making a scraper even if it was more tedious and time-consuming. What started out as
a simple desire for new wallpapers led to learning a lot about a particular Java library, html parsing, making connections between URLs and applications, and complying with different API. 

## What's next
The scraping for the most part works, I occasionally run into a specific error regarding SSL:
`sun.security.validator.ValidatorException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target`
I hope to resolve this issue and continue on to building a GUI component to preview stored images.

