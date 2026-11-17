package com.krishnendu.agent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TicketTriageAgentTest {
 private TicketTriageAgent agent;
 @BeforeEach void setUp() { agent = new TicketTriageAgent(); }
 @Test void loginIssueIsAccount() {
  assertEquals(TicketCategory.ACCOUNT, agent.process("I cannot log in to my account").category());
 }
 @Test void duplicateChargeIsPaymentAndMedium() {
  TicketResult r = agent.process("My card was charged twice for one transaction");
  assertEquals(TicketCategory.PAYMENT, r.category()); assertEquals(Priority.MEDIUM, r.priority());
 }
 @Test void websiteOutageIsTechnical() {
  assertEquals(TicketCategory.TECHNICAL, agent.process("The website is not loading").category());
 }
 @Test void urgentLockedAccountIsHighPriority() {
  TicketResult r = agent.process("Urgent: my account is locked and I cannot access it");
  assertEquals(TicketCategory.ACCOUNT, r.category()); assertEquals(Priority.HIGH, r.priority());
 }
 @Test void ordinaryRequestIsGeneralAndLow() {
  TicketResult r = agent.process("Could you please update my contact preferences?");
  assertEquals(TicketCategory.GENERAL, r.category()); assertEquals(Priority.LOW, r.priority());
 }
 @Test void shortUnclearMessageIsUnknown() {
  assertEquals(TicketCategory.UNKNOWN, agent.process("Help").category());
 }
 @Test void blankMessageIsRejected() {
  assertThrows(IllegalArgumentException.class, () -> agent.process("   "));
 }
 @Test void nullMessageIsRejected() {
  assertThrows(IllegalArgumentException.class, () -> agent.process(null));
 }
 @Test void suggestedReplyIsNotEmpty() {
  String reply = agent.process("I cannot log in to my account").suggestedReply();
  assertNotNull(reply); assertFalse(reply.isBlank());
 }
}