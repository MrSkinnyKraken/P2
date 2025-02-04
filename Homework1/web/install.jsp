<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "sob_grup_50";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                //users
                "INSERT INTO " + schema + ".USERS VALUES (NEXT VALUE FOR USER_GEN, 'kafjh@skdjf.com', null, 'sob', 'sob')",
                "INSERT INTO " + schema + ".USERS VALUES (NEXT VALUE FOR USER_GEN, 'rafa@gmail.com', null, 'rafa', 'rafa')",
                "INSERT INTO " + schema + ".USERS VALUES (NEXT VALUE FOR USER_GEN, 'arnau@gmail.com', null, 'arnau', 'arnau')",
                
                //topics
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Computer Science')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Bitcoin')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Java Script')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Blockchains')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'WebAssembly')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'AI')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Machine Learning')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Cloud Computing')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Big Data')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Cybersecurity')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Quantum Computing')",
                "INSERT INTO " + schema + ".TOPIC (id, name) VALUES (NEXT VALUE FOR TOPIC_GEN, 'Virtual Reality')",

                //articulos
                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Introduction to Computer Science', 'Basics of Computer Science.', 'Computer Science is the study of algorithms, data structures, and the principles behind modern computing. This article explores foundational concepts and their real-world applications.', 123, 1, CURRENT_TIMESTAMP, 'computer_science.png', 1)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Understanding Bitcoin', 'Intro to Bitcoin technology.', 'Bitcoin is a decentralized digital currency that operates on blockchain technology. This article explains how Bitcoin works, its uses, and its impact on the financial world.', 87, 2, CURRENT_TIMESTAMP, 'bitcoin.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'JavaScript Fundamentals', 'Learn JavaScript basics.', 'JavaScript is a powerful programming language used to create dynamic and interactive web applications. This article covers its core syntax, concepts, and practical examples.', 202, 3, CURRENT_TIMESTAMP, 'javascript.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Blockchain Essentials', 'The basics of blockchain.', 'Blockchain technology underpins cryptocurrencies and offers secure, decentralized ways to record transactions. This article explains its core principles and potential applications.', 145, 1, CURRENT_TIMESTAMP, 'blockchain.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Getting Started with WebAssembly', 'What is WebAssembly?', 'WebAssembly (Wasm) is a binary instruction format that enables high-performance web applications. This article explores its features, benefits, and how to start using it.', 76, 2, CURRENT_TIMESTAMP, 'webassembly.png', 0)",
                
                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Advanced Computer Science', 'Exploring advanced topics in CS.', 'This article covers advanced algorithms, data structures, and computational theories for seasoned developers.', 312, 1, CURRENT_TIMESTAMP, 'advanced_cs.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Bitcoin Mining Demystified', 'How does Bitcoin mining work?', 'Learn about the intricacies of Bitcoin mining, the technology behind it, and its economic implications.', 98, 2, CURRENT_TIMESTAMP, 'bitcoin_mining.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'JavaScript Advanced Patterns', 'Master advanced JavaScript.', 'This article delves into advanced JavaScript concepts, including closures, prototypal inheritance, and design patterns.', 250, 3, CURRENT_TIMESTAMP, 'js_advanced.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Future of Blockchain', 'Blockchain beyond cryptocurrencies.', 'Discover how blockchain technology is shaping industries like supply chain, healthcare, and beyond.', 180, 1, CURRENT_TIMESTAMP, 'blockchain_future.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'WebAssembly Use Cases', 'Practical Wasm applications.', 'Explore how WebAssembly is revolutionizing web performance and enabling near-native speeds for web apps.', 105, 2, CURRENT_TIMESTAMP, 'wasm_use_cases.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Data Science with Python', 'Intro to data science tools.', 'Learn how Python is used in data analysis, visualization, and machine learning.', 230, 1, CURRENT_TIMESTAMP, 'data_science.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Ethereum: Beyond Bitcoin', 'What makes Ethereum unique?', 'An in-depth look at Ethereum, its smart contracts, and the decentralized applications it enables.', 120, 2, CURRENT_TIMESTAMP, 'ethereum.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'React vs Angular', 'Choosing the right framework.', 'A comprehensive comparison between React and Angular to help developers choose the right tool.', 190, 3, CURRENT_TIMESTAMP, 'react_vs_angular.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Cybersecurity Basics', 'Protecting digital assets.', 'An essential guide to cybersecurity, including best practices and tools for safeguarding online presence.', 175, 1, CURRENT_TIMESTAMP, 'cybersecurity.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Deep Dive into WebAssembly', 'Advanced WebAssembly topics.', 'Explore advanced concepts and performance optimization techniques in WebAssembly.', 140, 2, CURRENT_TIMESTAMP, 'wasm_deep_dive.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Power of Artificial Intelligence', 'AI is transforming industries.', 'This article discusses the evolution of AI and its applications in various sectors like healthcare, finance, and more.', 220, 3, CURRENT_TIMESTAMP, 'ai_power.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Smart Contracts Explained', 'How do smart contracts work?', 'A detailed introduction to smart contracts, their use cases, and the technology behind them.', 110, 1, CURRENT_TIMESTAMP, 'smart_contracts.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Rise of Cloud Computing', 'Exploring the cloud revolution.', 'Cloud computing is changing the way businesses operate. This article explores its key benefits, trends, and providers.', 265, 2, CURRENT_TIMESTAMP, 'cloud_computing.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Exploring Big Data Technologies', 'Big data is reshaping industries.', 'This article dives into the big data landscape, its technologies, and their impact on business decision-making.', 350, 3, CURRENT_TIMESTAMP, 'big_data.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Blockchain for Business', 'Applying blockchain in business.', 'Learn how businesses can leverage blockchain technology to improve efficiency and transparency in operations.', 215, 1, CURRENT_TIMESTAMP, 'blockchain_business.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Decentralized Finance (DeFi)', 'The future of finance.', 'Decentralized finance is revolutionizing the financial sector. This article explores the key concepts of DeFi and its applications.', 195, 2, CURRENT_TIMESTAMP, 'defi.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Deep Learning in AI', 'Understanding deep learning techniques.', 'This article explains deep learning and its role in artificial intelligence, focusing on neural networks and deep neural networks.', 230, 3, CURRENT_TIMESTAMP, 'deep_learning.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Internet of Things (IoT)', 'Connecting devices to the internet.', 'Explore the Internet of Things (IoT), how it works, and its applications in everyday life.', 275, 1, CURRENT_TIMESTAMP, 'iot.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Introduction to Machine Learning', 'Machine learning basics.', 'Learn the fundamentals of machine learning, including supervised and unsupervised learning algorithms.', 160, 2, CURRENT_TIMESTAMP, 'machine_learning.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Building Scalable Web Apps', 'Creating scalable web applications.', 'Learn the best practices for building scalable and high-performance web applications using modern technologies.', 180, 3, CURRENT_TIMESTAMP, 'scalable_apps.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Serverless Computing', 'What is serverless?', 'Serverless computing is gaining traction. Learn what serverless computing is, and its benefits and challenges.', 250, 1, CURRENT_TIMESTAMP, 'serverless.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Augmented Reality (AR)', 'How AR is shaping industries.', 'This article discusses the use of augmented reality in gaming, healthcare, and education, and its potential future applications.', 315, 2, CURRENT_TIMESTAMP, 'ar.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Quantum Computing Explained', 'The next frontier in computing.', 'Quantum computing has the potential to revolutionize computing. This article explains the basic concepts and potential applications of quantum computers.', 400, 3, CURRENT_TIMESTAMP, 'quantum_computing.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Understanding 5G Networks', 'The future of wireless technology.', '5G is set to transform communication networks. This article explores its features, advantages, and potential use cases.', 230, 1, CURRENT_TIMESTAMP, '5g.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Rise of Virtual Reality (VR)', 'Virtual reality in various sectors.', 'Explore how virtual reality is being used in gaming, healthcare, and training environments.', 280, 2, CURRENT_TIMESTAMP, 'vr.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Impact of Big Data', 'Big data insights.', 'Big data is shaping the future of business. This article explores its impact on decision-making, marketing, and customer service.', 210, 3, CURRENT_TIMESTAMP, 'big_data_impact.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'How to Secure Your Cloud Infrastructure', 'Cloud security basics.', 'Learn the best practices to secure your cloud infrastructure, from access control to encryption.', 190, 1, CURRENT_TIMESTAMP, 'cloud_security.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Evolution of Programming Languages', 'A historical look at programming languages.', 'Explore the evolution of programming languages, from early assembly languages to modern high-level languages.', 130, 2, CURRENT_TIMESTAMP, 'programming_languages.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'Artificial Intelligence and Ethics', 'The ethics of AI.', 'As AI grows in power, ethical considerations become increasingly important. This article discusses the key ethical issues surrounding AI.', 175, 3, CURRENT_TIMESTAMP, 'ai_ethics.png', 0)",

                "INSERT INTO " + schema + ".ARTICLES (id, title, summary, fullText, views, author_id, publishedDate, img, isPrivate) " +
                "VALUES (NEXT VALUE FOR ARTICLE_GEN, 'The Future of Autonomous Vehicles', 'Exploring self-driving cars.', 'Autonomous vehicles are set to revolutionize the transport industry. This article explores the technologies behind self-driving cars and their potential impact.', 215, 1, CURRENT_TIMESTAMP, 'autonomous_vehicles.png', 0)",

                
                //credentials
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'sob', 'sob', 1)",
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'rafa', 'rafa', 2)",
                "INSERT INTO " + schema + ".CREDENTIALS VALUES (NEXT VALUE FOR CREDENTIALS_GEN, 'arnau', 'arnau', 3)",
                
                //article topics
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (1, 1)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (2, 2)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (3, 3)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (4, 4)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (5, 5)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (6, 1)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (7, 2)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (8, 3)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (9, 4)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (10, 5)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (11, 9)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (12, 2)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (13, 3)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (14, 10)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (15, 5)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (16, 6)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (17, 4)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (18, 8)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (19, 9)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (20, 4)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (21, 2)",
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (22, 6)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (23, 11)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (24, 7)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (25, 8)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (26, 8)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (27, 12)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (28, 11)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (29, 9)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (30, 12)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (31, 9)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (32, 10)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (33, 1)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (34, 6)", 
                "INSERT INTO " + schema + ".ARTICLE_TOPICS (article_id, topic_id) VALUES (35, 6)"
            };
            for (String datum : data) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
