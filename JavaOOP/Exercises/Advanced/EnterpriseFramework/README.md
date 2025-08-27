# Bài tập: Enterprise Application Framework

## Mục tiêu:

Xây dựng framework cho enterprise applications với focus vào scalability và maintainability

## Yêu cầu chi tiết:

### 1. Abstract Class: BaseEntity

- Fields: `id` (UUID), `createdAt`, `updatedAt`, `version` (for optimistic locking)
- Method: `updateTimestamp()`
- Method: `incrementVersion()`
- Abstract method: `validate()`
- Implement equals(), hashCode() based trên id

### 2. Interface: Repository<T extends BaseEntity>

- Method: `save(T entity)`
- Method: `findById(UUID id)`
- Method: `findAll()`
- Method: `delete(UUID id)`
- Method: `existsById(UUID id)`
- Method: `findByCriteria(SearchCriteria criteria)`

### 3. Interface: Service<T extends BaseEntity>

- Method: `create(T entity)`
- Method: `update(UUID id, T entity)`
- Method: `delete(UUID id)`
- Method: `findById(UUID id)`
- Method: `findAll(PageRequest pageRequest)`

### 4. Interface: Cacheable

- Method: `getCacheKey()`
- Method: `getCacheTTL()` - Time To Live in seconds
- Method: `shouldCache()`
- Method: `evictCache()`

### 5. Interface: Auditable

- Method: `getAuditInfo()`
- Method: `recordOperation(AuditOperation operation, String userId)`
- Method: `getAuditHistory()`

### 6. Core Entity Classes:

#### User extends BaseEntity implements Auditable

- Fields: `username`, `email`, `passwordHash`, `roles`, `isActive`
- Fields: `lastLoginAt`, `failedLoginAttempts`, `accountLockedUntil`
- Method: `authenticate(String password)`
- Method: `assignRole(Role role)`
- Method: `hasPermission(Permission permission)`
- Method: `lockAccount(Duration lockDuration)`

#### Role extends BaseEntity

- Fields: `name`, `description`, `permissions`
- Method: `addPermission(Permission permission)`
- Method: `removePermission(Permission permission)`
- Method: `hasPermission(Permission permission)`

#### Permission extends BaseEntity

- Fields: `name`, `resource`, `action` (CREATE, READ, UPDATE, DELETE)
- Method: `matches(String resource, String action)`

### 7. Service Layer Implementation:

#### UserService extends BaseService<User> implements Cacheable

- Method: `authenticateUser(String username, String password)`
- Method: `registerUser(UserRegistrationRequest request)`
- Method: `changePassword(UUID userId, String oldPassword, String newPassword)`
- Method: `resetPassword(String email)`
- Method: `searchUsers(UserSearchCriteria criteria)`
- Implement caching cho frequently accessed users

#### RoleService extends BaseService<Role>

- Method: `createRoleWithPermissions(String roleName, List<Permission> permissions)`
- Method: `assignRoleToUser(UUID userId, UUID roleId)`
- Method: `getRolesByUser(UUID userId)`
- Method: `updateRolePermissions(UUID roleId, List<Permission> permissions)`

### 8. Abstract Class: BaseService<T extends BaseEntity>

- Fields: `repository`, `cacheManager`, `auditService`, `validator`
- Implement common CRUD operations
- Method: `validateBeforeSave(T entity)`
- Method: `performAudit(T entity, AuditOperation operation)`
- Template method pattern cho save operations

### 9. Design Patterns Implementation:

#### Strategy Pattern: ValidationStrategy

```java
public interface ValidationStrategy<T> {
    ValidationResult validate(T entity);
}

public class UserValidationStrategy implements ValidationStrategy<User>
public class EmailValidationStrategy implements ValidationStrategy<String>
public class PasswordValidationStrategy implements ValidationStrategy<String>
```

#### Decorator Pattern: ServiceDecorator

```java
public abstract class ServiceDecorator<T> implements Service<T>
public class CachingServiceDecorator<T> extends ServiceDecorator<T>
public class AuditingServiceDecorator<T> extends ServiceDecorator<T>
public class ValidationServiceDecorator<T> extends ServiceDecorator<T>
```

#### Builder Pattern: QueryBuilder

```java
public class QueryBuilder {
    public QueryBuilder select(String... fields)
    public QueryBuilder from(String table)
    public QueryBuilder where(String condition)
    public QueryBuilder orderBy(String field, SortDirection direction)
    public QueryBuilder limit(int count)
    public Query build()
}
```

### 10. Exception Handling Framework:

#### Custom Exception Hierarchy:

```java
public class BusinessException extends Exception
public class ValidationException extends BusinessException
public class EntityNotFoundException extends BusinessException
public class DuplicateEntityException extends BusinessException
public class InsufficientPermissionException extends BusinessException
```

#### Global Exception Handler:

```java
public class GlobalExceptionHandler {
    public ErrorResponse handleValidationException(ValidationException ex)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex)
    public ErrorResponse handleGenericException(Exception ex)
}
```

### 11. Configuration Management:

#### Interface: ConfigurationProvider

- Method: `getProperty(String key, Class<T> type)`
- Method: `getProperty(String key, T defaultValue)`
- Method: `getAllProperties()`
- Method: `reloadConfiguration()`

#### Classes:

- `DatabaseConfigurationProvider` - load từ database
- `FileConfigurationProvider` - load từ properties files
- `EnvironmentConfigurationProvider` - load từ environment variables

### 12. Event-Driven Architecture:

#### Interface: Event

- Method: `getEventType()`
- Method: `getTimestamp()`
- Method: `getPayload()`

#### Interface: EventListener<T extends Event>

- Method: `handle(T event)`
- Method: `canHandle(Event event)`

#### Class: EventBus (Singleton)

- Method: `publish(Event event)`
- Method: `subscribe(EventListener<?> listener)`
- Method: `unsubscribe(EventListener<?> listener)`
- Async event processing với thread pool

### 13. Security Framework:

#### Interface: SecurityContext

- Method: `getCurrentUser()`
- Method: `hasRole(String roleName)`
- Method: `hasPermission(String resource, String action)`
- Method: `authenticate(Credentials credentials)`

#### Class: JWTTokenManager

- Method: `generateToken(User user)`
- Method: `validateToken(String token)`
- Method: `extractClaims(String token)`
- Method: `refreshToken(String refreshToken)`

### 14. Monitoring và Metrics:

#### Interface: MetricsCollector

- Method: `incrementCounter(String name, Map<String, String> tags)`
- Method: `recordTimer(String name, Duration duration)`
- Method: `recordGauge(String name, double value)`

#### Class: PerformanceMonitor

- Method: `startTimer(String operationName)`
- Method: `endTimer(String operationName)`
- Method: `recordMethodExecution(String methodName, long executionTime)`
- AOP integration cho automatic monitoring

### 15. Advanced Features:

#### Multi-tenancy Support:

- Interface: TenantAware với getCurrentTenant()
- TenantContext để manage tenant information
- Data isolation strategies

#### Internationalization:

- MessageSource interface cho localized messages
- ResourceBundleMessageSource implementation
- Locale-aware error messages

#### Data Transfer Objects:

- Abstract class BaseDTO với mapping utilities
- UserDTO, RoleDTO với proper validation annotations
- Automatic mapping between entities và DTOs

### 16. Testing Framework:

- AbstractRepositoryTest với test database setup
- AbstractServiceTest với mocking framework integration
- Integration tests với TestContainers
- Performance tests với JMH

### 17. Documentation Requirements:

- API documentation với detailed examples
- Architecture diagrams
- Configuration guide
- Deployment instructions
- Performance tuning guide

## Technical Requirements:

- Thread-safe implementations
- Database transaction management
- Connection pooling
- Proper logging với structured formats
- Health checks và metrics endpoints
- Graceful shutdown handling

## Đánh giá:

- Architecture design (25%)
- Security implementation (20%)
- Performance optimization (20%)
- Code quality và best practices (20%)
- Documentation và testing (15%)
