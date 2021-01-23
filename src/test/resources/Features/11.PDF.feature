Feature: QA3 - Advanced Automation. Tests for PDF files

  Scenario: PDF01. Check redirection by registration button
    When I verify PDF file contains following data
      | PAGE | LABEL                   | CONTENT |
      | 1    | @/PDF/BASIC_INVOICE     |         |
      | 1    | @/PDF/DATE              |         |
      | 1    | @/PDF/INVOICE_NO        |         |
      | 1    | @/PDF/BILL_TO           |         |
      | 1    | @/PDF/SHIP_TO           |         |
      | 1    | @/PDF/TOTAL             |         |
      | 1    | @/PDF/REMARKS           |         |
      | 1    | @/PDF/SUBTOTAL          |         |
      | 1    | @/PDF/DISCOUNT          |         |
      | 1    | @/PDF/SUBTOTAL_LESS     |         |
      | 1    | @/PDF/TAX_RATE          |         |
      | 1    | @/PDF/TOTAL_TAX         |         |
      | 1    | @/PDF/SHIPPING_HANDLING |         |
      | 1    | @/PDF/OTHER_TOTAL       |         |
      | 1    | @/PDF/INVOICE           |         |
      | 1    | @/PDF/FOR_QUESTIONS     |         |
      | 1    | @/PDF/PAYMENT_DUE_UPON  |         |
      | 1    | @/PDF/PLEASE_MAKE_CHECK |         |
      | 1    | @/PDF/THANK_YOU          |         |
      | 1    | @/PDF/DESCRIPTION       |         |
    Then I verify PDF file content is equal to expected result
    And  I delete 'lv.png' file from the downloads directory