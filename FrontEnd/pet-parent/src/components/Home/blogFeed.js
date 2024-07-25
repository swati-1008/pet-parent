import noImageFound from '../../assets/images/Home/Blogs/noImageFound.jpeg';

const fetchBlogFeed = async (url) => {
    try {
        // Fetching the RSS feed data from the provided url
        const response = await fetch(url);
        const text = await response.text();

        // Parsing the XML response into a DOM document using DOM Parser
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(text, 'application/xml');

        // Extracting data from each <item> element in the XML
        const items = Array.from(xmlDoc.querySelectorAll('item')).map(item => {
            // Extract image URL from description tag
            const descriptionElement = item.querySelector('description');
            let imageUrl = noImageFound;
            if (descriptionElement) {
                // Parse the HTML content inside <description> tag using a temporary div element
                const tempDiv = document.createElement('div');
                tempDiv.innerHTML = descriptionElement.textContent;
                const img = tempDiv.querySelector('img');
                if (img) {
                    imageUrl = img.getAttribute('src');
                }
            }

            // Handle description to strip HTML tags and get text content
            let description = '';
            if (descriptionElement) {
                const tempDiv = document.createElement('div');
                tempDiv.innerHTML = descriptionElement.textContent;
                description = tempDiv.textContent || tempDiv.innerText || '';
            }

            // Extract author from dc:creator tag using namespace
            // http://purl.org/dc/elements/1.1/ is the URI that uniquely identifies the Dublin Core namespace.
            // creator specifies the local name of the element within the namespace
            const creatorElement = item.getElementsByTagNameNS('http://purl.org/dc/elements/1.1/', 'creator');
            const author = creatorElement.length > 0 ? creatorElement[0].textContent : 'Unknown Author';

            return {
                image: imageUrl,
                title: item.querySelector('title').textContent,
                description: description,
                author: author,
                link: item.querySelector('link').textContent, 
            };
        });

        return items;
    } catch (error) {
        console.error('Error fetching RSS feed:', error);
        return [];
    }
};

export default fetchBlogFeed;
