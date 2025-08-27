# Bài tập: Hệ thống thanh toán trực tuyến

## Mục tiêu:

Xây dựng hệ thống xử lý thanh toán với nhiều phương thức khác nhau

## Yêu cầu chi tiết:

### 1. Abstract Class: PaymentMethod

- Fields: `accountId` (String), `balance` (BigDecimal), `isActive` (boolean)
- Abstract method: `processPayment(BigDecimal amount, String description)`
- Abstract method: `validatePayment(BigDecimal amount)`
- Abstract method: `getTransactionFee(BigDecimal amount)`
- Concrete method: `checkBalance(BigDecimal amount)` - throw exception if insufficient
- Method: `deactivateAccount()`, `activateAccount()`

### 2. Interface: Refundable

- Method: `processRefund(String transactionId, BigDecimal amount)`
- Method: `getRefundPolicy()` - return String với policy description
- Method: `calculateRefundFee(BigDecimal amount)`

### 3. Interface: Transferable

- Method: `transferTo(String targetAccount, BigDecimal amount)`
- Method: `getTransferLimit()`
- Method: `validateTransferAccount(String accountId)`

### 4. Concrete Payment Classes:

#### CreditCard extends PaymentMethod implements Refundable

- Fields: `cardNumber`, `expiryDate`, `cvv`, `creditLimit`, `currentDebt`
- Validation: kiểm tra CVV (3 digits), expiry date, credit limit
- Transaction fee: 2.5% của amount
- Refund fee: 1% của refund amount
- Implement security cho card number (mask khi display)

#### DebitCard extends PaymentMethod implements Refundable, Transferable

- Fields: `cardNumber`, `pin`, `dailyLimit`, `todayUsed`
- Validation: PIN verification, daily limit check
- Transaction fee: $0.50 flat fee
- Transfer limit: $5000/day
- Implement PIN encryption/decryption

#### DigitalWallet extends PaymentMethod implements Refundable, Transferable

- Fields: `walletProvider` (PAYPAL, VENMO, etc.), `linkedAccounts` (List)
- Validation: account linking verification
- Transaction fee: 1.5% for commercial, free for personal
- Instant transfers with $0.25 fee

#### BankTransfer extends PaymentMethod implements Transferable

- Fields: `bankCode`, `accountNumber`, `routingNumber`
- Validation: bank code verification, account number format
- Transaction fee: $1.00 flat fee
- Transfer limit: $10000/day
- Processing time: 1-3 business days

### 5. Enum Classes:

```java
public enum TransactionStatus {
    PENDING, COMPLETED, FAILED, CANCELLED, REFUNDED
}

public enum PaymentType {
    PURCHASE, REFUND, TRANSFER, FEE
}

public enum WalletProvider {
    PAYPAL(0.025, 10000),
    VENMO(0.015, 3000),
    APPLE_PAY(0.020, 5000);
    // Include fee rates and limits
}
```

### 6. Class: Transaction

- Fields: `transactionId`, `paymentMethod`, `amount`, `status`, `timestamp`
- Fields: `description`, `recipientAccount`, `transactionType`
- Method: `calculateTotalCost()` - amount + fees
- Method: `rollback()` - reverse transaction if possible
- Implement transaction history tracking

### 7. Class: PaymentProcessor (Factory Pattern)

- Static method: `createPaymentMethod(PaymentType type, Map<String, Object> params)`
- Method: `processPaymentChain(List<PaymentMethod> methods, BigDecimal amount)`
  - Try multiple payment methods until success
- Method: `validateAllPaymentMethods()`
- Method: `generatePaymentReport(Date from, Date to)`

### 8. Class: SecurityManager

- Method: `encryptSensitiveData(String data)`
- Method: `decryptSensitiveData(String encryptedData)`
- Method: `validateSecurityCode(PaymentMethod method, String code)`
- Method: `detectFraudulentTransaction(Transaction transaction)`
- Implement rate limiting cho failed attempts

### 9. Exception Classes:

```java
public class InsufficientFundsException extends PaymentException
public class PaymentDeclinedException extends PaymentException
public class InvalidPaymentMethodException extends PaymentException
public class SecurityViolationException extends PaymentException
public class DailyLimitExceededException extends PaymentException
```

### 10. Advanced Features:

- Implement Observer pattern cho transaction notifications
- Sử dụng Strategy pattern cho fee calculation
- Multi-currency support với conversion rates
- Transaction batching và processing
- Audit trail cho tất cả operations

### 11. Testing Scenarios:

- Test failed payments với multiple methods
- Test security validations và fraud detection
- Test concurrent transactions
- Test refund workflows
- Test transfer limits và validations
- Performance testing với large transaction volumes

## Validation Rules:

- Tất cả monetary amounts phải sử dụng BigDecimal
- Sensitive data phải được encrypt
- Transaction IDs phải unique và non-sequential
- Rate limiting cho security operations
- Proper error handling và logging

## Đánh giá:

- Security implementation (25%)
- Design patterns usage (20%)
- Error handling (20%)
- Code organization (20%)
- Testing coverage (15%)
