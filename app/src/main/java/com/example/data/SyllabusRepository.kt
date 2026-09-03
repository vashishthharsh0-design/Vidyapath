package com.example.data

import com.example.model.*

object SyllabusRepository {
    val chapters: List<ChapterItem> = listOf(
        // CLASS 10 SCIENCE - CHAPTER 1
        ChapterItem(
            id = "c10_sci_ch1",
            chapterNumber = 1,
            title = "Chemical Reactions & Equations",
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            marksWeightage = "4-6 Marks (1 MCQ + 1 Short/Case)",
            summary = "Fundamental chapter on balancing chemical equations, types of reactions (Combination, Decomposition, Displacement, Double Displacement, Redox), and daily life redox effects (Corrosion & Rancidity).",
            keyTopics = listOf(
                "Balancing chemical equations via Hit & Trial method",
                "Types of reactions: Combination (CaO + H2O), Decomposition (Thermal, Electrolytic, Photolytic)",
                "Displacement reactions & Reactivity Series application",
                "Double displacement & Precipitation (BaSO4 white ppt, PbI2 yellow ppt)",
                "Oxidation, Reduction, Oxidizing & Reducing agents",
                "Corrosion of Fe, Cu (Green patina), Ag (Black tarnish) & Rancidity prevention"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Slaked Lime & Whitewashing", "CaO (Quicklime) + H2O -> Ca(OH)2 (Slaked lime) + Heat. On walls, Ca(OH)2 reacts slowly with CO2 in air to form shiny layer of CaCO3 (Calcium carbonate) in 2-3 days.", isHighYield = true),
                NCERTCruxPoint(2, "Electrolysis of Water Ratio", "Volume of Hydrogen gas collected at cathode is twice (2:1) the volume of Oxygen gas collected at anode because H2O contains 2 hydrogen atoms per 1 oxygen atom.", isHighYield = true),
                NCERTCruxPoint(3, "Thermal Decomposition of Lead Nitrate", "2Pb(NO3)2 (s) -> 2PbO (s) [Yellow] + 4NO2 (g) [Brown fumes] + O2 (g). Frequently asked observation-based question.", isHighYield = true),
                NCERTCruxPoint(4, "Ferrous Sulphate Crystals Heating", "Green FeSO4.7H2O loses water to form white FeSO4, which on further heating gives reddish-brown Fe2O3 and choking sulfur gases (SO2 + SO3).", isHighYield = false),
                NCERTCruxPoint(5, "Precipitation Reactions", "Lead nitrate + Potassium iodide -> Lead iodide (PbI2) brilliant yellow precipitate + Potassium nitrate (KNO3).", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Combination (Quicklime)", "CaO (s) + H2O (l) -> Ca(OH)2 (aq) + Heat", "Exothermic combination reaction", "Slaked lime solution"),
                FormulaItem("Photolytic Decomposition", "2AgCl (s) --[Sunlight]--> 2Ag (s) + Cl2 (g)", "White AgCl turns grey; used in black & white photography", "Decomposition by light energy"),
                FormulaItem("Double Displacement", "Na2SO4 (aq) + BaCl2 (aq) -> BaSO4 (s)↓ + 2NaCl (aq)", "Barium sulphate forms insoluble white precipitate", "Precipitation reaction")
            ),
            pyqTrends = listOf(
                PYQTrend("Color Change Observations", "3-Mark Short", "2024, 2023, 2020", "Students often forget to write physical states like (s), (aq), (g) and precipitate arrows (↓)."),
                PYQTrend("Electrolysis of Water Cathode/Anode Gases", "2-Mark Question", "2023, 2022, 2019", "Confusing which gas is at Cathode (H2) vs Anode (O2). Trick: 'Cathode is Negative, attracts H+'."),
                PYQTrend("Identify Oxidized/Reduced Substances", "1-Mark MCQ", "Every Year", "Students often mistakenly pick the products instead of reactants as oxidizing/reducing agents.")
            ),
            topperTips = listOf(
                "Always write balanced chemical equations with state symbols (s, l, g, aq) to score 100% marks.",
                "Mention precipitate colors explicitly: BaSO4 (White), PbI2 (Yellow), Cu (Reddish-brown), Fe2O3 (Reddish-brown).",
                "For Redox questions, remember: Oxidizing agent is the substance that itself gets REDUCED."
            ),
            suggestedMethod = NoteMethodType.CORNELL
        ),

        // CLASS 10 SCIENCE - CHAPTER 6 (LIFE PROCESSES)
        ChapterItem(
            id = "c10_sci_ch6",
            chapterNumber = 6,
            title = "Life Processes",
            subject = SubjectType.BIOLOGY,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            marksWeightage = "8-10 Marks (Highest weightage in Biology)",
            summary = "Comprehensive study of Nutrition (Autotrophic/Heterotrophic, Digestive enzymes), Respiration (Aerobic vs Anaerobic, ATP), Transportation (Human Heart, Double circulation, Xylem/Phloem), and Excretion (Nephron structure, Dialysis).",
            keyTopics = listOf(
                "Photosynthesis events & Stomatal opening/closing mechanism (Guard cells)",
                "Human Alimentary Canal & Digestive enzymes (Pepsin, Trypsin, Lipase, Bile juice)",
                "Respiration pathways (Glycolysis, Aerobic in Mitochondria, Anaerobic in Yeast/Muscles)",
                "Human Heart anatomy, Double circulation & Blood pressure",
                "Transport in plants: Transpiration pull in Xylem vs Translocation in Phloem",
                "Structure and function of Nephron (Bowman's capsule, Glomerular filtration, Reabsorption)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Photosynthesis 3 Core Events", "1) Absorption of light energy by chlorophyll, 2) Conversion of light into chemical energy & splitting of water molecules into H2 & O2, 3) Reduction of CO2 to carbohydrates.", isHighYield = true),
                NCERTCruxPoint(2, "Small Intestine - Site of Complete Digestion", "Receives bile juice from liver (emulsifies fats, makes medium alkaline) and pancreatic juice (Amylase for starch, Trypsin for proteins, Lipase for emulsified fats). Villi increase surface area for absorption.", isHighYield = true),
                NCERTCruxPoint(3, "Double Circulation Advantage", "Separates oxygenated blood (Left side) from deoxygenated blood (Right side), preventing mixing and supplying high oxygen for warm-blooded human body temperature regulation.", isHighYield = true),
                NCERTCruxPoint(4, "Nephron Reabsorption", "Initial filtrate contains glucose, amino acids, salts & water. Useful substances selectively reabsorbed in tubular part depending on body hydration level.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Photosynthesis Net Equation", "6CO2 + 12H2O --[Chlorophyll + Sunlight]--> C6H12O6 + 6O2 + 6H2O", "Light energy conversion equation", "Produces glucose and oxygen"),
                FormulaItem("Normal Blood Pressure", "Systolic: 120 mm of Hg / Diastolic: 80 mm of Hg", "Measured using Sphygmomanometer", "Ventricle contraction vs relaxation")
            ),
            pyqTrends = listOf(
                PYQTrend("Breakdown of Glucose 3 Pathways", "3-Mark Flowchart", "2024, 2023, 2021, 2019", "Missing ATP numbers or confusing yeast anaerobic (Ethanol) with muscle cell (Lactic acid)."),
                PYQTrend("Nephron Diagram & Labeling", "5-Mark Long Answer", "2023, 2020, 2018", "Diagram must label Glomerulus, Bowman's capsule, Collecting duct, and Capillaries clearly."),
                PYQTrend("Digestive Enzymes Table", "3-Mark Table", "2022, 2020", "Confusing Pepsin (Acidic/Stomach) with Trypsin (Alkaline/Pancreas).")
            ),
            topperTips = listOf(
                "Draw neat, labeled pencil diagrams with clean single lines. Arrows should point directly to parts.",
                "Use a Flowchart method for Glucose breakdown to memorize the 3 distinct pathways effortlessly.",
                "Remember enzyme trigger: Pepsin needs HCl (Stomach); Trypsin needs Bile's alkaline environment (Intestine)."
            ),
            suggestedMethod = NoteMethodType.FLOW_CHART
        ),

        // CLASS 10 SCIENCE - ELECTRICITY
        ChapterItem(
            id = "c10_sci_ch12",
            chapterNumber = 12,
            title = "Electricity",
            subject = SubjectType.PHYSICS,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            marksWeightage = "7-9 Marks (Numericals + Theory)",
            summary = "Current, Potential Difference, Ohm's Law, Factors affecting Resistance, Series and Parallel combinations, Heating effect of electric current (Joule's Law), and Commercial unit of electric energy (kWh).",
            keyTopics = listOf(
                "Electric current (I = Q/t) & Potential Difference (V = W/Q)",
                "Ohm's Law (V = IR) and V-I graph slope = Resistance",
                "Factors affecting Resistance: R = ρ (l / A), Resistivity properties",
                "Resistors in Series (Req = R1 + R2 + ...) vs Parallel (1/Req = 1/R1 + 1/R2 + ...)",
                "Joule's Law of Heating: H = I²Rt",
                "Electric Power: P = VI = I²R = V²/R and Commercial Energy 1 kWh = 3.6 × 10⁶ J"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Ohm's Law Verification", "Current flowing through a conductor is directly proportional to potential difference across its ends, provided temperature remains constant (V ∝ I => V = IR).", isHighYield = true),
                NCERTCruxPoint(2, "Wire Stretching Trick", "If a wire is stretched to double its length (l' = 2l), its cross-sectional area halves (A' = A/2) to conserve volume. New resistance becomes R' = 4R (n² times original!).", isHighYield = true),
                NCERTCruxPoint(3, "Series vs Parallel in Domestic Wiring", "Parallel circuit is used in homes because: 1) Every device gets full 220V voltage, 2) Total resistance decreases, 3) If one appliance fails, others continue working with independent switches.", isHighYield = true),
                NCERTCruxPoint(4, "Resistivity vs Resistance", "Resistivity (ρ) depends ONLY on material and temperature—NOT on length or thickness of the wire. Units: Ω·m.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Electric Current", "I = Q / t = (n · e) / t", "Q in Coulombs, t in seconds, e = 1.6 × 10⁻¹⁹ C", "Unit: Ampere (A)"),
                FormulaItem("Potential Difference", "V = W / Q", "Work done per unit charge", "Unit: Volt (V)"),
                FormulaItem("Resistance Formula", "R = ρ · (l / A)", "l = length, A = cross-sectional area (π r²)", "Unit: Ohm (Ω)"),
                FormulaItem("Joule's Heating Law", "H = I² · R · t", "Heat generated by electric current", "Unit: Joules (J)"),
                FormulaItem("Commercial Energy Unit", "1 kWh = 1 unit = 3.6 × 10⁶ Joules", "E (kWh) = [Power (Watts) × Hours] / 1000", "Board Bill Calculation")
            ),
            pyqTrends = listOf(
                PYQTrend("Equivalent Resistance Ladder Numericals", "3-5 Marks", "2024, 2023, 2022", "Forgetting to invert the answer after computing 1/Req in parallel circuits."),
                PYQTrend("Stretching / Doubling Wire Resistance", "2-3 Marks", "2023, 2021, 2020", "Forgetting that stretching length also shrinks area (R becomes n² R)."),
                PYQTrend("Electricity Bill Cost Numericals", "3-Mark Numerical", "2024, 2020", "Forgetting to convert Watts to Kilowatts or days in month.")
            ),
            topperTips = listOf(
                "Use the Boxing method to keep all 5 power and resistance formulas together on one page.",
                "In numericals: Always write Given data with units, state the Formula first, show substitution, and box the Final Answer with Unit.",
                "Parallel trick: When two identical resistors R are in parallel, Req = R/2. When 'n' identical resistors are in parallel, Req = R/n."
            ),
            suggestedMethod = NoteMethodType.BOXING
        ),

        // CLASS 10 MATHS - TRIGONOMETRY
        ChapterItem(
            id = "c10_math_ch8",
            chapterNumber = 8,
            title = "Introduction to Trigonometry",
            subject = SubjectType.MATHEMATICS,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            marksWeightage = "6-8 Marks (Identities Proof + Value calculation)",
            summary = "Trigonometric ratios of acute angles in a right triangle, relationship between ratios, specific standard values (0°, 30°, 45°, 60°, 90°), and trigonometric identities proofs.",
            keyTopics = listOf(
                "Trigonometric Ratios (sin, cos, tan, cosec, sec, cot) using right-angled triangle",
                "Trig Table values: 0°, 30°, 45°, 60°, 90° with easy finger tricks",
                "Reciprocal and quotient identities: tan θ = sin θ / cos θ, cot θ = cos θ / sin θ",
                "Pythagorean identities: sin²θ + cos²θ = 1, 1 + tan²θ = sec²θ, 1 + cot²θ = cosec²θ",
                "Proving trigonometric identities by converting all terms into sin θ and cos θ"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "The PBP/HHB Golden Rule", "Pandit Badri Prasad / Har Har Bole: sin = P/H, cos = B/H, tan = P/B. Hypotenuse is always opposite to 90°, Perpendicular is opposite to angle θ.", isHighYield = true),
                NCERTCruxPoint(2, "Master Strategy for Proving Identities", "Convert complicated expressions (tan, sec, cosec, cot) completely into terms of sin θ and cos θ. Take LCM, apply algebraic identities like (a² - b²) or (a³ ± b³), and use sin²θ + cos²θ = 1.", isHighYield = true),
                NCERTCruxPoint(3, "Sec - Tan Conjugate Trick", "Since sec²θ - tan²θ = 1 => (sec θ - tan θ)(sec θ + tan θ) = 1. Therefore, (sec θ + tan θ) is the reciprocal of (sec θ - tan θ). Very common in 3-mark questions!", isHighYield = true),
                NCERTCruxPoint(4, "Angle Complementary awareness", "sin (90° - θ) = cos θ, tan (90° - θ) = cot θ, sec (90° - θ) = cosec θ.", isHighYield = false)
            ),
            formulas = listOf(
                FormulaItem("Identity 1", "sin²θ + cos²θ = 1", "sin²θ = 1 - cos²θ  |  cos²θ = 1 - sin²θ", "Fundamental trig identity"),
                FormulaItem("Identity 2", "1 + tan²θ = sec²θ", "sec²θ - tan²θ = 1  |  tan²θ = sec²θ - 1", "Valid for 0° ≤ θ < 90°"),
                FormulaItem("Identity 3", "1 + cot²θ = cosec²θ", "cosec²θ - cot²θ = 1  |  cot²θ = cosec²θ - 1", "Valid for 0° < θ ≤ 90°")
            ),
            pyqTrends = listOf(
                PYQTrend("Proof of (sin θ - 2sin³θ) / (2cos³θ - cos θ) = tan θ", "3-4 Marks Proof", "2024, 2022, 2020", "Factor out sin θ in numerator and cos θ in denominator; replace 1 with (sin²θ + cos²θ)."),
                PYQTrend("If sec θ + tan θ = p, find sin θ", "3-Mark Question", "2023, 2019", "Use (sec θ - tan θ) = 1/p. Add and subtract to find sec θ and tan θ, then divide to get sin θ = (p² - 1)/(p² + 1).")
            ),
            topperTips = listOf(
                "Write the trig values table in the first 2 minutes of the exam on the rough page using the √n/4 finger trick.",
                "In proof questions, write LHS and RHS clearly. Never transfer terms across the equals sign in proof questions unless explicitly showing LHS = RHS.",
                "Rationalize the denominator whenever you see terms like 1 / (1 + sin θ) or √( (1 + cos θ) / (1 - cos θ) )."
            ),
            suggestedMethod = NoteMethodType.BOXING
        ),

        // CLASS 10 SOCIAL SCIENCE - NATIONALISM IN INDIA
        ChapterItem(
            id = "c10_sst_hist_ch2",
            chapterNumber = 2,
            title = "Nationalism in India",
            subject = SubjectType.HISTORY,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            marksWeightage = "6-8 Marks (Map Item + 5-Mark Essay)",
            summary = "First World War impact, Satyagraha concept, Rowlatt Act, Jallianwala Bagh, Non-Cooperation Movement (Towns, Countryside, Plantations), Civil Disobedience Movement (Salt March), and Sense of Collective Belonging.",
            keyTopics = listOf(
                "First World War impact & Gandhi's Idea of Satyagraha (Champaran 1917, Kheda 1917, Ahmedabad 1918)",
                "Rowlatt Act (1919), Hartal, and Jallianwala Bagh Massacre (13 April 1919)",
                "Non-Cooperation Movement: Diverse social strands in Cities, Awadh Peasants (Baba Ramchandra), Gudem Hills Tribals (Alluri Sitaram Raju), Inland Emigration Act in Assam",
                "Chauri Chaura incident (1922) and Swaraj Party (C.R. Das & Motilal Nehru)",
                "Simon Commission (1928), Lahore Congress (1929 - Purna Swaraj) & Salt March (12 March 1930 from Sabarmati to Dandi)",
                "Civil Disobedience vs Non-Cooperation differences",
                "Sense of Collective Belonging: Bharat Mata image (Bankim Chandra & Abanindranath Tagore), Vande Mataram, Tricolour flag history, Folklore revival"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Early Satyagrahas Chronology", "1917 Champaran (Bihar) against Indigo planters, 1917 Kheda (Gujarat) for crop failure tax relief, 1918 Ahmedabad for Cotton Mill workers wage hike.", isHighYield = true),
                NCERTCruxPoint(2, "Non-Cooperation vs Civil Disobedience", "In Non-Cooperation (1920-22), Indians only REFUSED cooperation with British administration. In Civil Disobedience (1930-34), people BROKE colonial laws (e.g. Salt law, forest laws, tax refusal).", isHighYield = true),
                NCERTCruxPoint(3, "The Poona Pact (Sept 1932)", "Between Gandhiji and Dr. B.R. Ambedkar: Gave Depressed Classes (Scheduled Castes) reserved seats in provincial & central legislative councils, voted by general electorate (abandoning separate electorates).", isHighYield = true),
                NCERTCruxPoint(4, "Map Locations (Mandatory)", "Congress Sessions: Calcutta (Sept 1920), Nagpur (Dec 1920), Madras (1927). Satyagraha centers: Champaran, Kheda, Ahmedabad, Amritsar (Jallianwala), Chauri Chaura, Dandi.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Key Historical Date Milestones", "1919 (Rowlatt/Jallianwala) -> 1920 (NCM launch) -> 1922 (Chauri Chaura) -> 1929 (Lahore Purna Swaraj) -> 1930 (Dandi Salt March)", "Chronological memory chain", "Class 10 Board Map & Timeline")
            ),
            pyqTrends = listOf(
                PYQTrend("How different social groups participated in Civil Disobedience", "5-Mark Essay", "2024, 2023, 2020", "Must structure answer into: Rich Peasants (Patidars/Jats), Poor Peasants, Business Class (FICCI/Purshottamdas/GD Birla), Women, and Working Class."),
                PYQTrend("How cultural processes created sense of collective belonging", "5-Mark Long", "2023, 2022, 2019", "Cover 5 pillars: 1. Bharat Mata icon, 2. Vande Mataram hymn, 3. Folklore & Folk songs, 4. National Tricolor flag designs, 5. Reinterpretation of Indian history.")
            ),
            topperTips = listOf(
                "Use the QEC method to write 5 numbered headings with underline for every 5-mark history answer.",
                "Always write accurate historical years in bold (e.g. 13 April 1919, 12 March 1930, Sept 1932 Poona Pact).",
                "Practice the India outline map once a week—scoring full 3 marks on history map takes only 60 seconds."
            ),
            suggestedMethod = NoteMethodType.QEC
        ),

        // CLASS 11/12 & COMPETITIVE (JEE/NEET) - ORGANIC CHEMISTRY & PERIODICITY
        ChapterItem(
            id = "c11_chem_ch3",
            chapterNumber = 3,
            title = "Periodic Table & Periodic Properties",
            subject = SubjectType.CHEMISTRY,
            grade = ClassGrade.CLASS_11,
            board = BoardType.COMPETITIVE,
            marksWeightage = "6-8 Marks (JEE/NEET Hotspot)",
            summary = "Modern Periodic Law, Electronic configuration, Periodic trends in Atomic Radii, Ionization Enthalpy, Electron Gain Enthalpy, Electronegativity, Diagonal relationships, and anomalous properties of 2nd-period elements.",
            keyTopics = listOf(
                "Modern Periodic Law & IUPAC nomenclature for elements with Z > 100",
                "Atomic & Ionic Radii trends (Isoelectronic species radii comparison)",
                "Ionization Enthalpy (ΔiH): Factors, Period & Group trends, Exceptions (N > O, Be > B)",
                "Electron Gain Enthalpy (ΔegH): Exceptions (Cl > F, S > O due to small size & interelectronic repulsion in 2p orbital)",
                "Electronegativity scales (Pauling scale: F = 4.0, O = 3.5, N = 3.0, Cl = 3.0)",
                "Diagonal relationship (Li-Mg, Be-Al, B-Si) due to similar polarizing power (Charge/Radius ratio)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Ionization Enthalpy Exception 1 (N vs O)", "Nitrogen (1s² 2s² 2p³) has a stable half-filled 2p subshell, so its first Ionization Enthalpy is HIGHER than Oxygen (1s² 2s² 2p⁴), which easily loses 1 electron to gain half-filled stability.", isHighYield = true),
                NCERTCruxPoint(2, "Electron Gain Enthalpy Exception (Cl vs F)", "Chlorine has MORE negative electron gain enthalpy than Fluorine! Because Fluorine's 2p orbital is very compact, incoming electron experiences strong interelectronic repulsions.", isHighYield = true),
                NCERTCruxPoint(3, "Isoelectronic Species Radius Rule", "Among isoelectronic ions (e.g. N³⁻, O²⁻, F⁻, Na⁺, Mg²⁺, Al³⁺), Radius DECREASES as positive nuclear charge (number of protons) increases: Al³⁺ < Mg²⁺ < Na⁺ < F⁻ < O²⁻ < N³⁻.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Effective Nuclear Charge", "Z_eff = Z - σ (Slater's Screening Constant)", "Nuclear attraction experienced by valence electron", "Controls atomic contraction across period"),
                FormulaItem("Ionic Radius in Isoelectronic Series", "Ionic Radius ∝ 1 / Z (Atomic Number)", "More protons = Smaller radius for same electrons", "Key JEE/NEET shortcut formula")
            ),
            pyqTrends = listOf(
                PYQTrend("Order of Ionization Enthalpy / Electron Affinity", "JEE/NEET MCQ", "2024, 2023, 2022", "Forgetting the Cl > F and N > O exceptions."),
                PYQTrend("Isoelectronic ionic radius arrangement", "NEET Direct 4-Marks", "2023, 2021", "Students confuse positive charge magnitude with size.")
            ),
            topperTips = listOf(
                "Use the Mnemonic method to memorize groups and the reactivity series in seconds.",
                "Remember the 2 main periodic exceptions: IE (N > O, Be > B) and EA (Cl > F, S > O).",
                "Always check if ions are isoelectronic first before comparing sizes in competitive questions."
            ),
            suggestedMethod = NoteMethodType.MNEMONICS
        ),

        // ==========================================
        // COMMERCE CLASS 12 - ACCOUNTANCY CHAPTER 1
        // ==========================================
        ChapterItem(
            id = "c12_acc_ch1",
            chapterNumber = 1,
            title = "Partnership Fundamentals & Goodwill",
            subject = SubjectType.ACCOUNTANCY,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "12-16 Marks (1 MCQ + 3M Numerical + 6M Comprehensive)",
            summary = "Foundational chapter covering Profit and Loss Appropriation Account, Interest on Drawings (Average Period & Product Method), Past Adjustments (Single Adjustment Entry), Guarantee of Profits, and Methods of Goodwill Valuation (Average, Super Profit, and Capitalisation).",
            keyTopics = listOf(
                "Partnership Deed provisions in absence of agreement (Profit 1:1, Loan Int 6% p.a., No salary/int on capital)",
                "P&L Appropriation Account format & distinction from Profit & Loss Account",
                "Interest on Drawings: Monthly (Beg 6.5, Mid 6, End 5.5), Quarterly (Beg 7.5, Mid 6, End 4.5)",
                "Past Adjustments through Single Journal Entry via Statement Showing Adjustments",
                "Guarantee of Minimum Profit by firm, partners, or personal partner recovery",
                "Goodwill Valuation: Capitalisation of Super Profits = (Super Profit / NRR) × 100"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Charge vs Appropriation Rule", "Rent paid to a partner and Interest on Partner's Loan are CHARGES against profits (debited to P&L A/c). They MUST be paid even in case of loss, unlike Interest on Capital which is an appropriation.", isHighYield = true),
                NCERTCruxPoint(2, "Interest on Drawings Average Period Formula", "Average Period = [Months left after 1st drawing + Months left after last drawing] / 2. For beginning of month: (12 + 1)/2 = 6.5 months. End of month: (11 + 0)/2 = 5.5 months.", isHighYield = true),
                NCERTCruxPoint(3, "Past Adjustment Table Method", "Prepare table showing Partner Dr/Cr. Reverse wrong distribution (Credit capital), record correct entitlement (Debit capital), and pass single adjusting entry with net difference.", isHighYield = true),
                NCERTCruxPoint(4, "Super Profit Calculation Formula", "Super Profit = Actual Average Profit - Normal Profit. Where Normal Profit = Capital Employed × (Normal Rate of Return / 100).", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Interest on Drawings", "Total Drawings × (Rate/100) × (Avg Period / 12)", "Monthly (Beg: 6.5, Mid: 6, End: 5.5) | Quarterly (Beg: 7.5, Mid: 6, End: 4.5)", "Time in months"),
                FormulaItem("Super Profit Method Goodwill", "Goodwill = Super Profit × Number of Years' Purchase", "Super Profit = Actual Profit - (Capital Employed × NRR%)", "Valuation of Intangible"),
                FormulaItem("Capitalisation of Average Profit", "Goodwill = (Average Profit / NRR × 100) - Capital Employed", "Compares capitalized value with net tangible assets", "Intrinsic value method")
            ),
            pyqTrends = listOf(
                PYQTrend("Past Adjustment Entry with Interest on Capital Omitted", "4-Mark Question", "2024, 2023, 2022, 2020", "Students omit narration or reverse Dr/Cr in the final adjustment entry."),
                PYQTrend("Interest on Partner's Loan in Absence of Deed", "1-Mark MCQ", "Every Year", "Confusing 6% p.a. charge on loan with 0% interest on capital."),
                PYQTrend("Guarantee of Profit with Deficiency Calculation", "3-Mark Short", "2023, 2021, 2019", "Calculating deficiency on wrong partner or incorrect ratio.")
            ),
            topperTips = listOf(
                "Always write narrations for journal entries: 'Being adjustment entry passed to rectify omission...'",
                "Highlight working notes with step-by-step numbers (#WN 1, #WN 2) as CBSE awards up to 40% marks for Working Notes.",
                "Draw proper ledger accounts with Date, Particulars, J.F., Amount (₹) columns."
            ),
            suggestedMethod = NoteMethodType.BOXING
        ),

        // COMMERCE CLASS 12 - ACCOUNTANCY CHAPTER 2
        ChapterItem(
            id = "c12_acc_ch2",
            chapterNumber = 2,
            title = "Issue, Forfeiture & Reissue of Shares",
            subject = SubjectType.ACCOUNTANCY,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "18-20 Marks (Highest Weightage in Company Accounts)",
            summary = "Core company accounting module covering Pro-Rata Allotment table, Calls in Arrears, Forfeiture of shares issued at Par vs Premium, Maximum permissible discount on Reissue, and Transfer of net gain to Capital Reserve Account.",
            keyTopics = listOf(
                "Pro-Rata Allotment calculation table (Category, Applied, Allotted, App Recd, Adjusted, Refund)",
                "Forfeiture when Securities Premium is UNPAID (Securities Premium A/c debited)",
                "Forfeiture when Securities Premium is ALREADY PAID (Securities Premium A/c NOT touched)",
                "Reissue of Forfeited Shares at Discount & Maximum Discount rule",
                "Capital Reserve computation on partial reissue of forfeited shares",
                "Balance Sheet disclosure of Share Capital (Authorised, Issued, Subscribed & Fully Paid)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Pro-Rata Allotment Default Mechanism", "When a shareholder fails to pay allotment, deduct excess application money adjusted towards allotment BEFORE calculating calls in arrears!", isHighYield = true),
                NCERTCruxPoint(2, "Section 52(2) Securities Premium Golden Rule", "If Securities Premium has been received, it CANNOT be cancelled or debited upon forfeiture. If it remains unpaid, debit Securities Premium Reserve A/c.", isHighYield = true),
                NCERTCruxPoint(3, "Maximum Reissue Discount", "The maximum discount allowed on reissue cannot exceed the amount already credited to Share Forfeiture Account on those specific reissued shares.", isHighYield = true),
                NCERTCruxPoint(4, "Capital Reserve Partial Reissue Formula", "Capital Reserve = [(Forfeited Amount on Total Shares / Total Shares) × Reissued Shares] - Discount Allowed on Reissue.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Pro-Rata Shares Applied", "Shares Applied = Shares Allotted × (Total Applied in Category / Total Allotted)", "Converts allotted count to initial application count", "Unit: Number of shares"),
                FormulaItem("Net Allotment Unpaid", "Allotment Due (Allotted × Rate) - Excess Application Money Adjusted", "Key formula to calculate Calls in Arrears on Allotment", "Unit: ₹ Rupees"),
                FormulaItem("Capital Reserve Net Gain", "[(Forfeited Amt / Forfeited Shares) × Reissued Shares] - Discount on Reissue", "Net gain on reissued shares transferred to Capital Reserve", "Unit: ₹ Rupees")
            ),
            pyqTrends = listOf(
                PYQTrend("6-Mark Pro-Rata Comprehensive Allotment & Forfeiture", "6-Mark Mandatory", "Every Single Year (2024, 2023, 2022, 2020)", "Forgetting to proportion application excess to capital before premium, leading to wrong forfeiture debit."),
                PYQTrend("Capital Reserve on Partial Reissue", "3-Mark Short", "2024, 2023, 2021", "Subtracting reissue discount directly from total forfeited money without adjusting for un-reissued shares.")
            ),
            topperTips = listOf(
                "Prepare the standard 6-column Pro-Rata statement before writing journal entries.",
                "Share Capital is always credited with CALLED-UP FACE VALUE, never with premium or market value.",
                "Reissue discount is debited to 'Share Forfeiture A/c', NOT 'Discount on Issue of Shares A/c'."
            ),
            suggestedMethod = NoteMethodType.FLOW_CHART
        ),

        // COMMERCE CLASS 12 - ACCOUNTANCY CHAPTER 3
        ChapterItem(
            id = "c12_acc_ch3",
            chapterNumber = 3,
            title = "Cash Flow Statement (AS-3 Revised)",
            subject = SubjectType.ACCOUNTANCY,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "8 Marks (1 Long 6M Question + 2 MCQs)",
            summary = "Comprehensive standard for preparing Cash Flow Statements under AS-3 (Revised) classified into Operating, Investing, and Financing Activities, with adjustments for Provision for Tax, Proposed Dividend, and Sale of Fixed Assets.",
            keyTopics = listOf(
                "Classification of activities: Operating (Core business), Investing (Fixed assets & investments), Financing (Capital & Borrowings)",
                "Non-Cash & Non-Operating items (Depreciation, Goodwill written off, Gain/Loss on sale)",
                "Working capital adjustments: Current Assets (Inverse relation: Decrease is +), Current Liabilities (Direct relation: Increase is +)",
                "Ledger accounts: Machinery Account & Accumulated Depreciation Account for hidden purchases/sales",
                "Treatment of Provision for Tax: Tax Made (Added to Net Profit) vs Tax Paid (Deducted from Operating Cash Flow)",
                "Treatment of Proposed Dividend as per AS-4 (Previous year dividend is paid and treated in current year)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Working Capital Golden Inversion Rule", "Increase in Current Assets = Outflow of cash (-). Decrease in Current Assets = Inflow of cash (+). Increase in Current Liabilities = Inflow of cash (+).", isHighYield = true),
                NCERTCruxPoint(2, "Accumulated Depreciation Ledger Trick", "When Accumulated Depreciation A/c is given: Credit Machinery with original cost of asset sold, Debit Accumulated Depreciation with accumulated dep on sold asset, and debit Bank with sale value.", isHighYield = true),
                NCERTCruxPoint(3, "Proposed Dividend AS-4 Rule", "Proposed Dividend of Previous Year is added back to Net Profit (Operating) and subtracted as Outflow under Financing Activities. Current year proposed dividend is completely ignored!", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Net Profit before Tax & Extra", "Net Profit + Provision for Tax Made + Proposed Dividend (Prev Year) + Transfer to Reserve", "Starting point of Operating Activities", "Unit: ₹ Rupees"),
                FormulaItem("Cash & Cash Equivalents Verification", "Operating Cash + Investing Cash + Financing Cash + Opening Cash = Closing Cash", "Internal check verifying 100% answer accuracy", "Must match Balance Sheet")
            ),
            pyqTrends = listOf(
                PYQTrend("Full Cash Flow with Machinery & Provision for Depreciation", "6-Mark Long", "2024, 2023, 2022, 2020", "Confusing original cost of machine sold with its written-down value."),
                PYQTrend("Treatment of Bank Overdraft / Cash Credit", "1-Mark MCQ", "2023, 2022, 2020", "Thinking Bank Overdraft is working capital; it is a Short-term Borrowing (Financing Activity)!")
            ),
            topperTips = listOf(
                "Always check if your Net Increase in Cash + Opening Cash equals Closing Cash in the question. If it matches, you scored 6/6!",
                "Interest paid on debentures is ALWAYS Financing outflow; Interest received on investments is ALWAYS Investing inflow."
            ),
            suggestedMethod = NoteMethodType.CORNELL
        ),

        // ==========================================
        // COMMERCE CLASS 12 - BUSINESS STUDIES (BST)
        // ==========================================
        ChapterItem(
            id = "c12_bst_ch1",
            chapterNumber = 1,
            title = "Principles of Management (Fayol & Taylor)",
            subject = SubjectType.BUSINESS_STUDIES,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "7-9 Marks (MCQs + 4M Case Study)",
            summary = "Comparative study of Henri Fayol's 14 Administrative Principles and F.W. Taylor's Scientific Management principles & techniques (Functional Foremanship, Time/Motion study, Differential Piece Wage).",
            keyTopics = listOf(
                "Fayol's 14 Principles: Division of Work, Authority & Responsibility, Discipline, Unity of Command, Unity of Direction",
                "Scalar Chain and 'Gang Plank' exception for emergency cross-departmental communication",
                "Taylor's Scientific Principles: Science not Rule of Thumb, Harmony not Discord (Mental Revolution), Cooperation not Individualism",
                "Taylor's Techniques: Functional Foremanship (8 specialists: 4 Planning, 4 Production/Execution)",
                "Work Study: Method Study (Best way), Motion Study (Eliminating wasteful movements), Time Study (Standard time), Fatigue Study (Rest intervals)",
                "Differential Piece Wage System to reward efficient workers over inefficient workers"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Unity of Command vs Unity of Direction", "Unity of Command: One subordinate should receive orders from ONE boss only (prevents dual subordination). Unity of Direction: One head and one plan for activities having the same objective (prevents overlapping).", isHighYield = true),
                NCERTCruxPoint(2, "Scalar Chain & Gang Plank", "Scalar chain is the formal line of authority from top to bottom. Gang Plank is a direct contact route between two employees of the same level during emergencies to prevent administrative delays.", isHighYield = true),
                NCERTCruxPoint(3, "Mental Revolution in Taylor's Harmony", "Mental Revolution implies a total transformation in the attitude of workers and management towards each other from conflict of interest to mutual cooperation.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Standard Output & Time", "Standard Time = Total Working Time - Rest Intervals", "Used in Time Study to determine worker requirement", "Taylor Work Study"),
                FormulaItem("Functional Foremanship Breakdown", "Planning (Instruction, Route, Time/Cost, Disciplinarian) | Execution (Speed, Gang, Repair, Inspector)", "8 distinct foremen supervising one worker", "Separation of planning & doing")
            ),
            pyqTrends = listOf(
                PYQTrend("Identify Principle from Case Study lines", "4-Mark Case Study", "2024, 2023, 2022, 2020", "Writing the principle without quoting the specific sentences from the paragraph in inverted commas."),
                PYQTrend("Distinguish Fayol vs Taylor", "3-Mark Comparison", "2023, 2021", "Basis of comparison must include: Perspective (Top vs Shop floor), Focus (Administration vs Worker output), Unity of Command (Strict vs Violated in Functional Foremanship).")
            ),
            topperTips = listOf(
                "Always quote the exact line from the question paper inside quotation marks: \"...quote...\"",
                "Underline the principle name with a pencil and write 2 precise NCERT sentences explaining it.",
                "Use the mnemonic 'DAD U SEE USSR? O I SEE!' to recall all 14 Fayol principles effortlessly."
            ),
            suggestedMethod = NoteMethodType.QEC
        ),

        // COMMERCE CLASS 12 - BUSINESS STUDIES CHAPTER 2
        ChapterItem(
            id = "c12_bst_ch2",
            chapterNumber = 2,
            title = "Financial Management & Capital Structure",
            subject = SubjectType.BUSINESS_STUDIES,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "9-10 Marks (3M Numerical + 6M Factors)",
            summary = "In-depth analysis of Financial Decisions (Investment, Financing, Dividend), Capital Structure optimization, Trading on Equity (Financial Leverage), and factors determining Fixed & Working Capital requirements.",
            keyTopics = listOf(
                "Three Financial Decisions: Investment (Capital Budgeting), Financing (Debt-Equity mix), Dividend (Retained earnings vs Distribution)",
                "Objective of Financial Management: Wealth Maximisation of Equity Shareholders (Market price of shares)",
                "Trading on Equity (Financial Leverage) and EPS calculation under varying Debt-Equity ratios",
                "Condition for favorable leverage: Return on Investment (ROI) > Cost of Borrowed Debt",
                "Factors affecting Capital Structure: Cash flow position, Interest Coverage Ratio (ICR), Debt Service Coverage Ratio (DSCR), Cost of Debt, Tax Rate",
                "Factors affecting Working Capital: Nature of business, scale, business cycle, credit policy, lead time"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Trading on Equity Core Principle", "Employing cheaper debt in capital structure magnifies Earnings Per Share (EPS) because interest on debt is a tax-deductible expense, leaving more earnings for equity holders WHEN ROI > Cost of Debt.", isHighYield = true),
                NCERTCruxPoint(2, "Financial Risk Definition", "Debt increases the financial risk of insolvency because interest and principal repayment are mandatory legal obligations irrespective of whether the firm earns profits.", isHighYield = true),
                NCERTCruxPoint(3, "Tax Shield Benefit", "Interest is deducted from EBIT before tax calculation, creating a tax shield: Effective Cost of Debt = Interest Rate × (1 - Tax Rate).", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Return on Investment (ROI)", "ROI = (EBIT / Total Capital Employed) × 100", "Must be greater than interest rate for favorable leverage", "Percentage (%)"),
                FormulaItem("Earnings Per Share (EPS)", "EPS = (EAT - Preference Dividend) / Number of Equity Shares", "Primary metric measuring shareholder wealth", "₹ per share"),
                FormulaItem("Interest Coverage Ratio (ICR)", "ICR = EBIT / Interest", "Higher ratio indicates lower risk of default on interest", "Times (e.g. 5 times)")
            ),
            pyqTrends = listOf(
                PYQTrend("EBIT-EPS Numerical with 3 Capital Structure Plans", "4-Mark Numerical", "2024, 2023, 2021", "Students calculate tax without subtracting interest first from EBIT."),
                PYQTrend("Factors affecting Dividend Decision", "4-Mark Question", "2023, 2020", "Forgetting to mention Legal constraints and Contractual constraints as separate points.")
            ),
            topperTips = listOf(
                "In numerical questions, always construct a comparative EBIT-EPS table for Plan I (All Equity), Plan II (Equity + Debt), and Plan III.",
                "Conclude with: 'Since EPS increases from ₹1.50 to ₹2.10, the company should employ debt in its capital structure (Trading on Equity).'"
            ),
            suggestedMethod = NoteMethodType.CORNELL
        ),

        // COMMERCE CLASS 12 - BUSINESS STUDIES CHAPTER 3
        ChapterItem(
            id = "c12_bst_ch3",
            chapterNumber = 3,
            title = "Marketing Management & The 4 Ps",
            subject = SubjectType.BUSINESS_STUDIES,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "10-12 Marks (High Weightage in Part B)",
            summary = "Exploration of Marketing Philosophies, Functions of Marketing, Marketing Mix (Product, Price, Place, Promotion), Branding, Packaging (Primary, Secondary, Transportation), and Promotion Mix tools.",
            keyTopics = listOf(
                "5 Marketing Philosophies: Production, Product, Selling, Marketing, Societal Marketing Concept",
                "Product Mix: Branding (Brand name, Brand mark, Trademark), Packaging, Labelling",
                "3 Levels of Packaging: Primary package, Secondary package, Transportation packaging",
                "Pricing Mix & Factors affecting price determination (Product cost, utility, government regulations, competitor pricing)",
                "Place Mix: Channels of distribution (Zero level, One level, Two level, Three level)",
                "Promotion Mix: Advertising (Impersonal), Personal Selling (Personal contact), Sales Promotion (Short-term discounts), Public Relations (Image building)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Marketing Concept vs Selling Concept", "Selling focuses on existing product and transfer of ownership via aggressive push. Marketing starts with customer needs and delivers customer satisfaction at a profit.", isHighYield = true),
                NCERTCruxPoint(2, "Levels of Packaging Real Examples", "Primary: Toothpaste tube / medicine blister pack. Secondary: Cardboard box holding the tube. Transportation: Corrugated cardboard carton holding 100 boxes for shipping.", isHighYield = true),
                NCERTCruxPoint(3, "Public Relations Importance", "Public Relations builds a positive corporate image among stakeholders and handles unfavorable rumors or crises smoothly through press releases and community programs.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Marketing Mix 4 Ps", "Product + Price + Place + Promotion", "Controllable variables combined to achieve marketing targets", "Framework"),
                FormulaItem("Sales Promotion Tools", "Rebate, Discount, Refunds, Product Combinations, Quantity Gifts, Instant Draws, Contests", "Short-term consumer incentives", "Tactical promotional tools")
            ),
            pyqTrends = listOf(
                PYQTrend("Identify Packaging Level from Scenario", "3-Mark Case Study", "2024, 2023, 2022", "Confusing Secondary packaging with Transportation packaging."),
                PYQTrend("Functions of Labelling", "3-Mark Short", "2023, 2021", "State all 4: Describe product & specify contents, Identify product/brand, Grade of product, Promote sales.")
            ),
            topperTips = listOf(
                "Categorize answers under the specific 'P' of the Marketing Mix clearly in bold.",
                "Draw real-life diagrams or packaging hierarchy pyramids to catch the examiner's eye."
            ),
            suggestedMethod = NoteMethodType.MIND_MAP
        ),

        // ==========================================
        // COMMERCE CLASS 12 - ECONOMICS CHAPTER 1
        // ==========================================
        ChapterItem(
            id = "c12_eco_ch1",
            chapterNumber = 1,
            title = "National Income & Related Aggregates",
            subject = SubjectType.ECONOMICS,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "10-12 Marks (Part A Macroeconomics)",
            summary = "Core macroeconomic measurement module covering Circular Flow of Income in 2-sector economy, Domestic Territory vs Normal Residents, 3 Methods of Measurement (Value Added, Income, Expenditure), and Golden Conversion Rules.",
            keyTopics = listOf(
                "Circular flow of income: Real Flow (Factor services & Goods) vs Money Flow (Factor payments & Consumption expenditure)",
                "Stocks (measured at a point in time, e.g. Wealth) vs Flows (measured over a period, e.g. Income)",
                "Factor Income (Earned for productive services, included) vs Transfer Income (Unearned gifts/scholarships, excluded)",
                "Value Added Method: Gross Value Added (GVA_MP) = Value of Output (Sales + Δ Stock) - Intermediate Consumption",
                "Income Method: NDP_FC = Compensation of Employees + Operating Surplus (Rent, Royalty, Interest, Profit) + Mixed Income",
                "Expenditure Method: GDP_MP = PFCE + GFCE + GDCF (Net Fixed Inv + Dep + Δ Stock) + Net Exports (X - M)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "The Three Golden Conversion Rules", "(1) Gross - Depreciation = Net. (2) Domestic + NFIA = National. (3) Market Price - Net Indirect Taxes (NIT) = Factor Cost. Master these to solve any conversion in 10 seconds.", isHighYield = true),
                NCERTCruxPoint(2, "Operating Surplus Components", "Operating Surplus = Rent + Royalty + Interest + Profit (where Profit = Dividend + Corporate Tax + Undistributed Profits/Retained Earnings).", isHighYield = true),
                NCERTCruxPoint(3, "Gross Domestic Capital Formation Components", "GDCF = Gross Domestic Fixed Capital Formation + Change in Stock. If 'Net' is given, add Depreciation to make it Gross.", isHighYield = true),
                NCERTCruxPoint(4, "Items Excluded from National Income", "Second-hand goods sale (only commission included), Transfer payments (old age pension, pocket money), Illegal incomes, Windfall gains (lotteries), Financial transactions (shares/bonds purchases).", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("GDP_MP by Expenditure Method", "PFCE + GFCE + Gross Domestic Capital Formation + Net Exports (X - M)", "Total expenditure on final domestic production", "₹ in Crores"),
                FormulaItem("NDP_FC by Income Method", "Compensation of Employees + Operating Surplus + Mixed Income", "Factor payments generated within domestic boundary", "₹ in Crores"),
                FormulaItem("Real GDP & GDP Deflator", "Real GDP = (Nominal GDP / GDP Deflator) × 100", "Adjusts national output for price inflation", "Index number")
            ),
            pyqTrends = listOf(
                PYQTrend("Calculate National Income (NNP_FC) by Expenditure & Income", "6-Mark Numerical", "2024, 2023, 2022, 2020", "Forgetting to add Change in Stock when 'Fixed' capital formation is given, resulting in 2-mark deduction."),
                PYQTrend("Reason-based inclusion/exclusion in Domestic Income", "3-Mark Question", "Every Single Year", "Failing to check if production unit is physically within Indian economic territory.")
            ),
            topperTips = listOf(
                "Write the complete formula in words first before substituting any numbers.",
                "Always write the final unit clearly: 'National Income (NNP_FC) = ₹1,850 Crores'. Leaving out the unit loses 0.5 marks.",
                "Draw the 3 conversion equations side by side for fast error checking."
            ),
            suggestedMethod = NoteMethodType.FEYNMAN
        ),

        // COMMERCE CLASS 12 - ECONOMICS CHAPTER 2
        ChapterItem(
            id = "c12_eco_ch2",
            chapterNumber = 2,
            title = "Money & Banking: Credit Creation & RBI Policy",
            subject = SubjectType.ECONOMICS,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            marksWeightage = "6-8 Marks (Part A Macroeconomics)",
            summary = "Analytical study of Commercial Bank Credit Creation mechanism, Money Multiplier, Functions of the Central Bank (RBI), and Monetary Policy tools (Repo, Reverse Repo, CRR, SLR, OMO) used to control Inflation & Deflation.",
            keyTopics = listOf(
                "Money definition & Components of Money Supply: M1 = Currency held by public + Demand deposits + Other deposits with RBI",
                "Credit Creation process by commercial banks based on fractional reserve system",
                "Money Multiplier formula: k = 1 / Legal Reserve Ratio (LRR)",
                "Central Bank functions: Bank of Issue, Banker to Govt, Banker's Bank & Supervisor, Lender of Last Resort, Controller of Credit",
                "Quantitative instruments: Repo Rate, Reverse Repo Rate, Bank Rate, CRR, SLR, Open Market Operations (OMO)",
                "Qualitative instruments: Margin Requirement, Moral Suasion, Selective Credit Controls",
                "Monetary correction for Excess Demand (Inflation) vs Deficient Demand (Deflation)"
            ),
            cruxPoints = listOf(
                NCERTCruxPoint(1, "Credit Creation Mathematical Table", "With Initial Deposit ₹1,000 and LRR 20%: Round 1 lends ₹800; Round 2 lends ₹640... Total Deposit = 1,000 × (1 / 0.20) = ₹5,000 Crores; Total Credit Created = ₹4,000 Crores.", isHighYield = true),
                NCERTCruxPoint(2, "Repo Rate Inflation Mechanism", "To curb Inflation (Excess Demand): RBI increases Repo Rate -> Cost of borrowing rises -> Commercial banks raise lending rates -> Demand for loans falls -> Money supply contracts -> Inflation cools down.", isHighYield = true),
                NCERTCruxPoint(3, "Open Market Operations (OMO)", "During Inflation, RBI SELLS government securities in open market to soak excess liquidity from banks and public. During Deflation, RBI BUYS securities to inject liquidity.", isHighYield = true)
            ),
            formulas = listOf(
                FormulaItem("Money Multiplier", "k = 1 / LRR", "Inverse relationship with Legal Reserve Ratio", "Ratio multiplier"),
                FormulaItem("Total Deposit Created", "Total Deposits = Primary Cash Deposit × (1 / LRR)", "Total credit multiplier mechanism", "₹ in Crores"),
                FormulaItem("Credit Created (Derivative Deposits)", "Total Deposits - Primary Cash Deposit", "Net new credit injected into economy", "₹ in Crores")
            ),
            pyqTrends = listOf(
                PYQTrend("Explain Credit Creation process with hypothetical numerical example", "4-Mark Question", "2024, 2022, 2020", "Students omit the 2 fundamental assumptions: (1) All banks are a single system, (2) All transactions pass through banks."),
                PYQTrend("Role of Repo Rate or Reverse Repo Rate during Inflation", "3-Mark Short", "2023, 2021", "Missing the chain effect of borrowing cost -> credit contraction -> aggregate demand reduction.")
            ),
            topperTips = listOf(
                "Always state both NCERT assumptions before presenting the credit creation table.",
                "Draw a flow-arrow chart for Central Bank monetary instruments: '↑ Repo Rate -> ↑ Lending Rate -> ↓ Borrowings -> ↓ Money Supply'."
            ),
            suggestedMethod = NoteMethodType.FLOW_CHART
        )
    )

    fun getChaptersByGradeAndSubject(grade: ClassGrade, subject: SubjectType? = null): List<ChapterItem> {
        return chapters.filter { chapter ->
            val gradeMatches = when (grade) {
                ClassGrade.COMPETITIVE -> true
                ClassGrade.CLASS_12_COMMERCE -> chapter.grade == ClassGrade.CLASS_12_COMMERCE || (chapter.grade == ClassGrade.CLASS_12 && chapter.subject.isCommerce)
                ClassGrade.CLASS_11_COMMERCE -> chapter.grade == ClassGrade.CLASS_11_COMMERCE || (chapter.grade == ClassGrade.CLASS_11 && chapter.subject.isCommerce)
                ClassGrade.CLASS_12 -> chapter.grade == ClassGrade.CLASS_12 || chapter.grade == ClassGrade.CLASS_12_COMMERCE
                ClassGrade.CLASS_11 -> chapter.grade == ClassGrade.CLASS_11 || chapter.grade == ClassGrade.CLASS_11_COMMERCE
                else -> chapter.grade == grade
            }
            gradeMatches && (subject == null || chapter.subject == subject)
        }
    }

    fun searchChapters(query: String): List<ChapterItem> {
        val q = query.trim().lowercase()
        if (q.isEmpty()) return chapters
        return chapters.filter { chapter ->
            chapter.title.lowercase().contains(q) ||
            chapter.summary.lowercase().contains(q) ||
            chapter.keyTopics.any { it.lowercase().contains(q) } ||
            chapter.subject.displayName.lowercase().contains(q)
        }
    }

    fun getChapterById(id: String): ChapterItem? {
        return chapters.firstOrNull { it.id == id }
    }
}

