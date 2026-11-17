# AI Agent Testing Demo — Java + JUnit 5

## Problem statement
A customer-support automation agent reads a ticket and returns an issue category, priority, and suggested reply. Incorrect classification, priority, or empty responses can send work to the wrong queue or frustrate customers.

## Scope and honesty
This repository uses deterministic Java rules as a **stand-in** for an AI agent. It does not call an LLM or external AI service. This keeps unit tests repeatable while demonstrating QA practices. Do not describe it as testing a live AI model.

## Approach
1. Define expected behaviour for typical support requests.
2. Cover positive, negative, boundary, and invalid-input scenarios.
3. Assert category, priority, input validation, and response quality.
4. Run tests after changes to detect regressions.

## Stack
- Java 17
- Maven
- JUnit Jupiter 5

## Run
Prerequisites: JDK 17+ and Maven 3.8+.
```bash
mvn test
```
Detailed Surefire reports are generated under `target/surefire-reports/`.

## Project layout
```text
src/main/java/com/krishnendu/agent/
  Priority.java
  TicketCategory.java
  TicketResult.java
  TicketTriageAgent.java
src/test/java/com/krishnendu/agent/
  TicketTriageAgentTest.java
bug_reports/identified_bugs.md
reports/test_results.md
```

## Test scenarios
| Input / scenario | Expected |
|---|---|
| Cannot log in | ACCOUNT |
| Card charged twice | PAYMENT + MEDIUM |
| Website not loading | TECHNICAL |
| Urgent locked account | HIGH |
| Ordinary preference request | GENERAL + LOW |
| Short unclear message | UNKNOWN |
| Blank or null message | IllegalArgumentException |
| Suggested response | Non-null and non-blank |

## Example
Input: `Urgent: my account is locked and I cannot access it`
Expected: `ACCOUNT`, `HIGH`, and a non-empty suggested reply.

## Bug and risk notes
See `bug_reports/identified_bugs.md`. These are representative defects the test suite is designed to prevent or detect, not claims about defects observed in a production system.

## Next steps for a real AI integration
Mock the model API for deterministic unit tests. Add controlled integration/evaluation tests for malformed model output, prompt injection, hallucinations, privacy leakage, latency, and rate limits.

## Author
Krishnendu Das
