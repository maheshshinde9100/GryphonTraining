# Web Scraping with BeautifulSoup
from bs4 import BeautifulSoup

# Sample HTML content
web_content = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My First Web Page</title>
</head>
<body>

    <h1 id="main-title">Welcome to My Website</h1>

    <div class="content">
        <p class="intro">This is my first paragraph of text.</p>
        <p>This is second paragraph</p>
        <p>This is Third paragraph</p>
    </div>

    <h2>Useful Links</h2>
    <ul>
        <li><a href="https://example.com">Example</a></li>
        <li><a href="https://google.com">Google</a></li>
    </ul>

    <footer>
        <p class="footer-text">Copyright 2026</p>
    </footer>

</body>
</html>
"""
soup = BeautifulSoup(web_content, "html.parser")

print("All paragraph tags:")
paragraphs = soup.find_all("p")
for p in paragraphs:
    print("-", p.text)

print("\nParagraph with class 'intro':")
intro_para = soup.find("p", class_="intro")
print(intro_para.text)

print("\nMain Title:")
title = soup.find(id="main-title")
print(title.text)

print("\nAll links and URLs:")
links = soup.find_all("a")
for link in links:
    print(f"Text: {link.text}, URL: {link['href']}")

print("\nStatistics:")
print("Total paragraphs:", len(paragraphs))
print("Total links:", len(links))

print("\nClean page text:")
print(soup.get_text(strip=True))
