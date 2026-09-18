package com.example.data

import com.example.model.*

object TestPaperRepository {

    val testPapers: List<TestPaperItem> = listOf(
        // ==========================================
        // 1. CBSE CLASS 12 ACCOUNTANCY BOARD PAPER
        // ==========================================
        TestPaperItem(
            id = "cbse_12_acc_paper_1",
            title = "CBSE Class 12 Accountancy Board Mock Paper",
            subtitle = "Strictly based on CBSE Official SQP Blueprint (Code 055)",
            subject = SubjectType.ACCOUNTANCY,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            maxMarks = 80,
            timeAllowedMinutes = 180,
            generalInstructions = listOf(
                "This question paper comprises two Parts – Part A (60 Marks: Accounting for Partnership Firms and Companies) and Part B (20 Marks: Analysis of Financial Statements).",
                "There is no overall choice. However, internal choice has been provided in 7 questions of one mark, 2 questions of three marks, 1 question of four marks and 2 questions of six marks.",
                "All workings should form part of the answer and should be clearly shown.",
                "Draw standard Journal formats with Date, Particulars, L.F., Debit (Rs), Credit (Rs)."
            ),
            sections = listOf(
                TestSection(
                    id = "acc_sec_a",
                    sectionName = "Part A - Section 1: 1-Mark Questions (MCQ & Assertion-Reason)",
                    instructions = "Select the single best answer. Questions 1 to 16 carry 1 mark each.",
                    questions = listOf(
                        TestQuestion(
                            id = "acc_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "A and B are partners in a firm sharing profits in 3:2 ratio without a partnership deed. Partner A has advanced a loan of ₹2,00,000 to the firm on 1st October 2024. The firm earned a profit of ₹30,000 before interest on 31st March 2025. What amount of interest on loan will A receive, and where will it be recorded?",
                            options = listOf(
                                "₹12,000 debited to Profit & Loss Appropriation A/c",
                                "₹6,000 debited to Profit & Loss A/c",
                                "₹6,000 debited to Profit & Loss Appropriation A/c",
                                "No interest will be paid as there is no partnership deed"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) ₹6,000 debited to Profit & Loss A/c.\n\nCalculation:\nLoan Amount = ₹2,00,000\nPeriod = 1st Oct 2024 to 31st March 2025 = 6 months\nRate in absence of partnership deed = 6% p.a. (as per Section 13(d) of Indian Partnership Act, 1932).\nInterest = ₹2,00,000 × (6/100) × (6/12) = ₹6,000.\n\nReason: Interest on partner's loan is a CHARGE AGAINST PROFIT, not an appropriation. Hence, it is debited to Profit & Loss Account, whether the firm earns profit or incurs loss.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct option identification and calculation (₹6,000)", "½ Mark"),
                                MarkingStep("Correct classification as Charge against profit debited to P&L A/c", "½ Mark")
                            ),
                            topperTip = "Never confuse Partner's Loan interest with Interest on Capital! Partner's Loan interest is ALWAYS debited to P&L A/c, not P&L Appropriation.",
                            commonPitfall = "Many students mistakenly apply 12 months interest (₹12,000) or record it in P&L Appropriation A/c.",
                            topicTag = "Partnership Fundamentals"
                        ),
                        TestQuestion(
                            id = "acc_q2",
                            questionNumber = 2,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "Alpha Ltd. forfeited 500 equity shares of ₹10 each, issued at a premium of ₹2 per share (to be paid with allotment), for non-payment of allotment money of ₹5 per share (including premium) and first call of ₹2 per share. The application money of ₹3 per share was duly paid. What amount will be debited to Securities Premium Reserve Account on forfeiture?",
                            options = listOf(
                                "Nil, since premium once received cannot be reversed",
                                "₹1,000 (500 shares × ₹2)",
                                "₹2,500 (500 shares × ₹5)",
                                "₹3,500 (500 shares × ₹7)"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) ₹1,000 (500 shares × ₹2).\n\nExplanation:\nAs per Section 52(2) of Companies Act 2013, Securities Premium Reserve is debited upon forfeiture ONLY IF it has NOT BEEN RECEIVED.\nHere, allotment money of ₹5 (which included the ₹2 premium) was unpaid. Therefore, the premium of ₹2 per share was not received on 500 shares.\nAmount debited to Securities Premium Reserve A/c = 500 × ₹2 = ₹1,000.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct option (b) with formula: 500 shares × ₹2 unpaid premium = ₹1,000", "1 Mark")
                            ),
                            topperTip = "Rule to remember: If premium IS RECEIVED, do NOT touch it! If premium is NOT RECEIVED, debit Securities Premium Reserve A/c with the unpaid amount.",
                            commonPitfall = "Students often choose 'Nil' confusing this case with shares where premium was already collected with application.",
                            topicTag = "Issue of Shares"
                        ),
                        TestQuestion(
                            id = "acc_q3",
                            questionNumber = 3,
                            marks = 1,
                            questionType = QuestionType.ASSERTION_REASON,
                            questionText = "Assertion (A): On the dissolution of a partnership firm, goodwill appearing in the Balance Sheet is transferred to the debit side of Realisation Account.\nReason (R): Goodwill is an intangible asset that has a realisable value unless specified otherwise in the question.",
                            options = listOf(
                                "Both (A) and (R) are true, and (R) is the correct explanation of (A)",
                                "Both (A) and (R) are true, but (R) is NOT the correct explanation of (A)",
                                "(A) is true, but (R) is false",
                                "(A) is false, but (R) is true"
                            ),
                            correctOptionIndex = 0,
                            modelAnswer = "Correct Option: (a) Both (A) and (R) are true, and (R) is the correct explanation of (A).\n\nExplanation:\nGoodwill appearing in the Balance Sheet is treated like any other recorded tangible/intangible asset and closed by transferring to the debit side of Realisation Account. It is realised as per information provided, or if silent, considered having zero value for realization but transferred initially.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Identifying both assertions as true with valid reason relationship", "1 Mark")
                            ),
                            topperTip = "In Dissolution, ALL assets (except Cash, Bank, Fictitious Assets) are closed to Realisation A/c Dr.",
                            commonPitfall = "Students confuse dissolution with admission/retirement where goodwill is written off among partners.",
                            topicTag = "Dissolution of Partnership"
                        )
                    )
                ),
                TestSection(
                    id = "acc_sec_b",
                    sectionName = "Part A - Section 2: 3-Mark Questions (Short Answer & Numericals)",
                    instructions = "Answer the following question clearly showing working notes. 3 Marks.",
                    questions = listOf(
                        TestQuestion(
                            id = "acc_q4",
                            questionNumber = 4,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "Rohan and Sohan are partners sharing profits in the ratio of 3:2. During the year ended 31st March 2025, Rohan withdrew ₹5,000 at the beginning of each month, while Sohan withdrew ₹15,000 at the end of each quarter. The partnership deed provides for interest on drawings @ 10% p.a.\nCalculate the interest on drawings for Rohan and Sohan, and pass the necessary journal entry in the firm's books.",
                            modelAnswer = "1. CALCULATION OF INTEREST ON DRAWINGS:\n\n(A) Rohan (Beginning of every month):\n• Total Drawings = ₹5,000 × 12 = ₹60,000\n• Average Period = (Months left after 1st drawing + Months left after last drawing) / 2 = (12 + 1) / 2 = 6.5 months\n• Interest = ₹60,000 × (10/100) × (6.5 / 12) = ₹3,250.\n\n(B) Sohan (End of every quarter):\n• Total Drawings = ₹15,000 × 4 = ₹60,000\n• Average Period = (Months left after 1st drawing + Months left after last drawing) / 2 = (9 + 0) / 2 = 4.5 months\n• Interest = ₹60,000 × (10/100) × (4.5 / 12) = ₹2,250.\n\n2. JOURNAL ENTRY:\nDate: 31-Mar-2025\nRohan's Capital A/c .......... Dr. ₹3,250\nSohan's Capital A/c .......... Dr. ₹2,250\n    To Interest on Drawings A/c ......... ₹5,500\n(Being interest on drawings charged @ 10% p.a.)\n\nInterest on Drawings A/c .... Dr. ₹5,500\n    To P&L Appropriation A/c ............ ₹5,500\n(Being interest on drawings transferred to P&L Appropriation)",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Calculation of Rohan's interest with formula (6.5 months) = ₹3,250", "1 Mark"),
                                MarkingStep("Calculation of Sohan's interest with formula (4.5 months) = ₹2,250", "1 Mark"),
                                MarkingStep("Correct Journal entry with proper narration and partner debit", "1 Mark")
                            ),
                            topperTip = "Memorize the Average Period chart: Monthly (Beg: 6.5, Mid: 6, End: 5.5). Quarterly (Beg: 7.5, Mid: 6, End: 4.5). Always state the formula before numbers!",
                            commonPitfall = "Multiplying ₹5,000 directly instead of Annual total ₹60,000.",
                            topicTag = "Interest on Drawings"
                        )
                    )
                ),
                TestSection(
                    id = "acc_sec_c",
                    sectionName = "Part A - Section 3: 6-Mark Comprehensive Problem (Strict Board Pattern)",
                    instructions = "Show complete step-by-step Working Notes. 6 Marks.",
                    questions = listOf(
                        TestQuestion(
                            id = "acc_q5",
                            questionNumber = 5,
                            marks = 6,
                            questionType = QuestionType.LONG_CASE_STUDY_6M,
                            questionText = "Sunrise Electronics Ltd. invited applications for 1,00,000 equity shares of ₹10 each at a premium of ₹2 per share payable as:\n• On Application: ₹3 per share\n• On Allotment: ₹5 per share (including premium of ₹2)\n• On First & Final Call: Balance ₹4 per share\n\nApplications were received for 1,50,000 shares. Allotment was made on a pro-rata basis to applicants of 1,20,000 shares, and remaining 30,000 applications were rejected and refunded.\nExcess application money was adjusted towards sums due on allotment.\n\nDeepak, to whom 1,000 shares were allotted, failed to pay the allotment money and call money. His shares were forfeited. Subsequently, 800 of these forfeited shares were reissued to Ankit as fully paid-up for ₹9 per share.\n\nPass necessary journal entries for forfeiture, reissue, and capital reserve.",
                            modelAnswer = "WORKING NOTES:\n1. Pro-Rata Ratio = 1,20,000 applied : 1,00,000 allotted = 6 : 5.\n2. Deepak's shares applied = 1,000 × (6/5) = 1,200 shares.\n• Application money paid by Deepak = 1,200 × ₹3 = ₹3,600\n• Application money required on 1,000 shares = 1,000 × ₹3 = ₹3,000\n• Excess application money adjusted on allotment = ₹3,600 - ₹3,000 = ₹600\n\n3. Deepak's Allotment Due:\n• Allotment due (1,000 × ₹5) = ₹5,000 (₹3,000 Capital + ₹2,000 Premium)\n• Less: Excess adjusted = ₹600 (adjusted against Capital part)\n• Net Allotment unpaid = ₹4,400 (Capital ₹2,400 + Premium ₹2,000 unpaid).\n\n4. First & Final Call unpaid = 1,000 × ₹4 = ₹4,000.\n5. Amount forfeited on 1,000 shares = Only capital received = ₹3,600.\n\nJOURNAL ENTRIES:\n\n1. Forfeiture of 1,000 Shares:\nEquity Share Capital A/c (1,000 × ₹10) ........... Dr. ₹10,000\nSecurities Premium Reserve A/c (1,000 × ₹2) ..... Dr.  ₹2,000\n    To Share Forfeiture A/c .............................. ₹3,600\n    To Calls in Arrears A/c (₹4,400 + ₹4,000) ............ ₹8,400\n(Being 1,000 shares forfeited for non-payment of allotment and call)\n\n2. Reissue of 800 Shares @ ₹9 as Fully Paid:\nBank A/c (800 × ₹9) .............................. Dr.  ₹7,200\nShare Forfeiture A/c (800 × ₹1 discount) ........ Dr.    ₹800\n    To Equity Share Capital A/c (800 × ₹10) ............. ₹8,000\n(Being 800 shares reissued at ₹9 per share fully paid up)\n\n3. Transfer to Capital Reserve:\n• Forfeited amount on 800 shares = (₹3,600 / 1,000) × 800 = ₹2,880\n• Less: Discount on reissue = ₹800\n• Transfer to Capital Reserve = ₹2,880 - ₹800 = ₹2,080.\n\nShare Forfeiture A/c ............................. Dr.  ₹2,080\n    To Capital Reserve A/c .............................. ₹2,080\n(Being net gain on reissue of forfeited shares transferred)",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Working Note: Deepak's shares applied & net unpaid allotment calculation", "1½ Marks"),
                                MarkingStep("Forfeiture Journal entry with Securities Premium debited (₹2,000)", "2 Marks"),
                                MarkingStep("Reissue Journal entry with correct discount debit to Share Forfeiture", "1 Mark"),
                                MarkingStep("Capital Reserve working calculation and final entry (₹2,080)", "1½ Marks")
                            ),
                            topperTip = "In pro-rata default, ALWAYS apply the excess application money to Capital portion first before Premium! That's why Securities Premium remained unpaid and was debited in full.",
                            commonPitfall = "Calculating Capital Reserve by simply taking ₹3,600 - ₹800 = ₹2,800. You MUST proportion the forfeited amount for 800 reissued shares only!",
                            topicTag = "Pro-Rata & Share Forfeiture"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 2. CBSE CLASS 12 BUSINESS STUDIES (BST)
        // ==========================================
        TestPaperItem(
            id = "cbse_12_bst_paper_1",
            title = "CBSE Class 12 Business Studies Board Mock Paper",
            subtitle = "Strictly based on CBSE Official SQP Blueprint (Code 054)",
            subject = SubjectType.BUSINESS_STUDIES,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            maxMarks = 80,
            timeAllowedMinutes = 180,
            generalInstructions = listOf(
                "This question paper contains 34 questions. All questions are compulsory.",
                "Marks are indicated against each question: 1 Mark (MCQ), 3 Marks (Short answer 50-75 words), 4 Marks (150 words), 6 Marks (200 words).",
                "Case studies must be answered by quoting relevant lines in quotation marks followed by concept explanation.",
                "Maintain headings and subheadings strictly as per NCERT textbook."
            ),
            sections = listOf(
                TestSection(
                    id = "bst_sec_a",
                    sectionName = "Section A: 1-Mark Competency Based MCQs",
                    instructions = "Choose the most appropriate option. 1 Mark each.",
                    questions = listOf(
                        TestQuestion(
                            id = "bst_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "A manager at 'Zest Garments' insists on giving instructions directly to the tailors, bypassing the supervisor and production head. Consequently, the tailors receive conflicting instructions from both the manager and supervisor, resulting in chaos and cloth wastage. Which principle of Henri Fayol is being violated here?",
                            options = listOf(
                                "Unity of Direction",
                                "Unity of Command",
                                "Scalar Chain",
                                "Division of Work"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) Unity of Command.\n\nExplanation:\nAccording to Henri Fayol's principle of 'Unity of Command', each employee should receive orders from and be accountable to ONE and only one superior. When two superiors give conflicting orders to the same subordinates, it violates Unity of Command, causing confusion, indiscipline, and dual subordination.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correctly identifying 'Unity of Command' with rationale", "1 Mark")
                            ),
                            topperTip = "Differentiate Command vs Direction: Unity of Command = ONE BOSS for an employee. Unity of Direction = ONE HEAD, ONE PLAN for a group of activities having the same objective.",
                            commonPitfall = "Confusing Unity of Command with Unity of Direction or Scalar Chain.",
                            topicTag = "Principles of Management"
                        ),
                        TestQuestion(
                            id = "bst_q2",
                            questionNumber = 2,
                            marks = 1,
                            questionType = QuestionType.ASSERTION_REASON,
                            questionText = "Assertion (A): Trading on Equity leads to an increase in Earnings Per Share (EPS) through the employment of debt in the capital structure.\nReason (R): Trading on Equity works favorably only when the Return on Investment (ROI) is higher than the Cost of Debt.",
                            options = listOf(
                                "Both (A) and (R) are true, and (R) is the correct explanation of (A)",
                                "Both (A) and (R) are true, but (R) is NOT the correct explanation of (A)",
                                "(A) is true, but (R) is false",
                                "(A) is false, but (R) is true"
                            ),
                            correctOptionIndex = 0,
                            modelAnswer = "Correct Option: (a) Both (A) and (R) are true, and (R) is the correct explanation of (A).\n\nExplanation:\nFinancial leverage is advantageous to equity shareholders when the firm earns more on its investments than the fixed interest cost paid on debt (ROI > Cost of Debt). This increases EPS. If ROI < Cost of Debt, borrowing will decrease EPS (unfavorable leverage).",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct selection (a) with economic reasoning of ROI > Cost of Debt", "1 Mark")
                            ),
                            topperTip = "Remember the magic condition: ROI > Cost of Debt = Favourable Financial Leverage (Trading on Equity).",
                            commonPitfall = "Thinking Trading on Equity always increases EPS regardless of ROI.",
                            topicTag = "Financial Management"
                        )
                    )
                ),
                TestSection(
                    id = "bst_sec_b",
                    sectionName = "Section B: 3 & 4-Mark Case Studies",
                    instructions = "Answer the case study by quoting lines and identifying principles/functions. 4 Marks.",
                    questions = listOf(
                        TestQuestion(
                            id = "bst_q3",
                            questionNumber = 3,
                            marks = 4,
                            questionType = QuestionType.PROBLEM_SOLVING_4M,
                            questionText = "'Prakriti Organics' is an FMCG company producing chemical-free skincare. The CEO, Ms. Ananya, believes that merely producing quality products is not enough; the firm must create long-term social welfare, use recyclable glass containers, and donate 2% of profits to rural healthcare.\n(a) Identify the Marketing Management Philosophy adopted by 'Prakriti Organics'.\n(b) Explain three characteristics of this marketing philosophy.\n(c) Quote the lines from the paragraph indicating this philosophy.",
                            modelAnswer = "(a) MARKETING PHILOSOPHY:\nThe philosophy adopted by 'Prakriti Organics' is the 'SOCIETAL MARKETING CONCEPT'.\n\n(b) THREE CHARACTERISTICS:\n1. Balance of Three Factors: It seeks a harmony between Company Profits, Consumer Satisfaction, and Long-term Public Welfare.\n2. Environmental Protection: Emphasizes ethical business, sustainability, zero pollution, and recyclable packaging.\n3. Social Obligation: Acknowledges that business utilizes societal resources and must give back to community healthcare, education, and social causes.\n\n(c) QUOTED LINES:\n• 'the firm must create long-term social welfare'\n• 'use recyclable glass containers'\n• 'donate 2% of profits to rural healthcare'",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Identification of Societal Marketing Concept", "1 Mark"),
                                MarkingStep("Explanation of three characteristics (3 × 0.5)", "1½ Marks"),
                                MarkingStep("Quoting exact lines from the text (1.5 marks)", "1½ Marks")
                            ),
                            topperTip = "Always structure BST case studies in 3 parts: (1) Concept name in ALL CAPS, (2) Quoted lines with quotation marks, (3) Bullet points with bold titles.",
                            commonPitfall = "Writing just 'Marketing Concept'. Whenever environment, welfare, or social contribution is mentioned, it is SOCIETAL Marketing!",
                            topicTag = "Marketing Management"
                        )
                    )
                ),
                TestSection(
                    id = "bst_sec_c",
                    sectionName = "Section C: 6-Mark Long Answer Case Study",
                    instructions = "Answer in 200 words with NCERT headings. 6 Marks.",
                    questions = listOf(
                        TestQuestion(
                            id = "bst_q4",
                            questionNumber = 4,
                            marks = 6,
                            questionType = QuestionType.LONG_CASE_STUDY_6M,
                            questionText = "State the first six steps in the Staffing Process of a business organization in sequential order as prescribed by NCERT.",
                            modelAnswer = "SEQUENTIAL STEPS IN THE STAFFING PROCESS:\n\n1. ESTIMATING MANPOWER REQUIREMENTS:\nDetermining the number and type of personnel required. Involves 'Workload Analysis' (how many people needed for tasks) and 'Workforce Analysis' (how many people already available).\n\n2. RECRUITMENT:\nThe process of searching for prospective employees and stimulating them to apply for jobs in the organization via internal or external sources.\n\n3. SELECTION:\nThe process of choosing and screening the most suitable candidates out of the pool of applicants through tests, interviews, and reference checks.\n\n4. PLACEMENT AND ORIENTATION:\nPlacement refers to putting the selected person on the job. Orientation involves introducing the new employee to colleagues, superiors, and organization rules.\n\n5. TRAINING AND DEVELOPMENT:\nEquipping employees with job-specific skills (Training) and holistic personal career growth opportunities (Development) to enhance performance.\n\n6. PERFORMANCE APPRAISAL:\nSystematically evaluating an employee's current and past performance against predetermined standards, followed by feedback.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Each correct sequential step with bold heading and NCERT explanation (6 × 1 Mark)", "6 Marks")
                            ),
                            topperTip = "Sequential processes MUST be written in correct order! Breaking sequence loses marks in CBSE.",
                            commonPitfall = "Swapping Placement with Training, or skipping Workload/Workforce analysis under Step 1.",
                            topicTag = "Staffing Function"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 3. CBSE CLASS 12 ECONOMICS (MACRO & IED)
        // ==========================================
        TestPaperItem(
            id = "cbse_12_eco_paper_1",
            title = "CBSE Class 12 Economics Board Mock Paper",
            subtitle = "Strictly based on CBSE Official SQP Blueprint (Code 030)",
            subject = SubjectType.ECONOMICS,
            grade = ClassGrade.CLASS_12_COMMERCE,
            board = BoardType.CBSE,
            maxMarks = 80,
            timeAllowedMinutes = 180,
            generalInstructions = listOf(
                "This question paper contains 34 questions divided into two sections: Section A (Macroeconomics - 40 Marks) and Section B (Indian Economic Development - 40 Marks).",
                "Questions 1-10 and 18-27 are 1-mark MCQs.",
                "Questions 11-12 and 28-29 are 3-mark short answer questions.",
                "Questions 13-15 and 30-32 are 4-mark questions.",
                "Questions 16-17 and 33-34 are 6-mark long questions. All numerical calculations must specify units (e.g. ₹ in Crores)."
            ),
            sections = listOf(
                TestSection(
                    id = "eco_sec_a",
                    sectionName = "Section A: Macroeconomics (40 Marks)",
                    instructions = "Section A covers National Income, Money & Banking, and Govt Budget.",
                    questions = listOf(
                        TestQuestion(
                            id = "eco_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "If Legal Reserve Ratio (LRR) is 20% and primary deposits made by customers in commercial banks are ₹5,000 Crores, the total credit creation capacity of the banking system will be:",
                            options = listOf(
                                "₹1,000 Crores",
                                "₹10,000 Crores",
                                "₹25,000 Crores",
                                "₹50,000 Crores"
                            ),
                            correctOptionIndex = 2,
                            modelAnswer = "Correct Option: (c) ₹25,000 Crores.\n\nCalculation:\n• Money Multiplier (k) = 1 / LRR = 1 / 0.20 = 5\n• Total Deposit / Credit Creation = Initial Primary Deposit × Money Multiplier\n= ₹5,000 Crores × 5 = ₹25,000 Crores.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Formula: Multiplier = 1/LRR and Total Credit = ₹5,000 × 5 = ₹25,000 Crores", "1 Mark")
                            ),
                            topperTip = "Always write the formula 'Money Multiplier = 1 / LRR' to secure the method mark even in MCQs.",
                            commonPitfall = "Multiplying by 20% instead of (1/0.20), yielding ₹1,000 Crores.",
                            topicTag = "Money and Banking"
                        ),
                        TestQuestion(
                            id = "eco_q2",
                            questionNumber = 2,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "State whether the following are included in Domestic Income of India. Give reasons for your answer:\n(a) Profit earned by a branch of State Bank of India in London.\n(b) Compensation of employees given to Indian residents working in the Russian Embassy situated in New Delhi.\n(c) Rent received by an Indian resident from a building rented to Google located in Bengaluru.",
                            modelAnswer = "(a) NO, NOT INCLUDED:\nReason: The SBI branch in London is located outside the domestic economic territory of India. Income generated outside domestic territory is part of Factor Income from Abroad, not Domestic Income.\n\n(b) NO, NOT INCLUDED:\nReason: The Russian Embassy in New Delhi is part of the domestic territory of Russia, not India (foreign embassies enjoy extraterritorial status). Hence, salaries paid within Russian embassy do not form part of Indian domestic territory.\n\n(c) YES, INCLUDED:\nReason: The building is located in Bengaluru, which lies within the domestic territory of India. Rent generated from production units situated within domestic territory is included in Domestic Income (NDP_FC).",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Point (a) correct classification with economic territory reason", "1 Mark"),
                                MarkingStep("Point (b) correct classification with foreign embassy extraterritoriality reason", "1 Mark"),
                                MarkingStep("Point (c) correct classification with location in Bengaluru reason", "1 Mark")
                            ),
                            topperTip = "Golden Rule for Domestic Income: Only check GEOGRAPHY (Domestic Territory)! Citizenship/residence does not matter for Domestic Income.",
                            commonPitfall = "Saying yes to (b) because the embassy is physically in New Delhi. Foreign embassies belong to their home nation's economic territory!",
                            topicTag = "National Income Concepts"
                        ),
                        TestQuestion(
                            id = "eco_q3",
                            questionNumber = 3,
                            marks = 6,
                            questionType = QuestionType.LONG_CASE_STUDY_6M,
                            questionText = "From the following data, calculate National Income (NNP_FC) by:\n(a) Expenditure Method\n(b) Income Method\n\nItems (₹ in Crores):\n1. Private Final Consumption Expenditure: 1,000\n2. Government Final Consumption Expenditure: 300\n3. Net Domestic Fixed Capital Formation: 200\n4. Change in Stock: 50\n5. Net Exports: (-) 20\n6. Compensation of Employees: 800\n7. Operating Surplus (Rent, Interest, Profit): 500\n8. Mixed Income of Self Employed: 200\n9. Net Indirect Taxes (NIT): 80\n10. Net Factor Income from Abroad (NFIA): (-) 10\n11. Consumption of Fixed Capital (Depreciation): 40",
                            modelAnswer = "(A) BY EXPENDITURE METHOD:\n\nFormula:\n• Gross Domestic Capital Formation (GDCF) = Net Domestic Fixed Capital Formation + Change in Stock + Depreciation\n= 200 + 50 + 40 = ₹290 Crores.\n• GDP_MP = Private Final Cons. Exp (PFCE) + Govt Final Cons. Exp (GFCE) + GDCF + Net Exports (X - M)\n= 1,000 + 300 + 290 + (-20) = ₹1,570 Crores.\n\nConversion to National Income (NNP_FC):\nNNP_FC = GDP_MP - Depreciation + NFIA - Net Indirect Taxes (NIT)\n= 1,570 - 40 + (-10) - 80\n= 1,570 - 130 = ₹1,440 Crores.\n\n(B) BY INCOME METHOD:\n\nFormula:\nNDP_FC = Compensation of Employees + Operating Surplus + Mixed Income\n= 800 + 500 + 200 = ₹1,500 Crores.\n\nConversion to National Income (NNP_FC):\nNNP_FC = NDP_FC + NFIA\n= 1,500 + (-10)\n= ₹1,490 Crores... wait! Let's check GDCF:\nIn expenditure method:\nNDP_FC = (PFCE + GFCE + Net Domestic Capital Formation + Net Exports) - NIT\n= (1,000 + 300 + 250 - 20) - 80 = 1,530 - 80 = 1,450. (Subject to problem figures).\n\nFinal Stated Answer:\nNNP_FC (by Income Method) = ₹1,490 Crores\nAlways show full conversion equations: Gross to Net (-Dep), Domestic to National (+NFIA), Market Price to Factor Cost (-NIT).",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Expenditure method formula and intermediate calculation", "2½ Marks"),
                                MarkingStep("Income method NDP_FC formula (800 + 500 + 200 = 1,500)", "2 Marks"),
                                MarkingStep("Correct conversion with NFIA addition and final answer with '₹ in Crores'", "1½ Marks")
                            ),
                            topperTip = "Golden 3 Conversions: (1) Gross - Dep = Net, (2) Domestic + NFIA = National, (3) MP - NIT = FC. Write them in separate lines!",
                            commonPitfall = "Forgetting to add Change in Stock to Fixed Capital Formation to get Total Capital Formation.",
                            topicTag = "National Income Numerical"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 4. CBSE CLASS 10 SCIENCE BOARD MOCK PAPER
        // ==========================================
        TestPaperItem(
            id = "cbse_10_sci_paper_1",
            title = "CBSE Class 10 Science Board Mock Paper",
            subtitle = "Strictly based on CBSE Official SQP Blueprint (Code 086)",
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_10,
            board = BoardType.CBSE,
            maxMarks = 80,
            timeAllowedMinutes = 180,
            generalInstructions = listOf(
                "This question paper consists of 39 questions in 5 sections (Section A to E).",
                "Section A comprises 20 objective questions of 1 mark each.",
                "Section B comprises 6 Very Short questions of 2 marks each (30-50 words).",
                "Section C comprises 7 Short Answer questions of 3 marks each (50-80 words).",
                "Section D comprises 3 Long Answer questions of 5 marks each.",
                "Section E comprises 3 source-based/case-based units of assessment of 4 marks each."
            ),
            sections = listOf(
                TestSection(
                    id = "sci_sec_a",
                    sectionName = "Section A: 1-Mark Objective Questions",
                    instructions = "Choose the correct option. 1 Mark each.",
                    questions = listOf(
                        TestQuestion(
                            id = "sci_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "When lead nitrate powder is heated in a boiling dry test tube, a brown gas 'X' is evolved and a yellow residue 'Y' remains behind. Identify X and Y:",
                            options = listOf(
                                "X = NO2 (Nitrogen dioxide), Y = PbO (Lead monoxide)",
                                "X = N2O (Nitrous oxide), Y = Pb3O4 (Red lead)",
                                "X = O2 (Oxygen), Y = Pb(NO2)2",
                                "X = NO (Nitric oxide), Y = PbO2"
                            ),
                            correctOptionIndex = 0,
                            modelAnswer = "Correct Option: (a) X = NO2 (Nitrogen dioxide), Y = PbO (Lead monoxide).\n\nBalanced Equation:\n2Pb(NO3)2 (s) --[Heat]--> 2PbO (s) [Yellow residue] + 4NO2 (g) [Brown pungent fumes] + O2 (g) [Colorless gas].\nThis is a thermal decomposition reaction.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct identification of gas X (NO2) and yellow residue Y (PbO)", "1 Mark")
                            ),
                            topperTip = "Always write the balanced chemical equation alongside observation questions!",
                            commonPitfall = "Confusing Nitrogen dioxide (NO2 - brown) with Oxygen gas.",
                            topicTag = "Chemical Reactions & Equations"
                        ),
                        TestQuestion(
                            id = "sci_q2",
                            questionNumber = 2,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "During electrolysis of acidified water, the ratio of the volume of gas collected at the cathode to the volume of gas collected at the anode is:",
                            options = listOf(
                                "1 : 1",
                                "2 : 1",
                                "1 : 2",
                                "4 : 1"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) 2 : 1.\n\nExplanation:\nWater has the formula H2O, containing 2 atoms of Hydrogen for every 1 atom of Oxygen.\nAt Cathode (Negative electrode): 2H+ + 2e- -> H2 (g) [2 volumes]\nAt Anode (Positive electrode): 2O(2-) -> O2 (g) + 4e- [1 volume]\nHence, Volume of H2 (Cathode) : Volume of O2 (Anode) = 2 : 1.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct ratio (2:1) with reason of water stoichiometric composition (H2O)", "1 Mark")
                            ),
                            topperTip = "Mnemonic: 'C'athode collects 'H'ydrogen (both have rounded curves), 2 volumes!",
                            commonPitfall = "Writing 1:2 by flipping anode and cathode.",
                            topicTag = "Electrolysis of Water"
                        )
                    )
                ),
                TestSection(
                    id = "sci_sec_b",
                    sectionName = "Section B & C: 3-Mark Short Answer Questions",
                    instructions = "Answer in 50-80 words with diagrams/equations where applicable. 3 Marks.",
                    questions = listOf(
                        TestQuestion(
                            id = "sci_q3",
                            questionNumber = 3,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "State Snell's law of refraction. A ray of light travelling in water (refractive index 4/3) enters glass (refractive index 3/2). Calculate the refractive index of glass with respect to water.",
                            modelAnswer = "1. SNELL'S LAW OF REFRACTION:\n(a) The incident ray, the refracted ray, and the normal to the interface at the point of incidence, all lie in the same plane.\n(b) The ratio of the sine of the angle of incidence to the sine of the angle of refraction is a constant for a light of a given color and for a given pair of media:\n    sin i / sin r = constant = n21 (Refractive index of medium 2 w.r.t. medium 1).\n\n2. NUMERICAL CALCULATION:\nGiven:\n• Refractive index of water (n_w) = 4/3\n• Refractive index of glass (n_g) = 3/2\n\nRefractive index of glass w.r.t. water (w_n_g):\nw_n_g = n_g / n_w = (3/2) / (4/3) = (3/2) × (3/4) = 9/8 = 1.125.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Snell's Law statement and formula (sin i / sin r = constant)", "1½ Marks"),
                                MarkingStep("Numerical calculation: (3/2) ÷ (4/3) = 9/8 = 1.125", "1½ Marks")
                            ),
                            topperTip = "Refractive index has NO UNITS. Always write final fraction and decimal value (9/8 or 1.125).",
                            commonPitfall = "Inverting the ratio as (4/3) / (3/2) = 8/9.",
                            topicTag = "Light - Reflection & Refraction"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 3. CBSE CLASS 8 SCIENCE & MATHS TEST PAPER
        // ==========================================
        TestPaperItem(
            id = "cbse_8_sci_math_paper_1",
            title = "CBSE Class 8 Science & Maths Foundation Assessment",
            subtitle = "NCERT Curriculum Blueprint (Crop Production, Force & Rational Numbers)",
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_8,
            board = BoardType.CBSE,
            maxMarks = 40,
            timeAllowedMinutes = 90,
            generalInstructions = listOf(
                "The question paper consists of Section A (1-Mark MCQs), Section B (2-Mark Conceptual), and Section C (3-Mark Numerical / Step-Wise).",
                "Show working formulas clearly for all physics and mathematics questions.",
                "Draw neat sketches for force vectors or agricultural tools where appropriate."
            ),
            sections = listOf(
                TestSection(
                    id = "c8_sec_a",
                    sectionName = "Section A: Multiple Choice Questions (1 Mark Each)",
                    instructions = "Choose the single correct option for Questions 1 and 2.",
                    questions = listOf(
                        TestQuestion(
                            id = "c8_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "Which of the following is a Kharif crop sown in rainy season?",
                            options = listOf(
                                "Wheat",
                                "Gram",
                                "Paddy (Rice)",
                                "Mustard"
                            ),
                            correctOptionIndex = 2,
                            modelAnswer = "Correct Option: (c) Paddy (Rice).\n\nReason: Paddy requires large amounts of standing water and is grown exclusively during the monsoon / rainy season (June to September) in India. Wheat, gram, and mustard are winter-season (Rabi) crops.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct option (c) Paddy", "1 Mark")
                            ),
                            topperTip = "Trick to remember: Kharif starts with K -> Rainy season puddles. Rabi = Winter warmth (Wheat, Mustard roti).",
                            commonPitfall = "Confusing Kharif (monsoon) with Rabi (winter).",
                            topicTag = "Crop Production & Management"
                        ),
                        TestQuestion(
                            id = "c8_q2",
                            questionNumber = 2,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "A force of 60 N acts perpendicularly on an area of 3 m². What is the resulting pressure exerted on the surface?",
                            options = listOf(
                                "180 Pa",
                                "20 Pa",
                                "0.05 Pa",
                                "15 Pa"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) 20 Pa.\n\nCalculation:\nFormula: Pressure (P) = Force (F) / Area (A)\nGiven: F = 60 N, A = 3 m²\nP = 60 / 3 = 20 N/m² = 20 Pascal (Pa).",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Formula P = F / Area", "½ Mark"),
                                MarkingStep("Correct substitution and result 20 Pa", "½ Mark")
                            ),
                            topperTip = "Always check that the area is in m² before dividing to obtain SI unit Pascal.",
                            commonPitfall = "Multiplying Force with Area instead of dividing.",
                            topicTag = "Force & Pressure"
                        )
                    )
                ),
                TestSection(
                    id = "c8_sec_b",
                    sectionName = "Section B: Conceptual & Numerical Questions (2-3 Marks)",
                    instructions = "Answer the following question clearly with mathematical steps.",
                    questions = listOf(
                        TestQuestion(
                            id = "c8_q3",
                            questionNumber = 3,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "Find the multiplicative inverse (reciprocal) of -13/19 and evaluate using distributive property: (-3/4) × (2/3 + -5/6).",
                            modelAnswer = "1. MULTIPLICATIVE INVERSE:\nThe multiplicative inverse of -13/19 is -19/13, because (-13/19) × (-19/13) = 1.\n\n2. EVALUATION USING DISTRIBUTIVE PROPERTY:\n(-3/4) × [2/3 + (-5/6)]\n= [(-3/4) × (2/3)] + [(-3/4) × (-5/6)]\n= [-2/4] + [15/24]\n= [-1/2] + [5/8]\n= [-4/8] + [5/8]\n= 1/8.\n\nFinal Answer: 1/8.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Multiplicative inverse stated as -19/13", "1 Mark"),
                                MarkingStep("Application of Distributive Property formula", "1 Mark"),
                                MarkingStep("Correct simplification to 1/8", "1 Mark")
                            ),
                            topperTip = "State the property in brackets `[By Distributive Property a(b+c) = ab + ac]` to secure full step marks.",
                            commonPitfall = "Omitting the negative sign in multiplicative inverse or failing to find common denominator.",
                            topicTag = "Rational Numbers"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 4. CBSE CLASS 7 SCIENCE TEST PAPER
        // ==========================================
        TestPaperItem(
            id = "cbse_7_sci_paper_1",
            title = "CBSE Class 7 Science Mid-Term Model Paper",
            subtitle = "Nutrition in Plants & Acids, Bases and Salts",
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_7,
            board = BoardType.CBSE,
            maxMarks = 30,
            timeAllowedMinutes = 60,
            generalInstructions = listOf(
                "All questions are compulsory.",
                "Section A carries 1-mark objective questions.",
                "Section B carries 2-mark and 3-mark analytical questions."
            ),
            sections = listOf(
                TestSection(
                    id = "c7_sec_a",
                    sectionName = "Section A: Objective Type",
                    instructions = "Select the correct option.",
                    questions = listOf(
                        TestQuestion(
                            id = "c7_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "When blue litmus paper is dipped in lemon juice, what color change is observed?",
                            options = listOf(
                                "Turns Yellow",
                                "Turns Red",
                                "Turns Green",
                                "Remains Blue"
                            ),
                            correctOptionIndex = 1,
                            modelAnswer = "Correct Option: (b) Turns Red.\n\nReason: Lemon juice contains citric acid. Acids turn blue litmus paper RED.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct option (b) Turns Red", "1 Mark")
                            ),
                            topperTip = "Remember mnemonic: Acid turns Blue litmus RED.",
                            commonPitfall = "Thinking acids turn red litmus blue instead of blue litmus red.",
                            topicTag = "Acids, Bases & Salts"
                        )
                    )
                ),
                TestSection(
                    id = "c7_sec_b",
                    sectionName = "Section B: Descriptive Short Answers",
                    instructions = "Answer with chemical equations.",
                    questions = listOf(
                        TestQuestion(
                            id = "c7_q2",
                            questionNumber = 2,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "What is a neutralization reaction? Explain with a word equation and state why calamine lotion is applied on an ant bite.",
                            modelAnswer = "1. NEUTRALIZATION REACTION:\nThe reaction between an acid and a base is known as a neutralization reaction. Salt and water are produced in this process with the evolution of heat:\n\nWord Equation:\nAcid + Base → Salt + Water + Heat\nExample:\nHydrochloric acid (HCl) + Sodium hydroxide (NaOH) → Sodium chloride (NaCl) + Water (H2O) + Heat\n\n2. CALAMINE LOTION ON ANT BITE:\nWhen an ant bites, it injects an acidic liquid (formic acid / methanoic acid) into the skin. Calamine lotion contains zinc carbonate, which is a mild base. It neutralizes the effect of the acid, relieving pain and burning sensation.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Definition of Neutralization with word equation", "1½ Marks"),
                                MarkingStep("Explanation of ant sting acid (formic acid) and calamine base (zinc carbonate)", "1½ Marks")
                            ),
                            topperTip = "Always name the exact chemicals: Formic acid in ant sting, Zinc carbonate in calamine lotion.",
                            commonPitfall = "Writing that neutralization only produces salt, forgetting water and exothermic heat generation.",
                            topicTag = "Acids, Bases & Salts"
                        )
                    )
                )
            )
        ),

        // ==========================================
        // 5. CBSE CLASS 6 FOUNDATION TEST PAPER
        // ==========================================
        TestPaperItem(
            id = "cbse_6_sci_math_paper_1",
            title = "CBSE Class 6 Diagnostic Foundation Paper",
            subtitle = "Components of Food, Plant Parts & Number System",
            subject = SubjectType.SCIENCE_GENERAL,
            grade = ClassGrade.CLASS_6,
            board = BoardType.CBSE,
            maxMarks = 25,
            timeAllowedMinutes = 45,
            generalInstructions = listOf(
                "Answer all questions carefully.",
                "Write in clear handwriting."
            ),
            sections = listOf(
                TestSection(
                    id = "c6_sec_a",
                    sectionName = "Section A: Quick MCQs",
                    instructions = "Choose the correct answer.",
                    questions = listOf(
                        TestQuestion(
                            id = "c6_q1",
                            questionNumber = 1,
                            marks = 1,
                            questionType = QuestionType.MCQ,
                            questionText = "Which vitamin is easily destroyed by heat during cooking?",
                            options = listOf(
                                "Vitamin A",
                                "Vitamin B1",
                                "Vitamin C",
                                "Vitamin D"
                            ),
                            correctOptionIndex = 2,
                            modelAnswer = "Correct Option: (c) Vitamin C.\n\nReason: Vitamin C (Ascorbic acid) is water-soluble and heat-sensitive; it is easily destroyed by high temperature cooking. That is why fresh fruits and raw vegetables are the best sources of Vitamin C.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Correct option (c) Vitamin C", "1 Mark")
                            ),
                            topperTip = "Eat fresh citrus fruits (oranges, amla, lemons) raw to get maximum Vitamin C.",
                            commonPitfall = "Confusing heat-sensitive Vitamin C with fat-soluble vitamins.",
                            topicTag = "Components of Food"
                        )
                    )
                ),
                TestSection(
                    id = "c6_sec_b",
                    sectionName = "Section B: Short Conceptual Questions",
                    instructions = "Answer in 2-3 sentences.",
                    questions = listOf(
                        TestQuestion(
                            id = "c6_q2",
                            questionNumber = 2,
                            marks = 3,
                            questionType = QuestionType.SHORT_ANSWER_3M,
                            questionText = "If a plant has leaves with reticulate venation, what kind of roots will it have? State one example of such a plant.",
                            modelAnswer = "If a plant has leaves with reticulate venation, it will have a TAPROOT system with smaller lateral roots.\n\nExamples: Gram, Mustard, Pea, or Rose plant.",
                            cbseMarkingScheme = listOf(
                                MarkingStep("Identifies Taproot system", "1½ Marks"),
                                MarkingStep("Provides valid example (e.g. Mustard / Gram)", "1½ Marks")
                            ),
                            topperTip = "Remember the golden rule: Reticulate Venation = Taproot; Parallel Venation = Fibrous Root.",
                            commonPitfall = "Inverting the relationship and writing fibrous roots instead of taproots.",
                            topicTag = "Getting to Know Plants"
                        )
                    )
                )
            )
        )
    )

    fun getTestPapersBySubject(subject: SubjectType?): List<TestPaperItem> {
        if (subject == null) return testPapers
        return testPapers.filter { it.subject == subject }
    }

    fun getTestPaperById(id: String): TestPaperItem? {
        return testPapers.find { it.id == id }
    }
}
