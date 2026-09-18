package com.example.data

import com.example.model.ClassGrade
import com.example.model.SubjectType
import com.example.model.VideoCategory
import com.example.model.YouTubeVideoSuggestion

object VideoSuggestionRepository {

    val allVideos: List<YouTubeVideoSuggestion> = listOf(
        // ==========================================
        // CLASS 12 COMMERCE
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c12_comm_acc_01",
            title = "Accounting for Partnership: Fundamentals (One Shot Full Chapter)",
            channelName = "Rajat Arora",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ACCOUNTANCY,
            chapterTitle = "Accounting for Partnership Firms - Fundamentals",
            durationText = "2 hr 18 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "1.8M views",
            description = "Complete coverage of P&L Appropriation A/c, Partner Capital (Fixed vs Fluctuating), Interest on Capital & Drawings, Past Adjustments, and Guarantee of Profits.",
            keyTopicsCovered = listOf("P&L Appropriation", "Interest on Drawings", "Past Adjustments", "Guarantee of Profits"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Rajat+Arora+Class+12+Accounting+for+Partnership+Fundamentals+One+Shot",
            searchFallbackQuery = "Rajat Arora Class 12 Accounting for Partnership Fundamentals One Shot",
            recommendedFor = "Best for full concept mastery & board numerical questions",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_acc_02",
            title = "Share Capital: Pro-Rata Allotment & Forfeiture Masterclass",
            channelName = "Sunil Panda - The Commerce Guruji",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ACCOUNTANCY,
            chapterTitle = "Accounting for Share Capital",
            durationText = "1 hr 45 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "1.2M views",
            description = "Master the 6-column pro-rata table, calls-in-arrears, journal entries for forfeiture of shares issued at premium, and capital reserve calculation.",
            keyTopicsCovered = listOf("Pro-Rata Table", "Forfeiture of Shares", "Reissue at Discount", "Capital Reserve"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Sunil+Panda+Share+Capital+Pro+Rata+Allotment+Class+12",
            searchFallbackQuery = "Sunil Panda Share Capital Pro Rata Allotment Class 12",
            recommendedFor = "Guaranteed 6-mark board numerical question problem solving",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_acc_03",
            title = "Cash Flow Statement (AS-3 Revised) Complete Format & Adjustments",
            channelName = "CA Parag Gupta",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ACCOUNTANCY,
            chapterTitle = "Cash Flow Statement (AS-3)",
            durationText = "1 hr 55 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "950K views",
            description = "Detailed breakdown of Operating, Investing, and Financing activities. Includes working capital inverted rules and provision for tax adjustments.",
            keyTopicsCovered = listOf("Operating Activities", "Provision for Tax", "Accumulated Depreciation", "Financing Activities"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=CA+Parag+Gupta+Cash+Flow+Statement+Class+12",
            searchFallbackQuery = "CA Parag Gupta Cash Flow Statement Class 12",
            recommendedFor = "Crystal clear conceptual clarity on hidden balance sheet adjustments",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_bst_01",
            title = "Principles of Management (Fayol & Taylor) Full Revision with Tricks",
            channelName = "Sunil Panda - The Commerce Guruji",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.BUSINESS_STUDIES,
            chapterTitle = "Principles of Management",
            durationText = "1 hr 12 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "1.4M views",
            description = "Covers Fayol's 14 principles (with Scalar Chain & Gang Plank) vs Taylor's Scientific techniques (Fatigue study, Differential piece wage). Mnemonics included!",
            keyTopicsCovered = listOf("Fayol 14 Principles", "Gang Plank", "Taylor Techniques", "Mental Revolution"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Sunil+Panda+Principles+of+Management+Class+12+One+Shot",
            searchFallbackQuery = "Sunil Panda Principles of Management Class 12 One Shot",
            recommendedFor = "Essential mnemonics for memorizing all 14 principles without confusion",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_bst_02",
            title = "Financial Management: Trading on Equity & Capital Structure Numericals",
            channelName = "Rajat Arora",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.BUSINESS_STUDIES,
            chapterTitle = "Financial Management",
            durationText = "58 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "890K views",
            description = "EBIT-EPS analysis, Return on Investment (ROI) vs Cost of Debt, working capital determinants, and dividend decision case studies.",
            keyTopicsCovered = listOf("Trading on Equity", "EBIT-EPS Analysis", "ROI vs Cost of Debt", "Fixed Capital Factors"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Rajat+Arora+Financial+Management+Class+12+BST",
            searchFallbackQuery = "Rajat Arora Financial Management Class 12 BST",
            recommendedFor = "Solving the 4-mark case study question on capital structure selection",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_bst_03",
            title = "Marketing Management: The 4 Ps & Packaging Levels with Real Examples",
            channelName = "Commerce Wallah by PW",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.BUSINESS_STUDIES,
            chapterTitle = "Marketing Management",
            durationText = "1 hr 30 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "780K views",
            description = "Detailed examination of Product, Price, Place, and Promotion. Real brand case studies on packaging hierarchy (Primary, Secondary, Transportation).",
            keyTopicsCovered = listOf("4 Ps Framework", "Packaging Levels", "Promotion Mix", "Branding vs Labelling"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Commerce+Wallah+Marketing+Management+Class+12",
            searchFallbackQuery = "Commerce Wallah Marketing Management Class 12",
            recommendedFor = "High-scoring case studies identification tips for CBSE exams",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_eco_01",
            title = "National Income & Related Aggregates: All 3 Methods Numericals Marathon",
            channelName = "Rajat Arora",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ECONOMICS,
            chapterTitle = "National Income and Related Aggregates",
            durationText = "2 hr 05 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "2.1M views",
            description = "Value Added, Income, and Expenditure methods with double counting pitfalls, intermediate vs final goods, and domestic to national conversions.",
            keyTopicsCovered = listOf("Value Added Method", "Income Method", "Expenditure Method", "NFIA & NIT Conversions"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Rajat+Arora+National+Income+Numericals+Class+12+Macroeconomics",
            searchFallbackQuery = "Rajat Arora National Income Numericals Class 12 Macroeconomics",
            recommendedFor = "Must watch for securing full 6 marks in National Income calculation",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_comm_eco_02",
            title = "Money & Banking: Credit Creation Multiplier & RBI Monetary Policy",
            channelName = "Sunil Panda - The Commerce Guruji",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12_COMMERCE,
            subject = SubjectType.ECONOMICS,
            chapterTitle = "Money and Banking",
            durationText = "50 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "990K views",
            description = "Commercial bank credit creation schedule (1/LRR formula), central bank quantitative (Repo rate, CRR, SLR) and qualitative instruments.",
            keyTopicsCovered = listOf("Credit Creation Table", "Money Multiplier", "Repo vs Reverse Repo", "Cash Reserve Ratio"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Sunil+Panda+Money+and+Banking+Class+12+Economics",
            searchFallbackQuery = "Sunil Panda Money and Banking Class 12 Economics",
            recommendedFor = "Quick 50-minute revision before board examinations",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 12 SCIENCE
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c12_sci_phy_01",
            title = "Electric Charges & Fields: Full Chapter Revision in 1 Shot",
            channelName = "Physics Wallah - Alakh Pandey",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.PHYSICS,
            chapterTitle = "Electric Charges and Fields",
            durationText = "2 hr 40 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "4.5M views",
            description = "Alakh Sir explains Coulomb's law, electric field lines, electric dipole axial and equatorial derivation, and Gauss's law applications.",
            keyTopicsCovered = listOf("Coulomb's Law", "Electric Dipole Derivations", "Gauss's Theorem", "Electric Flux"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Physics+Wallah+Electric+Charges+and+Fields+Class+12+One+Shot",
            searchFallbackQuery = "Physics Wallah Electric Charges and Fields Class 12 One Shot",
            recommendedFor = "Essential foundation for Electrodynamics & board derivations",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_sci_phy_02",
            title = "Ray Optics & Optical Instruments: Complete Board Marathon",
            channelName = "Abhishek Sahu Physics",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.PHYSICS,
            chapterTitle = "Ray Optics and Optical Instruments",
            durationText = "2 hr 15 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "1.1M views",
            description = "Lens Maker's formula derivation, prism minimum deviation formula, compound microscope and astronomical telescope ray diagrams.",
            keyTopicsCovered = listOf("Lens Maker Formula", "Prism Derivation", "Microscope Ray Diagram", "Telescope Magnification"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Abhishek+Sahu+Ray+Optics+Class+12+One+Shot",
            searchFallbackQuery = "Abhishek Sahu Ray Optics Class 12 One Shot",
            recommendedFor = "Mastering the 5-mark ray diagram and derivation questions",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_sci_chem_01",
            title = "Solutions: Raoult's Law, Colligative Properties & Van't Hoff Factor",
            channelName = "Bharat Panchal - Chemistry Guruji 2.0",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.CHEMISTRY,
            chapterTitle = "Solutions",
            durationText = "1 hr 35 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "1.6M views",
            description = "Molarity, Molality, Henry's Law, Raoult's Law (ideal & non-ideal solutions), Elevation in Boiling Point, Osmotic Pressure, and Abnormal Molar Mass.",
            keyTopicsCovered = listOf("Henry's Law", "Raoult's Law", "Colligative Properties", "Van't Hoff Factor i"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Bharat+Panchal+Solutions+Class+12+Chemistry+One+Shot",
            searchFallbackQuery = "Bharat Panchal Solutions Class 12 Chemistry One Shot",
            recommendedFor = "Fast formula revision and standard board numericals",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_sci_chem_02",
            title = "Electrochemistry: Nernst Equation & Kohlrausch's Law Numericals",
            channelName = "Sourabh Raina",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.CHEMISTRY,
            chapterTitle = "Electrochemistry",
            durationText = "1 hr 10 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "940K views",
            description = "Step-by-step solutions for Nernst equation equilibrium constants, Gibbs free energy, molar conductivity, and Faraday's laws of electrolysis.",
            keyTopicsCovered = listOf("Nernst Equation", "Kohlrausch Law", "Electrochemical Cell", "Gibbs Free Energy"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Sourabh+Raina+Electrochemistry+Class+12+One+Shot",
            searchFallbackQuery = "Sourabh Raina Electrochemistry Class 12 One Shot",
            recommendedFor = "Cracking 3-mark electrochemical numerical questions with precision",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_sci_bio_01",
            title = "Sexual Reproduction in Flowering Plants (NCERT Line by Line)",
            channelName = "Seep Pahuja - Biology",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.BIOLOGY,
            chapterTitle = "Sexual Reproduction in Flowering Plants",
            durationText = "1 hr 50 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "1.3M views",
            description = "Microsporogenesis, megasporogenesis, double fertilization (syngamy + triple fusion), and apomixis explained with board exam labeled diagrams.",
            keyTopicsCovered = listOf("Pollen Grain Development", "Embryo Sac Structure", "Double Fertilization", "Apomixis"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Seep+Pahuja+Sexual+Reproduction+in+Flowering+Plants+NCERT",
            searchFallbackQuery = "Seep Pahuja Sexual Reproduction in Flowering Plants NCERT",
            recommendedFor = "NCERT diagram clarity & 100% board accuracy",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c12_sci_math_01",
            title = "Matrices and Determinants: Complete Board Exam One Shot",
            channelName = "Neha Agrawal Mathematically Inclined",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_12,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Matrices & Determinants",
            durationText = "2 hr 25 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.3M views",
            description = "Multiplication of matrices, transpose symmetric/skew-symmetric, adjoint of matrix, finding inverse via adj(A)/|A|, and solving linear equations.",
            keyTopicsCovered = listOf("Matrix Multiplication", "Inverse of Matrix", "Matrix Method for Systems", "Properties of Determinants"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Neha+Agrawal+Matrices+and+Determinants+Class+12+One+Shot",
            searchFallbackQuery = "Neha Agrawal Matrices and Determinants Class 12 One Shot",
            recommendedFor = "High-scoring 5-mark matrix method linear equations question",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 10 (BOARD EXAM)
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c10_sci_01",
            title = "Light: Reflection and Refraction (Complete Ray Diagrams & Numericals)",
            channelName = "Physics Wallah Foundation",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Light - Reflection and Refraction",
            durationText = "2 hr 10 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "3.8M views",
            description = "Concave/Convex mirror ray diagrams, Lens formula, Mirror formula, Cartesian sign conventions, and Refractive index numericals.",
            keyTopicsCovered = listOf("Mirror Formula", "Lens Formula", "Ray Diagrams", "Sign Conventions", "Power of Lens"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Physics+Wallah+Foundation+Class+10+Light+Reflection+and+Refraction+One+Shot",
            searchFallbackQuery = "Physics Wallah Foundation Class 10 Light Reflection and Refraction One Shot",
            recommendedFor = "Mastering all 6 concave mirror ray diagrams and sign convention numericals",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_sci_02",
            title = "Chemical Reactions and Equations: Full Chapter One Shot",
            channelName = "Shubham Pathak",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Chemical Reactions and Equations",
            durationText = "1 hr 15 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.4M views",
            description = "Balancing chemical equations trick, Combination, Decomposition, Displacement, Double Displacement, Redox (Oxidation & Reduction), Corrosion & Rancidity.",
            keyTopicsCovered = listOf("Balancing Equations Trick", "Types of Chemical Reactions", "Thermal Decomposition", "Redox Reactions"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Shubham+Pathak+Chemical+Reactions+and+Equations+Class+10+One+Shot",
            searchFallbackQuery = "Shubham Pathak Chemical Reactions and Equations Class 10 One Shot",
            recommendedFor = "Quick conceptual revision with colorful animations & board notes",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_sci_03",
            title = "Life Processes: Nutrition, Respiration, Transport & Excretion",
            channelName = "Khan Academy India",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Life Processes",
            durationText = "1 hr 40 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "1.9M views",
            description = "Alimentary canal enzymes breakdown, human heart double circulation diagram, nephron filtration in kidneys, and stomatal opening mechanism.",
            keyTopicsCovered = listOf("Double Circulation", "Nephron Structure", "Digestive Enzymes", "Photosynthesis Stages"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Khan+Academy+India+Life+Processes+Class+10",
            searchFallbackQuery = "Khan Academy India Life Processes Class 10",
            recommendedFor = "Clear conceptual diagrams without unnecessary rote memorization",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_sci_04",
            title = "Electricity: Circuit Diagrams, Ohm's Law & Series-Parallel Numericals",
            channelName = "Science and Fun - Ashu Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Electricity",
            durationText = "1 hr 50 min",
            category = VideoCategory.NUMERICALS_PRACTICE,
            viewsCount = "2.7M views",
            description = "Practical demonstrations of Ohm's law, calculating equivalent resistance of combined series-parallel circuits, Joule's law of heating, and electric power.",
            keyTopicsCovered = listOf("Ohm's Law", "Series & Parallel Circuits", "Equivalent Resistance", "Joule's Law", "Electric Power"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Ashu+Sir+Science+and+Fun+Electricity+Class+10+One+Shot",
            searchFallbackQuery = "Ashu Sir Science and Fun Electricity Class 10 One Shot",
            recommendedFor = "Practical real-life experiments showing current flow & resistor circuits",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_math_01",
            title = "Real Numbers & Polynomials: Complete Chapter with Board Questions",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Real Numbers & Polynomials",
            durationText = "1 hr 25 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "5.2M views",
            description = "Dear Sir's fun mnemonics for Fundamental Theorem of Arithmetic, proving irrationality of √2 and √5, relationship between zeroes and coefficients of quadratic polynomials.",
            keyTopicsCovered = listOf("HCF and LCM relation", "Proof of Irrationality", "Zeroes of Polynomial", "Sum & Product of Zeroes"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Real+Numbers+Class+10+Maths+One+Shot",
            searchFallbackQuery = "Dear Sir Real Numbers Class 10 Maths One Shot",
            recommendedFor = "Fast-paced, entertaining explanation that removes math anxiety",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_math_02",
            title = "Introduction to Trigonometry: Angles Table Trick & Identity Proofs",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Introduction to Trigonometry",
            durationText = "1 hr 38 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "7.1M views",
            description = "Hand palm trick for remembering trigonometric ratios of 0°, 30°, 45°, 60°, 90°. Rigorous proofs of identities like sin²θ + cos²θ = 1 and sec²θ - tan²θ = 1.",
            keyTopicsCovered = listOf("Trig Values Table Trick", "sin cos tan ratios", "Trigonometric Identities", "LHS = RHS Proofs"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Trigonometry+Class+10+Maths+Trick",
            searchFallbackQuery = "Dear Sir Trigonometry Class 10 Maths Trick",
            recommendedFor = "The easiest way to remember trigonometry tables without ever forgetting",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_sst_01",
            title = "The Rise of Nationalism in Europe: Complete Animated Storyline",
            channelName = "Digraj Singh Rajput",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.HISTORY,
            chapterTitle = "The Rise of Nationalism in Europe",
            durationText = "2 hr 02 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "3.4M views",
            description = "Digraj Sir narrating the French Revolution impact, Napoleonic Civil Code of 1804, Unification of Germany (Bismarck), Unification of Italy (Mazzini, Garibaldi, Cavour), and Balkans crisis.",
            keyTopicsCovered = listOf("Napoleonic Code 1804", "Zollverein Customs Union", "Unification of Germany", "Unification of Italy", "Balkans Crisis"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Digraj+Singh+Rajput+The+Rise+of+Nationalism+in+Europe+One+Shot",
            searchFallbackQuery = "Digraj Singh Rajput The Rise of Nationalism in Europe One Shot",
            recommendedFor = "Story-based comprehension that helps write 5-mark structured answers",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c10_sst_02",
            title = "Power Sharing & Federalism: Full Civics Board Exam Revision",
            channelName = "Shubham Pathak",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_10,
            subject = SubjectType.POLITY,
            chapterTitle = "Power Sharing & Federalism",
            durationText = "1 hr 10 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "1.7M views",
            description = "Belgium vs Sri Lanka ethnic composition case study, horizontal vs vertical power sharing, Union, State, and Concurrent lists under Indian federalism.",
            keyTopicsCovered = listOf("Belgium vs Sri Lanka", "Horizontal Power Sharing", "Union vs State List", "Decentralization 1992"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Shubham+Pathak+Power+Sharing+Class+10+Civics",
            searchFallbackQuery = "Shubham Pathak Power Sharing Class 10 Civics",
            recommendedFor = "Mastering assertion-reasoning & comparison questions in board exams",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 11 COMMERCE & SCIENCE
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c11_comm_acc_01",
            title = "Class 11 Accountancy: Golden Rules & Journal Entries Masterclass",
            channelName = "Rajat Arora",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_11_COMMERCE,
            subject = SubjectType.ACCOUNTANCY,
            chapterTitle = "Journal Entries & Accounting Equation",
            durationText = "1 hr 40 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.9M views",
            description = "Traditional vs Modern rules of debit and credit. Trade discount vs Cash discount entries, bad debts recovered, and compound journal entries.",
            keyTopicsCovered = listOf("Golden Rules of Accounting", "Journal Entries", "Trade vs Cash Discount", "Compound Entries"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Rajat+Arora+Journal+Entries+Class+11+Accounts",
            searchFallbackQuery = "Rajat Arora Journal Entries Class 11 Accounts",
            recommendedFor = "The bedrock foundation every Commerce student must master",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c11_sci_phy_01",
            title = "Class 11 Physics: Kinematics (Motion in 1D & 2D) Full One Shot",
            channelName = "Physics Wallah - Alakh Pandey",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_11,
            subject = SubjectType.PHYSICS,
            chapterTitle = "Kinematics & Motion",
            durationText = "3 hr 10 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "3.2M views",
            description = "Equations of motion via calculus & graphical methods, projectile motion trajectories, range, maximum height, and relative velocity in 2D.",
            keyTopicsCovered = listOf("Equations of Motion", "Calculus Method", "Projectile Motion", "Relative Velocity"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Physics+Wallah+Kinematics+Class+11+One+Shot",
            searchFallbackQuery = "Physics Wallah Kinematics Class 11 One Shot",
            recommendedFor = "Deep concept building for school exams & JEE/NEET basics",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 9
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c9_sci_01",
            title = "Class 9 Matter in Our Surroundings: Complete Chapter Animation",
            channelName = "Magnet Brains",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_9,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Matter in Our Surroundings",
            durationText = "1 hr 18 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.1M views",
            description = "States of matter (solid, liquid, gas, plasma, BEC), latent heat of fusion & vaporization, factors affecting evaporation with daily life examples.",
            keyTopicsCovered = listOf("States of Matter", "Latent Heat", "Sublimation", "Evaporation Cooling Effect"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Magnet+Brains+Matter+in+Our+Surroundings+Class+9+One+Shot",
            searchFallbackQuery = "Magnet Brains Matter in Our Surroundings Class 9 One Shot",
            recommendedFor = "Excellent visual diagrams explaining particle behavior at microscopic level",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c9_math_01",
            title = "Class 9 Number Systems & Coordinate Geometry: Easy One Shot",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_9,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Number Systems",
            durationText = "1 hr 20 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "4.1M views",
            description = "Rationalizing denominators, laws of exponents for real numbers, decimal expansions (terminating vs non-terminating recurring), and Cartesian plane.",
            keyTopicsCovered = listOf("Rational Numbers", "Rationalizing the Denominator", "Laws of Exponents", "Cartesian Plane"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Number+Systems+Class+9+Maths",
            searchFallbackQuery = "Dear Sir Number Systems Class 9 Maths",
            recommendedFor = "Makes math fun and simple for Class 9 students",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 8 (NCERT FOUNDATION)
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c8_sci_01",
            title = "Class 8 Science: Force and Pressure Full Chapter One Shot",
            channelName = "Magnet Brains",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Force and Pressure",
            durationText = "55 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.8M views",
            description = "Complete breakdown of Contact vs Non-Contact forces, pressure calculation (P = F/A), liquid pressure, and atmospheric pressure demonstrations.",
            keyTopicsCovered = listOf("Types of Forces", "Formula P=F/A", "Pressure in Fluids", "Atmospheric Pressure"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Magnet+Brains+Force+and+Pressure+Class+8+One+Shot",
            searchFallbackQuery = "Magnet Brains Force and Pressure Class 8 One Shot",
            recommendedFor = "Essential foundation for Class 9 Physics kinematics and Newton's laws",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c8_math_01",
            title = "Class 8 Maths: Rational Numbers & Linear Equations Super Concept",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_8,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Rational Numbers",
            durationText = "1 hr 10 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "3.5M views",
            description = "Closure, commutative, associative properties, additive & multiplicative inverses, and solving linear equations with single variable step-by-step.",
            keyTopicsCovered = listOf("Properties of Rational Numbers", "Additive/Multiplicative Inverse", "Transposition Method", "Word Problems"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Rational+Numbers+Class+8+Maths",
            searchFallbackQuery = "Dear Sir Rational Numbers Class 8 Maths",
            recommendedFor = "Fun, conceptual approach with high retention mnemonics",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 7 (NCERT FOUNDATION)
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c7_sci_01",
            title = "Class 7 Science: Nutrition in Plants & Acids, Bases and Salts One Shot",
            channelName = "Magnet Brains",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Nutrition in Plants",
            durationText = "50 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "1.9M views",
            description = "Autotrophic vs Heterotrophic modes, photosynthesis equation, stomatal guard cells, natural indicators (litmus, turmeric, china rose), and neutralization.",
            keyTopicsCovered = listOf("Photosynthesis in Plants", "Stomata", "Natural Indicators", "Neutralization Reaction"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Magnet+Brains+Nutrition+in+Plants+Class+7+One+Shot",
            searchFallbackQuery = "Magnet Brains Nutrition in Plants Class 7 One Shot",
            recommendedFor = "Clear diagrams illustrating plant physiology and chemistry reactions",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c7_math_01",
            title = "Class 7 Maths: Integers, Fractions & Decimals Complete Revision",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_7,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Integers & Operations",
            durationText = "1 hr 05 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "3.2M views",
            description = "Multiplication and division rules for integers, properties of operations, reciprocal of fractions, and decimal multiplication tricks.",
            keyTopicsCovered = listOf("Integer Multiplication Rules", "BODMAS", "Fraction Reciprocals", "Decimal Arithmetic"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Integers+Class+7+Maths",
            searchFallbackQuery = "Dear Sir Integers Class 7 Maths",
            recommendedFor = "Eliminates sign confusion (+/-) permanently with easy visual rules",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // CLASS 6 (NCERT FOUNDATION)
        // ==========================================
        YouTubeVideoSuggestion(
            id = "c6_sci_01",
            title = "Class 6 Science: Components of Food & Getting to Know Plants",
            channelName = "Magnet Brains",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.SCIENCE_GENERAL,
            chapterTitle = "Components of Food",
            durationText = "48 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "1.7M views",
            description = "Major nutrients in food (carbohydrates, fats, proteins, vitamins, minerals, roughage, water), balanced diet, and deficiency disease charts.",
            keyTopicsCovered = listOf("Nutrients & Tests (Iodine, Biuret)", "Balanced Diet", "Deficiency Diseases", "Plant Parts & Leaves"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Magnet+Brains+Components+of+Food+Class+6+One+Shot",
            searchFallbackQuery = "Magnet Brains Components of Food Class 6 One Shot",
            recommendedFor = "Engaging real-life examples of food tests and plant structures",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "c6_math_01",
            title = "Class 6 Maths: Knowing Our Numbers & Basic Geometrical Ideas",
            channelName = "Dear Sir",
            verifiedChannel = true,
            grade = ClassGrade.CLASS_6,
            subject = SubjectType.MATHEMATICS,
            chapterTitle = "Knowing Our Numbers",
            durationText = "58 min",
            category = VideoCategory.ONE_SHOT,
            viewsCount = "2.9M views",
            description = "Indian vs International system of numeration, Roman numerals, brackets estimation, points, lines, rays, angles, and polygons made super easy.",
            keyTopicsCovered = listOf("Indian vs International Numeration", "Roman Numerals", "Estimation & Rounding", "Basic Geometry"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Dear+Sir+Knowing+Our+Numbers+Class+6+Maths",
            searchFallbackQuery = "Dear Sir Knowing Our Numbers Class 6 Maths",
            recommendedFor = "Step-by-step guidance for early middle school mathematics confidence",
            isBoardTopperFavorite = true
        ),

        // ==========================================
        // COMPETITIVE (CUET / CA FOUNDATION / JEE / NEET)
        // ==========================================
        YouTubeVideoSuggestion(
            id = "comp_cuet_01",
            title = "CUET 2026 Strategy & Domain Subjects Complete Preparation Guide",
            channelName = "Adda247 CUET",
            verifiedChannel = true,
            grade = ClassGrade.COMPETITIVE,
            subject = SubjectType.ECONOMICS,
            chapterTitle = "CUET Domain Prep",
            durationText = "1 hr 15 min",
            category = VideoCategory.PYQ_STRATEGY,
            viewsCount = "850K views",
            description = "Syllabus mapping from NCERT to NTA CUET format, time management for 50 questions in 45 minutes, negative marking strategy, and mock test tips.",
            keyTopicsCovered = listOf("NTA CUET Pattern", "Negative Marking Elimination", "Time Management", "Speed Solving Tricks"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=Adda247+CUET+Strategy+and+Syllabus",
            searchFallbackQuery = "Adda247 CUET Strategy and Syllabus",
            recommendedFor = "University entrance exam readiness right alongside boards",
            isBoardTopperFavorite = true
        ),
        YouTubeVideoSuggestion(
            id = "comp_ca_01",
            title = "CA Foundation Accounting Basics & True/False Strategy Marathon",
            channelName = "CA Wallah by PW",
            verifiedChannel = true,
            grade = ClassGrade.COMPETITIVE,
            subject = SubjectType.ACCOUNTANCY,
            chapterTitle = "CA Foundation Fundamentals",
            durationText = "2 hr 30 min",
            category = VideoCategory.CONCEPT_DEEP_DIVE,
            viewsCount = "670K views",
            description = "Theoretical framework of accounting, accounting standards overview, and scoring full 12 marks in the mandatory True/False section with reasoning.",
            keyTopicsCovered = listOf("Accounting Concepts", "True/False with Reasons", "Consignment & Bills", "Trial Balance Rectification"),
            youtubeVideoUrl = "https://www.youtube.com/results?search_query=CA+Wallah+CA+Foundation+Accounting+Marathon",
            searchFallbackQuery = "CA Wallah CA Foundation Accounting Marathon",
            recommendedFor = "Commerce students targeting CA Foundation alongside Class 12",
            isBoardTopperFavorite = true
        )
    )

    fun getVideosForGrade(grade: ClassGrade): List<YouTubeVideoSuggestion> {
        val direct = allVideos.filter { it.grade == grade }
        if (direct.isNotEmpty()) return direct
        // If empty, return grade-appropriate fallbacks
        return when (grade) {
            ClassGrade.CLASS_11_COMMERCE -> allVideos.filter { it.grade == ClassGrade.CLASS_12_COMMERCE || it.grade == ClassGrade.CLASS_11_COMMERCE }
            ClassGrade.CLASS_11 -> allVideos.filter { it.grade == ClassGrade.CLASS_12 || it.grade == ClassGrade.CLASS_11 }
            else -> allVideos.filter { it.grade == ClassGrade.CLASS_10 }
        }
    }

    fun getVideosForChapter(chapterTitle: String, grade: ClassGrade): List<YouTubeVideoSuggestion> {
        val normalized = chapterTitle.lowercase()
        val directMatch = allVideos.filter {
            (it.grade == grade || it.grade.code == grade.code) &&
                    (it.chapterTitle.lowercase().contains(normalized) ||
                            normalized.contains(it.chapterTitle.lowercase()) ||
                            it.keyTopicsCovered.any { topic -> normalized.contains(topic.lowercase()) })
        }
        if (directMatch.isNotEmpty()) return directMatch

        // Fallback to matching same subject in that grade
        val gradeVideos = getVideosForGrade(grade)
        return gradeVideos.take(3)
    }

    fun filterVideos(
        grade: ClassGrade,
        subject: SubjectType?,
        category: VideoCategory,
        query: String
    ): List<YouTubeVideoSuggestion> {
        var list = getVideosForGrade(grade)

        if (subject != null) {
            list = list.filter { it.subject == subject }
        }

        if (category != VideoCategory.ALL) {
            list = list.filter { it.category == category }
        }

        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter {
                it.title.lowercase().contains(q) ||
                        it.channelName.lowercase().contains(q) ||
                        it.chapterTitle.lowercase().contains(q) ||
                        it.description.lowercase().contains(q) ||
                        it.keyTopicsCovered.any { topic -> topic.lowercase().contains(q) }
            }
        }

        return list
    }
}
