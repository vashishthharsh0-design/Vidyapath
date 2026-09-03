package com.example.data

import com.example.model.ExamTrick
import com.example.model.FlashcardEntity

object MnemonicsRepository {
    val tricks: List<ExamTrick> = listOf(
        // VEDIC MATH 1
        ExamTrick(
            id = "vedic_sq_5",
            category = "Vedic Math & Speed Calculation",
            title = "Squaring Any Number Ending in 5 (Ekadhikena Purvena)",
            shortSummary = "Find the square of any number like 25, 65, 85, 115 in 2 seconds in your head without paper calculations.",
            formulaOrTrick = "Formula: (N * (N + 1)) followed by '25'",
            stepByStepExample = "Example 1: 75²\n• First part: 7 × (7 + 1) = 7 × 8 = 56\n• Second part: Always 25\n• Answer: 5625!\n\nExample 2: 115²\n• First part: 11 × 12 = 132\n• Second part: 25\n• Answer: 13225!",
            applicationScenario = "Class 10 Surface Areas & Volumes, Electricity numericals, and Physics problems involving (r²) where radius ends in .5 or 5.",
            boardRelevance = "Saves 4-5 minutes in long calculations and eliminates basic multiplication errors."
        ),
        // VEDIC MATH 2
        ExamTrick(
            id = "vedic_base_100",
            category = "Vedic Math & Speed Calculation",
            title = "Fast Multiplication Near Base 100 (Nikhilam Navatashcaramam)",
            shortSummary = "Multiply 96 × 94 or 104 × 107 in a single line without multi-step traditional multiplication.",
            formulaOrTrick = "Rule: (Number 1 + Deficiency 2) | (Deficiency 1 × Deficiency 2)",
            stepByStepExample = "Example: 94 × 97\n• Deficiencies from 100: 94 is (-6), 97 is (-3)\n• Left Part: 94 + (-3) = 91 (or 97 - 6 = 91)\n• Right Part: (-6) × (-3) = 18\n• Combine: 9118!\n\nExample 2: 104 × 108\n• Surplus from 100: (+4) and (+8)\n• Left Part: 104 + 8 = 112\n• Right Part: 4 × 8 = 32\n• Combine: 11232!",
            applicationScenario = "CBSE/ICSE Physics & Chemistry stoichiometry calculations with molar masses and constants.",
            boardRelevance = "Lightning speed cross-checks for objective MCQs and numerical answers."
        ),
        // SCIENCE MNEMONIC 1
        ExamTrick(
            id = "sci_reactivity",
            category = "Science Mnemonics",
            title = "Metal Reactivity Series Mnemonic",
            shortSummary = "Remember all 13 metals in the CBSE/ICSE reactivity series in perfect descending order with one sentence.",
            formulaOrTrick = "Mnemonic: \"Please Stop Calling Me A Careless Zebra Instead Try Learning How Copper Saves Gold\"",
            stepByStepExample = "• Please  -> Potassium (K)\n• Stop    -> Sodium (Na)\n• Calling -> Calcium (Ca)\n• Me      -> Magnesium (Mg)\n• A       -> Aluminium (Al)\n• Careless-> Carbon (C) [Non-metal benchmark]\n• Zebra   -> Zinc (Zn)\n• Instead -> Iron (Fe)\n• Try     -> Tin (Sn)\n• Learning-> Lead (Pb)\n• How     -> Hydrogen [H] [Acid benchmark]\n• Copper  -> Copper (Cu)\n• Saves   -> Silver (Ag)\n• Gold    -> Gold (Au)",
            applicationScenario = "Displacement reactions, Extraction of metals (Metallurgy), Reactions with acids, Electrolytic refining.",
            boardRelevance = "Directly solves at least 3-4 marks of chemical reaction questions in CBSE Class 10."
        ),
        // SCIENCE MNEMONIC 2
        ExamTrick(
            id = "sci_fl_left_hand",
            category = "Science Mnemonics",
            title = "Fleming's Left Hand Rule (Father-Mother-Child Trick)",
            shortSummary = "Never get confused between Magnetic Field, Current, and Force/Motion direction in Electric Motors.",
            formulaOrTrick = "Trick: FBI = Thumb (Force), Forefinger (B/Field), Middle Finger (I/Current) | FMC = Father, Mother, Child",
            stepByStepExample = "• Thumb = F (Force / Motion) -> 'Father'\n• Forefinger = B / Field (Magnetic Field from North to South) -> 'Mother'\n• Middle finger = I (Current from Positive to Negative) -> 'Child'\n\nKey Rule: Hold all 3 fingers mutually perpendicular (at 90° to each other)!",
            applicationScenario = "Class 10 & 12 Physics: Direction of force on a current-carrying conductor in a magnetic field, electric motor rotation.",
            boardRelevance = "Guaranteed 2-3 mark conceptual question every year in CBSE/ICSE Physics."
        ),
        // BOARD EXAM HACK 1
        ExamTrick(
            id = "board_15min_strategy",
            category = "CBSE Board Hacks",
            title = "The 15-Minute Reading Time Golden Protocol",
            shortSummary = "How toppers utilize the mandatory 15-minute question paper reading window to gain 10-15 bonus marks.",
            formulaOrTrick = "Strategy: 3-Pass Scanning + Section Order Deciding (Never start calculating!)",
            stepByStepExample = "• Minute 1 to 5: Scan Section A (MCQs) and mentally eliminate wrong options.\n• Minute 6 to 10: Check Internal Choices (OR questions) in 3-Mark and 5-Mark sections; mentally lock which option you will attempt.\n• Minute 11 to 15: Read the Case-Based comprehension questions and locate the clue keywords.\n• Important: Do NOT start solving tough numericals in reading time; lock your strategy instead!",
            applicationScenario = "All 3-Hour Board Examinations (CBSE, ICSE, State Boards).",
            boardRelevance = "Prevents last-minute exam hall panic and ensures you never choose the tougher alternative in internal choices."
        ),
        // BOARD EXAM HACK 2
        ExamTrick(
            id = "board_answer_presentation",
            category = "Topper Note Secrets",
            title = "The 5-Point Answering Framework for 5-Markers",
            shortSummary = "Why writing long unstructured paragraphs loses marks and how the bullet-point framework gets full 5/5.",
            formulaOrTrick = "Structure: [Heading in CAPS] -> [Subheading Bold] -> [Definition / Reason] -> [Formula / Diagram Box] -> [Underlined Key Terms]",
            stepByStepExample = "Sample Answer Layout:\n1. Draw 1-inch margins on both sides of the sheet.\n2. Write 'SECTION C' in the center.\n3. For every 5-marker, write 5 distinct points with bold headings (e.g., '1. Temperature Effect:', '2. Catalyst Role:').\n4. Underline key scientific words (e.g., *exothermic*, *photosynthesis*, *endosperm*) with a pencil.\n5. Leave 2 blank lines between two consecutive answers and draw a neat horizontal line.",
            applicationScenario = "Science, Social Science, Biology, and Chemistry descriptive answers.",
            boardRelevance = "Makes the examiner's job effortless, ensuring max marks in the 20-30 seconds they spend scanning each answer."
        )
    )

    val seedFlashcards: List<FlashcardEntity> = listOf(
        FlashcardEntity(
            subject = "Science",
            chapter = "Chemical Reactions",
            questionFront = "What are the brown fumes evolved when Lead Nitrate Pb(NO3)2 is heated strongly?",
            answerBack = "Nitrogen Dioxide gas (NO2).\nEquation: 2Pb(NO3)2 -> 2PbO (Yellow) + 4NO2 (Brown fumes) + O2",
            mnemonicOrTrick = "Trick: 'Nitrate gives NO2 (Brown) and Oxide (Yellow)'"
        ),
        FlashcardEntity(
            subject = "Biology",
            chapter = "Life Processes",
            questionFront = "Which enzyme in pancreatic juice digests emulsified fats?",
            answerBack = "Lipase enzyme.\n(Bile from liver first emulsifies large fat globules into smaller droplets, then Lipase breaks them into fatty acids + glycerol).",
            mnemonicOrTrick = "Trick: 'Lipase eats Lipids (fats)'"
        ),
        FlashcardEntity(
            subject = "Physics",
            chapter = "Electricity",
            questionFront = "If length of a wire is stretched to 'n' times its original length, what happens to its Resistance?",
            answerBack = "New Resistance R' = n² × R.\nBecause stretching length by 'n' reduces area to A/n to conserve volume.",
            mnemonicOrTrick = "Trick: Stretching = n² factor. (Double length = 4× Resistance)"
        ),
        FlashcardEntity(
            subject = "Mathematics",
            chapter = "Trigonometry",
            questionFront = "What is the relationship between (sec θ - tan θ) and (sec θ + tan θ)?",
            answerBack = "They are reciprocals of each other!\n(sec θ - tan θ) = 1 / (sec θ + tan θ) because sec²θ - tan²θ = 1.",
            mnemonicOrTrick = "Trick: Conjugate product equals 1"
        ),
        FlashcardEntity(
            subject = "History",
            chapter = "Nationalism in India",
            questionFront = "In which year and Congress Session was the resolution of 'Purna Swaraj' (Complete Independence) adopted?",
            answerBack = "December 1929, Lahore Congress Session, presided over by Jawaharlal Nehru.\n(26 January 1930 was declared as Independence Day).",
            mnemonicOrTrick = "Trick: '1929 Lahore -> Nehru -> Purna Swaraj'"
        )
    )
}
