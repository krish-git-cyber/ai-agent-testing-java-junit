# Bug Report: Incorrect Priority for Duplicate Charges

**Bug ID:** BUG-001  
**Status:** Fixed  
**Severity:** Medium  
**Component:** TicketTriageAgent – Priority Classification

## Problem
A customer reporting that their card was charged twice received LOW priority instead of MEDIUM priority.

## Steps to Reproduce
1. Submit the ticket: "My card was charged twice."
2. Run the ticket through the triage agent.
3. Check the returned priority.

## Expected Result
The ticket should receive MEDIUM priority.

## Actual Result
The ticket received LOW priority.

## Root Cause
The priority classification logic recognised "deducted twice" but did not recognise "charged twice".

## Fix Applied
Added "charged twice" to the MEDIUM-priority keyword conditions.

## Verification
Reran the JUnit test suite.

**Result:** 9 tests passed, 0 failures.

**Status:** Fixed and verified.