package com.example.data

import com.example.model.*

object NcertNotesProvider {

    fun getDetailedNotes(book: NcertBook, chapter: NcertChapterInfo): NcertChapterDetailedNotes {
        // Return chapter's own detailedNotes if already defined, or generate/lookup curated notes
        if (chapter.detailedNotes != null) {
            return chapter.detailedNotes
        }

        return findCuratedNotes(book, chapter) ?: generateComprehensiveNotes(book, chapter)
    }

    private fun findCuratedNotes(book: NcertBook, chapter: NcertChapterInfo): NcertChapterDetailedNotes? {
        val key = "${book.grade.name}_${book.subject.name}_${chapter.chapterNumber}".uppercase()

        return when {
            // Class 10 Science Chapter 1: Chemical Reactions and Equations
            key.contains("CLASS_10_SCIENCE_GENERAL_1") || chapter.title.contains("Chemical Reactions", ignoreCase = true) -> {
                NcertChapterDetailedNotes(
                    overview = "This chapter introduces the fundamental concepts of chemical changes, representation of chemical equations, the necessity of balancing equations according to the Law of Conservation of Mass, diverse types of chemical reactions, and their everyday applications including corrosion and rancidity.",
                    keyConcepts = listOf(
                        NcertNoteConcept(
                            title = "1. Chemical Reactions & Characteristics",
                            explanation = "A chemical reaction is a process in which one or more substances (reactants) undergo chemical changes to form new substances (products) with entirely different physical and chemical properties.",
                            keyPoints = listOf(
                                "Observations determining a chemical reaction: Change in state, change in colour, evolution of gas (e.g., H₂ gas test with pop sound), change in temperature (exothermic/endothermic), or formation of a precipitate.",
                                "Law of Conservation of Mass: Mass can neither be created nor destroyed in a chemical reaction. Total mass of reactants equals total mass of products.",
                                "Balancing by Hit-and-Trial Method: Ensure the number of atoms of each element on LHS (reactants) equals RHS (products)."
                            )
                        ),
                        NcertNoteConcept(
                            title = "2. Classification of Chemical Reactions",
                            explanation = "Chemical reactions are classified into distinct types based on the rearrangement of chemical bonds and energy changes.",
                            keyPoints = listOf(
                                "Combination Reaction: Two or more reactants combine to form a single product. Example: Quicklime + Water: CaO(s) + H₂O(l) → Ca(OH)₂(aq) + Heat (Slaked lime used for whitewashing).",
                                "Decomposition Reaction: A single reactant breaks down into two or more simpler products. Requires energy as Heat (Thermal: 2FeSO₄ → Fe₂O₃ + SO₂ + SO₃, 2Pb(NO₃)₂ → 2PbO + 4NO₂ + O₂), Light (Photolytic: 2AgCl → 2Ag + Cl₂ used in black-and-white photography), or Electricity (Electrolytic: 2H₂O → 2H₂ + O₂ in 2:1 volume ratio).",
                                "Displacement Reaction: A more reactive metal displaces a less reactive metal from its salt solution. Example: Fe(s) + CuSO₄(aq, blue) → FeSO₄(aq, light green) + Cu(s, reddish brown).",
                                "Double Displacement (Precipitation): Mutual exchange of ions between two aqueous compounds. Example: Na₂SO₄(aq) + BaCl₂(aq) → BaSO₄(s, white ppt) + 2NaCl(aq).",
                                "Redox Reactions: Oxidation is gain of oxygen or loss of hydrogen/electrons. Reduction is loss of oxygen or gain of hydrogen/electrons. The substance getting oxidised is the reducing agent; the one getting reduced is the oxidising agent."
                            )
                        ),
                        NcertNoteConcept(
                            title = "3. Effects of Oxidation in Daily Life",
                            explanation = "Everyday chemical degradation caused by atmospheric oxygen and moisture.",
                            keyPoints = listOf(
                                "Corrosion: Slow eating away of metals by air, moisture, and acids. Iron rusts to form hydrated ferric oxide Fe₂O₃·xH₂O (reddish brown). Copper forms basic copper carbonate CuCO₃·Cu(OH)₂ (green coating). Silver forms silver sulphide Ag₂S (black tarnish). Prevention: Galvanisation (coating with zinc), painting, greasing, alloying.",
                                "Rancidity: Oxidation of fats and oils in food leading to unpleasant smell and bad taste. Prevention: Flushing packages with unreactive Nitrogen gas (e.g. potato chips bags), adding antioxidants (BHA, BHT), vacuum packaging, and refrigeration."
                            )
                        )
                    ),
                    importantDefinitions = listOf(
                        "Exothermic Reaction" to "Reactions in which heat energy is released along with the formation of products (e.g., burning of natural gas CH₄ + 2O₂ → CO₂ + 2H₂O + heat, and cellular respiration).",
                        "Endothermic Reaction" to "Reactions in which energy is absorbed from surroundings in the form of heat, light, or electricity (e.g., decomposition of calcium carbonate CaCO₃ + Heat → CaO + CO₂).",
                        "Precipitate" to "An insoluble solid that emerges from a liquid solution during a chemical double-displacement reaction.",
                        "Reducing Agent" to "A substance that loses electrons or donates hydrogen/gains oxygen, thereby causing reduction of the other substance."
                    ),
                    keyFormulasOrLaws = listOf(
                        "Slaked Lime Formation: CaO(s) + H₂O(l) → Ca(OH)₂(aq) + Heat",
                        "Whitewashing Wall Reaction: Ca(OH)₂(aq) + CO₂(g) → CaCO₃(s, shiny layer) + H₂O(l)",
                        "Thermal Decomposition of Lead Nitrate: 2Pb(NO₃)₂(s) → 2PbO(s) + 4NO₂(g, brown fumes) + O₂(g)",
                        "Water Electrolysis Volume Ratio: H₂ : O₂ = 2 : 1",
                        "Rust Composition: Fe₂O₃·xH₂O (Hydrated Iron(III) Oxide)"
                    ),
                    ncertQuestionsAndAnswers = listOf(
                        NcertQnA(
                            question = "Why does the colour of copper sulphate solution change when an iron nail is dipped in it?",
                            answer = "Iron is more reactive than copper according to the metal reactivity series. When an iron nail is dipped into blue copper sulphate (CuSO₄) solution, iron displaces copper to form light green ferrous sulphate (FeSO₄) while reddish-brown copper deposits on the iron nail:\nFe(s) + CuSO₄(aq, blue) → FeSO₄(aq, green) + Cu(s)."
                        ),
                        NcertQnA(
                            question = "Why is respiration considered an exothermic reaction? Explain with equation.",
                            answer = "During digestion, food containing carbohydrates is broken down into glucose. In our cells, this glucose combines with oxygen to release energy required by our body for various metabolic activities:\nC₆H₁₂O₆(aq) + 6O₂(aq) → 6CO₂(aq) + 6H₂O(l) + Energy (ATP).\nSince heat/energy is released in this process, respiration is exothermic."
                        ),
                        NcertQnA(
                            question = "Why are decomposition reactions called the opposite of combination reactions? Write equations.",
                            answer = "In a combination reaction, two or more reactants join to form a single product (e.g., C + O₂ → CO₂). In contrast, in a decomposition reaction, a single reactant splits into two or more simpler products (e.g., CaCO₃ → CaO + CO₂). Hence, they are exact opposites in terms of reaction mechanism."
                        )
                    ),
                    examPointers = listOf(
                        "Always write state symbols (s, l, g, aq) when writing chemical equations in CBSE board papers to score full marks.",
                        "Brown fumes emitted upon heating lead nitrate are strictly Nitrogen Dioxide (NO₂)—frequently asked in 1-mark objective questions.",
                        "In electrolysis of water, cathode collects Hydrogen gas (double volume) and anode collects Oxygen gas (single volume)."
                    )
                )
            }

            // Class 10 Mathematics Chapter 8: Introduction to Trigonometry
            key.contains("CLASS_10_MATHEMATICS_8") || chapter.title.contains("Trigonometry", ignoreCase = true) -> {
                NcertChapterDetailedNotes(
                    overview = "Trigonometry deals with the relationship between angles and side lengths of a right-angled triangle. This chapter covers trigonometric ratios of acute angles, standard values for 0°, 30°, 45°, 60°, and 90°, and fundamental Pythagorean trigonometric identities.",
                    keyConcepts = listOf(
                        NcertNoteConcept(
                            title = "1. Trigonometric Ratios (T-Ratios)",
                            explanation = "In a right-angled triangle ABC right-angled at B, with respect to acute angle A, the sides are Opposite Side (Perpendicular P), Adjacent Side (Base B), and Hypotenuse (H).",
                            keyPoints = listOf(
                                "sin A = Perpendicular / Hypotenuse (P / H)",
                                "cos A = Base / Hypotenuse (B / H)",
                                "tan A = Perpendicular / Base (P / B) = sin A / cos A",
                                "cosec A = 1 / sin A = H / P",
                                "sec A = 1 / cos A = H / B",
                                "cot A = 1 / tan A = B / P = cos A / sin A",
                                "Mnemonic: 'Some People Have, Curly Brown Hair, Turned Permanent Black'."
                            )
                        ),
                        NcertNoteConcept(
                            title = "2. Standard Angle Values Table",
                            explanation = "Memorising standard angle values (0°, 30°, 45°, 60°, 90°) is critical for solving evaluation and identity problems.",
                            keyPoints = listOf(
                                "sin: 0° = 0, 30° = 1/2, 45° = 1/√2, 60° = √3/2, 90° = 1",
                                "cos: 0° = 1, 30° = √3/2, 45° = 1/√2, 60° = 1/2, 90° = 0 (reverse sequence of sin)",
                                "tan: 0° = 0, 30° = 1/√3, 45° = 1, 60° = √3, 90° = Not Defined (∞)",
                                "Values of sin θ increase from 0 to 1 as θ increases from 0° to 90°, whereas cos θ decreases from 1 to 0."
                            )
                        ),
                        NcertNoteConcept(
                            title = "3. Fundamental Trigonometric Identities",
                            explanation = "Identities derived from the Pythagoras theorem (P² + B² = H²) that hold true for all acute angles 0° ≤ θ ≤ 90°.",
                            keyPoints = listOf(
                                "Identity 1: sin²θ + cos²θ = 1 (Derived by dividing P² + B² = H² by H²)",
                                "Identity 2: 1 + tan²θ = sec²θ or sec²θ - tan²θ = 1",
                                "Identity 3: 1 + cot²θ = cosec²θ or cosec²θ - cot²θ = 1",
                                "Strategy for proving identities: Convert complex terms into sin θ and cos θ; take LCM; use algebraic identities like (a² - b²) = (a - b)(a + b)."
                            )
                        )
                    ),
                    importantDefinitions = listOf(
                        "Trigonometric Ratio" to "The ratio of the lengths of two sides of a right-angled triangle with respect to one of its acute angles.",
                        "Trigonometric Identity" to "An equation involving trigonometric ratios of an angle which is valid for all values of the angles for which the ratios are defined."
                    ),
                    keyFormulasOrLaws = listOf(
                        "sin²θ + cos²θ = 1  ⇒  sin²θ = 1 - cos²θ  |  cos²θ = 1 - sin²θ",
                        "sec²θ - tan²θ = 1  ⇒  (sec θ - tan θ)(sec θ + tan θ) = 1",
                        "cosec²θ - cot²θ = 1  ⇒  (cosec θ - cot θ)(cosec θ + cot θ) = 1",
                        "tan θ = sin θ / cos θ  and  cot θ = cos θ / sin θ"
                    ),
                    ncertQuestionsAndAnswers = listOf(
                        NcertQnA(
                            question = "If sin A = 3/4, calculate cos A and tan A.",
                            answer = "Let ABC be a right-angled triangle with ∠B = 90°.\nsin A = Perpendicular / Hypotenuse = BC / AC = 3/4.\nLet BC = 3k, AC = 4k for some positive constant k.\nBy Pythagoras theorem: AC² = AB² + BC²\n(4k)² = AB² + (3k)²  ⇒  16k² = AB² + 9k²  ⇒  AB² = 7k²  ⇒  AB = √7 k.\nTherefore:\ncos A = Base / Hypotenuse = AB / AC = (√7 k) / (4k) = √7 / 4.\ntan A = Perpendicular / Base = BC / AB = (3k) / (√7 k) = 3 / √7."
                        ),
                        NcertQnA(
                            question = "Prove that: (sin θ - 2sin³θ) / (2cos³θ - cos θ) = tan θ.",
                            answer = "LHS = [sin θ(1 - 2sin²θ)] / [cos θ(2cos²θ - 1)]\nWe know that 1 = sin²θ + cos²θ.\nNumerator: sin θ(sin²θ + cos²θ - 2sin²θ) = sin θ(cos²θ - sin²θ)\nDenominator: cos θ(2cos²θ - (sin²θ + cos²θ)) = cos θ(cos²θ - sin²θ)\nCancelling the common factor (cos²θ - sin²θ) in numerator and denominator:\n= sin θ / cos θ = tan θ = RHS. Hence proved!"
                        )
                    ),
                    examPointers = listOf(
                        "Never write sin A as 'sin × A'. Sine is a function, not a multiplication factor.",
                        "When proving identities, multiplying the numerator and denominator by the conjugate (e.g. 1 - sin A or sec A + tan A) resolves 90% of tricky square-root questions."
                    )
                )
            }

            // Class 10 Science Chapter 5: Life Processes
            key.contains("CLASS_10_SCIENCE_GENERAL_5") || chapter.title.contains("Life Processes", ignoreCase = true) -> {
                NcertChapterDetailedNotes(
                    overview = "Life processes are the basic maintenance processes that keep an organism alive. This chapter explores Nutrition (Autotrophic & Heterotrophic), Respiration (Aerobic & Anaerobic), Transportation in humans and plants, and Excretion in humans and plants.",
                    keyConcepts = listOf(
                        NcertNoteConcept(
                            title = "1. Nutrition in Plants and Animals",
                            explanation = "Nutrition is the intake of nutrients for energy, growth, and tissue repair.",
                            keyPoints = listOf(
                                "Autotrophic Nutrition (Photosynthesis): 6CO₂ + 12H₂O + Sunlight + Chlorophyll → C₆H₁₂O₆ + 6O₂ + 6H₂O. Steps: (1) Absorption of light energy by chlorophyll, (2) Conversion of light into chemical energy and splitting of water into hydrogen and oxygen, (3) Reduction of CO₂ to carbohydrates.",
                                "Stomatal Function: Stomata pores facilitate gaseous exchange. Guard cells swell when water flows into them, opening the stomatal pore; and shrink when water leaves, closing the pore.",
                                "Human Digestion: Mouth (Salivary amylase breaks starch into maltose) → Stomach (Pepsin digests proteins in acidic medium maintained by HCl; Mucus protects stomach wall) → Small Intestine (Bile emulsifies fats; Pancreatic trypsin digests proteins; Lipase breaks down fats; Villi absorb nutrients into blood) → Large Intestine (absorbs excess water)."
                            )
                        ),
                        NcertNoteConcept(
                            title = "2. Respiration & Energy Release",
                            explanation = "Breakdown of glucose to yield ATP energy inside cells.",
                            keyPoints = listOf(
                                "First step in Cytoplasm: Glucose (6C) breaks down into Pyruvate (3C) + energy.",
                                "Fate of Pyruvate: (A) In yeast (Anaerobic / Fermentation): Ethanol (2C) + CO₂ + Energy. (B) In human muscle cells during vigorous exercise (lack of O₂): Lactic acid (3C) + Energy (causes muscle cramps). (C) In Mitochondria (Aerobic with O₂): 6CO₂ + 6H₂O + 38 ATP.",
                                "Human Respiratory System: Nostrils → Pharynx → Larynx → Trachea (supported by cartilage rings to prevent collapse) → Bronchi → Alveoli (richly supplied with blood capillaries for maximal diffusion of gases)."
                            )
                        ),
                        NcertNoteConcept(
                            title = "3. Transportation & Excretion",
                            explanation = "Circulation of nutrients and disposal of nitrogenous metabolic wastes.",
                            keyPoints = listOf(
                                "Double Circulation in Humans: Blood passes through the heart twice in one complete cardiac cycle (Pulmonary circulation to lungs + Systemic circulation to the body). Prevents mixing of oxygenated and deoxygenated blood, ensuring high energy efficiency for warm-blooded mammals.",
                                "Blood Vessels: Arteries carry blood away from heart (thick elastic walls, high pressure, no valves). Veins carry blood towards heart (thin walls, low pressure, have valves to prevent backflow).",
                                "Excretion (Nephron): Nephron is the structural and functional filtration unit of the kidney. Bowman's capsule encapsulates glomerulus where ultrafiltration occurs. Useful substances (glucose, amino acids, salts, water) are selectively reabsorbed along the tubular part."
                            )
                        )
                    ),
                    importantDefinitions = listOf(
                        "Peristaltic Movements" to "Rhythmic contraction and relaxation of the muscular walls of the alimentary canal to push food downwards.",
                        "Emulsification" to "The breakdown of large fat globules into smaller globules by bile salts, increasing enzyme efficiency.",
                        "Double Circulation" to "Circulatory mechanism where blood passes through the heart twice during each complete cycle (pulmonary and systemic).",
                        "Translocation" to "Transport of soluble products of photosynthesis (sucrose, amino acids) through phloem using energy from ATP."
                    ),
                    keyFormulasOrLaws = listOf(
                        "Photosynthesis Equation: 6CO₂ + 12H₂O + Sunlight + Chlorophyll → C₆H₁₂O₆ + 6O₂ + 6H₂O",
                        "Aerobic Respiration: C₆H₁₂O₆ + 6O₂ → 6CO₂ + 6H₂O + 38 ATP",
                        "Lactic Acid Fermentation (Muscles): Pyruvate → Lactic Acid + Energy",
                        "Alcoholic Fermentation (Yeast): Pyruvate → Ethanol + CO₂ + Energy"
                    ),
                    ncertQuestionsAndAnswers = listOf(
                        NcertQnA(
                            question = "Why is double circulation necessary in human beings?",
                            answer = "Human beings and birds are warm-blooded organisms that constantly require large amounts of energy to maintain a constant body temperature. Double circulation completely separates oxygenated blood from deoxygenated blood in a 4-chambered heart, preventing any mixing and ensuring a highly efficient supply of oxygen to body cells."
                        ),
                        NcertQnA(
                            question = "What are the differences between aerobic and anaerobic respiration?",
                            answer = "1. Aerobic takes place in the presence of Oxygen, whereas anaerobic occurs in the absence of Oxygen.\n2. Aerobic occurs in Mitochondria; anaerobic occurs in cytoplasm (yeast/muscles).\n3. End products in aerobic are CO₂ + H₂O; in anaerobic are Ethanol + CO₂ (yeast) or Lactic acid (muscles).\n4. Aerobic releases 38 ATP per glucose; anaerobic yields only 2 ATP."
                        )
                    ),
                    examPointers = listOf(
                        "Diagrams of the Human Heart and Nephron are high-frequency 5-mark questions in CBSE Class 10 Science.",
                        "Remember that cartilage rings in the trachea prevent it from collapsing when air pressure drops."
                    )
                )
            }

            // Class 10 History Chapter 1: The Rise of Nationalism in Europe
            key.contains("CLASS_10_HISTORY_1") || chapter.title.contains("Nationalism in Europe", ignoreCase = true) -> {
                NcertChapterDetailedNotes(
                    overview = "Nationalism in 19th-century Europe emerged as a powerful force that swept away multi-national dynastic empires and gave birth to the nation-state. This chapter traces the French Revolution, the Napoleonic Code, the Romantic Movement, liberal-nationalist uprisings of 1848, the unifications of Germany and Italy, and the Balkan crisis.",
                    keyConcepts = listOf(
                        NcertNoteConcept(
                            title = "1. The French Revolution and the Idea of the Nation",
                            explanation = "The French Revolution of 1789 introduced the first clear expression of nationalism in Europe.",
                            keyPoints = listOf(
                                "Measures to create collective identity: Notion of 'la patrie' (fatherland) and 'le citoyen' (citizen), new tricolour flag replacing the royal standard, Estate General elected by active citizens and renamed National Assembly, abolition of internal customs duties, uniform system of weights and measures, French designated the national language.",
                                "Civil Code of 1804 (Napoleonic Code): Abolished privileges based on birth, established equality before law, secured right to property, eliminated feudal dues in conquered territories. However, political freedoms were curtailed, censorship was enforced, and forced conscription into French armies created hostility."
                            )
                        ),
                        NcertNoteConcept(
                            title = "2. Unification of Germany and Italy",
                            explanation = "How fragmented monarchies transformed into unified European nation-states.",
                            keyPoints = listOf(
                                "Unification of Germany (1866–1871): Led by Prussian Chief Minister Otto von Bismarck using Prussian army and bureaucracy ('Blood and Iron policy'). Fought three wars over seven years against Denmark, Austria, and France, ending in Prussian victory. Kaiser William I proclaimed German Emperor at the Hall of Mirrors in Versailles (Jan 1871).",
                                "Unification of Italy: Italy was divided into 7 states. Sardinia-Piedmont under King Victor Emmanuel II led the movement. Key figures: Giuseppe Mazzini (founded secret society 'Young Italy'), Count Cavour (diplomatic alliance with France defeating Austria in 1859), Giuseppe Garibaldi (led the 'Red Shirts' through Kingdom of the Two Sicilies, winning peasant support). Victor Emmanuel II declared King of Italy in 1861."
                            )
                        ),
                        NcertNoteConcept(
                            title = "3. Visualizing the Nation & The Balkan Powder Keg",
                            explanation = "Allegories representing abstract nationhood, and nationalism transforming into imperialism.",
                            keyPoints = listOf(
                                "Female Allegories: Artists in the 18th and 19th centuries personified nations. Marianne represented the French Republic (red cap, cockade, liberty). Germania represented the German nation (crown of oak leaves standing for heroism).",
                                "Balkans Crisis: Region comprising modern-day Romania, Bulgaria, Albania, Greece, Serbia, etc., under Ottoman Empire. The spread of romantic nationalism and decay of Ottoman power made the region deeply explosive. Rivalry among major powers (Russia, Germany, Britain, Austro-Hungary) culminated in World War I."
                            )
                        )
                    ),
                    importantDefinitions = listOf(
                        "Zollverein (1834)" to "A customs union formed at the initiative of Prussia. It abolished tariff barriers and reduced the number of currencies from over thirty to two, creating economic national unity.",
                        "Romanticism" to "A cultural movement that sought to develop a particular form of nationalist sentiment by emphasizing emotion, intuition, mystical feelings, folk culture, and vernacular language over cold reason.",
                        "Nation-State" to "A state in which the majority of its citizens, and not only its rulers, develop a sense of common identity and shared history or descent."
                    ),
                    keyFormulasOrLaws = listOf(
                        "Key Timeline 1789: Outbreak of the French Revolution",
                        "Key Timeline 1804: Napoleonic Civil Code introduced",
                        "Key Timeline 1815: Battle of Waterloo; Treaty of Vienna hosted by Duke Metternich",
                        "Key Timeline 1834: Zollverein Customs Union established",
                        "Key Timeline 1861: Victor Emmanuel II crowned King of Unified Italy",
                        "Key Timeline 1871: German Empire proclaimed at Versailles under Kaiser William I"
                    ),
                    ncertQuestionsAndAnswers = listOf(
                        NcertQnA(
                            question = "Explain the measures and practices that the French revolutionaries introduced to create a sense of collective identity amongst the French people.",
                            answer = "1. The ideas of 'la patrie' (the fatherland) and 'le citoyen' (the citizen) emphasized the notion of a united community enjoying equal constitutional rights.\n2. A new French tricolour flag was chosen to replace the former royal standard.\n3. The Estates General was elected by active citizens and renamed the National Assembly.\n4. New hymns were composed, oaths taken, and martyrs commemorated in the name of the nation.\n5. Internal customs duties and dues were abolished, and a uniform system of weights and measures was adopted.\n6. Regional dialects were discouraged, and French became the common language of the nation."
                        ),
                        NcertQnA(
                            question = "Briefly trace the process of German unification.",
                            answer = "1. In 1848, middle-class Germans tried to unite different regions of the German confederation into a nation-state governed by an elected parliament (Frankfurt Parliament), but were repressed by the monarchy and military.\n2. Prussia took on the leadership under its Chief Minister Otto von Bismarck, the architect of the process.\n3. Bismarck followed the policy of 'Blood and Iron', fighting three wars over seven years with Austria, Denmark, and France.\n4. Prussian victory completed unification. In January 1871, the Prussian King, William I, was proclaimed German Emperor at the Palace of Versailles."
                        )
                    ),
                    examPointers = listOf(
                        "Duke Metternich's famous quote: 'When France sneezes, the rest of Europe catches cold' is a perennial favorite in CBSE 1-mark quote questions.",
                        "Clearly distinguish between the contributions of Mazzini (ideological visionary), Cavour (diplomatic tactician), and Garibaldi (military leader) in Italian unification."
                    )
                )
            }

            else -> null
        }
    }

    private fun generateComprehensiveNotes(book: NcertBook, chapter: NcertChapterInfo): NcertChapterDetailedNotes {
        val topicBullets = if (chapter.keyTopics.isNotEmpty()) {
            chapter.keyTopics
        } else {
            listOf("Core conceptual foundations", "Practical applications & methodology", "Summary & key formulas")
        }

        val concepts = topicBullets.mapIndexed { index, topic ->
            NcertNoteConcept(
                title = "${index + 1}. $topic",
                explanation = "In CBSE Class ${book.grade.displayName} ${book.subject.displayName}, '$topic' represents an essential pillar of the rationalised NCERT curriculum for '${chapter.title}'. Master this concept by understanding foundational definitions, logical derivations, and real-life problem-solving contexts.",
                keyPoints = listOf(
                    "Thorough conceptual clarity: Understanding the primary principles governing $topic as outlined in NCERT.",
                    "Syllabus Relevance: High weightage area in both formative assessments and terminal CBSE examinations.",
                    "Analytical Application: Step-by-step methodology to solve in-text examples, numerical problems, and reasoning questions.",
                    "Exam Technique: Highlight key terms and standard NCERT phrasing to secure maximum marks."
                )
            )
        }

        val definitions = listOf(
            "${chapter.title} Core Principle" to "The fundamental scientific, mathematical, or literary framework that underpins this chapter in the rationalised NCERT curriculum.",
            "Standard NCERT Terminology" to "Authoritative definitions prescribed by CBSE and NCERT for accurate answering in board and school exams."
        )

        val formulasOrLaws = listOf(
            "Essential Rule 1: Always verify assumptions against NCERT textbook guidelines.",
            "Essential Rule 2: In descriptive answers, structure points logically with numbered subheadings.",
            "Essential Rule 3: For numerical and analytical problems, write given data, formula, substitution, and final answer with correct SI units."
        )

        val questions = listOf(
            NcertQnA(
                question = "What is the primary learning objective and significance of '${chapter.title}' in ${book.title}?",
                answer = "The chapter '${chapter.title}' (${chapter.hindiTitle}) provides foundational understanding of key concepts in ${book.subject.displayName}. It equips students with analytical understanding, practical problem-solving capability, and conceptual clarity required for CBSE examinations and higher secondary studies."
            ),
            NcertQnA(
                question = "Summarize the key NCERT takeaway from '${chapter.title}'.",
                answer = "${chapter.summary}\n\nKey areas to revise thoroughly include:\n" + topicBullets.joinToString("\n") { "• $it" }
            )
        )

        val pointers = listOf(
            "Read the NCERT textbook line-by-line; board exam multiple-choice and assertion-reason questions are framed directly from textbook statements.",
            "Attempt all in-text questions (blue box questions) and end-of-chapter NCERT exercises before consulting reference books.",
            "Maintain a separate formula / vocabulary notebook for quick revision before exam day."
        )

        return NcertChapterDetailedNotes(
            overview = "${chapter.summary} This comprehensive study note provides chapter theory, detailed concept breakdowns, definitions, NCERT exercise solutions, and CBSE board examination tips.",
            keyConcepts = concepts,
            importantDefinitions = definitions,
            keyFormulasOrLaws = formulasOrLaws,
            ncertQuestionsAndAnswers = questions,
            examPointers = pointers
        )
    }
}
