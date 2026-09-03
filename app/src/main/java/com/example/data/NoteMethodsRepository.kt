package com.example.data

import com.example.model.MethodStep
import com.example.model.NoteMethodDetail
import com.example.model.NoteMethodType
import com.example.model.SubjectType

object NoteMethodsRepository {
    val methods: List<NoteMethodDetail> = listOf(
        NoteMethodDetail(
            type = NoteMethodType.CORNELL,
            iconKey = "ViewQuilt",
            shortSummary = "Divide your page into 2.5\" Cue Column (left), 6\" Main Note Body (right), and 2\" Summary Box (bottom). Perfect for CBSE/ICSE revision.",
            whyItWorks = "Forces active recall during revision by covering the right-hand notes and testing yourself with the left-hand cue questions. Saves 60% of pre-exam study time.",
            bestForSubjects = listOf(SubjectType.PHYSICS, SubjectType.CHEMISTRY, SubjectType.HISTORY, SubjectType.POLITY),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Record (Right Column)",
                    instruction = "During class or while reading NCERT, write concise bullet points, definitions, and equations in the main body area.",
                    proTip = "Use standard Indian syllabus abbreviations (e.g. w.r.t, approx, rxn, eq, diff)."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Formulate Cues (Left Column)",
                    instruction = "Immediately after the chapter, write probable 1-Mark, 3-Mark, or 5-Mark board exam questions and keywords in the cue column.",
                    proTip = "Turn headings into questions (e.g., 'What is Le Chatelier’s Principle?')."
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Summarize (Bottom Box)",
                    instruction = "Write a 2 to 4 sentence essence of the entire page in your own words.",
                    proTip = "If you can't summarize it in 30 words, you haven't mastered the core crux."
                ),
                MethodStep(
                    stepNumber = 4,
                    title = "Active Recall (The Cover Trick)",
                    instruction = "Cover the right column with a paper. Look only at the cue questions and recite the answers out loud.",
                    proTip = "Mark with a red star any question you faltered on for rapid pre-exam triage."
                )
            ),
            sampleTitle = "Class 10 Science: Chemical Reactions & Equations",
            sampleCue = "• What are the 4 indicators of a chemical rxn?\n• Define Exothermic vs Endothermic rxn with examples.\n• Why is respiration considered exothermic? [CBSE 2023]\n• What is Rancidity & Prevention?",
            sampleMainContent = "1. Indicators of Chemical Reaction:\n   a) Change in state (e.g. H2 + O2 -> H2O)\n   b) Change in colour (Fe + CuSO4 -> FeSO4 + Cu [Blue to Pale Green])\n   c) Evolution of gas (Zn + H2SO4 -> ZnSO4 + H2↑ with pop sound)\n   d) Change in temperature\n\n2. Types of Reactions:\n   - Combination: CaO (quick lime) + H2O -> Ca(OH)2 (slaked lime) + Heat (Hissing sound!)\n   - Decomposition: Thermal (FeSO4 -> Fe2O3 + SO2 + SO3 [Rotten egg smell]), Electrolytic (H2O -> 2H2 + O2 [2:1 volume ratio]), Photolytic (2AgCl -> 2Ag + Cl2 [White to Grey, used in black & white photography])\n   - Displacement: Fe + CuSO4 -> FeSO4 + Cu\n   - Double Displacement / Precipitation: Na2SO4 + BaCl2 -> BaSO4↓ (White ppt) + 2NaCl\n\n3. Redox & Corrosion:\n   - Oxidation: Gain of O2 / Loss of H2 (OIL - Oxidation Is Loss)\n   - Reduction: Gain of H2 / Loss of O2 (RIG - Reduction Is Gain)\n   - Corrosion: Iron + O2 + H2O -> Fe2O3.xH2O (Rust)\n   - Prevention: Galvanisation (Zinc coating), Painting, Greasing",
            sampleSummary = "A chemical reaction involves breaking and making bonds. 5 main types: Combination, Decomposition (Thermal/Electrolytic/Photo), Displacement, Double Displacement (Precipitation), and Redox. Prevent rancidity via nitrogen flushing & antioxidants.",
            visualLayoutStructure = "Left: 30% Cues | Right: 70% Body Notes\nBottom: 100% Core Summary (3-4 lines)"
        ),
        NoteMethodDetail(
            type = NoteMethodType.FEYNMAN,
            iconKey = "Psychology",
            shortSummary = "Learn by teaching. Explain any complex theorem or mechanism as if explaining to a 10-year-old child using simple everyday analogies.",
            whyItWorks = "Identifies false illusions of competence. When you get stuck trying to use jargon-free Indian life analogies, that's exactly where your exam trap lies.",
            bestForSubjects = listOf(SubjectType.PHYSICS, SubjectType.CHEMISTRY, SubjectType.MATHEMATICS, SubjectType.BIOLOGY),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Pick the Tough Topic",
                    instruction = "Write the exact theorem or concept at the top (e.g., Lenz's Law, Total Internal Reflection, Photosynthesis Light Reaction).",
                    proTip = "Focus on topics where you usually memorize formulas without intuitive grasp."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Explain to a 5th Grader (ELI5)",
                    instruction = "Write the explanation avoiding all textbook jargon. Use everyday Indian analogies (traffic, chai, cricket, water pipes).",
                    proTip = "If you have to use a scientific word, you must define it with an analogy."
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Pinpoint Knowledge Gaps",
                    instruction = "Notice where you hesitated or reverted to complex vocabulary. Go back to NCERT and re-read specifically that gap.",
                    proTip = "Ask yourself: 'Why does this happen at the microscopic / fundamental level?'"
                ),
                MethodStep(
                    stepNumber = 4,
                    title = "Simplify & Craft a Story/Analogy",
                    instruction = "Refine your final note into an unforgettable visual story.",
                    proTip = "Great analogies lock into long-term memory for life."
                )
            ),
            sampleTitle = "Lenz's Law & Electromagnetic Induction (Class 12 Physics)",
            sampleCue = "Concept: Lenz's Law\nAnalogy: 'The Stubborn Child / Anti-Change Rule'",
            sampleMainContent = "ELI5 Explanation:\nImagine you try to push a magnet's North pole toward a closed wire loop. The wire loop gets annoyed by this change! To push you away, it creates its own North pole facing you, repelling your magnet.\n\nNow, when you try to pull the magnet away, the loop again hates change! It turns into a South pole to attract and pull you back.\n\nWhy does nature do this?\nBecause of the Law of Conservation of Energy! If the loop helped you pull it faster for free, we'd get infinite energy from nothing (impossible). So the induced current ALWAYS fights the cause that creates it.\n\nFormula: ε = - dΦ/dt (The minus sign '-' IS Lenz's Law!)",
            sampleSummary = "Lenz's Law = Nature hates change in magnetic flux. Induced EMF direction always opposes the magnetic change that produced it (Conservation of Energy).",
            visualLayoutStructure = "Top: Concept | Middle: ELI5 Everyday Story/Analogy\nBottom: Formula + The 'Why' Behind the Math"
        ),
        NoteMethodDetail(
            type = NoteMethodType.MIND_MAP,
            iconKey = "Hub",
            shortSummary = "Central core node branching out into sub-topics with color-coded nodes, keywords, and micro-diagrams for spatial memory.",
            whyItWorks = "The human brain remembers images and visual hierarchy 6x better than linear text. Perfect for Biology body systems and History eras.",
            bestForSubjects = listOf(SubjectType.BIOLOGY, SubjectType.HISTORY, SubjectType.GEOGRAPHY, SubjectType.ECONOMICS),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Central Anchor Node",
                    instruction = "Draw a bold circle in the middle with the chapter name and a high-impact icon.",
                    proTip = "Use landscape mode for maximum radial spreading space."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Primary Trunk Branches (3 to 6 max)",
                    instruction = "Draw thick curved branches for major NCERT sections (e.g., Types, Functions, Diseases, Exceptions).",
                    proTip = "Use a distinct color for each primary branch (e.g. Red for Cardiac, Green for Respiratory)."
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Secondary Twigs & Keywords",
                    instruction = "Attach smaller branches containing ONLY 1-2 keywords, key enzymes, or years. No full sentences!",
                    proTip = "Add tiny doodles (heart, leaf, test tube) next to keywords to trigger photographic recall."
                )
            ),
            sampleTitle = "Class 10 Biology: Human Circulatory System",
            sampleCue = "Central Concept: 4-Chambered Heart & Double Circulation",
            sampleMainContent = "🌲 [CENTRAL NODE: Human Heart & Blood]\n  ├── 🔴 Branch 1: Blood Components\n  │     ├── Plasma (55%, water + proteins)\n  │     ├── RBCs (Haemoglobin, biconcave, O2 carrier, no nucleus)\n  │     ├── WBCs (Lymphocytes, Phagocytes, soldiers of body)\n  │     └── Platelets (Thrombocytes, blood clotting, prevents leakage)\n  ├── 🔵 Branch 2: Heart Anatomy\n  │     ├── Right Atrium -> Deox blood from Vena Cava\n  │     ├── Right Ventricle -> Pumps to Lungs via Pulmonary Artery (Exception!)\n  │     ├── Left Atrium -> Ox blood from Lungs via Pulmonary Vein (Exception!)\n  │     └── Left Ventricle -> Thickest muscular wall, pumps to whole body via Aorta\n  ├── 🟡 Branch 3: Double Circulation Mechanism\n  │     ├── Pulmonary: Heart -> Lungs -> Heart\n  │     └── Systemic: Heart -> Rest of Body -> Heart\n  │     └── Importance: Separates Oxygenated & Deoxygenated blood for high energy\n  └── 🟣 Branch 4: Blood Pressure & Vessels\n        ├── Arteries: Thick elastic walls, high pressure, no valves, carry away from heart\n        ├── Veins: Thin walls, low pressure, have valves to prevent backflow\n        └── Normal BP: 120/80 mm of Hg (Sphygmomanometer)",
            sampleSummary = "4 chambers ensure complete separation of oxygenated/deoxygenated blood. Arteries carry blood away (thick walls); Veins carry towards heart (valves). Double circulation delivers high efficiency for warm-blooded mammals.",
            visualLayoutStructure = "Radial Tree Layout: Center Chapter Hub -> 4 Color-coded Quadrants -> Keyword Twigs"
        ),
        NoteMethodDetail(
            type = NoteMethodType.BOXING,
            iconKey = "DashboardCustomize",
            shortSummary = "Group distinct formulas, case laws, or named reactions into standalone visual geometric boxes with color-coded high-yield borders.",
            whyItWorks = "Eliminates visual overwhelm and compartmentalizes information. You can visually navigate straight to the exact box you need during rapid revision.",
            bestForSubjects = listOf(SubjectType.MATHEMATICS, SubjectType.CHEMISTRY, SubjectType.PHYSICS),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Create Themed Boxes",
                    instruction = "Allocate 1 box per formula, law, or named reaction.",
                    proTip = "Use a grid of 4 to 6 boxes per A4 sheet."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Standardize Box Headers",
                    instruction = "Top: Formula Name & Board Frequency | Body: Formula & Variable Units | Footer: Common Traps.",
                    proTip = "Highlight the units in bright yellow—unit conversion mistakes cause 40% of marks loss in physics!"
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Color-Code by Difficulty",
                    instruction = "Green Box = Easy/Memorized, Orange Box = High PYQ weightage, Red Box = Tricky calculation/Exception.",
                    proTip = "Review red boxes 30 minutes before entering the exam hall."
                )
            ),
            sampleTitle = "Class 10 Maths: Trigonometry Master Box Cheatsheet",
            sampleCue = "Box 1: Ratios | Box 2: Identities | Box 3: Table Values | Box 4: Height & Distance Angle Tips",
            sampleMainContent = "┌── 📦 BOX 1: TRIG RATIOS (PBP / HHB Trick) ───────────────────┐\n│ Trick: 'Pandit Badri Prasad, Har Har Bole, Sona Chandi Tole'  │\n│ • sin θ = P/H    • cos θ = B/H    • tan θ = P/B              │\n│ • cosec θ = H/P  • sec θ = H/B    • cot θ = B/P              │\n└──────────────────────────────────────────────────────────────┘\n\n┌── 📦 BOX 2: THREE GOLDEN IDENTITIES ─────────────────────────┐\n│ 1) sin²θ + cos²θ = 1       => sin²θ = 1 - cos²θ              │\n│ 2) 1 + tan²θ = sec²θ       => sec²θ - tan²θ = 1              │\n│ 3) 1 + cot²θ = cosec²θ     => cosec²θ - cot²θ = 1            │\n│ ⚠️ BOARD TRAP: Remember (sec θ - tan θ) = 1 / (sec θ + tan θ) │\n└──────────────────────────────────────────────────────────────┘\n\n┌── 📦 BOX 3: SPEED VALUES (0°, 30°, 45°, 60°, 90°) ──────────┐\n│ • sin: 0, 1/2, 1/√2, √3/2, 1 (Count √0/4, √1/4, √2/4, √3/4, √4/4)\n│ • cos: 1, √3/2, 1/√2, 1/2, 0 (Reverse of sin)                 │\n│ • tan: 0, 1/√3, 1, √3, Not Defined (sin / cos)               │\n│ • tan 30° = 1/√3 and tan 60° = √3 (30 has 3 in denominator!) │\n└──────────────────────────────────────────────────────────────┘",
            sampleSummary = "Master the PBP/HHB trick for ratios, the 3 Pythagorean identities, and the √n/4 finger trick for standard angles.",
            visualLayoutStructure = "Modular Bento Layout: 3-4 distinct rounded boxes with high-contrast accent headers"
        ),
        NoteMethodDetail(
            type = NoteMethodType.FLOW_CHART,
            iconKey = "AccountTree",
            shortSummary = "Step-by-step linear or branching sequence diagrams showing input, intermediate stages, reagents, catalysts, and outcomes.",
            whyItWorks = "Essential for multi-step processes like organic reaction conversions, metallurgical extraction, and historical timelines.",
            bestForSubjects = listOf(SubjectType.CHEMISTRY, SubjectType.BIOLOGY, SubjectType.HISTORY, SubjectType.POLITY),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Define Start & End Goals",
                    instruction = "Specify initial reactant/event at the top and final product/outcome at the bottom.",
                    proTip = "In organic chemistry, identify if carbon chain length changes (step-up vs step-down)."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Annotate Every Arrow",
                    instruction = "Never draw a bare arrow. Write temperature, catalyst, reagent, or constitutional article on top of the arrow.",
                    proTip = "CBSE board examiners check arrow reagents before reading the text!"
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Highlight Branching Decisions",
                    instruction = "Use diamond decision boxes for condition-based splits (e.g. Aerobic vs Anaerobic respiration).",
                    proTip = "Keep direction of flow strictly top-to-bottom or left-to-right."
                )
            ),
            sampleTitle = "Class 10 Science: Breakdown of Glucose (Respiration Pathways)",
            sampleCue = "Flowchart: 6-Carbon Glucose -> 3-Carbon Pyruvate -> 3 Pathways",
            sampleMainContent = "[GLUCOSE: 6-Carbon Molecule]\n     │\n     ▼ (In Cytoplasm: Glycolysis)\n[PYRUVATE: 3-Carbon Molecule + Energy]\n     │\n     ├───► PATH 1: [Absence of Oxygen / Yeast - Anaerobic/Fermentation]\n     │         └──► Ethanol (2-Carbon) + CO2 + Energy (2 ATP) [Used in Breweries/Baking]\n     │\n     ├───► PATH 2: [Lack of Oxygen / Human Muscle Cells - Strenuous Exercise]\n     │         └──► Lactic Acid (3-Carbon) + Energy (2 ATP)\n     │              ⚠️ Cause of muscle cramps!\n     │\n     └───► PATH 3: [Presence of Oxygen / Mitochondria - Aerobic Respiration]\n               └──► CO2 + H2O + Energy (36-38 ATP)\n                    ⭐ Produces max energy!",
            sampleSummary = "Glucose breaks down in cytoplasm to Pyruvate. Fate depends on O2 presence: Yeast (Ethanol+CO2), Muscle cells (Lactic acid + Cramps), Mitochondria (CO2+H2O+38 ATP).",
            visualLayoutStructure = "Sequential Arrow Tree: Root -> Trunk -> 3 Distinct Terminal Pathways"
        ),
        NoteMethodDetail(
            type = NoteMethodType.MNEMONICS,
            iconKey = "EmojiEmotions",
            shortSummary = "Transform dry, hard-to-remember lists, periodic elements, and order of events into hilarious or memorable sentences and acronyms.",
            whyItWorks = "Engages emotional and semantic memory, making retrieval instantaneous under intense exam hall pressure.",
            bestForSubjects = listOf(SubjectType.CHEMISTRY, SubjectType.PHYSICS, SubjectType.GEOGRAPHY, SubjectType.HISTORY),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Extract First Letters",
                    instruction = "List down the sequence you must remember (e.g., K, Na, Ca, Mg, Al, Zn, Fe, Pb, H, Cu, Hg, Ag, Au).",
                    proTip = "Keep the list under 12 items per mnemonic sentence for peak retention."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "Create a Funny or Indian Context Story",
                    instruction = "Map each letter to a memorable character or everyday Indian phrase.",
                    proTip = "The funnier or more absurd the image, the harder it is to forget."
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Pair with a Quick Trigger Word",
                    instruction = "Associate the story with the chapter topic so the trigger pops up instantly in the exam.",
                    proTip = "Practice writing the first letters along the rough margin within 10 seconds."
                )
            ),
            sampleTitle = "Reactivity Series & Trigonometric Trick Vault",
            sampleCue = "Reactivity Series: K > Na > Ca > Mg > Al > Zn > Fe > Pb > [H] > Cu > Hg > Ag > Au",
            sampleMainContent = "🔤 Mnemonic 1: Metal Reactivity Series (Top to Bottom)\nSentence: \"Please Stop Calling Me A Careless Zebra Instead Try Learning How Copper Saves Gold\"\n• Please  -> Potassium (K)  [Most Reactive]\n• Stop    -> Sodium (Na)\n• Calling -> Calcium (Ca)\n• Me      -> Magnesium (Mg)\n• A       -> Aluminium (Al)\n• Careless-> Carbon (C)     [Non-metal benchmark]\n• Zebra   -> Zinc (Zn)\n• Instead -> Iron (Fe)\n• Try     -> Tin (Sn)\n• Learning-> Lead (Pb)\n• How     -> Hydrogen [H]   [Acid displacement benchmark]\n• Copper  -> Copper (Cu)\n• Saves   -> Silver (Ag)\n• Gold    -> Gold (Au)      [Least Reactive / Native State]\n\n🔤 Mnemonic 2: Taxonomic Hierarchy (Biology)\nSentence: \"Dear King Philip Came Over For Good Soup\"\n• Domain -> Kingdom -> Phylum -> Class -> Order -> Family -> Genus -> Species\n\n🔤 Mnemonic 3: Stages of Cell Division (Mitosis)\nAcronym: \"PMAT\"\n• Prophase -> Metaphase -> Anaphase -> Telophase",
            sampleSummary = "Reactivity series sentence allows instant determination of single displacement reactions and metallurgical extraction methods.",
            visualLayoutStructure = "Key Letter Column (Left) -> Mnemonic Word (Center) -> Scientific Term (Right)"
        ),
        NoteMethodDetail(
            type = NoteMethodType.QEC,
            iconKey = "FactCheck",
            shortSummary = "Question - Evidence - Conclusion: Structure every topic as a Board Exam 5-Marker with targeted bullet evidence and conclusion.",
            whyItWorks = "Directly aligns your study notes with the examiner's marking scheme. You learn in the exact format required for full 100/100 scores.",
            bestForSubjects = listOf(SubjectType.HISTORY, SubjectType.POLITY, SubjectType.ECONOMICS, SubjectType.SCIENCE_GENERAL),
            steps = listOf(
                MethodStep(
                    stepNumber = 1,
                    title = "Frame the Big Question",
                    instruction = "Turn the chapter topic into a formal board exam question (e.g. 'Why did Mahatma Gandhi launch the Non-Cooperation Movement?').",
                    proTip = "Always check past 10-year board question phrasing."
                ),
                MethodStep(
                    stepNumber = 2,
                    title = "List 5 Bullet Evidences (For 5 Marks)",
                    instruction = "Write exactly 5 distinct, numbered points with clear bold underlined subheadings.",
                    proTip = "CBSE examiners allocate 1 mark per distinct valid point."
                ),
                MethodStep(
                    stepNumber = 3,
                    title = "Write a 2-Line Concluding Impact",
                    instruction = "Wrap up with the historical, scientific, or economic significance.",
                    proTip = "A crisp conclusion distinguishes an 85% answer from a 98% topper answer."
                )
            ),
            sampleTitle = "Class 10 History: Non-Cooperation Movement Causes (5-Marker)",
            sampleCue = "CBSE 5-Mark Question: Analyze the 3 major factors and outcomes of the Non-Cooperation Movement (1920-1922).",
            sampleMainContent = "QUESTION: Explain the reasons for the launch of the Non-Cooperation Movement in 1920.\n\nEVIDENCE (5 Main Factors):\n1. First World War Economic Hardships:\n   - Huge increase in defence expenditure, price rise doubling between 1913-1918.\n   - Forced recruitment in rural areas causing widespread anger.\n\n2. The Rowlatt Act (1919) - 'Black Act':\n   - Allowed detention of political prisoners without trial for up to 2 years.\n   - Rallies organized, nation-wide hartal on 6th April.\n\n3. Jallianwala Bagh Massacre (13 April 1919):\n   - General Dyer blocked exit points and opened fire on peaceful crowd.\n   - Deep emotional trauma across the nation and widespread strikes.\n\n4. Khilafat Issue:\n   - Harsh peace treaty on Ottoman Emperor (Khalifa).\n   - Gandhi saw an opportunity to unite Hindus and Muslims under one umbrella.\n\n5. Gandhiji's Philosophy in 'Hind Swaraj' (1909):\n   - British rule established and survived only due to Indian cooperation; if Indians refused, British rule would collapse in a year.\n\nCONCLUSION:\nLaunched at Nagpur Congress Session (Dec 1920), uniting diverse social groups before withdrawal in 1922 following the Chauri Chaura incident.",
            sampleSummary = "5 core triggers: WW1 economic crisis, Rowlatt Act, Jallianwala Bagh massacre, Khilafat movement unity, and Hind Swaraj philosophy. Withdrawn post Chauri Chaura (1922).",
            visualLayoutStructure = "Header Question -> 5 Structured Evidence Headings -> Bold Concluding Impact"
        )
    )

    fun getMethodByType(type: NoteMethodType): NoteMethodDetail {
        return methods.firstOrNull { it.type == type } ?: methods.first()
    }
}
