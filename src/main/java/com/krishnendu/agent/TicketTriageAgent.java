package com.krishnendu.agent;
import java.util.Locale;
/** Deterministic stand-in for an AI support-ticket agent; no external AI API is called. */
public class TicketTriageAgent {
 public TicketResult process(String message) {
  if (message == null || message.isBlank()) throw new IllegalArgumentException("Ticket message must not be empty");
  String input = message.toLowerCase(Locale.ROOT);
  TicketCategory category = classify(input);
  Priority priority = priority(input);
  String reply = switch (category) {
   case ACCOUNT -> "Please check your account details. We can help restore access.";
   case PAYMENT -> "We will review the payment and transaction details.";
   case TECHNICAL -> "Please share the steps to reproduce the technical issue.";
   case GENERAL -> "Thank you for contacting support. We will review your request.";
   case UNKNOWN -> "Please provide more details so support can investigate.";
  };
  return new TicketResult(category, priority, reply);
 }
 private TicketCategory classify(String s) {
  if (has(s,"payment","charged","transaction","refund")) return TicketCategory.PAYMENT;
  if (has(s,"login","log in","password","account","locked")) return TicketCategory.ACCOUNT;
  if (has(s,"website","server","app","error","not loading","crash")) return TicketCategory.TECHNICAL;
  if (s.length() < 12) return TicketCategory.UNKNOWN;
  return TicketCategory.GENERAL;
 }
 private Priority priority(String s) {
  if (has(s,"urgent","immediately","locked out","cannot access")) return Priority.HIGH;
  if (has(s,"not working","failed","error","deducted twice","charged twice")) return Priority.MEDIUM;
  return Priority.LOW;
 }
 private boolean has(String s, String... words) {
  for (String word : words) if (s.contains(word)) return true;
  return false;
 }
}