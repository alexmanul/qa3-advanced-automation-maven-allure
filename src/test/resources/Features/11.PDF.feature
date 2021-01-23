@PDF
Feature: QA3 - Advanced Automation. Tests for PDF files

  @PDF
  Scenario: PDF01. Check redirection by registration button
    When I verify PDF file contains following data
      | PAGE | LABEL                   | CONTENT |
      | 1    | @/PDF/BASIC_INVOICE     |         |
      | 1    | @/PDF/INVOICE           |         |
      | 1    | @/PDF/DATE              |         |
      | 1    | @/PDF/INVOICE_NO        |         |
      | 1    | @/PDF/BILL_TO           |         |
      | 1    | @/PDF/SHIP_TO           |         |
      | 1    | @/PDF/DESCRIPTION       |         |
      | 1    | @/PDF/TOTAL             |         |
      | 1    | @/PDF/REMARKS           |         |
      | 1    | @/PDF/SUBTOTAL          |         |
      | 1    | @/PDF/DISCOUNT          |         |
      | 1    | @/PDF/SUBTOTAL_LESS     |         |
      | 1    | @/PDF/PLEASE_MAKE_CHECK |         |