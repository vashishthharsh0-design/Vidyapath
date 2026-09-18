package com.example.data

import com.example.model.*

object NcertBookRepository {

    val books: List<NcertBook> = listOf(
        // ====================================================================
        // CLASS 8 NCERT TEXTBOOKS (CBSE CURRICULUM)
        // ====================================================================
        NcertBook(
            id = "ncert_c8_sci",
            title = "Science (Class 8)",
            hindiTitle = "विज्ञान (कक्षा 8)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.SCIENCE_GENERAL,
            subjectCategory = "Science & Technology",
            cbseBookCode = "NCERT-8-SCI (hesc1)",
            description = "Standard CBSE textbook covering fundamental Physics, Chemistry, and Biology principles: Crop production, Microorganisms, Combustion, Reproduction, Force, Sound, and Light.",
            coverColorHex = 0xFF00897B, // Teal
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hesc1=0-13",
            chapters = listOf(
                NcertChapterInfo(1, "Crop Production and Management", "फसल उत्पादन एवं प्रबंध", "Agricultural practices, soil preparation, sowing, manures vs fertilizers, drip and sprinkler irrigation, harvesting, and grain storage.", listOf("Kharif vs Rabi Crops", "Manure vs Chemical Fertilizers", "Modern Drip & Sprinkler Irrigation", "Granary & Silos Storage"), matchingChapterId = "c8_sci_ch1"),
                NcertChapterInfo(2, "Microorganisms: Friend and Foe", "सूक्ष्मजीव: मित्र एवं शत्रु", "Classification of bacteria, fungi, protozoa, algae, viruses; beneficial fermentation and antibiotic production; food preservation techniques.", listOf("Antibiotics (Penicillin)", "Food Preservation (Pasteurisation)", "Nitrogen Fixation & Nitrogen Cycle")),
                NcertChapterInfo(3, "Coal and Petroleum", "कोयला और पेट्रोलियम", "Exhaustible natural resources, fractional distillation of petroleum, fractions (petrol, diesel, kerosene, paraffin), coal tar, and CNG.", listOf("Fossil Fuels Formation", "Fractions of Petroleum", "Conservation of Energy")),
                NcertChapterInfo(4, "Combustion and Flame", "दहन और ज्वाला", "Conditions for combustion, ignition temperature, inflammable substances, fire control, structure of a candle flame, and fuel calorific value.", listOf("Ignition Temperature", "Zones of Candle Flame (Dark, Luminous, Non-luminous)", "Calorific Value (kJ/kg)")),
                NcertChapterInfo(5, "Conservation of Plants and Animals", "पौधे एवं जंतुओं का संरक्षण", "Deforestation impacts, biodiversity conservation, Wildlife Sanctuaries, National Parks, Biosphere Reserves, endemic species, and Red Data Book.", listOf("Biosphere Reserves", "Flora and Fauna", "Red Data Book & Project Tiger")),
                NcertChapterInfo(6, "Reproduction in Animals", "जंतुओं में जनन", "Sexual reproduction in animals, male and female reproductive systems, fertilization (internal vs external), zygote and embryo, metamorphosis in frogs.", listOf("Internal vs External Fertilization", "Zygote & Foetus Formation", "Asexual Reproduction (Binary Fission & Budding)")),
                NcertChapterInfo(7, "Reaching the Age of Adolescence", "किशोरावस्था की ओर", "Changes at puberty, secondary sexual characters, endocrine glands (Pituitary, Thyroid, Adrenal, Pancreas), hormones, sex determination in humans.", listOf("Role of Hormones & Pituitary", "Sex Determination (XX vs XY)", "Nutritional Needs of Adolescents")),
                NcertChapterInfo(8, "Force and Pressure", "बल तथा दाब", "Push or pull, contact vs non-contact forces (gravitational, electrostatic, magnetic), pressure formula P = F/A, fluid pressure, atmospheric pressure.", listOf("Types of Forces", "Formula P = Force / Area", "Liquid Pressure with Depth", "Atmospheric Pressure"), matchingChapterId = "c8_sci_ch8"),
                NcertChapterInfo(9, "Friction", "घर्षण", "Factors affecting friction, interlocking of irregularities, Static, Sliding, and Rolling friction (Static > Sliding > Rolling), fluid friction and drag.", listOf("Static vs Sliding vs Rolling Friction", "Friction as a Necessary Evil", "Lubrication and Streamlining")),
                NcertChapterInfo(10, "Sound", "ध्वनि", "Sound produced by vibrating objects, sound propagation through medium (cannot travel in vacuum), human voice box (larynx), human ear, frequency and pitch.", listOf("Propagation in Solids/Liquids/Gases", "Human Voice Box & Ear Anatomy", "Frequency, Amplitude and Pitch")),
                NcertChapterInfo(11, "Chemical Effects of Electric Current", "विद्युत धारा के रासायनिक प्रभाव", "Conductors vs insulators in liquids, electrolysis, chemical decomposition, electroplating process (copper plating on spoons) and industrial applications.", listOf("Liquids as Conductors", "Electroplating Principle", "Corrosion Protection Applications")),
                NcertChapterInfo(12, "Some Natural Phenomena", "कुछ प्राकृतिक परिघटनाएँ", "Electric charges by friction, electroscope, lightning and lightning conductors, earthquake causes, fault zones, and the Richter scale.", listOf("Charging by Rubbing", "Lightning Conductor Safety", "Fault Zones & Richter Scale")),
                NcertChapterInfo(13, "Light", "प्रकाश", "Laws of reflection, regular vs diffused reflection, plane mirror images (lateral inversion), human eye anatomy, rods and cones, Braille system.", listOf("Laws of Reflection (i = r)", "Multiple Images (Kaleidoscope)", "Human Eye Structure & Power of Accommodation", "Braille Code for Visually Impaired"))
            )
        ),
        NcertBook(
            id = "ncert_c8_math",
            title = "Mathematics (Class 8)",
            hindiTitle = "गणित (कक्षा 8)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.MATHEMATICS,
            subjectCategory = "Mathematics",
            cbseBookCode = "NCERT-8-MATH (hemh1)",
            description = "Complete CBSE Class 8 Mathematics syllabus: Rational Numbers, Linear Equations, Geometry, Mensuration, Exponents, Factorisation, and Graphs.",
            coverColorHex = 0xFF1565C0, // Blue
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hemh1=0-13",
            chapters = listOf(
                NcertChapterInfo(1, "Rational Numbers", "परिमेय संख्याएँ", "Properties of rational numbers (closure, commutative, associative, distributive), role of 0 and 1, additive and multiplicative inverse.", listOf("Properties of Rational Numbers", "Additive vs Multiplicative Inverse", "Distributive Property"), matchingChapterId = "c8_math_ch1"),
                NcertChapterInfo(2, "Linear Equations in One Variable", "एक चर वाले रैखिक समीकरण", "Solving linear equations with variables on one side and both sides, transposing terms, and real-world algebraic word problems.", listOf("Solving Equations by Transposition", "Equations Reducible to Linear Form", "Age & Digit Word Problems")),
                NcertChapterInfo(3, "Understanding Quadrilaterals", "चतुर्भुजों को समझना", "Polygons, sum of interior angles (n-2)×180°, exterior angles sum 360°, properties of Parallelograms, Rhombus, Rectangle, Square, and Trapezium.", listOf("Sum of Angles of Polygon", "Properties of Parallelogram", "Special Quadrilaterals (Square, Rhombus)")),
                NcertChapterInfo(4, "Data Handling", "आँकड़ों का प्रबंधन", "Organising data, grouped frequency distribution, histograms with equal intervals, circle graphs / pie charts, and simple probability concepts.", listOf("Grouping Data & Histograms", "Drawing Pie Charts", "Chance and Probability Outcomes")),
                NcertChapterInfo(5, "Squares and Square Roots", "वर्ग और वर्गमूल", "Square numbers, Pythagorean triplets (2m, m²-1, m²+1), finding square roots by prime factorisation and long division method, decimal square roots.", listOf("Pythagorean Triplets", "Prime Factorisation Method", "Long Division for Square Roots")),
                NcertChapterInfo(6, "Cubes and Cube Roots", "घन और घनमूल", "Cube numbers, units digit patterns of cubes, prime factorisation method for cube roots, Hardy-Ramanujan numbers (1729).", listOf("Cube Numbers Patterns", "Finding Cube Roots by Factorisation", "Hardy-Ramanujan Number 1729")),
                NcertChapterInfo(7, "Comparing Quantities", "राशियों की तुलना", "Ratios and percentages, discount calculation, sales tax and Goods & Services Tax (GST), compound interest formula A = P(1 + R/100)ⁿ.", listOf("Discount & Sales Tax (GST)", "Simple vs Compound Interest", "Compounded Annually & Half-Yearly")),
                NcertChapterInfo(8, "Algebraic Expressions and Identities", "बीजीय व्यंजक एवं सर्वसमिकाएँ", "Monomials, binomials, polynomials, multiplication of expressions, standard algebraic identities (a+b)², (a-b)², and (a+b)(a-b).", listOf("Multiplying Polynomials", "Standard Algebraic Identities", "Applying Identities in Fast Calculations")),
                NcertChapterInfo(9, "Mensuration", "क्षेत्रमिति", "Area of trapezium and general quadrilaterals, surface area of cube, cuboid, cylinder; volume of cube, cuboid, and cylinder (V = πr²h).", listOf("Area of Trapezium & Polygons", "Total & Lateral Surface Area of Cylinder", "Volume of 3D Solid Shapes")),
                NcertChapterInfo(10, "Exponents and Powers", "घातांक और घात", "Powers with negative exponents, laws of exponents for integral exponents (aᵐ × aⁿ = aᵐ⁺ⁿ, (aᵐ)ⁿ = aᵐⁿ), expressing small numbers in standard scientific form.", listOf("Laws of Integral Exponents", "Negative Exponent Rules", "Standard Scientific Notation")),
                NcertChapterInfo(11, "Direct and Inverse Proportions", "सीधा और प्रतिलोम समानुपात", "Direct variation (x/y = k) vs inverse variation (x·y = k), unitary method shortcuts, and worker-time proportional word problems.", listOf("Direct Proportion Formula x1/y1 = x2/y2", "Inverse Proportion Formula x1·y1 = x2·y2", "Speed, Time & Work Problems")),
                NcertChapterInfo(12, "Factorisation", "गुणनखंडन", "Method of common factors, factorisation by grouping terms, factorisation using algebraic identities, and division of algebraic expressions.", listOf("Factoring by Common Monomials", "Factoring by Grouping", "Splitting the Middle Term", "Polynomial Division")),
                NcertChapterInfo(13, "Introduction to Graphs", "आलेखों से परिचय", "Line graphs, Cartesian coordinate axes, plotting points (x, y), linear graphs, and independent vs dependent variable graphs.", listOf("Cartesian Coordinate Plane (x, y)", "Reading & Plotting Coordinates", "Interpreting Linear Graphs"))
            )
        ),
        NcertBook(
            id = "ncert_c8_hist",
            title = "Our Pasts – III (History)",
            hindiTitle = "हमारे अतीत – III (इतिहास)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.HISTORY,
            subjectCategory = "Social Science - History",
            cbseBookCode = "NCERT-8-HIST (hess1)",
            description = "Modern Indian History from East India Company establishment, rural revenue systems, 1857 revolt, colonial education, social reform movements, to India's Independence.",
            coverColorHex = 0xFFC2185B, // Rose
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hess1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "Introduction: How, When and Where", "कैसे, कब और कहाँ", "Periodisation of Indian history by James Mill into Hindu, Muslim, British; official records of British administration, surveys, and colonial archives.", listOf("James Mill's Periodisation Critique", "Official Archives & National Documentation", "Sources of Modern Indian History")),
                NcertChapterInfo(2, "From Trade to Territory", "व्यापार से साम्राज्य तक", "Mercantilism, Battle of Plassey (1757), Battle of Buxar (1764), Subsidiary Alliance, Doctrine of Lapse, Anglo-Maratha and Anglo-Mysore wars.", listOf("Battle of Plassey & Robert Clive", "Subsidiary Alliance System", "Doctrine of Lapse (Dalhousie)")),
                NcertChapterInfo(3, "Ruling the Countryside", "ग्रामीण क्षेत्र पर शासन चलाना", "Company's revenue systems: Permanent Settlement (1793 in Bengal), Mahalwari system, Ryotwari system (Munro); the Blue Rebellion of Indigo cultivators.", listOf("Permanent Settlement (Lord Cornwallis)", "Mahalwari vs Ryotwari Systems", "Indigo Cultivation & Blue Rebellion")),
                NcertChapterInfo(4, "Tribals, Dikus and the Vision of a Golden Age", "आदिवासी, दीकु और एक स्वर्ण युग की कल्पना", "Livelihood of tribal societies (Jhum slash-and-burn cultivators, hunter-gatherers, pastoralists), colonial forest laws, Birsa Munda and Munda rebellion.", listOf("Jhum Cultivators vs Settled Tribals", "Impact of British Forest Laws", "Birsa Munda's Ulgulan Movement")),
                NcertChapterInfo(5, "When People Rebel: 1857 and After", "जब जनता बग़ावत करती है: 1857 और उसके बाद", "Causes of 1857 Revolt: Greased cartridges, Doctrine of Lapse, Meerut mutiny; leadership by Bahadur Shah Zafar, Rani Lakshmibai, Kunwar Singh; British reprisals.", listOf("Causes of the Revolt of 1857", "Key Centres and Leaders", "Government of India Act 1858 (Crown Rule)")),
                NcertChapterInfo(6, "Civilising the \"Native\", Educating the Nation", "देशी जनता को सभ्य बनाना, राष्ट्र को शिक्षित करना", "Orientalists (William Jones) vs Anglicists (Thomas Macaulay), Macaulay's Minute 1835, Wood's Despatch 1854, Mahatma Gandhi and Rabindranath Tagore's Shantiniketan.", listOf("Macaulay's Minute of 1835", "Wood's Despatch of 1854", "Tagore's Shantiniketan vs Gandhi's Nai Talim")),
                NcertChapterInfo(7, "Women, Caste and Reform", "महिलाएँ, जाति एवं सुधार", "Abolition of Sati (1829 by Raja Rammohun Roy), Widow Remarriage Act (1856 by Vidyasagar), girls' schools, Jyotirao Phule's Gulamgiri, Dr. BR Ambedkar's temple entry.", listOf("Raja Rammohun Roy & Brahmo Samaj", "Ishwar Chandra Vidyasagar & Widow Remarriage", "Jyotirao Phule (Gulamgiri) & Periyar")),
                NcertChapterInfo(8, "The Making of the National Movement: 1870s–1947", "राष्ट्रीय आंदोलन का संघटन: 1870 के दशक से 1947 तक", "Formation of Indian National Congress (1885), Moderates vs Extremists, Partition of Bengal (1905), Rowlatt Satyagraha, Non-Cooperation, Civil Disobedience, Quit India Movement.", listOf("Founding of Congress (1885)", "Rowlatt Act & Jallianwala Bagh (1919)", "Dandi March (Salt Satyagraha 1930)", "Quit India Movement (1942)"))
            )
        ),
        NcertBook(
            id = "ncert_c8_geog",
            title = "Resource and Development (Geography)",
            hindiTitle = "संसाधन एवं विकास (भूगोल)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.GEOGRAPHY,
            subjectCategory = "Social Science - Geography",
            cbseBookCode = "NCERT-8-GEOG (hess2)",
            description = "CBSE Geography text covering Natural Resources, Land, Soil, Water, Agriculture, Industries, and Human Capital development.",
            coverColorHex = 0xFF5D4037, // Brown
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hess2=0-5",
            chapters = listOf(
                NcertChapterInfo(1, "Resources", "संसाधन", "Definition of resource, utility and value, classification into Natural, Human-Made, and Human Resources; concept of Sustainable Development and conservation principles.", listOf("Natural vs Human Resources", "Renewable vs Non-Renewable", "Principles of Sustainable Development")),
                NcertChapterInfo(2, "Land, Soil, Water, Natural Vegetation and Wildlife", "भूमि, मृदा, जल, प्राकृतिक वनस्पति और वन्य जीवन", "Land use patterns, soil formation factors (Parent rock, Climate, Relief, Time, Organisms), soil degradation mitigation (Terrace farming, Shelter belts), water crisis, CITES treaty.", listOf("Soil Profile & Weathering", "Soil Conservation Techniques", "Water Harvesting & CITES Convention")),
                NcertChapterInfo(3, "Agriculture", "कृषि", "Primary activity, Subsistence farming (Intensive & Primitive/Shifting) vs Commercial farming (Grain, Mixed, Plantation), major crops (Rice, Wheat, Millets, Cotton, Jute, Tea, Coffee).", listOf("Subsistence vs Commercial Agriculture", "Major Food & Fiber Crops (Soil & Climate)", "Agricultural Development (India vs USA Farm)")),
                NcertChapterInfo(4, "Industries", "उद्योग", "Secondary economic activity, classification by raw materials (Agro, Mineral, Marine, Forest), size (Small vs Large scale), and ownership (Private, Public, Joint, Co-operative); Industrial regions and IT hubs.", listOf("Classification of Industries", "Factors Influencing Industrial Location", "Iron & Steel (Jamshedpur/Pittsburgh)", "Information Technology (Bengaluru/Silicon Valley)")),
                NcertChapterInfo(5, "Human Resources", "मानव संसाधन", "People as a nation's greatest asset, population distribution and density, factors affecting distribution (Geographical, Economic, Cultural), population change rates, and population pyramid analysis.", listOf("Population Density Formula", "Birth Rate, Death Rate & Migration", "Interpreting Age-Sex Population Pyramids"))
            )
        ),
        NcertBook(
            id = "ncert_c8_civ",
            title = "Social and Political Life – III (Civics)",
            hindiTitle = "सामाजिक एवं राजनीतिक जीवन – III (नागरिक शास्त्र)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.POLITY,
            subjectCategory = "Social Science - Civics",
            cbseBookCode = "NCERT-8-CIV (hess3)",
            description = "The Indian Constitution, Fundamental Rights, Secularism, Parliament, Indian Judiciary, Marginalisation, and Social Justice.",
            coverColorHex = 0xFFE65100, // Deep Orange
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hess3=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "The Indian Constitution", "भारतीय संविधान", "Why does a country need a Constitution? Key features: Federalism, Parliamentary Form of Government, Separation of Powers, Fundamental Rights, and Secularism.", listOf("Preamble & Constitutional Ideals", "Federalism vs Separation of Powers", "The Six Fundamental Rights of Citizens")),
                NcertChapterInfo(2, "Understanding Secularism", "धर्मनिरपेक्षता की समझ", "Meaning of secularism, why separation of religion from State is essential, Indian secularism vs American secularism, State non-interference with exceptions.", listOf("Separation of Religion & State", "Indian Model of Secularism", "Freedom to Practice Religion")),
                NcertChapterInfo(3, "Parliament and the Making of Laws", "संसद तथा कानूनों का निर्माण", "Why people should decide in democracy, role of Parliament (Lok Sabha & Rajya Sabha), functions of MPs, how a Bill becomes a Law, Domestic Violence Act 2005.", listOf("Role of Lok Sabha & Rajya Sabha", "Legislative Procedure for Law Making", "Civil Society Role (Domestic Violence Act)")),
                NcertChapterInfo(4, "Judiciary", "न्यायपालिका", "Role of the Judiciary: Dispute resolution, Judicial review, upholding Fundamental Rights; Structure of courts in India (Supreme Court, High Courts, Subordinate courts), Public Interest Litigation (PIL).", listOf("Three-Tier Court Hierarchy", "Independent Judiciary & Judicial Review", "Public Interest Litigation (PIL) Significance")),
                NcertChapterInfo(5, "Understanding Marginalisation", "हाशियाकरण की समझ", "Definition of marginalisation, who are Adivasis (tribals, stereotypes, displacement), Muslims and marginalisation, Sachar Committee report recommendations.", listOf("Adivasis & Forest Displacement", "Minorities & Sachar Committee Findings", "Social & Economic Vulnerability")),
                NcertChapterInfo(6, "Confronting Marginalisation", "हाशियाकरण से निपटना", "Invoking Fundamental Rights (Article 15, Article 17 untouchability abolition), Scheduled Castes and Scheduled Tribes (Prevention of Atrocities) Act 1989, reservations policy.", listOf("Constitutional Safeguards (Article 15, 17, 21)", "Prevention of Atrocities Act 1989", "Promoting Social Justice via Reservations")),
                NcertChapterInfo(7, "Public Facilities", "जनसुविधाएँ", "Water and the People of Chennai, Water as part of Fundamental Right to Life (Article 21), government's responsibility for public amenities (sanitation, electricity, transport).", listOf("Public Facilities as Public Goods", "Article 21 (Right to Clean Drinking Water)", "Government Budget & Revenue Collection")),
                NcertChapterInfo(8, "Law and Social Justice", "कानून और सामाजिक न्याय", "Worker safety and minimum wages, Bhopal Gas Tragedy 1984, corporate liability, environmental protection laws as public goods, enforcement challenges.", listOf("Minimum Wages Act Provisions", "Bhopal Gas Tragedy & Corporate Negligence", "Environment Protection as a Fundamental Right"))
            )
        ),
        NcertBook(
            id = "ncert_c8_eng",
            title = "Honeydew & It So Happened (English)",
            hindiTitle = "हनीड्यू एवं इट सो हैपन्ड (अंग्रेजी)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.ENGLISH,
            subjectCategory = "Language - English",
            cbseBookCode = "NCERT-8-ENG (heen1)",
            description = "CBSE Class 8 English literature textbooks containing prose, poetry, reading comprehension, grammar in context, and moral life themes.",
            coverColorHex = 0xFF6A1B9A, // Purple
            officialNcertUrl = "https://ncert.nic.in/textbook.php?heen1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "The Best Christmas Present in the World", "क्रिसमस का सर्वश्रेष्ठ उपहार", "Michael Morpurgo's poignant story about a letter found in a desk regarding the Christmas Day 1914 World War I truce between British and German troops.", listOf("Theme of Peace Over War", "Poem: The Ant and the Cricket (Fable on Hard Work)")),
                NcertChapterInfo(2, "The Tsunami", "सुनामी", "Stories of courage and resilience from the 2004 Indian Ocean Tsunami in Andaman & Nicobar islands and Thailand (Tilly Smith's quick geography action).", listOf("Courage & Disaster Response", "Poem: Geography Lesson (Zulfikar Ghose)")),
                NcertChapterInfo(3, "Glimpses of the Past", "अतीत की झलकियाँ", "Pictorial comic narrative of India's freedom struggle from 1757 to 1857, early reformers, British atrocities, and uprising sparks.", listOf("Historical Comic Narrative", "Poem: Macavity: The Mystery Cat (T.S. Eliot)")),
                NcertChapterInfo(4, "Bepin Choudhury's Lapse of Memory", "बिपिन चौधरी की स्मृति का खोना", "Satyajit Ray's witty psychological tale about a prank played by Chunilal to teach his forgetful affluent friend an unforgettable moral lesson.", listOf("Humour, Irony and Friendship", "Poem: The Last Bargain (Rabindranath Tagore)")),
                NcertChapterInfo(5, "The Summit Within", "भीतर का शिखर", "Major H.P.S. Ahluwalia's inspiring philosophical account of conquering Mount Everest (1965) and the higher internal summit of the human spirit.", listOf("Conquering the Internal Mind", "Poem: The School Boy (William Blake)")),
                NcertChapterInfo(6, "This is Jody's Fawn", "यह जोडी का मृगछौना है", "Marjorie Kinnan Rawlings' heartfelt story of a young boy's determination and compassion to find and nurture the orphaned fawn whose mother saved his father's life.", listOf("Compassion Towards Nature", "Responsibility & Animal Empathy")),
                NcertChapterInfo(7, "A Visit to Cambridge", "कैंब्रिज की एक यात्रा", "Firdaus Kanga's moving interaction with renowned theoretical physicist Stephen Hawking, exploring physical disability, intellectual brilliance, and resilience.", listOf("Interview with Stephen Hawking", "Overcoming Physical Challenges", "Poem: When I Set Out for Lyonnesse (Thomas Hardy)")),
                NcertChapterInfo(8, "A Short Monsoon Diary", "मानसून की एक संक्षिप्त डायरी", "Ruskin Bond's evocative diary entries recording the arrival, rhythms, flora, fauna, and quiet solitude of the monsoon in Mussoorie hills.", listOf("Nature Writing & Ruskin Bond Style", "Poem: On the Grasshopper and Cricket (John Keats)")),
                NcertChapterInfo(9, "It So Happened (Supplementary Highlights)", "पूरक पाठ्यपुस्तक झलकियाँ", "Selected stories: How the Camel Got His Hump, The Selfish Giant (Oscar Wilde), Princess September, and Jalebis.", listOf("Moral Stories & Humorous Tales", "Character Sketch and Thematic Analysis"))
            )
        ),
        NcertBook(
            id = "ncert_c8_hin",
            title = "Vasant Bhag 3 (Hindi Reader)",
            hindiTitle = "वसंत भाग 3 (हिंदी)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.HINDI,
            subjectCategory = "Language - Hindi",
            cbseBookCode = "NCERT-8-HIN (heds1)",
            description = "CBSE Class 8 Hindi literature: Classic poetry, satirical essays, historical accounts, short stories by Premchand, Nirala, Dinkar, and Harishankar Parsai.",
            coverColorHex = 0xFFD84315, // Orange Red
            officialNcertUrl = "https://ncert.nic.in/textbook.php?heds1=0-14",
            chapters = listOf(
                NcertChapterInfo(1, "ध्वनि (कविता)", "सूर्यकांत त्रिपाठी 'निराला'", "युवा उत्साह और आशावाद से भरपूर छायावादी कविता जिसमें कवि जीवन में कभी हार न मानने का संदेश देते हैं।", listOf("निराला जी का आशावादी दृष्टिकोण", "प्रकृति का नवजागरण", "काव्य सौंदर्य एवं छंद")),
                NcertChapterInfo(2, "लाख की चूड़ियाँ (कहानी)", "कामतानाथ", "बदलू मनिहार की कहानी के माध्यम से मशीनी युग द्वारा पारंपरिक ग्रामीण कारीगरों की आजीविका पर पड़े आघात का संवेदनशील चित्रण।", listOf("शहरीकरण बनाम ग्रामीण कुटीर उद्योग", "मशीनी युग की चुनौतियाँ", "बदलू का स्वाभिमान")),
                NcertChapterInfo(3, "बस की यात्रा (व्यंग्य)", "हरिशंकर परसाई", "खटारा बस की हास्य-व्यंग्यात्मक यात्रा के माध्यम से परिवहन व्यवस्था और मानव जीवन की लाचारी पर तीखा व्यंग्य।", listOf("परसाई जी की व्यंग्य शैली", "निजी बस ऑपरेटरों की लापरवाही", "हास्य के माध्यम से सामाजिक संदेश")),
                NcertChapterInfo(4, "दीवानों की हस्ती (कविता)", "भगवतीचरण वर्मा", "मस्तमौला और देशप्रेमी वीरों की जीवन दृष्टि, जो जहाँ भी जाते हैं खुशियाँ और स्नेह बिखेरते हैं।", listOf("मस्तमौला स्वभाव का चित्रण", "देशभक्ति एवं त्याग की भावना", "सुख-दुख को समान भाव से ग्रहण करना")),
                NcertChapterInfo(5, "चिट्ठियों की अनूठी दुनिया (निबंध)", "अरविंद कुमार सिंह", "पत्रों के ऐतिहासिक, सांस्कृतिक और भावनात्मक महत्व पर विचारोत्तेजक निबंध।", listOf("पत्रों की ऐतिहासिक यात्रा", "एसएमएस बनाम हस्तलिखित पत्र", "महात्मा गांधी और नेहरू जी के पत्र संग्रह")),
                NcertChapterInfo(6, "भगवान के डाकिए (कविता)", "रामधारी सिंह 'दिनकर'", "पक्षी और बादल को विश्व-बंधुत्व और प्रेम का संदेश फैलाने वाले भगवान के डाकियों के रूप में प्रस्तुत किया गया है।", listOf("विश्व-बंधुत्व की भावना", "प्रकृति में सीमाओं का अभाव", "प्रेम एवं सद्भाव का संदेश")),
                NcertChapterInfo(7, "क्या निराश हुआ जाए (निबंध)", "हजारीप्रसाद द्विवेदी", "वर्तमान समय में फैले भ्रष्टाचार और निराशा के वातावरण के बीच भारतीय मानवीय मूल्यों की अमरता का प्रतिपादन।", listOf("सकारात्मक जीवन दृष्टि", "आदर्शवाद एवं मानवीय मूल्य", "समाचार पत्रों में नकारात्मकता पर विचार")),
                NcertChapterInfo(8, "कबीर की साखियाँ", "संत कबीरदास", "ज्ञान, नीति और सामाजिक समरसता की अमृतवाणी: जाति न पूछो साधु की, आवत गारी एक है, माला तो कर में फिरै।", listOf("कबीर का ज्ञानमार्गी दृष्टिकोण", "दोहों का भावार्थ एवं शिल्प सौंदर्य", "आडंबरों का खंडन")),
                NcertChapterInfo(9, "सुदामा चरित (काव्य)", "नरोत्तमदास", "भगवान श्रीकृष्ण और उनके बालसखा सुदामा की निश्छल, भेदभाव-रहित मित्रता का मार्मिक ब्रजभाषा काव्य।", listOf("मित्रता की पराकाष्ठा", "अतिशयोक्ति अलंकार (पानी परात को हाथ छुयो नहिं)", "ब्रजभाषा का लालित्य")),
                NcertChapterInfo(10, "जहाँ पहिया है (रिपोर्ताज)", "पी. साईनाथ", "तमिलनाडु के पुडुकोट्टई जिले में साइकिल चलाकर ग्रामीण महिलाओं द्वारा अपनी आत्मनिर्भरता और स्वतंत्रता की क्रांति।", listOf("महिला सशक्तिकरण का अनूठा उदाहरण", "साइकिल के माध्यम से सामाजिक गतिशीलता", "रूढ़ियों को तोड़ती ग्रामीण महिलाएँ"))
            )
        ),
        NcertBook(
            id = "ncert_c8_skt",
            title = "Ruchira Bhag 3 (Sanskrit)",
            hindiTitle = "रुचिरा भाग 3 (संस्कृत)",
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.SANSKRIT,
            subjectCategory = "Language - Sanskrit",
            cbseBookCode = "NCERT-8-SKT (hesk1)",
            description = "CBSE Class 8 Sanskrit reader comprising Subhashitas, moral fables from Panchatantra, Digital India discourse, and grammatical conjugation exercises.",
            coverColorHex = 0xFF455A64, // Slate
            officialNcertUrl = "https://ncert.nic.in/textbook.php?hesk1=0-14",
            chapters = listOf(
                NcertChapterInfo(1, "सुभाषितानि", "प्रथमः पाठः", "गुण, वाणी, उद्यम और सदाचार पर आधारित नीति श्लोक।", listOf("गुणा गुणज्ञेषु गुणा भवन्ति", "साहित्यसंगीतकलाविहीनः साक्षात्पशुः")),
                NcertChapterInfo(2, "बिलस्य वाणी न कदापि मे श्रुता", "द्वितीयः पाठः (पञ्चतन्त्रम्)", "चतुर श्रृंगाल और सिंह की कथा जिसमें चातुर्य से प्राण रक्षा का संदेश है।", listOf("पञ्चतन्त्र की कथा शैली", "उपस्थित बुद्धि से संकट निवारण")),
                NcertChapterInfo(3, "डिजीभारतम् (Digital India)", "तृतीयः पाठः", "डिजिटल इंडिया कार्यक्रम के लाभ, कैशलेस लेनदेन और इंटरनेट क्रांति का संस्कृत में रोचक विवरण।", listOf("डिजिटल युग में संस्कृत भाषा", "चलदूरभाषयंत्रस्य उपयोगाः")),
                NcertChapterInfo(4, "सदैव पुरतो निधेहि चरणम्", "चतुर्थः पाठः (श्रीधर भास्कर वर्णेकर)", "मार्ग में आने वाली बाधाओं से डरे बिना निरंतर आगे बढ़ते रहने का प्रेरणादायी राष्ट्रवादी गीत।", listOf("राष्ट्रकवि वर्णेकर जी का गीत", "कर्मण्यता एवं आत्मविश्वास का संदेश")),
                NcertChapterInfo(5, "कण्टकेनैव कण्टकम्", "पञ्चमः पाठः", "कांटे से ही कांटा निकलता है - मध्य प्रदेश के डिंडोरी जिले की लोककथा।", listOf("चालाक व्याघ्र एवं चतुर लोमशिका कथा", "लोककथाओं का महत्व")),
                NcertChapterInfo(6, "गृहं शून्यं सुतां विना", "षष्ठः पाठः", "कन्या भ्रूण हत्या के विरोध और बेटी बचाओ, बेटी पढ़ाओ अभियान का संस्कृत संवाद।", listOf("स्त्री शिक्षा एवं सुरक्षा का महत्व", "सामाजिक कुरीतियों पर प्रहार")),
                NcertChapterInfo(7, "भारतजनताऽहम्", "सप्तमः पाठः (डॉ. रमाकान्त शुक्ल)", "भारतीय जनता के गौरव, शूरवीरता और सांस्कृतिक उदारता का यशोगान।", listOf("अभिमानधना विनयोपेता भारतजनता", "वसुधैव कुटुम्बकम् की भावना")),
                NcertChapterInfo(8, "संसारसागरस्य नायकाः", "अष्टमः पाठः (अनुपम मिश्र)", "पारंपरिक जल संरक्षण करने वाले शिल्पकारों (गजधर) के प्रति आदर का पाठ।", listOf("जल संरक्षण की प्राचीन भारतीय विधा", "गजधर शिल्पकारों का योगदान"))
            )
        ),

        // ====================================================================
        // CLASS 7 NCERT TEXTBOOKS (CBSE CURRICULUM)
        // ====================================================================
        NcertBook(
            id = "ncert_c7_sci",
            title = "Science (Class 7)",
            hindiTitle = "विज्ञान (कक्षा 7)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.SCIENCE_GENERAL,
            subjectCategory = "Science & Technology",
            cbseBookCode = "NCERT-7-SCI (gesc1)",
            description = "CBSE Class 7 Science textbook: Nutrition, Heat, Acids & Bases, Respiration, Motion, Electric Currents, and Light.",
            coverColorHex = 0xFF00897B,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gesc1=0-13",
            chapters = listOf(
                NcertChapterInfo(1, "Nutrition in Plants", "पादपों में पोषण", "Autotrophic and heterotrophic nutrition, photosynthesis equation, stomata, parasitic Cuscuta, insectivorous Pitcher plant, and symbiotic Lichens.", listOf("Photosynthesis Chemical Process", "Stomata & Guard Cells", "Heterotrophic Plants (Pitcher Plant, Fungi)"), matchingChapterId = "c7_sci_ch1"),
                NcertChapterInfo(2, "Nutrition in Animals", "प्राणियों में पोषण", "Different ways of taking food, human digestive system (mouth, oesophagus, stomach, small intestine, liver, pancreas), digestion in grass-eating ruminants, feeding in Amoeba.", listOf("Human Alimentary Canal Anatomy", "Digestion in Ruminants (Cud Chewing)", "Amoeba Pseudopodia Digestion")),
                NcertChapterInfo(3, "Heat", "ऊष्मा", "Hot and cold objects, temperature measurement with clinical and laboratory thermometers, conduction, convection, radiation, summer vs winter clothing.", listOf("Clinical vs Lab Thermometer", "Conduction, Convection & Radiation", "Sea Breeze and Land Breeze")),
                NcertChapterInfo(4, "Acids, Bases and Salts", "अम्ल, क्षारक और लवण", "Sour and bitter taste, litmus, turmeric, china rose indicators, neutralization reaction (Acid + Base -> Salt + Water + Heat), ant sting remedy.", listOf("Natural Indicators (Litmus, Turmeric)", "Neutralization Reaction & Word Equation", "Everyday Applications (Antacid, Ant Sting)"), matchingChapterId = "c7_sci_ch4"),
                NcertChapterInfo(5, "Physical and Chemical Changes", "भौतिक एवं रासायनिक परिवर्तन", "Physical changes (reversible shape/size) vs chemical changes (new substance formed: iron rusting, magnesium ribbon burning, copper sulphate reaction), galvanisation.", listOf("Physical vs Chemical Distinction", "Rusting of Iron (Fe + O2 + H2O)", "Crystallisation Purification")),
                NcertChapterInfo(6, "Respiration in Organisms", "जीवों में श्वसन", "Cellular respiration (aerobic vs anaerobic in yeast/muscles), human respiratory system, inhalation vs exhalation mechanics, breathing in cockroach (spiracles), earthworm, fish (gills).", listOf("Aerobic vs Anaerobic Respiration", "Human Lungs & Diaphragm Action", "Breathing in Fish & Insects")),
                NcertChapterInfo(7, "Transportation in Animals and Plants", "जंतुओं और पादपों में परिवहन", "Circulatory system: heart chambers, arteries, veins, capillaries, blood components (RBC, WBC, platelets), pulse rate, excretory system (kidneys, nephrons), xylem and phloem.", listOf("Heart Anatomy & Pulse Rate", "Excretory System in Humans", "Xylem & Phloem Translocation")),
                NcertChapterInfo(8, "Reproduction in Plants", "पादप में जनन", "Asexual reproduction (vegetative propagation in rose/potato, budding in yeast, fragmentation in Spirogyra, spore formation), sexual reproduction, flower parts, pollination, seed dispersal.", listOf("Vegetative Propagation Methods", "Flower Reproductive Parts", "Self vs Cross Pollination")),
                NcertChapterInfo(9, "Motion and Time", "गति एवं समय", "Slow or fast motion, speed formula S = Distance / Time, measurement of time using simple pendulum (periodic motion, time period), distance-time graphs.", listOf("Calculating Speed (km/h & m/s)", "Simple Pendulum Time Period", "Interpreting Distance-Time Graphs")),
                NcertChapterInfo(10, "Electric Current and its Effects", "विद्युत धारा और इसके प्रभाव", "Symbols of electric components, heating effect of electric current (heating elements, electric fuses), magnetic effect (Hans Christian Oersted), electromagnets, electric bell.", listOf("Circuit Diagrams with Symbols", "Heating Effect & Electric Fuse Safety", "Electromagnet Working & Electric Bell")),
                NcertChapterInfo(11, "Light", "प्रकाश", "Rectilinear propagation of light, reflection by plane mirrors, spherical mirrors (Concave: real/virtual images; Convex: rear-view mirror), lenses (Convex vs Concave), Newton's disc.", listOf("Plane Mirror Image Properties", "Concave vs Convex Mirrors", "Splitting of White Light (Rainbow Colors)")),
                NcertChapterInfo(12, "Forests: Our Lifeline", "वन: हमारी जीवन रेखा", "Forest structure (Canopy, Understorey, Forest floor), food chains, decomposers, role of forests as green lungs and water purifying systems.", listOf("Canopy & Understorey Layers", "Decomposers & Humus Formation", "Forests as Dynamic Living Entities")),
                NcertChapterInfo(13, "Wastewater Story", "अपशिष्ट जल की कहानी", "Sewage definition, wastewater treatment plant (WWTP) processes (bar screens, grit chamber, clarifier, aeration tank), sanitation and alternative sewage disposal.", listOf("Sewage Composition & Contaminants", "WWTP Step-by-Step Cleaning", "Better Housekeeping Practices"))
            )
        ),
        NcertBook(
            id = "ncert_c7_math",
            title = "Mathematics (Class 7)",
            hindiTitle = "गणित (कक्षा 7)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.MATHEMATICS,
            subjectCategory = "Mathematics",
            cbseBookCode = "NCERT-7-MATH (gemh1)",
            description = "CBSE Class 7 Mathematics: Integers, Fractions, Decimals, Simple Equations, Triangles, Comparing Quantities, Exponents, and Mensuration.",
            coverColorHex = 0xFF1565C0,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gemh1=0-13",
            chapters = listOf(
                NcertChapterInfo(1, "Integers", "पूर्णांक", "Properties of addition, subtraction, multiplication, and division of integers; rules of signs; distributive property of multiplication.", listOf("Properties of Addition & Subtraction", "Multiplication Rules for Signs", "Distributive Property"), matchingChapterId = "c7_math_ch1"),
                NcertChapterInfo(2, "Fractions and Decimals", "भिन्न एवं दशमलव", "Proper, improper, mixed fractions; multiplication and division of fractions (reciprocal method); decimal operations and conversions.", listOf("Multiplication of Fractions", "Reciprocal & Division of Fractions", "Multiplication and Division of Decimals")),
                NcertChapterInfo(3, "Data Handling", "आँकड़ों का प्रबंधन", "Collection and organisation of data, arithmetic mean, range, mode, median of ungrouped data, and double bar graphs.", listOf("Arithmetic Mean & Range", "Mode and Median Calculation", "Interpreting Double Bar Graphs")),
                NcertChapterInfo(4, "Simple Equations", "सरल समीकरण", "Setting up an equation, solving equations by balance and transposition methods, and translating word problems into algebraic equations.", listOf("Equation Concept & Balance Method", "Transposition Method for Solving", "Formulating Word Equations")),
                NcertChapterInfo(5, "Lines and Angles", "रेखा एवं कोण", "Complementary angles (sum = 90°), supplementary angles (sum = 180°), adjacent angles, linear pair, vertically opposite angles, transversal intersecting parallel lines.", listOf("Complementary vs Supplementary Angles", "Linear Pair & Vertically Opposite Angles", "Transversal Angles (Alternate, Corresponding)")),
                NcertChapterInfo(6, "The Triangle and Its Properties", "त्रिभुज और उसके गुण", "Medians and altitudes of a triangle, exterior angle property (exterior angle = sum of interior opposite angles), angle sum property (180°), Pythagoras property (hypotenuse² = base² + perp²).", listOf("Exterior Angle Property", "Angle Sum Property of Triangle", "Pythagoras Property for Right Triangles")),
                NcertChapterInfo(7, "Comparing Quantities", "राशियों की तुलना", "Ratios, equivalent ratios, percentage conversion, increase/decrease percentage, profit and loss (Cost price, Selling price), and Simple Interest formula I = (P×R×T)/100.", listOf("Percentage Conversions", "Profit & Loss Calculation", "Simple Interest Formula")),
                NcertChapterInfo(8, "Rational Numbers", "परिमेय संख्याएँ", "Positive and negative rational numbers, standard form, representing on number line, equivalent rational numbers, comparison, and four basic operations.", listOf("Standard Form of Rational Numbers", "Comparison of Rational Numbers", "Addition & Subtraction of Fractions/Rationals")),
                NcertChapterInfo(9, "Perimeter and Area", "परिमाप और क्षेत्रफल", "Perimeter and area of squares and rectangles, area of parallelogram (base × height), area of triangle (½ × base × height), circumference and area of circle (C = 2πr, A = πr²).", listOf("Area of Parallelogram & Triangle", "Circumference of Circle (2πr)", "Area of Circle (πr²)")),
                NcertChapterInfo(10, "Algebraic Expressions", "बीजीय व्यंजक", "How expressions are formed, terms of an expression, factors, numerical coefficients, like and unlike terms, addition and subtraction of algebraic expressions.", listOf("Terms, Factors & Coefficients", "Like vs Unlike Terms", "Adding and Subtracting Expressions")),
                NcertChapterInfo(11, "Exponents and Powers", "घातांक और घात", "Exponential form, base and exponent, laws of exponents (aᵐ × aⁿ = aᵐ⁺ⁿ, aᵐ ÷ aⁿ = aᵐ⁻ⁿ, (aᵐ)ⁿ = aᵐⁿ, a⁰ = 1), decimal number system in expanded exponential form.", listOf("Laws of Exponents", "Zero Power Rule (a⁰ = 1)", "Expressing Large Numbers in Standard Form")),
                NcertChapterInfo(12, "Visualising Solid Shapes", "ठोस आकारों का चित्रण", "2D representations of 3D shapes, faces, edges, vertices (Euler's formula F + V - E = 2), nets for building 3D shapes (cube, cylinder, cone), oblique and isometric sketches.", listOf("Faces, Edges, Vertices", "Euler's Formula", "Nets for 3D Solids"))
            )
        ),
        NcertBook(
            id = "ncert_c7_hist",
            title = "Our Pasts – II (History)",
            hindiTitle = "हमारे अतीत – II (इतिहास)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.HISTORY,
            subjectCategory = "Social Science - History",
            cbseBookCode = "NCERT-7-HIST (gess1)",
            description = "Medieval Indian history: Emergence of new kingdoms, Delhi Sultanate, Mughal Empire, tribal communities, and Bhakti-Sufi traditions.",
            coverColorHex = 0xFFC2185B,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gess1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "Introduction: Tracing Changes Through a Thousand Years", "प्रारंभिक कथन: हज़ार वर्षों के दौरान हुए परिवर्तनों की पड़ताल", "Cartography through the centuries (Al-Idrisi & French cartographer), changing terminologies ('Hindustan', 'foreigner'), historians and their manuscript sources.", listOf("Changing Geographic Notions", "Terminology Evolution (Hindustan)", "Manuscripts & Scribes")),
                NcertChapterInfo(2, "Kings and Kingdoms", "राजा और उनके राज्य", "Emergence of new dynasties (Rashtrakutas, Gurjara-Pratiharas, Palas), tripartite struggle for Kanauj, Prashastis, and Chola administration and bronze sculptures.", listOf("Tripartite Struggle for Kanauj", "Chola Administration & Ur/Sabha", "Chola Bronze Sculptures")),
                NcertChapterInfo(3, "Delhi: 12th to 15th Century", "दिल्ली: 12वीं से 15वीं शताब्दी", "Tomara Rajputs, Chauhan rule, Delhi Sultans (Mamluk/Slave dynasty, Khaljis, Tughluqs), Raziyya Sultan, Alauddin Khalji's market control, Muhammad Tughluq's projects.", listOf("Delhi as Capital", "Raziyya Sultan's Legacy", "Alauddin Khalji's Military & Market Policies")),
                NcertChapterInfo(4, "The Mughals (16th to 17th Century)", "मुग़ल: 16वीं से 17वीं शताब्दी", "Mughal military campaigns from Babur to Aurangzeb, Mughal traditions of succession, relations with other rulers, Mansabdari and Jagirdari system, Akbar Nama and Ain-i Akbari, Sulh-i Kul.", listOf("Babur's Conquests to Aurangzeb", "Mansabdari & Jagirdari System", "Akbar's Sulh-i Kul Policy")),
                NcertChapterInfo(5, "Tribes, Nomads and Settled Communities", "जनजातियाँ, खानाबदोश और एक जगह बसे हुए समुदाय", "Tribal societies beyond caste rules, pastoral nomads, Gonds of central India (Garha Katanga, shift cultivation), and Ahoms of northeast India (Paik system).", listOf("Nomadic Pastoral Lifestyles", "Gond Kingdom Administration", "Ahom Kingdom & Paik Labor System")),
                NcertChapterInfo(6, "Devotional Paths to the Divine", "ईश्वर से अनुराग", "Philosophy and Bhakti: Shankara (Advaita) and Ramanuja (Vishishtadvaita), Virashaivism in Karnataka, Bhakti saints of Maharashtra, Kabir, Baba Guru Nanak, and Sufi silsilas.", listOf("Shankara vs Ramanuja Philosophy", "Bhakti Tradition in Maharashtra", "Teachings of Kabir & Guru Nanak")),
                NcertChapterInfo(7, "The Making of Regional Cultures", "क्षेत्रीय संस्कृतियों का निर्माण", "Regional languages and identity, Malayalam and the Chera kingdom, Jagannatha cult of Puri, Kathak dance evolution, Rajput painting traditions, and Bengal's temples.", listOf("Malayalam & Chera Administration", "Jagannatha Cult & Integration", "Bengal's Fisherfolk & Temple Architecture")),
                NcertChapterInfo(8, "Eighteenth-Century Political Formations", "अठारहवीं शताब्दी में नए राजनीतिक गठन", "Decline of the Mughal Empire, Nadir Shah's sack of Delhi (1739), emergence of new states: Awadh (Burhan-ul-Mulk), Bengal (Murshid Quli Khan), Hyderabad (Asaf Jah), Marathas (Shivaji & Peshwas), and Sikhs.", listOf("Decline of Later Mughals", "Autonomous States (Awadh, Bengal, Hyderabad)", "Maratha Expansion under Shivaji & Peshwas"))
            )
        ),
        NcertBook(
            id = "ncert_c7_geog",
            title = "Our Environment (Geography)",
            hindiTitle = "हमारा पर्यावरण (भूगोल)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.GEOGRAPHY,
            subjectCategory = "Social Science - Geography",
            cbseBookCode = "NCERT-7-GEOG (gess2)",
            description = "Earth's internal structure, atmospheric layers, water cycle, weathering, deserts, and ecosystems.",
            coverColorHex = 0xFF5D4037,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gess2=0-7",
            chapters = listOf(
                NcertChapterInfo(1, "Environment", "पर्यावरण", "Natural environment (Lithosphere, Atmosphere, Hydrosphere, Biosphere), Biotic vs Abiotic, ecosystem definition, human environment interactions.", listOf("Four Spheres of Earth", "Biotic vs Abiotic Components", "Ecosystem Balance")),
                NcertChapterInfo(2, "Inside Our Earth", "हमारी पृथ्वी के अंदर", "Interior of Earth: Crust, Mantle, Core; SIAL (Silica + Alumina) and SIMA (Silica + Magnesium), NIFE (Nickel + Iron); Rocks: Igneous, Sedimentary, Metamorphic; rock cycle.", listOf("Crust, Mantle, Core Layers", "Rock Classification & Rock Cycle", "Minerals in Human Use")),
                NcertChapterInfo(3, "Our Changing Earth", "हमारी बदलती पृथ्वी", "Lithospheric plates, Endogenic forces (earthquakes, volcanoes), Exogenic forces (weathering, erosion), river features (meanders, ox-bow lakes, waterfalls, delta), glacial moraines, sand dunes.", listOf("Earthquake Epicentre & Focus", "River Landforms (Meanders, Ox-bow lakes)", "Glacial & Desert Wind Landforms")),
                NcertChapterInfo(4, "Air", "वायु", "Composition of the atmosphere (Nitrogen 78%, Oxygen 21%, Argon, CO2), structure of atmosphere (Troposphere, Stratosphere, Mesosphere, Thermosphere, Exosphere), weather vs climate, winds.", listOf("Atmospheric Gas Composition", "Layers of Atmosphere", "Cyclones, Pressure Belts & Wind Systems")),
                NcertChapterInfo(5, "Water", "जल", "Distribution of water bodies, water cycle, ocean movements: Waves (Tsunamis), Tides (Spring and Neap tides due to gravitational pull), and Ocean currents (Warm Gulf Stream vs Cold Labrador).", listOf("The Global Hydrological Cycle", "Spring vs Neap Tides Causes", "Warm & Cold Ocean Currents")),
                NcertChapterInfo(6, "Human Environment Interactions: Tropical & Subtropical Regions", "मानव-पर्यावरण अन्योन्यक्रिया: उष्णकटिबंधीय एवं उपोष्ण प्रदेश", "Life in the Amazon Basin: equatorial climate, rainforest flora and fauna, slash-and-burn farming; Life in the Ganga-Brahmaputra Basin: agriculture, tourism, delta ecology.", listOf("Amazon Rainforest Flora & Fauna", "Ganga-Brahmaputra Basin Agriculture", "Wildlife (One-Horned Rhino, Bengal Tiger)")),
                NcertChapterInfo(7, "Life in the Deserts", "रेगिस्तान में जीवन", "The Hot Desert: Sahara (climate, oasis, Tuaregs and Bedouins nomadism, oil discovery); The Cold Desert: Ladakh (high altitude rain shadow, Buddhist gompas, Pashmina wool goats).", listOf("Sahara Hot Desert Features", "Ladakh Cold Desert Ecology", "Pashmina Wool & Gompa Culture"))
            )
        ),
        NcertBook(
            id = "ncert_c7_civ",
            title = "Social and Political Life – II (Civics)",
            hindiTitle = "सामाजिक एवं राजनीतिक जीवन – II (नागरिक शास्त्र)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.POLITY,
            subjectCategory = "Social Science - Civics",
            cbseBookCode = "NCERT-7-CIV (gess3)",
            description = "Equality in Indian democracy, State Government functioning, healthcare systems, gender socialization, and market supply chains.",
            coverColorHex = 0xFFE65100,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gess3=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "On Equality", "समानता", "Universal adult franchise, equality in Indian democracy, Midday Meal Scheme benefits (Tamil Nadu first state), civil rights movement in USA (Rosa Parks).", listOf("Universal Adult Franchise", "Midday Meal Scheme Objectives", "Civil Rights Movement (USA)")),
                NcertChapterInfo(2, "Role of the Government in Health", "स्वास्थ्य में सरकार की भूमिका", "Public healthcare services (PHCs, district hospitals) vs private clinics, Hakimi Sheikh's legal case, Kerala health experience, Costa Rican approach to health.", listOf("Public vs Private Healthcare", "The Costa Rican Model", "Right to Health as Part of Article 21")),
                NcertChapterInfo(3, "How the State Government Works", "राज्य शासन कैसे काम करता है", "Who is an MLA (Member of Legislative Assembly), constituencies, majority party, Chief Minister appointment by Governor, debates in Legislative Assembly.", listOf("Role of MLA & Legislative Assembly", "Forming Government & Coalition", "Press Conferences & Accountability")),
                NcertChapterInfo(4, "Growing up as Boys and Girls", "लड़के और लड़कियों के रूप में बड़ा होना", "Growing up in Samoa (1920s) and Madhya Pradesh (1960s), gender stereotypes, devaluing domestic housework, domestic workers' lives.", listOf("Samoan Island Case Study", "Invisible & Unpaid Domestic Labor", "Gender Equality in Childcare")),
                NcertChapterInfo(5, "Women Change the World", "औरतों ने बदली दुनिया", "Stereotyped expectations, pioneering women: Rokeya Sakhawat Hossain (Sultana's Dream), Rashsundari Devi (Amar Jiban), literacy census trends, women's movement campaigns.", listOf("Breaking Gender Stereotypes", "Rokeya Hossain's Sultana's Dream", "Women's Movement Campaigns")),
                NcertChapterInfo(6, "Understanding Media", "संचार माध्यमों को समझना", "Media and technology, media and big business houses, independent media, setting agendas, censorship and advertising revenue.", listOf("Mass Media & Technology Link", "Media Ownership & Big Business", "Setting the Public Agenda")),
                NcertChapterInfo(7, "Markets Around Us", "हमारे आस-पास के बाज़ार", "Weekly markets, neighborhood shops, shopping complexes and malls, chain of markets (wholesale traders to retailers), buyers and sellers inequality.", listOf("Weekly Markets vs Malls", "Wholesale Chain of Markets", "Credit & Inequality in Commerce")),
                NcertChapterInfo(8, "A Shirt in the Market", "बाज़ार में एक कमीज़", "Journey of a cotton shirt: Swapna the cotton farmer in Kurnool, the merchant and weaver putting-out system, garment exporter in Delhi, foreign supermarket retail margin.", listOf("Putting-Out System Between Merchant & Weaver", "Garment Factory Labor Conditions", "Unequal Profit Distribution Across Supply Chain"))
            )
        ),
        NcertBook(
            id = "ncert_c7_eng",
            title = "Honeycomb (English)",
            hindiTitle = "हनीकॉम्ब (अंग्रेजी)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.ENGLISH,
            subjectCategory = "Language - English",
            cbseBookCode = "NCERT-7-ENG (geen1)",
            description = "CBSE Class 7 English Literature textbook: Inspiring short stories, classic fables, poetry analysis, and supplementary reading.",
            coverColorHex = 0xFF6A1B9A,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?geen1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "Three Questions", "तीन प्रश्न", "Leo Tolstoy's timeless moral story about a king who sought answers to the three most important questions: the right time, the right people, and the most important work.", listOf("Leo Tolstoy's Philosophy", "Poem: The Squirrel (Mildred Bowers Armstrong)")),
                NcertChapterInfo(2, "A Gift of Chappals", "चप्पलों का उपहार", "Vasantha Surya's charming tale set in Madras about children giving away their music teacher's footwear to a barefoot, blistered beggar.", listOf("Innocence, Empathy and Generosity", "Poem: The Rebel (D.J. Enright)")),
                NcertChapterInfo(3, "Gopal and the Hilsa-Fish", "गोपाल और हिल्सा मछली", "Clever comic strip narrative about how the witty courtier Gopal proved that intelligence and humor can outshine popular gossip.", listOf("Comic Strip Format", "Poem: The Shed (Frank Flynn)")),
                NcertChapterInfo(4, "The Ashes That Made Trees Bloom", "राख जिससे पेड़ खिल उठे", "Japanese folklore about an honest elderly couple, their loyal dog, and an ungrateful wicked neighbor.", listOf("Traditional Japanese Folklore", "Poem: Chivvy (Michael Rosen)")),
                NcertChapterInfo(5, "Quality", "गुणवत्ता", "John Galsworthy's celebrated story about Mr. Gessler, a dedicated German bootmaker who sacrificed his livelihood rather than compromise on craftsmanship.", listOf("Artisanal Integrity vs Mass Production", "Poem: Trees (Shirley Bauer)")),
                NcertChapterInfo(6, "Expert Detectives", "कुशल जासूस", "Sharda Dwivedi's suspenseful mystery involving sibling detectives Maya and Nishad investigating their enigmatic neighbor Mr. Nath.", listOf("Mystery and Inference", "Poem: Mystery of the Talking Fan (Maude Rubin)")),
                NcertChapterInfo(7, "The Invention of Vita-Wonk", "वीटा-वॉक का आविष्कार", "Roald Dahl's wildly imaginative fantasy about Mr. Willy Wonka inventing an elixir made from the oldest living specimens on Earth to age people backwards.", listOf("Roald Dahl's Humorous Fantasy", "Poem: Meadow Surprises (Lois Brandt Phillips)")),
                NcertChapterInfo(8, "An Alien Hand (Supplementary)", "पूरक पाठ्यपुस्तक", "Highlights from the supplementary reader: The Tiny Teacher (ant wisdom), Bringing Up Kari (elephant loyalty), and Golu Grows a Nose (Rudyard Kipling).", listOf("Animal Behavior and Morals", "Character Sketches & Literary Device Analysis"))
            )
        ),
        NcertBook(
            id = "ncert_c7_hin",
            title = "Vasant Bhag 2 (Hindi Reader)",
            hindiTitle = "वसंत भाग 2 (हिंदी)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.HINDI,
            subjectCategory = "Language - Hindi",
            cbseBookCode = "NCERT-7-HIN (geds1)",
            description = "CBSE Class 7 Hindi literature: Classic Hindi prose, poetry, and memoirs by Shivmangal Singh Suman, Nagarjun, and Sarveshwar Dayal Saxena.",
            coverColorHex = 0xFFD84315,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?geds1=0-15",
            chapters = listOf(
                NcertChapterInfo(1, "हम पंछी उन्मुक्त गगन के", "शिवमंगल सिंह 'सुमन'", "स्वतंत्रता के महत्व को रेखांकित करती कविता जिसमें पिंजरे में बंद पक्षियों की व्यथा और मुक्त उड़ान की चाह व्यक्त हुई है।", listOf("स्वाधीनता का मूल्य", "प्रकृति प्रेम", "काव्य सौंदर्य")),
                NcertChapterInfo(2, "दादी माँ", "शिवप्रसाद सिंह", "लेखक द्वारा अपनी ममतामयी दादी माँ के स्नेह, सेवा और त्यागपूर्ण स्वभाव का आत्मीय संस्मरण।", listOf("संस्मरण विधा", "संयुक्त परिवार की भावना", "भारतीय ग्रामीण परिवेश")),
                NcertChapterInfo(3, "हिमालय की बेटियाँ", "नागार्जुन", "हिमालय से निकलने वाली नदियों (गंगा, यमुना, सतलुज) को बेटियों के रूप में मानवीय संवेदना के साथ चित्रित करता ललित निबंध।", listOf("नदियों का मानवीकरण", "नागार्जुन का प्रकृति प्रेम", "पर्यावरण चेतना")),
                NcertChapterInfo(4, "कठपुतली", "भवानीप्रसाद मिश्र", "पराधीनता की बेड़ियों को तोड़कर स्वतंत्र होने की इच्छा व्यक्त करती प्रतीकात्मक कविता।", listOf("स्वतंत्रता और स्वावलंबन", "कठपुतली का अंतर्द्वंद्व", "जिम्मेदारी का बोध")),
                NcertChapterInfo(5, "मिठाईवाला", "भगवतीप्रसाद वाजपेयी", "बच्चों के प्रति अगाध प्रेम रखने वाले एक दुखियारे पिता की मार्मिक कहानी जो खिलौनेवाला, मुरलीवाला और मिठाईवाला बनकर आता है।", listOf("वात्सल्य और करुणा", "मानवीय संवेदना", "पात्र चित्रण")),
                NcertChapterInfo(6, "रक्त और हमारा शरीर", "यतीश अग्रवाल", "मानव शरीर में रक्त के घटकों (लाल रक्त कण, श्वेत कण, बिम्बाणु) और एनीमिया से बचाव की वैज्ञानिक जानकारी देने वाला निबंध।", listOf("रक्त के घटक व कार्य", "एनीमिया के कारण एवं उपचार", "संतुलित आहार")),
                NcertChapterInfo(7, "पापा खो गए", "विजय तेंदुलकर", "मराठी नाटक का अनुवाद जिसमें खंभा, नाचने वाली, पेड़, लेटर बॉक्स और कौआ मिलकर एक अपहृत नन्ही बच्ची को बचाते हैं।", listOf("नाट्य विधा एवं संवाद", "निर्जीव वस्तुओं का सजीव रूप", "बाल सुरक्षा का संदेश")),
                NcertChapterInfo(8, "शाम-एक किसान", "सर्वेश्वर दयाल सक्सेना", "जाड़े की शाम के प्राकृतिक दृश्य का किसान के रूप में अनूठा मानवीकरण।", listOf("अनोखे उपमान और बिंब", "प्राकृतिक सौंदर्य", "कविता का शिल्प")),
                NcertChapterInfo(9, "रहीम के दोहे", "अब्दुल रहीम खानखाना", "जीवन के व्यावहारिक ज्ञान, नीति और सामाजिक मूल्यों की सीख देने वाले कालजयी दोहे।", listOf("रहीम की नीतिपरक दृष्टि", "दोहा छंद एवं ब्रजभाषा", "सच्ची मित्रता की पहचान")),
                NcertChapterInfo(10, "अपूर्व अनुभव", "तेत्सुको कुरोयानागी (तोत्तो-चान)", "तोत्तो-चान और उसके पोलियोग्रस्त मित्र यासुकी-चान के पेड़ पर चढ़ने के अद्वितीय रोमांच की जापानी बाल-कथा।", listOf("मित्रता और अदम्य साहस", "दिव्यांगों के प्रति संवेदनशीलता", "जापानी बाल साहित्य"))
            )
        ),
        NcertBook(
            id = "ncert_c7_skt",
            title = "Ruchira Bhag 2 (Sanskrit)",
            hindiTitle = "रुचिरा भाग 2 (संस्कृत)",
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.SANSKRIT,
            subjectCategory = "Language - Sanskrit",
            cbseBookCode = "NCERT-7-SKT (gesk1)",
            description = "CBSE Class 7 Sanskrit: Subhashitas, inspiring historical biographies (Pandita Ramabai), moral fables, and national flag study.",
            coverColorHex = 0xFF455A64,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?gesk1=0-15",
            chapters = listOf(
                NcertChapterInfo(1, "सुभाषितानि", "प्रथमः पाठः", "सत्य, मधुर वाणी, दान और ज्ञान की महत्ता पर श्लोक।", listOf("पृथिव्यां त्रीणि रत्नानि जलमन्नं सुभाषितम्", "सत्येन धार्यते पृथ्वी")),
                NcertChapterInfo(2, "दुर्बुद्धिः विनश्यति", "द्वितीयः पाठः (पञ्चतन्त्रम्)", "कच्छप और हंसों की प्रसिद्ध कथा - अनुचित समय पर बोलने का दुष्परिणाम।", listOf("हितैषियों के वचन की उपेक्षा का फल", "मौन का महत्व")),
                NcertChapterInfo(3, "स्वावलम्बनम्", "तृतीयः पाठः", "स्वावलम्बी कृष्णमूर्ति और समृद्ध श्रीकण्ठ की कथा - अपना कार्य स्वयं करने का सुख।", listOf("आत्मनिर्भरता का आनंद", "समय पालन एवं कर्मनिष्ठा")),
                NcertChapterInfo(4, "पण्डिता रमाबाई", "चतुर्थः पाठः", "स्त्री शिक्षा और विधवाओं के उद्धार के लिए समर्पित विदुषी पण्डिता रमाबाई का प्रेरक जीवन वृत्त।", listOf("पण्डिता रमाबाई का संघर्ष", "शारदा सदन की स्थापना", "स्त्री उद्धार")),
                NcertChapterInfo(5, "सदाचारः", "पञ्चमः पाठः", "आलस्य त्याग, सत्य संभाषण और गुरु व बड़ों के आदर पर शिक्षाप्रद श्लोक।", listOf("आलस्यं हि मनुष्याणां शरीरस्थो महान् रिपुः", "सत्यं ब्रूयात् प्रियं ब्रूयात्")),
                NcertChapterInfo(6, "सङ्कल्पः सिद्धिदायकः", "षष्ठः पाठः", "माता पार्वती की कठोर तपस्या और भगवान शिव की प्राप्ति का नाटकीय संवाद।", listOf("दृढ़ संकल्प का फल", "संस्कृत नाट्य संवाद शैली")),
                NcertChapterInfo(7, "त्रिवर्णः ध्वजः", "सप्तमः पाठः", "भारतीय तिरंगे झंडे के तीनों रंगों (केसरिया, श्वेत, हरा) और अशोक चक्र के प्रतीकात्मक अर्थ।", listOf("राष्ट्रध्वज का इतिहास", "शौर्य, शांति और समृद्धि के प्रतीक", "अशोक चक्र के २४ आरे")),
                NcertChapterInfo(8, "विश्वबन्धुत्वम्", "अष्टमः पाठः", "समस्त संसार के प्रति भ्रातृत्व भाव और वसुधैव कुटुम्बकम् की भावना का संदेश।", listOf("अयं निजः परो वेति गणना लघुचेतसाम्", "उदारचरितानां तु वसुधैव कुटुम्बकम्"))
            )
        ),

        // ====================================================================
        // CLASS 6 NCERT TEXTBOOKS (CBSE CURRICULUM)
        // ====================================================================
        NcertBook(
            id = "ncert_c6_sci",
            title = "Curiosity / Science (Class 6)",
            hindiTitle = "जिज्ञासा / विज्ञान (कक्षा 6)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.SCIENCE_GENERAL,
            subjectCategory = "Science & Technology",
            cbseBookCode = "NCERT-6-SCI (fesc1)",
            description = "Latest NEP-aligned CBSE Class 6 Science curriculum (Curiosity textbook): Components of Food, Materials, Living World, Motion, Measurement, and Earth.",
            coverColorHex = 0xFF00897B,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?fesc1=0-12",
            chapters = listOf(
                NcertChapterInfo(1, "The Wonderful World of Science", "विज्ञान की अद्भुत दुनिया", "What is science, how scientists observe nature, curious questions, the scientific method, and making hypotheses.", listOf("Scientific Inquiry Process", "Observation & Wonder in Daily Life", "Role of Science in Society")),
                NcertChapterInfo(2, "Diversity in the Living World", "सजीव जगत में विविधता", "Habitats (Terrestrial, Aquatic), adaptations of camel, fish, cactus; classification of living beings; biotic vs abiotic components.", listOf("Terrestrial vs Aquatic Habitats", "Animal Adaptations (Camel & Fish)", "Plant Adaptations in Deserts")),
                NcertChapterInfo(3, "Mindful Eating: Components of Food", "भोजन के घटक (संतुलित आहार)", "Carbohydrates, fats, proteins, vitamins, minerals, roughage, water; chemical tests for starch (iodine), protein (Biuret), fat; deficiency diseases.", listOf("Food Nutrient Testing", "Balanced Diet Composition", "Vitamin Deficiency Diseases"), matchingChapterId = "c6_sci_ch1"),
                NcertChapterInfo(4, "Exploring Magnets", "चुंबकों की खोज", "Discovery of magnets (Magnes the shepherd), magnetic vs non-magnetic materials, poles of a magnet (North-South), magnetic compass, attraction and repulsion rules.", listOf("Poles of Magnet", "Finding Directions with Compass", "Attraction vs Repulsion Rules")),
                NcertChapterInfo(5, "Measurement of Length and Motion", "लंबाई का मापन और गति", "Ancient units (cubit, foot) vs Standard International (SI) unit metre; measuring curved lines; types of motion (Rectilinear, Circular, Periodic).", listOf("SI Units & Metric Conversion", "Measuring with Broken Scales", "Rectilinear, Circular & Periodic Motion")),
                NcertChapterInfo(6, "Materials Around Us", "हमारे आस-पास की सामग्री", "Sorting objects into groups, properties of materials: appearance/lustre, hardness, solubility in water, floating/sinking, transparency (Transparent, Translucent, Opaque).", listOf("Soluble vs Insoluble Substances", "Floating vs Sinking Density", "Transparent, Translucent, Opaque")),
                NcertChapterInfo(7, "Temperature and Its Measurement", "तापमान और इसका मापन", "Concepts of hotness, sensation of touch limitations, thermometers, measuring scale in Celsius (°C), and safe handling.", listOf("Sensation vs Reliable Measurement", "Reading a Thermometer", "Normal Human Body Temperature")),
                NcertChapterInfo(8, "A Journey through States of Water", "जल की अवस्थाओं की यात्रा", "Evaporation, condensation, precipitation; cloud formation experiment; water cycle in nature; groundwater recharging.", listOf("Evaporation vs Condensation", "Cloud Formation Mechanism", "Water Conservation & Rainwater Harvesting")),
                NcertChapterInfo(9, "Methods of Separation in Everyday Life", "दैनिक जीवन में पृथक्करण की विधियाँ", "Handpicking, threshing, winnowing, sieving, sedimentation, decantation, filtration, evaporation, and condensation in water purification.", listOf("Threshing vs Winnowing", "Sedimentation & Decantation", "Filtration Techniques in Everyday Life")),
                NcertChapterInfo(10, "Living Creatures: Exploring Characteristics", "सजीव प्राणी: उनकी विशेषताएँ", "Growth, respiration, response to stimuli, excretion, reproduction, movement, and life cycle of organisms.", listOf("Characteristics of Living Organisms", "Response to Stimuli (Touch-me-not plant)", "Excretion and Life Span")),
                NcertChapterInfo(11, "Nature's Treasures: Getting to Know Plants", "प्रकृति के खजाने: पौधों को जानिए", "Herbs, Shrubs, Trees; Taproot vs Fibrous roots; Reticulate vs Parallel leaf venation; transpiration; parts of flower (Sepals, Petals, Stamens, Pistil).", listOf("Herbs, Shrubs, Trees", "Venation-Root Correlation", "Parts of Flower Anatomy"), matchingChapterId = "c6_sci_ch7"),
                NcertChapterInfo(12, "Beyond Earth", "पृथ्वी के पार", "The Sun, the Moon, phases of moon, constellations (Ursa Major/Saptarshi), planets of the Solar System, and artificial satellites (Aryabhata, Chandrayaan).", listOf("Phases of the Moon", "Constellations & Pole Star", "Solar System & Indian Space Missions"))
            )
        ),
        NcertBook(
            id = "ncert_c6_math",
            title = "Ganita Prakash / Mathematics (Class 6)",
            hindiTitle = "गणित प्रकाश / गणित (कक्षा 6)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.MATHEMATICS,
            subjectCategory = "Mathematics",
            cbseBookCode = "NCERT-6-MATH (femh1)",
            description = "Latest NEP-aligned CBSE Class 6 Mathematics curriculum: Patterns, Numbers, Geometry, Data Handling, Fractions, and Integers.",
            coverColorHex = 0xFF1565C0,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?femh1=0-10",
            chapters = listOf(
                NcertChapterInfo(1, "Patterns in Mathematics", "गणित में पैटर्न", "Visual and number patterns, repeating sequences, dot patterns for triangle and square numbers, discovering rules algebraically.", listOf("Visual Geometric Patterns", "Square and Triangular Numbers", "Extending Pattern Sequences")),
                NcertChapterInfo(2, "Lines and Angles", "रेखाएँ और कोण", "Points, line segments, rays, lines, intersecting lines, parallel lines, measuring angles with protractor, acute, right, obtuse, straight, and reflex angles.", listOf("Point, Line Segment, Ray", "Intersecting vs Parallel Lines", "Classifying Angles by Measure")),
                NcertChapterInfo(3, "Number Play (Knowing Our Numbers)", "संख्याओं का खेल (अपनी संख्याओं की जानकारी)", "Comparing large numbers, Indian System vs International System of Numeration, place value charts, estimation and rounding off, Roman numerals.", listOf("Indian vs International Numeration", "Comma Placement Rules", "Roman Numerals & Rounding Off"), matchingChapterId = "c6_math_ch1"),
                NcertChapterInfo(4, "Data Handling and Presentation", "आँकड़ों का प्रबंधन एवं प्रस्तुति", "Recording data with tally marks, organising data in frequency tables, pictographs with keys, and drawing bar graphs with appropriate scale.", listOf("Tally Marks Counting", "Reading & Making Pictographs", "Drawing Uniform Bar Graphs")),
                NcertChapterInfo(5, "Prime Time (Playing with Numbers)", "अभाज्य संख्याएँ (संख्याओं के साथ खेलना)", "Factors and multiples, prime and composite numbers, divisibility tests (for 2, 3, 4, 5, 6, 8, 9, 10, 11), Highest Common Factor (HCF) and Lowest Common Multiple (LCM).", listOf("Prime vs Composite Numbers", "Divisibility Rules for 2, 3, 5, 9, 11", "Finding HCF and LCM via Prime Factorisation")),
                NcertChapterInfo(6, "Perimeter and Area", "परिमाप और क्षेत्रफल", "Perimeter of closed rectilinear figures, formula for perimeter of rectangle P = 2(l+b) and square P = 4s; Area concept using grid squares, area of rectangle and square.", listOf("Perimeter Calculation Formula", "Area of Rectangle (l × b)", "Area of Square (side × side)")),
                NcertChapterInfo(7, "Fractions", "भिन्न", "Fraction as a part of a whole, fraction on a number line, proper, improper, mixed fractions, equivalent fractions, simplest form, comparing fractions, addition and subtraction of like/unlike fractions.", listOf("Proper, Improper, Mixed Fractions", "Equivalent Fractions", "Addition & Subtraction with LCM")),
                NcertChapterInfo(8, "Playing with Constructions", "रचनाओं के साथ खेलना", "Using ruler and compass: drawing a circle, perpendicular bisector of a line segment, angle bisector, constructing angles of 60°, 90°, 120° without protractor.", listOf("Constructing Perpendicular Bisector", "Angle Bisector Construction", "Constructing 60° and 90° Angles")),
                NcertChapterInfo(9, "Symmetry", "सममिति", "Line of symmetry, bilateral symmetry in nature and letters of alphabet, figures with multiple lines of symmetry, reflection and symmetry.", listOf("Lines of Symmetry in Regular Polygons", "Mirror Reflection Symmetry", "Rotational Symmetry Basics")),
                NcertChapterInfo(10, "The Other Side of Zero (Integers)", "शून्य के दूसरी ओर (पूर्णांक)", "Need for negative numbers (temperatures below zero, depths), number line representation, successor and predecessor of integers, addition and subtraction using number line.", listOf("Negative Numbers Introduction", "Integers on Number Line", "Addition & Subtraction on Number Line"))
            )
        ),
        NcertBook(
            id = "ncert_c6_hist",
            title = "Our Pasts – I (History)",
            hindiTitle = "हमारे अतीत – I (इतिहास)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.HISTORY,
            subjectCategory = "Social Science - History",
            cbseBookCode = "NCERT-6-HIST (fess1)",
            description = "Ancient Indian History: Hunter-gatherers, Indus Valley Harappan civilization, Vedic age, Mahajanapadas, Ashoka's Empire, and Gupta Golden Age.",
            coverColorHex = 0xFFC2185B,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?fess1=0-10",
            chapters = listOf(
                NcertChapterInfo(1, "What, Where, How and When?", "क्या, कब, कहाँ और कैसे?", "Finding out about the past, hunting-gathering along the Narmada, Sulaiman and Kirthar hills agriculture, manuscripts on palm leaves, inscriptions on stone, dates (BC/BCE and AD/CE).", listOf("Historical Geography of Subcontinent", "Manuscripts vs Inscriptions", "Significance of BCE and CE Dates")),
                NcertChapterInfo(2, "From Hunting-Gathering to Growing Food", "आरंभिक मानव: आखेट-खाद्य संग्रह से भोजन उत्पादन तक", "Palaeolithic, Mesolithic, Neolithic stone tools; Bhimbetka cave rock paintings; discovery of fire; domestication of animals; Mehrgarh earliest farming settlement.", listOf("Stone Age Stages (Palaeolithic, Neolithic)", "Bhimbetka Cave Art", "Mehrgarh Farming & Burial Practices")),
                NcertChapterInfo(3, "In the Earliest Cities (Harappa)", "आरंभिक नगर (हड़प्पा सभ्यता)", "The story of Harappa, town planning: Citadel and Lower Town, Great Bath at Mohenjodaro, grid street drainage system, crafts, seals, terracotta toys, and decline theories.", listOf("Citadel and Great Bath", "Harappan Drainage & House Architecture", "Seals, Script, and Trade Networks")),
                NcertChapterInfo(4, "What Books and Burials Tell Us", "क्या बताती हैं हमें किताबें और कब्रें", "The Rigveda: hymns (suktas), language (Vedic Sanskrit), prayers for cattle and horses; Megalithic burial stone boulders (Inamgaon skeleton analysis).", listOf("Four Vedas (Rigveda Oldest)", "Social Differences in Burials", "Megalithic Traditions & Inamgaon Excavations")),
                NcertChapterInfo(5, "Kingdoms, Kings and an Early Republic", "राज्य, राजा और एक प्राचीन गणराज्य", "Ashvamedha sacrifice, Rajas and Janapadas, Mahajanapadas (fortified capitals, army, taxes on crops: bhaga 1/6th), Magadha powerful kingdom, Vajji sangha republic.", listOf("Ashvamedha Horse Sacrifice", "Magadha's Military & Natural Advantages", "Vajji Democratic Ganas/Sanghas")),
                NcertChapterInfo(6, "New Questions and Ideas", "नए प्रश्न नए विचार", "Siddhartha Gautama Buddha's enlightenment under Bodhi tree at Bodh Gaya, Four Noble Truths, Vardhamana Mahavira and Jainism, Upanishadic thinkers (Gargi, Satyakama Jabala).", listOf("Life and Teachings of Buddha", "Mahavira and Jain Principles (Ahimsa)", "Upanishadic Dialogues")),
                NcertChapterInfo(7, "From a Kingdom to an Empire (Ashoka)", "अशोक: एक अनोखा सम्राट जिसने युद्ध का त्याग किया", "Chandragupta Maurya and Chanakya's Arthashastra, Pataliputra imperial capital, Kalinga war horror, Ashoka's Dhamma, rock and pillar edicts, Lion Capital at Sarnath.", listOf("Mauryan Empire Administration", "Kalinga War Transformation", "Ashoka's Dhamma & Sarnath Lion Capital")),
                NcertChapterInfo(8, "Villages, Towns and Trade", "गाँव, शहर और व्यापार", "Iron tools and agriculture expansion, irrigation works, northern black polished ware (NBPW), punch-marked coins, coastal port of Arikamedu.", listOf("Iron Agriculture & Village Life", "Punch-Marked Silver/Copper Coins", "Arikamedu Indo-Roman Trading Post")),
                NcertChapterInfo(9, "New Empires and Kingdoms", "नए साम्राज्य और राज्य", "Samudragupta's Prashasti by Harishena on Allahabad pillar, genealogies, Harshavardhana and Chinese pilgrim Xuanzang, Pallavas of Kanchipuram and Chalukyas of Badami.", listOf("Samudragupta's Military Campaigns", "Harshavardhana's Reign", "Pallavas and Chalukyas of South India")),
                NcertChapterInfo(10, "Buildings, Paintings and Books", "इमारतें, चित्र तथा किताबें", "Mehrauli iron pillar rustless metallurgy, stupas (Sanchi Great Stupa, pradakshina patha), Ajanta cave paintings, epics Silappadikaram, Manimekalai, Aryabhata's astronomy.", listOf("Iron Pillar of Delhi Metallurgy", "Structure of a Stupa (Harmika, Anda)", "Ajanta Buddhist Frescoes", "Aryabhata's Aryabhatiya"))
            )
        ),
        NcertBook(
            id = "ncert_c6_geog",
            title = "The Earth: Our Habitat (Geography)",
            hindiTitle = "पृथ्वी: हमारा आवास (भूगोल)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.GEOGRAPHY,
            subjectCategory = "Social Science - Geography",
            cbseBookCode = "NCERT-6-GEOG (fess2)",
            description = "Earth in the Solar System, Latitudes & Longitudes, Earth's motions, Maps, Major Domains, and Major Landforms.",
            coverColorHex = 0xFF5D4037,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?fess2=0-6",
            chapters = listOf(
                NcertChapterInfo(1, "The Earth in the Solar System", "सौरमंडल में पृथ्वी", "Celestial bodies, stars and constellations (Saptarshi), planets, why Earth is called unique planet, satellite Moon, asteroids, meteoroids.", listOf("Planets Order (My Very Educated Mother...)", "Earth as a Geoid & Blue Planet", "Asteroids Belt & Meteoroids")),
                NcertChapterInfo(2, "Globe: Latitudes and Longitudes", "ग्लोब: अक्षांश एवं देशांतर", "Equator 0°, Tropic of Cancer 23½° N, Tropic of Capricorn 23½° S, Arctic and Antarctic Circles, Heat Zones (Torrid, Temperate, Frigid), Prime Meridian 0° Greenwich, Indian Standard Time (IST 82°30' E).", listOf("Three Heat Zones of Earth", "Longitudes & Local Time Calculation", "Indian Standard Time (IST = GMT + 5:30)")),
                NcertChapterInfo(3, "Motions of the Earth", "पृथ्वी की गतियाँ", "Rotation on tilted axis causes day and night, circle of illumination, revolution around Sun causes seasons, leap year concept, summer and winter solstices, equinoxes.", listOf("Rotation vs Revolution", "Solstice (June 21 & Dec 22)", "Equinox (March 21 & Sept 23)")),
                NcertChapterInfo(4, "Maps", "मानचित्र", "Types of maps (Physical, Political, Thematic), three components of maps: Distance (scale: small scale vs large scale), Direction (cardinal points & compass), Symbols (conventional signs).", listOf("Physical vs Political vs Thematic Maps", "Scale Calculation on Maps", "Conventional Signs and Symbols")),
                NcertChapterInfo(5, "Major Domains of the Earth", "पृथ्वी के प्रमुख परिमंडल", "Lithosphere (continents: Asia, Africa, North America, South America, Antarctica, Europe, Australia), Hydrosphere (Pacific, Atlantic, Indian, Southern, Arctic Oceans), Atmosphere, Biosphere.", listOf("Seven Continents & Five Oceans", "Marianas Trench & Mount Everest", "Atmosphere Composition & Biosphere Zone")),
                NcertChapterInfo(6, "Major Landforms of the Earth", "पृथ्वी के प्रमुख स्थलरूप", "Internal and external processes, Mountains (Fold mountains like Himalayas/Alps, Block mountains, Volcanic mountains), Plateaus (Deccan, Tibet roof of the world), Plains agriculture.", listOf("Fold vs Block vs Volcanic Mountains", "Tibet Plateau & Mineral Wealth", "Plains Human Settlement Density"))
            )
        ),
        NcertBook(
            id = "ncert_c6_civ",
            title = "Social and Political Life – I (Civics)",
            hindiTitle = "सामाजिक एवं राजनीतिक जीवन – I (नागरिक शास्त्र)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.POLITY,
            subjectCategory = "Social Science - Civics",
            cbseBookCode = "NCERT-6-CIV (fess3)",
            description = "Diversity, Discrimination, Democratic Government, Panchayati Raj, Rural and Urban Administration, and Livelihoods.",
            coverColorHex = 0xFFE65100,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?fess3=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "Understanding Diversity", "विविधता की समझ", "Diversity in food, festivals, languages; case study of Ladakh (cold desert, wool trade, Buddhism/Islam) and Kerala (spice coast, spice trade with Arabs and Chinese).", listOf("Unity in Diversity Concept", "Ladakh vs Kerala Comparison", "National Anthem & Shared Heritage")),
                NcertChapterInfo(2, "Diversity and Discrimination", "विविधता एवं भेदभाव", "Prejudice and stereotypes ('boys don't cry'), discrimination against Dalits, Dr. BR Ambedkar's childhood experience at Koregaon station, Indian Constitution's vision against untouchability.", listOf("Prejudice vs Stereotype Definitions", "Dr. Ambedkar's Struggle Against Untouchability", "Constitutional Equality Safeguards")),
                NcertChapterInfo(3, "What is Government?", "सरकार क्या है?", "Role of government (law, defense, public welfare), levels of government (Local, State, National), laws and the government, types of government (Democratic vs Monarchy), women's suffrage movement.", listOf("Functions of Government", "Local, State and Central Levels", "Suffrage Movement & Democratic Voting")),
                NcertChapterInfo(4, "Key Elements of a Democratic Government", "लोकतांत्रिक सरकार के मुख्य तत्व", "South Africa's struggle against Apartheid (Nelson Mandela), participation of people via voting, public protests, resolving religious procession and inter-state river disputes (Cauvery water dispute).", listOf("Apartheid Abolition in South Africa", "Resolving Conflicts in Democracy", "Cauvery Water Dispute Example")),
                NcertChapterInfo(5, "Panchayati Raj", "पंचायती राज", "Gram Sabha (meeting of all adults), Gram Panchayat election, Sarpanch and Ward Panchs, Secretary appointed by government, functions of Panchayat, sources of funds, three levels of Panchayats (Gram, Block/Samiti, Zilla Parishad).", listOf("Gram Sabha vs Gram Panchayat", "Three-Tier Panchayati Raj System", "Zilla Parishad Administration")),
                NcertChapterInfo(6, "Rural Administration", "गाँव का प्रशासन", "A quarrel in the village, role of Police Station and SHO, work of Patwari/Lekhpal (measuring land and keeping village records, Khasra), Hindu Succession Amendment Act 2005 (daughters get equal share in agricultural land).", listOf("FIR Filing at Local Police Station", "Duties of Patwari (Land Records)", "Hindu Succession Amendment Act 2005")),
                NcertChapterInfo(7, "Urban Administration", "नगर प्रशासन", "Children playing cricket break a street light; Municipal Corporation in big cities (Ward Councillors, Committees), Commissioner appointed by government, garbage disposal, Yasmin Khala's explanations.", listOf("Municipal Corporation vs Council", "Ward Councillors Role", "Garbage Collection & Public Health")),
                NcertChapterInfo(8, "Rural Livelihoods", "ग्रामीण क्षेत्र में आजीविका", "Kalpattu village in Tamil Nadu: Thulasi agricultural laborer, Sekar small farmer, Ramalingam large landowner and rice mill owner, fishing community in Pudupet.", listOf("Agricultural Laborers Debt Cycle", "Small vs Large Farmers in India", "Fishing Livelihoods & Marine Seasons")),
                NcertChapterInfo(9, "Urban Livelihoods", "शहरी क्षेत्र में आजीविका", "Street vendors and hawkers, self-employed workers, permanent vs casual jobs in garment factories, corporate offices and call centers, benefits of regular permanent employment (provident fund, medical leave).", listOf("Street Hawkers & Vending Zones", "Casual Wage Labor vs Permanent Jobs", "Call Center & Corporate Employment"))
            )
        ),
        NcertBook(
            id = "ncert_c6_eng",
            title = "Poorvi / Honeysuckle (English)",
            hindiTitle = "पूर्वी / हनीसकल (अंग्रेजी)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.ENGLISH,
            subjectCategory = "Language - English",
            cbseBookCode = "NCERT-6-ENG (feen1)",
            description = "CBSE Class 6 English curriculum: Beautiful prose, classic poems, vocabulary expansion, and supplementary reading from A Pact with the Sun.",
            coverColorHex = 0xFF6A1B9A,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?feen1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "A Bottle of Dew / Who Did Patrick's Homework?", "पैट्रिक का गृहकार्य किसने किया?", "Humorous tale about Patrick who hated homework until a little elf (or his own effort!) inspired him to score straight A's.", listOf("Hard Work and Self-Reliance", "Poem: A House, A Home (Lorraine M. Halli)")),
                NcertChapterInfo(2, "How the Dog Found Himself a New Master!", "कुत्ते को कैसे मिला नया मालिक!", "Delightful folktale explaining how the wild dog tested the wolf, bear, and lion before choosing human beings as the most faithful companion.", listOf("Folklore & Animal Domestication", "Poem: The Kite (Harry Behn)")),
                NcertChapterInfo(3, "Taro's Reward", "तारो का पुरस्कार", "Japanese fable about an affectionate, dutiful young woodcutter whose sincere devotion to his elderly parents was magically rewarded.", listOf("Filial Piety and Respect", "Poem: The Quarrel (Eleanor Farjeon)")),
                NcertChapterInfo(4, "An Indian-American Woman in Space: Kalpana Chawla", "कल्पना चावला: अंतरिक्ष में पहली भारतीय-अमेरिकी महिला", "Biographical tribute to astronaut Kalpana Chawla from Karnal, Haryana, who pursued her dream of reaching the stars aboard Columbia STS-107.", listOf("Inspiring Role Model Biography", "Space Exploration Vocabulary", "Poem: Beauty (E-Yeh-Shure)")),
                NcertChapterInfo(5, "A Different Kind of School", "एक अलग तरह का विद्यालय", "E.V. Lucas' heartwarming story of Miss Beam's school where every child experiences a Blind Day, Lame Day, and Deaf Day to cultivate true empathy.", listOf("Cultivating Empathy and Kindness", "Poem: Where Do All the Teachers Go? (Peter Dixon)")),
                NcertChapterInfo(6, "Who I Am", "मैं कौन हूँ", "Six children from diverse backgrounds (Radha, Nasir, Rohit, Serbjit, Peter, Dolma) voice their dreams, interests, and aspirations.", listOf("Celebrating Individuality & Diversity", "Poem: The Wonderful Words (Mary O'Neill)")),
                NcertChapterInfo(7, "Fair Play", "निष्पक्ष न्याय", "Munshi Premchand's classic story about childhood friends Jumman Shaikh and Algu Chowdhry whose friendship was tested on the seat of the Panch.", listOf("Premchand's Village Justice Theme", "The Voice of the Panch is the Voice of God")),
                NcertChapterInfo(8, "A Pact with the Sun (Supplementary Highlights)", "सूरज के साथ समझौता (पूरक)", "Selected tales: A Tale of Two Birds, The Friendly Mongoose, The Shepherd's Treasure, Tansen, and The Monkey and the Crocodile.", listOf("Panchatantra Animal Wisdom", "Moral Character Insights"))
            )
        ),
        NcertBook(
            id = "ncert_c6_hin",
            title = "Malhar / Vasant Bhag 1 (Hindi Reader)",
            hindiTitle = "मल्हार / वसंत भाग 1 (हिंदी)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.HINDI,
            subjectCategory = "Language - Hindi",
            cbseBookCode = "NCERT-6-HIN (feds1)",
            description = "CBSE Class 6 Hindi reader: Poems, memoirs, short stories, and historical accounts by Kedarnath Agarwal, Krishna Sobti, and Premchand.",
            coverColorHex = 0xFFD84315,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?feds1=0-10",
            chapters = listOf(
                NcertChapterInfo(1, "वह चिड़िया जो (कविता)", "केदारनाथ अग्रवाल", "नीले पंखों वाली छोटी, संतोषी चिड़िया जो दूध भरे ज्वार के दानों को चाव से खाती है और वन में मधुर कंठ से गाती है।", listOf("संतोषी स्वभाव की प्रेरणा", "प्रकृति से गहरा लगाव", "कविता का गेय सौंदर्य")),
                NcertChapterInfo(2, "बचपन (संस्मरण)", "कृष्णा सोबती", "लेखिका द्वारा अपने बचपन के शिमला, ग्रामोफोन, पोशाकों, चॉकलेट और पहले चश्मे का सुरुचिपूर्ण संस्मरण।", listOf("संस्मरण विधा", "बदलते समय के साथ जीवनशैली", "शिमला की ऐतिहासिक स्मृतियाँ")),
                NcertChapterInfo(3, "नादान दोस्त (कहानी)", "मुंशी प्रेमचंद", "केशव और श्यामा की नादानी में चिड़िया के अंडों को छूने और अंडों के टूट जाने की संवेदनशील बाल-मनोवैज्ञानिक कहानी।", listOf("बाल मनोविज्ञान का सजीव चित्रण", "अज्ञानतावश हुई भूल का पश्चाताप", "प्रेमचंद की सहज भाषा शैली")),
                NcertChapterInfo(4, "चाँद से थोड़ी सी गप्पें (कविता)", "शमशेर बहादुर सिंह", "दस-ग्यारह साल की एक लड़की द्वारा घटते-बढ़ते चाँद से की गई भोली-भाली और चुलबुली बातचीत।", listOf("कल्पनाशीलता और बाल कौतूहल", "अतुकांत कविता का शिल्प", "चाँद के आकार का वर्णन")),
                NcertChapterInfo(5, "अक्षरों का महत्व (निबंध)", "गुणाकर मुले", "मानव इतिहास में अक्षरों की खोज, प्रागैतिहासिक काल के चित्र संदेश और लिपि के विकास का ऐतिहासिक विश्लेषण।", listOf("लिपि और भाषा का विकास", "शिलाचित्रों से आधुनिक अक्षरों तक", "ज्ञान का संरक्षण")),
                NcertChapterInfo(6, "पार नज़र के (कहानी)", "जयंत विष्णु नार्लीकर", "मंगल ग्रह की ज़मीन के नीचे रहने वाले छोटू और उसके पिता की वैज्ञानिक अंतरिक्ष कहानी।", listOf("वैज्ञानिक दृष्टिकोण एवं कल्पना", "मंगल ग्रह का वातावरण", "अंतरिक्ष यान का अन्वेषण")),
                NcertChapterInfo(7, "साथी हाथ बढ़ाना (गीत)", "साहिर लुधियानवी", "सामूहिक श्रम और एकता की शक्ति का जयघोष करने वाला लोकप्रिय प्रेरणादायी गीत।", listOf("एकता और सहयोग का महत्व", "श्रमजीवियों का गौरव", "सकारात्मक प्रेरणा")),
                NcertChapterInfo(8, "ऐसे-ऐसे (एकांकी)", "विष्णु प्रभाकर", "गृहकार्य न करने पर पेट में 'ऐसे-ऐसे' होने का बहाना बनाने वाले मोहन की हास्य-नाटिका।", listOf("हास्य एकांकी विधा", "बच्चों के स्कूल से बचने के बहाने", "डॉक्टर और वैद्य जी के संवाद")),
                NcertChapterInfo(9, "टिकट अलबम (कहानी)", "सुंदरा रामस्वामी", "राजप्पा और नागराजन के टिकट अलबम के माध्यम से ईर्ष्या, पश्चाताप और बाल-सुलभ आत्मग्लानि की मर्मस्पर्शी कथा।", listOf("ईर्ष्या और पश्चाताप का द्वंद्व", "टिकट संग्रह का शौक", "कहानी का नैतिक अंत")),
                NcertChapterInfo(10, "झाँसी की रानी (कविता)", "सुभद्रा कुमारी चौहान", "रानी लक्ष्मीबाई के अप्रतिम शौर्य और 1857 के प्रथम स्वतंत्रता संग्राम की अमर वीरगाथा।", listOf("वीर रस की कालजयी रचना", "बुंदेले हरबोलों के मुख से", "रानी लक्ष्मीबाई का अमर बलिदान"))
            )
        ),
        NcertBook(
            id = "ncert_c6_skt",
            title = "Deepakam / Ruchira Bhag 1 (Sanskrit)",
            hindiTitle = "दीपकम / रुचिरा भाग 1 (संस्कृत)",
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.SANSKRIT,
            subjectCategory = "Language - Sanskrit",
            cbseBookCode = "NCERT-6-SKT (fesk1)",
            description = "CBSE Class 6 introductory Sanskrit reader: Basic noun and verb forms, vocabulary, simple fables, and classroom conversations.",
            coverColorHex = 0xFF455A64,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?fesk1=0-8",
            chapters = listOf(
                NcertChapterInfo(1, "शब्दपरिचयः I (पुंल्लिङ्ग)", "प्रथमः पाठः", "पुंल्लिङ्ग अकारान्त शब्दों (एषः कः? सः कः?) और एकवचन, द्विवचन, बहुवचन का परिचय।", listOf("पुंल्लिङ्ग शब्द रूप", "चषकः, सौचिकः, शुनकौ")),
                NcertChapterInfo(2, "शब्दपरिचयः II (स्त्रीलिङ्ग)", "द्वितीयः पाठः", "स्त्रीलिङ्ग आकारान्त शब्दों (एषा का? सा का?) का सरल वाक्य प्रयोग।", listOf("स्त्रीलिङ्ग शब्द रूप", "दोला, घटिका, चटके")),
                NcertChapterInfo(3, "शब्दपरिचयः III (नपुंसकलिङ्ग)", "तृतीयः पाठः", "नपुंसकलिङ्ग अकारान्त शब्दों (एतत् किम्? तत् किम्?) का ज्ञान।", listOf("नपुंसकलिङ्ग शब्द रूप", "खनित्रम्, विश्रामगृहम्, पर्णानि")),
                NcertChapterInfo(4, "विद्यालयः", "चतुर्थः पाठः", "विद्यालय का वातावरण, छात्र-शिक्षक वार्तालाप और सर्वनाम पदों (अहम्, त्वम्, आवाम्, यूयम्) का अभ्यास।", listOf("संस्कृत में परस्पर वार्तालाप", "अस्मद् एवं युष्मद् प्रयोग")),
                NcertChapterInfo(5, "वृक्षाः", "पञ्चमः पाठः (डॉ. हर्षदेव माधव)", "वृक्षों के परोपकार, पक्षियों के बसेरे और छाया-दान का सुंदर पद्य वर्णन।", listOf("वने वने निवसन्तो वृक्षाः", "प्रकृति का सम्मान")),
                NcertChapterInfo(6, "समुद्रतटः", "षष्ठः पाठः", "भारत के समुद्र तटों (मुम्बई, गोवा, मरीना, विशाखापट्टनम) और तृतीया विभक्ति का अभ्यास।", listOf("भारत के प्रसिद्ध समुद्र तट", "करण कारक (तृतीया विभक्ति)")),
                NcertChapterInfo(7, "बकस्य प्रतिकारः", "सप्तमः पाठः", "बगुला और सियार की कथा - जैसे को तैसा व्यवहार (शठं प्रति शाठ्यम्)।", listOf("पञ्चतन्त्र कथा शैली", "सद्भावना का महत्व")),
                NcertChapterInfo(8, "सूक्तिस्तबकः", "अष्टमः पाठः", "परिश्रम, वाणी और सदाचार की प्रेरणा देने वाले सुभाषित।", listOf("उद्यमेन हि सिध्यन्ति कार्याणि न मनोरथैः", "प्रियवाक्यप्रदानेन सर्वे तुष्यन्ति जन्तवः"))
            )
        ),

        // ====================================================================
        // CLASS 10 NCERT TEXTBOOKS (CBSE BOARD CURRICULUM)
        // ====================================================================
        NcertBook(
            id = "ncert_c10_sci",
            title = "Science (Class 10)",
            hindiTitle = "विज्ञान (कक्षा 10)",
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.SCIENCE_GENERAL,
            subjectCategory = "Science & Technology",
            cbseBookCode = "NCERT-10-SCI (jesc1)",
            description = "Official CBSE Class 10 Science textbook: Chemical Reactions, Acids & Bases, Metals & Non-metals, Carbon Compounds, Life Processes, Control & Coordination, Reproduction, Heredity, Light, Electricity, and Magnetic Effects.",
            coverColorHex = 0xFF00897B, // Teal
            officialNcertUrl = "https://ncert.nic.in/textbook.php?jesc1=0-13",
            chapters = listOf(
                NcertChapterInfo(1, "Chemical Reactions and Equations", "रासायनिक अभिक्रियाएँ एवं समीकरण", "Balancing equations, combination, decomposition, displacement, double displacement, oxidation and reduction, corrosion and rancidity.", listOf("Balancing Chemical Equations", "Types of Chemical Reactions", "Oxidation & Reduction", "Corrosion and Rancidity"), matchingChapterId = "c10_sci_ch1"),
                NcertChapterInfo(2, "Acids, Bases and Salts", "अम्ल, क्षारक एवं लवण", "Chemical properties of acids and bases, pH scale and everyday importance, common salt chemicals (NaOH, Bleaching powder, Baking soda, Washing soda, Plaster of Paris).", listOf("pH Scale & Universal Indicator", "Chlor-Alkali Process (NaOH, Cl2, H2)", "Plaster of Paris & Gypsum (CaSO4·½H2O)")),
                NcertChapterInfo(3, "Metals and Non-metals", "धातु एवं अधातु", "Physical and chemical properties, reactivity series, ionic bond formation, metallurgy and extraction of metals, refining, corrosion prevention.", listOf("Reactivity Series of Metals", "Formation & Properties of Ionic Compounds", "Extraction of Metals (Calcination vs Roasting)")),
                NcertChapterInfo(4, "Carbon and its Compounds", "कार्बन एवं उसके यौगिक", "Covalent bonding in carbon, versatile nature (catenation & tetravalency), homologous series, nomenclature, chemical properties, ethanol and ethanoic acid, soaps and detergents.", listOf("Covalent Bonding & Tetravalency", "Homologous Series & Functional Groups", "Soaps and Micelle Formation Mechanism")),
                NcertChapterInfo(5, "Life Processes", "जैव प्रक्रम", "Autotrophic and heterotrophic nutrition, human digestive system, aerobic vs anaerobic respiration, human circulatory system and double circulation, human nephron excretion.", listOf("Autotrophic vs Heterotrophic Nutrition", "Human Respiration & ATP", "Structure of Human Heart & Nephron"), matchingChapterId = "c10_sci_ch6"),
                NcertChapterInfo(6, "Control and Coordination", "नियंत्रण एवं समन्वय", "Neuron structure, reflex arc, human brain (Forebrain, Midbrain, Hindbrain), plant hormones (Auxin, Gibberellin, Cytokinin, Abscisic acid), endocrine glands in animals.", listOf("Reflex Arc and Action", "Human Brain Anatomy & Functions", "Plant Tropisms (Phototropism, Geotropism)", "Endocrine Hormones (Thyroxine, Adrenaline, Insulin)")),
                NcertChapterInfo(7, "How do Organisms Reproduce?", "जीव जनन कैसे करते हैं?", "Asexual reproduction (Fission, Fragmentation, Regeneration, Budding, Vegetative propagation, Spore formation), sexual reproduction in flowering plants, human male and female reproductive systems, contraception.", listOf("Asexual Reproduction Modes", "Double Fertilization in Angiosperms", "Human Reproductive Systems & Contraceptive Methods")),
                NcertChapterInfo(8, "Heredity", "आनुवंशिकता", "Mendel's experiments with pea plants, monohybrid cross (3:1 ratio) and dihybrid cross (9:3:3:1 ratio), laws of inheritance, sex determination in humans (XX vs XY).", listOf("Mendel's Monohybrid & Dihybrid Crosses", "Dominant vs Recessive Alleles", "Sex Determination in Humans")),
                NcertChapterInfo(9, "Light – Reflection and Refraction", "प्रकाश – परावर्तन तथा अपवर्तन", "Spherical mirrors (ray diagrams, mirror formula 1/f = 1/v + 1/u, magnification), refraction of light, Snell's law, refractive index, spherical lenses (lens formula, power of lens P = 1/f).", listOf("Mirror Formula & Sign Convention", "Snell's Law of Refraction", "Lens Formula & Power of Lens (Dioptres)"), matchingChapterId = "c10_sci_ch10"),
                NcertChapterInfo(10, "The Human Eye and the Colourful World", "मानव नेत्र तथा रंगबिरंगा संसार", "Human eye structure and accommodation, vision defects (Myopia, Hypermetropia, Presbyopia) and corrections, refraction through glass prism, dispersion, atmospheric refraction (twinkling of stars), scattering of light (Tyndall effect).", listOf("Myopia & Hypermetropia Correction", "Dispersion of White Light through Prism", "Atmospheric Refraction & Twinkling Stars", "Scattering of Light & Tyndall Effect")),
                NcertChapterInfo(11, "Electricity", "विद्युत", "Electric current and potential difference, Ohm's law (V = IR), factors affecting resistance (resistivity ρ), series and parallel combinations, heating effect of electric current (Joule's law H = I²Rt), electric power (P = VI = I²R).", listOf("Ohm's Law Verification & Resistance", "Resistors in Series vs Parallel", "Joule's Law of Heating", "Electric Power & Commercial Unit kWh")),
                NcertChapterInfo(12, "Magnetic Effects of Electric Current", "विद्युत धारा के चुंबकीय प्रभाव", "Magnetic field and field lines, field due to current-carrying straight conductor, circular loop and solenoid, Fleming's Left-Hand Rule, domestic electric circuits (earthing, short circuit, overloading, fuse).", listOf("Magnetic Field Lines Properties", "Right-Hand Thumb Rule & Solenoid", "Fleming's Left-Hand Rule", "Domestic Circuit Earthing & Fuse Safety")),
                NcertChapterInfo(13, "Our Environment", "हमारा पर्यावरण", "Eco-system components (biotic and abiotic), food chains and food webs, 10% energy law (Lindeman), biological magnification of pesticides, ozone layer depletion by CFCs, waste management.", listOf("Food Chains & 10% Energy Transfer Law", "Biological Magnification of Non-biodegradables", "Ozone Hole Depletion & Waste Disposal"))
            )
        ),
        NcertBook(
            id = "ncert_c10_math",
            title = "Mathematics (Class 10)",
            hindiTitle = "गणित (कक्षा 10)",
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.MATHEMATICS,
            subjectCategory = "Mathematics",
            cbseBookCode = "NCERT-10-MATH (jemh1)",
            description = "Official CBSE Class 10 Mathematics: Real Numbers, Polynomials, Linear Equations, Quadratic Equations, Arithmetic Progressions, Triangles, Coordinate Geometry, Trigonometry, Circles, Surface Areas and Volumes, Statistics, and Probability.",
            coverColorHex = 0xFF1565C0, // Blue
            officialNcertUrl = "https://ncert.nic.in/textbook.php?jemh1=0-14",
            chapters = listOf(
                NcertChapterInfo(1, "Real Numbers", "वास्तविक संख्याएँ", "Fundamental Theorem of Arithmetic (prime factorisation, HCF × LCM = a × b), proving irrationality of √2, √3, √5.", listOf("Fundamental Theorem of Arithmetic", "HCF & LCM Prime Factorisation", "Proof of Irrationality of √2, √3, √5")),
                NcertChapterInfo(2, "Polynomials", "बहुपद", "Geometrical meaning of zeroes of a polynomial, relationship between zeroes and coefficients of quadratic polynomials (α + β = -b/a, αβ = c/a).", listOf("Zeroes of Quadratic Polynomials", "Sum and Product of Roots (α + β, αβ)", "Forming Polynomials from Given Zeroes")),
                NcertChapterInfo(3, "Pair of Linear Equations in Two Variables", "दो चर वाले रैखिक समीकरण युग्म", "Graphical and algebraic methods: substitution method, elimination method, consistency conditions (intersecting, parallel, coincident lines).", listOf("Consistency Conditions (a1/a2 ≠ b1/b2)", "Substitution & Elimination Methods", "Real-Life Word Problems")),
                NcertChapterInfo(4, "Quadratic Equations", "द्विघात समीकरण", "Standard form ax² + bx + c = 0, solution by factorisation and quadratic formula x = (-b ± √(b² - 4ac))/(2a), nature of roots via discriminant D = b² - 4ac.", listOf("Solving by Factorisation", "Quadratic Formula Derivation", "Nature of Roots via Discriminant D")),
                NcertChapterInfo(5, "Arithmetic Progressions", "समांतर श्रेढियाँ", "Definition of AP, general term aₙ = a + (n-1)d, sum of first n terms Sₙ = n/2[2a + (n-1)d] = n/2[a + l], real-world word problems.", listOf("nth Term Formula aₙ = a + (n-1)d", "Sum of First n Terms Sₙ", "Application to Real-World Scenarios")),
                NcertChapterInfo(6, "Triangles", "त्रिभुज", "Similar figures, Basic Proportionality Theorem (Thales Theorem) and its converse, criteria for similarity of triangles (AAA, SSS, SAS).", listOf("Basic Proportionality Theorem (BPT)", "Converse of BPT", "Similarity Criteria (AAA, SSS, SAS)")),
                NcertChapterInfo(7, "Coordinate Geometry", "निर्देशांक ज्यामिति", "Distance formula d = √[(x₂-x₁)² + (y₂-y₁)²], Section formula for internal division [(mx₂+nx₁)/(m+n), (my₂+ny₁)/(m+n)], midpoint formula.", listOf("Distance Formula Applications", "Section Formula for Internal Division", "Midpoint Formula & Collinearity")),
                NcertChapterInfo(8, "Introduction to Trigonometry", "त्रिकोणमिति का परिचय", "Trigonometric ratios of acute angles, values of trigonometric ratios of 0°, 30°, 45°, 60°, 90°, trigonometric identities: sin²θ + cos²θ = 1, 1 + tan²θ = sec²θ, 1 + cot²θ = cosec²θ.", listOf("Trigonometric Ratios (P/H, B/H, P/B)", "Table of Specific Angles (0° to 90°)", "Fundamental Trigonometric Identities"), matchingChapterId = "c10_math_ch8"),
                NcertChapterInfo(9, "Some Applications of Trigonometry", "त्रिकोणमिति के कुछ अनुप्रयोग", "Line of sight, angle of elevation, angle of depression, heights and distances problems involving single and double triangles.", listOf("Angle of Elevation vs Depression", "Single Triangle Height Calculations", "Two-Observation Point Problems")),
                NcertChapterInfo(10, "Circles", "वृत्त", "Tangent to a circle, Theorem 1: Tangent at any point is perpendicular to radius through point of contact, Theorem 2: Lengths of tangents drawn from an external point to a circle are equal.", listOf("Tangent Perpendicular to Radius", "Equal Tangents from External Point", "Proofs and Geometric Applications")),
                NcertChapterInfo(11, "Areas Related to Circles", "वृत्तों से संबंधित क्षेत्रफल", "Area of sector of a circle (θ/360° × πr²), length of an arc of a sector (θ/360° × 2πr), area of segment of a circle.", listOf("Area of Minor and Major Sectors", "Length of Arc Formula", "Area of Segment of Circle")),
                NcertChapterInfo(12, "Surface Areas and Volumes", "पृष्ठीय क्षेत्रफल और आयतन", "Surface areas and volumes of combinations of two solid shapes: cubes, cuboids, spheres, hemispheres, and right circular cylinders/cones.", listOf("Volume of Combined Solids", "Surface Area of Combined Solids", "Conversion of Solid from One Shape to Another")),
                NcertChapterInfo(13, "Statistics", "सांख्यिकी", "Mean of grouped data (Direct method, Assumed mean method), Mode of grouped data using modal class formula, Median of grouped data using cumulative frequency distribution.", listOf("Mean by Direct & Assumed Mean Methods", "Mode of Grouped Data Formula", "Median using Cumulative Frequency (cf)")),
                NcertChapterInfo(14, "Probability", "प्रायिकता", "Theoretical probability of an event P(E) = Number of favourable outcomes / Total possible outcomes, elementary events, complementary events P(E) + P(not E) = 1, impossible and sure events.", listOf("Classical Definition of Probability", "Complementary Events P(not E)", "Card, Coin and Dice Probability Problems"))
            )
        ),
        NcertBook(
            id = "ncert_c10_hist",
            title = "India and the Contemporary World – II (History)",
            hindiTitle = "भारत और समकालीन विश्व – II (इतिहास)",
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.HISTORY,
            subjectCategory = "Social Science - History",
            cbseBookCode = "NCERT-10-HIST (jess1)",
            description = "CBSE Class 10 History: Rise of Nationalism in Europe, Nationalism in India, The Making of a Global World, The Age of Industrialisation, and Print Culture.",
            coverColorHex = 0xFFC2185B, // Rose
            officialNcertUrl = "https://ncert.nic.in/textbook.php?jess1=0-5",
            chapters = listOf(
                NcertChapterInfo(1, "The Rise of Nationalism in Europe", "यूरोप में राष्ट्रवाद का उदय", "French Revolution and idea of nation, Napoleonic Civil Code 1804, Romanticism, Revolutions of 1848, Unification of Germany (Bismarck) and Italy (Mazzini, Cavour, Garibaldi), Allegories (Marianne & Germania).", listOf("French Revolution & Napoleonic Code", "Unification of Germany & Italy", "Visualizing the Nation (Allegories)"), matchingChapterId = "c10_hist_ch1"),
                NcertChapterInfo(2, "Nationalism in India", "भारत में राष्ट्रवाद", "First World War impacts, Mahatma Gandhi's Satyagraha (Champaran, Kheda, Ahmedabad), Rowlatt Act & Jallianwala Bagh massacre 1919, Non-Cooperation Movement, Civil Disobedience Movement (Salt March), Simon Commission, Poona Pact.", listOf("Rowlatt Satyagraha & Jallianwala Bagh", "Non-Cooperation Movement Dynamics", "Dandi Salt March & Civil Disobedience", "Poona Pact & Sense of Collective Belonging")),
                NcertChapterInfo(3, "The Making of a Global World", "भूमंडलीकृत विश्व का बनना", "Pre-modern world: Silk routes, food travels (spaghetti and potato), conquest, disease and trade (smallpox in Americas), 19th-century indentured labor migration, Great Depression 1929, post-war Bretton Woods institutions (IMF & World Bank).", listOf("Silk Routes Connecting the World", "19th-Century Indentured Labour", "Great Depression (1929) Causes", "Bretton Woods System (IMF & World Bank)")),
                NcertChapterInfo(4, "The Age of Industrialisation", "औद्योगीकरण का युग", "Before the Industrial Revolution (proto-industrialisation), hand labour vs steam power in Victorian Britain, industrialisation in colonies (Indian textile dominance, weaver miseries), rise of Indian entrepreneurs (Dwarkanath Tagore, Jamsetji Tata), market for goods.", listOf("Proto-Industrialisation Concept", "Hand Labour vs Steam Power", "Indian Textile Industry & British Policies", "Advertisements & Marketing of Goods")),
                NcertChapterInfo(5, "Print Culture and the Modern World", "मुद्रण संस्कृति और आधुनिक दुनिया", "First printed books in China, Japan, Korea; Gutenberg's printing press in Germany; Print Revolution and its impact on religion (Martin Luther's 95 Theses) and French Revolution; 19th-century reading mania; Print culture in India (vernacular press, women reformers).", listOf("Gutenberg's Printing Press Innovation", "Print Revolution & Protestant Reformation", "Print Culture and the French Revolution", "Vernacular Press Act & Indian Reformers"))
            )
        ),
        NcertBook(
            id = "ncert_c9_sci",
            title = "Science (Class 9)",
            hindiTitle = "विज्ञान (कक्षा 9)",
            grade = ClassGrade.CLASS_9,
            subject = SubjectType.SCIENCE_GENERAL,
            subjectCategory = "Science & Technology",
            cbseBookCode = "NCERT-9-SCI (iesc1)",
            description = "CBSE Class 9 Science: Matter in our Surroundings, Atoms and Molecules, Cell – Fundamental Unit of Life, Tissues, Motion, Forces, Gravitation, Work & Energy, Sound, and Food Resources.",
            coverColorHex = 0xFF00897B,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?iesc1=0-12",
            chapters = listOf(
                NcertChapterInfo(1, "Matter in Our Surroundings", "हमारे आस-पास के पदार्थ", "Physical nature of matter, states of matter (solid, liquid, gas, plasma, BEC), change of state (melting, boiling, sublimation, latent heat), evaporation factors.", listOf("States of Matter Properties", "Latent Heat of Fusion & Vaporisation", "Evaporation Cooling Effect")),
                NcertChapterInfo(2, "Is Matter Around Us Pure?", "क्या हमारे आस-पास के पदार्थ शुद्ध हैं?", "Pure substances vs mixtures (homogeneous and heterogeneous), solutions, suspensions, colloids, Tyndall effect, separation techniques, physical vs chemical changes.", listOf("Solutions, Colloids & Suspensions", "Tyndall Effect & Brownian Motion", "Separation of Mixtures")),
                NcertChapterInfo(3, "Atoms and Molecules", "परमाणु एवं अणु", "Laws of chemical combination (Mass conservation, Constant proportions), Dalton's atomic theory, atomic mass unit (u), molecules, ions, writing chemical formulas via valency criss-cross, molar mass.", listOf("Laws of Chemical Combination", "Writing Chemical Formulas by Valency", "Formula Unit Mass Calculation")),
                NcertChapterInfo(4, "Structure of the Atom", "परमाणु की संरचना", "Charged particles in matter, Thomson's plum pudding model, Rutherford's alpha-scattering gold foil experiment, Bohr's atomic model, subatomic particles (e-, p+, n), electronic configuration, isotopes and isobars.", listOf("Rutherford's Alpha Scattering Experiment", "Bohr's Atomic Shell Model", "Valency, Isotopes and Isobars")),
                NcertChapterInfo(5, "The Fundamental Unit of Life", "जीवन की मौलिक इकाई", "Cell discovery (Robert Hooke 1665), plasma membrane (osmosis and diffusion), cell wall, nucleus, cytoplasm, cell organelles (ER, Golgi, Lysosomes, Mitochondria, Plastids, Vacuoles).", listOf("Osmosis in Plant & Animal Cells", "Mitochondria: Powerhouse of Cell", "Lysosomes: Suicide Bags", "Prokaryotic vs Eukaryotic Cells")),
                NcertChapterInfo(6, "Tissues", "ऊतक", "Plant tissues (Meristematic: apical, lateral, intercalary; Permanent: parenchyma, collenchyma, sclerenchyma, xylem, phloem), Animal tissues (Epithelial, Connective: blood, bone, cartilage; Muscular: striated, smooth, cardiac; Nervous).", listOf("Meristematic vs Permanent Plant Tissues", "Complex Permanent Tissues (Xylem & Phloem)", "Connective Tissues (Blood, Bone, Ligaments)")),
                NcertChapterInfo(7, "Motion", "गति", "Distance vs displacement, speed and velocity, uniform vs non-uniform motion, acceleration, graphical representation (distance-time, velocity-time), three equations of motion (v = u + at, s = ut + ½at², v² - u² = 2as), uniform circular motion.", listOf("Distance vs Displacement Distinction", "Three Equations of Uniformly Accelerated Motion", "Interpreting Velocity-Time Graphs")),
                NcertChapterInfo(8, "Force and Laws of Motion", "बल तथा गति के नियम", "Balanced and unbalanced forces, Newton's first law (inertia), momentum p = mv, Newton's second law (F = ma), Newton's third law (action and reaction), conservation of momentum.", listOf("Inertia and Newton's First Law", "Newton's Second Law Formula F = ma", "Newton's Third Law & Momentum Conservation")),
                NcertChapterInfo(9, "Gravitation", "गुरुत्वाकर्षण", "Universal law of gravitation F = G(m1·m2)/d², importance of universal law, free fall, acceleration due to gravity (g = 9.8 m/s²), mass vs weight, thrust and pressure, Archimedes' principle, relative density.", listOf("Universal Law of Gravitation Formula", "Free Fall & Calculation of g", "Mass vs Weight Distinction", "Archimedes' Principle & Buoyancy")),
                NcertChapterInfo(10, "Work and Energy", "कार्य तथा ऊर्जा", "Scientific conception of work W = F·s, positive, negative, zero work, kinetic energy KE = ½mv², potential energy PE = mgh, law of conservation of energy, power P = W/t, commercial unit of energy (kWh).", listOf("Work Done Formula W = F·s·cosθ", "Kinetic Energy Derivation (½mv²)", "Potential Energy PE = mgh", "Law of Conservation of Energy")),
                NcertChapterInfo(11, "Sound", "ध्वनि", "Production and propagation of sound as longitudinal wave, compression and rarefaction, characteristics of sound (wavelength, frequency, amplitude, time period, speed v = λν), reflection of sound, echo, reverberation, ultrasound applications (SONAR).", listOf("Longitudinal Nature of Sound Waves", "Formula v = Frequency × Wavelength", "Echo Calculation (Minimum 17.2 m)", "Ultrasound Applications & SONAR")),
                NcertChapterInfo(12, "Improvement in Food Resources", "खाद्य संसाधनों में सुधार", "Crop yield improvement, crop variety improvement (hybridisation), crop production management (nutrients, manures, fertilizers, irrigation), crop protection, animal husbandry (cattle farming, poultry, fish farming, beekeeping).", listOf("Manures vs Chemical Fertilizers", "Cropping Patterns (Mixed, Inter, Crop Rotation)", "Animal Husbandry & Dairy/Poultry Management"))
            )
        ),
        NcertBook(
            id = "ncert_c9_math",
            title = "Mathematics (Class 9)",
            hindiTitle = "गणित (कक्षा 9)",
            grade = ClassGrade.CLASS_9,
            subject = SubjectType.MATHEMATICS,
            subjectCategory = "Mathematics",
            cbseBookCode = "NCERT-9-MATH (iemh1)",
            description = "CBSE Class 9 Mathematics: Number Systems, Polynomials, Coordinate Geometry, Linear Equations in Two Variables, Lines & Angles, Triangles, Quadrilaterals, Circles, Heron's Formula, Surface Areas & Volumes, and Statistics.",
            coverColorHex = 0xFF1565C0,
            officialNcertUrl = "https://ncert.nic.in/textbook.php?iemh1=0-12",
            chapters = listOf(
                NcertChapterInfo(1, "Number Systems", "संख्या पद्धति", "Rational numbers, real numbers and decimal expansions (terminating vs non-terminating recurring), representing irrational numbers on number line, rationalising denominator, laws of exponents for real numbers.", listOf("Terminating vs Recurring Decimal Expansions", "Rationalising the Denominator", "Laws of Real Exponents")),
                NcertChapterInfo(2, "Polynomials", "बहुपद", "Polynomials in one variable, degree of polynomial, zeroes of a polynomial, Remainder Theorem, Factor Theorem, factorising quadratic polynomials by splitting middle term, algebraic identities: (x+y+z)², (x+y)³, x³+y³+z³-3xyz.", listOf("Factor Theorem Applications", "Splitting the Middle Term Method", "Advanced Algebraic Identities Expansion")),
                NcertChapterInfo(3, "Coordinate Geometry", "निर्देशांक ज्यामिति", "Cartesian plane, coordinate axes (x-axis, y-axis), origin, quadrants (I, II, III, IV), coordinates of a point (abscissa, ordinate), plotting points in the plane.", listOf("Cartesian Coordinate System", "Abscissa and Ordinate Definitions", "Identifying Quadrants and Points")),
                NcertChapterInfo(4, "Linear Equations in Two Variables", "दो चरों वाले रैखिक समीकरण", "Linear equation standard form ax + by + c = 0, solutions of linear equations (infinitely many solutions), plotting graph of linear equation in two variables, equations of lines parallel to x-axis and y-axis.", listOf("Standard Form ax + by + c = 0", "Finding Multiple Solutions", "Graph of Linear Equation in Two Variables")),
                NcertChapterInfo(5, "Introduction to Euclid's Geometry", "यूक्लिड की ज्यामिति का परिचय", "Euclid's definitions, axioms, and postulates (especially Postulate 5 on parallel lines), equivalent versions of Euclid's fifth postulate.", listOf("Euclid's 5 Postulates", "Axioms vs Postulates Distinction", "Playfair's Parallel Postulate Axiom")),
                NcertChapterInfo(6, "Lines and Angles", "रेखाएँ और कोण", "Basic terms and definitions, intersecting lines and vertically opposite angles, parallel lines and a transversal (alternate interior angles, corresponding angles), lines parallel to the same line.", listOf("Vertically Opposite Angles Equality", "Transversal Angles on Parallel Lines", "Angle Sum Property of Lines")),
                NcertChapterInfo(7, "Triangles", "त्रिभुज", "Congruence of triangles, criteria for congruence (SAS, ASA, AAS, SSS, RHS congruence rules), properties of isosceles triangles (angles opposite to equal sides are equal).", listOf("SAS, ASA, SSS, RHS Congruence Rules", "Isosceles Triangle Theorems", "Geometric Proof Construction")),
                NcertChapterInfo(8, "Quadrilaterals", "चतुर्भुज", "Properties of a parallelogram (opposite sides equal, opposite angles equal, diagonals bisect each other), conditions for a quadrilateral to be a parallelogram, Mid-Point Theorem and its converse.", listOf("Properties of Parallelogram", "Mid-Point Theorem and Proof", "Converse of Mid-Point Theorem")),
                NcertChapterInfo(9, "Circles", "वृत्त", "Angle subtended by a chord at a point, perpendicular from centre to chord bisects the chord, equal chords and their distances from centre, angle subtended by an arc at centre is double angle at circumference, cyclic quadrilaterals (sum of opposite angles is 180°).", listOf("Angle Subtended by Arc at Centre Theorem", "Angles in the Same Segment are Equal", "Cyclic Quadrilateral Theorem (180° sum)")),
                NcertChapterInfo(10, "Heron's Formula", "हीरोन का सूत्र", "Area of a triangle using Heron's formula A = √[s(s-a)(s-b)(s-c)] where semi-perimeter s = (a+b+c)/2, application in finding areas of triangles and quadrilaterals.", listOf("Semi-Perimeter Formula s = (a+b+c)/2", "Heron's Formula Calculation Steps", "Applications to Scalene Triangles")),
                NcertChapterInfo(11, "Surface Areas and Volumes", "पृष्ठीय क्षेत्रफल और आयतन", "Surface area and volume of right circular cone (CSA = πrl, TSA = πr(l+r), V = ⅓πr²h), sphere (SA = 4πr², V = 4/3πr³) and hemisphere (CSA = 2πr², TSA = 3πr², V = ⅔πr³).", listOf("Curved & Total Surface Area of Cone", "Volume of Cone vs Cylinder", "Surface Area and Volume of Sphere & Hemisphere")),
                NcertChapterInfo(12, "Statistics", "सांख्यिकी", "Graphical representation of data: bar graphs, histograms (with uniform and varying widths), and frequency polygons.", listOf("Constructing Accurate Histograms", "Drawing Frequency Polygons", "Interpreting Grouped Data Displays"))
            )
        )
    )

    fun getBooksForGrade(grade: ClassGrade): List<NcertBook> {
        return books.filter { it.grade == grade }
    }

    fun getBooksByGradeAndSubject(grade: ClassGrade, subject: SubjectType?): List<NcertBook> {
        val gradeBooks = getBooksForGrade(grade)
        return if (subject == null) {
            gradeBooks
        } else {
            gradeBooks.filter { it.subject == subject }
        }
    }

    fun searchBooks(query: String, grade: ClassGrade? = null): List<NcertBook> {
        val q = query.trim().lowercase()
        if (q.isBlank()) {
            return if (grade != null) getBooksForGrade(grade) else books
        }
        val pool = if (grade != null) getBooksForGrade(grade) else books
        return pool.filter { book ->
            book.title.lowercase().contains(q) ||
            book.hindiTitle.lowercase().contains(q) ||
            book.subjectCategory.lowercase().contains(q) ||
            book.cbseBookCode.lowercase().contains(q) ||
            book.description.lowercase().contains(q) ||
            book.chapters.any { ch ->
                ch.title.lowercase().contains(q) ||
                ch.hindiTitle.lowercase().contains(q) ||
                ch.summary.lowercase().contains(q) ||
                ch.keyTopics.any { t -> t.lowercase().contains(q) }
            }
        }
    }

    fun getBookById(id: String): NcertBook? {
        return books.find { it.id == id }
    }

    fun getAllClass6to8Books(): List<NcertBook> {
        return books.filter { it.grade in listOf(ClassGrade.CLASS_6, ClassGrade.CLASS_7, ClassGrade.CLASS_8) }
    }
}
